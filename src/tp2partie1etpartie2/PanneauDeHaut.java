
package tp2partie1etpartie2;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe permettant de construire le panneau de haut
 * @author Romy Steve
 */
public class PanneauDeHaut extends JPanel{
    
    private final static String titrePanCirc = "Noms des circonscriptions";
    private final static String titrePanParti = "Noms des partis";
    private final static String titrePanDep = "Noms des députés";
    
    private PanneauAffichageNoms panCirconscriptions;
    private PanneauAffichageNoms panParti;
    private PanneauAffichageNoms panDepute;
   
    
    
    /***
     * 
     * @param election 
     */
    public PanneauDeHaut(Election election){
        initComponent(election);
    }
    
    /***
     * 
     * @param election 
     */
    private void initComponent(Election election){
        String[] arrNomsParti;
        String[] arrNomsDepute;
        
        panCirconscriptions = new PanneauAffichageNoms(
                              election.obtenirNomsCirconscription(), titrePanCirc);
        
        arrNomsParti = election.obtenirNomsPartisParCirconscription(
                              panCirconscriptions.getFirstItem());
        panParti = new PanneauAffichageNoms(arrNomsParti, titrePanParti);
        
        arrNomsDepute = election.obtenirNomsDeputesParCirconscription(
                               panCirconscriptions.getFirstItem(), arrNomsParti);
        panDepute = new PanneauAffichageNoms(arrNomsDepute, titrePanDep);
        
        
        
        GridLayout grille = new GridLayout(1, 3, 5, 5);
        this.setLayout(grille);
        
        this.add(panCirconscriptions);
        this.add(panParti);
        this.add(panDepute);
        
        
        
    }
    
    
    
}
