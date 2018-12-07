
package tp2partie1etpartie2;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Classe qui permet de construire les panneau d'affichage de noms
 * @author Romy Steve
 */
public class PanneauAffichageNoms extends JPanel
{
    
    //constantes de classe
    private final static int LARG = 250;
    private final static int HAUT = 500;
    
    private JList list; // le jList des noms
    private JLabel lblTitre; // le label du titre
    
    /**
     * Constructeur de la classe PanneauAffichageNoms
     * @param data tableau de nom à remplir sur le JList
     * @param titre le titre sur le jLabel
     */
    public PanneauAffichageNoms(String[] data, String titre){
       initComponent(data, titre);
    }
    /**
     * accesseur qui permet d'accéder au JList list
     * @return list Jlist rempli
     */
    public JList getList(){
        return list;
    }
    /***
     * méthode qui permet de créer chaque panneau de noms 
     * @param data tableau de nom à mettre le JList
     * @param titre le titre du JList
     */
    private void initComponent(String[] data, String titre){
        //initialiser le JList
        list = new JList(data); 
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        
        //initialiser le jLabel
        lblTitre = new JLabel(titre);

        //Rendre le JList scrollable
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 200));
        
        //initialiser le JPanel et y mettre le label et le jlist
        this.setSize(LARG, HAUT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(lblTitre);
        this.add(listScroller);
        this.setBorder(BorderFactory.createEmptyBorder( 5, 5, 5, 5));
        
        //Creation et ajout de l'ecouteur au jlist
        EcouteurList ecouteur = new EcouteurList( EcouteurBarreMenu.electionRempli()); 
        list.addListSelectionListener(ecouteur);
    }
    
    /***
     * Methode qui permet obtenir le premier élement du JList
     * @return le string sélectionné
     */
    public String getFirstItem(){
        return (String)list.getSelectedValue();
    }
    
}
