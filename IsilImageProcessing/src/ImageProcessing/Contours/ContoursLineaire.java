/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing.Contours;

import ImageProcessing.Lineaire.FiltrageLineaireLocal;
import ImageProcessing.Lineaire.MatriceConverter;

/**
 *
 * @author PC
 */
public class ContoursLineaire {
    
    public static int[][] gradientPrewitt(int[][] image,int dir) 
     //si dir= 1 gradient horizontal else gradient vertical
    {
        int [][]masque=null;
        if(dir==2)
        {
            masque=new int[][]{  {1,0,-1},
                                 {1,0,-1},
                                 {1,0,-1}};
        }
        if(dir==1)
        {
             masque=new int[][]{ {1,1,1},
                                 {0,0,0},
                                 {-1,-1,-1}};
        }
        
      int [][] resultat=FiltrageLineaireLocal.filtreMoyenneur(FiltrageLineaireLocal.filtreMasqueConvolution(image,MatriceConverter.IntToDouble(masque)), 3);
        
     return resultat;
    }
    
    public static int[][] gradientSobel(int[][] image,int dir)
    { 
         int [][]masque=null;
        if(dir==2)
        {
            masque=new int[][]{  {1,0,-1},
                                 {2,0,-2},
                                 {1,0,-1}};
        }
        if(dir==1)
        {
             masque=new int[][]{ {1,2,1},
                                 {0,0,0},
                                 {-1,-2,-1}};
        }
        
      int [][] resultat=FiltrageLineaireLocal.filtreMoyenneur(FiltrageLineaireLocal.filtreMasqueConvolution(image,MatriceConverter.IntToDouble(masque)), 3);
        
     return resultat;
   
    }
    public static int[][] laplacien4(int[][] image)
    {
        
         int [][]masque=null;
     
            masque=new int[][]{  {0,1,0},
                                 {1,-4,1},
                                 {0,1,0}};
     
       
        
     return     FiltrageLineaireLocal.filtreMasqueConvolution(image,MatriceConverter.IntToDouble(masque));

  
    }
    public static int[][] laplacien8(int[][] image)
    {
        
         int [][]masque=null;
     
            masque=new int[][]{  {1,1,1},
                                 {1,-8,1},
                                 {1,1,1}};
     
       
        
     return     FiltrageLineaireLocal.filtreMasqueConvolution(image,MatriceConverter.IntToDouble(masque));

       
    }
    
    
}
