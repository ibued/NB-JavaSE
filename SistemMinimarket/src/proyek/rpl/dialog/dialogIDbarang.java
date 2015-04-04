
package proyek.rpl.dialog;

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.daoImplement.db.barang.transaksiPenjualanImplement;
import proyek.rpl.data.dataGetBarang;
import proyek.rpl.dataModel.modelGetBarang;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.frame.frameIndex;

/**
 *
 * @author ibued
 */
public class dialogIDbarang extends javax.swing.JDialog {

    private dbKoneksi koneksi;
    private modelGetBarang barangM;
    private final frameIndex iIndex;
    private tabelIDbrng dbrng;
    
    private final transaksiPenjualanImplement penjualanImplement = new transaksiPenjualanImplement();
    
    int row;
    DecimalFormat dfo = new DecimalFormat("#,##0");
   
    public dialogIDbarang(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iIndex = (frameIndex) parent;
        koneksi();
     
        tabelBarang();
        segarkan();
        memilihTabelBarang();
        lebarTabel();          
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
    }
    
    private class tabelIDbrng { // MEMBUAT CLASS
        
        private void cariIDbarang(String namaBarang) {
            int no = 1;
            try {
                String cariNamaBrng = ("SELECT * FROM IBUED.BARANG WHERE NAMA_BARANG LIKE '%"+namaBarang+"%' ");
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cariNamaBrng);
                ResultSet rs = ps.executeQuery();
                newFilter();
                while(rs.next()) {
                    dataGetBarang getBarang = new dataGetBarang();
                    getBarang.setNomorBarang(no++);
                    getBarang.setIDbarang(rs.getString(1));
                    getBarang.setNamaBarang(rs.getString(2));
                    getBarang.setHargaBarang(rs.getString("HARGA"));
                    getBarang.setDiskonBarang(rs.getString("DISKON"));
                    barangM.insert(getBarang);
                }                
            } catch (SQLException e) { }
            if (txCariNamaBrng.getText().length()==0){
                jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID BARANG");
            } else if (tabelCariBarang.getRowCount()>0) {
                int dataTemukan = tabelCariBarang.getRowCount();
                jLabel2.setText("INFO : DITEMUKAN "+dataTemukan+" DATA");            
            } else {
                jLabel2.setText("INFO : DATA TIDAK DITEMUKAN");
            }
        }
        
