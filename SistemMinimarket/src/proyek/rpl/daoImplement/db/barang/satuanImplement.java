

package proyek.rpl.daoImplement.db.barang;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.daointerface.barang.satuanInterface;
import proyek.rpl.data.dataSatuan;
import proyek.rpl.database.dbKoneksi;

/**
 *
 * @author ibued
 */
public class satuanImplement implements satuanInterface{

    @Override
    public boolean tambahDataSatuan(dataSatuan satuan) {
        try {
            String tambah = ("INSERT INTO IBUED.SATUAN VALUES('"+satuan.getIDSatuan()+"','"+satuan.getNamaSatuan()+"')");
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
    public boolean editDataSatuan(dataSatuan satuan, String idSat) {
        try {
            String edit = ("UPDATE IBUED.SATUAN SET NAMA_SATUAN='"+satuan.getNamaSatuan()+"' WHERE ID_SATUAN='"+idSat+"'");
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
    public boolean hapusDataSatuan(String idSat) {
        try {
            String hapus = ("DELETE FROM IBUED.SATUAN WHERE ID_SATUAN='"+idSat+"'");
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
