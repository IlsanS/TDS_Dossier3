package ImageProcessing.NonLineaire;

import CImage.Exceptions.CImageNGException;
import java.util.Arrays;

public class MorphoComplexe 
{
    //dilatation g�od�sique
    public static int[][] dilatationGeodesique(
            int[][] image, int[][] masqueGeodesique, int tailleElemStruct, int nbIter) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        
        System.out.println("Dilatation g�od�sique");
        
        imageNew = image;
        
        //on boucle le nombre de fois sp�cifi� en param�tre
        for(int i=0; i<nbIter; i++)
        {
            //dilatation binaire
            imageNew = MorphoElementaire.dilatationBinaire(imageNew, tailleElemStruct);  
            
            //application du masque g�od�sique
            imageNew = masqueGeodesique(imageNew, masqueGeodesique);
        }       
        
        return imageNew;
    }
    
    private static int[][] masqueGeodesique(int[][] image, int[][] masqueGeodesique)
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                //si le pixel consid�r� est noir dans le masque (fait partie de l'objet)
                //alors le pixel de l'image de d�part est copi� dans la nouvelle image
                //sinon le pixel est mis � 255 dans la nouvelle image
                if(masqueGeodesique[xImage][yImage] == 0)
                    imageNew[xImage][yImage] = image[xImage][yImage];
                else imageNew[xImage][yImage] = 255;
            }
        }
        
        return imageNew;
    }
    
    public static int[][] reconstructionGeodesique(
            int[][] image, int[][] masqueGeodesique, int tailleElemStruct) throws CImageNGException
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageTmp = new int[xSize][ySize];
        int[][] imageNew = new int[xSize][ySize];
        int i = 0;
        
        System.out.println("Reconstruction g�od�sique");       
        imageNew = image;
        while(true)
        {
            //utilisation d'une image temporaire pour la comparaison
            imageTmp = imageNew;
            
            //application de la dilatation g�od�sique
            imageNew = dilatationGeodesique(imageNew, masqueGeodesique, tailleElemStruct, 1);
            
            i++;
            
            //on boucle tant que la dilatation g�od�sique apporte une modification
            //on peut quitter la boucle quand la dilatation g�od�sique n'a plus d'effet
            if(areEqual(imageTmp, imageNew))
                break;
        }
        System.out.println("Reconstruction r�alis�e en " + i + " it�rations");
        
        return imageNew;
    }
    
    public static int[][] filtreMedian(int[][] image, int tailleElemStruct)
    {
        int xSize=image.length;
        int ySize=image[0].length;
        int[][] imageNew = new int[xSize][ySize];
        
        //on utilise un tableau pour stocker les valeurs des pixels
        //avant d'en r�cup�rer la valeur m�diane
        int[] pixelsStruct = new int[tailleElemStruct*tailleElemStruct];
        
        int medianValue;
        int pixelValue;
        int pixelIndex;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                //on commence au d�but du tableau
                pixelIndex = 0;
                for(int yStruct=yImage-(tailleElemStruct-1)/2; yStruct<=yImage+(tailleElemStruct-1)/2; yStruct++)
                {
                    for(int xStruct=xImage-(tailleElemStruct-1)/2; xStruct<=xImage+(tailleElemStruct-1)/2; xStruct++)
                    {
                        //v�rification qu'on est dans l'image et pas en dehors
                        if(xStruct>=0 && yStruct>=0 && xStruct<xSize && yStruct<ySize)
                        {
                            //on r�cup�re la valeur du pixel courant
                            pixelValue = image[xStruct][yStruct];
                            
                            //on place cette valeur dans le tableau
                            pixelsStruct[pixelIndex] = pixelValue;
                            
                            //on incr�mente l'index consid�r� du tableau
                            pixelIndex++;
                        }
                    }
                }
                //on classe le tableau des pixels par ordre d�croissant
                pixelsStruct = orderPixels(pixelsStruct);
                
                //on prend la valeur m�diane des pixels du tableau
                medianValue = pixelsStruct[((tailleElemStruct*tailleElemStruct)+1)/2];
                
                //on attribue cette valeur m�diane au pixel de la nouvelle image
                imageNew[xImage][yImage] = medianValue;
            }
        }
        
        return imageNew;
    }
    
    public static boolean areEqual(int[][] image1, int[][] image2)
    {
        int xSize=image1.length;
        int ySize=image1[0].length;
        
        for(int yImage=0; yImage<ySize; yImage++)
        {
            for(int xImage=0; xImage<xSize; xImage++)
            {
                //au premier pixel diff�rent dans les deux images, on retourne false
                if(image1[xImage][yImage] != image2[xImage][yImage])
                    return false;
            }
        }
        
        return true;
    }
    
    public static int[] orderPixels(int[] pixels)
    {
        //tri par ordre croissant
        Arrays.sort(pixels);
        int[] orderedPixels = new int[pixels.length];
        
        //recopiage en commen�ant par le dernier �l�ment, pour avoir un tri d�croissant
        for(int i=0; i<orderedPixels.length; i++)
        {
            orderedPixels[i] = pixels[pixels.length-1-i];
        }
        
        return orderedPixels;
    }
}
