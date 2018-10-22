/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Vector;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Romy Steve
 */
public class ModuleFichier {
    
    public static final int SAUVE = 0;
   public static final int OPEN = 1;

   /**
    * M�thode utilitaire priv�e qui permet d'obtenir un fichier s�lectionn� par
    * l'utilisateur. L'extension ne doit pas contenir le "."
    *
    * @param description Appara�t dans "type de fichier" pour guider
    * l'utilisateur.
    *
    * @param extension Les 3 lettres en suffixe au point d'un nom de fichier.
    *
    * @param type : OUVRE ou SAUVE Sert � avoir le bon bouton dans le
    * JFileChooser, selon le type on a "ouvrir" ou "enregistrer".
    *
    * @return null si le nom n'est pas valide ou si annul�.
    */
   private static File obtenirFic(String description,
           String extension,
           int type) {

      /*
       * Strat�gie : On utilise le JFileChooser de javax.swing selon 
       * le type (SAUVE ou OPEN) re�ue.
       * 
       * FileNameExtensionFilter permet de filtrer les extensions.
       */
      
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

      /*
       * Strat�gie :
       */
    
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


    
    public static void getElection(Election election){
        ArrayList<String> listInfosElection;
        String [] tab;
        int noParti;
        
        listInfosElection = obtenirInfosFichierTexte();
        for (String object: listInfosElection) {
            tab = object.split("\t");
            election.ajouterCirconscription(tab[1], Integer.parseInt(tab[0]));
            noParti = election.ajouterNomParti(tab[2]);
            election.ajouterDepute(Integer.parseInt(tab[0]), tab[3] + " " + tab[4], noParti);
        }
    }
    
    
    public static Election getElectionBinaire(){
    
      /*
       * Strat�gie :
       */
      Election election = new Election();
      File fic = obtenirFic("Obtenir le fichier binaire electionQuebec2018", "bin", SAUVE);
      
      if(fic != null){
          try{
              
              FileInputStream inFile = new FileInputStream(fic);
              ObjectInputStream inStream = new ObjectInputStream(inFile);
              election = (Election)inStream.readObject();
              
          }catch(FileNotFoundException e){
             e.printStackTrace();
          }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Format de fichier invalide");
             e.printStackTrace();
          }
      }
      return election;
    }
    
    
    
    public static void sauverFichierBinaire(Election election){
        
      /*
       * Strat�gie : On utilise  un FileOutputStream qui permet d'�crire
       * les donn�es de l'application d'un coup.
       */
      
      File fic
              = obtenirFic("Nom du fichier binaire + l'extension (.bin)",
                      "bin",
                      SAUVE);

      if (fic != null) {
         ObjectOutputStream tampon = null;

         try {
            //Cr�e le fichier et ouverture du tampon d'�criture		
            FileOutputStream tamponFic = new FileOutputStream(fic);
            tampon = new ObjectOutputStream(tamponFic);

            //	�criture et fermeture.
            tampon.writeObject(election);
            tampon.close();

         } catch (FileNotFoundException e1) {

            e1.printStackTrace();

         // Une erreur de lecture, on d�truit le fichier si on a eu
         // le temps de le cr�er.
         } catch (IOException e) {

            // On obtient le chemin du fichier pour le d�truire.
            Path path
                    = FileSystems.getDefault().getPath(fic.getName());

            // Destruction du fichier ouvert (ou cr��) s'il y a un probl�me.
            try {
               tampon.close();
               Files.delete(path);

            } catch (IOException e1) {
               e1.printStackTrace();
            }

            e.printStackTrace();
         }
      }
    
    }
    
    
    
    
    
    
    
    
}
