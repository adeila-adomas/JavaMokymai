/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group121;

/**
 *
 * @author Bronius
 */
public class Masina {
    private String pavadinimas;
    protected int greitis;
    int kelias;
    protected int maxGreitis;
    
    public Masina(String pavadinimas) {
        this.pavadinimas = pavadinimas;
        this.greitis=0;
        this.kelias=0;
        this.maxGreitis=200;
    }
    
    public void gazuok(int kiek){
        this.greitis=kiek;
        if(this.greitis>this.maxGreitis){
            this.greitis=this.maxGreitis;
        }
    }
    
    public void stabdyk(int kiek) {
        greitis-=kiek;
        if(greitis<0){
            greitis=0;
        }
    }
    public void vaziuok(){
        kelias+=greitis;
    }

    @Override
    public String toString() {
        return "Masina{" + "pavadinimas=" + pavadinimas + ", greitis=" + greitis + ", kelias=" + kelias + ", maxGreitis=" + maxGreitis + '}';
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public int getGreitis() {
        return greitis;
    }

    public int getKelias() {
        return kelias;
    }
}
