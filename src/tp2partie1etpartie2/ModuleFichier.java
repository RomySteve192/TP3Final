
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
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Romy Steve
 */
public class ModuleFichier {
    
   public static final int SAUVE = 0;
   public static final int OPEN = 1;

   /**
    * @copyrigth TP1
    * 
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
   
    /**
    * @copyrigth TP1
    * 
    * M�thode utilitaire priv�e qui permet de lire chaque logne du fichier
    * et la stocké dans un ArrayList
    *
    * @return ArrayList<String> des informations concernant les élections
    */
    private static ArrayList<String> obtenirInfosFichierTexte() {
        String ligne;
        ArrayList<String> tabInfosElection = new ArrayList<String>();
       /*
        * Strat�gie : obtenir le fichier text et lire chaque ligne jusqu'à la fin du fichier
        */
       
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


    /**
    * @copyrigth TP1
    * 
    * M�thode qui permet de remplir les attributs de l'object election
    * après la liture du fichier
    *
    */
    public static void getElection(Election election){
        ArrayList<String> listInfosElection;
        String [] tab;
        int noParti;
        
       /*
        * Strat�gie : obtenir chaque String de l'ArrayList, transformé chaque 
        *             en tableau de string et attribuer chaque string à l'attribut 
        *             correspondante
        */
        listInfosElection = obtenirInfosFichierTexte();
        for (String object: listInfosElection) {
            tab = object.split("\t");
            election.ajouterCirconscription(tab[1], Integer.parseInt(tab[0]));
            noParti = election.ajouterNomParti(tab[2]);
            election.ajouterDepute(Integer.parseInt(tab[0]), tab[3] + " " + tab[4], noParti);
        }
        genererPartis(election);
        genererSupporteurs(election);
    }
    
    /**
    * @copyrigth TP1
    * 
    * M�thode utilitaire qui permet de sauvegarder l'objection election
    * dans un fichier .bin
    *
    * @param election l'objet election à sauvegarder
    *
    */
    public static Election getElectionBinaire(){
    
      /*
       * Strat�gie : On utilise  un FileInputStream qui permet de lire
       * les donn�es de l'application d'un coup.
       */
        Election election = new Election();
        File fic = obtenirFic("Obtenir le fichier binaire electionQuebec2018", "bin", SAUVE);
      
        if(fic != null){
            try{
              
                FileInputStream inFile = new FileInputStream(fic);
                ObjectInputStream inStream = new ObjectInputStream(inFile);
                //Lecture et fermeture
                election = (Election)inStream.readObject();
                inStream.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Format de fichier invalide");
                e.printStackTrace();
            }
        }
        return election;
    }
    
    
    /**
    * @copyrigth TP1
    * 
    * M�thode utilitaire qui permet de sauvegarder l'objection election
    * dans un fichier .bin
    *
    * @param election l'objet election à sauvegarder
    *
    */
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
    
    
    /****
     * Methode qui permet de générer les partis
     * @param election pour obtenir la collection nomsParti
     */
    public static void genererPartis(Election election){
        Parti unParti;
        int nb;
        
        for(String str : election.obtenirNomsParti()){
            nb = UtilitaireFonctions.tirerUnNombreAleatoire(1, Constantes.NB_CATEGORIE_PARTI);
            if(nb == Constantes.PARTI_DE_GAUCHE){
                unParti = new PartiDeGauche(str);
                election.ajouterPartiGauche(str);
            }else if(nb == Constantes.PARTI_DE_CENTRE){
                unParti = new PartiDuCentre(str);
                election.ajouterNomsPartiCentre(str);
            }else{
                unParti = new PartiDeDroite(str);
                election.ajouterNomsPartiDroite(str);
            }
            election.AjouterParti(unParti);
        }
        
    }
    
    /***
     * Méthode qui permet de générer les supporteurs
     * @param election objet Election
     */
    public static void genererSupporteurs(Election election){
        
        for(int i = 0; i < election.tabParti().length; i++){
            if(election.tabParti()[i] instanceof PartiDeGauche){
                genererOBNLSupporteurs((PartiDeGauche)election.tabParti()[i]);
            
            }else if (election.tabParti()[i] instanceof PartiDuCentre){
                genererCirconscriptionSupportrice((PartiDuCentre)election.tabParti()[i], 
                                                 election);
            }else if (election.tabParti()[i] instanceof PartiDeDroite){
                genererDeputeSupporteur((PartiDeDroite)election.tabParti()[i], 
                                                election);
            }
        }
    }
    
    /***
     * Pour générer le nombre de supporteur OBNL
     * @param gauche
     * @param election 
     */
    private static void genererOBNLSupporteurs(PartiDeGauche gauche){
        int nb;
        nb = UtilitaireFonctions.tirerUnNombreAleatoire(1, Constantes.NB_OBNL_MAX);
      
        for(int i = 0; i < nb; i++){
            gauche.ajouterOBNL("");
        }
      
    }
    
    /***
     * Méthode Pour générer les circonscriptions supportrices
     * @param centre le parti du centre
     * @param election l'objet Election
     */
    private static void genererCirconscriptionSupportrice(PartiDuCentre centre, 
            Election election){
        int nb, noCirc;
        ArrayList<Integer> tab = new ArrayList<Integer>();//pour vérifier si un nombre à été déjà choisi
        Circonscription circ;
        nb = UtilitaireFonctions.tirerUnNombreAleatoire(1,
                Constantes.NB_CIRCONSCRIPTIONS_MAX);
       
        for(int i = 0; i < nb; i++){
            noCirc = UtilitaireFonctions.tirerUnNombreAleatoire(0, 
                    election.obtenirNomsCirconscription().length - 1);
            while(tab.contains(noCirc)){
                noCirc = UtilitaireFonctions.tirerUnNombreAleatoire(0,
                        election.obtenirNomsCirconscription().length - 1);
            }
            circ = election.obtenirCirconscription(noCirc);
            //ajouter la circonscription 
            centre.ajouterCirconscription(circ);
            tab.add(noCirc);
        }
    }
    
    /****
     * Pour générer les députés supporteurs
     * @param droite
     * @param election 
     */
    private static void genererDeputeSupporteur(PartiDeDroite droite, 
            Election election){
        int nb;
        int noDep;
        String nomParti;
        boolean Dedroite = false;
        ArrayList<Integer> tab = new ArrayList<Integer>();//pour vérifier si un nombre à été déjà choisi
        Depute depute = null;
        nb = UtilitaireFonctions.tirerUnNombreAleatoire(1,
                Constantes.NB_DEPUTE_MAX);
       
        
        for(int i = 0; i < nb; i++){
            noDep = UtilitaireFonctions.tirerUnNombreAleatoire(0, 
                    election.obtenirNomsDepute().length - 1);
            
            while(Dedroite == false){
                while(tab.contains(noDep)){
                    noDep = UtilitaireFonctions.tirerUnNombreAleatoire(0,
                            election.obtenirNomsCirconscription().length - 1);
                }
                //j obtiens le deputé
                depute = election.obtenirDepute(noDep);
                nomParti = election.obtenirNomsParti()[depute.getNoCaseNomParti()];
                //vérifie si le depute est de droite
                if(election.getNomsPartisDroite().contains(nomParti)){
                    Dedroite = true;
                }else{
                    noDep = UtilitaireFonctions.tirerUnNombreAleatoire(0,
                            election.obtenirNomsCirconscription().length - 1);
                }
            }
            /*ajouter le député */
            droite.ajouterDepute(depute);
            tab.add(noDep);
            Dedroite = false;
        }
    }
    
}
