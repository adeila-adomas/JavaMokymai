/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group121;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class SortCarsByDistance implements Comparator<Masina> 
{
    public int compare(Masina a, Masina b) 
    { 
        return b.getKelias() - a.getKelias(); 
    } 
} 

/**
 *
 * @author Bronius
 */
public class Lenktynes {

    /**
     * @param args the command line arguments
     */
    private static final int MAX_DISTANCE = 1000;
    
    public static void main(String[] args) {
        Masina[] visos = {
            new LetaMasina("Karieta"),
            new Masina("Pirma"),
            new Masina("Antra"),
            new Masina("Trecia"),
            new Masina("Ketvirta"),
            new GreitaMasina("Pirma greita"),
            new GreitaMasina("Antra Greita"),
            new LetaMasina("Vezimas")
        };

        
        int currentDistance = 0;
        while (currentDistance <= MAX_DISTANCE) {
            
            // isvedus sita sout aptikau kad distancija visada imama paskutines masinos
            // System.out.println(carName + ": " + currentDistance);
            // Vidiniame cikle 'for' einame per visas masinas ir tikriname ar atstumas jau pasiektas 
            for (int i = 0; (i < visos.length && currentDistance <= MAX_DISTANCE); i++) {
                if (Math.random() >= 0.3) {
                    int kiekStabdo = (int) (Math.random() * (10 - 1 + 1)) + 1;
                    visos[i].stabdyk(kiekStabdo);
                } else {
                    int kiekGazuoja = (int) (Math.random() * (15 - 1 + 1)) + 1;
                    visos[i].gazuok(kiekGazuoja);
                }
                visos[i].vaziuok();
                
                currentDistance = visos[i].getKelias();
                
                System.out.println("[" + i + "] KELIAS: " + visos[i].getKelias() + ": " + visos[i].getPavadinimas());
            }
        }
        
        Collections.sort(Arrays.asList(visos), new SortCarsByDistance());
        for (Masina auto : visos) { 
            System.out.println(auto.toString());
        }
    }
}
