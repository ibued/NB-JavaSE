
package proyek.rpl.daointerface.barang;

import proyek.rpl.data.dataTransaksiPenjualan;

/**
 *
 * @author ibued
 */
public interface transaksiPenjualanInterface {
    public boolean simpan(dataTransaksiPenjualan penjualan);
    public boolean edit(dataTransaksiPenjualan penjualan, String idJual);
    public boolean hapus(String idJual);
    public void tampilDataPenjualan();
    public void tampilIDpelanggan();
    public void tampilIDbarang();
}
