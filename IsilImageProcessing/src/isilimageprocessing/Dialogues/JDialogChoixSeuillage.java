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
public class JDialogChoixSeuillage extends javax.swing.JDialog {
    private int seuil1;
    private int seuil2;
    private boolean sDouble = false;
    

    public JDialogChoixSeuillage(java.awt.Frame parent, boolean modal, boolean seuilDouble) {
        super(parent, modal);
        initComponents();
         sDouble=seuilDouble;
        if(sDouble ==false)
        { 
            this.JspinnerSeuil2.setVisible(false);
            this.jLabelseuil2.setVisible(false);
            
        }
            
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ButtonOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JspinnerSeuil1 = new javax.swing.JSpinner();
        jLabelseuil2 = new javax.swing.JLabel();
        JspinnerSeuil2 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ButtonOk.setText("Ok");
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        jLabel2.setText("Seuil 1 ");

        JspinnerSeuil1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 255, 1));

        jLabelseuil2.setText("Seuil 2");

        JspinnerSeuil2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 255, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JspinnerSeuil1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelseuil2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JspinnerSeuil2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspinnerSeuil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelseuil2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspinnerSeuil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ButtonOk))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public synchronized void addInputMethodListener(InputMethodListener il) {
        super.addInputMethodListener(il); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
  
          if(this.sDouble) 
            { this.seuil2=(Integer)this.JspinnerSeuil2.getValue();
                System.out.println("seuil 2 :"+seuil2);
            }
         
          this.seuil1=((Integer)this.JspinnerSeuil1.getValue()).intValue();
            System.out.println("seuil 1 :"+seuil1);
            this.dispose();
    }//GEN-LAST:event_ButtonOkActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonOk;
    private javax.swing.JSpinner JspinnerSeuil1;
    private javax.swing.JSpinner JspinnerSeuil2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelseuil2;
    // End of variables declaration//GEN-END:variables


    /**
     * @return the seuil1
     */
    public int getOrientation() {
        return getSeuil1();
    }

    

    /**
     * @return the seuil1
     */
    public int getSeuil1() {
        return seuil1;
    }

    /**
     * @return the seuil2
     */
    public int getSeuil2() {
        return seuil2;
    }
}
