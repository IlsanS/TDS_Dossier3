package isilimageprocessing.Panels;

import ImageProcessing.Histogramme.Events.HistoValueChangedEventListener;
import ImageProcessing.Histogramme.Events.LinSatValueChangedEvent;
import java.util.Iterator;
import java.util.Vector;

public class JPanelTransfoLinSatParametres extends javax.swing.JPanel 
{
    private Vector listeners;
    private int minValue;
    private int maxValue;
    
    public JPanelTransfoLinSatParametres() 
    {
        initComponents();
        
        this.listeners = new Vector();
    }
    
    public void addEventListener(HistoValueChangedEventListener listener)
    {
        if(this.listeners == null)
            listeners = new Vector();
        
        listeners.add(listener);
    }
    
    public void removeEventListener(HistoValueChangedEventListener listener)
    {
        if(this.listeners != null)
        {
            listeners.remove(listener);
        }
    }
    
    private void notifyListeners()
    {
        LinSatValueChangedEvent lsvce = 
                new LinSatValueChangedEvent(this, minValue, maxValue);
        
        Iterator i = listeners.iterator();
        while(i.hasNext())
            ((HistoValueChangedEventListener) i.next()).handlerHistoValueChanged(lsvce);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelSaturationMin = new javax.swing.JLabel();
        jSliderSatMin = new javax.swing.JSlider();
        jLabelSatMinValue = new javax.swing.JLabel();
        jLabelSaturationMax = new javax.swing.JLabel();
        jSliderSatMax = new javax.swing.JSlider();
        jLabelSatMaxValue = new javax.swing.JLabel();

        jLabelSaturationMin.setText("Saturation min :");

        jSliderSatMin.setMajorTickSpacing(1);
        jSliderSatMin.setMaximum(255);
        jSliderSatMin.setMinorTickSpacing(1);
        jSliderSatMin.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderSatMin.setValue(0);
        jSliderSatMin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderSatMinStateChanged(evt);
            }
        });

        jLabelSaturationMax.setText("Saturation max :");

        jSliderSatMax.setMajorTickSpacing(1);
        jSliderSatMax.setMaximum(255);
        jSliderSatMax.setMinorTickSpacing(1);
        jSliderSatMax.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderSatMax.setValue(255);
        jSliderSatMax.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderSatMaxStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSaturationMin)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderSatMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSatMinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSaturationMax)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSatMaxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSliderSatMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSaturationMin)
                    .addComponent(jLabelSaturationMax))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderSatMin, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jSliderSatMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSatMinValue, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(jLabelSatMaxValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSliderSatMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderSatMinStateChanged
        minValue = jSliderSatMin.getValue();
        maxValue = jSliderSatMax.getValue();
        
        if(minValue >= maxValue)
            jSliderSatMin.setValue(maxValue);
        else
            jLabelSatMinValue.setText(String.valueOf(minValue));
        
        notifyListeners();
    }//GEN-LAST:event_jSliderSatMinStateChanged

    private void jSliderSatMaxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderSatMaxStateChanged
        minValue = jSliderSatMin.getValue();
        maxValue = jSliderSatMax.getValue();
        
        if(maxValue <= minValue)
            jSliderSatMax.setValue(minValue);
        else 
            jLabelSatMaxValue.setText(String.valueOf(maxValue));
        
        notifyListeners();
    }//GEN-LAST:event_jSliderSatMaxStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelSatMaxValue;
    private javax.swing.JLabel jLabelSatMinValue;
    private javax.swing.JLabel jLabelSaturationMax;
    private javax.swing.JLabel jLabelSaturationMin;
    private javax.swing.JSlider jSliderSatMax;
    private javax.swing.JSlider jSliderSatMin;
    // End of variables declaration//GEN-END:variables
}
