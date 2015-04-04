
package proyek.rpl.data;

/**
 *
 * @author ibued
 */
public class dataBarang {
    int no;
    String idBarang;
    String idKategori;
    String idSatuan;
    String namaBarang;
    String harga;
    String stok;
    String diskon;
    
//    public dataBarang() {
//        
//    }
//    public dataBarang(int nomor) {
//        this.no=nomor;
//    }
    
    public int getNomorBarang() {
        return no;
    }
    public void setNomorBarang(int no) {
        this.no = no;
    }
    
    public String getIDBarang() {
        return idBarang;
    }
    public void setIDbarang(String idBrng) {
        this.idBarang = idBrng;
    }
    
    public String getIDkategori() {
        return idKategori;
    }
    public void setIDkategori(String idKat) {
        this.idKategori = idKat;
    }
    
    public String getIDsatuan() {
        return idSatuan;
    }
    public void setIDsatuan(String idSat) {
        this.idSatuan = idSat;
    }
    
    public String getNamaBarang() {
        return namaBarang;
    }
    public void setNamaBarang(String namaBrng) {
        this.namaBarang = namaBrng;
    }
    
    public String getHargaBarang() {
        return harga;
    }
    public void setHargaBarang(String  hargaBrng) {
        this.harga = hargaBrng;
    }
    
    public String getStokBarang() {
        return stok;
    }
    public void setStokBarang(String  stokBrng) {
        this.stok = stokBrng;
    }
    
    public String getDiskonBarang() {
        return diskon;
    }
    public void setDiskonBarang(String  diskonBrng) {
        this.diskon = diskonBrng;
    }
    
    
}
