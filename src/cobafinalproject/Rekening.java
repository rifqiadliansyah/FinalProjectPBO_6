
package cobafinalproject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Rekening {
    private IntegerProperty noRekening;
    private DoubleProperty saldo;
    
    public Rekening(int noRekening,double saldo) {
        this.noRekening= new SimpleIntegerProperty(noRekening);
        this.saldo = new SimpleDoubleProperty(saldo);
    }
    public Integer getNoRekening() {
        return noRekening.get();
    }

    public void setNoRekening(Integer noRekening) {
        this.noRekening.set(noRekening);
    }

    public Double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(Double saldo) {
        if(saldo<=0){
        this.saldo.set(0);
        }
        else{
        this.saldo.set(saldo);}
    }
    
    public IntegerProperty noRekeningProperty(){
    return noRekening;
    }
    
    public DoubleProperty saldoProperty(){
    return saldo;
    }
    public void tambahSaldo(double jumlah){
        this.saldo.set(this.saldo.get()+jumlah);
    }

    public void tarikTunai(double jumlah){
        if(this.saldo.get()-jumlah>=0){
        this.saldo.set(this.saldo.get()-jumlah);
        }
 }

}



