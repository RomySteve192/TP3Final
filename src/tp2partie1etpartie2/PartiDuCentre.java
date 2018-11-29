/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;

import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class PartiDuCentre extends Parti{
    
    private ArrayList<Circonscription> circonscriptions;
    
   
    /***
     * constructeur avec argument
     * @param nomPartiCentre 
     */
    public PartiDuCentre(String nomPartiCentre){
        super(nomPartiCentre);
        circonscriptions = new ArrayList<Circonscription>();
    }
    
    
    
    
    /*******
     * Rédéfinition de la Méthode getCategorie() qui est dans la classe parente
     * @return  la chaine Parti de droite
     */
    public String getCategorie(){
        return super.getCategorie() + " du centre";
    }
    
    /*******
     * Méthode qui permet d'ajouter une circonscription dans la collection de député
     * @param circonscription la circonscription à ajouter
     */
    public void ajouterCirconscription(Circonscription circonscription){
        
        circonscriptions.add(circonscription);
        
    }
    
    /*******
     * Méthode qui permet de créer un tableau d'objet circonscription
     * @return circonscription[] le tableau de circonscription  supporteur
     */
    public Circonscription[] tabSupporteurs(){
        Circonscription[] tab = new Circonscription[circonscriptions.size()];
        return circonscriptions.toArray(tab);
    }
    
    /***
     * redefinition de la méthode String
     * @return String
     */
    public String toString(){
        return super.toString() + " (" + this.getCategorie() + ")";
    }
  
    
}
