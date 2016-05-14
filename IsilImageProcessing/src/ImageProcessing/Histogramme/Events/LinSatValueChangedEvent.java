package ImageProcessing.Histogramme.Events;

import java.util.EventObject;

//�v�nement pour la modification de la valeur du slider min ou max
//pour une transformation lin�aire avec saturation
public class LinSatValueChangedEvent extends EventObject
{
    private int min;
    private int max;
    
    public LinSatValueChangedEvent(Object source, int min, int max)
    {
        super(source);
        this.min = min;
        this.max = max;
    }
    
    public int getMin()
    {
        return min;
    }
    
    public int getMax()
    {
        return max;
    }
}
