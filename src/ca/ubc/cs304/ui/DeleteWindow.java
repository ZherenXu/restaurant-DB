package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DeleteWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;

    JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField chefSINField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField deliveryPeopleSINField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField foodSupplierCompanyNameField = new JTextField(TEXT_FIELD_WIDTH);

    public DeleteWindow(DatabaseConnectionHandler dbhandler) {
        super("Delete Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("DELETE DATA");
        JLabel deleteOrderLabel = new JLabel("Order:");
        JLabel orderNumLabel = new JLabel(" Order #: ");
        JLabel deleteChefLabel = new JLabel("Chef:");
        JLabel chefSINLabel = new JLabel(" SIN: ");
        JLabel deleteDeliveryPeopleLabel = new JLabel("Delivery People:");
        JLabel deliveryPeopleSINLabel = new JLabel(" SIN: ");
        JLabel deleteFoodSupplierLabel = new JLabel("Food Supplier:");
        JLabel foodSupplierCompanyNameLabel = new JLabel("Company Name: ");

        JButton deleteOrderBtn = new JButton("Delete");
        JButton deleteChefBtn = new JButton("Delete");
        JButton deleteDeliveryPeopleBtn = new JButton("Delete");
        JButton deleteFoodSupplierBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");
        JButton backBtn = new JButton("Back");

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
        ////////////////////////////////DELETE ORDERS///////////////////////////////
        // Order label
        deleteOrderLabel.setFont(deleteOrderLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(deleteOrderLabel, c);
        contentPane.add(deleteOrderLabel);

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

        // Delete order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteOrderBtn, c);
        contentPane.add(deleteOrderBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////DELETE CHEF///////////////////////////////
        // Chef label
        deleteChefLabel.setFont(deleteChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(deleteChefLabel, c);
        contentPane.add(deleteChefLabel);

        // SIN label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(chefSINLabel, c);
        contentPane.add(chefSINLabel);

        // SIN field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefSINField, c);
        contentPane.add(chefSINField);

        // Delete order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteChefBtn, c);
        contentPane.add(deleteChefBtn);

        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////DELETE DELIVERY PEOPLE///////////////////////////
        // Delivery People label
        deleteDeliveryPeopleLabel.setFont(deleteDeliveryPeopleLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(deleteDeliveryPeopleLabel, c);
        contentPane.add(deleteDeliveryPeopleLabel);

        // SIN label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(deliveryPeopleSINLabel, c);
        contentPane.add(deliveryPeopleSINLabel);

        // SIN text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleSINField, c);
        contentPane.add(deliveryPeopleSINField);

        // Delete delivery people button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteDeliveryPeopleBtn, c);
        contentPane.add(deleteDeliveryPeopleBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////DELETE FOOD SUPPLIER////////////////////////////
        // Food Supplier label
        deleteFoodSupplierLabel.setFont(deleteFoodSupplierLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(deleteFoodSupplierLabel, c);
        contentPane.add(deleteFoodSupplierLabel);

        // Company Name label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(foodSupplierCompanyNameLabel, c);
        contentPane.add(foodSupplierCompanyNameLabel);

        // Company Name field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierCompanyNameField, c);
        contentPane.add(foodSupplierCompanyNameField);

        // Delete food supplier button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteFoodSupplierBtn, c);
        contentPane.add(deleteFoodSupplierBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Refresh button
        c.gridwidth = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(refreshBtn, c);
        contentPane.add(refreshBtn);

        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        backBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        deleteOrderBtn.addActionListener(this);
        deleteChefBtn.addActionListener(this);
        deleteDeliveryPeopleBtn.addActionListener(this);
        deleteFoodSupplierBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        refreshBtn.setActionCommand("refresh");
        deleteOrderBtn.setActionCommand("order");
        deleteChefBtn.setActionCommand("chef");
        deleteDeliveryPeopleBtn.setActionCommand("delivery people");
        deleteFoodSupplierBtn.setActionCommand("food supplier");

        pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch(cmd){
            case "order":
                //break;
            case "chef":
                //break;
            case "delivery people":
                //break;
            case "food supplier":
                //break;
            case "back":
                dispose();
                new MenuWindow(dbhandler);
                break;
            case "refresh":
                //break;
            default:
                break;
        }
    }
}
