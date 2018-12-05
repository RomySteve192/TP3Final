/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Romy Steve
 */
public class PanneauAffichageNoms extends JPanel
{
    
    //constantes de classe
    private final static int LARG = 250;
    private final static int HAUT = 500;
    
    private JList list;
    private JLabel lblTitre;
    
    /**
     * 
     * @param data
     * @param titre 
     */
    public PanneauAffichageNoms(String[] data, String titre){
       initComponent(data, titre);
    }
    
    /***
     * 
     * @param data
     * @param titre 
     */
    private void initComponent(String[] data, String titre){
        list = new JList(data); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        
        lblTitre = new JLabel(titre);

        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 200));
        
        this.setSize(LARG, HAUT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(lblTitre);
        this.add(listScroller);
        this.setBorder(BorderFactory.createEmptyBorder( 5, 5, 5, 5));
    }
    
    /***
     * 
     * @return 
     */
    public String getFirstItem(){
        return (String)list.getSelectedValue();
    }
    
}
