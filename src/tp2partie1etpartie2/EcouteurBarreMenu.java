
package tp2partie1etpartie2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * classe qui implemente ActionListener
 * @author Romy Steve
 */
public class EcouteurBarreMenu implements ActionListener{
    
    private static Election election;
    private JMenuBar barDeMenu;
    private JFrame frame;
     
    /**
     * 
     * @param election
     * @param barDeMenu 
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
            
           // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.dispose();
        }
    }
    
    /***
     * 
     * @param election 
     */
    private void creerCadre(Election election, JFrame fenetre){
        election.genererIndex();
        if(election.getIndex().length != 0){
             CadreElection.setElection(election, fenetre);
        }
    }
    
    public static Election electionRempli(){
        return election;
    }
      
    
}
