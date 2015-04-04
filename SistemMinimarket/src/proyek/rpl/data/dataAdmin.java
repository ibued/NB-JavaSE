

package proyek.rpl.data;

/**
 *
 * @author ibued
 */
public class dataAdmin {
    
    int nomor;
    String idAdmin;
    String usernameAdmin;
    String passwdAdmin;
    
    public int getNomorAdmin() {
        return nomor;
    }
    public void setNomorAdmin(int no) {
        this.nomor=no;
    }
    
    public String getIDadmin() {
        return idAdmin;
    }
    public void setIDadmin(String idAdmin) {
        this.idAdmin=idAdmin;
    }
    
    public String getUsernameAdmin() {
        return usernameAdmin;
    }
    public void setUsernameAdmin(String username) {
        this.usernameAdmin=username;
    }
    
    public String getPasswdAdmin() {
        return passwdAdmin;
    }
    public void setPasswdAdmin(String passwd) {
        this.passwdAdmin=passwd;
    }  
    
}
