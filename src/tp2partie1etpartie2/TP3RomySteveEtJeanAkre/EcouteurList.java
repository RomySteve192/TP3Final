
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
    
    /**
      * Methode appelee lorsqu'un evenement valueChanged est genere.
      * @param e l'evenement genere.
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
        item = (String) list.getSelectedValue();//obtenir le string sélectionné dans le JList
        
        if(item != null){
            if(verifCircOuPart(election.obtenirNomsCirconscription(), item)){
                //obtenir le tableau de nom des partis
                arrNomsParti = election.obtenirNomsPartisParCirconscription(
                                  item);
                //obtenir le tableau de nom des noms des deputés
                arrNomsDepute = election.obtenirNomsDeputesParCirconscription(
                                   item, arrNomsParti);
                //Mettre à jour le panneau de haut jList nom deputé et nom parti
                PanneauDeHaut.updatePanDepute(arrNomsDepute);
                PanneauDeHaut.updatePanParti(arrNomsParti);
            }else{
               //enlever l'orientation du parti qui est en parenthèse 
               //sur le string sélectionné pour utiliser le nom du parti
                s1 = item.replaceAll("\\(", "!").replaceAll("\\)","!");
                // vérifier si le nom du parti est aussi au féminin (ex independant(e)
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
                    //mettre à jour le panneau de bas
                    PanneauDeBas.updatePanDepute(arrNomsDepute);
                    PanneauDeBas.updatePanSupporteur(arrNomsSupp);
                }
            }
            
        }
    }
    
    /***
     * méthode qui permet de rechercher l'élement qui a été cliqué
     * si c'est une circonscription(on traite panneauhaut)
     * si c'est un parti(on traite panneau bas
     * NB:on pouvais accéder autrement en cherchant l'indice du composant 
     * 
     * @param t tableau de string contenant les noms
     * @param s le noms à chercher
     * @return 
     */
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
