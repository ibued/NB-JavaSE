
package proyek.rpl.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import proyek.rpl.data.dataKasir;
import proyek.rpl.dataModel.modelDataKasir;
import proyek.rpl.database.dbKasir;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public final class dialogPengaturanKasir extends javax.swing.JDialog {
    
    private dbKoneksi koneksi;     
    private kasir kas; // membuat class didalam paket
    
    // global
    public String username;
    public String idKasPengaturan;
    private int row;    
    private String lbTampilIDkas;

    public dialogPengaturanKasir(java.awt.Frame parent, boolean modal) throws SQLException{
        super(parent, modal);
        initComponents();
        koneksi();
        initListener();
        tabelKasir();
        lebarKolomTabel();  
        pilihTabelKasir();
        segarkan();       
    }   
    
    private class kasir { // MEMBUAT CLASS DI DALAM PAKET   
        String cekID = txIDkasir.getText();
        String tampilIDkas = lbBerdasarkanIDkas.getText();
        String idKasCek = null;
             
        private void formWindowTampil() {
        lbBerdasarkanIDkas.setText(idKasPengaturan);        
        String idKas = lbBerdasarkanIDkas.getText();
        try {
            int no = 1;       
            try (ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR WHERE ID_KASIR='"+idKas+"'")) {
                while(resultSet.next()) {
                    dataKasir kasir = new dataKasir();
                    kasir.setNomorKasir(no);
                    kasir.setIDKasir(resultSet.getString(1));
                    kasir.setNamaKasir(resultSet.getString(2));
                    kasir.setTelpKasir(resultSet.getString(3));
                    kasir.setUsernameKasir(resultSet.getString(4));
                    kasir.setPasswordKasir(resultSet.getString(5));
                    dataKasirM.insert(kasir);
                    
//                    txCekRecord.setText(resultSet.getString(1));         
//                    txIDkasir.setText(resultSet.getString(1));
//                    txIDkasir.setEditable(false);
//                    txNamaKasir.setText(resultSet.getString(2));
//                    txTelpKasir.setText(resultSet.getString(3));
//                    txUsername.setText(resultSet.getString(4));
//                    txPasswordKasir.setText(resultSet.getString(5));
//                    txKonfirmPasswordKasir.setText(resultSet.getString(5));
                }
            }
            dbKoneksi.conn.commit();
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }        
        }     
        
        private void textFieldKasir() {
        lbBerdasarkanIDkas.setText(idKasPengaturan);        
        String idKas = lbBerdasarkanIDkas.getText();
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR WHERE ID_KASIR='"+idKas+"'");
                while(resultSet.next()) {
                    txCekRecord.setText(resultSet.getString(1));         
                    txIDkasir.setText(resultSet.getString(1));
                    txIDkasir.setEditable(false);
                    txNamaKasir.setText(resultSet.getString(2));
                    txTelpKasir.setText(resultSet.getString(3));
                    txUsername.setText(resultSet.getString(4));
                    txPasswordKasir.setText(resultSet.getString(5));
                    txKonfirmPasswordKasir.setText(resultSet.getString(5));
                }
            } catch (SQLException e) {
            }
        }
        
        private void tampilBerdasarkanIDkasir() {            
            int no = 1; 
            row = tabelKasir.getRowCount();
            for (int i = 0; i < row; i++) {
                dataKasirM.delete(0, row);
            }
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR WHERE ID_KASIR='"+tampilIDkas+"'");
                while(resultSet.next()) {
                    dataKasir kasir = new dataKasir();
                    kasir.setNomorKasir(no);
                    kasir.setIDKasir(resultSet.getString(1));
                    kasir.setNamaKasir(resultSet.getString(2));
                    kasir.setTelpKasir(resultSet.getString(3));
                    kasir.setUsernameKasir(resultSet.getString(4));
                    kasir.setPasswordKasir(resultSet.getString(5));
                    dataKasirM.insert(kasir);
                    
                    lbTampilIDkas = resultSet.getString("ID_KASIR");
                }
            } catch (SQLException e) { }
        }
        
        // CEK ID KASIR
        private boolean cekIDkasir() {             
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_KASIR FROM IBUED.KASIR WHERE ID_KASIR='"+cekID+"' ");
                while(rs.next()) {
                    idKasCek = rs.getString("ID_KASIR");
                    return true;
                }
            } catch (SQLException e) { }
            return false;
        }
        
        private void randomIDkasir() {
            int no = 1;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR");
                while(rs.next()) {
                    no++;
                }
            } catch (SQLException e) { }
            txIDkasir.setText("KASIR"+"-"+no);
        }
        
    } // END CLASS
    
    private class selectTabelKasir implements ListSelectionListener { // MEMBUAT CLASS DI DALAM PAKET

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                btRandomIDkas.setEnabled(false);
                txIDkasir.setEditable(false);
                btSimpanDataKasir.setEnabled(false);                     
                
                txCekRecord.setText(modelDataKasir.fieldKasir.get(n).getIDkasir());   
                txIDkasir.setText(modelDataKasir.fieldKasir.get(n).getIDkasir());
                txNamaKasir.setText(modelDataKasir.fieldKasir.get(n).getNamakasir());
                txTelpKasir.setText(modelDataKasir.fieldKasir.get(n).getTelpKasir());
                txUsername.setText(modelDataKasir.fieldKasir.get(n).getUsernameKasir());
                txPasswordKasir.setText(modelDataKasir.fieldKasir.get(n).getPasswordKasir());
                txKonfirmPasswordKasir.setText(modelDataKasir.fieldKasir.get(n).getPasswordKasir());
            }
        }
        
    } // END CLASS    
    private void pilihTabelKasir() {
        ListSelectionModel lsm = tabelKasir.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelKasir());
    }  
    
    private void initListener() {
        
        // CEK BUTTON RANDOM ID KASIR
        btRandomIDkas.addActionListener((ActionEvent e) -> {
            kas = new kasir();
            kas.randomIDkasir();
        });
        
        btBatal.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        btBatalAdmin.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        // CEK SIMPAN DATA KASIR FRAME ADMIN
        btSimpanDataKasir.addActionListener((ActionEvent e) -> {
            validasiSimpan();
        });
        
        // CEK EDIT DATA KASIR FRAME ADMIN
        btEditDataKas.addActionListener((ActionEvent e) -> {
            if (txCekRecord.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            } else {
                validasiEditDataKasir();
                segarkanSimpanData();
            }
            
        });
        
        btHapusDataKas.addActionListener((ActionEvent e) -> {
            String hapus = txIDkasir.getText();
            
            if (txCekRecord.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                 int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Kasir "+hapus+"?","Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        kasir.hapusKasir(hapus);
                        segarkanSimpanData();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }     
                }
            }
        });
        
        btSegarkanKas.addActionListener((ActionEvent e) -> {
            refreshAdmin();
        });
        
        
        // CEK EDIT KASIR FRAME KASIR
        btEditDataKasir.addActionListener((ActionEvent e) -> {
            lbTampilBerdasarkanIDkasir.setText(txIDkasir.getText());
            validasiEditDataKasir();   
            segarkan();
        });
        
        txIDkasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txNamaKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        
        txNamaKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txTelpKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        
        txTelpKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                validasiAngka(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txUsername.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        
        txUsername.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txPasswordKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        
        txPasswordKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txKonfirmPasswordKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        
        txKonfirmPasswordKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                validasiBatal();
            }
        });
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());
    }
    
    private boolean validasiSimpan() {
        kas = new kasir();
        if (kas.cekIDkasir()) {
             JOptionPane.showMessageDialog(null, "Data ID Kasir Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDkasir.requestFocus();
            return false;
        } else if (txIDkasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Kasir Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDkasir.requestFocus();
            return false;
        } else if (txNamaKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaKasir.requestFocus();
            return false;
        } else if (txTelpKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Telepon Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTelpKasir.requestFocus();
            return false;
        } else if(txUsername.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Username Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txUsername.requestFocus();
            return false;
        } else if (txPasswordKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txPasswordKasir.requestFocus();
        } else if (txKonfirmPasswordKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Konfirmasi Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txKonfirmPasswordKasir.requestFocus();
        } else if (txKonfirmPasswordKasir.getText().equals(txPasswordKasir.getText())) {
            try {
//                    kasir = new dbKasir();
                
                    kasirData.setIDKasir(txIDkasir.getText());
                    kasirData.setNamaKasir(txNamaKasir.getText());
                    kasirData.setTelpKasir(txTelpKasir.getText());
                    kasirData.setUsernameKasir(txUsername.getText());
                    kasirData.setPasswordKasir(txPasswordKasir.getText());

                    kasir.tambahKasirAdmin(kasirData); 
                    dataKasirM.insert(kasirData);
                    segarkanSimpanData();                    

                } catch (Exception ex) { }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Password Tidak Valid","WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }
        return true;
    }
    
    private boolean validasiEditDataKasir() {        
        String idKasir = txIDkasir.getText();
//        if (txCekRecord.getText().length()==0) {
//            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            return false;
//        } else 
            
            if (txNamaKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaKasir.requestFocus();
            return false;
        } else if (txTelpKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Telepon Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTelpKasir.requestFocus();
            return false;
        } else if(txUsername.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Username Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txUsername.requestFocus();
            return false;
        } else if (txPasswordKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Password Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txPasswordKasir.requestFocus();
            return false;
        } else if (txKonfirmPasswordKasir.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Mohon Konfirmasi Password Dulu \nUntuk Memastikan Apakah Password Benar-Benar Valid atau Tidak ","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txKonfirmPasswordKasir.requestFocus();
            return false;
        } else if (txKonfirmPasswordKasir.getText().equals(txPasswordKasir.getText())) {
            try {
//                kasir   = new dbKasir();         
              
                kasirData.setNamaKasir(txNamaKasir.getText());
                kasirData.setTelpKasir(txTelpKasir.getText());
                kasirData.setUsernameKasir(txUsername.getText());                
                kasirData.setPasswordKasir(txKonfirmPasswordKasir.getText());
                
                kasir.editDataKasir(kasirData, idKasir);
                kas = new kasir();
                if (lbBerdasarkanIDkas.getText().equals(lbTampilIDkas)) {
                    kas.tampilBerdasarkanIDkasir();
                 
                } 
//                else {
//                    try {
//                        segarkanSimpanData();
//                    } catch (Exception e) {
//                    }
//                }
                
            } catch (Exception e) { }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Password Tidak Valid","WARNING",
                    JOptionPane.WARNING_MESSAGE);          
            return false;
        }
        return true;
    }
    
    private void validasiAngka(KeyEvent e) {
        char c = e.getKeyChar();        
        if (Character.isLetter(c)) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Karakter Harus Berupa Angka","WARNING",
                    JOptionPane.WARNING_MESSAGE);           
        }
    }
    
    private void segarkanSimpanData() {
//        txCekRecord.setText(null);
//        txIDkasir.setText(null);
//        txNamaKasir.setText(null);
//        txTelpKasir.setText(null);
//        txUsername.setText(null);
//        txPasswordKasir.setText(null);
//        txKonfirmPasswordKasir.setText(null);
        try {
            row = tabelKasir.getRowCount();
            for (int i = 0; i < row; i++) {
                dataKasirM.delete(0, row);
            }
//            kasir = new dbKasir();
            kasir.tampilKasirAdmin();
        } catch (Exception e) {
        }
    }
    
        private void refreshAdmin() {
        txCekRecord.setText(null);
        txIDkasir.setText(null);
        txNamaKasir.setText(null);
        txTelpKasir.setText(null);
        txUsername.setText(null);
        txPasswordKasir.setText(null);
        txKonfirmPasswordKasir.setText(null);
        txIDkasir.setEditable(true);
        btRandomIDkas.setEnabled(true);
        btSimpanDataKasir.setEnabled(true);
        try {
            row = tabelKasir.getRowCount();
            for (int i = 0; i < row; i++) {
                dataKasirM.delete(0, row);
            }
//            kasir = new dbKasir();
            kasir.tampilKasirAdmin();
        } catch (Exception e) {
        }
    }
    
    private void segarkan() {
        try {
            row = tabelKasir.getRowCount();
            for (int i = 0; i < row; i++) {
                dataKasirM.delete(0, row);
            }
            kas = new kasir();
            kas.formWindowTampil();
        } catch (Exception e) { }
        jScrollPane1.getViewport().setOpaque(false);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txCekRecord = new javax.swing.JTextField();
        lbTampilBerdasarkanIDkasir = new javax.swing.JLabel();
        lbBerdasarkanIDkas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txIDkasir = new javax.swing.JTextField();
        txNamaKasir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txTelpKasir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txUsername = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txPasswordKasir = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txKonfirmPasswordKasir = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        btEditDataKasir = new javax.swing.JButton();
        btBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKasir = new javax.swing.JTable();
        btEditDataKas = new javax.swing.JButton();
        btHapusDataKas = new javax.swing.JButton();
        btSegarkanKas = new javax.swing.JButton();
        btSimpanDataKasir = new javax.swing.JButton();
        btBatalAdmin = new javax.swing.JButton();
        btRandomIDkas = new javax.swing.JButton();

        lbBerdasarkanIDkas.setForeground(new java.awt.Color(204, 204, 204));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PENGATURAN KASIR");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("ID KASIR");

        txIDkasir.setBackground(new java.awt.Color(16, 29, 51));
        txIDkasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDkasir.setForeground(new java.awt.Color(204, 204, 204));
        txIDkasir.setCaretColor(new java.awt.Color(204, 204, 204));

        txNamaKasir.setBackground(new java.awt.Color(16, 29, 51));
        txNamaKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txNamaKasir.setForeground(new java.awt.Color(204, 204, 204));
        txNamaKasir.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("NAMA KASIR");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("TELEPON");

        txTelpKasir.setBackground(new java.awt.Color(16, 29, 51));
        txTelpKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txTelpKasir.setForeground(new java.awt.Color(204, 204, 204));
        txTelpKasir.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("USERNAME");

        txUsername.setBackground(new java.awt.Color(16, 29, 51));
        txUsername.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txUsername.setForeground(new java.awt.Color(204, 204, 204));
        txUsername.setCaretColor(new java.awt.Color(204, 204, 204));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txPasswordKasir.setBackground(new java.awt.Color(16, 29, 51));
        txPasswordKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txPasswordKasir.setForeground(new java.awt.Color(204, 204, 204));
        txPasswordKasir.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("PASSWORD");

        txKonfirmPasswordKasir.setBackground(new java.awt.Color(16, 29, 51));
        txKonfirmPasswordKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txKonfirmPasswordKasir.setForeground(new java.awt.Color(204, 204, 204));
        txKonfirmPasswordKasir.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("KONFIRMASI PASSWORD");

        btEditDataKasir.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btEditDataKasir.setText("EDIT DATA");
        btEditDataKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btBatal.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btBatal.setText("BATAL");
        btBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabelKasir.setBackground(new java.awt.Color(51, 102, 0));
        tabelKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelKasir.setForeground(new java.awt.Color(204, 204, 204));
        tabelKasir.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelKasir.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(tabelKasir);

        btEditDataKas.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btEditDataKas.setText("EDIT DATA");
        btEditDataKas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusDataKas.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusDataKas.setText("HAPUS DATA");
        btHapusDataKas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSegarkanKas.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSegarkanKas.setText("SEGARKAN");
        btSegarkanKas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSimpanDataKasir.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpanDataKasir.setText("SIMPAN DATA");
        btSimpanDataKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btBatalAdmin.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btBatalAdmin.setText("BATAL");
        btBatalAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btRandomIDkas.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRandomIDkas.setText("RANDOM ID");
        btRandomIDkas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txKonfirmPasswordKasir, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addComponent(txPasswordKasir)
                        .addComponent(txNamaKasir)
                        .addComponent(txTelpKasir)
                        .addComponent(txUsername)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txIDkasir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btRandomIDkas)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btSimpanDataKasir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btEditDataKasir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btBatal, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(btBatalAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btEditDataKas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapusDataKas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSegarkanKas)
                        .addGap(0, 311, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
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
                            .addComponent(txIDkasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRandomIDkas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txTelpKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txPasswordKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txKonfirmPasswordKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpanDataKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBatalAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditDataKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditDataKas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapusDataKas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSegarkanKas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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

        setSize(new java.awt.Dimension(1044, 358));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:    
        kas = new kasir();
        kas.formWindowTampil();
        kas.textFieldKasir();
    }//GEN-LAST:event_formWindowOpened
    
    private final dataKasir kasirData = new dataKasir();  
    private final dbKasir kasir = new dbKasir();
    private modelDataKasir dataKasirM = new modelDataKasir();
    
    /**
     * @param args the command line arguments
     * 
     */   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btBatal;
    public javax.swing.JButton btBatalAdmin;
    public javax.swing.JButton btEditDataKas;
    public javax.swing.JButton btEditDataKasir;
    public javax.swing.JButton btHapusDataKas;
    public javax.swing.JButton btRandomIDkas;
    public javax.swing.JButton btSegarkanKas;
    public javax.swing.JButton btSimpanDataKasir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JLabel lbBerdasarkanIDkas;
    public javax.swing.JLabel lbTampilBerdasarkanIDkasir;
    private javax.swing.JTable tabelKasir;
    private javax.swing.JTextField txCekRecord;
    public javax.swing.JTextField txIDkasir;
    public javax.swing.JPasswordField txKonfirmPasswordKasir;
    public javax.swing.JTextField txNamaKasir;
    public javax.swing.JPasswordField txPasswordKasir;
    public javax.swing.JTextField txTelpKasir;
    public javax.swing.JTextField txUsername;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        koneksi = new dbKoneksi();
        koneksi.openKoneksi();
    }

    private void tabelKasir() {
        dataKasirM = new modelDataKasir();
        tabelKasir.setModel(dataKasirM);
    }
    
    private void lebarKolomTabel() {        
        tabelKasir.setRowHeight(27);
        tabelKasir.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelKasir.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabelKasir.getColumnModel().getColumn(2).setPreferredWidth(260);
        tabelKasir.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabelKasir.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabelKasir.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabelKasir.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
}
