

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataSatuan;

/**
 *
 * @author ibued
 */
public class modelSatuan extends AbstractTableModel{
    
    public  static Vector<dataSatuan> fieldSatuan=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldSatuan.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldSatuan.get(rowIndex).getNomorSatuan();
            case 1 : return fieldSatuan.get(rowIndex).getIDSatuan();
            case 2 : return fieldSatuan.get(rowIndex).getNamaSatuan();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID SATUAN";
            case 2 : return "NAMA SATUAN";           
            default: return null;
        }
    }
    
    public void insert(dataSatuan dtp){
        fieldSatuan.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataSatuan dtp, int index){
        fieldSatuan.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldSatuan.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldSatuan.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
    
}
