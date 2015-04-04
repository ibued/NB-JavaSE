

package proyek.rpl.data;

/**
 * 
 * @author ibued
 */

/**
 * 
 * @Entity
 * @Table (name = "PELANGGAN")
 */
public class dataGetPelanggan {
    
    int no;
    String idPelanggan;
    String namaPelanggan;
    
    
    public int getNomorPelanggan() {
        return no;
    }
    public void setNomorPelanggan(int no) {
        this.no = no;
    }
    
    public String getIDpelanggan() {
        return idPelanggan;
    }
    public void setIDpelanggan(String idPlg) {
        this.idPelanggan = idPlg;
    }
    
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaPelanggan(String namaPlg) {
        this.namaPelanggan = namaPlg;
    }   
    
}
