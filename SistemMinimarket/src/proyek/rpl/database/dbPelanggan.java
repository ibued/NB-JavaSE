

package proyek.rpl.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.data.dataPelanggan;
import proyek.rpl.dataModel.modelDataPelanggan;

/**
 *
 * @author ibued
 */
public class dbPelanggan {    
    private final modelDataPelanggan dataPelanggan = new modelDataPelanggan(); 
    
    public boolean tambahData(dataPelanggan pelanggan) {
        boolean cek = true;       
        try {
            String tambah = (" INSERT INTO IBUED.PELANGGAN VALUES ('"+pelanggan.getIDpelanggan()+"','"+pelanggan.getNamaPelanggan()+"',"
                    + "'"+pelanggan.getJenisKelamin()+"','"+pelanggan.getAlamatPelanggan()+"','62"+pelanggan.getTeleponPelanggan()+"') ");
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
    
    public boolean editDataPelanggan(dataPelanggan pelanggan, String idPelanggan) {
        boolean cek = true;
        try {
            String edit = ("UPDATE IBUED.PELANGGAN SET NAMA_PELANGGAN='"+pelanggan.getNamaPelanggan()+"', JENIS_KELAMIN='"+pelanggan.getJenisKelamin()+"',"
                    + "ALAMAT='"+pelanggan.getAlamatPelanggan()+"',NO_TELP='62"+pelanggan.getTeleponPelanggan()+"' WHERE ID_PELANGGAN='"+idPelanggan+"' ");
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
    
    public boolean hapusPelanggan(String idPlg) {
        boolean cek = true;        
            try {
                String hapus = ("DELETE FROM IBUED.PELANGGAN WHERE ID_PELANGGAN='"+idPlg+"'");
                dbKoneksi.st.executeUpdate(hapus);
                dbKoneksi.conn.commit();
                JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal di Hapus","INFO",
                        JOptionPane.INFORMATION_MESSAGE);
            }        
        return cek;
    }
    
    public void tampilDataPelanggan() {
        
        try {
            int no = 1;       
            ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.PELANGGAN ORDER BY ID_PELANGGAN ASC");
            dbKoneksi.conn.commit();
            while(resultSet.next()) {
                dataPelanggan dtPelanggan = new dataPelanggan();
                dtPelanggan.setNomorPelanggan(no++);
                dtPelanggan.setIDpelanggan(resultSet.getString(1));
                dtPelanggan.setNamaPelanggan(resultSet.getString(2));
                dtPelanggan.setJenisKelamin(resultSet.getString(3));
                dtPelanggan.setAlamatPelanggan(resultSet.getString(4));
                dtPelanggan.setTeleponPelanggan(resultSet.getString(5).substring(2));
                dataPelanggan.insert(dtPelanggan);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }   
        
}
