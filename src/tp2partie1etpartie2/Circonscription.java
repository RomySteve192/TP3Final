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
public class Circonscription implements Serializable{
    
    private int noCircons;
    private String nomCircons;
    private int noCaseDepCircons;
    
    public Circonscription( int noCaseDepCircons){
           this.noCaseDepCircons = noCaseDepCircons;
    }
    
    public Circonscription(){
           this(Constantes.VIDE);
    }
    
    public String getNomCircons(){
           return this.nomCircons;
    }
    
    public int getNoCircons(){
           return this.noCircons;
    }
    
    public int getNoCaseDepCircons(){
           return this.noCaseDepCircons;
    }
    
    
     public void setNomCircons(String nomCir){
            this.nomCircons = nomCir;
    }
    
    public void setNoCircons(int noCir){
          this.noCircons = noCir;
    }
    
    public void setNoCaseDepCircons(int noCaseDepCircons){
           this.noCaseDepCircons = noCaseDepCircons;
    }
 
    
}
