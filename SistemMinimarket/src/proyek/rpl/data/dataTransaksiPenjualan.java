

package proyek.rpl.data;

/**
 *
 * @author ibued
 */
public class dataTransaksiPenjualan {
    
    int nomor;
    String idJual;
    String tglJual;
    String idPlgJual;
    String idKasirJual;
    String idBarangJual;
    String hargaBarangJual;
    String jumlahJual;
    String totalJual;
    String bayar;
    String kembalian;
    
    public int getNomorJual() {
        return nomor;
    }
    public void setNomorJual(int no) {
        this.nomor = no;
    }
    
    public String getIDJual() {
        return idJual;
    }
    public void setIDJual(String idJual) {
        this.idJual = idJual;
    }
    
    public String getTanggalJual() {
        return tglJual;
    }
    public void setTanggalJual(String tglJual) {
        this.tglJual = tglJual;
    }
    
    public String getIDPelangganJual() {
        return idPlgJual;
    }
    public void setIDPelangganJual(String idPlgJual) {
        this.idPlgJual = idPlgJual;
    }
    
    public String getIDKasirJual() {
        return idKasirJual;
    }
    public void setIDKasirJual(String idKasirJual) {
        this.idKasirJual = idKasirJual;
    }
    
    public String getIDBarangJual() {
        return idBarangJual;
    }
    public void setIDBarangJual(String idBrngJual) {
        this.idBarangJual = idBrngJual;
    }
    
    public String getHargaBarangJual() {
        return hargaBarangJual;
    }
    public void setHargaBarangJual(String hargaJual) {
        this.hargaBarangJual = hargaJual;
    }
    
    public String getJumlahJual() {
        return jumlahJual;
    }
    public void setJumlahJual(String jumlahJual) {
        this.jumlahJual = jumlahJual;
    }
    
    public String getTotalJual() {
        return totalJual;
    }
    public void setTotalJual(String totalJual) {
        this.totalJual = totalJual;
    }
    
    public String getBayarJual() {
        return bayar;
    }
    public void setBayarJual(String bayarJual) {
        this.bayar = bayarJual;
    }
    
    public String getKembalianBayar() {
        return kembalian;
    }
    public void setKembalianJual(String kembalianJual) {
        this.kembalian = kembalianJual;
    }
    
}
