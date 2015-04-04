

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataGetPelanggan;

/**
 *
 * @author ibued
 */
public class modelGetPelanggan extends AbstractTableModel{
    
    public  static Vector<dataGetPelanggan> fieldPlg=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldPlg.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldPlg.get(rowIndex).getNomorPelanggan();
            case 1 : return fieldPlg.get(rowIndex).getIDpelanggan();
            case 2 : return fieldPlg.get(rowIndex).getNamaPelanggan();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {        
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID PELANGGAN";
            case 2 : return "NAMA PELANGGAN";           
            default: return null;
        }
    }
    
    public void insert(dataGetPelanggan dtp){
        fieldPlg.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataGetPelanggan dtp, int index){
        fieldPlg.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldPlg.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldPlg.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
        
}
