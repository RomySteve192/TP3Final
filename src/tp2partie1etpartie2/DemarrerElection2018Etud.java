/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import static tp2partie1etpartie2.DemarrerElection2018Etud.preparerPourMac;

/**
 *
 * @author Romy Steve
 */
public class DemarrerElection2018Etud {

    
    public static void main(String[] args) {
        // TODO code application logic here
        String[] tabMenuPremiereFois = {"Ouvrir fichier texte",
                                      "Ouvrir fichier binaire"};
      //preparer le look and feel pour mac
      preparerPourMac();

      //Les données sont chargées en mémoire.
      Election election = new Election(Constantes.ANNEE_ELECTION);

      //Affichage du menu demandant quel type de fichier on veut ouvrir.
      String str = (String) JOptionPane.showInputDialog(null,
              "Sélectionnez le type de fichier",
              "Type de fichier (texte ou binaire?)",
              0, null, tabMenuPremiereFois, 0);

     
      //À exécuter la première fois (option ouvrir fic texte).
      if (str.equals(tabMenuPremiereFois[0])) {
         
         //lecture du fichier texte
         ModuleFichier.getElection(election);
         
         //generation de l'index
         //A FAIRE DANS LA PARTIE 2, donc mettez cette instruction en commentaire
         //pendant que vous faites la partie 1.
        /***** election.genererIndex();*****/
         
         //sauvegarde en binaire
         ModuleFichier.sauverFichierBinaire(election);
         
      //A éxécuter seulement après que le fichier texte ait été ouvert
      //et sauvegardé en binaire (option ouvrir fic bin).
      }  else {
         election = ModuleFichier.getElectionBinaire();
      }

      //Affiche les données des 3 collections (pour tests... à enlever dans la partie 2)
      //Notez qu'on ne voit pas toutes les données, mais assez pour juger
      JOptionPane.showMessageDialog(null,
              election.obtenirNomsCirconscription());

      JOptionPane.showMessageDialog(null, election.obtenirNomsParti());

      JOptionPane.showMessageDialog(null, election.obtenirNomsDepute());

   }
        
        
        
        
    
    
    /**
    * Nécessaire à JOptionPane sur un Mac
    */
   public static void preparerPourMac() {

      try {
         UIManager.setLookAndFeel(
                 UIManager.getCrossPlatformLookAndFeelClassName());

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
    
}



   
   /**
    * Programme principal.
    * @param args (non utilisé)
    */