        private boolean mouseClicked(MouseEvent e) {
            
            String txIDbrng = txKomponenIDbrng.getText();
            String idBrng = null; 
            String harga = null;
            String diskon = null;
        
            if (e.getClickCount()==2) {
                try {
                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_BARANG,HARGA,DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+txIDbrng+"'");
                    while(rs.next()) {
                        idBrng = rs.getString("ID_BARANG");
                        harga = rs.getString("HARGA");
                        diskon = rs.getString("DISKON");
//                        return true;
                    }
                } catch (SQLException ex) { } 
                
                    // from Index
                if (iIndex.txIDbarangJual.getText().isEmpty()) {
                    if (iIndex.idBrngJual == 1) {
                        iIndex.txIDbarangJual.setText(idBrng);
                        iIndex.txHargaBarangJual.setText(harga); 
                        
                        if (diskon.equals("0")) {
                            iIndex.txDiskonBarangJual.setText(diskon);
                        } else {
                            iIndex.txDiskonBarangJual.setText(diskon+"%");                      
                        }            
                        setVisible(false);
                        return false;                        
                    }
                } if (iIndex.txIDbarangJual1.getText().isEmpty()) {
                    if (iIndex.idBrngJual1 == 2) {
                        iIndex.txIDbarangJual1.setText(idBrng);
                        iIndex.txHargaBarangJual1.setText(harga); 
                        if (diskon.equals("0")) {
                            iIndex.txDiskonBarangJual1.setText(diskon);
                        } else {
                            iIndex.txDiskonBarangJual1.setText(diskon+"%");                            
                        }
                        setVisible(false);
                        return false;
                    }                                       
                } if (iIndex.txIDbarangJual2.getText().isEmpty()) {
                    if (iIndex.idBrngJual2 == 3) {
                        iIndex.txIDbarangJual2.setText(idBrng);
                        iIndex.txHargaBarangJual2.setText(harga); 
//                        if (diskon.equals("0")) {
//                            iIndex.txDiskonBarangJual1.setText(diskon);
//                        } else {
//                            iIndex.txDiskonBarangJual1.setText(diskon+"%");                            
//                        }
                        setVisible(false);
                        return false;
                    }    
                } if (iIndex.txIDbarangJual3.getText().isEmpty()) {
                    if (iIndex.idBrngJual3 == 4) {
                        iIndex.txIDbarangJual3.setText(idBrng);
                        iIndex.txHargaBarangJual3.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                } if (iIndex.txIDbarangJual4.getText().isEmpty()) {
                    if (iIndex.idBrngJual4 == 5) {
                        iIndex.txIDbarangJual4.setText(idBrng);
                        iIndex.txHargaBarangJual4.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                } if (iIndex.txIDbarangJual5.getText().isEmpty()) {
                    if (iIndex.idBrngJual5 == 6) {
                        iIndex.txIDbarangJual5.setText(idBrng);
                        iIndex.txHargaBarangJual5.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                } if (iIndex.txIDbarangJual6.getText().isEmpty()) {
                    if (iIndex.idBrngJual6 == 7) {
                        iIndex.txIDbarangJual6.setText(idBrng);
                        iIndex.txHargaBarangJual6.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                } if (iIndex.txIDbarangJual7.getText().isEmpty()) {
                    if (iIndex.idBrngJual7 == 8) {
                        iIndex.txIDbarangJual7.setText(idBrng);
                        iIndex.txHargaBarangJual7.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                } if (iIndex.txIDbarangJual8.getText().isEmpty()) {
                    if (iIndex.idBrngJual8 == 9) {
                        iIndex.txIDbarangJual8.setText(idBrng);
                        iIndex.txHargaBarangJual8.setText(harga); 
                        setVisible(false);
                        return false;
                    }
                }
                
            }
            return true;
        }
        
    } // END CLASS
    
    private class memilihIDpelanggan implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int n=lsm.getMinSelectionIndex();    
            
            txKomponenIDbrng.setText(modelGetBarang.fieldBrng.get(n).getIDbarang());            
        } 
    }
    private void memilihTabelBarang() {
        ListSelectionModel lsm = tabelCariBarang.getSelectionModel();
        lsm.addListSelectionListener(new memilihIDpelanggan());       
    }
    
//    private void tampilKategori() {               
//        try {
//            penjualanImplement.tampilIDpelanggan();
//        } catch (Exception e) {
//        }               
//    }
//    
    private void segarkan() {
        row = tabelCariBarang.getRowCount();
        for (int i = 0; i < row; i++) {
            barangM.delete(0, row);
        }
        penjualanImplement.tampilIDbarang();
    }
    
    private void newFilter() {
        row = tabelCariBarang.getRowCount();
        for (int i = 0; i < row; i++) {
            barangM.delete(0, row);
        }
    }
    
    private void tabelBarang() {
        barangM = new modelGetBarang();
        tabelCariBarang.setModel(barangM);      
    }
    private void lebarTabel() {
        tabelCariBarang.setRowHeight(27);
        tabelCariBarang.getColumnModel().getColumn(0).setPreferredWidth(40);        
        tabelCariBarang.getColumnModel().getColumn(1).setPreferredWidth(90);
        tabelCariBarang.getColumnModel().getColumn(2).setPreferredWidth(220);
        tabelCariBarang.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabelCariBarang.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabelCariBarang.getColumnModel().getColumn(5).setPreferredWidth(60);
        tabelCariBarang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getViewport().setOpaque(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txKomponenIDbrng = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCariBarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txCariNamaBrng = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        txKomponenIDbrng.setText("AAAAAA");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GET ID BARANG");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tabelCariBarang.setBackground(new java.awt.Color(51, 102, 0));
        tabelCariBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelCariBarang.setForeground(new java.awt.Color(204, 204, 204));
        tabelCariBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelCariBarang.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tabelCariBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelCariBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelCariBarang);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("CARI NAMA BARANG");

        txCariNamaBrng.setBackground(new java.awt.Color(16, 29, 51));
        txCariNamaBrng.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txCariNamaBrng.setForeground(new java.awt.Color(204, 204, 204));
        txCariNamaBrng.setCaretColor(new java.awt.Color(204, 204, 204));
        txCariNamaBrng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCariNamaBrngKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariNamaBrngKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID BARANG");

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
                                .addComponent(txCariNamaBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel2))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txCariNamaBrng, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        setSize(new java.awt.Dimension(554, 402));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txCariNamaBrngKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaBrngKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txCariNamaBrngKeyTyped

    private void tabelCariBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelCariBarangMouseClicked
        // TODO add your handling code here:      
        try {
            dbrng = new tabelIDbrng();
            dbrng.mouseClicked(evt);
        } catch (Exception e) { } 
        
    }//GEN-LAST:event_tabelCariBarangMouseClicked

    private void txCariNamaBrngKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaBrngKeyReleased
        // TODO add your handling code here:
        String idBrng = txCariNamaBrng.getText();
        try {
            dbrng = new tabelIDbrng();
            dbrng.cariIDbarang(idBrng);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txCariNamaBrngKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (tabelCariBarang.getRowCount()==0) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabelCariBarang;
    private javax.swing.JTextField txCariNamaBrng;
    private javax.swing.JTextField txKomponenIDbrng;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }
    
}
