package isilimageprocessing.Panels;

import ImageProcessing.Histogramme.Events.GammaValueChangedEvent;
import ImageProcessing.Histogramme.Events.HistoValueChangedEventListener;
import java.util.Iterator;
import java.util.Vector;

public class JPanelTransfoGammaParametres extends javax.swing.JPanel 
{
    private Vector listeners;
    private double gamma;

    public JPanelTransfoGammaParametres() 
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
        GammaValueChangedEvent gvce = 
                new GammaValueChangedEvent(this, gamma);
        
        Iterator i = listeners.iterator();
        while(i.hasNext())
            ((HistoValueChangedEventListener) i.next()).handlerHistoValueChanged(gvce);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelGamma = new javax.swing.JLabel();
        jSpinnerGammaValue = new javax.swing.JSpinner();

        jLabelGamma.setText("Gamma :");

        jSpinnerGammaValue.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(0.1d), null, Double.valueOf(0.1d)));
        jSpinnerGammaValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerGammaValueStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGamma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinnerGammaValue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGamma)
                    .addComponent(jSpinnerGammaValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinnerGammaValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerGammaValueStateChanged
        gamma = (Double) jSpinnerGammaValue.getValue();
        
        notifyListeners();
    }//GEN-LAST:event_jSpinnerGammaValueStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelGamma;
    private javax.swing.JSpinner jSpinnerGammaValue;
    // End of variables declaration//GEN-END:variables
}
