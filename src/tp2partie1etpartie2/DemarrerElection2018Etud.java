/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2partie1etpartie2;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import static tp2partie1etpartie2.DemarrerElection2018Etud.preparerPourMac;

/**
 *
 * @author Romy Steve
 */
public class DemarrerElection2018Etud {
    
    
    public static void main(String[] args) {
        // TODO code application logic here
      String option;
      String[] tabMenuPremiereFois = {"Ouvrir fichier texte",
                                      "Ouvrir fichier binaire"};
      String[] tabMenuDeuxiemeFois = {"Tous les députés d'une circonscription",
                                      "Tous les députés d'un parti",
                                      "le parti et la circonscription d'un député",
                                      "Quittez"};
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
        election.genererIndex();
         
         //sauvegarde en binaire
        /* ModuleFichier.sauverFichierBinaire(election);*/
         
      //A éxécuter seulement après que le fichier texte ait été ouvert
      //et sauvegardé en binaire (option ouvrir fic bin).
      }  else {
         election = ModuleFichier.getElectionBinaire();
      }

      //Affiche les données des 3 collections (pour tests... à enlever dans la partie 2)
      //Notez qu'on ne voit pas toutes les données, mais assez pour juger
     /* JOptionPane.showMessageDialog(null,
              election.obtenirNomsCirconscription());

      JOptionPane.showMessageDialog(null, election.obtenirNomsParti());

      JOptionPane.showMessageDialog(null, election.obtenirNomsDepute());*/
      option = affichageMenu(tabMenuDeuxiemeFois);
      sousMenu( Arrays.asList(tabMenuDeuxiemeFois).indexOf(option), election);
    
     
     

   }
        
        
   private static String affichageMenu(String [] tabMenu){
        String str = (String) JOptionPane.showInputDialog(null,
              "Sélectionnez une option",
              "Les informations sur les députés",
              0, null, tabMenu, tabMenu[0]);
       
       
        return str;
   }     
   
   
        
    
    private static void sousMenu(int indice, Election election){
       String nomCirc;
       String nomDep;
       String nomParti;
       List list;
       int ind;
       switch (indice) {
	    case 0:
                list = Arrays.asList(election.obtenirNomsCirconscription());
		nomCirc = dialogAffichageDesNoms(election, election.obtenirNomsCirconscription());
                ind = list.indexOf(nomCirc);
	    	depEtPartiCirconcription( ind, election);
	        break;
		
	    case 1:
	        list = Arrays.asList(election.obtenirNomsParti());
                nomParti = dialogAffichageDesNoms(election, election.obtenirNomsParti());
                ind = list.indexOf(nomParti);
                obtenirNomsDepParti(ind,  election, nomParti);
		break;
                
            case 2:
		list = Arrays.asList(election.obtenirNomsDepute());
                nomDep = dialogAffichageDesNoms(election, election.obtenirNomsDepute());
                ind = list.indexOf(nomDep);
                obtenirCirconsEtPartiDep(ind,  election,  nomDep); 
	    	
	        break;
		
	    case 3:
	         
		break;    
                
                
	   }
    
    }
    
    private static String dialogAffichageDesNoms(Election election, String[] listeNom){
                
         String str = (String) JOptionPane.showInputDialog(null,
              "Sélectionnez une circonscription",
              "Circonscription",
              0, null, listeNom, listeNom[0]);
       
        
        return str;
    }
    
    private static void depEtPartiCirconcription(int indice, Election election){
       String str = "";
        String titre = "Députés (Partis)";
       int [] depEtParti = election.getIndex()[indice];
      
       for(int col = 0; col < depEtParti.length; col++){
          if(depEtParti[col] != Constantes.VIDE){
              str += election.obtenirNomsDepute()[depEtParti[col]] +" (" 
                + election.obtenirNomsParti()[col] + ")" +"\t\n";
          }
       }
        affichage(str, titre);
       //JOptionPane.showMessageDialog(null, str, "Députés (Partis)", 0);
       
    }
    
    public static void obtenirNomsDepParti(int indColumn, Election election, String parti){
        String str = "";
        String titre = "Députés du " + parti;
        for(int ligne = 0; ligne < election.obtenirNomsCirconscription().length; ligne++){
               if(election.getIndex()[ligne][indColumn] != -1){
                  str+=election.obtenirNomsDepute()[election.getIndex()[ligne][indColumn]]+"\t\n";
               }
        }
        affichage(str, titre);
        //JOptionPane.showMessageDialog(null, str, "Députés du " + parti, 0);
    }
    
    public static void obtenirCirconsEtPartiDep(int ind, Election election, String nomDep){
        String str = "";
        String titre = "Circonscription et parti du député " + nomDep;
        for(int ligne = 0; ligne < election.getIndex().length; ligne++){
            for(int col = 0; col < election.getIndex()[ligne].length; col++){
                if(election.getIndex()[ligne][col] != Constantes.VIDE && 
                        election.getIndex()[ligne][col] == ind){
                    str = "\t\n" + "Circonscription: " + election.obtenirNomsCirconscription()[ligne]+ 
                            "\t\n" + "Parti: " + election.obtenirNomsDepute()[col];
                }
            }
        }
        JOptionPane.showMessageDialog(null, str, titre, 0);
    }
    
    public static void affichage(String str1, String str2){
        JTextArea textArea = new JTextArea(str1);
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, scrollPane.getPreferredSize().height ) );
        JOptionPane.showMessageDialog(null, scrollPane, str2,  
                                               JOptionPane.OK_OPTION);
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