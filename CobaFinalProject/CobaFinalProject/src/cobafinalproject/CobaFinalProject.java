
package cobafinalproject;

import cobafinalproject.db.DBHelper;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CobaFinalProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

   
    public static void main(String[] args) {
        try {
            
            NasabahDataModel ndm = new NasabahDataModel("MYSQL");
            Individu in = new Individu(1L,2L,"Doni","Bekasi",3,new Rekening(10, 1000.0));
            Individu on = new Individu(3L,1L,"Dono","Bandung",1,new Rekening(11, 1010.0));
            ndm.addNasabah(in);
            ndm.addNasabah(on);
            System.out.println("Sukses ditambahkan");
        } catch (SQLException ex) {
            Logger.getLogger(CobaFinalProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
