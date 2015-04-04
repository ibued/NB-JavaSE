

package proyek.rpl.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.data.dataKasir;
import proyek.rpl.dataModel.modelDataKasir;

/**
 *
 * @author ibued
 */
public class dbKasir {
    
    modelDataKasir dataKasirM = new modelDataKasir();
    
    public boolean tambahKasirAdmin(dataKasir kasir) {
            boolean cek = true;
            try {
                String tambahKasir = ("INSERT INTO IBUED.KASIR VALUES ('"+kasir.getIDkasir()+"', '"+kasir.getNamakasir()+"', '"+kasir.getTelpKasir()+"',"
                        + "'"+kasir.getUsernameKasir()+"', '"+kasir.getPasswordKasir()+"') ");
                dbKoneksi.st.executeUpdate(tambahKasir);
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
    
    public void editDataKasir(dataKasir kasir, String idKas) {
        try {
            String edit = ("UPDATE IBUED.KASIR SET NAMA_KASIR='"+kasir.getNamakasir()+"', TELEPON='"+kasir.getTelpKasir()+"', "
                    + "USERNAME='"+kasir.getUsernameKasir()+"', PASSWORD='"+kasir.getPasswordKasir()+"' WHERE ID_KASIR='"+idKas+"' ");
            dbKoneksi.st.executeUpdate(edit);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Edit", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Edit", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean hapusKasir(String idKasir) {
        boolean cek = true;        
            try {
                String hapus = ("DELETE FROM IBUED.KASIR WHERE ID_KASIR='"+idKasir+"'");
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
    
    // FRAME ADMIN
    public void tampilKasirAdmin() {
                      
            try {
                int no = 1;  
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.KASIR ORDER BY ID_KASIR ASC");
                while(resultSet.next()) {
                    dataKasir kasir = new dataKasir();
                    kasir.setNomorKasir(no++);
                    kasir.setIDKasir(resultSet.getString(1));
                    kasir.setNamaKasir(resultSet.getString(2));
                    kasir.setTelpKasir(resultSet.getString(3));
                    kasir.setUsernameKasir(resultSet.getString(4));
                    kasir.setPasswordKasir(resultSet.getString(5));
                    dataKasirM.insert(kasir);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
   
}
