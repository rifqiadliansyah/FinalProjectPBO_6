
package cobafinalproject;

import cobafinalproject.db.DBHelper;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class NasabahDataModel {
    public final Connection conn;

    public NasabahDataModel(String driver) throws SQLException {
    this.conn= DBHelper.getConnection(driver);
    }
    
    public void addNasabah(Individu i) throws SQLException{
     String insertNasabah = "INSERT INTO nasabah (idNasabah,nama,alamat)"+" VALUES (?,?,?)";
     String insertIndividu = "INSERT INTO individu (idNasabah,nik,npwp)"+" VALUES(?,?,?)";
     String insertRekening = "INSERT INTO rekening (no_rekening,saldo,idNasabah)"+" VALUES(?,?,?)"; 
     
     PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
     stmtNasabah.setInt(1,i.getIdNasabah());
     stmtNasabah.setString(2,i.getNama());
     stmtNasabah.setString(3, i.getAlamat());
     stmtNasabah.execute();
     
     PreparedStatement stmtIndividu = conn.prepareStatement(insertIndividu);
     stmtIndividu.setInt(1,i.getIdNasabah());
     stmtIndividu.setLong(2,i.getNik());
     stmtIndividu.setLong(3,i.getNpwp());
     stmtIndividu.execute();
     
     PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
     stmtRekening.setInt(1,i.getRekening().get(0).getNoRekening());
     stmtRekening.setDouble(2,i.getRekening().get(0).getSaldo());
     stmtRekening.setInt(3,i.getIdNasabah());
     stmtRekening.execute();
    }
    
    public void addNasabah(Perusahaan i) throws SQLException{
     String insertNasabah = "INSERT INTO nasabah (idNasabah,nama,alamat)"+" VALUES (?,?,?)";
     String insertPerusahaan = "INSERT INTO perusahaan (idNasabah,nib)"+" VALUES(?,?)";
     String insertRekening = "INSERT INTO rekening (no_rekening,saldo,idNasabah)"+" VALUES(?,?,?)"; 
     
     PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
     stmtNasabah.setInt(1,i.getIdNasabah());
     stmtNasabah.setString(2,i.getNama());
     stmtNasabah.setString(3, i.getAlamat());
     stmtNasabah.execute();
     
     PreparedStatement stmtIndividu = conn.prepareStatement(insertPerusahaan);
     stmtIndividu.setInt(1,i.getIdNasabah());
     stmtIndividu.setString(2,i.getNib());
     stmtIndividu.execute();
     
     PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
     stmtRekening.setInt(1,i.getRekening().get(0).getNoRekening());
     stmtRekening.setDouble(2,i.getRekening().get(0).getSaldo());
     stmtRekening.setInt(3,i.getIdNasabah());
     stmtRekening.execute();
    }
    
    public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();
        //Tandaiin kalo salah
        String sql = "SELECT `nik`,`npwp`,`nama`,`alamat`,`idNasabah` FROM `nasabah` NATURAL JOIN `individu` ORDER BY nama";
        
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
             String sqlRekening = "SELECT no_rekening, saldo FROM rekening WHERE idNasabah= "+rs.getInt(1);
             ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
             ArrayList<Rekening> dataRekening = new ArrayList<>();
             while(rsRekening.next()){
             dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
             }
             data.add(new Individu(rs.getLong(1),rs.getLong(2),rs.getString(3),rs.getString(4),rs.getInt(5),dataRekening));
            }
           

        } catch (SQLException ex) {
            
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    } 
    
       public ObservableList<Perusahaan> getPerusahaan(){
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();
        //Tandaiin kalo salah
        String sql = "SELECT `nib`,`nama`,`alamat`,`idNasabah` FROM `nasabah` NATURAL JOIN `perusahaan` ORDER BY nama";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
             String sqlRekening = "SELECT no_rekening,saldo FROM rekening WHERE idNasabah= "+rs.getInt(1);
             ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
             ArrayList<Rekening> dataRekening = new ArrayList<>();
             while(rsRekening.next()){
             dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
             }
             data.add(new Perusahaan(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),dataRekening));
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    } 
       //ambil data rekening
       public ObservableList<Rekening> getRekening(int idNasabah){
        ObservableList<Rekening> data = FXCollections.observableArrayList();
        
        String sql = "SELECT `no_rekening`,`saldo` FROM `rekening` WHERE idNasabah= "+idNasabah;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                data.add(new Rekening(rs.getInt(1),rs.getDouble(2)));
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
       }
       
       //method supaya idnasabah tidak sama
       public int nextNasabahID () throws SQLException{
       String sql = "SELECT MAX(idNasabah) FROM nasabah";
       ResultSet rs = conn.createStatement().executeQuery(sql);
       while(rs.next()){
              return rs.getInt(1)==0? 1000001 :rs.getInt(1)+1;
            }
       return 1000001; 
       }
       
       //setting no rekening agar sma mulainya kek ID nasabah
       public int nextRekeningNo (int idNasabah) throws SQLException{
       String sql = "SELECT MAX(no_rekening) FROM rekening WHERE idNasabah="+idNasabah;
       ResultSet rs = conn.createStatement().executeQuery(sql);
       while(rs.next()){
              return rs.getInt(1)+1;
            }
       return 0; 
       }
       
     public void addRekening(int idNasabah, Rekening rekening) throws SQLException{
     String insertNasabah = "INSERT INTO rekening (idNasabah,no_rekening,saldo)"+" VALUES (?,?,?)";
     PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
     stmtNasabah.setInt(1,idNasabah);
     stmtNasabah.setInt(2,rekening.getNoRekening());
     stmtNasabah.setDouble(3,rekening.getSaldo());
     stmtNasabah.execute();
    }
}
