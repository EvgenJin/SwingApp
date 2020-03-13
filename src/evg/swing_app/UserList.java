package evg.swing_app;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.*;

public class UserList extends JFrame {
    // create grid table
    DefaultTableModel model = new DefaultTableModel() {
        // edit cell = false 
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        };
    };
    JPanel jp = new JPanel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);
    JScrollPane pg = new JScrollPane(jtbl);        
    public UserList() {        
        JTextField filterField = RowFilterUtil.createRowFilter(jtbl);        
        cnt.setLayout(new BoxLayout(cnt, BoxLayout.Y_AXIS));
        cnt.add(filterField);
        cnt.add(pg);        
        model.addColumn("id");        
        model.addColumn("serial_num");
        TableColumnModel columnModel = jtbl.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(25);
        columnModel.getColumn(1).setMaxWidth(100);
        
        jtbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jtbl.rowAtPoint(evt.getPoint());
                int col = jtbl.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    String cell_val = jtbl.getValueAt(row, col).toString();
                    JOptionPane.showMessageDialog(rootPane, cell_val);
                }
            }
        });
        
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Projects\\JAVA\\jsp_app\\database.sqlite3");
            System.out.println("Connected to databse");
            PreparedStatement pstm = con.prepareStatement("select * from products");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getInt("id"),Rs.getString("serial_num")});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.pack();
    }
}