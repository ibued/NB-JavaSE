

package proyek.rpl.frame;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import proyek.rpl.daoImplement.db.barang.barangImplement;
import proyek.rpl.daoImplement.db.barang.kategoriImplement;
import proyek.rpl.daoImplement.db.barang.satuanImplement;
import proyek.rpl.daoImplement.db.barang.transaksiPenjualanImplement;
import proyek.rpl.data.dataBarang;
import proyek.rpl.data.dataKategori;
import proyek.rpl.data.dataPelanggan;
import proyek.rpl.data.dataQueryJual;
import proyek.rpl.data.dataSatuan;
import proyek.rpl.data.dataTransaksiPenjualan;
import proyek.rpl.dataModel.modelDataBarang;
import proyek.rpl.dataModel.modelDataPelanggan;
import proyek.rpl.dataModel.modelDataQueryJual;
import proyek.rpl.dataModel.modelDataQueryJualDetil;
import proyek.rpl.dataModel.modelKategori;
import proyek.rpl.dataModel.modelSatuan;
import proyek.rpl.dataModel.modelTransaksiPenjualan;
import proyek.rpl.database.dbKasir;
import proyek.rpl.database.dbKoneksi;
import proyek.rpl.database.dbPelanggan;
import proyek.rpl.database.query.queryJual;
import proyek.rpl.dialog.dialogAbout;
import proyek.rpl.dialog.dialogHelp;
import proyek.rpl.dialog.dialogIDbarang;
import proyek.rpl.dialog.dialogIDkategori;
import proyek.rpl.dialog.dialogIDpelanggan;
import proyek.rpl.dialog.dialogIDsatuan;
import proyek.rpl.dialog.dialogPengaturanAdmin;
import proyek.rpl.dialog.dialogPengaturanKasir;
import proyek.rpl.dialog.dialogPenjualan;

/**
 * Created on Mey 09, 2014, 20:02:09 PM
 * @author ibued
 */
public class frameIndex extends javax.swing.JFrame {
    
    private dbKoneksi kon;    
    private updateDariDialogPengaturanKasir ddpk; // class didalam paket frame
    private IDPelanggan iDPelanggan; // class didalam paket frame
    private IDBarang iDBarang; // class didalam paket frame
    private cekIDkategori iDkategori; // class didalam paket frame
    private cekIDsatuan iDsatuan; // class didalam paket frame
    private penjualan jualClass; // class didalam paket frame 
    private queryJualPenjualan jualPenjualan; // class didalam paket frame 
    private queryJualDetilPenjualan detilPenjualan; // class didalam paket frame 
    
//    private JasperReport jasperReport;
//    private JasperDesign jasperDesign;
//    private JasperPrint jasperPrint;
    
    // global
    private int row;
    public int idPlgJual;
    public int idBrngJual;
    public int idBrngJual1;
    public int idBrngJual2;
    public int idBrngJual3;
    public int idBrngJual4;
    public int idBrngJual5;
    public int idBrngJual6;
    public int idBrngJual7;
    public int idBrngJual8;
    public int idBrngJual9;
    public int idKatDialog;
    public int idSatDialog;
    public String idKasTampilAdmin;
    public String id;
    public String name;
    public String user;
    public String password; 
        
    public frameIndex() {
        initComponents();
        koneksi();
        initListenerQueryJual();
        initListenerQueryJualDetil();
        initListenerTransaksiPenjualan();
        initListenerKasir();
        initListenerPelanggan();
        initListenerBarang();   
        initListenerAdminDataKasir();
        initListenerMenuItem();
        initListenerKategori();
        initListenerSatuan();
        tabelModelDataPlg();  
        tabelModelDataBrng();
        tabelModelDataKategori();
        tabelModelDataSatuan();
        tabelModelQueryJual();
        tabelModelQueryJualDetil();
        tampilDataPelanggan();
        tampilDataBarang();
        tampilDataKategori();
        tampilDataSatuan();
        tampilQuery();
        tampilQueryDetil();
        selectTabelPlg();
        selectTabelBrng();
        selectTabelKategori();
        selectTabelSatuan();
        komponen();  
        lebarKolomTabelKategori();
        lebarKolomTabelSatuan();
        dataPelangganSegarkan();
        dataBarangSegarkan();      
        tanggal();
    }    
    
    /*                                                                                                                                          ||
        ================================================== FUNGSI METHOD QUERY PENJUALAN ===============================================
    ||                                                                                                                                          */
    private class queryJualPenjualan {      
        
        private void cariQueryJual() {
            int no = 1;
            int comboBox = cbCariQuery.getSelectedIndex();
            String textCari = txCariQueryJual.getText();
            String cari = null;
            try {
                if (comboBox == 1) {
                    cari = (" SELECT Jual.*, " +
                    "Pelanggan.NAMA_PELANGGAN, " +
                    "Kasir.NAMA_KASIR " +
                    "FROM Pelanggan " +
                    "RIGHT JOIN (Kasir " +
                    "RIGHT JOIN Jual " +
                    "ON Kasir.ID_KASIR         = Jual.ID_KASIR) " +
                    "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN "+ 
                    "WHERE Jual.ID_JUAL LIKE '%"+textCari+"%' ");
                } else if (comboBox == 2) {
                    cari = (" SELECT Jual.*, " +
                    "Pelanggan.NAMA_PELANGGAN, " +
                    "Kasir.NAMA_KASIR " +
                    "FROM Pelanggan " +
                    "RIGHT JOIN (Kasir " +
                    "RIGHT JOIN Jual " +
                    "ON Kasir.ID_KASIR         = Jual.ID_KASIR) " +
                    "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN "+ 
                    "WHERE Pelanggan.NAMA_PELANGGAN LIKE '%"+textCari+"%' ");
                } else if (comboBox == 3) {
                    cari = (" SELECT Jual.*, " +
                    "Pelanggan.NAMA_PELANGGAN, " +
                    "Kasir.NAMA_KASIR " +
                    "FROM Pelanggan " +
                    "RIGHT JOIN (Kasir " +
                    "RIGHT JOIN Jual " +
                    "ON Kasir.ID_KASIR         = Jual.ID_KASIR) " +
                    "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN "+ 
                    "WHERE Kasir.NAMA_KASIR LIKE '%"+textCari+"%' ");
                } else if (comboBox == 4) {
                    cari = (" SELECT Jual.*, " +
                    "Pelanggan.NAMA_PELANGGAN, " +
                    "Kasir.NAMA_KASIR " +
                    "FROM Pelanggan " +
                    "RIGHT JOIN (Kasir " +
                    "RIGHT JOIN Jual " +
                    "ON Kasir.ID_KASIR         = Jual.ID_KASIR) " +
                    "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN "+ 
                    "WHERE Jual.TANGGAL_JUAL LIKE '%"+textCari+"%' ");
                }
                
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cari);
                ResultSet rs = ps.executeQuery();
                filter();
                while (rs.next()) {
                    dataQueryJual Jual = new dataQueryJual();
                    Jual.setNomorJual(no++);
                    Jual.setIDJual(rs.getString(1));
                    Jual.setTanggalJual(rs.getString(2));
                    Jual.setIDPelangganJual(rs.getString(3));
                    Jual.setIDKasirJual(rs.getString(4));
                    Jual.setTotalJual(rs.getString(5));
                    Jual.setBayarJual(rs.getString(6));
                    Jual.setKembalianJual(rs.getString(7));
                    Jual.setNamaPelanggan(rs.getString(8));
                    Jual.setNamaKasir(rs.getString(9));
                    queryJual.insert(Jual);
                }
            } catch (SQLException e) { }
            if (txCariQueryJual.getText().length()==0) {
                segarkanQueryJual();
//                jLabel41.setText(null);
            } else if (tabelQueryPenjualan.getRowCount()>0) {
                jLabel41.setText("INFO : DATA DI TEMUKAN");
            } else {
                jLabel41.setText("INFO : DATA TIDAK DI TEMUKAN");
            }
        }
        
        private void selisihJual() {
            int no = 1;         
            String textCari1 = jTextField1.getText();
            String textCari5 = jTextField5.getText();
            try {
                String cari = (" SELECT Jual.*, " +
                "Pelanggan.NAMA_PELANGGAN, " +
                "Kasir.NAMA_KASIR " +
                "FROM Pelanggan " +
                "RIGHT JOIN (Kasir " +
                "RIGHT JOIN Jual " +
                "ON Kasir.ID_KASIR         = Jual.ID_KASIR) " +
                "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN " + 
                "WHERE (Jual.TANGGAL_JUAL LIKE '%"+textCari1+"%') " +
                "OR (Jual.TANGGAL_JUAL LIKE '%"+textCari5+"%') " +
                "ORDER BY Jual.TANGGAL_JUAL");                
                
                PreparedStatement ps = dbKoneksi.conn.prepareStatement(cari);
                ResultSet rs = ps.executeQuery();
                filter();
                while (rs.next()) {
                    dataQueryJual Jual = new dataQueryJual();
                    Jual.setNomorJual(no++);
                    Jual.setIDJual(rs.getString(1));
                    Jual.setTanggalJual(rs.getString(2));
                    Jual.setIDPelangganJual(rs.getString(3));
                    Jual.setIDKasirJual(rs.getString(4));
                    Jual.setTotalJual(rs.getString(5));
                    Jual.setBayarJual(rs.getString(6));
                    Jual.setKembalianJual(rs.getString(7));
                    Jual.setNamaPelanggan(rs.getString(8));
                    Jual.setNamaKasir(rs.getString(9));
                    queryJual.insert(Jual);
                }
            } catch (SQLException e) { }
//            if (txCariQueryJual.getText().length()==0) {
//                segarkanQueryJual();
////                jLabel41.setText(null);
//            } else if (tabelQueryPenjualan.getRowCount()>0) {
//                jLabel41.setText("INFO : DATA DI TEMUKAN");
//            } else {
//                jLabel41.setText("INFO : DATA TIDAK DI TEMUKAN");
//            }
        }
        
        private boolean hapusQueryJual() {            
            try {
                String hapus = ("DELETE FROM IBUED.JUAL WHERE ID_JUAL='"+jTextField4.getText()+"'");
                dbKoneksi.st.executeUpdate(hapus);
                dbKoneksi.conn.commit();                
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }        
            return false;
        }
        
    }
    
    private void initListenerQueryJual() {
        
        cbCariQuery.addItemListener((ItemEvent e) -> {
            if (cbCariQuery.getSelectedIndex()==0) {                
                segarkanQueryJual();
                txCariQueryJual.setEditable(false);
                txCariQueryJual.setText(null);
            } else if (cbCariQuery.getSelectedIndex()==1 || cbCariQuery.getSelectedIndex()==2 || cbCariQuery.getSelectedIndex()==3 || cbCariQuery.getSelectedIndex()==4) {
                txCariQueryJual.setEditable(true);
            } else {
                segarkanQueryJual();
            }
        });
        
        txCariQueryJualCopy.addTextListener((TextEvent e) -> {
            if (txCariQueryJualCopy.getText().length()==0) {
                segarkanQueryJual();
                jLabel41.setText(null);
            } else {
                try {
                    jualPenjualan = new queryJualPenjualan();
                    jualPenjualan.cariQueryJual();
                } catch (Exception ex) { } 
            }
        });
        
        btRefreshQueryJual.addActionListener((ActionEvent e) -> {
            segarkanQueryJual();
            txCariQueryJual.setText(null);
            txCariQueryJualCopy.setText(null);
        });
        
        btHapusDataJual.addActionListener((ActionEvent e) -> {
            if (tabelQueryPenjualan.getRowCount()==0) {
                 JOptionPane.showMessageDialog(null, "Tabel Jual Kosong","WARNING",
                        JOptionPane.WARNING_MESSAGE);   
            } else if (jTextField4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                        JOptionPane.WARNING_MESSAGE);                                              
            } else {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Jual Berdasarkan "+jTextField4.getText()+" " ,"Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        jualPenjualan = new queryJualPenjualan();
                        jualPenjualan.hapusQueryJual();
                        segarkanQueryJual();
                        segarkanQueryJualDetil();
                    } catch (Exception ex) { } 
                }  
            }
        });
        
        btCetakJual.addActionListener((ActionEvent e) -> {
            if (tabelQueryPenjualan.getRowCount()==0) {
                JOptionPane.showMessageDialog(null, "Data Penjualan Kosong","WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                //dispose();
            } else {
                try {                    
                    InputStream repot1 = getClass().getResourceAsStream("/proyek/rpl/laporan/jual/jual.jrxml");
                    JasperReport jr = JasperCompileManager.compileReport(repot1);
                    JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), dbKoneksi.st.getConnection());
                    JasperViewer viewer = new JasperViewer(jp, false);    
                    viewer.setTitle("Laporan Data Penjualan");
                    viewer.setIconImage(new ImageIcon(getClass().getResource("/proyek/rpl/gambar/print.PNG")).getImage());
                    viewer.setExtendedState(getExtendedState() | 0x6);
                    viewer.setVisible(true);
                    viewer.setZoomRatio((float) 0.75);                     
//                    viewer.setFitWidthZoomRatio();
                } catch (JRException | SQLException ex) { }
            }
        });
        
    }
    
    private void tampilQuery() {
        try {
            row = tabelQueryPenjualan.getRowCount();
            for (int i = 0; i < row; i++) {
                queryJual.delete(0, row);
            }     
            Queues.tampilQueryJual();
        } catch (Exception e) { }
    }
    
    private void segarkanQueryJual() {
        try {
            row = tabelQueryPenjualan.getRowCount();
            for (int i = 0; i < row; i++) {
                queryJual.delete(0, row);
            }           
            Queues.tampilQueryJual();
            jTextField4.setText(null);
            jTextField1.setText(null);
            jTextField5.setText(null);
            jLabel41.setText(null);
        } catch (Exception e) { }
    }
    private void filter() {
        row = tabelQueryPenjualan.getRowCount();
        for (int i = 0; i < row; i++) {
            queryJual.delete(0, row);
        }      
    }
    
    private void tabelModelQueryJual() {
        tabelQueryPenjualan.setModel(queryJual);  
        lebarKolomTabelQuery(tabelQueryPenjualan, new int[] {40,90,150,140,120,130,150,130,190,190} );
    }
    private void lebarKolomTabelQuery(javax.swing.JTable tb,int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);       
        int kolom = tb.getColumnCount();
        int i;
        for(i=0 ; i<kolom; i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(27); 
        }      
    }
    
    /*                                                                                                                                          ||
        ================================================== FUNGSI METHOD QUERY DETIL PENJUALAN ===============================================
    ||                                                                                                                                          */
    private class queryJualDetilPenjualan {
        
        private boolean hapusSemuaQueryJualDetil() {            
            try {
                String hapus = ("DELETE FROM IBUED.JUAL_DETIL");
                dbKoneksi.st.executeUpdate(hapus);
                dbKoneksi.conn.commit();                
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }        
            return false;
        }
        
        private boolean hapusQueryJualDetil() {            
            try {
                String hapus = ("DELETE FROM IBUED.JUAL_DETIL WHERE ID_JUAL='"+jTextField3.getText()+"'");
                dbKoneksi.st.executeUpdate(hapus);
                dbKoneksi.conn.commit();                
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }        
            return false;
        }
        
    }
    
    private void initListenerQueryJualDetil() {
        
        cekBokQueryDetil.addActionListener((ActionEvent e) -> {
            try {
                if (cekBokQueryDetil.isSelected()==true) {
                    tabelQueryPenjualanDetil.selectAll();
                } else {
                    tabelQueryPenjualanDetil.clearSelection();
                    segarkanQueryJualDetil();
                }
            } catch (Exception ex) { }
        });
        
        btRefreshQueryJualDetil.addActionListener((ActionEvent e) -> {
            segarkanQueryJualDetil();
        });
        
        btHapusDataJualDetil.addActionListener((ActionEvent e) -> {
            if (cekBokQueryDetil.isSelected()==true) {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Semua Data Jual Detil" ,"Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        detilPenjualan = new queryJualDetilPenjualan();
                        detilPenjualan.hapusSemuaQueryJualDetil();
                        segarkanQueryJualDetil();
                        segarkanQueryJual();
                    } catch (Exception ex) { } 
                }
            } else if (tabelQueryPenjualanDetil.getRowCount()==0) {
                JOptionPane.showMessageDialog(null, "Tabel Jual Detil Kosong","WARNING",
                        JOptionPane.WARNING_MESSAGE);  
            } else if (jTextField3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                        JOptionPane.WARNING_MESSAGE);                                              
            } else {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Jual Detil Berdasarkan "+jTextField3.getText()+" " ,"Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        detilPenjualan = new queryJualDetilPenjualan();
                        detilPenjualan.hapusQueryJualDetil();
                        segarkanQueryJualDetil();
                        segarkanQueryJual();
                    } catch (Exception ex) { } 
                }  
            }
        });
                
    }
    
    private void tampilQueryDetil() {
        try {
            row = tabelQueryPenjualanDetil.getRowCount();
            for (int i = 0; i < row; i++) {
                queryJualDetil.delete(0, row);
            }
            Queues.tampilQueryJualDetil();
        } catch (Exception e) { }
    }
    
    private void segarkanQueryJualDetil() {
        try {
            row = tabelQueryPenjualanDetil.getRowCount();
            for (int i = 0; i < row; i++) {
                queryJualDetil.delete(0, row);
            }
            Queues.tampilQueryJualDetil();
            cekBokQueryDetil.setSelected(false);
            jTextField3.setText(null);
        } catch (Exception e) { }
    }
    
    private void tabelModelQueryJualDetil() {
        tabelQueryPenjualanDetil.setModel(queryJualDetil);
        lebarKolomTabelQueryDetil(tabelQueryPenjualanDetil, new int[] {40,120,140,140,80,300,170,170,170} );
    }
    private void lebarKolomTabelQueryDetil(javax.swing.JTable tb,int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);       
        int kolom = tb.getColumnCount();
        int i;
        for(i=0 ; i<kolom; i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            tb.setRowHeight(27); 
        }      
    }
    
    
    /*                                                                                                                                          ||
        ================================================== FUNGSI METHOD TRANSAKSI PENJUALAN ===============================================
    ||                                                                                                                                          */
    private class penjualan { // MEMBUAT CLASS DI DALAM PAKET
        DecimalFormat dfo = new DecimalFormat("#,##0");
        int kurang;
        int hasil;
        int hasil1;
        int hasil2;
        int hasil3;
        int hasil4;
        int hasil5;
        int hasil6;
        int hasil7;
        int hasil8;
        int hasil9;
        int kurang1;
//        int kurang2;
//        int hasil2;
        
        float total = 0;
        float diskonParse = 0;
        long subTotal = 0;
        /*---JUMLAH BARANG 1---*/
            float hasilDiskon1 = 0;
            float jumlah1 = 0;
            int jum1 = 0;                  
            float total1 = 0;
            float total2 = 0;
            float diskonParse1 = 0;
            float diskonParse2 = 0;
        int jumlahBarang = 0;
        int jumlahBarang1 = 0;
        int jumlahBarang2 = 0;
        
        int jumlahBarangKedua_1 = 0;
        int jumlahBarangKedua_2 = 0;
        int jumlahBarangKedua_3 = 0;
        String idBarang = txIDbarangJual.getText();
        String idBarang1 = txIDbarangJual1.getText();
        String idBarang2 = txIDbarangJual2.getText();
        String idBarang3 = txIDbarangJual3.getText();
        String idBarang4 = txIDbarangJual4.getText();
        String idBarang5 = txIDbarangJual5.getText();
        String idBarang6 = txIDbarangJual6.getText();
        String idBarang7 = txIDbarangJual7.getText();
        String idBarang8 = txIDbarangJual8.getText();
        String idBarang9 = txIDbarangJual9.getText();
        String stok = null;
        String namaBarang = null;
        String namaBarang1 = null;
        
        private boolean cekStokBarang() {  
            
            try {                
                jumlahBarang = Integer.parseInt(txJumlahBarangJual.getText());
                jumlahBarang1 = Integer.parseInt(txJumlahBarangJual.getText());
                jumlahBarang2 = Integer.parseInt(txJumlahBarangJual.getText());                
            } catch (NumberFormatException e) { }            
           
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
                    
                try {
                    jumlahBarang = Integer.parseInt(stok);
                    kurang  = Integer.parseInt(stok);                
                    hasil   =  kurang - jumlahBarang1;                   
                } catch (NumberFormatException e) { }
                    
            } catch (SQLException e) { }
            
            if (jumlahBarang<=0) {                   
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual.requestFocus();
                return true;
            } else if (jumlahBarang2>kurang) {                    
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual.requestFocus();
                return true;
            }            
            return false;
        }
        
        private boolean cekStokBarang1() {
            try {
                jumlahBarangKedua_1 = Integer.parseInt(txJumlahBarangJual1.getText());
                jumlahBarangKedua_2 = Integer.parseInt(txJumlahBarangJual1.getText());
                jumlahBarangKedua_3 = Integer.parseInt(txJumlahBarangJual1.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang1+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKedua_1 = Integer.parseInt(stok);
                kurang1 = Integer.parseInt(stok);
                hasil1   =  kurang1 - jumlahBarangKedua_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKedua_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual1.requestFocus();
                return true;
            } else if (jumlahBarangKedua_3>kurang1) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang1+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual1.requestFocus();
                return true;
            }
            return false;
        }
        
        private boolean cekStokBarang2() {
            int jumlahBarangKetiga_1 = 0;
            int jumlahBarangKetiga_2 = 0;
            int jumlahBarangKetiga_3 = 0;
            int kurang2 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKetiga_1 = Integer.parseInt(txJumlahBarangJual2.getText());
                jumlahBarangKetiga_2 = Integer.parseInt(txJumlahBarangJual2.getText());
                jumlahBarangKetiga_3 = Integer.parseInt(txJumlahBarangJual2.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang2+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKetiga_1 = Integer.parseInt(stok);
                kurang2 = Integer.parseInt(stok);
                hasil2   =  kurang2 - jumlahBarangKetiga_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKetiga_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual2.requestFocus();
                return true;
            } else if (jumlahBarangKetiga_3>kurang2) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang2+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual2.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang3() {
            int jumlahBarangKeempat_1 = 0;
            int jumlahBarangKeempat_2 = 0;
            int jumlahBarangKeempat_3 = 0;
            int kurang3 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKeempat_1 = Integer.parseInt(txJumlahBarangJual3.getText());
                jumlahBarangKeempat_2 = Integer.parseInt(txJumlahBarangJual3.getText());
                jumlahBarangKeempat_3 = Integer.parseInt(txJumlahBarangJual3.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang3+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKeempat_1 = Integer.parseInt(stok);
                kurang3 = Integer.parseInt(stok);
                hasil3   =  kurang3 - jumlahBarangKeempat_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKeempat_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual3.requestFocus();
                return true;
            } else if (jumlahBarangKeempat_3>kurang3) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang3+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual3.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang4() {
            int jumlahBarangKelima_1 = 0;
            int jumlahBarangKelima_2 = 0;
            int jumlahBarangKelima_3 = 0;
            int kurang4 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKelima_1 = Integer.parseInt(txJumlahBarangJual4.getText());
                jumlahBarangKelima_2 = Integer.parseInt(txJumlahBarangJual4.getText());
                jumlahBarangKelima_3 = Integer.parseInt(txJumlahBarangJual4.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang4+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKelima_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil4   =  kurang4 - jumlahBarangKelima_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKelima_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual4.requestFocus();
                return true;
            } else if (jumlahBarangKelima_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual4.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang5() {
            int jumlahBarangKelima_1 = 0;
            int jumlahBarangKelima_2 = 0;
            int jumlahBarangKelima_3 = 0;
            int kurang4 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKelima_1 = Integer.parseInt(txJumlahBarangJual5.getText());
                jumlahBarangKelima_2 = Integer.parseInt(txJumlahBarangJual5.getText());
                jumlahBarangKelima_3 = Integer.parseInt(txJumlahBarangJual5.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang5+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKelima_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil5   =  kurang4 - jumlahBarangKelima_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKelima_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual5.requestFocus();
                return true;
            } else if (jumlahBarangKelima_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual5.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang6() {
            int jumlahBarangKeemam_1 = 0;
            int jumlahBarangKeemam_2 = 0;
            int jumlahBarangKeemam_3 = 0;
            int kurang4 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKeemam_1 = Integer.parseInt(txJumlahBarangJual6.getText());
                jumlahBarangKeemam_2 = Integer.parseInt(txJumlahBarangJual6.getText());
                jumlahBarangKeemam_3 = Integer.parseInt(txJumlahBarangJual6.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang6+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKeemam_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil6   =  kurang4 - jumlahBarangKeemam_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKeemam_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual6.requestFocus();
                return true;
            } else if (jumlahBarangKeemam_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual6.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang7() {
            int jumlahBarangKetujuh_1 = 0;
            int jumlahBarangKetujuh_2 = 0;
            int jumlahBarangKetujuh_3 = 0;
            int kurang4 = 0;
//            int hasilStokBrng2;
            
            try {
                jumlahBarangKetujuh_1 = Integer.parseInt(txJumlahBarangJual7.getText());
                jumlahBarangKetujuh_2 = Integer.parseInt(txJumlahBarangJual7.getText());
                jumlahBarangKetujuh_3 = Integer.parseInt(txJumlahBarangJual7.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang7+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKetujuh_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil7   =  kurang4 - jumlahBarangKetujuh_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKetujuh_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual7.requestFocus();
                return true;
            } else if (jumlahBarangKetujuh_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual7.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang8() {
            int jumlahBarangKedelapan_1 = 0;
            int jumlahBarangKedelapan_2 = 0;
            int jumlahBarangKedelapan_3 = 0;
            int kurang4 = 0;
            
            try {
                jumlahBarangKedelapan_1 = Integer.parseInt(txJumlahBarangJual8.getText());
                jumlahBarangKedelapan_2 = Integer.parseInt(txJumlahBarangJual8.getText());
                jumlahBarangKedelapan_3 = Integer.parseInt(txJumlahBarangJual8.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang8+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKedelapan_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil8  =  kurang4 - jumlahBarangKedelapan_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKedelapan_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual8.requestFocus();
                return true;
            } else if (jumlahBarangKedelapan_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual8.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekStokBarang9() {
            int jumlahBarangKesembilan_1 = 0;
            int jumlahBarangKesembilan_2 = 0;
            int jumlahBarangKesembilan_3 = 0;
            int kurang4 = 0;
            
            try {
                jumlahBarangKesembilan_1 = Integer.parseInt(txJumlahBarangJual9.getText());
                jumlahBarangKesembilan_2 = Integer.parseInt(txJumlahBarangJual9.getText());
                jumlahBarangKesembilan_3 = Integer.parseInt(txJumlahBarangJual9.getText());
            } catch (NumberFormatException e) { }
            
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT NAMA_BARANG, STOK FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang9+"' ");         
                while(rs.next()) {
                    stok = rs.getString("STOK"); 
                    namaBarang  = rs.getString("NAMA_BARANG");  
                }
            } catch (SQLException e) { }
            
            try {
                jumlahBarangKesembilan_1 = Integer.parseInt(stok);
                kurang4 = Integer.parseInt(stok);
                hasil9  =  kurang4 - jumlahBarangKesembilan_2;
            } catch (NumberFormatException e) { }
            
            if (jumlahBarangKesembilan_1<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Sudah Habis","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txJumlahBarangJual9.requestFocus();
                return true;
            } else if (jumlahBarangKesembilan_3>kurang4) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang "+namaBarang+" Maksimal "+kurang4+" ","WARNING",
                        JOptionPane.WARNING_MESSAGE);    
                txJumlahBarangJual9.requestFocus();
                return true;
            }
            
            return false;
        }
        
        private boolean cekEditStok() {
            int a = 0;
            try {
                a = Integer.parseInt(txBanyakBarang.getText());
            } catch (NumberFormatException e) { }
            
            switch (a) {
                case 1:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 2:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 3:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 4:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 5:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 6:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil5+"' WHERE ID_BARANG='"+idBarang5+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                
                case 7:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil5+"' WHERE ID_BARANG='"+idBarang5+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil6+"' WHERE ID_BARANG='"+idBarang6+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                
                case 8:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil5+"' WHERE ID_BARANG='"+idBarang5+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil6+"' WHERE ID_BARANG='"+idBarang6+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil7+"' WHERE ID_BARANG='"+idBarang7+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                case 9:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil5+"' WHERE ID_BARANG='"+idBarang5+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil6+"' WHERE ID_BARANG='"+idBarang6+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil7+"' WHERE ID_BARANG='"+idBarang7+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil8+"' WHERE ID_BARANG='"+idBarang8+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                
                case 10:
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil+"' WHERE ID_BARANG='"+idBarang+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil1+"' WHERE ID_BARANG='"+idBarang1+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil2+"' WHERE ID_BARANG='"+idBarang2+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil3+"' WHERE ID_BARANG='"+idBarang3+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil4+"' WHERE ID_BARANG='"+idBarang4+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil5+"' WHERE ID_BARANG='"+idBarang5+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil6+"' WHERE ID_BARANG='"+idBarang6+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil7+"' WHERE ID_BARANG='"+idBarang7+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil8+"' WHERE ID_BARANG='"+idBarang8+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                    try {
                        String edit = ("UPDATE IBUED.BARANG SET STOK='"+hasil8+"' WHERE ID_BARANG='"+idBarang9+"'");
                        dbKoneksi.st.executeUpdate(edit);
                        dbKoneksi.conn.commit();
                    } catch (SQLException e) { }
                break;
                    
                default: return false;
            }
            
            return true;
        }
        
        private boolean tambahJual() {   
            String now = null;
            try {
                Calendar cal = Calendar.getInstance();
    //            Time time = Time.valueOf("HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm:ss a");
                now = sdf.format(cal.getTime());
//                txTglJual.setText(" "+now);
            } catch (Exception e) { }
            try {
                String tambah = ("INSERT INTO IBUED.JUAL VALUES ('"+txIDjual.getText()+"','"+txTglJual.getText()+now+"','"+txIDplgJual.getText()+"',"
                        + "'"+txIDkasirJual.getText()+"','"+txSubTotalBarangJual.getText()+"','"+txBayarJual.getText()+"','"+lbKembalianJual.getText()+"') ");
                dbKoneksi.st.executeUpdate(tambah);
                dbKoneksi.conn.commit();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
                segarkanQueryJual();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Tambah","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
        }
        
        private boolean tambahJualDetil() {
            int bnyakJual = 0 ;
            try {
                bnyakJual = Integer.parseInt(txBanyakBarang.getText());
            } catch (NumberFormatException e) { }
            if (bnyakJual==1) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");            
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }                
            } else if (bnyakJual==2) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                     String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }  
            } else if (bnyakJual==3) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }  
            } else if (bnyakJual==4) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==5) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==6) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    String tambahJualDetil5 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual5.getText()+"', '"+txJumlahBarangJual5.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.st.executeUpdate(tambahJualDetil5);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==7) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    String tambahJualDetil5 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual5.getText()+"', '"+txJumlahBarangJual5.getText()+"')");
                    String tambahJualDetil6 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual6.getText()+"', '"+txJumlahBarangJual6.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.st.executeUpdate(tambahJualDetil5);
                    dbKoneksi.st.executeUpdate(tambahJualDetil6);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==8) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    String tambahJualDetil5 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual5.getText()+"', '"+txJumlahBarangJual5.getText()+"')");
                    String tambahJualDetil6 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual6.getText()+"', '"+txJumlahBarangJual6.getText()+"')");
                    String tambahJualDetil7 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual7.getText()+"', '"+txJumlahBarangJual7.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.st.executeUpdate(tambahJualDetil5);
                    dbKoneksi.st.executeUpdate(tambahJualDetil6);
                    dbKoneksi.st.executeUpdate(tambahJualDetil7);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==9) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    String tambahJualDetil5 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual5.getText()+"', '"+txJumlahBarangJual5.getText()+"')");
                    String tambahJualDetil6 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual6.getText()+"', '"+txJumlahBarangJual6.getText()+"')");
                    String tambahJualDetil7 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual7.getText()+"', '"+txJumlahBarangJual7.getText()+"')");
                    String tambahJualDetil8 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual8.getText()+"', '"+txJumlahBarangJual8.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.st.executeUpdate(tambahJualDetil5);
                    dbKoneksi.st.executeUpdate(tambahJualDetil6);
                    dbKoneksi.st.executeUpdate(tambahJualDetil7);
                    dbKoneksi.st.executeUpdate(tambahJualDetil8);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            } else if (bnyakJual==10) {
                try {
                    String tambahJualDetil = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual.getText()+"', '"+txJumlahBarangJual.getText()+"')");  
                    String tambahJualDetil1 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual1.getText()+"', '"+txJumlahBarangJual1.getText()+"')"); 
                    String tambahJualDetil2 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual2.getText()+"', '"+txJumlahBarangJual2.getText()+"')");
                    String tambahJualDetil3 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual3.getText()+"', '"+txJumlahBarangJual3.getText()+"')");
                    String tambahJualDetil4 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual4.getText()+"', '"+txJumlahBarangJual4.getText()+"')");
                    String tambahJualDetil5 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual5.getText()+"', '"+txJumlahBarangJual5.getText()+"')");
                    String tambahJualDetil6 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual6.getText()+"', '"+txJumlahBarangJual6.getText()+"')");
                    String tambahJualDetil7 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual7.getText()+"', '"+txJumlahBarangJual7.getText()+"')");
                    String tambahJualDetil8 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual8.getText()+"', '"+txJumlahBarangJual8.getText()+"')");
                    String tambahJualDetil9 = ("INSERT INTO IBUED.JUAL_DETIL (ID_JUAL,ID_BARANG,JUMLAH) VALUES('"+txIDjual.getText()+"',"
                            + "'"+txIDbarangJual9.getText()+"', '"+txJumlahBarangJual9.getText()+"')");
                    dbKoneksi.st.executeUpdate(tambahJualDetil);
                    dbKoneksi.st.executeUpdate(tambahJualDetil1);
                    dbKoneksi.st.executeUpdate(tambahJualDetil2);
                    dbKoneksi.st.executeUpdate(tambahJualDetil3);
                    dbKoneksi.st.executeUpdate(tambahJualDetil4);
                    dbKoneksi.st.executeUpdate(tambahJualDetil5);
                    dbKoneksi.st.executeUpdate(tambahJualDetil6);
                    dbKoneksi.st.executeUpdate(tambahJualDetil7);
                    dbKoneksi.st.executeUpdate(tambahJualDetil8);
                    dbKoneksi.st.executeUpdate(tambahJualDetil9);
                    dbKoneksi.conn.commit();
                    segarkanQueryJualDetil();
                } catch (SQLException e) { }
            }
            return true;            
        }
        
        private void hasilTransaksiJual() {
            try {     
                double hasilL;
                int subTotall = Integer.parseInt(txSubTotalBarangJualCopy.getText());
                float bayar = Float.parseFloat(lbCopyBayar.getText());
                hasilL = bayar-subTotall;       
                lbKembalianJual.setText("Rp. "+dfo.format(hasilL));
            } catch (NumberFormatException e) { }           
        }
        
