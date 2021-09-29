
package cobafinalproject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


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
    private TableView<?> tblKoperasi;

    @FXML
    private TableColumn<?, ?> colIdNasabah;

    @FXML
    private TableColumn<?, ?> colNama;

    @FXML
    private TableColumn<?, ?> colAlamat;

    @FXML
    private TableColumn<?, ?> colNik;

    @FXML
    private TableColumn<?, ?> colNpwp;

    @FXML
    private TableView<?> tblRekening;

    @FXML
    private TableColumn<?, ?> colNoRekening;

    @FXML
    private TableColumn<?, ?> colSaldo;

    @FXML
    private TextField tfNewIdNasabah;

    @FXML
    private TextField tfNewNoRek;

    @FXML
    private TextField tfNewSaldo;

    @FXML
    private Button btnAddAccount;

    @FXML
    
//    private Label lblSaveStatus;
    private Label lbDBStatus;
    private NasabahDataModel ndm;
    @FXML
    void handleAddAccountButton(ActionEvent event) {

    }

    @FXML
    void handleClearButton(ActionEvent event) {

    }

    @FXML
    void handleReloadButton(ActionEvent event) {

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
           
//         lblSaveStatus.setText("Account berhasil dibuat");
     } catch (SQLException ex) {
           
//         lblSaveStatus.setText("Account gagal dibuat");
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
         tfIdNasabah.setText(""+ndm.nextNasabahID());
         tfNorekening.setText(tfIdNasabah.getText()+"01");
         
     } catch (SQLException ex) {
         Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    
    
}
