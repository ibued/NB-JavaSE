
package proyek.rpl.dialog;

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.daoImplement.db.barang.barangImplement;
import proyek.rpl.data.dataKategori;
import proyek.rpl.dataModel.modelKategori;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.frame.frameIndex;

/**
 *
 * @author ibued
 */
public class dialogIDkategori extends javax.swing.JDialog {

    private dbKoneksi koneksi;
    private modelKategori kategoriM; 
    private final frameIndex iIndex;
    private tabelIDkat tabDkat;
    
    private final barangImplement implement = new barangImplement();
    
    int row;
   
    public dialogIDkategori(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iIndex = (frameIndex) parent;
        koneksi();
        tampilKategori();
        tabelKategori();    
        segarkan();
        memilihTabelKategori();
        lebarTabel();              
    }
    
    private class tabelIDkat { // MEMBUAT CLASS
        
        private void cariIDkategori(String idKat) {
            int no = 1;
            try {
                String cariNamaKat = ("SELECT * FROM IBUED.KATEGORI WHERE NAMA_KATEGORI LIKE '%"+idKat+"%' ");
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cariNamaKat);
                ResultSet rs = ps.executeQuery();
                newFilter();
                while(rs.next()) {
                    dataKategori kategori = new dataKategori();
                    kategori.setNomorKategori(no++);
                    kategori.setIDkategori(rs.getString(1));
                    kategori.setNamaKategori(rs.getString(2));
                    kategoriM.insert(kategori);
                }                
            } catch (SQLException e) { }
            if (txCariNamaKat.getText().length()==0){
                jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID KATEGORI");
            } else if (tabelCariKategori.getRowCount()>0) {
                int dataTemukan = tabelCariKategori.getRowCount();
                jLabel2.setText("INFO : DITEMUKAN "+dataTemukan+" DATA");            
            } else {
                jLabel2.setText("INFO : DATA TIDAK DITEMUKAN");
            }
        }
        
        private void mouseClicked(MouseEvent e) {
            String txIDkat = txKomponenIDkat.getText();
            String idKat = null; 
            if (e.getClickCount()==2) {
                try {
                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_KATEGORI FROM IBUED.KATEGORI WHERE ID_KATEGORI='"+txIDkat+"'");
                    while(rs.next()) {
                        idKat = rs.getString("ID_KATEGORI");
                    }
                } catch (SQLException ex) { }                    
                    if (iIndex.idKatDialog==1) {
                        iIndex.txIDkategori.setText(txIDkat);
                    }                
            }
        }
        
    } // END CLASS
    
    private class memilihIDkategori implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int n=lsm.getMinSelectionIndex();    
            
            txKomponenIDkat.setText(modelKategori.fieldKategori.get(n).getIDkategori());            
        } 
    }
    private void memilihTabelKategori() {
        ListSelectionModel lsm = tabelCariKategori.getSelectionModel();
        lsm.addListSelectionListener(new memilihIDkategori());       
    }
    
    private void tampilKategori() throws SQLException {               
        try {
            implement.tampilIDkat();
        } catch (SQLException e) {
        }               
    }
    
    private void segarkan() throws SQLException {
        row = tabelCariKategori.getRowCount();
        for (int i = 0; i < row; i++) {
            kategoriM.delete(0, row);
        }
        implement.tampilIDkat(); 
    }
    
    private void newFilter() {
        row = tabelCariKategori.getRowCount();
        for (int i = 0; i < row; i++) {
            kategoriM.delete(0, row);
        }
    }
    
    private void tabelKategori() {
        kategoriM = new modelKategori();
        tabelCariKategori.setModel(kategoriM);      
    }
    private void lebarTabel() {
        tabelCariKategori.setRowHeight(27);
        tabelCariKategori.getColumnModel().getColumn(0).setPreferredWidth(40);        
        tabelCariKategori.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabelCariKategori.getColumnModel().getColumn(2).setPreferredWidth(300);
        jScrollPane1.getViewport().setOpaque(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txKomponenIDkat = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelCariKategori = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txCariNamaKat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ID KATEGORI");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        tabelCariKategori.setBackground(new java.awt.Color(51, 102, 0));
        tabelCariKategori.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelCariKategori.setForeground(new java.awt.Color(204, 204, 204));
        tabelCariKategori.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelCariKategori.setSelectionBackground(new java.awt.Color(51, 51, 51));
        tabelCariKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelCariKategoriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelCariKategori);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("CARI NAMA KATEGORI");

        txCariNamaKat.setBackground(new java.awt.Color(16, 29, 51));
        txCariNamaKat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txCariNamaKat.setForeground(new java.awt.Color(204, 204, 204));
        txCariNamaKat.setCaretColor(new java.awt.Color(204, 204, 204));
        txCariNamaKat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCariNamaKatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariNamaKatKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("INFO : DOBEL KLIK PADA TABEL UNTUK MENDAPATKAN ID KATEGORI");

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
                                .addComponent(txCariNamaKat, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(txCariNamaKat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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

        setSize(new java.awt.Dimension(543, 403));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txCariNamaKatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaKatKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txCariNamaKatKeyTyped

    private void tabelCariKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelCariKategoriMouseClicked
        // TODO add your handling code here:      
        try {
            tabDkat = new tabelIDkat();
            tabDkat.mouseClicked(evt);
        } catch (Exception e) { } 
        
    }//GEN-LAST:event_tabelCariKategoriMouseClicked

    private void txCariNamaKatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariNamaKatKeyReleased
        // TODO add your handling code here:
        String idKat = txCariNamaKat.getText();
        try {
            tabDkat = new tabelIDkat();
            tabDkat.cariIDkategori(idKat);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txCariNamaKatKeyReleased

    /**
     * @param args the command line arguments
     */    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabelCariKategori;
    private javax.swing.JTextField txCariNamaKat;
    private javax.swing.JTextField txKomponenIDkat;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }
    
}
