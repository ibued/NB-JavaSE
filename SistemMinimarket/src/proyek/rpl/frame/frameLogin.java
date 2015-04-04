

package proyek.rpl.frame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.dialog.dialogHelp;

/**
 * 
 * @author ibued
 */
public class frameLogin extends javax.swing.JFrame {
    
    private dbKoneksi kon;
    private dbLoginL loginL1;
    private dbCekUsernamePassword cekUserNamePasswd;  
    
    public String namaKasir;
    public String idKasTampil;
    public String id;
    
    public frameLogin() throws Exception{
        initComponents();
        initListener();
        koneksi();   
        cekDataKasir();
    }   
    
    // MEMBUAT CLASS UNTUK LOGIN
    public class dbLoginL {
        
        private boolean login(String username, String password) {
            String akses = txAkses.getText();
            boolean cek = false;     
            
            switch(akses) {
                case "ADMIN" :
                    try {
                        ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.ADMIN "
                        + "WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"' ");  
            
                        while(resultSet.next()) {
                            username  = resultSet.getString("USERNAME");
                            password  = resultSet.getString("PASSWORD");
                            cek = true;
                        }
                    } catch (SQLException e) { }
                    if (cek==false) {
                        JOptionPane.showMessageDialog(null, "Login Gagal","INFO",
                                JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    } else if (cek==true) {
                        JOptionPane.showMessageDialog(null, "Login Berhasil","INFO",
                                JOptionPane.INFORMATION_MESSAGE);                
                        formLogAdmin();
                        return true;                     
                    }
                break;
                    
                case "KASIR" :
                    try {
                        ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR "
                        + "WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"' ");  

                        while(resultSet.next()) {
                            id = resultSet.getString("ID_KASIR");
                            username  = resultSet.getString("USERNAME");
                            password    = resultSet.getString("PASSWORD");
                            idKasTampil = resultSet.getString("ID_KASIR");
                            namaKasir = resultSet.getString("NAMA_KASIR");
                            cek = true;
                        }
                        } catch (SQLException e) { }
                        if (cek==false) {
                            JOptionPane.showMessageDialog(null, "Login Gagal","INFO",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        } else if (cek==true) {
                            JOptionPane.showMessageDialog(null, "Login Berhasil","INFO",
                                    JOptionPane.INFORMATION_MESSAGE);                
                            formLogKasir();
                            return true;                     
                        }                        
                    break;
                    default:
                        JOptionPane.showMessageDialog(null, "AKSES SALAH","INFO",
                                JOptionPane.INFORMATION_MESSAGE);
                        txAkses.requestFocus();
                        break;
                        
            }        
            return cek;
        }      
        
        private void cekRecordKasir() {
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR");
                if (resultSet.next()) {
                    
                } else {
                    lbInfoKasir.setText("INFO : TIDAK ADA DATA KASIR, MOHON TAMBAHKAN DULU OLEH ADMIN !!!");
//                    lbInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/warning.png")));    
                }
            } catch (SQLException e) {
            }
        }
    }
    
    // MEMBUAT CLASS UNTUK MENGECEK USERNAME & PASSWORD
    private class dbCekUsernamePassword {
        
