
package tp2partie1etpartie2;
import java.io.Serializable;
/**
 * La classe Circonscription qui décrit un circonscription particulière
 * @author Romy Steve / Jean Acre
 * @version A2018
 */
public class Circonscription implements Serializable{
    
    private int noCircons; //numero de circonscription
    private String nomCircons; // nom de la circonscription
    private int noCaseDepCircons; // numero de case du deputé 
                                  // d'une circonscription particulière
    
    /**
     * Constructeur de la classe circonscription
     * @param noCaseDepCircons // numero de case du deputé 
     *                         // d'une circonscription particulière
     */
    public Circonscription( int noCaseDepCircons){
        this.noCaseDepCircons = noCaseDepCircons;
    }
    
    /**
     * Constructeur sans paramètre
     */
    public Circonscription(){
        this(Constantes.VIDE);
    }
    
    /**
    * accesseur qui permet d'accéder au nom de la circonscription
    * 
    * @return le nom de la circonscription
    */
    public String getNomCircons(){
        return this.nomCircons;
    }
    
    /**
    * accesseur qui permet d'accéder au numero de circonscription
    * 
    * @return le numero de la circonscription
    */
    public int getNoCircons(){
        return this.noCircons;
    }
    
   /**
    * accesseur qui permet d'accéder au numero de circonscription
    * 
    * @return le numero de case du deputé de la circonscription
    */
    public int getNoCaseDepCircons(){
        return this.noCaseDepCircons;
    }
    
   /**
    * accesseur qui permet de modifier le nom de circonscription d'un deputé
    * @param String le nom de la circonscription
    * 
    */
    public void setNomCircons(String nomCir){
            this.nomCircons = nomCir;
    }
    
   /**
    * accesseur qui permet de modifier le numero de circonscription d'un deputé
    * @param int le numero de la circonscription
    * 
    */
    public void setNoCircons(int noCir){
          this.noCircons = noCir;
    }
    
   /**
    * accesseur qui permet de modifier le numero de case de la circonscription d'un deputé
    * @param int le numero de case de la circonscription
    * 
    */
    public void setNoCaseDepCircons(int noCaseDepCircons){
           this.noCaseDepCircons = noCaseDepCircons;
    }
}
