
package tp2partie1etpartie2;

import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe permettant de construire le panneau de haut
 * @author Romy Steve / Jean Akre
 */

public class PanneauDeBas {
    private JPanel panel;// panneau de bas par composition
    //les titre des différents titres des sous panel du panneau de bas
    private final static String TITRE_PAN_PARTI = "Noms des partis";
    private final static String TITRE_PAN_DEP = "Noms des députés";
    private final static String TITRE_PAN_SUPP = "Noms des supporteurs";
    
    private static PanneauAffichageNoms panPartis; //panneau pour affichage des noms de parti
    private static PanneauAffichageNoms panDep; //panneau pour affichage des noms des députés
    private static PanneauAffichageNoms panSupporteur;//panneau pour affichage des noms des supporteurs
    
    /***
     * constructeur de la classe PanneauDeBas
     * @param election 
     */
    public PanneauDeBas(Election election){
        initComponent(election);
    }
    
    /**
     * accesseur qui permet d'accéder au panel du panneau de bas
     * @return le panel de bas
     */
    public JPanel getPanelPanneauDeBas(){
        return panel;
    }
    
    /***
     * méthode qui permet de construire le panneau de bas
     * @param election 
     */
    private void initComponent(Election election){
        String[] arrNomsSupp;
        String[] arrNomsDepute;
       //initialiser le panel
        panel = new JPanel();
        //initialisation du panneau d'affichage de noms des partis
        panPartis = new PanneauAffichageNoms(arrPartisAvecOrientation(
                        election.obtenirNomsParti(), election), TITRE_PAN_PARTI);
        
        arrNomsSupp = election.obtenirNomsSupporteursParParti(
                              election.obtenirNomsParti()[0]);
        //initialisation du panneau d'affichage de noms des supporteurs
        panSupporteur = new PanneauAffichageNoms(arrNomsSupp, TITRE_PAN_SUPP);
        
        arrNomsDepute = election.obtenirNomsDeputesParParti(
                              election.obtenirNomsParti()[0]);
        //initialisation du panneau d'affichage de noms des députés
        panDep = new PanneauAffichageNoms(arrNomsDepute, TITRE_PAN_DEP);
        
        
        GridLayout grille = new GridLayout(1, 3, 5, 5);
        panel.setLayout(grille);
        
        panel.add(panPartis);
        panel.add(panDep);
        panel.add(panSupporteur);
    }
    
    /**
     * Methode qui permet de crér un tableau de noms de parti 
     * avec leurs orientations
     * @param nomsPartis le tableau de noms des partis
     * @param election l'objet Election
     * @return le tableau de noms de parti et leur orientation entre parenthèse
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
     * methode qui permet de mettre à jour le tableayu de supporteur
     * @param data tableau de nom de supporteur
     * 
     */
    public static void updatePanSupporteur(String[] data){
        DefaultListModel model = new DefaultListModel();
        
        for (int i = 0; i < data.length; i++) {
            model.addElement(data[i] );
        }
        panSupporteur.getList().setModel(model);
        panSupporteur.getList().setSelectedIndex(0);
    }
    /**
     * methode qui permet de mettre à jour le tableau de deputé 
     * du panneau de bas
     * @param data tableau de nom de supporteur
     *
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
