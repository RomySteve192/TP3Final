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
public class Parti implements Serializable{
    //nom du parti
    private String nom;
    
    
    /*******
     * constructeur sans argument
     */
    public Parti(){
        this.nom = "Pas de nom";
    }
    
    /***
     * constructeur avec argument
     * @param nomParti 
     */
    public Parti(String nomParti){
        this.nom = nomParti;
    }
    
    /*******
     * accesseur qui perment d'accéder au nom du parti
     * @return  nom le nom du parti
     */
    public String getNom(){
        return nom;
    }
    
    /*******
     * accesseur qui perment d'accéder de modifier le nom du parti
     * @param  nomParti le nom du parti
     */
    public void setNom(String nomParti){
        this.nom = nomParti;
    }
    
    /*******
     * Méthode qui permet de retourner simplement la chaine parti
     * @return  la chaine Parti
     */
    public String getCategorie(){
        return "Parti";
    }
    
    /***
     * redefinition de la méthode String
     * @return String
     */
    public String toString(){
        return nom;
    }
}
