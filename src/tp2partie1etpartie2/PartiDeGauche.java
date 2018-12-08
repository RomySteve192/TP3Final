
package tp2partie1etpartie2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe parti de gauche dérivée de pari
 * @author Romy Steve / Jean Akre
 */
public class PartiDeGauche extends Parti implements Serializable{
    
    private ArrayList<String> noms; //liste de nom des OBNL
    
  
    /**
     * constructeur avec argument
     * @param nomPartiGauche nom du parti de gauche
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
