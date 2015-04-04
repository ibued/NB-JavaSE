
package proyek.rpl.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.data.dataAdmin;
import proyek.rpl.dataModel.modelDataAdmin;
import proyek.rpl.database.dbAdmin;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public final class dialogPengaturanAdmin extends javax.swing.JDialog {
    
    private dbKoneksi koneksi;  
    private dbAdmin adminDB;
    private cekIDAdmin iDAdmin; // membuat class didalam paket
 
    private int row;     

    public dialogPengaturanAdmin(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        koneksi();
        initListener();
        tabelAdmin();
        lebarKolomTabel();  
        pilihTabelKasir();
        tampilDataAdmin();
    } 
    
    private class cekIDAdmin { // MEMBUAT CLASS DIDALAM PAKET
        String idCekAdmin = txIDadmin.getText();
        String cek = null;
        private boolean cekID() {
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_ADMIN FROM IBUED.ADMIN WHERE ID_ADMIN='"+idCekAdmin+"'");
                while(rs.next()) {
                    cek = rs.getString("ID_ADMIN");
                    return true;
                }
            } catch (SQLException e) { }
            return false;
        }
        
        private void randomIdAdmin() { 
            int num = 1;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.ADMIN");
                while(rs.next()) {
                    num++;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            txIDadmin.setText("ADMIN"+"-"+num);     
        }
        
    } // END CLASS
    
    private class selectTabelKasir implements ListSelectionListener { // MEMBUAT CLASS DI DALAM PAKET

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                txIDadmin.setEditable(false);
                btRandomIDadmin.setEnabled(false);
                btSimpanDataAdmin.setEnabled(false);                     
                
                txCekRecord.setText(modelDataAdmin.fieldAdmin.get(n).getIDadmin());   
                txIDadmin.setText(modelDataAdmin.fieldAdmin.get(n).getIDadmin());
                txUsernameAdmin.setText(modelDataAdmin.fieldAdmin.get(n).getUsernameAdmin());
                txPasswordAdmin.setText(modelDataAdmin.fieldAdmin.get(n).getPasswdAdmin());
                txKonfirmPasswordAdmin.setText(modelDataAdmin.fieldAdmin.get(n).getPasswdAdmin());
            }
        }   
        
    } // END CLASS    
    private void pilihTabelKasir() {
        ListSelectionModel lsm = tabelAdmin.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelKasir());
    }  
    
    private void initListener() {     
        
        // CEK BUTTON RANDOM ID
        btRandomIDadmin.addActionListener((ActionEvent e) -> {
            iDAdmin = new cekIDAdmin();
            iDAdmin.randomIdAdmin();
        });
        
        btBatalAdmin.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        // CEK SIMPAN DATA KASIR FRAME ADMIN
        btSimpanDataAdmin.addActionListener((ActionEvent e) -> {
            validasiSimpanAdmin();
        });
        
        // CEK EDIT DATA KASIR FRAME ADMIN
        btEditDataAdmin.addActionListener((ActionEvent e) -> {        
            validasiEditDataAdmin();
            refreshAdmin(); 
//            segarkan();            
        });       
        
        // CEK HAPUS DATA ADMIN
        btHapusDataAdmin.addActionListener((ActionEvent e) -> {
            String hapus = txIDadmin.getText();
            
            if (tabelAdmin.getRowCount()==1) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b\b<b>PERINGATAN</b>\nDATA ADMIN TIDAK BOLEH KOSONG","WARNING",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (txCekRecord.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                        JOptionPane.WARNING_MESSAGE);
                } else {
                     int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Admin "+hapus+"?","Question", 
                            JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (konfirm == JOptionPane.YES_NO_OPTION) {
                        try {
                            adminDB.hapusDataAdmin(hapus);
                            refreshAdmin();
                            segarkan();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }     
                    }
                }
            }            
            
        });
        
        btSegarkanAdmin.addActionListener((ActionEvent e) -> {
            segarkan();
        });             
       
    }
    
    private boolean validasiSimpanAdmin() {
        iDAdmin = new cekIDAdmin();
        if (iDAdmin.cekID()) {
            JOptionPane.showMessageDialog(null, "ID Admin Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDadmin.requestFocus();
            return false;
        } else if (txIDadmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Admin Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDadmin.requestFocus();
            return false;
        } else if (txUsernameAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Username Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txUsernameAdmin.requestFocus();
            return false;
        } else if (txPasswordAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txPasswordAdmin.requestFocus();
        } else if (txKonfirmPasswordAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Konfirmasi Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txKonfirmPasswordAdmin.requestFocus();
        } else if (txKonfirmPasswordAdmin.getText().equals(txPasswordAdmin.getText())) {
            try {                
                    adminData.setIDadmin(txIDadmin.getText());
                    adminData.setUsernameAdmin(txUsernameAdmin.getText());
                    adminData.setPasswdAdmin(txPasswordAdmin.getText());
                    
                    if (adminDB.tambahDataAdmin(adminData)) {
                        adminModel.insert(adminData);
                        refreshAdmin();
                    }                        

                } catch (Exception ex) { }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Password Tidak Valid","WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }
        return true;
    }
    
    private boolean validasiEditDataAdmin() {        
        String idAdmin = txIDadmin.getText();
        if (txCekRecord.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txUsernameAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Username Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txUsernameAdmin.requestFocus();
            return false;
        } else if (txPasswordAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txPasswordAdmin.requestFocus();
            return false;
        } else if (txKonfirmPasswordAdmin.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Mohon Konfirmasi Password Dulu \nUntuk Memastikan Apakah Password Benar-Benar Valid atau Tidak ","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txKonfirmPasswordAdmin.requestFocus();
            return false;
        } else if (txKonfirmPasswordAdmin.getText().equals(txPasswordAdmin.getText())) {
            try {       
              
                adminData.setUsernameAdmin(txUsernameAdmin.getText());              
                adminData.setPasswdAdmin(txPasswordAdmin.getText());
                
                adminDB.editDataAdmin(adminData, idAdmin);
                
            } catch (Exception e) { }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Password Tidak Valid","WARNING",
                    JOptionPane.WARNING_MESSAGE);          
            return false;
        }
        return true;
    }      
    
    private void segarkan() {
        txCekRecord.setText(null);
        txIDadmin.setText(null);
        txUsernameAdmin.setText(null);
        txPasswordAdmin.setText(null);
        txKonfirmPasswordAdmin.setText(null);
        txIDadmin.setEditable(true);
        btRandomIDadmin.setEnabled(true);
        btSimpanDataAdmin.setEnabled(true);        
        try {
            row = tabelAdmin.getRowCount();
            for (int i = 0; i < row; i++) {
                adminModel.delete(0, row);
            }
            adminDB = new dbAdmin();
            adminDB.tampilDataAdmin();
        } catch (Exception e) {
        }
    }

    private void refreshAdmin() {
//        txCekRecord.setText(null);
//        txIDadmin.setText(null);
//        txUsernameAdmin.setText(null);
//        txPasswordAdmin.setText(null);
//        txKonfirmPasswordAdmin.setText(null);
//        txIDadmin.setEditable(true);
//        btSimpanDataAdmin.setEnabled(true);
        try {
            row = tabelAdmin.getRowCount();
            for (int i = 0; i < row; i++) {
                adminModel.delete(0, row);
            }
            adminDB = new dbAdmin();
            adminDB.tampilDataAdmin();
        } catch (Exception e) {
        }
    }
    
    private void tampilDataAdmin() {
        row = tabelAdmin.getRowCount();
        for (int i = 0; i < row; i++) {
            adminModel.delete(0, row);
        }
        try {
            adminDB = new dbAdmin();
            adminDB.tampilDataAdmin();
        } catch (Exception e) {
        }
        jScrollPane1.getViewport().setOpaque(false);
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txCekRecord = new javax.swing.JTextField();
        lbTampilBerdasarkanIDkasir = new javax.swing.JLabel();
        lbBerdasarkanIDkas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txIDadmin = new javax.swing.JTextField();
        txUsernameAdmin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txPasswordAdmin = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txKonfirmPasswordAdmin = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelAdmin = new javax.swing.JTable();
        btEditDataAdmin = new javax.swing.JButton();
        btHapusDataAdmin = new javax.swing.JButton();
        btSegarkanAdmin = new javax.swing.JButton();
        btSimpanDataAdmin = new javax.swing.JButton();
        btBatalAdmin = new javax.swing.JButton();
        btRandomIDadmin = new javax.swing.JButton();

        lbBerdasarkanIDkas.setForeground(new java.awt.Color(204, 204, 204));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PENGATURAN KASIR");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("ID KASIR");

        txIDadmin.setBackground(new java.awt.Color(16, 29, 51));
        txIDadmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDadmin.setForeground(new java.awt.Color(204, 204, 204));
        txIDadmin.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDadmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txIDadminKeyTyped(evt);
            }
        });

        txUsernameAdmin.setBackground(new java.awt.Color(16, 29, 51));
        txUsernameAdmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txUsernameAdmin.setForeground(new java.awt.Color(204, 204, 204));
        txUsernameAdmin.setCaretColor(new java.awt.Color(204, 204, 204));
        txUsernameAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txUsernameAdminKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("USERNAME");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txPasswordAdmin.setBackground(new java.awt.Color(16, 29, 51));
        txPasswordAdmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txPasswordAdmin.setForeground(new java.awt.Color(204, 204, 204));
        txPasswordAdmin.setCaretColor(new java.awt.Color(204, 204, 204));
        txPasswordAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPasswordAdminKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("PASSWORD");

        txKonfirmPasswordAdmin.setBackground(new java.awt.Color(16, 29, 51));
        txKonfirmPasswordAdmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txKonfirmPasswordAdmin.setForeground(new java.awt.Color(204, 204, 204));
        txKonfirmPasswordAdmin.setCaretColor(new java.awt.Color(204, 204, 204));
        txKonfirmPasswordAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txKonfirmPasswordAdminKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("KONFIRMASI PASSWORD");

        tabelAdmin.setBackground(new java.awt.Color(51, 102, 0));
        tabelAdmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelAdmin.setForeground(new java.awt.Color(204, 204, 204));
        tabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelAdmin.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(tabelAdmin);

        btEditDataAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btEditDataAdmin.setText("EDIT DATA");
        btEditDataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusDataAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusDataAdmin.setText("HAPUS DATA");
        btHapusDataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSegarkanAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSegarkanAdmin.setText("SEGARKAN");
        btSegarkanAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSimpanDataAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpanDataAdmin.setText("SIMPAN DATA");
        btSimpanDataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btBatalAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btBatalAdmin.setText("BATAL");
        btBatalAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btRandomIDadmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRandomIDadmin.setText("RANDOM ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txIDadmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRandomIDadmin))
                    .addComponent(txKonfirmPasswordAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(txPasswordAdmin)
                    .addComponent(txUsernameAdmin)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btSimpanDataAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBatalAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btEditDataAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapusDataAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSegarkanAdmin)
                        .addGap(0, 308, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txIDadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRandomIDadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txUsernameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txPasswordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txKonfirmPasswordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpanDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBatalAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapusDataAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSegarkanAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1044, 308));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txIDadminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDadminKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txIDadminKeyTyped

    private void txUsernameAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txUsernameAdminKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txUsernameAdminKeyTyped

    private void txPasswordAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPasswordAdminKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txPasswordAdminKeyTyped

    private void txKonfirmPasswordAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txKonfirmPasswordAdminKeyTyped
        // TODO add your handling code here:
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_txKonfirmPasswordAdminKeyTyped

    private modelDataAdmin adminModel = new modelDataAdmin();
    private final dataAdmin adminData = new dataAdmin();
    
    /**
     * @param args the command line arguments
     * 
     */   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBatalAdmin;
    public javax.swing.JButton btEditDataAdmin;
    public javax.swing.JButton btHapusDataAdmin;
    private javax.swing.JButton btRandomIDadmin;
    public javax.swing.JButton btSegarkanAdmin;
    public javax.swing.JButton btSimpanDataAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lbBerdasarkanIDkas;
    public javax.swing.JLabel lbTampilBerdasarkanIDkasir;
    private javax.swing.JTable tabelAdmin;
    private javax.swing.JTextField txCekRecord;
    public javax.swing.JTextField txIDadmin;
    public javax.swing.JPasswordField txKonfirmPasswordAdmin;
    public javax.swing.JPasswordField txPasswordAdmin;
    public javax.swing.JTextField txUsernameAdmin;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }

    private void tabelAdmin() {
        adminModel = new modelDataAdmin();
        tabelAdmin.setModel(adminModel);
    }
    
    private void lebarKolomTabel() {        
        tabelAdmin.setRowHeight(27);
        tabelAdmin.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelAdmin.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelAdmin.getColumnModel().getColumn(2).setPreferredWidth(260);
        tabelAdmin.getColumnModel().getColumn(3).setPreferredWidth(200);        
        tabelAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
}
