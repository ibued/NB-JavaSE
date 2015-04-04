

package proyek.rpl.daointerface.barang;

import java.sql.SQLException;
import proyek.rpl.data.dataKategori;

/**
 *
 * @author ibued
 */
public interface kategoriInterface {
    
    public boolean tambahKategori(dataKategori kategori) throws SQLException;
    public boolean editKategori(dataKategori kategori, String idKat) throws SQLException;
    public boolean hapusKategori(String idKat) throws SQLException;
}
