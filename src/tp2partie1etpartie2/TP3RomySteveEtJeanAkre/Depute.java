
package tp2partie1etpartie2;
import java.io.Serializable;
/**
 * La classe Depute qui décrit un député particulier
 * @author Romy Steve / Jean Acre
 * @version A2018
 */
public class Depute implements Serializable{
    
    private String nom; //nom du député
    private int noCaseCircons; // numero de case de la circonscription du deputé
    private int noCaseNomParti; // numero de case de nom du parti du deputé
    
    /**
     * Constructeur de la classe deputé
     * @param nom nom du député
     * @param noCaseCircons numero de case de la circonscription du deputé
     * @param noCaseNomParti numero de case de nom du parti du deputé
     */
    public Depute(String nom, int noCaseCircons, int noCaseNomParti){
        this.nom = nom;
        this.noCaseCircons = noCaseCircons;
        this.noCaseNomParti = noCaseNomParti;
    }
    
   /**
    * accesseur qui permet d'accéder au nom du député
    * 
    * @return le nom du député
    */
    public String getNom(){
        return this.nom;
    }
    
   /**
    * accesseur qui permet d'accéder au numero de case d'une circonscription
    * 
    * @return le numero de case d'une circonscription
    */
    public int getNoCaseCircons(){
        return this.noCaseCircons;
    }
    
   /**
    * accesseur qui permet d'accéder au numero de case d'un parti
    * 
    * @return le numero de case d'un parti
    */
    public int getNoCaseNomParti(){
        return this.noCaseNomParti;
    }
    
   /**
    * accesseur qui permet de modifier le nom du député
    * @param String le nom du député
    * 
    */
    public void setNom(String nomDep){
        this.nom = nomDep;
    }
    
   /**
    * méthode qui permet de concatener le prénom et le nom d'un député
    * 
    * @return le nom du député concatené
    */
    public static String prenEtNom(String prenomDep, String nomDep){
        return prenomDep +" " + nomDep;
    }
    
}
