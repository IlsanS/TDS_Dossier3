package ImageProcessing.Histogramme.Events;

import java.util.EventObject;

//événement pour la modification de la valeur de gamma 
//pour une transformation gamma
public class GammaValueChangedEvent extends EventObject
{
    private double gamma;

    public GammaValueChangedEvent(Object source, double gamma) 
    {
        super(source);
        this.gamma = gamma;
    }
    
    public double getGamma()
    {
        return this.gamma;
    }
}
