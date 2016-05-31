/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;


/**
 *
 * @author Pc
 */
public class FiltrageLinaireGlobal 
{
    public static int[][]PasseBasIdeal(int[][] image, int fc)
    {
        //  [F(u,v)]f
        MatriceComplexe matricePixelFourier = Fourier.Fourier2D(MatriceConverter.IntToDouble(image));
        // F(0,0) au centre
        matricePixelFourier  = Fourier.decroise(matricePixelFourier );
        //1*F[u,v] si sqrt(u²+v²)< frequence de coupure
        int nbLigne = matricePixelFourier .getLignes();
        int nbColonne = matricePixelFourier .getColonnes();
      
        
        for(int i=0; i<nbLigne; i++)
        {
            int distY = i -  nbLigne/2;
            for(int j=0; j<nbColonne; j++)
            {
                int distX = j -  nbColonne/2;
                
                // Calcule du rayon 
                Double rayon =Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
                if(rayon > fc)
                {
                   
                    matricePixelFourier .set(i,j,0,0);
                }
                
            }
        }
       
        //4. [G(u,v) -> g(x,y)]
        return MatriceConverter.DoubleToInt(Fourier.InverseFourier2D(Fourier.decroise(matricePixelFourier )).getPartieReelle());
    }
    
    public static int[][] PasseHautIdeal(int[][] image, int fc)
    {
         //  [F(u,v)]
        MatriceComplexe matricePixelFourier = Fourier.Fourier2D(MatriceConverter.IntToDouble(image));
        // F(0,0) au centre
        matricePixelFourier = Fourier.decroise(matricePixelFourier);
        //1*F[u,v] si sqrt(u²+v²)>frequence de coupure
        
        int nbLigne = matricePixelFourier.getLignes();
        int nbColonne = matricePixelFourier.getColonnes();
     
        
        for(int i=0; i<nbLigne; i++)
        {
            int distY = i - nbLigne/2;
            for(int j=0; j<nbColonne; j++)
            {
                int distX = j - nbColonne/2;
                // Calcule du rayon 
                Double rayon =Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
                if(rayon < fc)
                {
                    matricePixelFourier.set(i,j,0,0);
                }
                
            }
        }
         matricePixelFourier.set(nbLigne/2,  nbColonne/2, 128, 0);
       
        //4. [G(u,v) -> g(x,y)]
        return MatriceConverter.DoubleToInt(Fourier.InverseFourier2D(Fourier.decroise(matricePixelFourier)).getPartieReelle());
 
    }
    
    public static int[][] PasseBasButterworth(int[][] image, int fc, int n)
    {
        //  [F(u,v)]f
        MatriceComplexe matricePixelFourier = Fourier.Fourier2D(MatriceConverter.IntToDouble(image));
        matricePixelFourier = Fourier.decroise(matricePixelFourier);
     
        int nbLigne = matricePixelFourier.getLignes();
        int nbColonne = matricePixelFourier.getColonnes();
  
         //  Je parcours ma matrice de Fourier 
        for(int i=0; i<nbLigne; i++)
        {
            int distY = i - nbLigne/2;
            
            for(int j=0; j<nbColonne; j++)
            {
                int distX = j - nbColonne/2;
                
                //Filtrage passe-haut Butterworth
                Double rayon =Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
                
                // calcul du coéfficient en fonction du rayon et de l'ordre n
                // le rayon est au numérateur, lorsque u,v augmente le coef diminue
                double coeffcient = 1 / (1+Math.pow(rayon/fc,2*n));  
                
                //multiplication du coefficient à la matrice
                matricePixelFourier.get(i, j).multiplie(new Complexe(coeffcient, 0));
            }
        }
        
        //  de la fonction frequentielle on passe en temporel
        return MatriceConverter.DoubleToInt(Fourier.InverseFourier2D(Fourier.decroise(matricePixelFourier)).getPartieReelle());
    }
    
    public static int[][] PasseHautButterworth(int[][] image, int frequenceCoupure, int n)
    {
        MatriceComplexe fourier2D = Fourier.Fourier2D(MatriceConverter.IntToDouble(image));
        fourier2D = Fourier.decroise(fourier2D);
        int nbLigne = fourier2D.getLignes();
        int nbColonne = fourier2D.getColonnes();
        
         //  Je parcours ma matrice de Fourier 
        for(int i=0; i<nbLigne; i++)
        {
            int distY = i -  nbLigne/2;
            
            for(int j=0; j<nbColonne; j++)
            {
                int distX = j - nbColonne/2;
                
                //Filtrage passe-haut Butterworth
                Double rayon =Math.sqrt(Math.pow(distX, 2)+Math.pow(distY, 2));
                  // calcul du coéfficient en fonction du rayon et de l'ordre n
                // le rayon est au denominateur, lorsque u,v diminue le coef diminue
                
                double coefficient = 1 / (1+Math.pow(frequenceCoupure/rayon,2*n));  
                
                fourier2D.get(i, j).multiplie(new Complexe(coefficient, 0));
            }
        }
       
        fourier2D.set( nbLigne/2, nbColonne/2, 128, 0);
        
        //  de la fonction frequentielle on passe en temporel
        return MatriceConverter.DoubleToInt(Fourier.InverseFourier2D(Fourier.decroise(fourier2D)).getPartieReelle());
    }
}
