/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;
import java.io.Serializable;
/**
 *
 * @author Romy Steve
 */
public class Depute implements Serializable{
    
    private String nom;
    private int noCaseCircons;
    private int noCaseNomParti;
    
    public Depute(String nom, int noCaseCircons, int noCaseNomParti){
           this.nom = nom;
           this.noCaseCircons = noCaseCircons;
           this.noCaseNomParti = noCaseNomParti;
    }
    
    public String getNom(){
         return this.nom;
    }
    
    public int getNoCaseCircons(){
         return this.noCaseCircons;
    }
    
    public int getNoCaseNomParti(){
         return this.noCaseNomParti;
    }
    
    public void setNom(String nomDep){
          this.nom = nomDep;
    }
    
    public static String prenEtNom(String prenomDep, String nomDep){
          return prenomDep +" " + nomDep;
    }
    
}
