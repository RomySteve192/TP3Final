
package tp2partie1etpartie2;

import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe permettant de construire le panneau de haut
 * @author Romy Steve
 */
public class PanneauDeHaut extends JPanel{
    
    private final static String TITRE_PAN_CIRC = "Noms des circonscriptions";
    private final static String TITRE_PAN_PARTI = "Noms des partis";
    private final static String TITRE_PAN_DEP = "Noms des députés";
    
    private static PanneauAffichageNoms panCirconscriptions;
    private static PanneauAffichageNoms panParti;
    private static PanneauAffichageNoms panDepute;
   
    
    
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
                              election.obtenirNomsCirconscription(), TITRE_PAN_CIRC);
        
        arrNomsParti = election.obtenirNomsPartisParCirconscription(
                              panCirconscriptions.getFirstItem());
        panParti = new PanneauAffichageNoms(arrNomsParti, TITRE_PAN_PARTI);
        
        arrNomsDepute = election.obtenirNomsDeputesParCirconscription(
                               panCirconscriptions.getFirstItem(), arrNomsParti);
        panDepute = new PanneauAffichageNoms(arrNomsDepute, TITRE_PAN_DEP);
        
        
        
        GridLayout grille = new GridLayout(1, 3, 5, 5);
        this.setLayout(grille);
        
        this.add(panCirconscriptions);
        this.add(panParti);
        this.add(panDepute);
    }
    /***
     * 
     * @param data
     * @return 
     */
    public static void updatePanParti(String[] data){
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < data.length; i++) {
            model.addElement(data[i] );
        }
        panParti.getList().setModel(model);
        panParti.getList().setSelectedIndex(0);
       // panParti = new PanneauAffichageNoms(data, TITRE_PAN_PARTI);
        
    }
    /**
     * 
     * @param data
     * @return 
     */
    public static void updatePanDepute(String[] data){
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < data.length; i++) {
            model.addElement(data[i] );
        }
        panDepute.getList().setModel(model);
        panDepute.getList().setSelectedIndex(0);
    }
    
}
