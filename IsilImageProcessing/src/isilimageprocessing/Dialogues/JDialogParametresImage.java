package isilimageprocessing.Dialogues;

import isilimageprocessing.Panels.JPanelAfficherParametresImage;
import java.text.DecimalFormat;

public class JDialogParametresImage extends javax.swing.JDialog 
{
    private JPanelAfficherParametresImage jpapi;
    
    public JDialogParametresImage(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        initComponents();
        
        jpapi = new JPanelAfficherParametresImage();
        jPanelParametres.setLayout(new java.awt.FlowLayout());
        jPanelParametres.add(jpapi);
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public void setMinimum(int minimum)
    {
        jpapi.setMinimum(minimum);
    }
    
    public void setMaximum(int maximum)
    {
        jpapi.setMaximum(maximum);
    }
    
    public void setLuminance(int luminance)
    {
        jpapi.setLuminance(luminance);
    }
    
    public void setContraste1(double contraste1)
    {
        jpapi.setContraste1(contraste1);
    }
    
    public void setContraste2(double contraste2)
    {
        jpapi.setContraste2(contraste2);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonOK = new javax.swing.JButton();
        jPanelParametres = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paramètres de l'image");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelParametresLayout = new javax.swing.GroupLayout(jPanelParametres);
        jPanelParametres.setLayout(jPanelParametresLayout);
        jPanelParametresLayout.setHorizontalGroup(
            jPanelParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelParametresLayout.setVerticalGroup(
            jPanelParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelParametres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelParametres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOK;
    private javax.swing.JPanel jPanelParametres;
    // End of variables declaration//GEN-END:variables
}
