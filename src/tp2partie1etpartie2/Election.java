
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;
import java.util.Vector;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 *
 * @author Romy Steve
 */
public class Election implements Serializable{
    private int annee;
    private Vector<String> nomsCirconscriptions;
    private ArrayList<String> nomsParti;
    private LinkedList<String> nomsDepute;
    private LinkedList<Depute> listeDepute;
    private Vector<Circonscription> listeCirconscription;
    private int [][] index;
    
    public Election(int annee){
           nomsCirconscriptions = new Vector<String>();
           nomsParti = new ArrayList<String>();
           nomsDepute = new LinkedList<String>();
           listeDepute = new LinkedList<Depute>();
           listeCirconscription = new Vector<Circonscription>();
           index = new int[nomsCirconscriptions.size()][nomsParti.size()];
           
    }
    
    public Election(){
           this(Constantes.ANNEE_ELECTION);
    }
    
     public int[][] getIndex(){
         return this.index;
    }
    
    public void setIndex(int [][] tab){
          this.index = tab;
    }
    
    public int ajouterCirconscription(String nom, int numero){
            int pos;
            if(!nomsCirconscriptions.contains(nom)){
              nomsCirconscriptions.add(nom);
              pos = this.creationObjCircons(nom, numero);
            }else{
              pos = nomsCirconscriptions.indexOf(nom);
            }
        return pos;
    }
    
    public int ajouterNomParti(String nomParti){
        
        if(!nomsParti.contains(nomParti)){
           nomsParti.add(nomParti);
        }
        return nomsParti.indexOf(nomParti);
    }
    
    public void ajouterDepute(int circonscription, String nomDepute, int noParti){
        if(!nomsDepute.contains(nomDepute)){
           nomsDepute.add(nomDepute);
           this.creationObjDepute(circonscription, nomDepute, noParti);
        }
    }
    
    private int creationObjCircons(String nom, int numero){
         Circonscription circons = new Circonscription();
         circons.setNomCircons(nom);
         circons.setNoCircons(numero);
         listeCirconscription.add(circons);
         
         return listeCirconscription.indexOf(circons);
    }
    
     private void creationObjDepute(int noCirconsDep, String nomDep, int noPartiDep){
         Depute depute = new Depute(nomDep, noCirconsDep, noPartiDep);
         listeDepute.add(depute);
     }
     
     public String[] obtenirNomsCirconscription(){
            return nomsCirconscriptions.toArray(new String[nomsCirconscriptions.size()]);
     }
     
      public String[] obtenirNomsParti(){
            return nomsParti.toArray(new String[nomsParti.size()]);
     }
      
      public String[] obtenirNomsDepute(){
            return nomsDepute.toArray(new String[nomsDepute.size()]);
     }
      
     public void genererIndex(){
        index = new int[nomsCirconscriptions.size()][nomsParti.size()];
        for(int i = 0; i < index.length; i++){
            for(int j = 0; j < index[i].length; j++){
                index[i][j] = Constantes.VIDE;
            }
        }
        for (Depute objDep: listeDepute) {
           
            index[objDep.getNoCaseCircons() - 1][objDep.getNoCaseNomParti()] 
                    = listeDepute.indexOf(objDep);
         
        }
     }
    
}
