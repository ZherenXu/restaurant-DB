package ca.ubc.cs304.ui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TableWindow {
    Vector<Vector<?>> rowData;
    Vector<?> columnNames;
    JTable dataTable;
    JFrame dataFrame;
    JScrollPane sp;

    public TableWindow(Vector<Vector<String>> rowData, Vector<String> columnNames, String title){
        dataFrame = new JFrame(title);
        dataTable = new JTable(rowData, columnNames);
        sp = new JScrollPane(dataTable);
        dataFrame.add(sp);
        dataFrame.setSize(300,200);
        dataFrame.setVisible(true);
    }

    public void updateTable(Vector<Vector<String>> rowData, Vector<String> columnNames){
        dataFrame.setVisible(false);
        dataTable = new JTable(rowData, columnNames);
        sp = new JScrollPane(dataTable);
        dataFrame.add(sp);
        dataFrame.setSize(300,200);
        dataFrame.setVisible(true);
    }
}
