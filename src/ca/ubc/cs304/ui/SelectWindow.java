package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class SelectWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;

    JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberBranchField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberDateField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberTimeField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField dishesLotNumberField = new JTextField(TEXT_FIELD_WIDTH);

    public SelectWindow(DatabaseConnectionHandler dbhandler) {
        super("Select Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("SELECT DATA");
        JLabel findByOrderNumberLabel = new JLabel("Find By Order Number:");
        JLabel findTheOrderNumberLabel = new JLabel("Find The Order Number");
        JLabel orderNumberBranchLabel = new JLabel("Branch: ");
        JLabel orderNumberDateLabel = new JLabel("Date: ");
        JLabel orderNumberTimeLabel = new JLabel("Time: ");
        JLabel findDishesLabel = new JLabel("Find Dishes");
        JLabel dishesLotNumberLabel = new JLabel("Lot#: ");
        JLabel findIngredientsByAllChefLabel = new JLabel("Find Ingredients Used By All Chefs");

        JButton findChefBtn = new JButton("Chef Name & Contact Number");
        JButton findIngredientBtn = new JButton("Ingredient Name");
        JButton findDeliveryPeopleBtn = new JButton("Delivery People Name & Contact Number");
        JButton findFoodSupplierBtn = new JButton("Food Supplier Company Name & Contact Number");
        JButton findStorageTemperatureBtn = new JButton("Storage Temperature");
        JButton selectOrderNumberBtn = new JButton("Select");
        JButton selectDishesBtn = new JButton("Select");
        JButton selectIngredientsBtn = new JButton("Select");
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
        ////////////////////////////////FIND BY ORDER///////////////////////////////
        // Find By Order label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(findByOrderNumberLabel, c);
        contentPane.add(findByOrderNumberLabel);

        // Order Number field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumField, c);
        contentPane.add(orderNumField);

        // Find button Chef
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(findChefBtn, c);
        contentPane.add(findChefBtn);

        // Find button Ingredient
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(findIngredientBtn, c);
        contentPane.add(findIngredientBtn);

        // Find button Delivery People
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(findDeliveryPeopleBtn, c);
        contentPane.add(findDeliveryPeopleBtn);

        // Find button Food Supplier
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(findFoodSupplierBtn, c);
        contentPane.add(findFoodSupplierBtn);

        // Find button Storage Temperature
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(findStorageTemperatureBtn, c);
        contentPane.add(findStorageTemperatureBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////FIND ORDER# BY BRANCH, DATE, TIME///////////////////////
        // Find the Order# label
        findTheOrderNumberLabel.setFont(findTheOrderNumberLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(findTheOrderNumberLabel, c);
        contentPane.add(findTheOrderNumberLabel);

        // Branch label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumberBranchLabel, c);
        contentPane.add(orderNumberBranchLabel);

        // Branch text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumberBranchField, c);
        contentPane.add(orderNumberBranchField);

        // Date label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumberDateLabel, c);
        contentPane.add(orderNumberDateLabel);

        // Date text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumberDateField, c);
        contentPane.add(orderNumberDateField);

        // Time label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumberTimeLabel, c);
        contentPane.add(orderNumberTimeLabel);

        // Time text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumberTimeField, c);
        contentPane.add(orderNumberTimeField);

        // Select button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(selectOrderNumberBtn, c);
        contentPane.add(selectOrderNumberBtn);

        ////////////////////////////////////////////////////////////////////////////
        /////////////////////////////FIND DISHES BY LOT#////////////////////////////
        // Find Dishes label
        findDishesLabel.setFont(findDishesLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(findDishesLabel, c);
        contentPane.add(findDishesLabel);

        // Lot# label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishesLotNumberLabel, c);
        contentPane.add(dishesLotNumberLabel);

        // Lot# text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishesLotNumberField, c);
        contentPane.add(dishesLotNumberField);

        // Select button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(selectDishesBtn, c);
        contentPane.add(selectDishesBtn);
        ////////////////////////////////////////////////////////////////////////////
        /////////////////FIND INGREDIENTS USED BY ALL CHEFS ////////////////////////
        // Find Ingredients label
        findIngredientsByAllChefLabel.setFont(findIngredientsByAllChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(findIngredientsByAllChefLabel, c);
        contentPane.add(findIngredientsByAllChefLabel);

        // Select button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(selectIngredientsBtn, c);
        contentPane.add(selectIngredientsBtn);

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
        findChefBtn.addActionListener(this);
        findIngredientBtn.addActionListener(this);
        findDeliveryPeopleBtn.addActionListener(this);
        findFoodSupplierBtn.addActionListener(this);
        findStorageTemperatureBtn.addActionListener(this);
        selectOrderNumberBtn.addActionListener(this);
        selectDishesBtn.addActionListener(this);
        selectIngredientsBtn.addActionListener(this);
        backBtn.setActionCommand("back");
        refreshBtn.setActionCommand("refresh");

        findChefBtn.setActionCommand("find chef");
        findIngredientBtn.setActionCommand("find ingredient");
        findDeliveryPeopleBtn.setActionCommand("find delivery people");
        findFoodSupplierBtn.setActionCommand("find food supplier");
        findStorageTemperatureBtn.setActionCommand("find storage temperature");

        selectOrderNumberBtn.setActionCommand("select order number");
        selectDishesBtn.setActionCommand("select dishes");
        selectIngredientsBtn.setActionCommand("select ingredients");

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
            case "find chef":
                //break;
            case "find ingredient":
                //break;
            case "find delivery people":
                //break;
            case "find food supplier":
                //break;
            case "find storage temperature":
                //break;
            case "select order number":
                //break;
            case "select dishes":
                //break;
            case "select ingredients":
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
