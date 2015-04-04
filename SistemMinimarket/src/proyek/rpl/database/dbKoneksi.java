
package proyek.rpl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Created 12 Mey 2014 Today 5:04:06 PM
 * @author ibued
 */
public class dbKoneksi {
    public static Statement st;
    public static Connection conn;
    public ResultSet rs;   
    
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String user = "IBUED";
    String pass = "budi";
    String driver = "oracle.jdbc.driver.OracleDriver";    
    public boolean c = false;
    
    public void openKoneksi() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);            
            c = true;
            st = conn.createStatement();
//            JOptionPane.showMessageDialog(null, "Koneksi Berhasil !!!","Info",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal !!!\nStart Dulu Oracle !!!","Info",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    /* ==== KONEKSI UNTUK iReport */
    public Connection getConnection() throws ClassNotFoundException, SQLException { 
         try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            // JOptionPane.showMessageDialog(null, "Koneksi Berhasil","Info", JOptionPane.INFORMATION_MESSAGE);
            c = true;
            st=conn.createStatement();
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal","Info",JOptionPane.INFORMATION_MESSAGE);
            // System.exit(0);
        }
        return null;
    }
    /* ==== KONEKSI UNTUK iReport */
}
