package isilimageprocessing.Panels;

import java.text.DecimalFormat;

public class JPanelAfficherParametresImage extends javax.swing.JPanel 
{

    public JPanelAfficherParametresImage() 
    {
        initComponents();
    }
    
    public void setMinimum(int minimum)
    {
        this.jLabelMinimumValue.setText(String.valueOf(minimum));
    }
    
    public void setMaximum(int maximum)
    {
        this.jLabelMaximumValue.setText(String.valueOf(maximum));
    }
    
    public void setLuminance(int luminance)
    {
        this.jLabelLuminanceValue.setText(String.valueOf(luminance));
    }
    
    public void setContraste1(double contraste1)
    {
        DecimalFormat df = new DecimalFormat("0.0000");
        this.jLabelContraste1Value.setText(df.format(contraste1));
    }
    
    public void setContraste2(double contraste2)
    {
        DecimalFormat df = new DecimalFormat("0.0000");
        this.jLabelContraste2Value.setText(df.format(contraste2));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelMinimum = new javax.swing.JLabel();
        jLabelMinimumValue = new javax.swing.JLabel();
        jLabelMaximum = new javax.swing.JLabel();
        jLabelMaximumValue = new javax.swing.JLabel();
        jLabelLuminance = new javax.swing.JLabel();
        jLabelLuminanceValue = new javax.swing.JLabel();
        jLabelContraste1 = new javax.swing.JLabel();
        jLabelContraste1Value = new javax.swing.JLabel();
        jLabelContraste2 = new javax.swing.JLabel();
        jLabelContraste2Value = new javax.swing.JLabel();

        jLabelMinimum.setText("Minimum :");

        jLabelMaximum.setText("Maximum :");

        jLabelLuminance.setText("Luminance :");

        jLabelContraste1.setText("Contraste1 :");

        jLabelContraste2.setText("Contraste2 :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMaximum)
                    .addComponent(jLabelMinimum)
                    .addComponent(jLabelLuminance)
                    .addComponent(jLabelContraste1)
                    .addComponent(jLabelContraste2))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMinimumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMaximumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLuminanceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelContraste1Value, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelContraste2Value, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMinimumValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMinimum))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelMaximum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMaximumValue, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLuminance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLuminanceValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContraste1)
                    .addComponent(jLabelContraste1Value, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContraste2)
                    .addComponent(jLabelContraste2Value, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelContraste1;
    private javax.swing.JLabel jLabelContraste1Value;
    private javax.swing.JLabel jLabelContraste2;
    private javax.swing.JLabel jLabelContraste2Value;
    private javax.swing.JLabel jLabelLuminance;
    private javax.swing.JLabel jLabelLuminanceValue;
    private javax.swing.JLabel jLabelMaximum;
    private javax.swing.JLabel jLabelMaximumValue;
    private javax.swing.JLabel jLabelMinimum;
    private javax.swing.JLabel jLabelMinimumValue;
    // End of variables declaration//GEN-END:variables
}
