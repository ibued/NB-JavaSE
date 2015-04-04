

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataTransaksiPenjualan;

/**
 *
 * @author ibued
 */
public class modelTransaksiPenjualan extends AbstractTableModel{
    
    public static Vector<dataTransaksiPenjualan> fieldJual=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldJual.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldJual.get(rowIndex).getNomorJual();
            case 1 : return fieldJual.get(rowIndex).getTanggalJual();
            case 2 : return fieldJual.get(rowIndex).getIDPelangganJual();
            case 3 : return fieldJual.get(rowIndex).getIDKasirJual();
            case 4 : return fieldJual.get(rowIndex).getIDBarangJual();
            case 5 : return fieldJual.get(rowIndex).getHargaBarangJual();
            case 6 : return fieldJual.get(rowIndex).getJumlahJual();
            case 7 : return fieldJual.get(rowIndex).getTotalJual();
            case 8 : return fieldJual.get(rowIndex).getBayarJual();
            case 9 : return fieldJual.get(rowIndex).getKembalianBayar();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "TANGGAL JUAL";
            case 2 : return "ID PELANGGAN";
            case 3 : return "ID KASIR";
            case 4 : return "ID BARANG";
            case 5 : return "HARGA BARANG";
            case 6 : return "JUMLAH BARANG";
            case 7 : return "SUB TOTAL";
            case 8 : return "PEMBAYARAN";
            case 9 : return "KEMBALIAN";
            default: return null;
        }
    }
    
    public void insert(dataTransaksiPenjualan dtp){
        fieldJual.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataTransaksiPenjualan dtp, int index){
        fieldJual.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldJual.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldJual.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
    
}
