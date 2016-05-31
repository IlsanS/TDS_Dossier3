/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing.Seuillage;

import ImageProcessing.Histogramme.Histogramme;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Seuillage {
     public static int[][]seuillageSimple(int[][] image,int seuil)
    {
        // je boucle et je mets les pixel inférieur au seuil à 0 et superieur au seuil à 255 
       
        int[][]resultat=new int[image.length][image[0].length];
        
        for(int i=0;i<image.length;i++)
            
            for(int j=0;j<image[0].length;j++)
            {
                if(image[i][j]<seuil)
                    
                    resultat[i][j]=0;
                else
                    resultat[i][j]=255;
            }
        return resultat;
    }
    
    public static int[][]seuillageDouble(int[][]image,int seuil1,int seuil2)
    {
       // Pareil que précédemment sauf qu'il y a une valeur intermedair d'où le else if  dans le else je met
       // les valeur qui sont entre les 2 seuil à 128 
        int[][]resultat=new int[image.length][image[0].length];
        for(int i=0;i<image.length;i++)
            for(int j=0;j<image[0].length;j++)
            {
                if(image[i][j]<seuil1)
                    resultat[i][j]=0;
                else if(image[i][j]>seuil2)
                    resultat[i][j]=255;
                else
                    // valeur entre les 2 seuil
                    resultat[i][j]=128;
            }
        
        return resultat;
    }
    
      public static int[][] seuillageAutomatique(int[][] image)
    {
        int ligne = image.length;
        int col = image[0].length;
        
        ArrayList<Integer> group1 = new ArrayList<Integer>();
        ArrayList<Integer> group2 = new ArrayList<Integer>();
        
        int Tfinal = (int) Histogramme.luminance(image);
        int Tdebut = -1;

    // dans le seuil automatique il y a convergence des intensité Tdebut vers Tfinal, on commence avec une valeur 
    // d'intensité initiale, on a 2 list qui sont les pixel superieur et inférieur à Tfinal,
    // à la fin de chaque tour on remplace TFinal par la moyennes des intensité des 2 groupe
    // et Tdebut devient l'ancien Tfinal... Et on refait un tour jusqu'à ce que Tfinal = Tdébut
    // à la fin on seuil l'image avec la valeur de Tfinal
        while(Tfinal != Tdebut)
        {
            // Choix t initial
            group1.clear();
            group2.clear();

            // premier tri
            for(int i = 0; i < ligne; ++i)
            {
                for(int j = 0; j < col; ++j)
                {
                    if (image[i][j] > Tfinal)
                        group1.add(image[i][j]);
                    else
                        group2.add(image[i][j]);
                }
            }

            // calcul des niveau de gris
            float moyenneNGg1 =Seuillage.calculateAverage(group1);
            float moyenneNGg2 =Seuillage.calculateAverage(group2);
            Tdebut = Tfinal;

            // mis à jour du seuil
            Tfinal = Math.round((moyenneNGg1 + moyenneNGg2)/2);
        }
        
        return Seuillage.seuillageSimple(image, Tfinal);
    }

   private  static Float calculateAverage(ArrayList <Integer> marks) {
    
    Integer sum = 0;
    if(!marks.isEmpty()) {
      for (int mark : marks) {
          sum += mark;
      }
      return sum.floatValue() / marks.size();
    }
    return sum.floatValue();
  }

    
}
