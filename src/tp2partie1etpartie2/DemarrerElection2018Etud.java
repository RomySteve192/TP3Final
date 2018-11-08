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
 * @author Romy Steve/ Jean Acre
 */
public class DemarrerElection2018Etud {
    
    
    public static void main(String[] args) {
        // TODO code application logic here
      String option = null;
      String retour = null;
      String str = null;
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
     
      do{
            //Affichage du menu demandant quel type de fichier on veut ouvrir.
              str = (String) JOptionPane.showInputDialog(null,
                    "Sélectionnez le type de fichier",
                    "Type de fichier (texte ou binaire?)",
                    0, null, tabMenuPremiereFois, 0);

              if(str != null){
                  //À exécuter la première fois (option ouvrir fic texte).
                  if (str.equals(tabMenuPremiereFois[0])) {

                      //lecture du fichier texte
                      ModuleFichier.getElection(election);

                      //generation de l'index
                      //A FAIRE DANS LA PARTIE 2, donc mettez cette instruction en commentaire
                      //pendant que vous faites la partie 1.


                      //sauvegarde en binaire
                      ModuleFichier.sauverFichierBinaire(election);

                      //A éxécuter seulement après que le fichier texte ait été ouvert
                      //et sauvegardé en binaire (option ouvrir fic bin).
                  }  else {
                      election = ModuleFichier.getElectionBinaire();
                  }
                  election.genererIndex();

                  //Affiche les données des 3 collections (pour tests... à enlever dans la partie 2)
                  //Notez qu'on ne voit pas toutes les données, mais assez pour juger
                     /* JOptionPane.showMessageDialog(null,
                              election.obtenirNomsCirconscription());

                      JOptionPane.showMessageDialog(null, election.obtenirNomsParti());

                      JOptionPane.showMessageDialog(null, election.obtenirNomsDepute());*/
                  do{
                      option = affichageMenu(tabMenuDeuxiemeFois);
                      retour = sousMenu( Arrays.asList(tabMenuDeuxiemeFois).indexOf(option), election);
                      if(option == tabMenuDeuxiemeFois[tabMenuDeuxiemeFois.length - 1]){
                          option = null;
                          str = null;
                      }
                  }while( option != null);

                  if(str == null){
                      JOptionPane.showMessageDialog(null, "Merci et bonne journée!!!");
                  }
            }else{
                JOptionPane.showMessageDialog(null, "Merci et bonne journée!!!");
            }

        }while(str != null);
   }
        
   /**
    * méthode utilitaire privé qui permet qui permet d'afficher 
    * les options du menu offertes à l'utilisateur dans un dialogue
    *
    * @param String[]  tableau contenant les noms à afficher
    * @return String retourne le choix de l'utilisateur
    */     
   private static String affichageMenu(String [] tabMenu){
        String str = (String) JOptionPane.showInputDialog(null,
              "Sélectionnez une option",
              "Les informations sur les députés",
              0, null, tabMenu, tabMenu[0]);
        return str;
   }     
    
