/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isilimageprocessing.Dialogues;

import CImage.CImage;
import CImage.Observers.JLabelCImage;

/**
 *
 * @author Pc
 */
public class JDialogShowImage extends javax.swing.JDialog
{
  JLabelCImage labelImage;
  
  /**
   * Creates new form ShowImageDialog
   */
  public JDialogShowImage(java.awt.Frame parent, boolean modal, CImage image)
  {
    super(parent, modal);
    initComponents();
    
    labelImage = new JLabelCImage();
    labelImage.setCImage(image);
    scroll.setViewportView(labelImage);
    pack();
    setLocationRelativeTo(parent);
  }
  
  public void setCImage(CImage image)
  {
    labelImage.setCImage(image);
  }

  
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(scroll);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
