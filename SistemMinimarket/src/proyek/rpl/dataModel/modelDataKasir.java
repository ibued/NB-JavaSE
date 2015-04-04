

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataKasir;

/**
 *
 * @author ibued
 */
public class modelDataKasir extends AbstractTableModel{
    
    public  static Vector<dataKasir> fieldKasir=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldKasir.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldKasir.get(rowIndex).getNomorKasir();
            case 1 : return fieldKasir.get(rowIndex).getIDkasir();
            case 2 : return fieldKasir.get(rowIndex).getNamakasir();
            case 3 : return fieldKasir.get(rowIndex).getTelpKasir();
            case 4 : return fieldKasir.get(rowIndex).getUsernameKasir();
            case 5 : return fieldKasir.get(rowIndex).getPasswordKasir();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID KASIR";
            case 2 : return "NAMA KASIR";  
            case 3 : return "TELEPON";
            case 4 : return "USERNAME";
            case 5 : return "PASSWORD";
            default: return null;
        }
    }
    
    public void insert(dataKasir dtp){
        fieldKasir.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
    
    public void update(dataKasir dtp, int index){
        fieldKasir.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldKasir.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldKasir.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
    
}
