
/**
 * Module de methodes utilitaires.
 *
 * @author pbelisle
 * @version H2017
 * @revisor melanie lord (A2018)
 */
package tp2partie1etpartie2;
public class UtilitaireFonctions {

   /**
    * Retourne un entier choisi aleatoirement entre min et max inclusivement.
    *
    * ANTECEDENT : Le parametre min doit etre plus petit que max.
    *
    * Aucune validation des parametres n'est faite.
    *
    * @param min la borne min (incluse) du nombre a generer
    * @param max la borne max (incluse) du nombre a generer.
    * @return Un nombre aleatoire entre min et max.
    */
   public static int tirerUnNombreAleatoire(int min, int max) {

      //STRATEGIE :
      //on utilise le generateur de Java qui retourne une valeur 
      //reelle entre 0 et 1. Ensuite, on le ramene dans l'intervalle min..max,
      //et on le transforme en entier.
      return (int) (Math.random() * (max - min + 1) + min);
   }
}
