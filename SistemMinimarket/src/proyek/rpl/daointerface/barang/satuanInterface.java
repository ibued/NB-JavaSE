
package proyek.rpl.daointerface.barang;

import proyek.rpl.data.dataSatuan;

/**
 *
 * @author ibued
 */
public interface satuanInterface {
    public boolean tambahDataSatuan(dataSatuan satuan);
    public boolean editDataSatuan(dataSatuan satuan, String idSat);
    public boolean hapusDataSatuan(String idSat);
}
