/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageProcessing.Lineaire;

/**
 *
 * @author Pc
 */


public class FiltrageLineaireLocal 
{
    
     public static int[][] filtreMasqueConvolution(int[][] image,double [][] masque)
    {
        int ligne=image.length;
        int col=image[0].length;
        int [][]final_image=new int[ligne][col];                
        int ligneMask=masque.length;
        int colMask=masque[0].length;
        
        for(int i=0;i<ligne;i++)
        {
            for(int j=0;j<col;j++)
            {
                double y_mn=0;
                for(int im=0;im<ligneMask;im++)
                {
                    int liPlus=(ligneMask/2)-im;
                    if(i-liPlus <0 || i-liPlus >=ligne)
                
                        liPlus=-liPlus;
                    
                    for(int jm=0;jm<colMask;jm++)
                    {
                        int colPlus=(colMask/2)-jm;
                        if(j-colPlus <0 || j-colPlus >= col)
                            colPlus=-colPlus;

                  
                        y_mn+=masque[im][jm]*image[i-liPlus][j-colPlus];
                    }
                }
                 if (y_mn > 255)
                {
                    y_mn = 255;
                }
                else if (y_mn < 0)
                {
                    y_mn = 0;
                }
                  
                final_image[i][j]=(int) y_mn;
                
            }
        }
        
        return final_image;
    }
    

    
    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque)
    {
        double[][] masque = new double[tailleMasque][tailleMasque];
        double coef = 1. / (tailleMasque*tailleMasque);
    
  
        for(int i=0; i<tailleMasque; i++)
        {
            for(int j=0; j<tailleMasque; j++)
            {
                masque[i][j] = coef;
            }
        }

        return filtreMasqueConvolution(image, masque);
    }

}
