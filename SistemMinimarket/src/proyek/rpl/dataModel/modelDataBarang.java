

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataBarang;

/**
 *
 * @author ibued
 */
public class modelDataBarang extends AbstractTableModel{
    
    public  static Vector<dataBarang> fieldBrng=new Vector<>();
    
    @Override
    public int getRowCount() {
        return fieldBrng.size();
    }
    
    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldBrng.get(rowIndex).getNomorBarang();
            case 1 : return fieldBrng.get(rowIndex).getIDBarang();
            case 2 : return fieldBrng.get(rowIndex).getNamaBarang();
            case 3 : return fieldBrng.get(rowIndex).getIDkategori();
            case 4 : return fieldBrng.get(rowIndex).getIDsatuan();            
            case 5 : return fieldBrng.get(rowIndex).getHargaBarang();
            case 6 : return fieldBrng.get(rowIndex).getStokBarang();
            case 7 : return fieldBrng.get(rowIndex).getDiskonBarang();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID BARANG";
            case 2 : return "NAMA BARANG";
            case 3 : return "ID KATEGORI";
            case 4 : return "ID SATUAN";                       
            case 5 : return "HARGA BARANG";
            case 6 : return "STOK";
            case 7 : return "DISKON";
            default: return null;
        }
    }    
    
    public void insert(dataBarang dtp){
        fieldBrng.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataBarang dtp, int index){
        fieldBrng.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldBrng.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldBrng.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
    
}
