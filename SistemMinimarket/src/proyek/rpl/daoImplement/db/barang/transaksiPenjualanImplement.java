
package proyek.rpl.daoImplement.db.barang;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.daointerface.barang.transaksiPenjualanInterface;
import proyek.rpl.data.dataGetBarang;
import proyek.rpl.data.dataGetPelanggan;
import proyek.rpl.data.dataTransaksiPenjualan;
import proyek.rpl.dataModel.modelGetBarang;
import proyek.rpl.dataModel.modelGetPelanggan;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public class transaksiPenjualanImplement implements transaksiPenjualanInterface{
    
    private final modelGetPelanggan pelangganM = new modelGetPelanggan();
    private final modelGetBarang barangM = new modelGetBarang();

    @Override
    public boolean simpan(dataTransaksiPenjualan penjualan) {
        try {
            String tambahJual = ("INSERT INTO IBUED.JUAL VALUES('"+penjualan.getIDJual()+"','"+penjualan.getTanggalJual()+"','"+penjualan.getIDPelangganJual()+"',"
                    + "'"+penjualan.getIDKasirJual()+"','"+penjualan.getTotalJual()+"','"+penjualan.getBayarJual()+"','"+penjualan.getKembalianBayar()+"')");
            dbKoneksi.st.executeUpdate(tambahJual);        
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Penjualan Berhasil di Simpan","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Penjualan Gagal di Simpan","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(dataTransaksiPenjualan penjualan, String idJual) {
        try {
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hapus(String idJual) {
         try {
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void tampilDataPenjualan() {
        //To change body of generated methods, choose Tools | Templates.
    }    

    @Override
    public void tampilIDpelanggan() {
        int no = 1;
        try {
            ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.PELANGGAN ORDER BY ID_PELANGGAN ASC");
            while(rs.next()) {
                dataGetPelanggan getPelanggan = new dataGetPelanggan();
                getPelanggan.setNomorPelanggan(no++);
                getPelanggan.setIDpelanggan(rs.getString(1));
                getPelanggan.setNamaPelanggan(rs.getString(2));
                pelangganM.insert(getPelanggan);
            }
        } catch (SQLException e) { }
    }
    
    @Override
    public void tampilIDbarang() {
        int no = 1;        
        try {
            ResultSet rs = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.BARANG ORDER BY ID_BARANG ASC");
            while(rs.next()) {
                dataGetBarang getBarang = new dataGetBarang();
                getBarang.setNomorBarang(no++);
                getBarang.setIDbarang(rs.getString(1));
                getBarang.setNamaBarang(rs.getString(2));
                getBarang.setStok(rs.getString("STOK"));
                getBarang.setHargaBarang(rs.getString("HARGA"));
                getBarang.setDiskonBarang(rs.getString("DISKON"));
                barangM.insert(getBarang);
            }
        } catch (SQLException e) { }
    } 
}
