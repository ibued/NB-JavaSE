

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataAdmin;

/**
 *
 * @author ibued
 */
public class modelDataAdmin extends AbstractTableModel{

    public  static Vector<dataAdmin> fieldAdmin=new Vector<>();
    
    @Override
    public int getRowCount() {
        return fieldAdmin.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldAdmin.get(rowIndex).getNomorAdmin();
            case 1 : return fieldAdmin.get(rowIndex).getIDadmin();
            case 2 : return fieldAdmin.get(rowIndex).getUsernameAdmin();
            case 3 : return fieldAdmin.get(rowIndex).getPasswdAdmin();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID ADMIN";
            case 2 : return "USERNAME";  
            case 3 : return "PASSWORD";           
            default: return null;
        }
    }
    
    public void insert(dataAdmin dtp){
        fieldAdmin.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
    
    public void update(dataAdmin dtp, int index){
        fieldAdmin.set(index, dtp);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index, int row){
        fieldAdmin.remove(index);
        fireTableRowsDeleted(index, row);
    }
    
    public void removeAll(){
        fieldAdmin.removeAllElements();
        fireTableRowsDeleted(getRowCount(), getRowCount());
    }
    
}