//        private void cekHargaDiskon() {
//            try {
//                float hasilDiskon = 0;
//                diskonParse = 0;
//                total = 0;
//                int harga = Integer.parseInt(txHargaBarangJual.getText());
//                String diskon = null;  
//                try {
//                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"'");
//                    while(rs.next()) {
//                        diskon = rs.getString("DISKON");                    
//                    }
//                } catch (SQLException e) { } 
//
//                    diskonParse = Integer.parseInt(diskon);
//                    total = total+harga;
//
//                    float bayar = diskonParse/100*total;
//                    hasilDiskon = total-bayar;
//                    txTotalBarangJual.setText("Rp. "+dfo.format(hasilDiskon));
//            } catch (NumberFormatException e) { }
//        }
        
        private void cekSubTotal() {            
            float hasilDiskon = 0;
            float jumlah = 0;
            int jum = 0;
            
            diskonParse = 0;
            total = 0;            
            int harga = Integer.parseInt(txHargaBarangJual.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            long textJumlah = Integer.parseInt(txJumlahBarangJualCopy.getText());       
            String diskon = null;              
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"'");
                while(rs.next()) {
                    diskon = rs.getString("DISKON");        
                }
            } catch (SQLException e) { } 
       
                diskonParse = Integer.parseInt(diskon);
                total = total+harga;
                
                float bayar = diskonParse/100*total;
                hasilDiskon = total-bayar;                    
                jumlah = hasilDiskon*textJumlah;
                jum = (int) (hasilDiskon*textJumlah);            
                subTotal = (long) (jum+copyDiskon+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
//                int hasilSubTotal = (int) (copyDiskon-jum);
                
                lbDiskonJual.setText(""+jum);
                txSubTotal.setText("Rp. "+dfo.format(jumlah));
//                if (txJumlahBarangJual1.getText().equals("0")) {
//                    txSubTotalBarangJual.setText("Rp. "+dfo.format(hasilSubTotal));
//                } else 
                txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
                txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal1() {
            int harga1 = Integer.parseInt(txHargaBarangJual1.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy1.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon1 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang1+"'");
                while(rs.next()) {                         
                    diskon1 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon1);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual1.setText(""+jum1);
            txSubTotal1.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal2() {
            int harga1 = Integer.parseInt(txHargaBarangJual2.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy2.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon1 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang2+"'");
                while(rs.next()) {                         
                    diskon1 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon1);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual2.setText(""+jum1);
            txSubTotal2.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal3() {
            int harga1 = Integer.parseInt(txHargaBarangJual3.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy3.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang3+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual3.setText(""+jum1);
            txSubTotal3.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal4() {
            int harga1 = Integer.parseInt(txHargaBarangJual4.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy4.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang4+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual4.setText(""+jum1);
            txSubTotal4.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal5() {
            int harga1 = Integer.parseInt(txHargaBarangJual5.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy5.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang5+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon6+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual5.setText(""+jum1);
            txSubTotal5.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal6() {
            int harga1 = Integer.parseInt(txHargaBarangJual6.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy6.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang6+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon7+copyDiskon8+copyDiskon9);
            
            lbDiskonJual6.setText(""+jum1);
            txSubTotal6.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal7() {
            int harga1 = Integer.parseInt(txHargaBarangJual7.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy7.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang7+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon8+copyDiskon9);
            
            lbDiskonJual7.setText(""+jum1);
            txSubTotal7.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal8() {
            int harga1 = Integer.parseInt(txHargaBarangJual8.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy8.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon9 = Integer.parseInt(lbDiskonJual9.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang8+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon9);
            
            lbDiskonJual8.setText(""+jum1);
            txSubTotal8.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private void cekSubTotal9() {
            int harga1 = Integer.parseInt(txHargaBarangJual9.getText());
            long textJumlah1 = Integer.parseInt(txJumlahBarangJualCopy9.getText());
            int copyDiskon = Integer.parseInt(lbDiskonJual.getText());
            int copyDiskon1 = Integer.parseInt(lbDiskonJual1.getText());
            int copyDiskon2 = Integer.parseInt(lbDiskonJual2.getText());
            int copyDiskon3 = Integer.parseInt(lbDiskonJual3.getText());
            int copyDiskon4 = Integer.parseInt(lbDiskonJual4.getText());
            int copyDiskon5 = Integer.parseInt(lbDiskonJual5.getText());
            int copyDiskon6 = Integer.parseInt(lbDiskonJual6.getText());
            int copyDiskon7 = Integer.parseInt(lbDiskonJual7.getText());
            int copyDiskon8 = Integer.parseInt(lbDiskonJual8.getText());
            
            String diskon2 = null;             
            try {                
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang9+"'");
                while(rs.next()) {                         
                    diskon2 = rs.getString("DISKON"); 
                }
            } catch (SQLException e) { }
            
            diskonParse1 = Integer.parseInt(diskon2);
            total1 = total1+harga1;
            
            float bayar1 = diskonParse1/100*total1;
            hasilDiskon1 = total1-bayar1;
            jumlah1 = hasilDiskon1*textJumlah1;
            jum1 = (int) (hasilDiskon1*textJumlah1);
            subTotal = (long) (jum1+copyDiskon+copyDiskon1+copyDiskon2+copyDiskon3+copyDiskon4+copyDiskon5+copyDiskon6+copyDiskon7+copyDiskon8);
            
            lbDiskonJual9.setText(""+jum1);
            txSubTotal9.setText("Rp. "+dfo.format(jum1));
            txSubTotalBarangJual.setText("Rp. "+dfo.format(subTotal));
            txSubTotalBarangJualCopy.setText(""+subTotal);
        }
        
        private boolean validasiPembayaran() {   
//            double a;
//            double b;
//            double c;
            try {
                int a = Integer.parseInt(txSubTotalBarangJualCopy.getText());
//                b = Double.parseDouble(lbDiskonJual1.getText());
                float c = Float.parseFloat(lbCopyBayar.getText());        
                if ( c < a ) {
                    JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b<b>PERINGATAN</b>\nPembayaran Tidak Mencukupi","WARNING",
                            JOptionPane.WARNING_MESSAGE);
                    txBayarJual.requestFocus();
                    return true;
                } 
            } catch (NumberFormatException e) { }
           return false;
        }
        
        private boolean cekIDjual() {
            String idJual = txIDjual.getText();
            String id = null;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_JUAL FROM IBUED.JUAL WHERE ID_JUAL='"+idJual+"'");
                while(rs.next()) {
                    id = rs.getString("ID_JUAL");
                    return true;
                }
            } catch (SQLException ex) { }
            return false;
        }
        
        private void randomIDjual() {
            int num = 1;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.JUAL");
                while(rs.next()) {
                    num++;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            txIDjual.setText("JUAL"+"-"+num);
        }
        
    } // END CLASS
    
    private void jumlahBarangBeli() { 
        int banyak = 0;
        try {     
            banyak = Integer.parseInt(txBanyakBarang.getText());
        } catch (NumberFormatException ex) { }        
        if (banyak==2) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
//            lbDiskonJual1.setText("0");
//            txJumlahBarangJualCopy1.setText("0");
//            txtShadow2.setVisible(true);    
            btBanyakBarang.setEnabled(false);
        } else if (banyak==3) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==4) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==5) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==6) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(true);
            btGetIDbarangJual5.setVisible(true);
            txJumlahBarangJual5.setVisible(true);
            txSubTotal5.setVisible(true);
//            txtShadow6.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            
            txIDbarangJual5.setText(null);
            txJumlahBarangJual5.setText(null);
            txSubTotal5.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==7) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(true);
            btGetIDbarangJual5.setVisible(true);
            txJumlahBarangJual5.setVisible(true);
            txSubTotal5.setVisible(true);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(true);
            btGetIDbarangJual6.setVisible(true);
            txJumlahBarangJual6.setVisible(true);
            txSubTotal6.setVisible(true);
//            txtShadow7.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            
            txIDbarangJual5.setText(null);
            txJumlahBarangJual5.setText(null);
            txSubTotal5.setText("TOTAL");
            
            txIDbarangJual6.setText(null);
            txJumlahBarangJual6.setText(null);
            txSubTotal6.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==8) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(true);
            btGetIDbarangJual5.setVisible(true);
            txJumlahBarangJual5.setVisible(true);
            txSubTotal5.setVisible(true);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(true);
            btGetIDbarangJual6.setVisible(true);
            txJumlahBarangJual6.setVisible(true);
            txSubTotal6.setVisible(true);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(true);
            btGetIDbarangJual7.setVisible(true);
            txJumlahBarangJual7.setVisible(true);
            txSubTotal7.setVisible(true);
//            txtShadow8.setVisible(true);
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            
            txIDbarangJual5.setText(null);
            txJumlahBarangJual5.setText(null);
            txSubTotal5.setText("TOTAL");
            
            txIDbarangJual6.setText(null);
            txJumlahBarangJual6.setText(null);
            txSubTotal6.setText("TOTAL");
            
            txIDbarangJual7.setText(null);
            txJumlahBarangJual7.setText(null);
            txSubTotal7.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==9) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(true);
            btGetIDbarangJual5.setVisible(true);
            txJumlahBarangJual5.setVisible(true);
            txSubTotal5.setVisible(true);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(true);
            btGetIDbarangJual6.setVisible(true);
            txJumlahBarangJual6.setVisible(true);
            txSubTotal6.setVisible(true);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(true);
            btGetIDbarangJual7.setVisible(true);
            txJumlahBarangJual7.setVisible(true);
            txSubTotal7.setVisible(true);
//            txtShadow8.setVisible(true);
            txIDbarangJual8.setVisible(true);
            btGetIDbarangJual8.setVisible(true);
            txJumlahBarangJual8.setVisible(true);
            txSubTotal8.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            
            txIDbarangJual5.setText(null);
            txJumlahBarangJual5.setText(null);
            txSubTotal5.setText("TOTAL");
            
            txIDbarangJual6.setText(null);
            txJumlahBarangJual6.setText(null);
            txSubTotal6.setText("TOTAL");
            
            txIDbarangJual7.setText(null);
            txJumlahBarangJual7.setText(null);
            txSubTotal7.setText("TOTAL");
            
            txIDbarangJual8.setText(null);
            txJumlahBarangJual8.setText(null);
            txSubTotal8.setText("TOTAL");
            btBanyakBarang.setEnabled(false);
        } else if (banyak==10) {
            txIDbarangJual1.setVisible(true);
            btGetIDbarangJual1.setVisible(true);
            txJumlahBarangJual1.setVisible(true);
            txSubTotal1.setVisible(true);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(true);
            btGetIDbarangJual2.setVisible(true);
            txJumlahBarangJual2.setVisible(true);
            txSubTotal2.setVisible(true);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(true);
            btGetIDbarangJual3.setVisible(true);
            txJumlahBarangJual3.setVisible(true);
            txSubTotal3.setVisible(true);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(true);
            btGetIDbarangJual4.setVisible(true);
            txJumlahBarangJual4.setVisible(true);
            txSubTotal4.setVisible(true);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(true);
            btGetIDbarangJual5.setVisible(true);
            txJumlahBarangJual5.setVisible(true);
            txSubTotal5.setVisible(true);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(true);
            btGetIDbarangJual6.setVisible(true);
            txJumlahBarangJual6.setVisible(true);
            txSubTotal6.setVisible(true);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(true);
            btGetIDbarangJual7.setVisible(true);
            txJumlahBarangJual7.setVisible(true);
            txSubTotal7.setVisible(true);
//            txtShadow8.setVisible(true);
            txIDbarangJual8.setVisible(true);
            btGetIDbarangJual8.setVisible(true);
            txJumlahBarangJual8.setVisible(true);
            txSubTotal8.setVisible(true);    
            
            txIDbarangJual9.setVisible(true);
            btGetIDbarangJual9.setVisible(true);
            txJumlahBarangJual9.setVisible(true);
            txSubTotal9.setVisible(true);
            
            txIDbarangJual1.setText(null);
            txJumlahBarangJual1.setText(null);
            txSubTotal1.setText("TOTAL");
            
            txIDbarangJual2.setText(null);
            txJumlahBarangJual2.setText(null);
            txSubTotal2.setText("TOTAL");
            
            txIDbarangJual3.setText(null);
            txJumlahBarangJual3.setText(null);
            txSubTotal3.setText("TOTAL");
            
            txIDbarangJual4.setText(null);
            txJumlahBarangJual4.setText(null);
            txSubTotal4.setText("TOTAL");
            
            txIDbarangJual5.setText(null);
            txJumlahBarangJual5.setText(null);
            txSubTotal5.setText("TOTAL");
            
            txIDbarangJual6.setText(null);
            txJumlahBarangJual6.setText(null);
            txSubTotal6.setText("TOTAL");
            
            txIDbarangJual7.setText(null);
            txJumlahBarangJual7.setText(null);
            txSubTotal7.setText("TOTAL");
            
            txIDbarangJual8.setText(null);
            txJumlahBarangJual8.setText(null);
            txSubTotal8.setText("TOTAL");
            
            txIDbarangJual9.setText(null);
            txJumlahBarangJual9.setText(null);
            txSubTotal9.setText("TOTAL");
            btBanyakBarang.setEnabled(true);
        } else if (banyak<0) {          
            JOptionPane.showMessageDialog(null, "Banyak Barang Tidak Boleh Kurang Dari Nol (0)", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txBanyakBarang.setText("");
            txIDbarangJual1.setVisible(false);
            btGetIDbarangJual1.setVisible(false);
            txJumlahBarangJual1.setVisible(false);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(false);
            btGetIDbarangJual2.setVisible(false);
            txJumlahBarangJual2.setVisible(false);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(false);
            btGetIDbarangJual3.setVisible(false);
            txJumlahBarangJual3.setVisible(false);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(false);
            btGetIDbarangJual4.setVisible(false);
            txJumlahBarangJual4.setVisible(false);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(false);
            btGetIDbarangJual5.setVisible(false);
            txJumlahBarangJual5.setVisible(false);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(false);
            btGetIDbarangJual6.setVisible(false);
            txJumlahBarangJual6.setVisible(false);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(false);
            btGetIDbarangJual7.setVisible(false);
            txJumlahBarangJual7.setVisible(false);
//            txtShadow8.setVisible(true);
            txIDbarangJual8.setVisible(false);
            btGetIDbarangJual8.setVisible(false);
            txJumlahBarangJual8.setVisible(false);
            txIDbarangJual9.setVisible(false);
            btGetIDbarangJual9.setVisible(false);
            txJumlahBarangJual9.setVisible(false);            
            txSubTotal1.setVisible(false);
            txSubTotal2.setVisible(false);
            txSubTotal3.setVisible(false);
            txSubTotal4.setVisible(false);
            txSubTotal5.setVisible(false);
            txSubTotal6.setVisible(false);
            txSubTotal7.setVisible(false);
            txSubTotal8.setVisible(false);
            txSubTotal9.setVisible(false);
            btBanyakBarang.setEnabled(false);
        } else if (banyak>10) {
            JOptionPane.showMessageDialog(null, "Banyak Barang Hanya ( 10 Barang )\nJika Ingin Lebih Banyak Barang \nKlik Lebih Banyak Barang", "WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txBanyakBarang.setText("");
            btBanyakBarang.setEnabled(false);
            txIDbarangJual1.setVisible(false);
            btGetIDbarangJual1.setVisible(false);
            txJumlahBarangJual1.setVisible(false);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(false);
            btGetIDbarangJual2.setVisible(false);
            txJumlahBarangJual2.setVisible(false);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(false);
            btGetIDbarangJual3.setVisible(false);
            txJumlahBarangJual3.setVisible(false);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(false);
            btGetIDbarangJual4.setVisible(false);
            txJumlahBarangJual4.setVisible(false);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(false);
            btGetIDbarangJual5.setVisible(false);
            txJumlahBarangJual5.setVisible(false);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(false);
            btGetIDbarangJual6.setVisible(false);
            txJumlahBarangJual6.setVisible(false);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(false);
            btGetIDbarangJual7.setVisible(false);
            txJumlahBarangJual7.setVisible(false);
//            txtShadow8.setVisible(true);
            txIDbarangJual8.setVisible(false);
            btGetIDbarangJual8.setVisible(false);
            txJumlahBarangJual8.setVisible(false);
            txIDbarangJual9.setVisible(false);
            btGetIDbarangJual9.setVisible(false);
            txJumlahBarangJual9.setVisible(false);            
            txSubTotal1.setVisible(false);
            txSubTotal2.setVisible(false);
            txSubTotal3.setVisible(false);
            txSubTotal4.setVisible(false);
            txSubTotal5.setVisible(false);
            txSubTotal6.setVisible(false);
            txSubTotal7.setVisible(false);
            txSubTotal8.setVisible(false);
            txSubTotal9.setVisible(false);
        } else if (txBanyakBarang.getText().equals("0")) {
            txBanyakBarang.setText("1");
        } else {
            txIDbarangJual1.setVisible(false);
            btGetIDbarangJual1.setVisible(false);
            txJumlahBarangJual1.setVisible(false);
//            txtShadow2.setVisible(true);
            txIDbarangJual2.setVisible(false);
            btGetIDbarangJual2.setVisible(false);
            txJumlahBarangJual2.setVisible(false);
//            txtShadow3.setVisible(true);
            txIDbarangJual3.setVisible(false);
            btGetIDbarangJual3.setVisible(false);
            txJumlahBarangJual3.setVisible(false);
//            txtShadow4.setVisible(true);            
            txIDbarangJual4.setVisible(false);
            btGetIDbarangJual4.setVisible(false);
            txJumlahBarangJual4.setVisible(false);
//            txtShadow5.setVisible(true);            
            txIDbarangJual5.setVisible(false);
            btGetIDbarangJual5.setVisible(false);
            txJumlahBarangJual5.setVisible(false);
//            txtShadow6.setVisible(true);
            txIDbarangJual6.setVisible(false);
            btGetIDbarangJual6.setVisible(false);
            txJumlahBarangJual6.setVisible(false);
//            txtShadow7.setVisible(true);
            txIDbarangJual7.setVisible(false);
            btGetIDbarangJual7.setVisible(false);
            txJumlahBarangJual7.setVisible(false);
//            txtShadow8.setVisible(true);
            txIDbarangJual8.setVisible(false);
            btGetIDbarangJual8.setVisible(false);
            txJumlahBarangJual8.setVisible(false);
            txIDbarangJual9.setVisible(false);
            btGetIDbarangJual9.setVisible(false);
            txJumlahBarangJual9.setVisible(false); 
            txSubTotal1.setVisible(false);
            txSubTotal2.setVisible(false);
            txSubTotal3.setVisible(false);
            txSubTotal4.setVisible(false);
            txSubTotal5.setVisible(false);
            txSubTotal6.setVisible(false);
            txSubTotal7.setVisible(false);
            txSubTotal8.setVisible(false);
            txSubTotal9.setVisible(false);
            btBanyakBarang.setEnabled(false);
        }
    }
    
    private void initListenerTransaksiPenjualan() {
        
        btBanyakBarang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new dialogPenjualan(null, true).setVisible(true);
            }
        });
     
        txJumlahBarangJualCopy.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal();                          
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy.getText().equals("0")) {
                txSubTotal.setText("TOTAL");
            }            
        });
        
        txJumlahBarangJualCopy1.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal1();                          
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy1.getText().equals("0")) {
                txSubTotal1.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy2.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal2();                          
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy2.getText().equals("0")) {
                txSubTotal2.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy3.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal3();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy3.getText().equals("0")) {
                txSubTotal3.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy4.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal4();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy4.getText().equals("0")) {
                txSubTotal4.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy5.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal5();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy5.getText().equals("0")) {
                txSubTotal5.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy6.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal6();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy6.getText().equals("0")) {
                txSubTotal6.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy7.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal7();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy7.getText().equals("0")) {
                txSubTotal7.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy8.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal8();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy8.getText().equals("0")) {
                txSubTotal8.setText("TOTAL");
            }
        });
        
        txJumlahBarangJualCopy9.addTextListener((TextEvent e) -> {
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal9();
            } catch (Exception ex) { }
            if (txJumlahBarangJualCopy9.getText().equals("0")) {
                txSubTotal9.setText("TOTAL");
            }
        });   
        
        // CEK BUTTON RANDOM JUAL
        btRandomJual.addActionListener((ActionEvent e) -> {
            jualClass = new penjualan();
            jualClass.randomIDjual();
            txBayarJual.setText(null);
        });
        
        // CEK BUTTON SIMPAN PENJUALAN
        buttonToolTransaksiPenjualan.getSimpanData().addActionListener((ActionEvent e) -> {           
                try {                
                double iFor;                
                    iFor = (double) Double.parseDouble(txBayarJual.getText());               
                    txBayarJual.setText("Rp. "+dfoCek.format(iFor));              
                } catch (NumberFormatException ex) { }
                    simpanPenjualanEnter();  
                       
        });         
        
        // CEK BUTTON SEGARKAN PENJUALAN
        buttonToolTransaksiPenjualan.getSegarkan().addActionListener((ActionEvent e) -> {
            
        });       
        
        // CEK BUTTON GET ID PELANGGAN
        btGetIDplgJual.addActionListener((ActionEvent e) -> {
            idPlgJual = 1;
            try {
                new dialogIDpelanggan(this, true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btGetIDbarangJual.addActionListener((ActionEvent e) -> {
            getIDbrng();      
        });
        
        btGetIDbarangJual1.addActionListener((ActionEvent e) -> {
            getIDbrng1();
        });
        
        btGetIDbarangJual2.addActionListener((ActionEvent e) -> {
            getIDbrng2();
        });
        
        btGetIDbarangJual3.addActionListener((ActionEvent e) -> {
            getIDbrng3();
        });
        
        btGetIDbarangJual4.addActionListener((ActionEvent e) -> {
            getIDbrng4();
        });
        
        btGetIDbarangJual5.addActionListener((ActionEvent e) -> {
            getIDbrng5();
        });
        
        btGetIDbarangJual6.addActionListener((ActionEvent e) -> {
            getIDbrng6();
        });
        
        btGetIDbarangJual7.addActionListener((ActionEvent e) -> {
            getIDbrng7();
        });
        
        btGetIDbarangJual8.addActionListener((ActionEvent e) -> {
            getIDbrng8();
        });
        
        btGetIDbarangJual9.addActionListener((ActionEvent e) -> {
            getIDbrng9();
        });
        
        // CEK BAYAR 
        txBayarJual.addActionListener((ActionEvent e) -> {
            try {
                double iFor;                
                iFor = (double) Double.parseDouble(txBayarJual.getText());               
                txBayarJual.setText("Rp. "+dfoCek.format(iFor));              
            } catch (NumberFormatException ex) { }
            simpanPenjualanEnter();           
        });
        
        txIDjual.addActionListener((ActionEvent e) -> {
            getIDplg();
        });
        
        txIDplgJual.addActionListener((ActionEvent e) -> {
            getIDbrng();
        });        
        
    }
    
    private void getIDplg() {
        idPlgJual = 1;
        try {
            new dialogIDpelanggan(this, true).setVisible(true);
            txIDplgJual.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng() {
        idBrngJual = 1;              
        txIDbarangJual.setText(null);
        
        txIDbarangJual.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();            
           if (txIDbarangJual.getText().length()==0) {     
               txJumlahBarangJualCopy.setText("0");
                txJumlahBarangJual.setEditable(false);
                txJumlahBarangJual.setText(null);  
                txSubTotal.setText("TOTAL");
                lbDiskonJual.setText("0");                  
            } else if (txIDbarangJual.getText().equals(txIDbarangJual.getText())) {
                txJumlahBarangJual.setEditable(true);
            }
           try {
                jualClass = new penjualan();
                jualClass.cekSubTotal();                          
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng1() {
        idBrngJual1 = 2;      
        txIDbarangJual1.setText(null);            
        txIDbarangJual1.requestFocus();
//        if (txJumlahBarangJual1.getText().length()==0 && txJumlahBarangJualCopy1.getText().equals("0")) {
//            txSubTotal1.setText("TOTAL");
//        }
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJualCopy1.getText().equals("0") || txJumlahBarangJual1.getText().length()==0 || txIDbarangJual1.getText().equals(txIDbarangJual1.getText())) {
                txSubTotal1.setText("TOTAL");
                txJumlahBarangJual1.setEditable(true);
            } else if (txIDbarangJual1.getText().length()==0) {
                txJumlahBarangJual1.setEditable(false);
                txJumlahBarangJual1.setText(null);    
                txSubTotal1.setText("TOTAL");
            } 
//            else if (txIDbarangJual1.getText().equals(txIDbarangJual1.getText())) {
//                txJumlahBarangJual1.setEditable(true);
//            } 
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal1();                          
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng2() {
        idBrngJual2 = 3;      
        txIDbarangJual2.setText(null);
        txIDbarangJual2.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual2.getText().length()==0 || txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
                txSubTotal2.setText("TOTAL");
                txJumlahBarangJual2.setEditable(true);
            } else if (txIDbarangJual2.getText().length()==0) {
                txJumlahBarangJual2.setEditable(false);
                txJumlahBarangJual2.setText(null);    
                txSubTotal2.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal2();                          
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng3() {
        idBrngJual3 = 4;      
        txIDbarangJual3.setText(null);
        txIDbarangJual3.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual3.getText().length()==0 || txIDbarangJual3.getText().equals(txIDbarangJual3.getText())) {
                txSubTotal3.setText("TOTAL");
                txJumlahBarangJual3.setEditable(true);
            } else if (txIDbarangJual3.getText().length()==0) {
                txJumlahBarangJual3.setEditable(false);
                txJumlahBarangJual3.setText(null);    
                txSubTotal3.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal3();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng4() {
        idBrngJual4 = 5;      
        txIDbarangJual4.setText(null);
        txIDbarangJual4.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual4.getText().length()==0 || txIDbarangJual4.getText().equals(txIDbarangJual4.getText())) {
                txSubTotal4.setText("TOTAL");
                txJumlahBarangJual4.setEditable(true);
            } else if (txIDbarangJual4.getText().length()==0) {
                txJumlahBarangJual4.setEditable(false);
                txJumlahBarangJual4.setText(null);    
                txSubTotal4.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal4();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng5() {
        idBrngJual5 = 6;      
        txIDbarangJual5.setText(null);
        txIDbarangJual5.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual5.getText().length()==0 || txIDbarangJual5.getText().equals(txIDbarangJual5.getText())) {
                txSubTotal5.setText("TOTAL");
                txJumlahBarangJual5.setEditable(true);
            } else if (txIDbarangJual5.getText().length()==0) {
                txJumlahBarangJual5.setEditable(false);
                txJumlahBarangJual5.setText(null);    
                txSubTotal5.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal5();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng6() {
        idBrngJual6 = 7;      
        txIDbarangJual6.setText(null);
        txIDbarangJual6.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual6.getText().length()==0 || txIDbarangJual6.getText().equals(txIDbarangJual6.getText())) {
                txSubTotal6.setText("TOTAL");
                txJumlahBarangJual6.setEditable(true);
            } else if (txIDbarangJual6.getText().length()==0) {
                txJumlahBarangJual6.setEditable(false);
                txJumlahBarangJual6.setText(null);    
                txSubTotal6.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal6();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng7() {
        idBrngJual7 = 8;      
        txIDbarangJual7.setText(null);
        txIDbarangJual7.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual7.getText().length()==0 || txIDbarangJual7.getText().equals(txIDbarangJual7.getText())) {
                txSubTotal7.setText("TOTAL");
                txJumlahBarangJual7.setEditable(true);
            } else if (txIDbarangJual7.getText().length()==0) {
                txJumlahBarangJual7.setEditable(false);
                txJumlahBarangJual7.setText(null);    
                txSubTotal7.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal7();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng8() {
        idBrngJual8 = 9;      
        txIDbarangJual8.setText(null);
        txIDbarangJual8.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual8.getText().length()==0 || txIDbarangJual8.getText().equals(txIDbarangJual8.getText())) {
                txSubTotal8.setText("TOTAL");
                txJumlahBarangJual8.setEditable(true);
            } else if (txIDbarangJual8.getText().length()==0) {
                txJumlahBarangJual8.setEditable(false);
                txJumlahBarangJual8.setText(null);    
                txSubTotal8.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal8();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getIDbrng9() {
        idBrngJual9 = 9;      
        txIDbarangJual9.setText(null);
        txIDbarangJual9.requestFocus();
        try {
            new dialogIDbarang(this, true).setVisible(true);                
//            jualClass = new penjualan();                
//            jualClass.cekHargaDiskon();
            if (txJumlahBarangJual9.getText().length()==0 || txIDbarangJual9.getText().equals(txIDbarangJual9.getText())) {
                txSubTotal9.setText("TOTAL");
                txJumlahBarangJual9.setEditable(true);
            } else if (txIDbarangJual9.getText().length()==0) {
                txJumlahBarangJual9.setEditable(false);
                txJumlahBarangJual9.setText(null);    
                txSubTotal9.setText("TOTAL");
            } 
//            else if (txIDbarangJual2.getText().equals(txIDbarangJual2.getText())) {
//                txJumlahBarangJual2.setEditable(true);
//            }
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal9();
            } catch (Exception ex) { }
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void simpanPenjualanEnter() {    
        validasiSimpanPenjualan();              
        dataBarangSegarkan();
        segarkanQueryJual();
        segarkanQueryJualDetil();
    }
    
    private boolean validasiPembayaranKey(KeyEvent e) {        
        try {
            char c = e.getKeyChar(); 
            String bayar = txBayarJual.getText();
        
//        Pattern pattern = Pattern.compile("[a-zA-Z]*[0-9]*");
            Pattern pattern;
            pattern = Pattern.compile("[a-zA-Z]*[0-9]*");
            Matcher matcher = pattern.matcher(bayar);
            boolean b = matcher.matches();
           
             if (Character.isLetter(c)) {
                e.consume();
                JOptionPane.showMessageDialog(null, "Karakter Harus Berupa Angka","WARNING",
                        JOptionPane.WARNING_MESSAGE);     
                txBayarJual.setText(null);
                return true;
            } else if (b == false) {
//                e.consume();
//                JOptionPane.showMessageDialog(null, "Karakter Harus Berupa Angka,,","WARNING",
//                        JOptionPane.WARNING_MESSAGE);    
                txBayarJual.setText(null);
                return true;
            }
        } catch (HeadlessException ex) { }
        return false;
    }
    
    private boolean validasiSimpanPenjualan() {
        jualClass = new penjualan();  
        
        int banyakBrng = 0;
        try {
            banyakBrng = Integer.parseInt(txBanyakBarang.getText());
        } catch (NumberFormatException e) { }
        
        if (jualClass.cekIDjual()) {
            JOptionPane.showMessageDialog(null, "Data ID Jual Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDjual.requestFocus();
            return false;
        } else if (txIDjual.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Jual Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDjual.requestFocus();
            return false;
        } else if (txIDplgJual.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDplgJual.requestFocus();
            return false;        
        } else if (txBanyakBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Mohon, Masukkan Banyak Barang Dulu !!!","INFO",
                    JOptionPane.INFORMATION_MESSAGE); 
            txBanyakBarang.requestFocus();
            return false;
        }else if (banyakBrng==1) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } 
//            else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
//                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText())) {
//                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Sudah di Masukkan", "WARNING", 
//                        JOptionPane.WARNING_MESSAGE);
//                return false;
//            } 
            else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            }   else if (jualClass.cekStokBarang()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();   
                return false;
            }            
        } else if (banyakBrng==2) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            }  else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            } 
        } else if (banyakBrng==3) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            }  else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        } else if (banyakBrng==4) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            }  else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() || jualClass.cekStokBarang3()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }            
        } else if (banyakBrng==5) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            }  else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }            
        } else if (banyakBrng==6) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual5.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual2.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual6.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual5.requestFocus();
                return false;
            } else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4() || jualClass.cekStokBarang5()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        } else if (banyakBrng==7) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual5.requestFocus();
                return false;
            } else if (txJumlahBarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual5.requestFocus();
                return false;
            } else if (txIDbarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual6.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual2.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual6.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual6.requestFocus();
                return false;
            } else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4() || jualClass.cekStokBarang5()
                    || jualClass.cekStokBarang6()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        } else if (banyakBrng==8) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual5.requestFocus();
                return false;
            } else if (txJumlahBarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual5.requestFocus();
                return false;
            } else if (txIDbarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual6.requestFocus();
                return false;
            }  else if (txJumlahBarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual6.requestFocus();
                return false;
            } else if (txIDbarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual7.requestFocus();
                return false;
            }  else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual2.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual7.getText()) || 
                    txIDbarangJual2.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual6.getText().equals(txIDbarangJual7.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual7.requestFocus();
                return false;
            } else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4() || jualClass.cekStokBarang5()
                    || jualClass.cekStokBarang6() || jualClass.cekStokBarang7()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        } else if (banyakBrng==9) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual5.requestFocus();
                return false;
            } else if (txJumlahBarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual5.requestFocus();
                return false;
            } else if (txIDbarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual6.requestFocus();
                return false;
            }  else if (txJumlahBarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual6.requestFocus();
                return false;
            } else if (txIDbarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual7.requestFocus();
                return false;
            } else if (txJumlahBarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual7.requestFocus();
                return false;
            } else if (txIDbarangJual8.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual8.requestFocus();
                return false;
            } else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual2.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual7.getText()) || 
                    txIDbarangJual2.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual6.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual.getText().equals(txIDbarangJual8.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual5.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual6.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual7.getText().equals(txIDbarangJual8.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual8.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual8.requestFocus();
                return false;
            } else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4() || jualClass.cekStokBarang5()
                    || jualClass.cekStokBarang6() || jualClass.cekStokBarang7() || jualClass.cekStokBarang8()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        } else if (banyakBrng==10) {
            if (txIDbarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual.requestFocus();
                return false;
            } else if (txJumlahBarangJual.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual.requestFocus();
                return false;
            } else if (txIDbarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual1.requestFocus();
                return false;
            } else if (txJumlahBarangJual1.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual1.requestFocus();
                return false;
            } else if (txIDbarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual2.requestFocus();
                return false;
            } else if (txJumlahBarangJual2.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual2.requestFocus();
                return false;
            } else if (txIDbarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual3.requestFocus();
                return false;
            } else if (txJumlahBarangJual3.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual3.requestFocus();
                return false;
            } else if (txIDbarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual4.requestFocus();
                return false;
            } else if (txJumlahBarangJual4.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual4.requestFocus();
                return false;
            } else if (txIDbarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual5.requestFocus();
                return false;
            } else if (txJumlahBarangJual5.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual5.requestFocus();
                return false;
            } else if (txIDbarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual6.requestFocus();
                return false;
            }  else if (txJumlahBarangJual6.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual6.requestFocus();
                return false;
            } else if (txIDbarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual7.requestFocus();
                return false;
            } else if (txJumlahBarangJual7.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual7.requestFocus();
                return false;
            } else if (txIDbarangJual8.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual8.requestFocus();
                return false;
            }  else if (txJumlahBarangJual8.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual8.requestFocus();
                return false;
            } else if (txIDbarangJual9.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txIDbarangJual9.requestFocus();
                return false;
            }  else if (txIDbarangJual.getText().equals(txIDbarangJual1.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual3.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual2.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual3.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual3.getText()) || txIDbarangJual.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual4.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual4.getText()) || txIDbarangJual.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual5.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual5.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual2.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual6.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual6.getText()) ||
                    txIDbarangJual.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual1.getText().equals(txIDbarangJual7.getText()) || 
                    txIDbarangJual2.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual3.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual4.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual5.getText().equals(txIDbarangJual7.getText()) ||
                    txIDbarangJual6.getText().equals(txIDbarangJual7.getText()) || txIDbarangJual.getText().equals(txIDbarangJual8.getText()) || 
                    txIDbarangJual1.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual5.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual6.getText().equals(txIDbarangJual8.getText()) ||
                    txIDbarangJual7.getText().equals(txIDbarangJual8.getText()) || txIDbarangJual.getText().equals(txIDbarangJual9.getText()) ||
                    txIDbarangJual1.getText().equals(txIDbarangJual9.getText()) || txIDbarangJual2.getText().equals(txIDbarangJual9.getText()) ||
                    txIDbarangJual3.getText().equals(txIDbarangJual9.getText()) || txIDbarangJual4.getText().equals(txIDbarangJual9.getText()) ||
                    txIDbarangJual5.getText().equals(txIDbarangJual9.getText()) || txIDbarangJual6.getText().equals(txIDbarangJual9.getText()) ||
                    txIDbarangJual7.getText().equals(txIDbarangJual9.getText()) || txIDbarangJual8.getText().equals(txIDbarangJual9.getText())) {
                JOptionPane.showMessageDialog(null, "Maaf, ID Barang Tidak Boleh Sama", "WARNING", 
                        JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (txJumlahBarangJual9.getText().length()==0) {
                JOptionPane.showMessageDialog(null, "Mohon, Masukkan Jumlah Barang Dulu !!!","INFO",
                JOptionPane.INFORMATION_MESSAGE); 
                txJumlahBarangJual9.requestFocus();
                return false;
            } else if (jualClass.cekStokBarang() || jualClass.cekStokBarang1() || jualClass.cekStokBarang2() 
                    || jualClass.cekStokBarang3() || jualClass.cekStokBarang4() || jualClass.cekStokBarang5()
                    || jualClass.cekStokBarang6() || jualClass.cekStokBarang7() || jualClass.cekStokBarang8() 
                    || jualClass.cekStokBarang9()) {
                return false;
            } else {
                validasiKomponenTransaksiJual();
                return false;
            }
        }
        return true;
    }
    
    private boolean validasiKomponenTransaksiJual() {
        int a = 0;
        try {
            a = Integer.parseInt(txBanyakBarang.getText());
        } catch (NumberFormatException e) {
        }
        if (txBayarJual.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Mohon, Masukkan Pembayaran Dulu !!!","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txBayarJual.requestFocus();
            return false;
        } else if (jualClass.validasiPembayaran()) {
            return false;
        } else {
            try {
                if (a==1) {
//                    jualClass.transaksiJual();
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==2) {
//                    jualClass.transaksiJual1();
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==3) {
//                    jualClass.transaksiJual2();
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==4) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==5) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==6) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==7) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==8) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==9) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                } else if (a==10) {
                    jualClass.hasilTransaksiJual();
                    jualClass.tambahJual();
                    jualClass.tambahJualDetil();            
                    jualClass.cekEditStok(); 
                    buttonToolTransaksiPenjualan.getSimpanData().requestFocus();
                    return false;
                }
