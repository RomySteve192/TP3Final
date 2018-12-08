
package tp2partie1etpartie2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * classe particentre dérivée de parti
 * @author Romy Steve
 */
public class PartiDuCentre extends Parti implements Serializable{
    
    private ArrayList<Circonscription> circonscriptions; // liste de circonscription
    
   
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
