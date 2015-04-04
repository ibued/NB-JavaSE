
package proyek.rpl.database.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import proyek.rpl.data.dataQueryJual;
import proyek.rpl.data.dataQueryJualDetil;
import proyek.rpl.dataModel.modelDataQueryJual;
import proyek.rpl.dataModel.modelDataQueryJualDetil;
import proyek.rpl.database.dbKoneksi;

/**
 * @see MEMBUAT QUERY
 * @author ibued
 */
public class queryJual {
    
    private final modelDataQueryJual dataQuery = new modelDataQueryJual();
    private final modelDataQueryJualDetil queryJualDetil = new modelDataQueryJualDetil();
    
    public void tampilQueryJual() {
        int no = 1;
        try {
            String tampil = ("SELECT Jual.*, "
                    + "Pelanggan.NAMA_PELANGGAN, "
                    + "Kasir.NAMA_KASIR "
                    + "FROM Pelanggan "
                    + "RIGHT JOIN (Kasir "
                    + "RIGHT JOIN Jual "
                    + "ON Kasir.ID_KASIR = Jual.ID_KASIR) "
                    + "ON Pelanggan.ID_PELANGGAN = Jual.ID_PELANGGAN "
                    + "ORDER BY Jual.ID_JUAL");
            ResultSet rs = dbKoneksi.st.executeQuery(tampil);
            
            while(rs.next()) {
                dataQueryJual queryJual = new dataQueryJual();
                queryJual.setNomorJual(no++);
                queryJual.setIDJual(rs.getString("ID_JUAL"));
                queryJual.setTanggalJual(rs.getString("TANGGAL_JUAL"));
                queryJual.setIDPelangganJual(rs.getString("ID_PELANGGAN"));
                queryJual.setIDKasirJual(rs.getString("ID_KASIR"));
                queryJual.setTotalJual(rs.getString("TOTAL"));
                queryJual.setBayarJual(rs.getString("BAYAR"));
                queryJual.setKembalianJual(rs.getString("KEMBALIAN"));
                queryJual.setNamaPelanggan(rs.getString("NAMA_PELANGGAN"));
                queryJual.setNamaKasir(rs.getString("NAMA_KASIR"));
                dataQuery.insert(queryJual);
            }
        } catch (SQLException e) { }
    }   
    
    public void tampilQueryJualDetil() {
        int no = 1;
        try {
            String tampil = ("SELECT JUAL_DETIL.*, "
                    + "BARANG.NAMA_BARANG, "
                    + "BARANG.HARGA, "
                    + "SATUAN.NAMA_SATUAN, JUAL_DETIL.JUMLAH * BARANG.HARGA AS SUB_TOTAL "
                    + "FROM SATUAN "
                    + "RIGHT JOIN (BARANG "
                    + "RIGHT JOIN JUAL_DETIL "
                    + "ON BARANG.ID_BARANG = JUAL_DETIL.ID_BARANG) "
                    + "ON SATUAN.ID_SATUAN = BARANG.ID_SATUAN");
            ResultSet rs = dbKoneksi.st.executeQuery(tampil);
            
            while (rs.next()) {
                dataQueryJualDetil jualDetil = new dataQueryJualDetil();
                jualDetil.setNomorJual(no++);
                jualDetil.setIDJualDetil(rs.getString("ID_JUAL_DETIL"));
                jualDetil.setIDJual(rs.getString("ID_JUAL"));
                jualDetil.setIDbarang(rs.getString("ID_BARANG"));
                jualDetil.setJumlah(rs.getString("JUMLAH"));
                jualDetil.setNamaBarang(rs.getString("NAMA_BARANG"));
                jualDetil.setHarga(rs.getString("HARGA"));
                jualDetil.setNamaSatuan(rs.getString("NAMA_SATUAN"));
                jualDetil.setSubTotal(rs.getString("SUB_TOTAL"));
                queryJualDetil.insert(jualDetil);
            }
        } catch (SQLException e) { }
    }
    
}
