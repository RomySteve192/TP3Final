
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
 * La classe qui représente le cadre principal
 * @author Romy Steve/ Jean Acre
 */
public class CadreElection extends JFrame implements Runnable{
    
    //variables static utile pour la méthode static setElectio(...)
    private static PanneauDeBas panneauBas;
    private static PanneauDeHaut panneauHaut;
    
    /***
     * contructeur de la classe CadreElection
     */
    public CadreElection(){
    
    }
    /**
    * Redefinition de la methode run de l'interface Runnable.
    */
    public void run() {
        //initialisation des composants
        BarreMenu barre = new BarreMenu(this);
    }
    
    
  
    /***
     * Méthode principale de lancement de l'application
     * @param args 
     */
   
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



   
  