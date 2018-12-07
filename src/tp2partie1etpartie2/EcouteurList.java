
package tp2partie1etpartie2;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * La classe qui permet d'écouter les évenement sur les composants JList
 * @author Romy Steve / Jean Akre
 */
public class EcouteurList implements ListSelectionListener{
    
    private Election election; // objet Election à passer au constructeur
   
    
    /**
     * Constructeur de la classe EcouteurList
     * @param election objet Election utile à l'instanciation de la classe
     */
    public EcouteurList(Election election){
        this.election = election;
    }
    
    /***
     * 
     * @param e 
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        String item;
        String s1;
        String parti;
        String[] arrNomsParti;
        String[] arrNomsDepute;
        String[] arrNomsSupp;
       
        
        JList list = (JList)e.getSource();
        item = (String) list.getSelectedValue();
        if(item != null){
            if(verifCircOuPart(election.obtenirNomsCirconscription(), item)){
                arrNomsParti = election.obtenirNomsPartisParCirconscription(
                                  item);
                arrNomsDepute = election.obtenirNomsDeputesParCirconscription(
                                   item, arrNomsParti);

                PanneauDeHaut.updatePanDepute(arrNomsDepute);
                PanneauDeHaut.updatePanParti(arrNomsParti);
            }else{
               
                s1 = item.replaceAll("\\(", "!").replaceAll("\\)","!");
                //String[] d = election.obtenirNomsParti();
                
                if(item.toLowerCase().contains(("(e)").toLowerCase())){
                    parti = (s1.split("!"))[0].trim() + "(e)";
                }else{
                    parti = (s1.split("!"))[0].trim();
                }
                
                if(verifCircOuPart(election.obtenirNomsParti(), parti)){
                     arrNomsSupp = election.obtenirNomsSupporteursParParti(
                                    parti);
                     arrNomsDepute = election.obtenirNomsDeputesParParti(
                                    parti);

                    PanneauDeBas.updatePanDepute(arrNomsDepute);
                    PanneauDeBas.updatePanSupporteur(arrNomsSupp);
                }
            }
        }
        
    }
    
    private boolean verifCircOuPart(String [] t, String s){
       int i = 0;
       boolean retour = false;
       
       while(i < t.length && !t[i].equals(s)){
           i++;
       }
       if(i < t.length && t[i].equals(s)){
           retour = true;
       }
       return retour;
    }
    
}
