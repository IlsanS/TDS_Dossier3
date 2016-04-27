package ImageProcessing.NonLineaire;

import CImage.Exceptions.CImageNGException;

public class MorphoElementaire 
{
    public static int[][] erosion(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Erosion : image binaire");
            imageNewPixels = erosionBinaire(image, tailleElemStruct);
        }
        else
        {
            System.out.println("Erosion : image niveaux gris");
            imageNewPixels = erosionNiveauxGris(image, tailleElemStruct);  
        }
        
        return imageNewPixels;
    }
    
    public static int[][] erosionBinaire(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean included;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                included=true;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            if(image[xStruct][yStruct] != 0)
                            {
                                included=false;
                                break;
                            }
                        }
                    }
                    if(!included)
                        break;
                }

                if(included)
                    imageNewPixels[xImage][yImage] = 0;
                else imageNewPixels[xImage][yImage] = 255;
            }
        }
        return imageNewPixels;
    }
    
    public static int[][] erosionNiveauxGris(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        int pixelValue;
        int minimum;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                minimum=255;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            pixelValue = image[xStruct][yStruct];
                            if(pixelValue<minimum)
                                minimum = pixelValue;
                        }
                    }
                }
                imageNewPixels[xImage][yImage] = minimum;
            }
        }
        
        return imageNewPixels;
    }
    
    public static int[][] dilatation(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Dilatation : image binaire");
            imageNewPixels = dilatationBinaire(image, tailleElemStruct);
        }
        else
        {
            System.out.println("Dilatation : image niveaux gris");
            imageNewPixels = dilatationNiveauxGris(image, tailleElemStruct);  
        }
        
        return imageNewPixels;
    }
    
    public static int[][] dilatationBinaire(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean touch;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                touch=false;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            if(image[xStruct][yStruct] == 0)
                            {
                                touch=true;
                                break;
                            }
                        }
                    }
                    if(touch)
                        break;
                }
                if(touch)
                    imageNewPixels[xImage][yImage] = 0;
                else imageNewPixels[xImage][yImage] = 255;
            }
        }
        
        return imageNewPixels;
    }
    
    public static int[][] dilatationNiveauxGris(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        int pixelValue;
        int maximum;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                maximum=0;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            pixelValue = image[xStruct][yStruct];
                            if(pixelValue>maximum)
                                maximum = pixelValue;
                        }
                    }
                }
                imageNewPixels[xImage][yImage] = maximum;
            }
        }
        
        return imageNewPixels;
    }
    
    public static int[][] ouverture(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Ouverture : image binaire");
            imageNewPixels = erosionBinaire(image, tailleElemStruct);
            imageNewPixels = dilatationBinaire(imageNewPixels, tailleElemStruct);
        }
        else
        {
            System.out.println("Ouverture : image niveaux gris");
            imageNewPixels = erosionNiveauxGris(image, tailleElemStruct);
            imageNewPixels = dilatationNiveauxGris(imageNewPixels, tailleElemStruct);
        }
        
        return imageNewPixels;
    }
    
    public static int[][] fermeture(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNewPixels = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Fermeture : image binaire");
            imageNewPixels = dilatationBinaire(image, tailleElemStruct);
            imageNewPixels = erosionBinaire(imageNewPixels, tailleElemStruct);
        }
        else
        {
            System.out.println("Fermeture : image niveaux gris");
            imageNewPixels = dilatationNiveauxGris(image, tailleElemStruct);
            imageNewPixels = erosionNiveauxGris(imageNewPixels, tailleElemStruct);
        }
        
        return imageNewPixels;
    }
    
    
    public static boolean isBinary(int[][] image)
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int pixelValue;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                pixelValue = image[xImage][yImage];
                if(pixelValue != 0 && pixelValue != 255)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
}
