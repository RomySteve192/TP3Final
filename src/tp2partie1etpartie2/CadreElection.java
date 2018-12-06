
package tp2partie1etpartie2;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import static tp2partie1etpartie2.CadreElection.preparerPourMac;

/**
 *
 * @author Romy Steve/ Jean Acre
 */
public class CadreElection extends JFrame implements Runnable{
        
    private static PanneauDeBas panneauBas;
    private static PanneauDeHaut panneauHaut;
    
    public CadreElection(){
    
    }
    /**
    * Redefinition de la methode run de l'interface Runnable.
    */
    public void run() {
      //initialisation des composants
       //menuPrincipale();
       BarreMenu barre = new BarreMenu(this);
    }
    
    /**
    * Initialise une fenetre avec deux panneau
    */
    private void initComposants(Election election) {
        
        panneauBas = new PanneauDeBas(election);
        panneauHaut = new PanneauDeHaut(election);
        
       // fenetre = new JFrame("Election au québec " + Constantes.ANNEE_ELECTION);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        this.getContentPane().add(panneauHaut, BorderLayout.NORTH);
        this.getContentPane().add(panneauBas.getPanelPanneauDeBas(), BorderLayout.SOUTH);

        //4. Size the frame.
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //5. Show it.
        this.setVisible(true);
    }
   /***
    * 
    */
    private void menuPrincipale(){
        
        String str = null;
      
        String[] tabMenuPremiereFois = {"Ouvrir fichier texte",
                                           "Ouvrir fichier binaire"};
        
         //preparer le look and feel pour mac
       // preparerPourMac();

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
                      
                    }else{
                        election = ModuleFichier.getElectionBinaire();
                    }
                election.genererIndex();
              //  tabAffichage = MethodeTestAffichagePartie1( election);
                  
                initComposants(election); 
                  
      
            }else{
                JOptionPane.showMessageDialog(null, "Merci et bonne journée!!!");
            }

        }while(str != null);
        

   }
   
   
    public static void main(String[] args) {
        preparerPourMac();
        
        //Demarrer le GUI dans un nouveau Thread
        Thread t = new Thread(new CadreElection());  //JFrame
        t.start();  //ne faire start qu'une seule fois.
       
   }
        
    /**
     * 
     * @param election
     * @param fen 
     */
    public static void setElection(Election election, JFrame fen){
        
        panneauBas = new PanneauDeBas(election);
        panneauHaut = new PanneauDeHaut(election);
        
        fen.getContentPane().add(panneauHaut, BorderLayout.NORTH);
        fen.getContentPane().add(panneauBas.getPanelPanneauDeBas(), BorderLayout.SOUTH);

        //4. Size the frame.
        fen.pack();
        fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
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



   
  