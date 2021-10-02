
package cobafinalproject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class NasabahFormController implements Initializable {
    
 @FXML
    private TextField tfIdNasabah;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfNik;

    @FXML
    private TextField tfNpwp;

    @FXML
    private TextField tfNorekening;

    @FXML
    private TextField tfSaldo;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnClear;

    @FXML
    private TableView<Individu> tblKoperasi;
    
    @FXML
    private TableColumn<Individu,Integer> colIdNasabah;

    @FXML
    private TableColumn<Individu, String> colNama;

    @FXML
    private TableColumn<Individu, String> colAlamat;

    @FXML
    private TableColumn<Individu, Long> colNik;

    @FXML
    private TableColumn<Individu, Long> colNpwp;

    @FXML
    private TableView<Rekening> tblRekening;

    @FXML
    private TableColumn<Rekening,Integer> colNoRekening;

    @FXML
    private TableColumn<Rekening,Double> colSaldo;

    @FXML
    private TextField tfNewIdNasabah;

    @FXML
    private TextField tfNewNoRek;

    @FXML
    private TextField tfNewSaldo;

    @FXML
    private Button btnAddAccount;
    
    @FXML
    private Label lblSaveStat;

    @FXML
    private Label lbDBStatus;
    @FXML
    private NasabahDataModel ndm;
    
    @FXML
    void handleAddAccountButton(ActionEvent event) {
     try {
        
         Rekening rek = new Rekening(Integer.parseInt(tfNewIdNasabah.getText()),Double.parseDouble(tfNewSaldo.getText()));
         ndm.addRekening(Integer.parseInt(tfNewIdNasabah.getText()),rek);
         viewDataRekening(Integer.parseInt(tfNewIdNasabah.getText()));
         btnReload.fire();
         tfNewSaldo.setText("");
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
 
    
    @FXML
    void handleClearButton(ActionEvent event) {
     try {
         tfIdNasabah.setText(""+ndm.nextNasabahID()); 
         tfNorekening.setText(tfIdNasabah.getText()+"01");
         tfNama.setText("");
         tfAlamat.setText("");
         tfNik.setText("");
         tfNpwp.setText("");
         tfSaldo.setText("");
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
  

    @FXML
    void handleReloadButton(ActionEvent event) {
     ObservableList<Individu> data = ndm.getIndividu();
     colNik.setCellValueFactory(new PropertyValueFactory<>("nik"));
     colNpwp.setCellValueFactory(new PropertyValueFactory<>("npwp"));
     colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
     colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
     colIdNasabah.setCellValueFactory(new PropertyValueFactory<>("idNasabah"));
     tblKoperasi.setItems(null);
     tblKoperasi.setItems(data);
     btnAddAccount.setDisable(true);
    }
    
   
    @FXML
    void handleSaveButton(ActionEvent event) {
        Individu individu = new Individu(Long.parseLong(tfNik.getText())
                ,Long.parseLong(tfNpwp.getText())
                ,tfNama.getText()
                ,tfAlamat.getText()
                ,Integer.parseInt(tfIdNasabah.getText())
                ,new Rekening(Integer.parseInt(tfNorekening.getText()),Double.parseDouble(tfSaldo.getText())));
        
     try {
         ndm.addNasabah(individu);
           
         lblSaveStat.setText("Account berhasil dibuat");
         btnReload.fire();
         btnClear.fire();
     } catch (SQLException ex) {
           
         lblSaveStat.setText("Account gagal dibuat");
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
         // TODO
         ndm = new NasabahDataModel("MYSQL");
         //checking database terkoneksi atau tidak
         lbDBStatus.setText(ndm.conn==null?"Not Connected":"Connected");
         btnClear.fire();
         btnReload.fire();
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     tblKoperasi.getSelectionModel().selectedIndexProperty().addListener(listener->{
     if(tblKoperasi.getSelectionModel().getSelectedItem()!=null){
     Individu individu = tblKoperasi.getSelectionModel().getSelectedItem();
     viewDataRekening(individu.getIdNasabah());
     btnAddAccount.setDisable(false);
     tfNewIdNasabah.setText(""+individu.getIdNasabah());
         try {
             tfNewNoRek.setText(""+ndm.nextRekeningNo(individu.getIdNasabah()));
         } catch (SQLException ex) {
             Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     });
    }    
    
    public void viewDataRekening(int idNasabah){
     ObservableList<Rekening> data = ndm.getRekening(idNasabah);
     colNoRekening.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
     colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
     tblRekening.setItems(null);
     tblRekening.setItems(data);
    }
}
