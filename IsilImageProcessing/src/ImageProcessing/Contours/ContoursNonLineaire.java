/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing.Contours;

import CImage.Exceptions.CImageNGException;
import ImageProcessing.NonLineaire.MorphoElementaire;

/**
 *
 * @author PC
 */
public class ContoursNonLineaire {
    
    public static int[][]gradientErosion(int[][]image) throws CImageNGException
    {
        int [][]resultat=MorphoElementaire.erosion(image, 3);
        resultat=ContoursNonLineaire.MatriceDifférence(image, resultat);
        
        
        
        return resultat;
    }
    
    public static int[][]gradientDilatation(int[][]image) throws CImageNGException
    {
        int [][]resultat=MorphoElementaire.dilatation(image, 3);
           resultat=ContoursNonLineaire.MatriceDifférence(image, resultat);
      
        
        return resultat;
    }
    
    public static int[][]gradientBeucher(int[][]image) throws CImageNGException
    {
        int [][]resultat1=MorphoElementaire.dilatation(image, 3);
        int [][]resultat2=MorphoElementaire.erosion(image, 3);
        
         int[][] resultat3=ContoursNonLineaire.MatriceDifférence(resultat1, resultat2);
      
        
        return resultat3;
    }
    
    public static int[][]laplacienNonLineaire(int[][]image) throws CImageNGException
    {
        int [][]resultat1=gradientDilatation(image);
        int [][]resultat2=gradientErosion(image);
     
         int[][] resultat3=ContoursNonLineaire.MatriceDifférence(resultat1, resultat2);
    
        return resultat3;
    }
    
     public static int[][] MatriceDifférence(int[][] a1, int[][] a2)
    {
        int ligne = a1.length;
        int colonne = a1[0].length;
        int[][] resultat = new int[ligne][colonne];

        for (int i = 0; i < ligne; ++i)
        {
            for (int j = 0; j < colonne; ++j)
            {
                resultat[i][j] = a1[i][j] - a2[i][j];
                if(resultat[i][j] < 0)
                    resultat[i][j] = 0;
            }
        }

        return resultat;
    }
}
