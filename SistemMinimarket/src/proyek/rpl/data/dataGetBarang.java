

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
public class dataGetBarang {
    
    int no;
    String idBarang;
    String namaNamaBarang;
    String hargaBarang;
    String stok;
    String diskonBarang;
    
    
    public int getNomorBarang() {
        return no;
    }
    public void setNomorBarang(int no) {
        this.no = no;
    }
    
    public String getIDbarang() {
        return idBarang;
    }
    public void setIDbarang(String idBrng) {
        this.idBarang = idBrng;
    }
    
    public String getNamaBarang() {
        return namaNamaBarang;
    }
    public void setNamaBarang(String namaBrng) {
        this.namaNamaBarang = namaBrng;
    }  
    
    public String getHargaBarang() {
        return hargaBarang;
    }
    public void setHargaBarang(String hargaBrng) {
        this.hargaBarang = hargaBrng;
    }
    
    public String getStok() {
        return stok;
    }
    public void setStok(String stok) {
        this.stok = stok;
    }
    
    public String getDiskonBarang() {
        return diskonBarang;
    }
    public void setDiskonBarang(String diskonBrng) {
        this.diskonBarang = diskonBrng;
    }  
    
}
