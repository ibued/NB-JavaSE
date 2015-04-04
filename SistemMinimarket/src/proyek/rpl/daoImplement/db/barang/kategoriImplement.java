

package proyek.rpl.daoImplement.db.barang;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.daointerface.barang.kategoriInterface;
import proyek.rpl.data.dataKategori;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public class kategoriImplement implements kategoriInterface{     

    @Override
    public boolean tambahKategori(dataKategori kategori) throws SQLException {
        try {
            String tambah = ("INSERT INTO IBUED.KATEGORI VALUES('"+kategori.getIDkategori()+"', '"+kategori.getNamaKategori()+"')");
            dbKoneksi.st.executeUpdate(tambah);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Tambah","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean editKategori(dataKategori kategori, String idKat) throws SQLException {
        try {
            String edit = ("UPDATE IBUED.KATEGORI SET NAMA_KATEGORI='"+kategori.getNamaKategori()+"' WHERE ID_KATEGORI='"+idKat+"'");
            dbKoneksi.st.executeUpdate(edit);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Edit","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Edit","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean hapusKategori(String idKat) throws SQLException {
        try {
            String hapus = ("DELETE FROM IBUED.KATEGORI WHERE ID_KATEGORI='"+idKat+"'");
            dbKoneksi.st.executeUpdate(hapus);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "<html>\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b<b>PERINGATAN\n"
                    + "\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bDATA GAGAL DIHAPUS\n"
                    + "DIKARENAKAN DATA TERSEBUT MEMILIKI RELASI ANTAR TABEL BARANG","WARNING",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }    
    
}
