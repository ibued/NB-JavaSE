

package proyek.rpl.palette.komponen;

import javax.swing.JButton;

/**
 *
 * @author ibued
 */
public class buttonTool extends javax.swing.JPanel {

   
    public buttonTool() {
        initComponents();
    }
    
    public JButton getEditData() {
        return btEditData;
    }
    
    public JButton getHapusData() {
        return btHapusData;
    }
    
    public JButton getSegarkan() {
        return btSegarkan;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btEditData = new javax.swing.JButton();
        btHapusData = new javax.swing.JButton();
        btSegarkan = new javax.swing.JButton();

        btEditData.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btEditData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/edit.png"))); // NOI18N
        btEditData.setText("EDIT DATA");
        btEditData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusData.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapusData.png"))); // NOI18N
        btHapusData.setText("HAPUS DATA");
        btHapusData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSegarkan.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSegarkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/segarkan.png"))); // NOI18N
        btSegarkan.setText("SEGARKAN [F5]");
        btSegarkan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btEditData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btHapusData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSegarkan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEditData, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHapusData, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditData;
    private javax.swing.JButton btHapusData;
    private javax.swing.JButton btSegarkan;
    // End of variables declaration//GEN-END:variables
}