   /**
    * méthode utilitaire privé qui permet de traiter le choix
    * de l'utilisateur en fonction de son choix éffectué sur le menu
    *
    * @param int indice  de l'option dans le menu
    * @param Election l'object election contenant les données
    * @return String le choix de l'utilisateur dans le sous option du nouveau menu
    */   
   private static String sousMenu(int indice, Election election){
       String nom = null;
       List list;
       int ind;
       /***
        * Stratégie: chaque case correspond à un choix de l'utilisateur sur le menu des 
        *            des options
        */
       switch (indice) {
	    case 0:
                list = Arrays.asList(election.obtenirNomsCirconscription());
                do{
                    //Obtenir le nom de la circonscription selectionnée
                    nom = dialogAffichageDesNoms(election.obtenirNomsCirconscription());
                    if(nom != null){
                        ind = list.indexOf(nom);
                        //Obtenir les deputes et leur parti engagé dans une circonscription
                        depEtPartiCirconcription( ind, election);
                    }
                }while(nom != null);
	        break;
		
	    case 1:
	        list = Arrays.asList(election.obtenirNomsParti());
                do{
                    //Obtenir le nom du parti selectionné
                    nom = dialogAffichageDesNoms(election.obtenirNomsParti());
                    if(nom != null){
                        ind = list.indexOf(nom);
                        //Obtenir le nom et le député d'un parti
                        obtenirNomsDepParti(ind,  election, nom);
                    }
                }while(nom != null);
		break;
                
            case 2:
		list = Arrays.asList(election.obtenirNomsDepute());
                do{
                    //Obtenir le nom du député selectionné
                    nom = dialogAffichageDesNoms(election.obtenirNomsDepute());
                    if(nom != null){
                        ind = list.indexOf(nom);
                        //Obtenir la circonscription et le parti d'un député
                        obtenirCirconsEtPartiDep(ind,  election,  nom); 
                    }
                }while(nom != null);
	    	
	        break;
		
	    case 3:
                //Pour quitter l'application
	         nom = null;
		break; 
        }
        return nom;
    }
    
   /**
    * méthode utilitaire privé qui permet d'afficher les choix
    * (circonscription, depute, parti) à l'utilisateur
    *
    * @param String[] listeNom tableau de noms (parti, circonscription ou député
    * @return String le choix de l'utilisateur dans le sous option du nouveau menu
    */   
    private static String dialogAffichageDesNoms( String[] listeNom){
                
        String str = (String) JOptionPane.showInputDialog(null,
              "Sélectionnez un nom",
              "Liste des noms",
              0, null, listeNom, listeNom[0]);
        
        return str;
    }
    
    /**
    * méthode utilitaire privé qui permet d'afficher 
    * les noms des députés et leurs partis respectives
    * engagés dans une circonscription particulière
    * 
    * @param int  indice représente  le choix de circonscription de l'utilisateur
    * @param Election election avec ses données
    * @return String le choix de l'utilisateur dans le sous option du nouveau menu
    */   
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
    }
    
   /**
    * méthode utilitaire privé qui permet d'afficher 
    * les noms des députés d'un parti
    * 
    * @param int  indColumn représente  le numero de colonne dans Index
    * @param Election election avec ses données
    * @param String parti nom du parti
    *
    */   
    public static void obtenirNomsDepParti(int indColumn, Election election, String parti){
        String str = "";
        String titre = "Députés du " + parti;
        
        for(int ligne = 0; ligne < election.obtenirNomsCirconscription().length; ligne++){
               if(election.getIndex()[ligne][indColumn] != -1){
                  str+=election.obtenirNomsDepute()[election.getIndex()[ligne][indColumn]]+"\t\n";
               }
        }
        affichage(str, titre);
    }
    
    /**
    * méthode utilitaire privé qui permet d'obtenir le nom de la circonscription
    * et le parti d'un député
    * 
    * @param int  indColumn représente  le numero de colonne dans Index
    * @param Election election avec ses données
    * @param String parti nom du député
    *
    */   
    public static void obtenirCirconsEtPartiDep(int ind, Election election, String nomDep){
        String str = "";
        String titre = "Circonscription et parti du député " + nomDep;
        
        for(int ligne = 0; ligne < election.getIndex().length; ligne++){
            for(int col = 0; col < election.getIndex()[ligne].length; col++){
                if(election.getIndex()[ligne][col] != Constantes.VIDE && 
                        election.getIndex()[ligne][col] == ind){
                    str = "\t\n" + "Circonscription: " + election.obtenirNomsCirconscription()[ligne]+ 
                            "\t\n" + "Parti: " + election.obtenirNomsParti()[col];
                }
            }
        }
        JOptionPane.showMessageDialog(null, str, titre, 0);
    }
    
   /**
    * méthode utilitaire privé qui permet d'afficher les resultat
    * le dialog s'adaptant dynamiquement à la quantité de donnée
    *
    */   
    private static void affichage(String str1, String str2){
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



   
  