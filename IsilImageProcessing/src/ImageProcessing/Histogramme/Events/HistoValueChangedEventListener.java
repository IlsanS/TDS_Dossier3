package ImageProcessing.Histogramme.Events;

import java.util.EventObject;

//EventListener pour les événements liés aux manipulations d'histogramme
public interface HistoValueChangedEventListener 
{
    public void handlerHistoValueChanged(EventObject e);
}
