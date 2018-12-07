
package tp2partie1etpartie2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * classe qui implemente ActionListener
 * @author Romy Steve / Jean Akre
 */
public class EcouteurBarreMenu implements ActionListener{
    
    private static Election election; // l'objet election utile pour accéder à l'election rempli
    private JMenuBar barDeMenu; // la barre de menu utilie pour accéder à ses items
    private JFrame frame; // le JFrame pour accéder à la fenêtre principale
     
    /***
     * Constructeur de la classe EcouteurBarreMenu
     * @param election l'objet election utile pour accéder à l'election rempli
     * @param barDeMenu la barre de menu utilie pour accéder à ses items
     * @param frame le JFrame pour accéder à la fenêtre principale
     */ 
    public EcouteurBarreMenu(Election election, 
                             JMenuBar barDeMenu, JFrame frame){
        this.election = election;
        this.barDeMenu = barDeMenu;
        this.frame = frame;
    }
    
    /**
      * Methode appelee lorsqu'un evenement ActionEvent est genere.
      * @param e l'evenement genere.
      */
    public void actionPerformed(ActionEvent e) {
        
        //obtenir l'item de menu qui a genere l'evenement 
        JMenuItem item = (JMenuItem)e.getSource();
        
        //Les données sont chargées en mémoire
        election = new Election(Constantes.ANNEE_ELECTION);
        
        if (item == barDeMenu.getMenu(0).getItem(0)) {
            //lecture du fichier texte
            ModuleFichier.getElection(election);
            ModuleFichier.sauverFichierBinaire(election);
            creerCadre( election, frame);
            
        } else if (item == barDeMenu.getMenu(0).getItem(1)) {
            //lecture du fichier binaire
            election = ModuleFichier.getElectionBinaire();
            creerCadre( election, frame);
           
        } else if (item == barDeMenu.getMenu(0).getItem(2)) {
           // fermer l'application
            frame.dispose();
        }
    }
    
    /***
     * Méthode qui créer le cadre une fois l'objet election rempli
     * @param election l'objet Election rempli
     * @param fenetre fenetre principale
     */
    private void creerCadre(Election election, JFrame fenetre){
        election.genererIndex();
        if(election.getIndex().length != 0){
             CadreElection.setElection(election, fenetre);
        }
    }
    
    /**
     * méthode static pour accéder à l'objet Election 
     * rempli depuis l'autre classe
     * @return election rempli
     */
    public static Election electionRempli(){
        return election;
    }
}
