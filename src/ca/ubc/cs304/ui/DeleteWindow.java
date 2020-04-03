package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.OrdersModel;

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
    private TableWindow tb = new TableWindow();
    private int choose = 0;

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
        JButton orderShowOrderBtn = new JButton("Show orders");
        JButton orderShowDishesBtn = new JButton("Show dishes");
        JButton deleteChefBtn = new JButton("Delete");
        JButton showChefBtn = new JButton("Show");
        JButton deleteDeliveryPeopleBtn = new JButton("Delete");
        JButton showDeliveryPeopleBtn = new JButton("Show");
        JButton deleteFoodSupplierBtn = new JButton("Delete");
        JButton showFoodSupplierBtn = new JButton("Show");
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteOrderBtn, c);
        contentPane.add(deleteOrderBtn);

        // show order button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(orderShowOrderBtn, c);
        contentPane.add(orderShowOrderBtn);

        // show dishes button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(orderShowDishesBtn, c);
        contentPane.add(orderShowDishesBtn);
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

        // Delete chef button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteChefBtn, c);
        contentPane.add(deleteChefBtn);

        // show chef button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showChefBtn, c);
        contentPane.add(showChefBtn);

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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteDeliveryPeopleBtn, c);
        contentPane.add(deleteDeliveryPeopleBtn);

        // show delivery people button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showDeliveryPeopleBtn, c);
        contentPane.add(showDeliveryPeopleBtn);

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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteFoodSupplierBtn, c);
        contentPane.add(deleteFoodSupplierBtn);

        // show food supplier button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showFoodSupplierBtn, c);
        contentPane.add(showFoodSupplierBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        backBtn.addActionListener(this);
        deleteOrderBtn.addActionListener(this);
        deleteChefBtn.addActionListener(this);
        deleteDeliveryPeopleBtn.addActionListener(this);
        deleteFoodSupplierBtn.addActionListener(this);
        orderShowOrderBtn.addActionListener(this);
        orderShowDishesBtn.addActionListener(this);
        showChefBtn.addActionListener(this);
        showDeliveryPeopleBtn.addActionListener(this);
        showFoodSupplierBtn.addActionListener(this);

        backBtn.setActionCommand("back");
        deleteOrderBtn.setActionCommand("order");
        deleteChefBtn.setActionCommand("chef");
        deleteDeliveryPeopleBtn.setActionCommand("delivery people");
        deleteFoodSupplierBtn.setActionCommand("food supplier");
        orderShowOrderBtn.setActionCommand("show order");
        orderShowDishesBtn.setActionCommand("show dished");
        showChefBtn.setActionCommand("show chef");
        showDeliveryPeopleBtn.setActionCommand("show delivery_people");
        showFoodSupplierBtn.setActionCommand("show food_supplier");

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
                dbhandler.deleteOrder(Integer.valueOf(orderNumField.getText()));
                choose = 1;
                break;
            case "chef":
                dbhandler.deleteChef(chefSINField.getText());
                choose = 2;
                break;
            case "delivery people":
                dbhandler.deleteDeliveryPeople(deliveryPeopleSINField.getText());
                choose = 3;
                break;
            case "food supplier":
                dbhandler.deleteFoodSupplier(foodSupplierCompanyNameField.getText());
                choose = 4;
                break;
            case "show order":
                //break;
            case "show dished":
                //break;
            case "show chef":
                //break;
            case "show delivery_people":
                //break;
            case "show food_supplier":
                //break;
            case "back":
                choose = 0;
                dispose();
                new MenuWindow(dbhandler);
                break;
            default:
                break;
        }

        switch(choose) {
            case 1:
                tb.updateTable(dbhandler.getAllOrder(), dbhandler.getOrderColumn(), "Orders");
                break;
            case 2:
                tb.updateTable(dbhandler.getAllChef(), dbhandler.getChefColumn(), "Chefs");
                break;
            case 3:
                tb.updateTable(dbhandler.getAllDeliveryPeople(),dbhandler.getDeliveryPeopleColumn(),"Delivery People");
                break;
            case 4:
                tb.updateTable(dbhandler.getAllFoodSupplier(),dbhandler.getFoodSupplierColumn(),"Food Suppliers");
                break;
            default:
                break;
        }
    }
}
