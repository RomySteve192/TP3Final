
package tp2partie1etpartie2;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Classe qui permet d'initialiser le cadre avec la barre de menu
 * @author Romy Steve
 */
public class BarreMenu extends JMenuBar{
    
    private JMenu m1;  //menu 1 
    JMenuItem[] itemsMenu1; //contiendra les items du menus 1
    private Election election;//object election que l'on passera en paramètre à l'écouteur
    
    /***
     * Constructeur avec paramètre
     * @param cadre JFrame représentant la fenêtre principale 
     */
    public BarreMenu(JFrame cadre){
        creerBarreDeMenus(cadre);
    }
    /**
     * Méthode privée appelé dans le constructeur qui permet 
     * de créer la barre de menu dans la fenêtre principale
     * @param frame JFrame représentant la fenêtre principale
     */
    private void creerBarreDeMenus(JFrame frame){
        election = new Election(Constantes.ANNEE_ELECTION);
        //creer le menu fichier
        m1 = new JMenu("Fichier");
        
        //creer et ajouter les items du menu m1
        //utilisation de add(JMenuItem)
      
        itemsMenu1 = new JMenuItem[3];
      
        itemsMenu1[0] = new JMenuItem("Ouvrir fichier texte");
        itemsMenu1[1] = new JMenuItem("Ouvrir fichier binaire");
        itemsMenu1[2] = new JMenuItem("Quitter");
        
        for (JMenuItem item : itemsMenu1) {
            m1.add(item);  //ajouter l'item a menu 1
        }
        
        this.add(m1);
        
        //créer et initialiser la fenêtre
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setJMenuBar(this);
        
        //Creation et ajout de l'ecouteur de la barre de menu
        EcouteurBarreMenu ecouteur = new EcouteurBarreMenu( election, 
                                           this,  frame);  //classe interne
        this.getMenu(0).getItem(0).addActionListener(ecouteur);  
        this.getMenu(0).getItem(1).addActionListener(ecouteur); 
        this.getMenu(0).getItem(2).addActionListener(ecouteur); 
    } 
}