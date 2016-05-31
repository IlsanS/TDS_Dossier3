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
        // pour convoluer avec matrice 3x3 on place le pixel au centre du masque, 
        // et on multiplie les terme h du masque avec les pixel de l'image qui on le même indice
        //on additionne ces produit et ça donne le pixel convolué.
        
        //paramètre de la matrice
        int ligne=image.length;
        int col=image[0].length;
        int [][]final_image=new int[ligne][col];     
        
        //paramètres du masque
        int ligneMask=masque.length;
        int colMask=masque[0].length;
        
        for(int i=0;i<ligne;i++)
        {
            for(int j=0;j<col;j++)
            {
                double y_mn=0;
                for(int im=0;im<ligneMask;im++)
                {
                    //liplux = position im du masque par rapport à son centre 
                    // si i-liplus est < 0 -> que le pixel du masque est plus tot que le pixel de l'image 
                    //si i-liPlus >= ligne -> que le pixel du maque dépasse vers la droite (max =ligne)
                    // si c'est le cas on miroirise en prennant li = ligneMask/2 + im;
                    // Pareil pour les colonnes
                    int liPlus=(ligneMask/2)-im;
                    if(i-liPlus <0 || i-liPlus >=ligne)
                
                        liPlus=-liPlus;
                    
                    for(int jm=0;jm<colMask;jm++)
                    {
                        int colPlus=(colMask/2)-jm;
                        if(j-colPlus <0 || j-colPlus >= col)
                            colPlus=-colPlus;

                  // le i-liPlus correspond au h11*x(m-1,n-1) 
                        y_mn+=masque[im][jm]*image[i-liPlus][j-colPlus];
                    }
                }
                //test de la conformité de l'image obtenue 
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
        //Initialisation du masque matrice
        double[][] masque = new double[tailleMasque][tailleMasque];
        
        //  pour un ematrice d*d le coeff = 1/d^2
        double coef = 1. / (tailleMasque*tailleMasque);
    
        
        // construction de la matrice contenant les 1/d^2
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
