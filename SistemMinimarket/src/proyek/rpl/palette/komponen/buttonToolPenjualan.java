

package proyek.rpl.palette.komponen;

import javax.swing.JButton;

/**
 *
 * @author ibued
 */
public class buttonToolPenjualan extends javax.swing.JPanel {

   
    public buttonToolPenjualan() {
        initComponents();
    }
    
    public JButton getSimpanData() {
        return btSimpan;
    }
    
    public JButton getSegarkan() {
        return btSegarkan;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btSegarkan = new javax.swing.JButton();
        btSimpan = new javax.swing.JButton();

        btSegarkan.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSegarkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/segarkan.png"))); // NOI18N
        btSegarkan.setText("SEGARKAN [F5]");
        btSegarkan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSimpan.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/simpan.PNG"))); // NOI18N
        btSimpan.setText("SIMPAN");
        btSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSegarkan)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSegarkan;
    private javax.swing.JButton btSimpan;
    // End of variables declaration//GEN-END:variables
}
