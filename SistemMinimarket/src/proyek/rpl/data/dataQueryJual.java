

package proyek.rpl.data;

/**
 *
 * @author ibued
 */
public class dataQueryJual {
    int nomor;
    String idJual;
    String tglJual;
    String idPlgJual;
    String idKasirJual;
    String totalJual;
    String bayar;
    String kembalian;
    String namaPelanggan;
    String namaKasir;
    
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
    
    public String getNamaPelanggan() {
        return namaPelanggan;
    }
    public void setNamaPelanggan(String plg) {
        this.namaPelanggan = plg;
    }
    
    public String getNamaKasir() {
        return namaKasir;
    }
    public void setNamaKasir(String kas) {
        this.namaKasir = kas;
    }
    
}
