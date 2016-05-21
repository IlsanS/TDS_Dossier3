/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isilimageprocessing.Dialogues;

import java.awt.event.InputMethodListener;

/**
 *
 * @author PC
 */
public class JDialogChoixParamètreFiltre extends javax.swing.JDialog {
    Boolean filtreNonIdeal;
    private int frequenceCoupure;
    private int ordre;
    
    

    public JDialogChoixParamètreFiltre(java.awt.Frame parent, boolean modal,boolean Nonideal) {
        
        super(parent, modal);
       
        initComponents();
        if(Nonideal == false)
        {
            this.jLabel2.setVisible(false);
            this.JspinnerOrdre.setVisible(false);
            filtreNonIdeal=Nonideal;
        }
            
 }
    public JDialogChoixParamètreFiltre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JspinnerFreqCoupure = new javax.swing.JSpinner();
        ButtonOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JspinnerOrdre = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Frequence de coupure");

        JspinnerFreqCoupure.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));

        ButtonOk.setText("Ok");
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        jLabel2.setText("Ordre");

        JspinnerOrdre.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ButtonOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(JspinnerFreqCoupure, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JspinnerOrdre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspinnerFreqCoupure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspinnerOrdre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(ButtonOk))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public synchronized void addInputMethodListener(InputMethodListener il) {
        super.addInputMethodListener(il); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        setFrequenceCoupure((int) (Integer)JspinnerFreqCoupure.getValue());
    
           if(filtreNonIdeal==true)
           setOrdre((int) (Integer)JspinnerOrdre.getValue());
        
            this.dispose();
    }//GEN-LAST:event_ButtonOkActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonOk;
    private javax.swing.JSpinner JspinnerFreqCoupure;
    private javax.swing.JSpinner JspinnerOrdre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the frequenceCoupure
     */
    public int getFrequenceCoupure() {
        return frequenceCoupure;
    }

    /**
     * @param frequenceCoupure the frequenceCoupure to set
     */
    public void setFrequenceCoupure(int frequenceCoupure) {
        this.frequenceCoupure = frequenceCoupure;
    }

    /**
     * @return the ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * @param ordre the ordre to set
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
}
