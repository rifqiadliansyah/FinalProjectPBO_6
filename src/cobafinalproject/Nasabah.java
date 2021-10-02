
package cobafinalproject;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


abstract class Nasabah {
   protected StringProperty nama;
   protected StringProperty alamat;
   protected IntegerProperty idNasabah;
   protected ArrayList<Rekening> rekening = new ArrayList<Rekening>();
   
    public Nasabah(String nama, String alamat,Integer idNasabah,ArrayList<Rekening> rekening) {
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.idNasabah = new SimpleIntegerProperty(idNasabah);
        this.rekening = rekening;
    }
    
    public Nasabah(String nama, String alamat,Integer idNasabah,Rekening rekening) {
        this.nama = new SimpleStringProperty(nama);
        this.alamat = new SimpleStringProperty(alamat);
        this.idNasabah = new SimpleIntegerProperty(idNasabah);
        this.rekening.add(rekening);
        
    }
    
    
    
    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public Integer getIdNasabah() {
        return idNasabah.get();
    }

    public void setIdNasabah(Integer idNasabah) {
        this.idNasabah.set(idNasabah);
    }
    public ArrayList<Rekening> getRekening() {
        return rekening;
    }

    public void setRekening(ArrayList<Rekening> rekening) {
        this.rekening = rekening;
    }
    public void tambahRekening(Rekening rek){
    rekening.add(rek);
    }
   
    abstract public void print();
   
    public StringProperty nama(){
        return nama;
    }
    public StringProperty alamat(){
        return alamat;
    }
    public IntegerProperty idNasabah(){
        return idNasabah;
    }

}


