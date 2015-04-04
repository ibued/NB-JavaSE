

package proyek.rpl.daoImplement.db.barang;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.daointerface.barang.barangInterface;
import proyek.rpl.data.dataBarang;
import proyek.rpl.data.dataKategori;
import proyek.rpl.data.dataSatuan;
import proyek.rpl.dataModel.modelDataBarang;
import proyek.rpl.dataModel.modelKategori;
import proyek.rpl.dataModel.modelSatuan;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public class barangImplement implements barangInterface{
    
    private final modelDataBarang dataBarang = new modelDataBarang();
    private final modelKategori dataKategori = new modelKategori();
    private final modelSatuan dataSatuan = new modelSatuan();

    @Override
    public boolean insert(dataBarang barang) throws SQLException {
        boolean cek = true;
        try {
            String tambah = (" INSERT INTO BARANG VALUES('"+barang.getIDBarang()+"','"+barang.getNamaBarang()+"','"+barang.getHargaBarang()+"',"
                    + "'"+barang.getStokBarang()+"','"+barang.getIDkategori()+"','"+barang.getIDsatuan()+"','"+barang.getDiskonBarang()+"') ");
            dbKoneksi.st.executeUpdate(tambah);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Tambah","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            cek = false;
        }
        return cek;
    }
    
    @Override
    public boolean update(dataBarang barang, String idBrng) throws SQLException {
        boolean cek = true;
        try {
            String edit = ("UPDATE IBUED.BARANG SET NAMA_BARANG='"+barang.getNamaBarang()+"', HARGA='"+barang.getHargaBarang()+"', "
                    + "STOK='"+barang.getStokBarang()+"', ID_KATEGORI='"+barang.getIDkategori()+"', ID_SATUAN='"+barang.getIDsatuan()+"', "
                    + "DISKON='"+barang.getDiskonBarang()+"' WHERE ID_BARANG='"+idBrng+"' ");
            dbKoneksi.st.executeUpdate(edit);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Edit","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Edit","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            cek = false;
        }
        return cek;
    }
    
    @Override
    public void delete(String idBrng) throws SQLException {
        try {
            String hapus = ("DELETE FROM IBUED.BARANG WHERE ID_BARANG='"+idBrng+"'");
            dbKoneksi.st.executeUpdate(hapus);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Hapus","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void tampilBarang() throws SQLException {
        try {
            int no = 1;       
            ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.BARANG ORDER BY ID_BARANG ASC");
            while(resultSet.next()) {
                dataBarang barang = new dataBarang();
                barang.setNomorBarang(no++);  
                barang.setIDbarang(resultSet.getString(1));                
                barang.setNamaBarang(resultSet.getString(2));
                barang.setHargaBarang(resultSet.getString(3));
                barang.setStokBarang(resultSet.getString(4));
                barang.setIDkategori(resultSet.getString(5));
                barang.setIDsatuan(resultSet.getString(6));                
                barang.setDiskonBarang(resultSet.getString(7));
                dataBarang.insert(barang);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }    

    @Override
    public void tampilIDkat() throws SQLException {
        int no = 1;
        try {
            ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KATEGORI ORDER BY ID_KATEGORI ASC");
            while(resultSet.next()) {
                dataKategori kategori = new dataKategori();
                kategori.setNomorKategori(no++);
                kategori.setIDkategori(resultSet.getString(1));
                kategori.setNamaKategori(resultSet.getString(2));
                dataKategori.insert(kategori);
            }
            dbKoneksi.conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void tampilIDsat() throws SQLException {
        int no = 1;
        try {
            ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.SATUAN ORDER BY ID_SATUAN ASC");
            while(resultSet.next()) {
                dataSatuan satuan = new dataSatuan();
                satuan.setNomorSatuan(no++);
                satuan.setIDsatuan(resultSet.getString(1));
                satuan.setNamaSatuan(resultSet.getString(2));
                dataSatuan.insert(satuan);
            }
            dbKoneksi.conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }  
    
    @Override
    public void mouseClickedKategori(String txIDkat) throws SQLException {
        String idKat = null;
        try {
            ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_KATEGORI FROM IBUED.KATEGORI WHERE ID_KATEGORI='"+txIDkat+"'");
            while(rs.next()) {
                idKat = rs.getString("ID_KATEGORI");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } 
    }

    @Override
    public void mouseClickedSatuan(String txIDsat) throws SQLException {
        String idSat = null;
        try {
            ResultSet rs = dbKoneksi.st.executeQuery("SELECT ID_SATUAN FROM IBUED.SATUAN WHERE ID_SATUAN='"+txIDsat+"'");
            while(rs.next()) {
                idSat = rs.getString("ID_SATUAN");
            }
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }    
    
}
