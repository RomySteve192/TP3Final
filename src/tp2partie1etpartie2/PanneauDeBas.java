
package tp2partie1etpartie2;

import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe permettant de construire le panneau de haut
 * @author Romy Steve
 */

public class PanneauDeBas {
    private JPanel panel;
    private final static String TITRE_PAN_PARTI = "Noms des partis";
    private final static String TITRE_PAN_DEP = "Noms des députés";
    private final static String TITRE_PAN_SUPP = "Noms des supporteurs";
    
    private static PanneauAffichageNoms panPartis;
    private static PanneauAffichageNoms panDep;
    private static PanneauAffichageNoms panSupporteur;
    
    /***
     * 
     * @param election 
     */
    public PanneauDeBas(Election election){
        initComponent(election);
    }
    
    /**
     * 
     * @return 
     */
    public JPanel getPanelPanneauDeBas(){
        return panel;
    }
    
    /***
     * 
     * @param election 
     */
    private void initComponent(Election election){
        String[] arrNomsSupp;
        String[] arrNomsDepute;
       
        panel = new JPanel();
        
        panPartis = new PanneauAffichageNoms(arrPartisAvecOrientation(
                        election.obtenirNomsParti(), election), TITRE_PAN_PARTI);
        
        arrNomsSupp = election.obtenirNomsSupporteursParParti(
                              election.obtenirNomsParti()[0]);
        panSupporteur = new PanneauAffichageNoms(arrNomsSupp, TITRE_PAN_SUPP);
        
        arrNomsDepute = election.obtenirNomsDeputesParParti(
                              election.obtenirNomsParti()[0]);
        panDep = new PanneauAffichageNoms(arrNomsDepute, TITRE_PAN_DEP);
        
        
        GridLayout grille = new GridLayout(1, 3, 5, 5);
        panel.setLayout(grille);
        
        panel.add(panPartis);
        panel.add(panDep);
        panel.add(panSupporteur);
    }
    
    /**
     * 
     * @param nomsPartis
     * @param election
     * @return 
     */
    private String[] arrPartisAvecOrientation(String[] nomsPartis, Election election){
        String[] retour = new String[nomsPartis.length];
       
        for(int j = 0; j < nomsPartis.length; j++){
            int i = 0;
        
            while(election.getPartis().get(i).getNom() != nomsPartis[j]){
                i++;
            }
            if(election.getPartis().get(i).getNom() == nomsPartis[j]){
                if(election.getPartis().get(i) instanceof PartiDeDroite){

                    retour[j] = nomsPartis[j] + " (" +  ((PartiDeDroite)
                                (election.getPartis().get(i))).getCategorie() + ")";

                }else if(election.getPartis().get(i) instanceof PartiDeGauche){

                    retour[j] = nomsPartis[j] + " (" +  ((PartiDeGauche)
                                (election.getPartis().get(i))).getCategorie() + ")";

                }else if(election.getPartis().get(i) instanceof PartiDuCentre){

                    retour[j] = nomsPartis[j] + " (" +  ((PartiDuCentre)
                               (election.getPartis().get(i))).getCategorie() + ")";
                }
            }
           
        }
        return retour;
    }
    
    /***
     * 
     * @param data
     * @return 
     */
    public static void updatePanSupporteur(String[] data){
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < data.length; i++) {
            model.addElement(data[i] );
        }
        panSupporteur.getList().setModel(model);
        panSupporteur.getList().setSelectedIndex(0);
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
        panDep.getList().setModel(model);
        panDep.getList().setSelectedIndex(0);
    }
    
    
    
    
}