//                return false;
            } catch (Exception e) { }
//            return false;
        }
        return true;
    }    
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD KASIR ===================================================
    ||                                                                                                                                          */
    private class updateDariDialogPengaturanKasir { // MEMBUAT CLASS DIALOG PENGATURAN KASIR DIDALAM PAKET
        dialogPengaturanKasir pengaturanKasir;
        private void updatePengaturanKasir() {
            try {
                
                pengaturanKasir = new dialogPengaturanKasir(null, true);
                
                pengaturanKasir.btRandomIDkas.setEnabled(false);
                pengaturanKasir.btSimpanDataKasir.setVisible(false);
                pengaturanKasir.btBatalAdmin.setVisible(false);
                pengaturanKasir.btEditDataKas.setVisible(false);
                pengaturanKasir.btHapusDataKas.setVisible(false);
                pengaturanKasir.btSegarkanKas.setVisible(false);                        
                pengaturanKasir.idKasPengaturan = lbIDkasir.getText();
                pengaturanKasir.setVisible(true);                
                btLoginKasir.setVisible(true);
                jButton3.setVisible(true);
                
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR WHERE ID_KASIR='"+pengaturanKasir.txIDkasir.getText()+"'");
                while(resultSet.next()) {
                    lbUserKasir.setText(resultSet.getString(2));
                    lbUserKasirPengaturan.setText(resultSet.getString(4));
                    lbPasswdKasirPengaturan.setText(resultSet.getString(5));
                    txUsernameKasir.setText(resultSet.getString(4));
                    txPasswdKasir.setText(resultSet.getString(5));
                }
               
            } catch (SQLException ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // END CLASS
    
    private void kasir() {
        String lbUser   = lbUserKasirPengaturan.getText();
        String lbPasswd = lbPasswdKasirPengaturan.getText();
        
        if (txUsernameKasir.getText().length()==0) {
            lbInfoLogin.setText("USERNAME MASIH KOSONG !!!");
            txUsernameKasir.requestFocus();
        } else if (txPasswdKasir.getText().length()==0) {
            lbInfoLogin.setText("PASSWORD MASIH KOSONG !!!");
            txPasswdKasir.requestFocus();
        }
        else if (txUsernameKasir.getText().equals(lbUser) && txPasswdKasir.getText().equals(lbPasswd)) {
           lbInfoLogin.setText(null);
           btLoginKasir.setVisible(false);
           jButton3.setVisible(false);
            try {                
                // UPDATE DARI DIALOG PENGATURAN KASIR       
                
                ddpk = new updateDariDialogPengaturanKasir();                
                ddpk.updatePengaturanKasir();    
            } catch (Exception ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }         
        } else {
           lbInfoLogin.setText("MOHON ISIKAN DENGAN BENAR USERNAME & PASSWORD ANDA !!!");
       }
    }    
    
    private void initListenerKasir() {
        btLoginKasir.addActionListener((ActionEvent e) -> {
            kasir();
        });
        
        jButton3.addActionListener((ActionEvent e) -> {
            txUsernameKasir.setText("USERNAME");        
            txUsernameKasir.setShadow(true);
            txUsernameKasir.setShadowTextColor(Color.GRAY);
            
            txPasswdKasir.setText("PASSWORD");        
            txPasswdKasir.setShadow(true);
            txPasswdKasir.setShadowTextColor(Color.GRAY);
            
            lbInfoLogin.setText(null);
        });
        
        txUsernameKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                hurufBalok(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txPasswdKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txUsernameKasir.getText().equals(txUsernameKasir.getText())) {
                    lbInfoLogin.setText(null);
                }
            }
        });
        
        txPasswdKasir.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                hurufBalok(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btLoginKasir.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txPasswdKasir.getText().equals(txPasswdKasir.getText())) {
                    lbInfoLogin.setText(null);
                }
            }
        });     
    }
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD PELANGGAN ===================================================
    ||                                                                                                                                          */    
    private class IDPelanggan { // MEMBUAT CLASS ID PELANGGAN DI DALAM PAKET FRAME
        
        String cekIDplg = txIDpelanggan.getText();
        String cekIDplg1 = null;
        private boolean cekIDpelanggan() {
            boolean cek = false;
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_PELANGGAN FROM IBUED.PELANGGAN "
                        + "WHERE ID_PELANGGAN='"+cekIDplg+"'");
                while(resultSet.next()) {
                    cekIDplg1 = resultSet.getString("ID_PELANGGAN");
                    cek=true;
                }            
            } catch (SQLException e) { }              
            return cek;
        }
        
        private void cekKesalahanIDplg() {
            boolean cek = false;            
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_PELANGGAN FROM IBUED.PELANGGAN "
                        + "WHERE ID_PELANGGAN='"+cekIDplg+"'");
                while(resultSet.next()) {
                    cekIDplg1 = resultSet.getString("ID_PELANGGAN");
                    cek=true;
                }            
            } catch (SQLException e) { }   
            
            if (cekIDplg.equals(cekIDplg1)) {
                lbDeteksiIDpelanggan.setVisible(true);
                lbDeteksiIDpelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));               
                cek=true;
            } else if (cekIDplg.length()==0) {
                lbDeteksiIDpelanggan.setVisible(false);
                cek=false;
            } else {
                lbDeteksiIDpelanggan.setVisible(false);
                cek=true;
            }
        }
    } // END CLASS
    
    private class selectTabelPelanggan implements ListSelectionListener { // MEMBUAT CLASS MEMILIH TABEL PELANGGAN DIDALAM PAKET FRAME

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                txIDpelanggan.setEditable(false);
                btSimpan.setEnabled(false);
                lbDeteksiIDpelanggan.setVisible(true);   
                
                txCekRecord.setText(modelDataPelanggan.fieldPlg.get(n).getIDpelanggan());
                txIDpelanggan.setText(modelDataPelanggan.fieldPlg.get(n).getIDpelanggan());
                txNamaPelanggan.setText(modelDataPelanggan.fieldPlg.get(n).getNamaPelanggan());
                if (modelDataPelanggan.fieldPlg.get(n).getJenisKelamin().equals("LAKI - LAKI")) {
                    rbCowokPlg.setSelected(true);
                } else {
                    rbCewekPlg.setSelected(true);
                }
                txAreaPelanggan.setText(modelDataPelanggan.fieldPlg.get(n).getAlamatPelanggan());
                txTeleponPelanggan.setText(modelDataPelanggan.fieldPlg.get(n).getTeleponPelanggan());
            }
        }
        
    } // END CLASS        
    
    private void initListenerPelanggan() {        
        
        // HAPUS FORM PELANGGAN
        btHapus.addActionListener((ActionEvent e) -> {
            hapusFormPelanggan();
        });
        
        // SIMPAN DATA PELANGGAN
        btSimpan.addActionListener((ActionEvent e) -> {
            if (validasiSimpanPelanggan()) {
                try {                    
                    
                    dtPelanggan.setIDpelanggan(txIDpelanggan.getText());
                    dtPelanggan.setNamaPelanggan(txNamaPelanggan.getText());
                    if (rbCowokPlg.isSelected()) {
                        dtPelanggan.setJenisKelamin(rbCowokPlg.getText());
                    } else {
                        dtPelanggan.setJenisKelamin(rbCewekPlg.getText());
                    }
                    dtPelanggan.setAlamatPelanggan(txAreaPelanggan.getText());
                    dtPelanggan.setTeleponPelanggan(txTeleponPelanggan.getText());
                    
                    if (pelanggan.tambahData(dtPelanggan)) {
                        dataPelanggan.insert(dtPelanggan);
                        dataPelangganSegarkan();
                    }                       
                    
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }                               
            }
        });
        
        // CEK ID PELANGGAN
        txIDpelanggan.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txNamaPelanggan.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                iDPelanggan = new IDPelanggan();
                iDPelanggan.cekKesalahanIDplg();
            }
        });
        
        // CEK NAMA PELANGGAN
        txNamaPelanggan.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txAreaPelanggan.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        // CEK ALAMAT PELANGGAN
        txAreaPelanggan.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txTeleponPelanggan.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });       
        
        // SEGARKAN
        buttonToolPelanggan.getSegarkan().addActionListener((ActionEvent e) -> {
            dataPelangganSegarkan();
        });
        
        // EDIT DATA PELANGGAN
        buttonToolPelanggan.getEditData().addActionListener((ActionEvent e) -> {
            String idPlg = txIDpelanggan.getText();
            try {
                if (validasiEditPelanggan()) {
                    dtPelanggan.setIDpelanggan(txIDpelanggan.getText());
                    dtPelanggan.setNamaPelanggan(txNamaPelanggan.getText());
                    if (rbCowokPlg.isSelected()) {
                        dtPelanggan.setJenisKelamin(rbCowokPlg.getText());
                    } else {
                        dtPelanggan.setJenisKelamin(rbCewekPlg.getText());
                    }
                    dtPelanggan.setAlamatPelanggan(txAreaPelanggan.getText());
                    dtPelanggan.setTeleponPelanggan(txTeleponPelanggan.getText());
                    
                    pelanggan.editDataPelanggan(dtPelanggan, idPlg);                        
                    dataPelangganSegarkan();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        
        //HAPUS DATA PELANGGAN
        buttonToolPelanggan.getHapusData().addActionListener((ActionEvent e) -> {
            String idP = txIDpelanggan.getText();         
            
            if (validasiHapusPelanggan()) {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Pelanggan "+idP+"?","Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        pelanggan.hapusPelanggan(idP);
                        dataPelangganSegarkan();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }     
                }
            }                        
        });
        
    }    
    
    private boolean validasiSimpanPelanggan() {
        iDPelanggan = new IDPelanggan();
        if (iDPelanggan.cekIDpelanggan()) {
            JOptionPane.showMessageDialog(null, "Data ID Pelanggan Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDpelanggan.requestFocus();
            return false;
        } else if (txIDpelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDpelanggan.requestFocus();
            return false;
        } else if (txNamaPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaPelanggan.requestFocus();
            return false;
        } else if (rbCowokPlg.isSelected()==false && rbCewekPlg.isSelected()==false) {
            JOptionPane.showMessageDialog(null, "Pilih Jenis Kelamin Dulu","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (txAreaPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Alamat Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txAreaPelanggan.requestFocus();
            return false;
        } else if (txTeleponPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Telepon Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTeleponPelanggan.requestFocus();
            return false;
        } else if (txTeleponPelanggan.getText().length()>=13) {
            JOptionPane.showMessageDialog(null, "Karakter Telepon Max 12 Angka","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTeleponPelanggan.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validasiEditPelanggan() {
        if (tabelPelanggan.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Pelanggan Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekRecord.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }  else if (txNamaPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaPelanggan.requestFocus();
            return false;
        } else if (rbCowokPlg.isSelected()==false && rbCewekPlg.isSelected()==false) {
            JOptionPane.showMessageDialog(null, "Pilih Jenis Kelamin Dulu","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (txAreaPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Alamat Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txAreaPelanggan.requestFocus();
            return false;
        } else if (txTeleponPelanggan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Telepon Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTeleponPelanggan.requestFocus();
            return false;
        } else if (txTeleponPelanggan.getText().length()>=13) {
            JOptionPane.showMessageDialog(null, "Karakter Telepon Max 12 Angka","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txTeleponPelanggan.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validasiHapusPelanggan() {
        if (tabelPelanggan.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Pelanggan Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekRecord.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }    
    
    private void tampilDataPelanggan() {
        try {
            pelanggan = new dbPelanggan();
            pelanggan.tampilDataPelanggan();
        } catch (Exception e) { }
    }
    
    private void dataPelangganSegarkan() {
        txIDpelanggan.setText(null);
        txNamaPelanggan.setText(null);
        txAreaPelanggan.setText(null);
        txTeleponPelanggan.setText(null);
        txCekRecord.setText(null);
        btSimpan.setEnabled(true);
        txIDpelanggan.setEditable(true);
        lbDeteksiIDpelanggan.setVisible(false);
        
        row = tabelPelanggan.getRowCount();
        for (int i = 0; i < row; i++) {
            dataPelanggan.delete(0, row);
        }      
        pelanggan.tampilDataPelanggan();       
    }
    
    private void hapusFormPelanggan() {
        dataPelangganSegarkan();
    }
    
    private void tabelModelDataPlg() {
        tabelPelanggan.setModel(dataPelanggan);  
        lebarKolomTabelPlg(tabelPelanggan, new int[] {40,130,308,99,245,140} );
    }
    private void lebarKolomTabelPlg(javax.swing.JTable tb,int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);       
        int kolom = tb.getColumnCount();
        int i;
            for(i=0 ; i<kolom; i++) {
              javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
              tbc.setPreferredWidth(lebar[i]);
              tb.setRowHeight(27); 
            }      
    }
    
    private void selectTabelPlg() {
        ListSelectionModel lsm = tabelPelanggan.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelPelanggan());
    }    
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD BARANG ===================================================
    ||                                                                                                                                          */ 
    private class IDBarang { // MEMBUAT CLASS ID PELANGGAN DI DALAM PAKET FRAME
        
        String cekIDBrng = txIDbarang.getText();
        String cekIDbrng1 = null;
        private boolean cekIDbarang() {
            boolean cek = false;
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_BARANG FROM IBUED.BARANG "
                        + "WHERE ID_BARANG='"+cekIDBrng+"'");
                while(resultSet.next()) {
                    cekIDbrng1 = resultSet.getString("ID_BARANG");
                    cek=true;
                }            
            } catch (SQLException e) { }              
            return cek;
        }
        
        private void cekKesalahanIDbrng() {
            boolean cek = false;            
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_BARANG FROM IBUED.BARANG "
                        + "WHERE ID_BARANG='"+cekIDBrng+"'");
                while(resultSet.next()) {
                    cekIDbrng1 = resultSet.getString("ID_BARANG");
                    cek=true;
                }            
            } catch (SQLException e) { }   
            
            if (cekIDBrng.equals(cekIDbrng1)) {
                lbDeteksiIDbarang.setVisible(true);
                lbDeteksiIDbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png")));               
                cek=true;
            } else if (cekIDBrng.length()==0) {
                lbDeteksiIDbarang.setVisible(false);
                cek=false;
            } else {
                lbDeteksiIDbarang.setVisible(false);
                cek=true;
            }
        }
    } // END CLASS
    
    private class selectTabelBarang implements ListSelectionListener { // MEMBUAT CLASS MEMILIH TABEL PELANGGAN DIDALAM PAKET FRAME

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                txIDbarang.setEditable(false);
                btSimpanBarang.setEnabled(false);
                lbDeteksiIDbarang.setVisible(true);          
                
                txCekRecordBarang.setText(modelDataBarang.fieldBrng.get(n).getIDBarang());
                txIDbarang.setText(modelDataBarang.fieldBrng.get(n).getIDBarang());
                txNamaBarang.setText(modelDataBarang.fieldBrng.get(n).getNamaBarang());               
                txIDkategori.setText(modelDataBarang.fieldBrng.get(n).getIDkategori());
                txIDsatuan.setText(modelDataBarang.fieldBrng.get(n).getIDsatuan());
                txHargaBarang.setText(modelDataBarang.fieldBrng.get(n).getHargaBarang());
                txStokBarang.setText(modelDataBarang.fieldBrng.get(n).getStokBarang());
                txDiskonBarang.setText(modelDataBarang.fieldBrng.get(n).getDiskonBarang());
//                txNamaBarang.setText(null);
            }
        }
        
    } // END CLASS
    
    private void initListenerBarang() {     
        
        // BUTTON GET ID KATEGORI
        btGetIDkat.addActionListener((ActionEvent e) -> {
            idKatDialog = 1;
            try {
                new dialogIDkategori(this, true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
            txIDkategori.requestFocus();
        });
        
        // BUTTON GET ID SATUAN
        btGetIDsat.addActionListener((ActionEvent e) -> {
            idSatDialog = 2;
            try {
                new dialogIDsatuan(this, true).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
            txIDsatuan.requestFocus();
        });
        
        // BUTTON SIMPAN BARANG
        btSimpanBarang.addActionListener((ActionEvent e) -> {
            if (validasiSimpanBarang()) {
                try {
                    barang = new barangImplement();
                    dtBarang.setIDbarang(txIDbarang.getText());                    
                    dtBarang.setNamaBarang(txNamaBarang.getText());
                    dtBarang.setHargaBarang(txHargaBarang.getText());
                    dtBarang.setStokBarang(txStokBarang.getText());
                    dtBarang.setIDkategori(txIDkategori.getText());
                    dtBarang.setIDsatuan(txIDsatuan.getText());
                    dtBarang.setDiskonBarang(txDiskonBarang.getText());
                    
                    if (barang.insert(dtBarang)) {
                        dataBarang.insert(dtBarang);
                        dataBarangSegarkan();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // BUTTON HAPUS BARANG
        btHapusBarang.addActionListener((ActionEvent e) -> {
            dataBarangSegarkan();
        });
        
        //BUTTON EDIT DATA BARANG
        buttonToolBarang.getEditData().addActionListener((ActionEvent e) -> {
            String idBarang = txIDbarang.getText();
            if (validasiEditBarang()) {
                try {
                    barang = new barangImplement();
                    dtBarang.setIDbarang(txIDbarang.getText());                    
                    dtBarang.setNamaBarang(txNamaBarang.getText());
                    dtBarang.setHargaBarang(txHargaBarang.getText());
                    dtBarang.setStokBarang(txStokBarang.getText());
                    dtBarang.setIDkategori(txIDkategori.getText());
                    dtBarang.setIDsatuan(txIDsatuan.getText());
                    dtBarang.setDiskonBarang(txDiskonBarang.getText());
                    
                    barang.update(dtBarang, idBarang);
                    dataBarangSegarkan();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // BUTTON HAPUS DATA BARANG
        buttonToolBarang.getHapusData().addActionListener((ActionEvent e) -> {
            String hapus = txIDbarang.getText();
            barang = new barangImplement();
            if (validasiHapusBarang()) {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Barang "+hapus+"?","Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        barang.delete(hapus);
                        dataBarangSegarkan();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }     
                }
            }
        });
        
        // BUTTON SEGARKAN DATA BARANG
        buttonToolBarang.getSegarkan().addActionListener((ActionEvent e) -> {
            dataBarangSegarkan();
        });
        
        // CEK ID BARANG
        txIDbarang.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txNamaBarang.requestFocus();
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {
                iDBarang = new IDBarang();
                iDBarang.cekKesalahanIDbrng();
            }
        });               
        
        txNamaBarang.addActionListener((ActionEvent e) -> {
            actionGetIDkat();
            txIDkategori.requestFocus();
        });
        
        txIDkategori.addActionListener((ActionEvent e) -> {
            actionGetIDsat();
            txIDsatuan.requestFocus();
        });
        
        txIDsatuan.addActionListener((ActionEvent e) -> {
            txHargaBarang.requestFocus();
        });  
        
        txHargaBarang.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                validasiAngka(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txStokBarang.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        txStokBarang.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                validasiAngka(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txDiskonBarang.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
        
    }    
    
    private void actionGetIDkat() {
        idKatDialog = 1;
        try {
            new dialogIDkategori(this, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void actionGetIDsat() {
        idSatDialog = 2;
        try {
            new dialogIDsatuan(this, true).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean validasiStokDiskonBarang() {
        try {
            int stok = Integer.parseInt(txStokBarang.getText());
            int diskon = Integer.parseInt(txDiskonBarang.getText());
            
            if (stok > 100) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang Tidak Boleh Lebih Dari 100","WARNING",
                        JOptionPane.WARNING_MESSAGE);
                txStokBarang.requestFocus();
                return true; 
            } else if (diskon > 100) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nDiskon Barang Tidak Boleh Lebih Dari 100","WARNING",
                        JOptionPane.WARNING_MESSAGE);
                txDiskonBarang.requestFocus();
                return true; 
            } else if (stok<=0) {
                JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang Tidak Bisa Kurang Dari 0","WARNING",
                        JOptionPane.WARNING_MESSAGE);
                txStokBarang.requestFocus();
                return true;            
            }
        } catch (NumberFormatException e) { }
        return false;
    }
    
    private boolean validasiSimpanBarang() {
//        int stok = Integer.parseInt(txStokBarang.getText());
//        int diskon = Integer.parseInt(txDiskonBarang.getText());
        iDBarang = new IDBarang();
        if (iDBarang.cekIDbarang()) {
            JOptionPane.showMessageDialog(null, "Data ID Barang Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDbarang.requestFocus();
            return false;
        } else if (txIDbarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDbarang.requestFocus();
            return false;
        } else if (txNamaBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaBarang.requestFocus();
            return false;
        } else if (txIDkategori.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDkategori.requestFocus();
            return false;
        } else if (txIDsatuan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Satuan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDsatuan.requestFocus();
            return false;
        } else if (txHargaBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Harga Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txHargaBarang.requestFocus();
            return false;
        } else if (txStokBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Stok Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txStokBarang.requestFocus();
            return false;
        } else if (txStokBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Stok Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txStokBarang.requestFocus();
            return false;
        } else if (txDiskonBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Diskon Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txDiskonBarang.requestFocus();
            return false;
        } else if (validasiStokDiskonBarang()) {
            return false;
        }
//        else if (diskon > 100) {
//            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nDiskon Barang Tidak Boleh Lebih Dari 100","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            txDiskonBarang.requestFocus();
//            return false; 
//        } else if (stok<=0) {
//            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang Tidak Bisa Kurang Dari 0","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            txStokBarang.requestFocus();
//            return false;            
//        }
        return true;
    }
    
    private boolean validasiEditBarang() {
//        int stok = Integer.parseInt(txStokBarang.getText());
//        int diskon = Integer.parseInt(txDiskonBarang.getText());
        if (tabelBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Barang Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekRecordBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txIDbarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Pelanggan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDbarang.requestFocus();
            return false;
        } else if (txNamaBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaBarang.requestFocus();
            return false;
        } else if (txHargaBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Harga Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txHargaBarang.requestFocus();
            return false;
        } else if (txStokBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Stok Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txStokBarang.requestFocus();
            return false;
        } else if (txDiskonBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Diskon Barang Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txDiskonBarang.requestFocus();
            return false;
        } else if (validasiStokDiskonBarang()) {
            return false;
        }
//        else if (stok > 100) {
//            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang Tidak Boleh Lebih Dari 100","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            txStokBarang.requestFocus();
//            return false; 
//        } else if (diskon > 100) {
//            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nDiskon Barang Tidak Boleh Lebih Dari 100","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            txDiskonBarang.requestFocus();
//            return false; 
//        } else if (stok<=0) {
//            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b<b>PERINGATAN</b>\nStok Barang Tidak Bisa Kurang Dari 0","WARNING",
//                    JOptionPane.WARNING_MESSAGE);
//            txStokBarang.requestFocus();
//            return false;            
//        }
        return true;
    }
    
    private boolean validasiHapusBarang() {
        if (tabelBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Barang Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekRecordBarang.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void tabelModelDataBrng() {
        tabelBarang.setModel(dataBarang);
        lebarKolomTabelBrng(tabelBarang, new int[] {40,110,262,110,110,140,90,96} );
    }
    private void lebarKolomTabelBrng(javax.swing.JTable tb,int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);       
        int kolom = tb.getColumnCount();
        int i;
            for(i=0 ; i<kolom; i++) {
              javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
              tbc.setPreferredWidth(lebar[i]);
              tb.setRowHeight(27); 
            }      
    }   
    
    private void selectTabelBrng() {
        ListSelectionModel lsm = tabelBarang.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelBarang());
    }
    
    private void dataBarangSegarkan() {  
        txIDbarang.setText(null);
        txNamaBarang.setText(null);
        txIDkategori.setText(null);
        txIDsatuan.setText(null);
        txHargaBarang.setText(null);
        txStokBarang.setText(null);     
        txCekRecordBarang.setText(null);
        txDiskonBarang.setText("0");
        lbDeteksiIDbarang.setVisible(false);
        btSimpanBarang.setEnabled(true);
        txIDbarang.setEditable(true);
        try {
            row = tabelBarang.getRowCount();
            for (int i = 0; i < row; i++) {
                dataBarang.delete(0, row);
            }      
            barang = new barangImplement();
            barang.tampilBarang();
        } catch (SQLException e) { }
    }
    
    private void tampilDataBarang() {
        try {
//            barang = new barangImplement();
            barang.tampilBarang();
        } catch (SQLException e) { }
    }  
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD KATEGORI ===================================================
    ||                                                                                                                                          */
    private class cekIDkategori { // MEMBUAT CLASS DI DALAM PAKET
        
        String idKategori = txIDkat.getText();
        String idKatT = null;
        
        private boolean idKat() {               
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_KATEGORI FROM IBUED.KATEGORI WHERE ID_KATEGORI='"+idKategori+"'");
                while(resultSet.next()) {
                    idKatT = resultSet.getString("ID_KATEGORI");
                    return true;
                }
            } catch (SQLException e) { }
            return false;
        }
        
        private void randomIdKat() { 
            int num = 1;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KATEGORI");
                while(rs.next()) {
                    num++;
                }
                dbKoneksi.conn.commit();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            txIDkat.setText("KAT"+"-"+num);     
        }
                
    } // END CLASS
    
    private class selectTabelKat implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                txIDkat.setEditable(false);
                btSimpanKat.setEnabled(false);       
                btRandomIDkat.setEnabled(false);
                
                txCekKategori.setText(modelKategori.fieldKategori.get(n).getIDkategori());
                txIDkat.setText(modelKategori.fieldKategori.get(n).getIDkategori());
                txNamaKat.setText(modelKategori.fieldKategori.get(n).getNamaKategori());                
            }
        }
        
    }  
    
    private void selectTabelKategori() {
        ListSelectionModel lsm = tabelKategoriBarang.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelKat());
    }
    
    private void initListenerKategori() {
        
        // CEK RANDOM ID KATEGORI
        btRandomIDkat.addActionListener((ActionEvent e) -> {
                iDkategori = new cekIDkategori();
                iDkategori.randomIdKat();           
        });
        
        // CEK BUTTON SIMPAN KATEGORI
        btSimpanKat.addActionListener((ActionEvent e) -> {
            if (validasiSimpanKat()) {
                try {
                    kategoriData.setIDkategori(txIDkat.getText());
                    kategoriData.setNamaKategori(txNamaKat.getText());
                   
                    if (katImplement.tambahKategori(kategoriData)) {
                       kategoriM.insert(kategoriData);
                       segarkanDataKategori();
                    }
                } catch (SQLException ex) { }
            }           
        });
        
        // CEK BUTTON HAPUS 
        btHapusKat.addActionListener((ActionEvent e) -> {
            segarkanDataKategori();
        });
        
        // CEK BUTTON EDIT DATA
        buttonToolKategori.getEditData().addActionListener((ActionEvent e) -> {
            String idKas = txIDkat.getText();
            if (validasiEditKat()) {
                try {
                    kategoriData.setNamaKategori(txNamaKat.getText());
                    if (katImplement.editKategori(kategoriData, idKas)) {
                        segarkanDataKategori();
                    }
                } catch (SQLException ex) { }
            }
        });
        
        // CEK BUTTON HAPUS DATA
        buttonToolKategori.getHapusData().addActionListener((ActionEvent e) -> {
            String idKat = txIDkat.getText();
            
            if (validasiHapusKat()) {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Kategori "+idKat+"?","Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        katImplement.hapusKategori(idKat);
                        segarkanDataKategori();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }     
                }
            }
        });
        
        //REFRESH DATA KATEGORI
        buttonToolKategori.getSegarkan().addActionListener((ActionEvent e) -> {
            segarkanDataKategori();
        });
        
        // CEK ID KATEGORI
        txIDkat.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                hurufBalok(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txNamaKat.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    private boolean validasiSimpanKat() {
        iDkategori = new cekIDkategori();
        if (iDkategori.idKat()) {
            JOptionPane.showMessageDialog(null, "ID Kategori Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDkat.requestFocus();
            return false;
        } else if (txIDkat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDkat.requestFocus();
            return false;
        } else if (txNamaKat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaKat.requestFocus();
            return false;
        }        
        return true;
    }
    
    private boolean validasiEditKat() {
        if (tabelKategoriBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Kategori Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekKategori.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);         
            return false;
        } else if (txNamaKat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaKat.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validasiHapusKat() {
        if (tabelKategoriBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Kategori Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekKategori.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);          
            return false;
        }
        return true;
    }
    
    private void tampilDataKategori() {
        try {
            row = tabelKategoriBarang.getRowCount();
            for (int i = 0; i < row; i++) {
                kategoriM.delete(0, row);
            }  
            barang = new barangImplement();
            barang.tampilIDkat();
        } catch (SQLException e) {
        }
    }
    
    private void tabelModelDataKategori() {
        kategoriM = new modelKategori();
        tabelKategoriBarang.setModel(kategoriM);
    }
    private void lebarKolomTabelKategori() {
        tabelKategoriBarang.setRowHeight(27);
        tabelKategoriBarang.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelKategoriBarang.getColumnModel().getColumn(1).setPreferredWidth(310);
        tabelKategoriBarang.getColumnModel().getColumn(2).setPreferredWidth(600);             
        tabelKategoriBarang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);       
    }
    
    private void segarkanDataKategori() {
        txCekKategori.setText(null);
        txIDkat.setText(null);
        txNamaKat.setText(null);
        txIDkat.setEditable(true);
        btSimpanKat.setEnabled(true);
        btRandomIDkat.setEnabled(true);
        try {
            row = tabelKategoriBarang.getRowCount();
            for (int i = 0; i < row; i++) {
                kategoriM.delete(0, row);
            }      
            barang = new barangImplement();
            barang.tampilIDkat();
        } catch (SQLException e) { }
    } 
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD SATUAN ===================================================
    ||                                                                                                                                          */
    private class cekIDsatuan { // MEMBUAT CLASS DI DALAM PAKET
        
        String idSatuan = txIDsat.getText();
        String idSatT = null;
        
        private boolean idSat() {               
            try {
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT ID_SATUAN FROM IBUED.SATUAN WHERE ID_SATUAN='"+idSatuan+"'");
                while(resultSet.next()) {
                    idSatT = resultSet.getString("ID_SATUAN");
                    return true;
                }
            } catch (SQLException e) { }
            return false;
        }
        
        private void randomIdSat() { 
            int num = 1;
            try {
                ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.SATUAN");
                while(rs.next()) {
                    num++;
                }
                dbKoneksi.conn.commit();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            txIDsat.setText("SAT"+"-"+num);     
        }
                
    } // END CLASS
    
    private class selectTabelSat implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                int n=lsm.getMinSelectionIndex();          
                
                txIDsat.setEditable(false);
                btSimpanSat.setEnabled(false);       
                btRandomIDsat.setEnabled(false);
                
                txCekSatuan.setText(modelSatuan.fieldSatuan.get(n).getIDSatuan());
                txIDsat.setText(modelSatuan.fieldSatuan.get(n).getIDSatuan());
                txNamaSat.setText(modelSatuan.fieldSatuan.get(n).getNamaSatuan());                
            }
        }
        
    }    
    private void selectTabelSatuan() {
        ListSelectionModel lsm = tabelSatuanBarang.getSelectionModel();
        lsm.addListSelectionListener(new selectTabelSat());
    }
    
    private void initListenerSatuan() {
        
        // CEK BUTTON RANDOM ID SATUAN
        btRandomIDsat.addActionListener((ActionEvent e) -> {
            iDsatuan = new cekIDsatuan();
            iDsatuan.randomIdSat();
        });
        
        // CEK BUTTON SIMPAN DATA SATUAN
        btSimpanSat.addActionListener((ActionEvent e) -> {
            if (validasiSimpanSat()) {
                satuanData.setIDsatuan(txIDsat.getText());
                satuanData.setNamaSatuan(txNamaSat.getText());
                if (satImplement.tambahDataSatuan(satuanData)) {
                    satuanM.insert(satuanData);
                    segarkanDataSatuan();
                }
            }
        });
        
        // CEK BUTTON HAPUS SATUAN
        btHapusSat.addActionListener((ActionEvent e) -> {
            segarkanDataSatuan();
        });
        
        // CEK BUTTON EDIT DATA SATUAN
        buttonToolSatuan.getEditData().addActionListener((ActionEvent e) -> {
            String edit = txIDsat.getText();
            if (validasiEditSat()) {
//                satuanM = new modelSatuan();
                satuanData.setNamaSatuan(txNamaSat.getText());
                
                if (satImplement.editDataSatuan(satuanData, edit)) {
                    satuanM.insert(satuanData);
                    segarkanDataSatuan();
//                    tampilDataSatuan();
                }
            }
        });
        
        // CEK HAPUS DATA SATUAN
        buttonToolSatuan.getHapusData().addActionListener((ActionEvent e) -> {
            String idSat = txIDsat.getText();
            
            if (validasiHapusSat()) {
                int konfirm = JOptionPane.showConfirmDialog(null, "Hapus Data Dengan ID Satuan "+idSat+"?","Question", 
                        JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (konfirm == JOptionPane.YES_NO_OPTION) {
                    try {
                        satImplement.hapusDataSatuan(idSat);
                        segarkanDataSatuan();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                                JOptionPane.ERROR_MESSAGE);
                    }     
                }
            }
        });
        
        //CEK BUTTON SEGARKAN
        buttonToolSatuan.getSegarkan().addActionListener((ActionEvent e) -> {
            segarkanDataSatuan();
        });
        
    }
    
    private boolean validasiSimpanSat() {
        iDsatuan = new cekIDsatuan();
        if (iDsatuan.idSat()) {
            JOptionPane.showMessageDialog(null, "ID Satuan Sudah Ada","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            txIDsat.requestFocus();
            return false;
        } else if (txIDsat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "ID Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txIDsat.requestFocus();
            return false;
        } else if (txNamaSat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Satuan Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaSat.requestFocus();
            return false;
        }        
        return true;
    }
    
    private boolean validasiEditSat() {
        if (tabelSatuanBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Satuan Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekSatuan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);         
            return false;
        } else if (txCekSatuan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);         
            return false;
        } else if (txNamaSat.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Nama Kategori Masih Kosong","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            txNamaKat.requestFocus();
            return false;
        }
        return true;
    }
    
    private boolean validasiHapusSat() {
        if (tabelSatuanBarang.getRowCount()==0) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b<b>PERINGATAN</b>\nTabel Satuan Kosong","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (txCekSatuan.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Pilih Record Dulu !!!","WARNING",
                    JOptionPane.WARNING_MESSAGE);         
            return false;
        }
        return true;
    }
    
    private void tabelModelDataSatuan() {
        satuanM = new modelSatuan();
        tabelSatuanBarang.setModel(satuanM);
    }
    
    private void tampilDataSatuan() {
        try {
            row = tabelSatuanBarang.getRowCount();
            for (int i = 0; i < row; i++) {
                satuanM.delete(0, row);
            } 
            barang = new barangImplement();
            barang.tampilIDsat();
        } catch (SQLException e) {
        }
    }
    
    private void segarkanDataSatuan() {
        txCekSatuan.setText(null);
        txIDsat.setText(null);
        txNamaSat.setText(null);
        btRandomIDsat.setEnabled(true);
        btSimpanSat.setEnabled(true);
        txIDsat.setEditable(true);
       try {
            row = tabelSatuanBarang.getRowCount();
            for (int i = 0; i < row; i++) {
                satuanM.delete(0, row);
            }      
            barang = new barangImplement();
            barang.tampilIDsat();
        } catch (SQLException e) {
        }
    }
    
    private void lebarKolomTabelSatuan() {
        tabelSatuanBarang.setRowHeight(27);
        tabelSatuanBarang.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabelSatuanBarang.getColumnModel().getColumn(1).setPreferredWidth(310);
        tabelSatuanBarang.getColumnModel().getColumn(2).setPreferredWidth(600);             
        tabelSatuanBarang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    }
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD MENU ADMIN ===================================================
    ||                                                                                                                                          */ 
    private void initListenerAdminDataKasir() {
        // BUTTON KASIR
        btAdminDataKasir.addActionListener((ActionEvent e) -> {
            dialogPengaturanKasir kasir;
            try {         
                ksr = new dbKasir();
                kasir = new dialogPengaturanKasir(this, true);
                kasir.btEditDataKasir.setVisible(false);
                kasir.btBatal.setVisible(false);      
                kasir.lbTampilBerdasarkanIDkasir.setText(null);
                ksr = new dbKasir();
                ksr.tampilKasirAdmin();              
                kasir.setVisible(true);        
            } catch (SQLException ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }            
        });
        
        // BUTTON ADMIN
        btAdminDataAdmin.addActionListener((ActionEvent e) -> {
            new dialogPengaturanAdmin(this, true).setVisible(true);
        });
        
    }
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD MENU ITEM ===================================================
    ||                                                                                                                                          */
    private void initListenerMenuItem() {
        menuLogOut.addActionListener((ActionEvent e) -> {
            try {
                frameLogin login = new frameLogin();
                login.setVisible(true);
                this.setVisible(false);          
            } catch (Exception ex) {
                Logger.getLogger(frameIndex.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        jMenuItem9.addActionListener((ActionEvent e) -> {
            new dialogAbout(null, true).setVisible(true);
        });
        
        menuRefresh.addActionListener((ActionEvent e) -> {
            segarkanQueryJual();
            segarkanQueryJualDetil();
            dataPelangganSegarkan();
            dataBarangSegarkan();
            segarkanDataKategori();
            segarkanDataSatuan();
        });
    }    
    
    /*                                                                                                                                          ||
        ============================================================ FUNGSI METHOD EVENTS ===================================================
    ||                                                                                                                                          */    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txCekRecord = new javax.swing.JTextField();
        txCekRecordBarang = new javax.swing.JTextField();
        txCekKategori = new javax.swing.JTextField();
        txCekSatuan = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txTotalBarangJual = new javax.swing.JTextField();
        txSubTotalBarangJualCopy = new javax.swing.JTextField();
        txHargaBarangJual = new javax.swing.JTextField();
        txDiskonBarangJual = new javax.swing.JTextField();
        txHargaBarangJual1 = new javax.swing.JTextField();
        txHargaBarangJual2 = new javax.swing.JTextField();
        txDiskonBarangJual1 = new javax.swing.JTextField();
        lbDiskonJual2 = new javax.swing.JLabel();
        lbDiskonJual1 = new java.awt.TextField();
        lbDiskonJual = new javax.swing.JLabel();
        lbDiskonJual3 = new javax.swing.JLabel();
        txHargaBarangJual3 = new javax.swing.JTextField();
        txHargaBarangJual4 = new javax.swing.JTextField();
        lbDiskonJual4 = new javax.swing.JLabel();
        lbDiskonJual5 = new javax.swing.JLabel();
        txHargaBarangJual5 = new javax.swing.JTextField();
        txHargaBarangJual6 = new javax.swing.JTextField();
        lbDiskonJual6 = new javax.swing.JLabel();
        txHargaBarangJual7 = new javax.swing.JTextField();
        lbDiskonJual7 = new javax.swing.JLabel();
        txHargaBarangJual8 = new javax.swing.JTextField();
        lbDiskonJual8 = new javax.swing.JLabel();
        lbDiskonJual9 = new javax.swing.JLabel();
        txHargaBarangJual9 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btAdminDataKasir = new javax.swing.JButton();
        btAdminDataAdmin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBeranda = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbUserKasir = new javax.swing.JLabel();
        panelTransaksiPenjualan = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txIDjual = new javax.swing.JTextField();
        btRandomJual = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txTglJual = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txIDkasirJual = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txIDplgJual = new javax.swing.JTextField();
        btGetIDplgJual = new javax.swing.JButton();
        txJumlahBarangJualCopy = new java.awt.TextField();
        txJumlahBarangJualCopy2 = new java.awt.TextField();
        txJumlahBarangJualCopy1 = new java.awt.TextField();
        txJumlahBarangJualCopy3 = new java.awt.TextField();
        txJumlahBarangJualCopy4 = new java.awt.TextField();
        txJumlahBarangJualCopy5 = new java.awt.TextField();
        txJumlahBarangJualCopy6 = new java.awt.TextField();
        txJumlahBarangJualCopy7 = new java.awt.TextField();
        txJumlahBarangJualCopy8 = new java.awt.TextField();
        txJumlahBarangJualCopy9 = new java.awt.TextField();
        jPanel5 = new javax.swing.JPanel();
        txSubTotalBarangJual = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txBayarJual = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        lbKembalianJual = new javax.swing.JLabel();
        buttonToolTransaksiPenjualan = new proyek.rpl.palette.komponen.buttonToolPenjualan();
        lbCopyKembalian = new javax.swing.JLabel();
        lbCopyBayar = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txBanyakBarang = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        txIDbarangJual = new javax.swing.JTextField();
        btGetIDbarangJual = new javax.swing.JButton();
        txIDbarangJual1 = new javax.swing.JTextField();
        btGetIDbarangJual1 = new javax.swing.JButton();
        btGetIDbarangJual2 = new javax.swing.JButton();
        txIDbarangJual2 = new javax.swing.JTextField();
        txIDbarangJual3 = new javax.swing.JTextField();
        btGetIDbarangJual3 = new javax.swing.JButton();
        txIDbarangJual4 = new javax.swing.JTextField();
        btGetIDbarangJual4 = new javax.swing.JButton();
        txIDbarangJual5 = new javax.swing.JTextField();
        btGetIDbarangJual5 = new javax.swing.JButton();
        txIDbarangJual6 = new javax.swing.JTextField();
        btGetIDbarangJual6 = new javax.swing.JButton();
        txIDbarangJual7 = new javax.swing.JTextField();
        btGetIDbarangJual7 = new javax.swing.JButton();
        txIDbarangJual8 = new javax.swing.JTextField();
        btGetIDbarangJual8 = new javax.swing.JButton();
        txJumlahBarangJual = new javax.swing.JTextField();
        txJumlahBarangJual1 = new javax.swing.JTextField();
        txJumlahBarangJual2 = new javax.swing.JTextField();
        txJumlahBarangJual3 = new javax.swing.JTextField();
        txJumlahBarangJual4 = new javax.swing.JTextField();
        txJumlahBarangJual5 = new javax.swing.JTextField();
        txJumlahBarangJual6 = new javax.swing.JTextField();
        txJumlahBarangJual7 = new javax.swing.JTextField();
        txJumlahBarangJual8 = new javax.swing.JTextField();
        txSubTotal = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal1 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal2 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal3 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal4 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal5 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal6 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal7 = new proyek.rpl.palette.komponen.txJumlahJual();
        txSubTotal8 = new proyek.rpl.palette.komponen.txJumlahJual();
        txIDbarangJual9 = new javax.swing.JTextField();
        btGetIDbarangJual9 = new javax.swing.JButton();
        txJumlahBarangJual9 = new javax.swing.JTextField();
        txSubTotal9 = new proyek.rpl.palette.komponen.txJumlahJual();
        jLabel35 = new javax.swing.JLabel();
        btBanyakBarang = new javax.swing.JButton();
        panelQueryPenjualanDetail = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabelQueryPenjualan = new javax.swing.JTable();
        btRefreshQueryJual = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        cbCariQuery = new javax.swing.JComboBox();
        txCariQueryJual = new javax.swing.JTextField();
        btHapusDataJual = new javax.swing.JButton();
        btCetakJual = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txCariQueryJualCopy = new java.awt.TextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelPenjualanDetail = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabelQueryPenjualanDetil = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        btRefreshQueryJualDetil = new javax.swing.JButton();
        btHapusDataJualDetil = new javax.swing.JButton();
        cekBokQueryDetil = new javax.swing.JCheckBox();
        panelDataPelanggan = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txIDpelanggan = new javax.swing.JTextField();
        txNamaPelanggan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbCowokPlg = new javax.swing.JRadioButton();
        rbCewekPlg = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txAreaPelanggan = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txTeleponPelanggan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btSimpan = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        buttonToolPelanggan = new proyek.rpl.palette.komponen.buttonTool();
        lbDeteksiIDpelanggan = new javax.swing.JLabel();
        panelDataKasir = new javax.swing.JPanel();
        btLoginKasir = new javax.swing.JButton();
        txPasswdKasir = new proyek.rpl.palette.komponen.passwdShadow();
        jButton3 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txUsernameKasir = new proyek.rpl.palette.komponen.txtShadow();
        lbInfoLogin = new javax.swing.JLabel();
        lbUserKasirPengaturan = new javax.swing.JLabel();
        lbPasswdKasirPengaturan = new javax.swing.JLabel();
        lbIDkasir = new javax.swing.JLabel();
        panelDataBarang = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txIDbarang = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txStokBarang = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btSimpanBarang = new javax.swing.JButton();
        btHapusBarang = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        buttonToolBarang = new proyek.rpl.palette.komponen.buttonTool();
        lbDeteksiIDbarang = new javax.swing.JLabel();
        txHargaBarang = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txDiskonBarang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txIDkategori = new javax.swing.JTextField();
        btGetIDkat = new javax.swing.JButton();
        txIDsatuan = new javax.swing.JTextField();
        btGetIDsat = new javax.swing.JButton();
        txNamaBarang = new javax.swing.JTextField();
        panelDataKategori = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txIDkat = new javax.swing.JTextField();
        txNamaKat = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btRandomIDkat = new javax.swing.JButton();
        btSimpanKat = new javax.swing.JButton();
        btHapusKat = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelKategoriBarang = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        buttonToolKategori = new proyek.rpl.palette.komponen.buttonTool();
        panelDataSatuan = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txIDsat = new javax.swing.JTextField();
        txNamaSat = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btRandomIDsat = new javax.swing.JButton();
        btSimpanSat = new javax.swing.JButton();
        btHapusSat = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelSatuanBarang = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        buttonToolSatuan = new proyek.rpl.palette.komponen.buttonTool();
        jPanel8 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        lbStatusAdmin = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lbStatusKasir = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuRefresh = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuLogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        jLabel39.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel39.setText("TOTAL DISKON");

        txTotalBarangJual.setEditable(false);
        txTotalBarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txTotalBarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txTotalBarangJual.setForeground(new java.awt.Color(204, 204, 204));
        txTotalBarangJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotalBarangJual.setCaretColor(new java.awt.Color(204, 204, 204));

        txHargaBarangJual.setEditable(false);
        txHargaBarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txHargaBarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txHargaBarangJual.setForeground(new java.awt.Color(204, 204, 204));

        txDiskonBarangJual.setEditable(false);
        txDiskonBarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txDiskonBarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txDiskonBarangJual.setForeground(new java.awt.Color(204, 204, 204));
        txDiskonBarangJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDiskonBarangJual.setCaretColor(new java.awt.Color(204, 204, 204));

        lbDiskonJual2.setText("0");

        lbDiskonJual1.setText("0");

        lbDiskonJual.setText("0");

        lbDiskonJual3.setText("0");

        lbDiskonJual4.setText("0");

        lbDiskonJual5.setText("0");

        lbDiskonJual6.setText("0");

        lbDiskonJual7.setText("0");

        lbDiskonJual8.setText("0");

        lbDiskonJual9.setText("0");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEM MINIMARKET");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 102, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)), "ADMIN", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        btAdminDataKasir.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btAdminDataKasir.setText("DATA KASIR");
        btAdminDataKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btAdminDataAdmin.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btAdminDataAdmin.setText("DATA ADMIN");
        btAdminDataAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btAdminDataKasir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAdminDataAdmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btAdminDataKasir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btAdminDataAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)), "KASIR", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        jTabbedPane1.setForeground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N

        panelBeranda.setBackground(new java.awt.Color(51, 102, 0));
        panelBeranda.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelBeranda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/icon.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("JL. RAYA NO.226 TAMBAKBOYO - TUBAN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(978, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/admin.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel5.setText("SELAMAT DATANG DI PROGRAM MINIMARKET");

        lbUserKasir.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbUserKasir.setForeground(new java.awt.Color(204, 204, 204));
        lbUserKasir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUserKasir.setText("KASIR");

        javax.swing.GroupLayout panelBerandaLayout = new javax.swing.GroupLayout(panelBeranda);
        panelBeranda.setLayout(panelBerandaLayout);
        panelBerandaLayout.setHorizontalGroup(
            panelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBerandaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbUserKasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBerandaLayout.setVerticalGroup(
            panelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBerandaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBerandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(panelBerandaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbUserKasir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("BERANDA  ", new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/home.png")), panelBeranda); // NOI18N

        panelTransaksiPenjualan.setBackground(new java.awt.Color(51, 102, 0));
        panelTransaksiPenjualan.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelTransaksiPenjualan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel30.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("FORM PENGISIAN TRANSAKSI PENJUALAN");

        jPanel3.setBackground(new java.awt.Color(51, 102, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "ID PENJUALAN", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel31.setText("ID JUAL");

        txIDjual.setBackground(new java.awt.Color(16, 29, 51));
        txIDjual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDjual.setForeground(new java.awt.Color(204, 204, 204));
        txIDjual.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDjual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txIDjualKeyTyped(evt);
            }
        });

        btRandomJual.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRandomJual.setText("RANDOM ID");
        btRandomJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel32.setText("TANGGAL JUAL");

        txTglJual.setBackground(new java.awt.Color(16, 29, 51));
        txTglJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txTglJual.setForeground(new java.awt.Color(204, 204, 204));
        txTglJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txTglJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txTglJualKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel33.setText("ID KASIR");

        txIDkasirJual.setEditable(false);
        txIDkasirJual.setBackground(new java.awt.Color(16, 29, 51));
        txIDkasirJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDkasirJual.setForeground(new java.awt.Color(204, 204, 204));
        txIDkasirJual.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel34.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel34.setText("ID PELANGGAN");

        txIDplgJual.setEditable(false);
        txIDplgJual.setBackground(new java.awt.Color(16, 29, 51));
        txIDplgJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDplgJual.setForeground(new java.awt.Color(204, 204, 204));
        txIDplgJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDplgJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDplgJualMouseClicked(evt);
            }
        });

        btGetIDplgJual.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDplgJual.setText("...");
        btGetIDplgJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31)
                            .addComponent(jLabel33)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txJumlahBarangJualCopy1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txJumlahBarangJualCopy2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txJumlahBarangJualCopy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txJumlahBarangJualCopy3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txJumlahBarangJualCopy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txJumlahBarangJualCopy5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txJumlahBarangJualCopy6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txJumlahBarangJualCopy8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txJumlahBarangJualCopy7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txJumlahBarangJualCopy9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txIDplgJual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGetIDplgJual, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txIDjual, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRandomJual))
                    .addComponent(txTglJual)
                    .addComponent(txIDkasirJual))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel31))
                            .addComponent(txIDjual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRandomJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel32))
                            .addComponent(txTglJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel33))
                            .addComponent(txIDkasirJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel34))
                            .addComponent(btGetIDplgJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(txIDplgJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(txJumlahBarangJualCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txJumlahBarangJualCopy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txJumlahBarangJualCopy9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(51, 102, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PEMBAYARAN BARANG", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        txSubTotalBarangJual.setEditable(false);
        txSubTotalBarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotalBarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txSubTotalBarangJual.setForeground(new java.awt.Color(204, 204, 204));
        txSubTotalBarangJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotalBarangJual.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel36.setText("SUB TOTAL");

        txBayarJual.setBackground(new java.awt.Color(16, 29, 51));
        txBayarJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txBayarJual.setForeground(new java.awt.Color(204, 204, 204));
        txBayarJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txBayarJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txBayarJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txBayarJualMouseClicked(evt);
            }
        });
        txBayarJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBayarJualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBayarJualKeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel42.setText("PEMBAYARAN");

        lbKembalianJual.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbKembalianJual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbKembalianJual.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "KEMBALIAN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        buttonToolTransaksiPenjualan.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(89, 89, 89)
                        .addComponent(txSubTotalBarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(74, 74, 74)
                        .addComponent(txBayarJual, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbKembalianJual, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(lbCopyKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(lbCopyBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(112, 112, 112))
                                .addComponent(buttonToolTransaksiPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel36))
                    .addComponent(txSubTotalBarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel42))
                    .addComponent(txBayarJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbKembalianJual, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(buttonToolTransaksiPenjualan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCopyBayar)
                .addGap(70, 70, 70)
                .addComponent(lbCopyKembalian)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(51, 102, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PENJUALAN BARANG", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 1, 12))); // NOI18N

        jLabel50.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel50.setText("BANYAK BARANG");

        txBanyakBarang.setBackground(new java.awt.Color(16, 29, 51));
        txBanyakBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txBanyakBarang.setForeground(new java.awt.Color(204, 204, 204));
        txBanyakBarang.setCaretColor(new java.awt.Color(204, 204, 204));
        txBanyakBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBanyakBarangKeyReleased(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(51, 102, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "ID BARANG", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        txIDbarangJual.setEditable(false);
        txIDbarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJualMouseClicked(evt);
            }
        });

        btGetIDbarangJual.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual.setText("...");
        btGetIDbarangJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual1.setEditable(false);
        txIDbarangJual1.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual1.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual1.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual1MouseClicked(evt);
            }
        });

        btGetIDbarangJual1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual1.setText("...");
        btGetIDbarangJual1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btGetIDbarangJual2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual2.setText("...");
        btGetIDbarangJual2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual2.setEditable(false);
        txIDbarangJual2.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual2.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual2.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual2MouseClicked(evt);
            }
        });

        txIDbarangJual3.setEditable(false);
        txIDbarangJual3.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual3.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual3.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual3MouseClicked(evt);
            }
        });

        btGetIDbarangJual3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual3.setText("...");
        btGetIDbarangJual3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual4.setEditable(false);
        txIDbarangJual4.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual4.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual4.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual4MouseClicked(evt);
            }
        });

        btGetIDbarangJual4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual4.setText("...");
        btGetIDbarangJual4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual5.setEditable(false);
        txIDbarangJual5.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual5.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual5.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual5MouseClicked(evt);
            }
        });

        btGetIDbarangJual5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual5.setText("...");
        btGetIDbarangJual5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual6.setEditable(false);
        txIDbarangJual6.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual6.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual6.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual6MouseClicked(evt);
            }
        });

        btGetIDbarangJual6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual6.setText("...");
        btGetIDbarangJual6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual7.setEditable(false);
        txIDbarangJual7.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual7.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual7.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual7MouseClicked(evt);
            }
        });
        txIDbarangJual7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txIDbarangJual7KeyPressed(evt);
            }
        });

        btGetIDbarangJual7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual7.setText("...");
        btGetIDbarangJual7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDbarangJual8.setEditable(false);
        txIDbarangJual8.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual8.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual8.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual8MouseClicked(evt);
            }
        });

        btGetIDbarangJual8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual8.setText("...");
        btGetIDbarangJual8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txJumlahBarangJual.setEditable(false);
        txJumlahBarangJual.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual.setToolTipText("JUMLAH BARANG");
        txJumlahBarangJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJualKeyTyped(evt);
            }
        });

        txJumlahBarangJual1.setEditable(false);
        txJumlahBarangJual1.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual1.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual1.setToolTipText("JUMLAH BARANG");
        txJumlahBarangJual1.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual1KeyTyped(evt);
            }
        });

        txJumlahBarangJual2.setEditable(false);
        txJumlahBarangJual2.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual2.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual2.setToolTipText("JUMLAH BARANG");
        txJumlahBarangJual2.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual2KeyTyped(evt);
            }
        });

        txJumlahBarangJual3.setEditable(false);
        txJumlahBarangJual3.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual3.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual3.setToolTipText("JUMLAH BARANG");
        txJumlahBarangJual3.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual3KeyTyped(evt);
            }
        });

        txJumlahBarangJual4.setEditable(false);
        txJumlahBarangJual4.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual4.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual4.setToolTipText("JUMLAH BARANG");
        txJumlahBarangJual4.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual4KeyTyped(evt);
            }
        });

        txJumlahBarangJual5.setEditable(false);
        txJumlahBarangJual5.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual5.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual5.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual5KeyTyped(evt);
            }
        });

        txJumlahBarangJual6.setEditable(false);
        txJumlahBarangJual6.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual6.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual6.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual6KeyTyped(evt);
            }
        });

        txJumlahBarangJual7.setEditable(false);
        txJumlahBarangJual7.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual7.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual7.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual7KeyTyped(evt);
            }
        });

        txJumlahBarangJual8.setEditable(false);
        txJumlahBarangJual8.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual8.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual8.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual8KeyTyped(evt);
            }
        });

        txSubTotal.setEditable(false);
        txSubTotal.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal.setForeground(java.awt.Color.lightGray);
        txSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal.setText("TOTAL");
        txSubTotal.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal1.setEditable(false);
        txSubTotal1.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal1.setForeground(java.awt.Color.lightGray);
        txSubTotal1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal1.setText("TOTAL");
        txSubTotal1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal2.setEditable(false);
        txSubTotal2.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal2.setForeground(java.awt.Color.lightGray);
        txSubTotal2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal2.setText("TOTAL");
        txSubTotal2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal3.setEditable(false);
        txSubTotal3.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal3.setForeground(java.awt.Color.lightGray);
        txSubTotal3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal3.setText("TOTAL");
        txSubTotal3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal4.setEditable(false);
        txSubTotal4.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal4.setForeground(java.awt.Color.lightGray);
        txSubTotal4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal4.setText("TOTAL");
        txSubTotal4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal5.setEditable(false);
        txSubTotal5.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal5.setForeground(java.awt.Color.lightGray);
        txSubTotal5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal5.setText("TOTAL");
        txSubTotal5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal6.setEditable(false);
        txSubTotal6.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal6.setForeground(java.awt.Color.lightGray);
        txSubTotal6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal6.setText("TOTAL");
        txSubTotal6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal7.setEditable(false);
        txSubTotal7.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal7.setForeground(java.awt.Color.lightGray);
        txSubTotal7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal7.setText("TOTAL");
        txSubTotal7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txSubTotal8.setEditable(false);
        txSubTotal8.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal8.setForeground(java.awt.Color.lightGray);
        txSubTotal8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal8.setText("TOTAL");
        txSubTotal8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        txIDbarangJual9.setEditable(false);
        txIDbarangJual9.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarangJual9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarangJual9.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarangJual9.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDbarangJual9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDbarangJual9MouseClicked(evt);
            }
        });

        btGetIDbarangJual9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDbarangJual9.setText("...");
        btGetIDbarangJual9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txJumlahBarangJual9.setEditable(false);
        txJumlahBarangJual9.setBackground(new java.awt.Color(16, 29, 51));
        txJumlahBarangJual9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txJumlahBarangJual9.setForeground(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txJumlahBarangJual9.setCaretColor(new java.awt.Color(204, 204, 204));
        txJumlahBarangJual9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual9KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txJumlahBarangJual9KeyTyped(evt);
            }
        });

        txSubTotal9.setEditable(false);
        txSubTotal9.setBackground(new java.awt.Color(16, 29, 51));
        txSubTotal9.setForeground(java.awt.Color.lightGray);
        txSubTotal9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txSubTotal9.setText("TOTAL");
        txSubTotal9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 102, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txIDbarangJual8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txIDbarangJual7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txIDbarangJual6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txIDbarangJual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(txIDbarangJual1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txIDbarangJual2)
                            .addComponent(txIDbarangJual3)
                            .addComponent(txIDbarangJual4)
                            .addComponent(txIDbarangJual5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txIDbarangJual9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(btGetIDbarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txJumlahBarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btGetIDbarangJual1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                .addComponent(btGetIDbarangJual2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                .addComponent(btGetIDbarangJual3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                .addComponent(btGetIDbarangJual4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txJumlahBarangJual1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addComponent(txJumlahBarangJual2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addComponent(txJumlahBarangJual3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addComponent(txJumlahBarangJual4)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btGetIDbarangJual5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btGetIDbarangJual6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txJumlahBarangJual5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addComponent(txJumlahBarangJual6))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(btGetIDbarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txJumlahBarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(btGetIDbarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txJumlahBarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btGetIDbarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txJumlahBarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txSubTotal1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txSubTotal9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txIDbarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txJumlahBarangJual1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSubTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txSubTotal9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txJumlahBarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGetIDbarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txIDbarangJual9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35))
        );

        btBanyakBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        btBanyakBarang.setText("LEBIH BANYAK BARANG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel50)
                .addGap(12, 12, 12)
                .addComponent(txBanyakBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btBanyakBarang)
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel50))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txBanyakBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btBanyakBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelTransaksiPenjualanLayout = new javax.swing.GroupLayout(panelTransaksiPenjualan);
        panelTransaksiPenjualan.setLayout(panelTransaksiPenjualanLayout);
        panelTransaksiPenjualanLayout.setHorizontalGroup(
            panelTransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiPenjualanLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTransaksiPenjualanLayout.setVerticalGroup(
            panelTransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiPenjualanLayout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTransaksiPenjualanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("TRANSAKSI PENJUALAN", panelTransaksiPenjualan);

        panelQueryPenjualanDetail.setBackground(new java.awt.Color(51, 102, 0));
        panelQueryPenjualanDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelQueryPenjualanDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane9.setOpaque(false);

        tabelQueryPenjualan.setBackground(new java.awt.Color(51, 51, 55));
        tabelQueryPenjualan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelQueryPenjualan.setForeground(new java.awt.Color(204, 204, 204));
        tabelQueryPenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelQueryPenjualan.setOpaque(false);
        tabelQueryPenjualan.setSelectionBackground(new java.awt.Color(51, 102, 0));
        tabelQueryPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelQueryPenjualanMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tabelQueryPenjualan);

        btRefreshQueryJual.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRefreshQueryJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/segarkan.png"))); // NOI18N
        btRefreshQueryJual.setText("SEGARKAN [F5]");
        btRefreshQueryJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel37.setText("CARI BERDASARKAN");

        cbCariQuery.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cbCariQuery.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- CARI --", "ID JUAL", "NAMA PELANGGAN", "NAMA KASIR", "TANGGAL JUAL" }));

        txCariQueryJual.setEditable(false);
        txCariQueryJual.setBackground(new java.awt.Color(16, 29, 51));
        txCariQueryJual.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txCariQueryJual.setForeground(new java.awt.Color(204, 204, 204));
        txCariQueryJual.setCaretColor(new java.awt.Color(204, 204, 204));
        txCariQueryJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCariQueryJualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCariQueryJualKeyTyped(evt);
            }
        });

        btHapusDataJual.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusDataJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapusData.png"))); // NOI18N
        btHapusDataJual.setText("HAPUS DATA");
        btHapusDataJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btCetakJual.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btCetakJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/print.PNG"))); // NOI18N
        btCetakJual.setText("CETAK");
        btCetakJual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel43.setText("CARI TANGGAL AWAL JUAL DAN AKHIR JUAL");

        jTextField1.setBackground(new java.awt.Color(16, 29, 51));
        jTextField1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel44.setText("ATAU");

        jTextField5.setBackground(new java.awt.Color(16, 29, 51));
        jTextField5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(204, 204, 204));
        jTextField5.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton1.setText("OK / INFO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelQueryPenjualanDetailLayout = new javax.swing.GroupLayout(panelQueryPenjualanDetail);
        panelQueryPenjualanDetail.setLayout(panelQueryPenjualanDetailLayout);
        panelQueryPenjualanDetailLayout.setHorizontalGroup(
            panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQueryPenjualanDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1320, Short.MAX_VALUE)
                    .addGroup(panelQueryPenjualanDetailLayout.createSequentialGroup()
                        .addComponent(btRefreshQueryJual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapusDataJual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCetakJual, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(panelQueryPenjualanDetailLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCariQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCariQueryJual, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCariQueryJualCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        panelQueryPenjualanDetailLayout.setVerticalGroup(
            panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelQueryPenjualanDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(cbCariQuery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txCariQueryJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txCariQueryJualCopy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btRefreshQueryJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btHapusDataJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelQueryPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCetakJual, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("QUERY PENJUALAN", panelQueryPenjualanDetail);

        panelPenjualanDetail.setBackground(new java.awt.Color(51, 102, 0));
        panelPenjualanDetail.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelPenjualanDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane8.setOpaque(false);

        tabelQueryPenjualanDetil.setBackground(new java.awt.Color(51, 51, 55));
        tabelQueryPenjualanDetil.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelQueryPenjualanDetil.setForeground(new java.awt.Color(204, 204, 204));
        tabelQueryPenjualanDetil.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelQueryPenjualanDetil.setOpaque(false);
        tabelQueryPenjualanDetil.setSelectionBackground(new java.awt.Color(51, 102, 0));
        tabelQueryPenjualanDetil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelQueryPenjualanDetilMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabelQueryPenjualanDetil);

        jLabel38.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel38.setText("CARI BERDASARKAN");

        jComboBox2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- CARI --", "ID JUAL", "NAMA PELANGGAN", "NAMA KASIR" }));

        jTextField2.setBackground(new java.awt.Color(16, 29, 51));
        jTextField2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextField2.setCaretColor(new java.awt.Color(204, 204, 204));

        btRefreshQueryJualDetil.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRefreshQueryJualDetil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/segarkan.png"))); // NOI18N
        btRefreshQueryJualDetil.setText("SEGARKAN [F5]");
        btRefreshQueryJualDetil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusDataJualDetil.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusDataJualDetil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapusData.png"))); // NOI18N
        btHapusDataJualDetil.setText("HAPUS DATA");
        btHapusDataJualDetil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cekBokQueryDetil.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        cekBokQueryDetil.setText("HAPUS SEMUA DATA");
        cekBokQueryDetil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekBokQueryDetil.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panelPenjualanDetailLayout = new javax.swing.GroupLayout(panelPenjualanDetail);
        panelPenjualanDetail.setLayout(panelPenjualanDetailLayout);
        panelPenjualanDetailLayout.setHorizontalGroup(
            panelPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenjualanDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1320, Short.MAX_VALUE)
                    .addGroup(panelPenjualanDetailLayout.createSequentialGroup()
                        .addComponent(btRefreshQueryJualDetil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHapusDataJualDetil)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelPenjualanDetailLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cekBokQueryDetil)))
                .addContainerGap())
        );
        panelPenjualanDetailLayout.setVerticalGroup(
            panelPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPenjualanDetailLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cekBokQueryDetil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPenjualanDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRefreshQueryJualDetil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btHapusDataJualDetil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("QUERY PENJUALAN DETAIL", panelPenjualanDetail);

        panelDataPelanggan.setBackground(new java.awt.Color(51, 102, 0));
        panelDataPelanggan.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelDataPelanggan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setText("ID PELANGGAN");

        txIDpelanggan.setBackground(new java.awt.Color(16, 29, 51));
        txIDpelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDpelanggan.setForeground(new java.awt.Color(204, 204, 204));
        txIDpelanggan.setCaretColor(new java.awt.Color(204, 204, 204));

        txNamaPelanggan.setBackground(new java.awt.Color(16, 29, 51));
        txNamaPelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txNamaPelanggan.setForeground(new java.awt.Color(204, 204, 204));
        txNamaPelanggan.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setText("NAMA PELANGGAN");

        buttonGroup1.add(rbCowokPlg);
        rbCowokPlg.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        rbCowokPlg.setText("LAKI - LAKI");
        rbCowokPlg.setName(""); // NOI18N
        rbCowokPlg.setOpaque(false);

        buttonGroup1.add(rbCewekPlg);
        rbCewekPlg.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        rbCewekPlg.setText("PEREMPUAN");
        rbCewekPlg.setName(""); // NOI18N
        rbCewekPlg.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel7.setText("JENIS KELAMIN");

        txAreaPelanggan.setBackground(new java.awt.Color(16, 29, 51));
        txAreaPelanggan.setColumns(20);
        txAreaPelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txAreaPelanggan.setForeground(new java.awt.Color(204, 204, 204));
        txAreaPelanggan.setRows(5);
        txAreaPelanggan.setCaretColor(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportView(txAreaPelanggan);

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel8.setText("ALAMAT");

        txTeleponPelanggan.setBackground(new java.awt.Color(16, 29, 51));
        txTeleponPelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txTeleponPelanggan.setForeground(new java.awt.Color(204, 204, 204));
        txTeleponPelanggan.setCaretColor(new java.awt.Color(204, 204, 204));
        txTeleponPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txTeleponPelangganKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel9.setText("TELEPON");

        btSimpan.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/simpan.PNG"))); // NOI18N
        btSimpan.setText("SIMPAN");
        btSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapus.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapus.png"))); // NOI18N
        btHapus.setText("HAPUS");
        btHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("FORM PENGISIAN DATA PELANGGAN");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DAFTAR DATA PELANGGAN");

        jScrollPane2.setOpaque(false);

        tabelPelanggan.setBackground(new java.awt.Color(51, 51, 55));
        tabelPelanggan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelPelanggan.setForeground(new java.awt.Color(204, 204, 204));
        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPelanggan.setOpaque(false);
        tabelPelanggan.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jScrollPane2.setViewportView(tabelPelanggan);

        buttonToolPelanggan.setOpaque(false);

        lbDeteksiIDpelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png"))); // NOI18N

        javax.swing.GroupLayout panelDataPelangganLayout = new javax.swing.GroupLayout(panelDataPelanggan);
        panelDataPelanggan.setLayout(panelDataPelangganLayout);
        panelDataPelangganLayout.setHorizontalGroup(
            panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txTeleponPelanggan)
                            .addComponent(txNamaPelanggan)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataPelangganLayout.createSequentialGroup()
                                .addComponent(txIDpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDeteksiIDpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                        .addComponent(btSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btHapus))
                                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                        .addComponent(rbCowokPlg)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbCewekPlg)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                .addComponent(buttonToolPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelDataPelangganLayout.setVerticalGroup(
            panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txIDpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbDeteksiIDpelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbCowokPlg)
                            .addComponent(rbCewekPlg)
                            .addComponent(jLabel7))
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDataPelangganLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txTeleponPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelDataPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDataPelangganLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonToolPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("DATA PELANGGAN", panelDataPelanggan);

        panelDataKasir.setBackground(new java.awt.Color(51, 102, 0));
        panelDataKasir.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelDataKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btLoginKasir.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btLoginKasir.setText("MASUK");
        btLoginKasir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txPasswdKasir.setBackground(new java.awt.Color(16, 29, 51));
        txPasswdKasir.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txPasswdKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txPasswdKasirMouseClicked(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton3.setText("BATAL");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/admin.png"))); // NOI18N

        txUsernameKasir.setBackground(new java.awt.Color(16, 29, 51));
        txUsernameKasir.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        txUsernameKasir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txUsernameKasirMouseClicked(evt);
            }
        });

        lbInfoLogin.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbInfoLogin.setForeground(new java.awt.Color(204, 204, 204));
        lbInfoLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbUserKasirPengaturan.setForeground(new java.awt.Color(51, 102, 0));

        lbPasswdKasirPengaturan.setForeground(new java.awt.Color(51, 102, 0));

        lbIDkasir.setForeground(new java.awt.Color(51, 102, 0));

        javax.swing.GroupLayout panelDataKasirLayout = new javax.swing.GroupLayout(panelDataKasir);
        panelDataKasir.setLayout(panelDataKasirLayout);
        panelDataKasirLayout.setHorizontalGroup(
            panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataKasirLayout.createSequentialGroup()
                .addGap(583, 583, 583)
                .addComponent(btLoginKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDataKasirLayout.createSequentialGroup()
                        .addGroup(panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUserKasirPengaturan)
                            .addComponent(lbPasswdKasirPengaturan)
                            .addComponent(lbIDkasir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDataKasirLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txUsernameKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txPasswdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(512, 512, 512))
            .addGroup(panelDataKasirLayout.createSequentialGroup()
                .addGap(467, 467, 467)
                .addComponent(lbInfoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(465, Short.MAX_VALUE))
        );
        panelDataKasirLayout.setVerticalGroup(
            panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataKasirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDataKasirLayout.createSequentialGroup()
                        .addComponent(lbUserKasirPengaturan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPasswdKasirPengaturan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDkasir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txUsernameKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txPasswdKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDataKasirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLoginKasir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbInfoLogin)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PENGATURAN KASIR", panelDataKasir);

        panelDataBarang.setBackground(new java.awt.Color(51, 102, 0));
        panelDataBarang.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelDataBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel12.setText("ID BARANG");

        txIDbarang.setBackground(new java.awt.Color(16, 29, 51));
        txIDbarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDbarang.setForeground(new java.awt.Color(204, 204, 204));
        txIDbarang.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel13.setText("NAMA BARANG");

        txStokBarang.setBackground(new java.awt.Color(16, 29, 51));
        txStokBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txStokBarang.setForeground(new java.awt.Color(204, 204, 204));
        txStokBarang.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel16.setText("STOK");

        btSimpanBarang.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpanBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/simpan.PNG"))); // NOI18N
        btSimpanBarang.setText("SIMPAN");
        btSimpanBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusBarang.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapus.png"))); // NOI18N
        btHapusBarang.setText("HAPUS");
        btHapusBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel17.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("FORM PENGISIAN DATA BARANG");

        jLabel18.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("DAFTAR DATA BARANG");

        jScrollPane4.setOpaque(false);

        tabelBarang.setBackground(new java.awt.Color(51, 51, 55));
        tabelBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelBarang.setForeground(new java.awt.Color(204, 204, 204));
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang.setOpaque(false);
        tabelBarang.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jScrollPane4.setViewportView(tabelBarang);

        buttonToolBarang.setOpaque(false);

        lbDeteksiIDbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/salah.png"))); // NOI18N

        txHargaBarang.setBackground(new java.awt.Color(16, 29, 51));
        txHargaBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txHargaBarang.setForeground(new java.awt.Color(204, 204, 204));
        txHargaBarang.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel19.setText("HARGA");

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel14.setText("ID KATEGORI");

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel15.setText("ID SATUAN");

        txDiskonBarang.setBackground(new java.awt.Color(16, 29, 51));
        txDiskonBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txDiskonBarang.setForeground(new java.awt.Color(204, 204, 204));
        txDiskonBarang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDiskonBarang.setText("0");
        txDiskonBarang.setCaretColor(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel20.setText("DISKON");

        txIDkategori.setEditable(false);
        txIDkategori.setBackground(new java.awt.Color(16, 29, 51));
        txIDkategori.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDkategori.setForeground(new java.awt.Color(204, 204, 204));
        txIDkategori.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDkategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDkategoriMouseClicked(evt);
            }
        });

        btGetIDkat.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDkat.setText("...");
        btGetIDkat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txIDsatuan.setEditable(false);
        txIDsatuan.setBackground(new java.awt.Color(16, 29, 51));
        txIDsatuan.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDsatuan.setForeground(new java.awt.Color(204, 204, 204));
        txIDsatuan.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDsatuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIDsatuanMouseClicked(evt);
            }
        });

        btGetIDsat.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        btGetIDsat.setText("...");
        btGetIDsat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txNamaBarang.setBackground(new java.awt.Color(16, 29, 51));
        txNamaBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txNamaBarang.setForeground(new java.awt.Color(204, 204, 204));
        txNamaBarang.setCaretColor(new java.awt.Color(204, 204, 204));
        txNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNamaBarangKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelDataBarangLayout = new javax.swing.GroupLayout(panelDataBarang);
        panelDataBarang.setLayout(panelDataBarangLayout);
        panelDataBarangLayout.setHorizontalGroup(
            panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataBarangLayout.createSequentialGroup()
                .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataBarangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(jLabel19)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel20))
                        .addGap(13, 13, 13)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txDiskonBarang)
                            .addComponent(txHargaBarang)
                            .addComponent(txStokBarang)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataBarangLayout.createSequentialGroup()
                                .addComponent(txIDbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDeteksiIDbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(panelDataBarangLayout.createSequentialGroup()
                                .addComponent(btSimpanBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btHapusBarang)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataBarangLayout.createSequentialGroup()
                                .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txIDkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txIDsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btGetIDkat, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(btGetIDsat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txNamaBarang))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDataBarangLayout.createSequentialGroup()
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
                            .addGroup(panelDataBarangLayout.createSequentialGroup()
                                .addComponent(buttonToolBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panelDataBarangLayout.setVerticalGroup(
            panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(panelDataBarangLayout.createSequentialGroup()
                .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataBarangLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txIDbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbDeteksiIDbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDkategori, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGetIDkat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txIDsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(btGetIDsat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txHargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txStokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txDiskonBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDataBarangLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonToolBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("DATA BARANG", panelDataBarang);

        panelDataKategori.setBackground(new java.awt.Color(51, 102, 0));
        panelDataKategori.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelDataKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel22.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("FORM PENGISIAN KATEGORI BARANG");

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel23.setText("ID KATEGORI");

        txIDkat.setBackground(new java.awt.Color(16, 29, 51));
        txIDkat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDkat.setForeground(new java.awt.Color(204, 204, 204));
        txIDkat.setCaretColor(new java.awt.Color(204, 204, 204));

        txNamaKat.setBackground(new java.awt.Color(16, 29, 51));
        txNamaKat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txNamaKat.setForeground(new java.awt.Color(204, 204, 204));
        txNamaKat.setCaretColor(new java.awt.Color(204, 204, 204));
        txNamaKat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNamaKatKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel24.setText("NAMA KATEGORI");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btRandomIDkat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRandomIDkat.setText("RANDOM ID");
        btRandomIDkat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSimpanKat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpanKat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/simpan.PNG"))); // NOI18N
        btSimpanKat.setText("SIMPAN");
        btSimpanKat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusKat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusKat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapus.png"))); // NOI18N
        btHapusKat.setText("HAPUS");
        btHapusKat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabelKategoriBarang.setBackground(new java.awt.Color(51, 51, 55));
        tabelKategoriBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelKategoriBarang.setForeground(new java.awt.Color(204, 204, 204));
        tabelKategoriBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelKategoriBarang.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jScrollPane3.setViewportView(tabelKategoriBarang);

        jLabel25.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("DAFTAR TABEL KATEGORI BARANG");

        buttonToolKategori.setOpaque(false);

        javax.swing.GroupLayout panelDataKategoriLayout = new javax.swing.GroupLayout(panelDataKategori);
        panelDataKategori.setLayout(panelDataKategoriLayout);
        panelDataKategoriLayout.setHorizontalGroup(
            panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataKategoriLayout.createSequentialGroup()
                .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDataKategoriLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataKategoriLayout.createSequentialGroup()
                                .addComponent(btSimpanKat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btHapusKat))
                            .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelDataKategoriLayout.createSequentialGroup()
                                    .addComponent(txIDkat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btRandomIDkat))
                                .addComponent(txNamaKat)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDataKategoriLayout.createSequentialGroup()
                        .addComponent(buttonToolKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDataKategoriLayout.setVerticalGroup(
            panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(panelDataKategoriLayout.createSequentialGroup()
                .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataKategoriLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(txIDkat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btRandomIDkat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaKat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(37, 37, 37)
                        .addGroup(panelDataKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpanKat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapusKat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDataKategoriLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonToolKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("KATEGORI BARANG", panelDataKategori);

        panelDataSatuan.setBackground(new java.awt.Color(51, 102, 0));
        panelDataSatuan.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        panelDataSatuan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel26.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("FORM PENGISIAN SATUAN BARANG");

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel27.setText("ID SATUAN");

        txIDsat.setBackground(new java.awt.Color(16, 29, 51));
        txIDsat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txIDsat.setForeground(new java.awt.Color(204, 204, 204));
        txIDsat.setCaretColor(new java.awt.Color(204, 204, 204));
        txIDsat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txIDsatKeyTyped(evt);
            }
        });

        txNamaSat.setBackground(new java.awt.Color(16, 29, 51));
        txNamaSat.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        txNamaSat.setForeground(new java.awt.Color(204, 204, 204));
        txNamaSat.setCaretColor(new java.awt.Color(204, 204, 204));
        txNamaSat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNamaSatKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel28.setText("NAMA SATUAN ");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btRandomIDsat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btRandomIDsat.setText("RANDOM ID");
        btRandomIDsat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btSimpanSat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btSimpanSat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/simpan.PNG"))); // NOI18N
        btSimpanSat.setText("SIMPAN");
        btSimpanSat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btHapusSat.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        btHapusSat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/hapus.png"))); // NOI18N
        btHapusSat.setText("HAPUS");
        btHapusSat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tabelSatuanBarang.setBackground(new java.awt.Color(51, 51, 55));
        tabelSatuanBarang.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        tabelSatuanBarang.setForeground(new java.awt.Color(204, 204, 204));
        tabelSatuanBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelSatuanBarang.setSelectionBackground(new java.awt.Color(51, 102, 0));
        jScrollPane5.setViewportView(tabelSatuanBarang);

        jLabel29.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DAFTAR TABEL SATUAN BARANG");

        buttonToolSatuan.setOpaque(false);

        javax.swing.GroupLayout panelDataSatuanLayout = new javax.swing.GroupLayout(panelDataSatuan);
        panelDataSatuan.setLayout(panelDataSatuanLayout);
        panelDataSatuanLayout.setHorizontalGroup(
            panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataSatuanLayout.createSequentialGroup()
                .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelDataSatuanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataSatuanLayout.createSequentialGroup()
                                .addComponent(btSimpanSat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btHapusSat))
                            .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelDataSatuanLayout.createSequentialGroup()
                                    .addComponent(txIDsat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btRandomIDsat))
                                .addComponent(txNamaSat)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelDataSatuanLayout.createSequentialGroup()
                        .addComponent(buttonToolSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelDataSatuanLayout.setVerticalGroup(
            panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(panelDataSatuanLayout.createSequentialGroup()
                .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDataSatuanLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(txIDsat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btRandomIDsat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNamaSat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(37, 37, 37)
                        .addGroup(panelDataSatuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSimpanSat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btHapusSat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelDataSatuanLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonToolSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SATUAN BARANG", panelDataSatuan);

        jLabel45.setText("ID");

        jLabel46.setText("NAMA");

        jLabel47.setText("HARGA");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel45))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField6)
                        .addComponent(jTextField7)
                        .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                .addContainerGap(844, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(429, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab10", jPanel8);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jToolBar1.setFloatable(false);

        lbStatusAdmin.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbStatusAdmin.setText("                                                             ");
        jToolBar1.add(lbStatusAdmin);

        jLabel40.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel40.setText("|");
        jToolBar1.add(jLabel40);

        lbStatusKasir.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lbStatusKasir.setText("                                                  ");
        lbStatusKasir.setPreferredSize(new java.awt.Dimension(144, 16));
        jToolBar1.add(lbStatusKasir);

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem1.setText("Transaksi Penjualan");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenu3.setText("Data Query Penjualan");
        jMenu3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem3.setText("Query Penjualan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem4.setText("Query Penjualan Detil");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenu1.add(jMenu3);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem2.setText("Data Pelanggan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem5.setText("Data Barang");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        menuRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuRefresh.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        menuRefresh.setText("Refresh");
        menuRefresh.setAutoscrolls(true);
        jMenu1.add(menuRefresh);
        jMenu1.add(jSeparator5);

        menuLogOut.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        menuLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/keluar.png"))); // NOI18N
        menuLogOut.setText("Keluar");
        jMenu1.add(menuLogOut);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tool");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem6.setText("Pengaturan Kasir");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenu5.setText("Theme");
        jMenu5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N

        jRadioButtonMenuItem1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Nimbus");
        jMenu5.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Windows");
        jMenu5.add(jRadioButtonMenuItem2);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Help");
        jMenu4.setFont(new java.awt.Font("Calibri", 1, 13)); // NOI18N

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem7.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyek/rpl/gambar/helpContent.png"))); // NOI18N
        jMenuItem7.setText("Help Content");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem8.setText("Online and Support");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jMenuItem9.setText("About");
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1385, 841));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txIDkategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDkategoriMouseClicked
        // TODO add your handling code here:
        actionGetIDkat();
    }//GEN-LAST:event_txIDkategoriMouseClicked

    private void txIDsatuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDsatuanMouseClicked
        // TODO add your handling code here:
        actionGetIDsat();
    }//GEN-LAST:event_txIDsatuanMouseClicked

    private void txPasswdKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txPasswdKasirMouseClicked
        // TODO add your handling code here:
        if (txUsernameKasir.getText().isEmpty()) {
            txUsernameKasir.setText("USERNAME");        
            txUsernameKasir.setShadow(true);
            txUsernameKasir.setShadowTextColor(Color.GRAY);
        }
        lbInfoLogin.setText(null);
    }//GEN-LAST:event_txPasswdKasirMouseClicked

    private void txUsernameKasirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txUsernameKasirMouseClicked
        // TODO add your handling code here:      
        if (txPasswdKasir.getText().isEmpty()) {
            txPasswdKasir.setText("PASSWORD");        
            txPasswdKasir.setShadow(true);
            txPasswdKasir.setShadowTextColor(Color.GRAY);
        }
        lbInfoLogin.setText(null);
    }//GEN-LAST:event_txUsernameKasirMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txIDkasirJual.setText(id);
        lbIDkasir.setText(idKasTampilAdmin);
        lbUserKasir.setText(name);
        lbUserKasirPengaturan.setText(user);
        lbPasswdKasirPengaturan.setText(password);     
    }//GEN-LAST:event_formWindowOpened

    private void txTeleponPelangganKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTeleponPelangganKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txTeleponPelangganKeyTyped

    private void txNamaKatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNamaKatKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txNamaKatKeyTyped

    private void txTglJualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTglJualKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txTglJualKeyTyped

    private void txIDplgJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDplgJualMouseClicked
        // TODO add your handling code here:
        getIDplg();
    }//GEN-LAST:event_txIDplgJualMouseClicked

    private void txJumlahBarangJualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJualKeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual.getText().length()==0) {
//            txJumlahBarangJual.setText("0");
            txSubTotal.setText("TOTAL");
            lbDiskonJual.setText("0");
            txBayarJual.setText(null);
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy.setText(txJumlahBarangJual.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal();
//                jualClass.cekStokBarang();
            } catch (Exception e) { }
        }        
    }//GEN-LAST:event_txJumlahBarangJualKeyReleased

    private void txBayarJualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBayarJualKeyReleased
        // TODO add your handling code here:     
        if (txBayarJual.getText().length()==0) {
            lbCopyBayar.setText(null);
        } else {
            try {
                float copy = (float) Float.parseFloat(txBayarJual.getText());            
                lbCopyBayar.setText(" "+copy);
            } catch (NumberFormatException e) { }     
        }        
    }//GEN-LAST:event_txBayarJualKeyReleased

    private void txIDjualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDjualKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txIDjualKeyTyped

    private void txIDbarangJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJualMouseClicked
        // TODO add your handling code here:
        getIDbrng();
    }//GEN-LAST:event_txIDbarangJualMouseClicked

    private void txJumlahBarangJualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJualKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJualKeyTyped

    private void txBayarJualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBayarJualKeyTyped
        // TODO add your handling code here:
