
package cobafinalproject;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

 class Perusahaan extends Nasabah{
    private StringProperty nib;
    
    public Perusahaan(String nib, String nama, String alamat, ArrayList<Rekening> rekening) {
        super(nama, alamat, rekening);
        this.nib = new SimpleStringProperty(nib); //bedaa
    }

    public String getNib() {
        return nib.get();
    }

    public void setNib(String nib) {
        this.nib.set(nib);
    }
    
    public StringProperty nibProperty() {
        return nib;
    }
    
    public void print(){
        System.out.println("Nama: "+getNama());
        System.out.println("Alamat: "+getAlamat());
        System.out.println("NIB: "+getNib());
        System.out.println("===========================================");
        System.out.printf("No Rekening"+"%26s","Saldo");
        System.out.print("\n");
        System.out.println("===========================================");
       for(Rekening r : rekening){
        System.out.printf("%d%34.2f\n",r.getNoRekening(),r.getSaldo());
        }
    }
}