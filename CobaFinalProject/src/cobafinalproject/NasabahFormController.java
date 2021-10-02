
package cobafinalproject;

import java.net.URL;
import java.sql.PreparedStatement;
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
    private TextField tfIdNasabahP;

    @FXML
    private TextField tfNamaP;

    @FXML
    private TextField tfAlamatP;
    
    @FXML
    private TextField tfNib;

    @FXML
    private TextField tfNorekeningP;

    @FXML
    private TextField tfSaldoP;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnClear;
    
    @FXML
    private Button btnSaveP;

    @FXML
    private Button btnReloadP;
    
    @FXML
    private Button btnClearP;

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
    private TableView<Perusahaan> tblKoperasiP;

    @FXML
    private TableColumn<Perusahaan,Integer> colIdNasabahP;

    @FXML
    private TableColumn<Perusahaan,String> colNamaP;

    @FXML
    private TableColumn<Perusahaan, String> colAlamatP;

    @FXML
    private TableColumn<Perusahaan,String> colNib;
    
    @FXML
    private TableView<Rekening> tblRekening;

    @FXML
    private TableColumn<Rekening,Integer> colNoRekening;

    @FXML
    private TableColumn<Rekening,Double> colSaldo;
    
    
    @FXML
    private TableView<Rekening> tblRekeningP;

    @FXML
    private TableColumn<Rekening,Integer> colNoRekeningP;

    @FXML
    private TableColumn<Rekening,Double> colSaldoP;

    @FXML
    private TextField tfNewIdNasabah;

    @FXML
    private TextField tfNewNoRek;

    @FXML
    private TextField tfNewSaldo;

    @FXML
    private Button btnAddAccount;
    
    @FXML
    private TextField tfNewIdNasabahP;

    @FXML
    private TextField tfNewNoRekP;

    @FXML
    private TextField tfNewSaldoP;

    @FXML
    private Button btnAddAccountP;
    
    @FXML
    private Button TrkSaldo;

    @FXML
    private Button TmbSaldo;
    
    @FXML
    private Button TrkSaldoP;

    @FXML
    private Button TmbSaldoP;


    @FXML
    private Label lblSaveStat;
    @FXML
    private Label lblSaveStatP;
    @FXML
    private Label lbDBStatus;
    @FXML
    private NasabahDataModel ndm;
    
    private Rekening bank;
    
    //check
    @FXML
    void handleAddAccountButton(ActionEvent event) {
     try {
        
         Rekening rek = new Rekening(Integer.parseInt(tfNewNoRek.getText()),Double.parseDouble(tfNewSaldo.getText()));
         ndm.addRekening(Integer.parseInt(tfNewIdNasabah.getText()),rek);
         viewDataRekening(Integer.parseInt(tfNewIdNasabah.getText()));
         btnReload.fire();
         tfNewSaldo.setText("");
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    //check
      @FXML
    void handleAddAccountButtonP(ActionEvent event) {
     try {
         
         Rekening rek = new Rekening(Integer.parseInt(tfNewNoRekP.getText()),Double.parseDouble(tfNewSaldoP.getText()));
         ndm.addRekening(Integer.parseInt(tfNewIdNasabahP.getText()),rek);
         viewDataRekeningP(Integer.parseInt(tfNewIdNasabahP.getText()));
         btnReloadP.fire();
         tfNewSaldoP.setText("");
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }    
    }
    //check
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
    //check
    @FXML
    void handleClearButtonP(ActionEvent event) {
     try {
         tfIdNasabahP.setText(""+ndm.nextNasabahID()); 
         tfNorekeningP.setText(tfIdNasabah.getText()+"01");
         tfNamaP.setText("");
         tfAlamatP.setText("");
         tfNib.setText("");
         tfSaldoP.setText("");
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
    void handleReloadButtonP(ActionEvent event) {
     ObservableList<Perusahaan> data = ndm.getPerusahaan();
     colNib.setCellValueFactory(new PropertyValueFactory<>("nib"));
     colNamaP.setCellValueFactory(new PropertyValueFactory<>("nama"));
     colAlamatP.setCellValueFactory(new PropertyValueFactory<>("alamat"));
     colIdNasabahP.setCellValueFactory(new PropertyValueFactory<>("idNasabah"));
     tblKoperasiP.setItems(null);
     tblKoperasiP.setItems(data);
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
    
      @FXML
    void handleSaveButtonP(ActionEvent event) {
         Perusahaan perusahaan = new Perusahaan(tfNib.getText()
                ,tfNamaP.getText()
                ,tfAlamatP.getText()
                ,Integer.parseInt(tfIdNasabahP.getText())
                ,new Rekening(Integer.parseInt(tfNorekeningP.getText()),Double.parseDouble(tfSaldoP.getText())));
        
     try {
         ndm.addNasabah(perusahaan);
           
         lblSaveStatP.setText("Account berhasil dibuat");
         btnReload.fire();
         btnClear.fire();
     } catch (SQLException ex) {
           
         lblSaveStatP.setText("Account gagal dibuat");
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
        @FXML
    void handleTambahSaldo(ActionEvent event) throws SQLException {
        double saldobaru = bank.getSaldo()+ Double.parseDouble(tfNewSaldo.getText());
        String Update = "UPDATE `rekening` SET `saldo` ="+ saldobaru + " WHERE `no_rekening` = "+bank.getNoRekening();
        PreparedStatement n = ndm.conn.prepareStatement(Update);
        n.execute();
        viewDataRekening(Integer.parseInt(tfNewIdNasabah.getText()));
    }

    @FXML
    void handleTambahSaldoP(ActionEvent event) throws SQLException {
        double saldobaru = bank.getSaldo()+ Double.parseDouble(tfNewSaldoP.getText());
        String Update = "UPDATE `rekening` SET `saldo` ="+ saldobaru + " WHERE `no_rekening` = "+bank.getNoRekening();
        PreparedStatement n = ndm.conn.prepareStatement(Update);
        n.execute();
        viewDataRekeningP(Integer.parseInt(tfNewIdNasabahP.getText()));
    }

    @FXML
    void handleTarikSaldoP(ActionEvent event) throws SQLException {
        double saldobaru = bank.getSaldo()- Double.parseDouble(tfNewSaldoP.getText());
        String Update = "UPDATE `rekening` SET `saldo` ="+ saldobaru + " WHERE `no_rekening` = "+bank.getNoRekening();
        PreparedStatement n = ndm.conn.prepareStatement(Update);
        n.execute();
        viewDataRekeningP(Integer.parseInt(tfNewIdNasabahP.getText()));
    }

    @FXML
    void handleTariksaldo(ActionEvent event) throws SQLException {
        double saldobaru = bank.getSaldo()- Double.parseDouble(tfNewSaldo.getText());
        String Update = "UPDATE `rekening` SET `saldo` ="+ saldobaru + " WHERE `no_rekening` = "+bank.getNoRekening();
        PreparedStatement n = ndm.conn.prepareStatement(Update);
        n.execute();
        viewDataRekening(Integer.parseInt(tfNewIdNasabah.getText()));
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
         btnClearP.fire();
         btnReloadP.fire();
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
     //
    tblRekening.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblRekening.getSelectionModel().getSelectedItem() != null){
                
                bank  = tblRekening.getSelectionModel().getSelectedItem();

            }
        
    } );
    
     tblRekeningP.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblRekeningP.getSelectionModel().getSelectedItem() != null){
                
                bank  = tblRekeningP.getSelectionModel().getSelectedItem();

            }
        
    } );
     
     tblKoperasiP.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tblKoperasiP.getSelectionModel().getSelectedItem() != null){
                Perusahaan p =  tblKoperasiP.getSelectionModel().getSelectedItem();
                viewDataRekeningP(p.getIdNasabah());
                btnAddAccountP.setDisable(false);
                tfNewIdNasabahP.setText("" + p.getIdNasabah());
                try {
                   tfNewNoRekP.setText(""+ndm.nextRekeningNo(p.getIdNasabah()));
                } catch (SQLException ex) {
                    Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    } 
    
    
    ///check
    public void viewDataRekening(int idNasabah){
     ObservableList<Rekening> data = ndm.getRekening(idNasabah);
     colNoRekening.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
     colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
     tblRekening.setItems(null);
     tblRekening.setItems(data);
    }
    //check
     public void viewDataRekeningP(int idNasabah){
     ObservableList<Rekening> data = ndm.getRekening(idNasabah);
     colNoRekeningP.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
     colSaldoP.setCellValueFactory(new PropertyValueFactory<>("saldo"));
     tblRekeningP.setItems(null);
     tblRekeningP.setItems(data);
    }
}
