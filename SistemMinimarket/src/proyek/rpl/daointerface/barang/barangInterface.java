

package proyek.rpl.daointerface.barang;

import java.sql.SQLException;
import proyek.rpl.data.dataBarang;

/**
 *
 * @author ibued
 */
public interface barangInterface {
    public boolean insert(dataBarang barang) throws SQLException;
    public boolean update(dataBarang barang, String idBrng) throws SQLException;
    public void delete(String idBrng) throws SQLException;
    
    public void tampilBarang() throws SQLException; 
    public void tampilIDkat() throws SQLException; 
    public void tampilIDsat() throws SQLException;   
    
    public void mouseClickedKategori(String txIDkat) throws SQLException;
    public void mouseClickedSatuan(String txIDsat) throws SQLException;
}

