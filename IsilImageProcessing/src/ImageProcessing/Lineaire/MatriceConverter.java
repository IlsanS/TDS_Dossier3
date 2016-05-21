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
public class MatriceConverter 
{
    public static double[][] IntToDouble(int[][] image)
    {
        double[][] newMatrice = new double[image.length][image[0].length];
        
        for(int i=0; i<image.length; i++)
        {
            for(int j=0; j<image[0].length; j++)
            {
                newMatrice[i][j] = (double) image[i][j];
            }
        }
        
        return newMatrice;
    }
    
    public static int[][] DoubleToInt(double[][] f)
    {
        int[][] oldImage = new int[f.length][f[0].length];
        
        for(int i=0; i<f.length; i++)
        {
            for(int j=0; j<f[0].length; j++)
            {
                oldImage[i][j] = (int) f[i][j];
            }
        }
        
        return oldImage;
    }
}
