

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataPelanggan;

/**
 *
 * @author ibued
 */
public class modelDataPelanggan extends AbstractTableModel {
    
    public  static Vector<dataPelanggan> fieldPlg=new Vector<>();
    
   @Override
    public int getRowCount() {
        return fieldPlg.size();
    }
    
    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldPlg.get(rowIndex).getNomorPelanggan();
            case 1 : return fieldPlg.get(rowIndex).getIDpelanggan();
            case 2 : return fieldPlg.get(rowIndex).getNamaPelanggan();
            case 3 : return fieldPlg.get(rowIndex).getJenisKelamin();
            case 4 : return fieldPlg.get(rowIndex).getAlamatPelanggan();
            case 5 : return fieldPlg.get(rowIndex).getTeleponPelanggan();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID PELANGGAN";
            case 2 : return "NAMA PELANGGAN";
            case 3 : return "JENIS KELAMIN";
            case 4 : return "ALAMAT";           
            case 5 : return "TELEPON";
            default: return null;
        }
    }    
    
    public void insert(dataPelanggan dtp){
        fieldPlg.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataPelanggan dtp, int index){
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
