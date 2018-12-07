
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
    //les titre des différents titres des sous panel du panneau de bas
    private final static String TITRE_PAN_CIRC = "Noms des circonscriptions";
    private final static String TITRE_PAN_PARTI = "Noms des partis";
    private final static String TITRE_PAN_DEP = "Noms des députés";
    
    private static PanneauAffichageNoms panCirconscriptions;//panneau noms de circonscription
    private static PanneauAffichageNoms panParti;//panneau noms de parti
    private static PanneauAffichageNoms panDepute;//panneau nom de député
   
    
    
    /***
     * constructeur de la classe PanneauDeHaut
     * @param election 
     */
    public PanneauDeHaut(Election election){
        initComponent(election);
    }
    
    /***
     * méthode qui permet l'initialiser du panneau de haut
     * @param election 
     */
    private void initComponent(Election election){
        String[] arrNomsParti;
        String[] arrNomsDepute;
        //initialisation du panneau de circonscription
        panCirconscriptions = new PanneauAffichageNoms(
                              election.obtenirNomsCirconscription(), TITRE_PAN_CIRC);
        
        arrNomsParti = election.obtenirNomsPartisParCirconscription(
                              panCirconscriptions.getFirstItem());
        //initialisation du panneau de parti
        panParti = new PanneauAffichageNoms(arrNomsParti, TITRE_PAN_PARTI);
        
        arrNomsDepute = election.obtenirNomsDeputesParCirconscription(
                               panCirconscriptions.getFirstItem(), arrNomsParti);
        //initialisation du de député
        panDepute = new PanneauAffichageNoms(arrNomsDepute, TITRE_PAN_DEP);
        
        
        
        GridLayout grille = new GridLayout(1, 3, 5, 5);
        this.setLayout(grille);
        
        this.add(panCirconscriptions);
        this.add(panParti);
        this.add(panDepute);
    }
    /***
     * Méthode qui permet de mettre à jour le panneau de parti
     * @param data tableau de nom de parti mis à jour
     * 
     */
    public static void updatePanParti(String[] data){
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < data.length; i++) {
            model.addElement(data[i] );
        }
        panParti.getList().setModel(model);
        panParti.getList().setSelectedIndex(0);
    }
    /**
     * Méthode qui permet de mettre à jour le panneau de deputé
     * @param data tableau de nom de député mis à jour
     * 
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
