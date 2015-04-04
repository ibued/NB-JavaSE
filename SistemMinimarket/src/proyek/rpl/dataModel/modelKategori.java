

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataKategori;

/**
 *
 * @author ibued
 */
public class modelKategori extends AbstractTableModel{
    
    public  static Vector<dataKategori> fieldKategori=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldKategori.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldKategori.get(rowIndex).getNomorKategori();
            case 1 : return fieldKategori.get(rowIndex).getIDkategori();
            case 2 : return fieldKategori.get(rowIndex).getNamaKategori();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {        
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID KATEGORI";
            case 2 : return "NAMA KATEGORI";           
            default: return null;
        }
    }
    
    public void insert(dataKategori dtp){
        fieldKategori.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataKategori dtp, int index){
        fieldKategori.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldKategori.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldKategori.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
        
}
