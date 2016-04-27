package ImageProcessing.NonLineaire;

import CImage.Exceptions.CImageNGException;
import java.util.Arrays;

public class MorphoComplexe 
{
    
    public static int[][] dilatationGeodesique(
            int[][] image, int[][] masqueGeodesique, int tailleElemStruct, int nbIter) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        
        System.out.println("Dilatation géodésique");
        for(int i=0; i<nbIter; i++)
        {
            if(i==0)
                imageNewPixels = MorphoElementaire.dilatationBinaire(image, tailleElemStruct);
            else
                imageNewPixels = MorphoElementaire.dilatationBinaire(imageNewPixels, tailleElemStruct);  
            
            imageNewPixels = masqueGeodesique(imageNewPixels, masqueGeodesique);
        }       
        
        return imageNewPixels;
    }
    
    private static int[][] masqueGeodesique(int[][] image, int[][] masqueGeodesique)
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                if(masqueGeodesique[xImage][yImage] == 0)
                    imageNewPixels[xImage][yImage] = image[xImage][yImage];
                else imageNewPixels[xImage][yImage] = 255;
            }
        }
        
        return imageNewPixels;
    }
    
    public static int[][] reconstructionGeodesique(
            int[][] image, int[][] masqueGeodesique, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageTmp = new int[xSize][ySize];
        int[][] imageNewPixels = new int[xSize][ySize];
        int i = 0;
        
        System.out.println("Reconstruction géodésique");       
        imageNewPixels = image;
        while(true)
        {
            imageTmp = imageNewPixels;
            imageNewPixels = dilatationGeodesique(imageNewPixels, masqueGeodesique, tailleElemStruct, 1);
            i++;
            if(areEqual(imageTmp, imageNewPixels))
                break;
        }
        System.out.println("Reconstruction réalisée en " + i + " itérations");
        
        return imageNewPixels;
    }
    
    public static int[][] filtreMedian(int[][] image, int tailleElemStruct)
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        int[] pixelsStruct = new int[tailleElemStruct*tailleElemStruct];
        int medianValue;
        int pixelValue;
        int pixelIndex;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                pixelIndex = 0;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            pixelValue = image[xStruct][yStruct];
                            pixelsStruct[pixelIndex] = pixelValue;
                            pixelIndex++;
                        }
                    }
                }
                pixelsStruct = orderPixels(pixelsStruct);
                medianValue = pixelsStruct[((tailleElemStruct*tailleElemStruct)+1)/2];
                imageNewPixels[xImage][yImage] = medianValue;
            }
        }
        
        return imageNewPixels;
    }
    
    public static boolean areEqual(int[][] image1, int[][] image2)
    {
        int xSize=image1.length;
        int ySize=image1[0].length;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                if(image1[xImage][yImage] != image2[xImage][yImage])
                    return false;
            }
        }
        
        return true;
    }
    
    public static int[] orderPixels(int[] pixels)
    {
        Arrays.sort(pixels);
        int[] orderedPixels = new int[pixels.length];
        
        for(int i=0; i<orderedPixels.length; i++)
        {
            orderedPixels[i] = pixels[pixels.length-1-i];
        }
        
        return orderedPixels;
    }
}
