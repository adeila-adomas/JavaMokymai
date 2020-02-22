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
public class LetaMasina extends Masina {
    public LetaMasina(String p){
        super(p);
    }
    
    public void vaziuok(){
        this.kelias+=(this.greitis/2);
    }
}
