package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.DishesModel;
import ca.ubc.cs304.model.OrdersModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.*;

public class InsertWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb;
    private boolean choose = true;
    private JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField dishOrderNumField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField dishField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField tastePreferenceField = new JTextField(TEXT_FIELD_WIDTH);

    public InsertWindow(DatabaseConnectionHandler dbhandler) {
        super("Insert Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("INSERT DATA");
        JLabel insertOrderLabel = new JLabel("Order:");
        JLabel orderNumLabel = new JLabel(" Order #: ");
        JLabel insertDishLabel = new JLabel("Dish:");
        JLabel dishOrderNumLabel = new JLabel("Order #: ");
        JLabel dishLabel = new JLabel(" Dish: ");
        JLabel tastePreferenceLabel = new JLabel("Teste Preference: ");


        JButton insertOrderBtn = new JButton("Insert");
        JButton insertDishBtn = new JButton("Insert");
        JButton backBtn = new JButton("Back");
        JButton refreshBtn = new JButton("Refresh");

        // Set the Window
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // place the title label
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.PLAIN,18));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(titleLabel, c);
        contentPane.add(titleLabel);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////INSERT ORDERS///////////////////////////////
        // Order label
        insertOrderLabel.setFont(insertOrderLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertOrderLabel, c);
        contentPane.add(insertOrderLabel);

        // Order# label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumLabel, c);
        contentPane.add(orderNumLabel);

        // Order# text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumField, c);
        contentPane.add(orderNumField);

        // Insert order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertOrderBtn, c);
        contentPane.add(insertOrderBtn);

        ////////////////////////////////////////////////////////////////////////////
        //////////////////////////////INSERT DISHES/////////////////////////////////
        // Dish label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertDishLabel, c);
        contentPane.add(insertDishLabel);

        // Dish Order# label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishOrderNumLabel, c);
        contentPane.add(dishOrderNumLabel);

        // Dish Order# text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishOrderNumField, c);
        contentPane.add(dishOrderNumField);

        // Dish label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishLabel, c);
        contentPane.add(dishLabel);

        // Dish text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishField, c);
        contentPane.add(dishField);

        // Taste Preference label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(tastePreferenceLabel, c);
        contentPane.add(tastePreferenceLabel);

        // Taste Preference field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(tastePreferenceField, c);
        contentPane.add(tastePreferenceField);

        // Insert dish button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertDishBtn, c);
        contentPane.add(insertDishBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        // Refresh button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(refreshBtn, c);
        contentPane.add(refreshBtn);

        backBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        insertDishBtn.addActionListener(this);
        insertDishBtn.setActionCommand("dish");
        insertOrderBtn.addActionListener(this);
        insertOrderBtn.setActionCommand("order");
        refreshBtn.addActionListener(this);
        refreshBtn.setActionCommand("refresh");

        pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        tb = new TableWindow(dbhandler.getAllOrder(),dbhandler.getOrderColumn(),"order");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch(cmd){
            case "order":
                OrdersModel order = new OrdersModel(Integer.valueOf(orderNumField.getText()), new Timestamp(System.currentTimeMillis()));
                dbhandler.insertOrder(order);
                choose = true;
                break;
            case "dish":
                DishesModel dish = new DishesModel(dishField.getText(), Integer.valueOf(dishOrderNumField.getText()), tastePreferenceField.getText());
                dbhandler.insertDish(dish);
                choose = false;
                break;
            case "back":
                dispose();
                new MenuWindow(dbhandler);
                break;
            case "refresh":
                if(choose) {
                    tb.updateTable(dbhandler.getAllOrder(),dbhandler.getOrderColumn(),"Order");
                }
                else{
                    tb.updateTable(dbhandler.getAllDishes(),dbhandler.getDishesColumn(),"Dish");
                }
                break;
            default:
                break;
        }
    }

}