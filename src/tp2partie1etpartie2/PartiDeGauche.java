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
public class PartiDeGauche extends Parti {
    //LISTE NOMS OBNL
    private ArrayList<String> noms;
    
  
    /*******
     * constructeur sans argument
     */
    public PartiDeGauche(String nomPartiGauche){
        super(nomPartiGauche);
        noms = new ArrayList<String>();
    }
    
    
   
    
    
    /*******
     * Rédéfinition de la Méthode getCategorie() qui est dans la classe parente
     * @return  la chaine Parti de gauche
     */
    public String getCategorie(){
        return super.getCategorie() + " de gauche";
    }
    
    /*******
     * Méthode qui permet d'ajouter le nom de l'OBNL
     * @param nomOBNL 
     */
    public void ajouterOBNL(String nomOBNL){
        if(!noms.contains(nomOBNL)){
            noms.add(nomOBNL);
        }
    }
    
    /*******
     * Méthode qui permet de créer un tableau de nom
     * @return String[] le tableau de nom 
     */
    public String[] tabSupporteurs(){
        String[] tab = new String[noms.size()];
        return noms.toArray(tab);
    }
    
    /***
     * redefinition de la méthode String
     * @return String
     */
    public String toString(){
        return super.toString() + " (" + this.getCategorie() + ")";
    }
    
    
}