        boolean cek = false;          
        private void tampilUsernamePassword() {
            String akses = txAkses.getText();
            String user = txUsername.getText();
            String pass = new String(txPasswd.getPassword());
            String tampilUser  = null;
            String tampilPss  = null;
            
            switch(akses) {
                
                case "ADMIN" :
                    try {                
                        ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT USERNAME,PASSWORD FROM IBUED.ADMIN "
                                + "WHERE USERNAME='"+user+"'");
                        while(resultSet.next()) {
                            tampilUser  = resultSet.getString("USERNAME");
                            tampilPss   = resultSet.getString("PASSWORD");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    
                    if ("ADMIN".equals(txAkses.getText())) {
                        lbCekAkses.setVisible(true);
                        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));               
                        cek=true;
                    } else {
                        lbCekAkses.setVisible(true);
                        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }
                    if (user.equals(tampilUser)) {
                        lbCekUser.setVisible(true);
                        lbCekUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));               
                        cek=true;
                    }   else {
                        lbCekUser.setVisible(true);
                        lbCekUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }            
                    if (pass.equals(tampilPss)){                
                        lbCekPasswd.setVisible(true);
                        lbCekPasswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));
                        cek=true;
                    }   else {
                        lbCekPasswd.setVisible(true);
                        lbCekPasswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }
                    if (txAkses.getText().length()==0) {
                        lbCekAkses.setVisible(false);
                        cek=false;
                    } if (user.length()==0) {
                        lbCekUser.setVisible(false);
                        cek=false;
                    } if (pass.length()==0) {
                        lbCekPasswd.setVisible(false);
                        cek=false;
                    }
                    break;
                    
                case "KASIR" :
                    try {                
                        ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT USERNAME,PASSWORD FROM IBUED.KASIR "
                                + "WHERE USERNAME='"+user+"'  ");
                        while(resultSet.next()) {
                            tampilUser  = resultSet.getString("USERNAME");
                            tampilPss   = resultSet.getString("PASSWORD");
                        } 
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }       

                    if ("KASIR".equals(txAkses.getText())) {
                        lbCekAkses.setVisible(true);
                        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));               
                        cek=true;
                    } else {
                        lbCekAkses.setVisible(true);
                        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }
                    if (user.equals(tampilUser)) {
                        lbCekUser.setVisible(true);
                        lbCekUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));               
                        cek=true;
                    }   else {
                        lbCekUser.setVisible(true);
                        lbCekUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }            
                    if (pass.equals(tampilPss)){                
                        lbCekPasswd.setVisible(true);
                        lbCekPasswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png")));
                        cek=true;
                    }   else {
                        lbCekPasswd.setVisible(true);
                        lbCekPasswd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                    }
                    if (txAkses.getText().length()==0) {
                        lbCekAkses.setVisible(false);
                        cek=false;
                    } if (user.length()==0) {
                        lbCekUser.setVisible(false);
                        cek=false;
                    } if (pass.length()==0) {
                        lbCekPasswd.setVisible(false);
                        cek=false;
                    }
                    break;
                    
                    default:
                        lbCekAkses.setVisible(true);
                        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));
                        cek=true;
                        if (txAkses.getText().length()==0) {
                            lbCekAkses.setVisible(false);
                            cek=false;
                        }
                        break;                        
            }           
        }
    }
    
    private void initListener() {
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
        lbCekAkses.setVisible(false);
        
        // BUTTON KELUAR
        btKeluar.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        // BUTTON MASUK
        btMasuk.addActionListener((ActionEvent e) -> {
            String username = txUsername.getText();
            String passwd   = new String(txPasswd.getPassword());             
            try {              
                if (validasiLogin()) {
                   loginL1 = new dbLoginL();
                   loginL1.login(username, passwd);      
                }                         
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        });    
        
        // CEK AKSES
        txAkses.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));                               
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txUsername.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cekUserNamePasswd = new dbCekUsernamePassword();
                cekUserNamePasswd.tampilUsernamePassword();
            }
        });
        
        // CEK USERNAME
        txUsername.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {               
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txPasswd.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {               
                cekUserNamePasswd = new dbCekUsernamePassword();
                cekUserNamePasswd.tampilUsernamePassword();
            }
        });
        
        // CEK PASSWORD
        txPasswd.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));                
            }

            @Override
            public void keyPressed(KeyEvent e) {               
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btMasuk.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cekUserNamePasswd = new dbCekUsernamePassword();
                cekUserNamePasswd.tampilUsernamePassword();
            }
        });    
        
        txPasswd.addActionListener((ActionEvent e) -> {
            buttonMasukEnter();
        });
        
    }
    
    private void buttonMasukEnter() {
        String username = txUsername.getText();
        String passwd   = new String(txPasswd.getPassword());             
        try {              
            if (validasiLogin()) {
                loginL1 = new dbLoginL();
                loginL1.login(username, passwd);      
            }                         
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validasiLogin() {
        if (txAkses.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Akses Login Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txAkses.requestFocus();
            return false;
        } else if (txUsername.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Username Masih Kosong !!!","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txUsername.requestFocus();
            return false;
        } else if (txPasswd.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Password Masih Kosong !!!","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txPasswd.requestFocus();
            return false;
        } 
        return true;
    }
    
    private void cekDataKasir() {
        loginL1 = new dbLoginL();
        loginL1.cekRecordKasir();
    }    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txUsername = new javax.swing.JTextField();
        txPasswd = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btMasuk = new javax.swing.JButton();
        btKeluar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lbCekUser = new javax.swing.JLabel();
        lbCekPasswd = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txAkses = new javax.swing.JTextField();
        lbInfoKasir = new javax.swing.JLabel();
        lbCekAkses = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuSetting = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("USERNAME");

        txUsername.setBackground(new java.awt.Color(16, 29, 51));
        txUsername.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txUsername.setForeground(new java.awt.Color(204, 204, 204));

        txPasswd.setBackground(new java.awt.Color(16, 29, 51));
        txPasswd.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txPasswd.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("PASSWORD");

        btMasuk.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/masuk.png"))); // NOI18N
        btMasuk.setText("MASUK");
        btMasuk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btKeluar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/keluar.png"))); // NOI18N
        btKeluar.setText("KELUAR");
        btKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("AKSES");

        txAkses.setBackground(new java.awt.Color(16, 29, 51));
        txAkses.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txAkses.setForeground(new java.awt.Color(204, 204, 204));

        lbInfoKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbInfoKasir.setForeground(new java.awt.Color(204, 204, 204));
        lbInfoKasir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbCekAkses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/benar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbInfoKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btMasuk)
                        .addGap(18, 18, 18)
                        .addComponent(btKeluar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txAkses, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txPasswd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(txUsername, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCekAkses)
                            .addComponent(lbCekUser)
                            .addComponent(lbCekPasswd))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbCekAkses, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCekUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lbCekPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(lbInfoKasir)
                .addGap(6, 6, 6))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM LOGIN MINIMARKET");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        menuFile.setText("File");
        jMenuBar1.add(menuFile);

        menuSetting.setText("Setting");
        jMenuBar1.add(menuSetting);

        menuHelp.setText("Help");
        menuHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHelpMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(446, 324));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHelpMouseClicked
        // TODO add your handling code here:
    new dialogHelp(this, true).setVisible(true);
    }//GEN-LAST:event_menuHelpMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new frameLogin().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(frameLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btKeluar;
    private javax.swing.JButton btMasuk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCekAkses;
    private javax.swing.JLabel lbCekPasswd;
    private javax.swing.JLabel lbCekUser;
    private javax.swing.JLabel lbInfoKasir;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuSetting;
    private javax.swing.JTextField txAkses;
    private javax.swing.JPasswordField txPasswd;
    private javax.swing.JTextField txUsername;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        kon = new dbKoneksi();
        kon.openKoneksi(); 
    }
    
    private void formLogAdmin() {
        try {
            frameIndex index = new frameIndex();
            index.lbStatusAdmin.setText("Admin : Aktif ["+txUsername.getText()+"] ");
            index.btAdminDataAdmin.setEnabled(true);
            index.btAdminDataKasir.setEnabled(true);
            index.lbStatusKasir.setText("Kasir : Tidak Aktif");
            index.jTabbedPane1.setEnabled(false);
            index.jMenuItem1.setVisible(false);
            index.jMenuItem2.setVisible(false);
//            index.lbInfoConstructor.setVisible(false);
//            index.name = txAkses.getText();
            index.setVisible(true);
            this.setVisible(false);
        } catch (Exception e) {
        }
    }
    
    private void formLogKasir() {
        try {   
        frameIndex utama = new frameIndex();
        utama.idKasTampilAdmin = idKasTampil;
        utama.id = id;
        utama.name = namaKasir;
        utama.user = txUsername.getText();
        utama.password = txPasswd.getText();
        utama.lbStatusAdmin.setText("Admin : Tidak Aktif");
        utama.lbStatusKasir.setText("Kasir : Aktif");
        utama.btAdminDataKasir.setEnabled(false);
        utama.btAdminDataAdmin.setEnabled(false);
        utama.jTabbedPane1.setEnabled(true);
        utama.jMenuItem1.setEnabled(true);
        utama.jMenuItem2.setEnabled(true);
        utama.jMenuItem3.setEnabled(true);
        utama.jMenuItem4.setEnabled(true);
        utama.jMenuItem5.setEnabled(true);
        utama.jMenuItem6.setEnabled(true);
//        utama.lbInfoConstructor.setVisible(false);
        utama.setVisible(true);
        this.setVisible(false);
        } catch (Exception e) {
        }        
    }
   
}
