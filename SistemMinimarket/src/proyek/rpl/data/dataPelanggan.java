

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
public class dataPelanggan {
    
    int no;
    String idPelanggan;
    String namaPelanggan;
    String jkPelanggan;
    String alamatPelanggan;
    String teleponPelanggan;
    
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
    
    public String getJenisKelamin() {
        return jkPelanggan;
    }
    public void setJenisKelamin(String jkPlg) {
        this.jkPelanggan = jkPlg;
    }
    
     public String getAlamatPelanggan() {
        return alamatPelanggan;
    }
    public void setAlamatPelanggan(String alamatPlg) {
        this.alamatPelanggan = alamatPlg;
    }
    
     public String getTeleponPelanggan() {
        return teleponPelanggan;
    }
    public void setTeleponPelanggan(String  telpPlg) {
        this.teleponPelanggan = telpPlg;
    }
}
