

package proyek.rpl.data;

/**
 *
 * @author ibued
 */
public class dataQueryJualDetil {
    
    int nomor;
    String idJualDetil;
    String idJual;
    String idBarang;
    String jumlah;
    String namaBarang;
    String harga;
    String namaSatuan;
    String subTotal;
    
     public int getNomorJual() {
        return nomor;
    }
    public void setNomorJual(int no) {
        this.nomor = no;
    }
    
    public String getIDJualDetil() {
        return idJualDetil;
    }
    public void setIDJualDetil(String idJualDetil) {
        this.idJualDetil = idJualDetil;
    }
    
    public String getIDJual() {
        return idJual;
    }
    public void setIDJual(String idJual) {
        this.idJual = idJual;
    }
    
    public String getIDbarang() {
        return idBarang;
    }
    public void setIDbarang(String barang) {
        this.idBarang = barang;
    }
    
    public String getJumlah() {
        return jumlah;
    }
    public void setJumlah(String jum) {
        this.jumlah = jum;
    }    
    
    public String getNamaBarang() {
        return namaBarang;
    }
    public void setNamaBarang(String namaBrng) {
        this.namaBarang = namaBrng;
    }
    
    public String getHarga() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }
    
    public String getNamaSatuan() {
        return namaSatuan;
    }
    public void setNamaSatuan(String satuan) {
        this.namaSatuan = satuan;
    }
    
    public String getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(String total) {
        this.subTotal = total;
    }
    
}