//        validasiAngka(evt);
        validasiPembayaranKey(evt);
    }//GEN-LAST:event_txBayarJualKeyTyped

    private void txBayarJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txBayarJualMouseClicked
        // TODO add your handling code here:
        txBayarJual.setText(null);
        if (txBayarJual.getText().isEmpty()) {
            txBayarJual.setText(null);
            lbCopyBayar.setText(null);
        }
    }//GEN-LAST:event_txBayarJualMouseClicked

    private void txBanyakBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBanyakBarangKeyReleased
        // TODO add your handling code here:
//        txBanyakBarangCopy.setText(txBanyakBarang.getText());
        jumlahBarangBeli(); 
        if (txJumlahBarangJualCopy1.getText().equals("0") || txJumlahBarangJual1.getText().length()==0) {
            txSubTotal1.setText("TOTAL");
        } else if (txBanyakBarang.getText().length()==0) {
            txJumlahBarangJualCopy1.setText("0");       
            txJumlahBarangJualCopy2.setText("0");
            txJumlahBarangJualCopy3.setText("0");
            txJumlahBarangJualCopy4.setText("0");
            txJumlahBarangJualCopy5.setText("0");
            txJumlahBarangJualCopy6.setText("0");
            txJumlahBarangJualCopy7.setText("0");
            txBayarJual.setText(null);
//            txJumlahBarangJualCopy8.setText("0");
        } 
         
    }//GEN-LAST:event_txBanyakBarangKeyReleased

    private void txIDbarangJual1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual1MouseClicked
        // TODO add your handling code here:
        getIDbrng1();
    }//GEN-LAST:event_txIDbarangJual1MouseClicked

    private void txIDbarangJual2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual2MouseClicked
        // TODO add your handling code here:
        getIDbrng2();
    }//GEN-LAST:event_txIDbarangJual2MouseClicked

    private void txIDbarangJual3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual3MouseClicked
        // TODO add your handling code here:
        getIDbrng3();
    }//GEN-LAST:event_txIDbarangJual3MouseClicked

    private void txIDbarangJual4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual4MouseClicked
        // TODO add your handling code here:
        getIDbrng4();
    }//GEN-LAST:event_txIDbarangJual4MouseClicked

    private void txIDbarangJual5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual5MouseClicked
        // TODO add your handling code here:
        getIDbrng5();
    }//GEN-LAST:event_txIDbarangJual5MouseClicked

    private void txIDbarangJual6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual6MouseClicked
        // TODO add your handling code here:
        getIDbrng6();
    }//GEN-LAST:event_txIDbarangJual6MouseClicked

    private void txIDbarangJual7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual7MouseClicked
        // TODO add your handling code here:
        getIDbrng7();
    }//GEN-LAST:event_txIDbarangJual7MouseClicked

    private void txIDbarangJual7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDbarangJual7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIDbarangJual7KeyPressed

    private void txIDbarangJual8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual8MouseClicked
        // TODO add your handling code here:
        getIDbrng8();
    }//GEN-LAST:event_txIDbarangJual8MouseClicked

    private void txJumlahBarangJual1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual1KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual1.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal1.setText("TOTAL");
            lbDiskonJual1.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy1.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy1.setText(txJumlahBarangJual1.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal1();
//                jualClass.cekStokBarang1();
            } catch (Exception e) { }
        } 
    }//GEN-LAST:event_txJumlahBarangJual1KeyReleased

    private void txJumlahBarangJual1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual1KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual1KeyTyped

    private void txJumlahBarangJual2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual2KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual2.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal2.setText("TOTAL");
            lbDiskonJual2.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy2.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy2.setText(txJumlahBarangJual2.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal2();
            } catch (Exception e) { }
        } 
    }//GEN-LAST:event_txJumlahBarangJual2KeyReleased

    private void txJumlahBarangJual2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual2KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual2KeyTyped

    private void txJumlahBarangJual3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual3KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual3.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal3.setText("TOTAL");
            lbDiskonJual3.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy3.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy3.setText(txJumlahBarangJual3.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal3();
            } catch (Exception e) { }
        }         
    }//GEN-LAST:event_txJumlahBarangJual3KeyReleased

    private void txJumlahBarangJual3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual3KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual3KeyTyped

    private void txJumlahBarangJual4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual4KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual4.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal4.setText("TOTAL");
            lbDiskonJual4.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy4.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy4.setText(txJumlahBarangJual4.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal4();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual4KeyReleased

    private void txJumlahBarangJual4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual4KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual4KeyTyped

    private void txJumlahBarangJual5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual5KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual5.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal5.setText("TOTAL");
            lbDiskonJual5.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy5.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy5.setText(txJumlahBarangJual5.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal5();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual5KeyReleased

    private void txJumlahBarangJual5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual5KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual5KeyTyped

    private void txJumlahBarangJual6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual6KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual6.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal6.setText("TOTAL");
            lbDiskonJual6.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy6.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy6.setText(txJumlahBarangJual6.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal6();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual6KeyReleased

    private void txJumlahBarangJual6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual6KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual6KeyTyped

    private void txJumlahBarangJual7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual7KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual7.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal7.setText("TOTAL");
            lbDiskonJual7.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy7.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy7.setText(txJumlahBarangJual7.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal7();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual7KeyReleased

    private void txJumlahBarangJual7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual7KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual7KeyTyped

    private void txJumlahBarangJual8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual8KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual8.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal8.setText("TOTAL");
            lbDiskonJual8.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy8.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy8.setText(txJumlahBarangJual8.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal8();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual8KeyReleased

    private void txJumlahBarangJual8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual8KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual8KeyTyped

    private void txIDbarangJual9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIDbarangJual9MouseClicked
        // TODO add your handling code here:
        getIDbrng9();
    }//GEN-LAST:event_txIDbarangJual9MouseClicked

    private void txJumlahBarangJual9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual9KeyReleased
        // TODO add your handling code here:
        if (txJumlahBarangJual9.getText().length()==0) {
//            txJumlahBarangJual1.setText("0");
            txSubTotal9.setText("TOTAL");
            lbDiskonJual9.setText("0");
            lbKembalianJual.setText(null);
            lbCopyBayar.setText(null);
            txJumlahBarangJualCopy9.setText("0");
//            txSubTotalBarangJual.setText("Rp. 0");
        } else {
            txJumlahBarangJualCopy9.setText(txJumlahBarangJual9.getText());
            try {
                jualClass = new penjualan();
                jualClass.cekSubTotal9();
            } catch (Exception e) { }
        }
    }//GEN-LAST:event_txJumlahBarangJual9KeyReleased

    private void txJumlahBarangJual9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txJumlahBarangJual9KeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_txJumlahBarangJual9KeyTyped

    private void txIDsatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txIDsatKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txIDsatKeyTyped

    private void txNamaSatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNamaSatKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txNamaSatKeyTyped

    private void txNamaBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNamaBarangKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txNamaBarangKeyTyped

    private void tabelQueryPenjualanDetilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelQueryPenjualanDetilMouseClicked
        // TODO add your handling code here:
        int baris = tabelQueryPenjualanDetil.getSelectedRow();
        jTextField3.setText(modelDataQueryJualDetil.fieldJual.get(baris).getIDJual());
        cekBokQueryDetil.setSelected(false);
    }//GEN-LAST:event_tabelQueryPenjualanDetilMouseClicked

    private void tabelQueryPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelQueryPenjualanMouseClicked
        // TODO add your handling code here:
        int baris = tabelQueryPenjualan.getSelectedRow();
        jTextField4.setText(modelDataQueryJual.fieldJual.get(baris).getIDJual());
//        cekBokQueryDetil.setSelected(false);
    }//GEN-LAST:event_tabelQueryPenjualanMouseClicked

    private void txCariQueryJualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariQueryJualKeyReleased
        // TODO add your handling code here:
        txCariQueryJualCopy.setText(txCariQueryJual.getText());
        if (txCariQueryJualCopy.getText().length()==0) {
                segarkanQueryJual();
                jLabel41.setText(null);
            } else {
                try {
                    jualPenjualan = new queryJualPenjualan();
                    jualPenjualan.cariQueryJual();
                } catch (Exception ex) { } 
            }
    }//GEN-LAST:event_txCariQueryJualKeyReleased

    private void txCariQueryJualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariQueryJualKeyTyped
        // TODO add your handling code here:
        hurufBalok(evt);
    }//GEN-LAST:event_txCariQueryJualKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    Desktop desktop = java.awt.Desktop.getDesktop();
    if (Desktop.isDesktopSupported()) {
      try {
          URI uri = null;
          try {
              uri = new URI("http://www.netbeans.org/kb/index.html");
          } catch (URISyntaxException e) { }          
          Desktop.getDesktop().browse(uri);
      } catch (IOException e) { }
    } else { /* TODO: error handling */ }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        new dialogHelp(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jTextField1.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Masukkan Tanggal Awal Dulu !!!\nPencarian Di Mulai Dengan Huruf Awal", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            jTextField1.requestFocus();
        } else if (jTextField5.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "Masukkan Tanggal Akhir Dulu !!!\nPencarian Di Mulai Dengan Huruf Awal", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            jTextField5.requestFocus();
        } else {
            jualPenjualan = new queryJualPenjualan();
            jualPenjualan.selisihJual();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
//        jButton1ActionPerformed(null);
        jualPenjualan = new queryJualPenjualan();
        jualPenjualan.selisihJual();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
//        jButton1ActionPerformed(null);
        jualPenjualan = new queryJualPenjualan();
        jualPenjualan.selisihJual();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int idd = 0;
        int nama1 = 0;
        int harga1 = 0;
        
        try {
            idd = Integer.parseInt(jTextField7.getText());
            nama1 = Integer.parseInt(jTextField7.getText());
            harga1 = Integer.parseInt(jTextField8.getText());
        } catch (NumberFormatException e) { }
        
        if (jTextField7.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Masih Kosong");
        } else {
            try {
                dbKoneksi.conn.createStatement();
                String tambah = ("insert into belajar1 values('"+idd+"','"+nama1+"','"+harga1+"') ");
                dbKoneksi.st.executeUpdate(tambah);
                JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah");
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Tambah");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private final transaksiPenjualanImplement penjualanImplement = new transaksiPenjualanImplement();
    private final modelTransaksiPenjualan penjualanM = new modelTransaksiPenjualan();
    private final modelDataPelanggan dataPelanggan = new modelDataPelanggan();
    private final modelDataQueryJual queryJual = new modelDataQueryJual();
    private final modelDataQueryJualDetil queryJualDetil = new modelDataQueryJualDetil();
    private final queryJual Queues = new queryJual();
    private final modelDataBarang dataBarang = new modelDataBarang();
    private final kategoriImplement katImplement = new kategoriImplement();
    private final satuanImplement satImplement = new satuanImplement();
    private final dataTransaksiPenjualan transaksiPenjualanData = new dataTransaksiPenjualan();
    private final dataPelanggan dtPelanggan  = new dataPelanggan();
    private final dataBarang dtBarang = new dataBarang();
    private final dataKategori kategoriData = new dataKategori();
    private final dataSatuan satuanData = new dataSatuan();    
    private final DecimalFormat dfoCek = new DecimalFormat("#,##0");
    private modelKategori kategoriM = new modelKategori();
    private modelSatuan satuanM = new modelSatuan();
    private barangImplement barang = new barangImplement();     
    private dbPelanggan pelanggan = new dbPelanggan();
    private dbKasir ksr = new dbKasir();
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
            java.util.logging.Logger.getLogger(frameIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frameIndex().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btAdminDataAdmin;
    public javax.swing.JButton btAdminDataKasir;
    private javax.swing.JButton btBanyakBarang;
    private javax.swing.JButton btCetakJual;
    private javax.swing.JButton btGetIDbarangJual;
    private javax.swing.JButton btGetIDbarangJual1;
    private javax.swing.JButton btGetIDbarangJual2;
    private javax.swing.JButton btGetIDbarangJual3;
    private javax.swing.JButton btGetIDbarangJual4;
    private javax.swing.JButton btGetIDbarangJual5;
    private javax.swing.JButton btGetIDbarangJual6;
    private javax.swing.JButton btGetIDbarangJual7;
    private javax.swing.JButton btGetIDbarangJual8;
    private javax.swing.JButton btGetIDbarangJual9;
    private javax.swing.JButton btGetIDkat;
    private javax.swing.JButton btGetIDplgJual;
    private javax.swing.JButton btGetIDsat;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btHapusBarang;
    private javax.swing.JButton btHapusDataJual;
    private javax.swing.JButton btHapusDataJualDetil;
    private javax.swing.JButton btHapusKat;
    private javax.swing.JButton btHapusSat;
    private javax.swing.JButton btLoginKasir;
    private javax.swing.JButton btRandomIDkat;
    private javax.swing.JButton btRandomIDsat;
    private javax.swing.JButton btRandomJual;
    private javax.swing.JButton btRefreshQueryJual;
    private javax.swing.JButton btRefreshQueryJualDetil;
    private javax.swing.JButton btSimpan;
    private javax.swing.JButton btSimpanBarang;
    private javax.swing.JButton btSimpanKat;
    private javax.swing.JButton btSimpanSat;
    private javax.swing.ButtonGroup buttonGroup1;
    private proyek.rpl.palette.komponen.buttonTool buttonToolBarang;
    private proyek.rpl.palette.komponen.buttonTool buttonToolKategori;
    private proyek.rpl.palette.komponen.buttonTool buttonToolPelanggan;
    private proyek.rpl.palette.komponen.buttonTool buttonToolSatuan;
    private proyek.rpl.palette.komponen.buttonToolPenjualan buttonToolTransaksiPenjualan;
    private javax.swing.JComboBox cbCariQuery;
    private javax.swing.JCheckBox cekBokQueryDetil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbCopyBayar;
    private javax.swing.JLabel lbCopyKembalian;
    private javax.swing.JLabel lbDeteksiIDbarang;
    private javax.swing.JLabel lbDeteksiIDpelanggan;
    public javax.swing.JLabel lbDiskonJual;
    private java.awt.TextField lbDiskonJual1;
    public javax.swing.JLabel lbDiskonJual2;
    private javax.swing.JLabel lbDiskonJual3;
    private javax.swing.JLabel lbDiskonJual4;
    private javax.swing.JLabel lbDiskonJual5;
    private javax.swing.JLabel lbDiskonJual6;
    private javax.swing.JLabel lbDiskonJual7;
    private javax.swing.JLabel lbDiskonJual8;
    private javax.swing.JLabel lbDiskonJual9;
    public javax.swing.JLabel lbIDkasir;
    private javax.swing.JLabel lbInfoLogin;
    private javax.swing.JLabel lbKembalianJual;
    private javax.swing.JLabel lbPasswdKasirPengaturan;
    public javax.swing.JLabel lbStatusAdmin;
    public javax.swing.JLabel lbStatusKasir;
    private javax.swing.JLabel lbUserKasir;
    private javax.swing.JLabel lbUserKasirPengaturan;
    private javax.swing.JMenuItem menuLogOut;
    private javax.swing.JMenuItem menuRefresh;
    private javax.swing.JPanel panelBeranda;
    private javax.swing.JPanel panelDataBarang;
    private javax.swing.JPanel panelDataKasir;
    private javax.swing.JPanel panelDataKategori;
    private javax.swing.JPanel panelDataPelanggan;
    private javax.swing.JPanel panelDataSatuan;
    private javax.swing.JPanel panelPenjualanDetail;
    private javax.swing.JPanel panelQueryPenjualanDetail;
    private javax.swing.JPanel panelTransaksiPenjualan;
    private javax.swing.JRadioButton rbCewekPlg;
    private javax.swing.JRadioButton rbCowokPlg;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelKategoriBarang;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JTable tabelQueryPenjualan;
    private javax.swing.JTable tabelQueryPenjualanDetil;
    private javax.swing.JTable tabelSatuanBarang;
    private javax.swing.JTextArea txAreaPelanggan;
    private javax.swing.JTextField txBanyakBarang;
    private javax.swing.JTextField txBayarJual;
    private javax.swing.JTextField txCariQueryJual;
    private java.awt.TextField txCariQueryJualCopy;
    private javax.swing.JTextField txCekKategori;
    private javax.swing.JTextField txCekRecord;
    private javax.swing.JTextField txCekRecordBarang;
    private javax.swing.JTextField txCekSatuan;
    private javax.swing.JTextField txDiskonBarang;
    public javax.swing.JTextField txDiskonBarangJual;
    public javax.swing.JTextField txDiskonBarangJual1;
    private javax.swing.JTextField txHargaBarang;
    public javax.swing.JTextField txHargaBarangJual;
    public javax.swing.JTextField txHargaBarangJual1;
    public javax.swing.JTextField txHargaBarangJual2;
    public javax.swing.JTextField txHargaBarangJual3;
    public javax.swing.JTextField txHargaBarangJual4;
    public javax.swing.JTextField txHargaBarangJual5;
    public javax.swing.JTextField txHargaBarangJual6;
    public javax.swing.JTextField txHargaBarangJual7;
    public javax.swing.JTextField txHargaBarangJual8;
    public javax.swing.JTextField txHargaBarangJual9;
    public javax.swing.JTextField txIDbarang;
    public javax.swing.JTextField txIDbarangJual;
    public javax.swing.JTextField txIDbarangJual1;
    public javax.swing.JTextField txIDbarangJual2;
    public javax.swing.JTextField txIDbarangJual3;
    public javax.swing.JTextField txIDbarangJual4;
    public javax.swing.JTextField txIDbarangJual5;
    public javax.swing.JTextField txIDbarangJual6;
    public javax.swing.JTextField txIDbarangJual7;
    public javax.swing.JTextField txIDbarangJual8;
    public javax.swing.JTextField txIDbarangJual9;
    public javax.swing.JTextField txIDjual;
    public javax.swing.JTextField txIDkasirJual;
    private javax.swing.JTextField txIDkat;
    public javax.swing.JTextField txIDkategori;
    public javax.swing.JTextField txIDpelanggan;
    public javax.swing.JTextField txIDplgJual;
    private javax.swing.JTextField txIDsat;
    public javax.swing.JTextField txIDsatuan;
    private javax.swing.JTextField txJumlahBarangJual;
    private javax.swing.JTextField txJumlahBarangJual1;
    private javax.swing.JTextField txJumlahBarangJual2;
    private javax.swing.JTextField txJumlahBarangJual3;
    private javax.swing.JTextField txJumlahBarangJual4;
    private javax.swing.JTextField txJumlahBarangJual5;
    private javax.swing.JTextField txJumlahBarangJual6;
    private javax.swing.JTextField txJumlahBarangJual7;
    private javax.swing.JTextField txJumlahBarangJual8;
    private javax.swing.JTextField txJumlahBarangJual9;
    private java.awt.TextField txJumlahBarangJualCopy;
    private java.awt.TextField txJumlahBarangJualCopy1;
    private java.awt.TextField txJumlahBarangJualCopy2;
    private java.awt.TextField txJumlahBarangJualCopy3;
    private java.awt.TextField txJumlahBarangJualCopy4;
    private java.awt.TextField txJumlahBarangJualCopy5;
    private java.awt.TextField txJumlahBarangJualCopy6;
    private java.awt.TextField txJumlahBarangJualCopy7;
    private java.awt.TextField txJumlahBarangJualCopy8;
    private java.awt.TextField txJumlahBarangJualCopy9;
    private javax.swing.JTextField txNamaBarang;
    private javax.swing.JTextField txNamaKat;
    private javax.swing.JTextField txNamaPelanggan;
    private javax.swing.JTextField txNamaSat;
    private proyek.rpl.palette.komponen.passwdShadow txPasswdKasir;
    private javax.swing.JTextField txStokBarang;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal1;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal2;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal3;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal4;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal5;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal6;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal7;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal8;
    private proyek.rpl.palette.komponen.txJumlahJual txSubTotal9;
    private javax.swing.JTextField txSubTotalBarangJual;
    private javax.swing.JTextField txSubTotalBarangJualCopy;
    private javax.swing.JTextField txTeleponPelanggan;
    private javax.swing.JTextField txTglJual;
    private javax.swing.JTextField txTotalBarangJual;
    public proyek.rpl.palette.komponen.txtShadow txUsernameKasir;
    // End of variables declaration//GEN-END:variables

    private void koneksi() {
        kon = new dbKoneksi();
        kon.openKoneksi();
    } 
    
    private void hurufBalok(KeyEvent e) {        
        e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
    }   
    
    private void validasiAngka(KeyEvent e) {
        char c = e.getKeyChar();        
        if (Character.isLetter(c)) {
            e.consume();
            JOptionPane.showMessageDialog(null, "Karakter Harus Berupa Angka","WARNING",
                    JOptionPane.WARNING_MESSAGE);           
        }
    }
    
    private void tanggal() {        
        try {            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
            String now = sdf.format(cal.getTime());
            txTglJual.setText(" "+now);
        } catch (Exception e) {
        }
    }
    
    private void komponen() {        
        txUsernameKasir.setText("USERNAME");
        txPasswdKasir.setText("PASSWORD"); 
        txUsernameKasir.setCaretColor(Color.GRAY);
        txPasswdKasir.setCaretColor(Color.GRAY);
        txUsernameKasir.setForeground(Color.GRAY);
        txPasswdKasir.setForeground(Color.GRAY);           
        jScrollPane2.getViewport().setOpaque(false);        
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane4.getViewport().setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        jScrollPane8.getViewport().setOpaque(false);
        jScrollPane9.getViewport().setOpaque(false);
        lbDeteksiIDpelanggan.setVisible(false);
        lbDeteksiIDbarang.setVisible(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(new ImageIcon(getClass().
                getResource("/proyek/rpl/gambar/icon.png")).getImage());   
        txBanyakBarang.setText("1");        
        txIDbarangJual1.setVisible(false);
        txIDbarangJual2.setVisible(false);
        txIDbarangJual3.setVisible(false);
        txIDbarangJual4.setVisible(false);
        txIDbarangJual5.setVisible(false);
        txIDbarangJual6.setVisible(false);
        txIDbarangJual7.setVisible(false);
        txIDbarangJual8.setVisible(false);
        txIDbarangJual9.setVisible(false);
        btGetIDbarangJual1.setVisible(false);
        btGetIDbarangJual2.setVisible(false);
        btGetIDbarangJual3.setVisible(false);
        btGetIDbarangJual4.setVisible(false);
        btGetIDbarangJual5.setVisible(false);
        btGetIDbarangJual6.setVisible(false);
        btGetIDbarangJual7.setVisible(false);
        btGetIDbarangJual8.setVisible(false);
        btGetIDbarangJual9.setVisible(false);
        txJumlahBarangJual1.setVisible(false);
        txJumlahBarangJual2.setVisible(false);
        txJumlahBarangJual3.setVisible(false);
        txJumlahBarangJual4.setVisible(false);
        txJumlahBarangJual5.setVisible(false);
        txJumlahBarangJual6.setVisible(false);
        txJumlahBarangJual7.setVisible(false);
        txJumlahBarangJual8.setVisible(false);
        txJumlahBarangJual9.setVisible(false);
        txSubTotal1.setVisible(false);
        txSubTotal2.setVisible(false);
        txSubTotal3.setVisible(false);
        txSubTotal4.setVisible(false);
        txSubTotal5.setVisible(false);
        txSubTotal6.setVisible(false);
        txSubTotal7.setVisible(false);
        txSubTotal8.setVisible(false);
        txSubTotal9.setVisible(false);
        txSubTotalBarangJual.setText("Rp. 0");
        txJumlahBarangJualCopy.setVisible(false);
        txJumlahBarangJualCopy1.setVisible(false);
        txJumlahBarangJualCopy2.setVisible(false);
        txJumlahBarangJualCopy3.setVisible(false);
        txJumlahBarangJualCopy4.setVisible(false);
        txJumlahBarangJualCopy5.setVisible(false);
        txJumlahBarangJualCopy6.setVisible(false);
        txJumlahBarangJualCopy7.setVisible(false);
        txJumlahBarangJualCopy8.setVisible(false);
        txJumlahBarangJualCopy9.setVisible(false);
        txCariQueryJualCopy.setVisible(false);
        btBanyakBarang.setEnabled(false);
        jLabel38.setVisible(false);
        jComboBox2.setVisible(false);
        jTextField2.setVisible(false);
        lbCopyBayar.setVisible(false);
        jMenuItem1.setEnabled(false);
        jMenuItem2.setEnabled(false);
        jMenuItem3.setEnabled(false);
        jMenuItem4.setEnabled(false);
        jMenuItem5.setEnabled(false);
        jMenuItem6.setEnabled(false);
        
        /* COMPILE */
        btAdminDataAdmin.setEnabled(false);
        btAdminDataKasir.setEnabled(false);
        jTabbedPane1.setEnabled(false);
        lbStatusAdmin.setText("INFO : MAAF, APLIKASI TIDAK AKTIF, MOHON UNTUK LOGIN TERLEBIH DAHULU");
    }

}

//        private void transaksiJual() {     
//            try {
//                float hasilDiskon = 0;
//                double jumlah = 0;
//                diskonParse = 0;
//                diskonParse1 = 0;
//                double sisa = 0;
//                total = 0;
//                total1 = 0;
//                int harga = Integer.parseInt(txHargaBarangJual.getText());
//                int textJumlah = Integer.parseInt(txJumlahBarangJual.getText());
//                
//                double totall = Double.parseDouble(lbCopyBayar.getText());
//                
//                String diskon = null;            
//                    try {
//                        ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"'");
//                        while(rs.next()) {
//                            diskon  = rs.getString("DISKON");    
//                        }
//                    } catch (SQLException e) { }    
//                    diskonParse = Integer.parseInt(diskon);
//                    total = total+harga;
//                    float bayar = diskonParse/100*total;
//                    hasilDiskon = total-bayar;
//                    jumlah = hasilDiskon*textJumlah;
//                    sisa = totall-jumlah;
//                    lbKembalianJual.setText("Rp. "+dfo.format(sisa));
//            } catch (NumberFormatException e) { }
//
//        }
//        
//        private void transaksiJual1() {
//            try {
//                float hasilDiskon = 0;
//                float hasilDiskon11 = 0;
//                double jumlah = 0;
//                double jumlah11 = 0;
//                diskonParse = 0;
//                diskonParse1 = 0;
//                double sisa = 0;
//                total = 0;
//                total1 = 0;
//                int harga = Integer.parseInt(txHargaBarangJual.getText());
//                int textJumlah = Integer.parseInt(txJumlahBarangJual.getText());
//                int harga1 = Integer.parseInt(txHargaBarangJual1.getText());
//                int textJumlah1 = Integer.parseInt(txJumlahBarangJual1.getText());
//                
//                double totall = Double.parseDouble(lbCopyBayar.getText());
//                
//                String diskon = null;            
//                String diskon1 = null;
//                
//                try {
//                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"'");
//                    while(rs.next()) {
//                        diskon  = rs.getString("DISKON");    
//                        diskon1 = rs.getString("DISKON");
//                    }
//                    } catch (SQLException e) { }       
//                    diskonParse = Integer.parseInt(diskon);
//                    total = total+harga;
//                    
//                    diskonParse1 = Integer.parseInt(diskon1);
//                    total1 = total1+harga1;
//                    
//                     float bayar = diskonParse/100*total;
//                    hasilDiskon = total-bayar;
//                    
//                    float bayar1 = diskonParse1/100*total1;
//                    hasilDiskon11 = total1-bayar1;
//                    
//                    jumlah = hasilDiskon*textJumlah;
////                    sisa = totall-jumlah;                    
//                    jumlah11 = hasilDiskon11*textJumlah1;
//                    
//                    sisa = totall-jumlah-jumlah11;
//                    lbKembalianJual.setText("Rp. "+dfo.format(sisa));          
//                
//            } catch (NumberFormatException e) {
//            }
//        }
//        
//        private void transaksiJual2() {
//            try {
//                float hasilDiskon = 0;
//                float hasilDiskon11 = 0;
//                float hasilDiskon12 = 0;
//                double jumlah = 0;
//                double jumlah11 = 0;
//                double jumlah12 = 0;
//                diskonParse = 0;
//                diskonParse1 = 0;
//                diskonParse2 = 0;
//                double sisa = 0;
//                total = 0;
//                total1 = 0;
//                total2 = 0;
//                int harga = Integer.parseInt(txHargaBarangJual.getText());
//                int textJumlah = Integer.parseInt(txJumlahBarangJual.getText());
//                int harga1 = Integer.parseInt(txHargaBarangJual1.getText());
//                int textJumlah1 = Integer.parseInt(txJumlahBarangJual1.getText());
//                int harga2 = Integer.parseInt(txHargaBarangJual2.getText());
//                int textJumlah2 = Integer.parseInt(txJumlahBarangJual2.getText());
//                
//                double totall = Double.parseDouble(lbCopyBayar.getText());
//                
//                String diskon = null;            
//                String diskon1 = null;
//                String diskon2 = null;
//                
//                try {
//                    ResultSet rs = dbKoneksi.st.executeQuery("SELECT DISKON FROM IBUED.BARANG WHERE ID_BARANG='"+idBarang+"'");
//                    while(rs.next()) {
//                        diskon  = rs.getString("DISKON");    
//                        diskon1 = rs.getString("DISKON");
//                        diskon2 = rs.getString("DISKON");
//                    }
//                    } catch (SQLException e) { }       
//                    diskonParse = Integer.parseInt(diskon);
//                    total = total+harga;
//                    
//                    diskonParse1 = Integer.parseInt(diskon1);
//                    total1 = total1+harga1;
//                    
//                    diskonParse2 = Integer.parseInt(diskon2);
//                    total2 = total2+harga2;
//                    
//                    float bayar = diskonParse/100*total;
//                    hasilDiskon = total-bayar;
//                    
//                    float bayar1 = diskonParse1/100*total1;
//                    hasilDiskon11 = total1-bayar1;
//                    
//                    float bayar2 = diskonParse2/100*total2;
//                    hasilDiskon12 = total2-bayar2;
//                    
//                    jumlah = hasilDiskon*textJumlah;
////                    sisa = totall-jumlah;                    
//                    jumlah11 = hasilDiskon11*textJumlah1;
//                    jumlah12 = hasilDiskon12*textJumlah2;
//                    
//                    sisa = (int) (totall-jumlah-jumlah11-jumlah12);
//                    lbKembalianJual.setText("Rp. "+dfo.format(sisa));          
//                
//            } catch (NumberFormatException e) {
//            }
//        }
