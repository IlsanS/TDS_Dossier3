package ImageProcessing.Histogramme.Events;

import java.util.EventObject;

//EventListener pour les �v�nements li�s aux manipulations d'histogramme
public interface HistoValueChangedEventListener 
{
    public void handlerHistoValueChanged(EventObject e);
}
