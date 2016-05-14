/*
 * Histogramme.java
 *
 * Created on 23 septembre 2007, 20:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ImageProcessing.Histogramme;

/**
 *
 * @author Jean-Marc
 */
public class Histogramme 
{
    public static int[] Histogramme256(int mat[][])
    {
        int M = mat.length;
        int N = mat[0].length;
        int histo[] = new int[256];
        
        for(int i=0 ; i<256 ; i++) histo[i] = 0;
        
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if ((mat[i][j] >= 0) && (mat[i][j]<=255)) histo[mat[i][j]]++;
        
        return histo;
    }
    
    public static int minimum(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int pixelValue;
        int minimum = 255;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                pixelValue = image[xImage][yImage];
                if(pixelValue<minimum)
                    minimum = pixelValue;
            }
        }
        
        return minimum;
    }
    
    public static int maximum(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int pixelValue;
        int maximum = 0;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                pixelValue = image[xImage][yImage];
                if(pixelValue>maximum)
                    maximum = pixelValue;
            }
        }
        
        return maximum;
    }
    
    public static int luminance(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int luminance = 0;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                luminance += image[xImage][yImage];
            }
        }
        luminance /= xSize*ySize;
        
        return luminance;
    }
    
    public static double contraste1(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int luminance;
        double contraste = 0;
        
        luminance = luminance(image);
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                contraste += (image[xImage][yImage]-luminance)^2;
            }
        }
        contraste = Math.sqrt(contraste/(xSize*ySize));
        
        return contraste;
    }
    
    public static double contraste2(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int minimum = 255;
        int maximum = 0;
        int pixelValue;
        double contraste;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                pixelValue = image[xImage][yImage];
                if(pixelValue < minimum)
                    minimum = pixelValue;
                if(pixelValue > maximum)
                    maximum = pixelValue;
            }
        }
        contraste = (maximum-minimum)/(maximum+minimum);
        
        return contraste;
    }
    
    public static int[][] rehaussement(int[][] image, int[] courbeTonale)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                imageNew[xImage][yImage] = courbeTonale[image[xImage][yImage]];
            }
        }
        
        return imageNew;
    }
    
    public static int[] creeCourbeTonaleLineaireSaturation(int smin, int smax)
    {
        int[] courbeTonale = new int[256];
        
        for(int i=0; i<256; i++)
        {
            if(i<smin)
                courbeTonale[i] = 0;
            else if(i>=smin && i<=smax)
                courbeTonale[i] = (int) Math.round(255.*(i-smin)/(smax-smin));
            else if(i>smax)
                courbeTonale[i] = 255;
        }
        
        return courbeTonale;
    }
    
    public static int[] creeCourbeTonaleGamma(double gamma)
    {
        int[] courbeTonale = new int[256];
        
        for(int i=0; i<256; i++)
        {            
            courbeTonale[i] = (int) Math.round(255.*Math.pow((double)i/255, 1./gamma));
        }
        
        return courbeTonale;
    }
    
    public static int[] creeCourbeTonaleNegatif()
    {
        int[] courbeTonale = new int[256];
        
        for(int i=0; i<256; i++)
        {
            courbeTonale[i] = 255-i;
        }
        
        return courbeTonale;
    }
    
    public static int[] creeCourbeTonaleEgalisation(int[][] image)
    {
        int[] courbeTonale = new int[256];
        double[] histoNorm = new double[256];
        double[] histoFreqCumul = new double[256];
        
        histoNorm = histogrammeNormalise(image);
        histoFreqCumul = histogrammeFreqCumul(histoNorm);
        
        for(int i=0; i<256; i++)
        {
            courbeTonale[i] = (int) Math.round(255.*histoFreqCumul[i]);
        }
        
        return courbeTonale;
    }
    
    private static double[] histogrammeNormalise(int[][] image)
    {
        int xSize = image.length;
        int ySize = image[0].length;
        int[] histo = new int[256];
        double[] histoNorm = new double[256];
        
        histo = Histogramme256(image);
        
        for(int i=0; i<256; i++)
        {
            histoNorm[i] = (double) histo[i]/(xSize*ySize);
        }
        
        return histoNorm;        
    }
    
    private static double[] histogrammeFreqCumul(double[] histoNorm)
    {
        double[] histoFreqCumul = new double[256];
        double sum = 0;        
        
        for(int i=0; i<256; i++)
        {
            sum += histoNorm[i];
            histoFreqCumul[i] = sum;
        }
        
        return histoFreqCumul;
    }
}
