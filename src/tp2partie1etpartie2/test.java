/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import static tp2partie1etpartie2.ModuleFichier.OPEN;

/**
 *
 * @author Romy Steve
 */
public class test {
    
     /*public static void main(String[] args) {
         
       
       Election election = new Election();
       getElectron( election);
     }
     
     private static File obtenirFic(String description,
           String extension,
           int type) {

      
      
      //Cr�ation du s�lectionneur de fichier (r�pertoire courant).
      JFileChooser fc = new JFileChooser(".");

      File fic = null;
      int reponse;

      //On filtre seulement les fichiers avec l'extension fournie
      FileNameExtensionFilter filter
              = new FileNameExtensionFilter(extension, extension);

      fc.setDialogTitle(description);
      fc.addChoosableFileFilter(filter);
      fc.setFileFilter(filter);

      //On obtient le nom du fichier � ouvrir en lecture ou en �criture?
      if (type == OPEN) {
         reponse = fc.showOpenDialog(null);
      } else {
         reponse = fc.showSaveDialog(null);
      }

      //On obtient le fichier seulement si le fichier a �t� choisi
      if (reponse == JFileChooser.APPROVE_OPTION) {
         fic = fc.getSelectedFile();
      }

      return fic;
   }
   
   
   
    private static ArrayList<String> obtenirInfosFichierTexte() {

     
    
     String ligne;
     ArrayList<String> tabInfosElection = new ArrayList<String>();
     
     File fic = obtenirFic ("Obtenir fichier electionQuebec2018", "txt", OPEN);
     
     if(fic != null){
        try{
             Scanner fichier = new Scanner(fic);
             while(fichier.hasNextLine()){
                 if((ligne = fichier.nextLine())!= null){
                     tabInfosElection.add(ligne);
                 }
             }
          fichier.close();
         }catch(FileNotFoundException e){
             e.printStackTrace();
         }
     }
     return tabInfosElection;
   }


    
    public static void getElectron(Election election){
        ArrayList<String> listInfosElection;
        String [] tab;
        int noParti;
        
        election = new Election();
        listInfosElection = obtenirInfosFichierTexte();
        for (String object: listInfosElection) {
            tab = object.split("\t");
            election.ajouterCirconscription(tab[1], Integer.parseInt(tab[0]));
            noParti = election.ajouterNomParti(tab[2]);
            election.ajouterDepute(Integer.parseInt(tab[0]), tab[3] + " " + tab[4], noParti);
            System.out.println(tab[1].toString());
        }
    }
    *****************/
    
    
    
     
     
     
     
     
    
}


