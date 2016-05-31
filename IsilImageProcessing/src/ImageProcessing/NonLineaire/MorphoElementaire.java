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
        
        //test image binaire ou niveaux gris
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
                //on considère l'élément structurant inclus dans l'objet
                included=true;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            //au premier pixel de l'élément structurant non inclus dans l'objet, 
                            //on considère l'élément structurant non inclus dans l'objet
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
                
                //si l'élément structurant est inclus dans l'objet, le nouveau
                //pixel considéré est mis à 0, donc forme l'objet
                //sinon il est mis à 255 comme l'arrière plan
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
                //on assigne au minimum la plus grande valeur possible
                minimum=255;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            //pour chaque pixel de l'élément structurant, si la valeur
                            //du pixel est plus petite que le précédent minimum,
                            //c'est cette valeur qui est gardée
                            pixelValue = image[xStruct][yStruct];
                            if(pixelValue<minimum)
                                minimum = pixelValue;
                        }
                    }
                }
                
                //le nouveau pixel considéré a la valeur du minimum des pixels
                //de l'élément structurant
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
        
        //test image binaire ou niveaux gris
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
                //on considère que l'élément structurant ne touche par l'objet
                touch=false;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            //au premier pixel de l'élément structurant inclus dans l'objet, 
                            //on considère l'élément structurant comme touchant l'objet 
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
                
                //si l'élément structurant touche l'objet, le nouveau
                //pixel considéré est mis à 0, donc forme l'objet
                //sinon il est mis à 255 comme l'arrière plan
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
                //on assigne au minimum la plus petite valeur possible
                maximum=0;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            //pour chaque pixel de l'élément structurant, si la valeur
                            //du pixel est plus grande que le précédent maximum,
                            //c'est cette valeur qui est gardée
                            pixelValue = image[xStruct][yStruct];
                            if(pixelValue>maximum)
                                maximum = pixelValue;
                        }
                    }
                }
                
                //le nouveau pixel considéré a la valeur du maximum des pixels
                //de l'élément structurant
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
        
        //test image binaire ou niveaux gris
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
        
        //test image binaire ou niveaux gris
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
                //au premier pixel différent de 0 ou 255, on retourne false
                //l'image est en niveaux de gris et pas binaire
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
