
package cobafinalproject;

import java.util.ArrayList;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class Individu extends Nasabah {
   private LongProperty nik;
   private LongProperty npwp;

    
   

    public Individu(Long nik, Long npwp, String nama, String alamat,Integer idNasabah, ArrayList<Rekening> rekening) {
        super(nama, alamat,idNasabah,rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    } 
   

     
    public Individu(Long nik, Long npwp, String nama, String alamat,Integer idNasabah, Rekening rekening) {
        super(nama, alamat,idNasabah,rekening);
        this.nik = new SimpleLongProperty(nik);
        this.npwp = new SimpleLongProperty(npwp);
    }
    
    public Long getNik() {
        return nik.get();
    }

    public void setNik(Long nik) {
        this.nik.set(nik);
    }

    public Long getNpwp() {
        return npwp.get();
    }

    public void setNpwp(Long npwp) {
        this.npwp.set(npwp);
    }
    
    public LongProperty NikProperty() {
        return nik;
    }
     public LongProperty NpwpProperty() {
        return npwp;
    }
     
   @Override
    public void print(){
        System.out.println("Nama: "+getNama());
        System.out.println("Alamat: "+getAlamat());
        System.out.println("Id Nasabah: "+getIdNasabah());
        System.out.println("NIK: "+getNik());
        System.out.println("NPWP: "+getNpwp());
        System.out.println("===========================================");
        System.out.printf("No Rekening"+"%26s","Saldo");
        System.out.print("\n");
        System.out.println("===========================================");
       for(Rekening r : rekening){
        System.out.printf("%d%34.2f\n",r.getNoRekening(),r.getSaldo());
        }
    }

}
