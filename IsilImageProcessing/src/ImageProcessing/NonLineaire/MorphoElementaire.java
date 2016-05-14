package ImageProcessing.NonLineaire;

import CImage.Exceptions.CImageNGException;

public class MorphoElementaire 
{
    public static int[][] erosion(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Erosion : image binaire");
            imageNew = erosionBinaire(image, tailleElemStruct);
        }
        else
        {
            System.out.println("Erosion : image niveaux gris");
            imageNew = erosionNiveauxGris(image, tailleElemStruct);  
        }
        
        return imageNew;
    }
    
    public static int[][] erosionBinaire(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
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
                    imageNew[xImage][yImage] = 0;
                else imageNew[xImage][yImage] = 255;
            }
        }
        return imageNew;
    }
    
    public static int[][] erosionNiveauxGris(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
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
                imageNew[xImage][yImage] = minimum;
            }
        }
        
        return imageNew;
    }
    
    public static int[][] dilatation(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Dilatation : image binaire");
            imageNew = dilatationBinaire(image, tailleElemStruct);
        }
        else
        {
            System.out.println("Dilatation : image niveaux gris");
            imageNew = dilatationNiveauxGris(image, tailleElemStruct);  
        }
        
        return imageNew;
    }
    
    public static int[][] dilatationBinaire(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
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
                    imageNew[xImage][yImage] = 0;
                else imageNew[xImage][yImage] = 255;
            }
        }
        
        return imageNew;
    }
    
    public static int[][] dilatationNiveauxGris(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
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
                imageNew[xImage][yImage] = maximum;
            }
        }
        
        return imageNew;
    }
    
    public static int[][] ouverture(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Ouverture : image binaire");
            imageNew = erosionBinaire(image, tailleElemStruct);
            imageNew = dilatationBinaire(imageNew, tailleElemStruct);
        }
        else
        {
            System.out.println("Ouverture : image niveaux gris");
            imageNew = erosionNiveauxGris(image, tailleElemStruct);
            imageNew = dilatationNiveauxGris(imageNew, tailleElemStruct);
        }
        
        return imageNew;
    }
    
    public static int[][] fermeture(int[][] image, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        boolean isBinary;
        
        isBinary = isBinary(image);
        
        if(isBinary)
        {
            System.out.println("Fermeture : image binaire");
            imageNew = dilatationBinaire(image, tailleElemStruct);
            imageNew = erosionBinaire(imageNew, tailleElemStruct);
        }
        else
        {
            System.out.println("Fermeture : image niveaux gris");
            imageNew = dilatationNiveauxGris(image, tailleElemStruct);
            imageNew = erosionNiveauxGris(imageNew, tailleElemStruct);
        }
        
        return imageNew;
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
