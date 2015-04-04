
package proyek.rpl.dataModel;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import proyek.rpl.data.dataQueryJual;

/**
 *
 * @author ibued
 */
public class modelDataQueryJual extends AbstractTableModel{
    public static Vector<dataQueryJual> fieldJual=new Vector<>();

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
            case 1 : return fieldJual.get(rowIndex).getIDJual();
            case 2 : return fieldJual.get(rowIndex).getTanggalJual();
            case 3 : return fieldJual.get(rowIndex).getIDPelangganJual();
            case 4 : return fieldJual.get(rowIndex).getIDKasirJual();            
            case 5 : return fieldJual.get(rowIndex).getTotalJual();
            case 6 : return fieldJual.get(rowIndex).getBayarJual();
            case 7 : return fieldJual.get(rowIndex).getKembalianBayar();
            case 8 : return fieldJual.get(rowIndex).getNamaPelanggan();
            case 9 : return fieldJual.get(rowIndex).getNamaKasir();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0 : return "NO";
            case 1 : return "ID JUAL";
            case 2 : return "TANGGAL JUAL";
            case 3 : return "ID PELANGGAN";
            case 4 : return "ID KASIR";
            case 5 : return "TOTAL";
            case 6 : return "PEMBAYARAN";
            case 7 : return "KEMBALIAN";
            case 8 : return "PELANGGAN.NAMA";
            case 9 : return "KASIR.NAMA";           
            default: return null;
        }
    }
    
    public void insert(dataQueryJual dtp){
        fieldJual.add(dtp);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }

    public void update(dataQueryJual dtp, int index){
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
    
    public dataQueryJual get(int row){
        return fieldJual.get(row);
    }
    
}
