

package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataQueryJualDetil;

/**
 *
 * @author ibued
 */
public class modelDataQueryJualDetil extends AbstractTableModel{
    
    public static Vector<dataQueryJualDetil> fieldJual=new Vector<>();

    @Override
    public int getRowCount() {
        return fieldJual.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0 : return fieldJual.get(rowIndex).getNomorJual();
            case 1 : return fieldJual.get(rowIndex).getIDJualDetil();
            case 2 : return fieldJual.get(rowIndex).getIDJual();
            case 3 : return fieldJual.get(rowIndex).getIDbarang();
            case 4 : return fieldJual.get(rowIndex).getJumlah();            
            case 5 : return fieldJual.get(rowIndex).getNamaBarang();
            case 6 : return fieldJual.get(rowIndex).getHarga();
            case 7 : return fieldJual.get(rowIndex).getNamaSatuan();
            case 8 : return fieldJual.get(rowIndex).getSubTotal();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID JUAL DETIL";
            case 2 : return "ID JUAL";
            case 3 : return "ID BARANG";
            case 4 : return "JUMLAH";
            case 5 : return "BARANG.NAMA";
            case 6 : return "HARGA";
            case 7 : return "SATUAN.NAMA";
            case 8 : return "SUB TOTAL";          
            default: return null;
        }
    }
    
    public void insert(dataQueryJualDetil dtp){
        fieldJual.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataQueryJualDetil dtp, int index){
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
    
    public dataQueryJualDetil get(int row){
        return fieldJual.get(row);
    }
    
}
