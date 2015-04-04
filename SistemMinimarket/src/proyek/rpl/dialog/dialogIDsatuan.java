

package proyek.rpl.dialog;

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.daoImplement.db.barang.barangImplement;
import proyek.rpl.data.dataSatuan;
import proyek.rpl.dataModel.modelSatuan;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.frame.frameIndex;

/**
 *
 * @author ibued
 */
public class dialogIDsatuan extends javax.swing.JDialog {

    private dbKoneksi koneksi;
    private modelSatuan satuanM;
    private final frameIndex iIndex;
    private tabelIDsat dsat;
    
    private final barangImplement implement =new barangImplement();
    
    private int row;
    
    public dialogIDsatuan(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        koneksi();
        iIndex = (frameIndex) parent;
        tampilSatuan();
        tabelSatuan();
        memilihTabelSatuan();
        lebarTabel();
        segarkan();
        jScrollPane1.getViewport().setOpaque(false);
    }
    
    private class tabelIDsat { // MEMBUAT CLASS
        
        private void cariIDsatuan(String idSat) {
            int no = 1;
            try {
                String cariNamaSat = ("SELECT * FROM IBUED.SATUAN WHERE NAMA_SATUAN LIKE '%"+idSat+"%' ");
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cariNamaSat);
                ResultSet rs = ps.executeQuery();
                newFilter();
                while(rs.next()) {
                    dataSatuan satuan = new dataSatuan();
                    satuan.setNomorSatuan(no++);
                    satuan.setIDsatuan(rs.getString(1));
                    satuan.setNamaSatuan(rs.getString(2));
                    satuanM.insert(satuan);
                }                
            } catch (SQLException e) { }
            if (txCariNamaSat.getText().length()==0){
                jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID SATUAN");
            } else if (tabelCariSatuan.getRowCount()>0) {
                int dataTemukan = tabelCariSatuan.getRowCount();
                jLabel2.setText("INFO : DITEMUKAN "+dataTemukan+" DATA");            
            } else {
                jLabel2.setText("INFO : DATA TIDAK DITEMUKAN");
            }
        }
        
        private void mouseClicked(MouseEvent e) {
            String txIDsat = txKomponenIDsat.getText();
            String idSat = null;
            if (e.getClickCount()==2) {
                try {
                    implement.mouseClickedSatuan(txIDsat);                  
                }catch (SQLException ex) {
                    Logger.getLogger(dialogIDsatuan.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (iIndex.idSatDialog==2) {
                    iIndex.txIDsatuan.setText(txIDsat);
                }
            }               
        }
        
    } // END CLASS
    
    private class memilihIDsatuan implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int n=lsm.getMinSelectionIndex();    
            
            txKomponenIDsat.setText(modelSatuan.fieldSatuan.get(n).getIDSatuan());            
        } 
    }
    private void memilihTabelSatuan() {
        ListSelectionModel lsm = tabelCariSatuan.getSelectionModel();
        lsm.addListSelectionListener(new memilihIDsatuan());       
    } 
    
    private void tabelSatuan() {
        satuanM = new modelSatuan();
        tabelCariSatuan.setModel(satuanM);
    }
    private void lebarTabel() {
        tabelCariSatuan.setRowHeight(27);
        tabelCariSatuan.getColumnModel().getColumn(0).setPreferredWidth(40);        
        tabelCariSatuan.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelCariSatuan.getColumnModel().getColumn(2).setPreferredWidth(300);
    }

    private void tampilSatuan() {
        try {
            implement.tampilIDsat();
        } catch (SQLException e) {
        }
    }
    
    private void segarkan() throws SQLException {
        row = tabelCariSatuan.getRowCount();
        for (int i = 0; i < row; i++) {
            satuanM.delete(0, row);
        }
        implement.tampilIDsat();
    }
    
    private void newFilter() {
        row = tabelCariSatuan.getRowCount();
        for (int i = 0; i < row; i++) {
            satuanM.delete(0, row);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txKomponenIDsat = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCariSatuan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txCariNamaSat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ID SATUAN");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tabelCariSatuan.setBackground(new java.awt.Color(51, 102, 0));
        tabelCariSatuan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelCariSatuan.setForeground(new java.awt.Color(204, 204, 204));
        tabelCariSatuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelCariSatuan.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tabelCariSatuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelCariSatuanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelCariSatuan);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("CARI NAMA SATUAN");

        txCariNamaSat.setBackground(new java.awt.Color(16, 29, 51));
        txCariNamaSat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txCariNamaSat.setForeground(new java.awt.Color(204, 204, 204));
        txCariNamaSat.setCaretColor(new java.awt.Color(204, 204, 204));
        txCariNamaSat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCariNamaSatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariNamaSatKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID SATUAN");

        jButton1.setText("CARI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCariNamaSat, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txCariNamaSat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(533, 402));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txCariNamaSatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaSatKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txCariNamaSatKeyTyped

    private void tabelCariSatuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelCariSatuanMouseClicked
        // TODO add your handling code here:
        try {
            dsat = new tabelIDsat();
            dsat.mouseClicked(evt);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tabelCariSatuanMouseClicked

    private void txCariNamaSatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaSatKeyReleased
        // TODO add your handling code here:
        String idSat = txCariNamaSat.getText();        
        try {
            dsat = new tabelIDsat();
            dsat.cariIDsatuan(idSat);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txCariNamaSatKeyReleased

    /**
     * @param args the command line arguments
     */   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelCariSatuan;
    private javax.swing.JTextField txCariNamaSat;
    private javax.swing.JTextField txKomponenIDsat;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }
}
