
package proyek.rpl.dialog;

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.daoImplement.db.barang.transaksiPenjualanImplement;
import proyek.rpl.data.dataGetPelanggan;
import proyek.rpl.dataModel.modelGetPelanggan;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.frame.frameIndex;

/**
 *
 * @author ibued
 */
public class dialogIDpelanggan extends javax.swing.JDialog {

    private dbKoneksi koneksi;
    private modelGetPelanggan pelangganM;
    private final frameIndex iIndex;
    private tabelIDplg dplg;
    
    private final transaksiPenjualanImplement penjualanImplement = new transaksiPenjualanImplement();
    
    int row;
   
    public dialogIDpelanggan(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iIndex = (frameIndex) parent;
        koneksi();
        tampilPelanggan();
        tabelPelanggan();
        segarkan();
        memilihTabelPlg();
        lebarTabel();  
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
    }
    
    private class tabelIDplg { // MEMBUAT CLASS
        
        private void cariIDpelanggan(String namaPlg) {
            int no = 1;
            try {
                String cariNamaPlg = ("SELECT * FROM IBUED.PELANGGAN WHERE NAMA_PELANGGAN LIKE '%"+namaPlg+"%' ");
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cariNamaPlg);
                ResultSet rs = ps.executeQuery();
                newFilter();
                while(rs.next()) {
                    dataGetPelanggan getPelanggan = new dataGetPelanggan();
                    getPelanggan.setNomorPelanggan(no++);
                    getPelanggan.setIDpelanggan(rs.getString(1));
                    getPelanggan.setNamaPelanggan(rs.getString(2));
                    pelangganM.insert(getPelanggan);
                }                
            } catch (SQLException e) { }
            if (txCariNamaPlg.getText().length()==0){
                jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID PELANGGAN");
            } else if (tabelCariPelanggan.getRowCount()>0) {
                int dataTemukan = tabelCariPelanggan.getRowCount();
                jLabel2.setText("INFO : DITEMUKAN "+dataTemukan+" DATA");            
            } else {
                jLabel2.setText("INFO : DATA TIDAK DITEMUKAN");
            }
        }
        
        private void mouseClicked(MouseEvent e) {
            String txIDplg = txKomponenIDplg.getText();
            String idPlg = null; 
            if (e.getClickCount()==2) {
                try {
                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_PELANGGAN FROM IBUED.PELANGGAN WHERE ID_PELANGGAN='"+txIDplg+"'");
                    while(rs.next()) {
                        idPlg = rs.getString("ID_PELANGGAN");
                    }
                } catch (SQLException ex) { }                    
                    if (iIndex.idPlgJual==1) {
                        iIndex.txIDplgJual.setText(txIDplg);
                    }                
            }
        }
        
    } // END CLASS
    
    private class memilihIDpelanggan implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int n=lsm.getMinSelectionIndex();    
            
            txKomponenIDplg.setText(modelGetPelanggan.fieldPlg.get(n).getIDpelanggan());            
        } 
    }
    private void memilihTabelPlg() {
        ListSelectionModel lsm = tabelCariPelanggan.getSelectionModel();
        lsm.addListSelectionListener(new memilihIDpelanggan());       
    }
    
    private void tampilPelanggan() {               
        try {
            penjualanImplement.tampilIDpelanggan();
        } catch (Exception e) {
        }               
    }
    
    private void segarkan() {
        row = tabelCariPelanggan.getRowCount();
        for (int i = 0; i < row; i++) {
            pelangganM.delete(0, row);
        }
        penjualanImplement.tampilIDpelanggan();
    }
    
    private void newFilter() {
        row = tabelCariPelanggan.getRowCount();
        for (int i = 0; i < row; i++) {
            pelangganM.delete(0, row);
        }
    }
    
    private void tabelPelanggan() {
        pelangganM = new modelGetPelanggan();
        tabelCariPelanggan.setModel(pelangganM);      
    }
    private void lebarTabel() {
        tabelCariPelanggan.setRowHeight(27);
        tabelCariPelanggan.getColumnModel().getColumn(0).setPreferredWidth(40);        
        tabelCariPelanggan.getColumnModel().getColumn(1).setPreferredWidth(190);
        tabelCariPelanggan.getColumnModel().getColumn(2).setPreferredWidth(300);
        tabelCariPelanggan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane1.getViewport().setOpaque(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txKomponenIDplg = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCariPelanggan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txCariNamaPlg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GET ID PELANGGAN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tabelCariPelanggan.setBackground(new java.awt.Color(51, 102, 0));
        tabelCariPelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelCariPelanggan.setForeground(new java.awt.Color(204, 204, 204));
        tabelCariPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelCariPelanggan.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tabelCariPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelCariPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelCariPelanggan);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("CARI NAMA PELANGGAN");

        txCariNamaPlg.setBackground(new java.awt.Color(16, 29, 51));
        txCariNamaPlg.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txCariNamaPlg.setForeground(new java.awt.Color(204, 204, 204));
        txCariNamaPlg.setCaretColor(new java.awt.Color(204, 204, 204));
        txCariNamaPlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCariNamaPlgKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariNamaPlgKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID PELANGGAN");

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
                                .addComponent(txCariNamaPlg, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(txCariNamaPlg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        setSize(new java.awt.Dimension(554, 402));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txCariNamaPlgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaPlgKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txCariNamaPlgKeyTyped

    private void tabelCariPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelCariPelangganMouseClicked
        // TODO add your handling code here:      
        try {
            dplg = new tabelIDplg();
            dplg.mouseClicked(evt);
        } catch (Exception e) { } 
        
    }//GEN-LAST:event_tabelCariPelangganMouseClicked

    private void txCariNamaPlgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaPlgKeyReleased
        // TODO add your handling code here:
        String idPlg = txCariNamaPlg.getText();
        try {
            dplg = new tabelIDplg();
            dplg.cariIDpelanggan(idPlg);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txCariNamaPlgKeyReleased

    /**
     * @param args the command line arguments
     */    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabelCariPelanggan;
    private javax.swing.JTextField txCariNamaPlg;
    private javax.swing.JTextField txKomponenIDplg;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }
    
}
