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
public class GreitaMasina extends Masina{
   public GreitaMasina(String pavadinimas){
       super(pavadinimas);
       this.maxGreitis = 400;
   } 
   
   public void gazuok(int kiek) {
        this.greitis=kiek * 3;
        if(this.greitis>this.maxGreitis){
            this.greitis=this.maxGreitis;
        }
    }
    
    public void stabdyk(int kiek) {
        greitis-=kiek * 2;
        if(greitis<0){
            greitis=0;
        }
    }
}
