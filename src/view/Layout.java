package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Layout extends JFrame {

    public void guiInitilaze(int width,int height,String title) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Tourism Agency System"+ title);
        this.setSize(width,height);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        this.setVisible(true);
    }

    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        table.setEnabled(false);


        DefaultTableModel Dmodel = (DefaultTableModel) table.getModel();
        Dmodel.setRowCount(0);

        if(rows == null) {
            rows = new ArrayList<>();
        }

        for (Object[] row : rows) {
            model.addRow(row);
        }

    }

    public int getTableSelectedRow(JTable table,int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

}
