/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class PartiDeDroite extends Parti implements Serializable{
    
    private ArrayList<Depute> deputes;
    
   
    /***
     * constructeur avec argument
     * @param nomPartiDroite 
     */
    public PartiDeDroite(String nomPartiDroite){
        super(nomPartiDroite);
        deputes = new ArrayList<Depute>();
    }
   
    
    
    
    /*******
     * Rédéfinition de la Méthode getCategorie() qui est dans la classe parente
     * @return  la chaine Parti de droite
     */
    public String getCategorie(){
        return super.getCategorie() + " de droite";
    }
    
    /*******
     * Méthode qui permet d'ajouter un député dans la collection de député
     * @param depute le député a ajouter
     */
    public void ajouterDepute(Depute depute){
       
        deputes.add(depute);
        
    }
    
    /*******
     * Méthode qui permet de créer un tableau d'objet député
     * @return Depute[] le tableau de Depute 
     */
    public Depute[] tabSupporteurs(){
        Depute[] tab = new Depute[deputes.size()];
        return deputes.toArray(tab);
    }
    
    /***
     * redefinition de la méthode String
     * @return String
     */
    public String toString(){
        return super.toString() + " (" + this.getCategorie() + ")";
    }
   
}
