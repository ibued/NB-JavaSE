

package proyek.rpl.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import proyek.rpl.data.dataAdmin;
import proyek.rpl.dataModel.modelDataAdmin;

/**
 *
 * @author ibued
 */
public class dbAdmin {
    
    private final modelDataAdmin dataAdminM = new modelDataAdmin();
    
    public boolean tambahDataAdmin(dataAdmin admin) {
        try {
            String tambah = ("INSERT INTO IBUED.ADMIN VALUES ('"+admin.getIDadmin()+"','"+admin.getUsernameAdmin()+"','"+admin.getPasswdAdmin()+"') ");
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
    
    public boolean editDataAdmin(dataAdmin admin, String idAdmin) {
        try {
            String edit = ("UPDATE IBUED.ADMIN SET USERNAME='"+admin.getUsernameAdmin()+"', PASSWORD='"+admin.getPasswdAdmin()+"' WHERE ID_ADMIN='"+idAdmin+"' ");
            dbKoneksi.st.executeUpdate(edit);
            dbKoneksi.conn.commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Edit", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Edit", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean hapusDataAdmin(String idAdmin) {
        boolean cek = true;        
            try {
                String hapus = ("DELETE FROM IBUED.ADMIN WHERE ID_ADMIN='"+idAdmin+"'");
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
    
    public void tampilDataAdmin() {
                      
            try {
                int no = 1;  
                ResultSet resultSet = dbKoneksi.st.executeQuery("SELECT * FROM IBUED.ADMIN ORDER BY ID_ADMIN ASC");
                while(resultSet.next()) {
                    dataAdmin admin = new dataAdmin();
                    admin.setNomorAdmin(no++);
                    admin.setIDadmin(resultSet.getString(1));
                    admin.setUsernameAdmin(resultSet.getString(2));
                    admin.setPasswdAdmin(resultSet.getString(3));
                    dataAdminM.insert(admin);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    
}
