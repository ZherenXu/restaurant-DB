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
    private JTextField ingredientsLotField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsDateField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsQuantityField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsTypeField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsPosIDField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField ingredientsSINField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField cookSINField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField cookDishNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField cookOrderNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField chefNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField chefSINField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField chefContactNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField chefHomeAddressField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField chefBranchAddressField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField deliveryPeopleSINField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField deliveryPeopleNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField deliveryPeopleContactNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField deliveryPeopleAddressField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField consumeDishNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField consumeOrderNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField consumeLotNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField consumeQuantityField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField foodSupplierCompanyNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField foodSupplierAddressField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField foodSupplierContactNumberField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField foodSupplierEmailField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField provideCompanyNameField = new JTextField(TEXT_FIELD_WIDTH);
    private JTextField provideLotNumberField = new JTextField(TEXT_FIELD_WIDTH);

    public InsertWindow(DatabaseConnectionHandler dbhandler) {
        super("Insert Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // LABELS
        JLabel titleLabel = new JLabel("INSERT DATA");
        JLabel insertOrderLabel = new JLabel("Order:");
        JLabel orderNumLabel = new JLabel(" Order #: ");
        JLabel insertDishLabel = new JLabel("Dish:");
        JLabel dishOrderNumLabel = new JLabel("Order #: ");
        JLabel dishLabel = new JLabel(" Dish: ");
        JLabel tastePreferenceLabel = new JLabel("Teste Preference: ");
        JLabel insertIngredientLabel = new JLabel("Ingredient & Category:");
        JLabel insertIngredientAttributeLabel = new JLabel("       Lot#                                   " +
                                                                "Name                                " +
                                                                "Date                             " +
                                                                "Quantity                           " +
                                                                "Type                                " +
                                                                "PosID                               " +
                                                                "SIN");
        JLabel insertCookLabel = new JLabel("Cook:");
        JLabel insertCookAttributeLabel = new JLabel("            SIN                           " +
                                                            "Dish Name                     " +
                                                            "Order#");
        JLabel insertChefLabel = new JLabel("Chef:");
        JLabel insertChefAttributeLabel = new JLabel("      Name                                  " +
                                                            "SIN                           " +
                                                            "Contact Number            " +
                                                            "Home Address           " +
                                                            "Branch Address");
        JLabel insertDeliveryPeopleLabel = new JLabel("Delivery People:");
        JLabel insertDeliveryPeopleAttributeLabel = new JLabel("        SIN                               " +
                                                                    "Name                           " +
                                                                    "Contact Number                 " +
                                                                    "Address");
        JLabel insertConsumeLabel = new JLabel("Consume:");
        JLabel insertConsumeAttributeLabel = new JLabel("   Dish Name                          " +
                                                            "Order#                             " +
                                                            "Lot#                               " +
                                                            "Quantity");
        JLabel insertFoodSupplierLabel = new JLabel("Food Supplier:");
        JLabel insertFoodSupplierAttributeLabel = new JLabel("Company Name                  " +
                                                                "Address                       " +
                                                                "Contact Number                    " +
                                                                "Email                            ");
        JLabel insertProvideLabel = new JLabel("Provide:");
        JLabel insertProvideAttributeLabel = new JLabel("Company Name                       " +
                                                                "Lot#");

        // BUTTONS
        JButton insertOrderBtn = new JButton("Insert");
        JButton insertDishBtn = new JButton("Insert");
        JButton insertIngredientBtn = new JButton("Insert");
        JButton insertCookBtn = new JButton("Insert");
        JButton insertChefBtn = new JButton("Insert");
        JButton insertDeliveryPeopleBtn = new JButton("Insert");
        JButton insertConsumeBtn = new JButton("Insert");
        JButton insertFoodSupplierBtn = new JButton("Insert");
        JButton insertProvideBtn = new JButton("Insert");
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
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
        ////////////////////////////INSERT INGREDIENTS//////////////////////////////
        // Ingredient label
        insertOrderLabel.setFont(insertOrderLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertIngredientLabel, c);
        contentPane.add(insertIngredientLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertIngredientAttributeLabel, c);
        contentPane.add(insertIngredientAttributeLabel);

        // Lot# field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsLotField, c);
        contentPane.add(ingredientsLotField);

        // Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsNameField, c);
        contentPane.add(ingredientsNameField);

        // Date field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsDateField, c);
        contentPane.add(ingredientsDateField);

        // quantity field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsQuantityField, c);
        contentPane.add(ingredientsQuantityField);

        // type field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsTypeField, c);
        contentPane.add(ingredientsTypeField);

        // Pos ID field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsPosIDField, c);
        contentPane.add(ingredientsPosIDField);

        // SIN field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientsSINField, c);
        contentPane.add(ingredientsSINField);

        // Insert ingredient button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertIngredientBtn, c);
        contentPane.add(insertIngredientBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////INSERT COOK//////////////////////////////
        // Cook label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertCookLabel, c);
        contentPane.add(insertCookLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertCookAttributeLabel, c);
        contentPane.add(insertCookAttributeLabel);

        // SIN field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(cookSINField, c);
        contentPane.add(cookSINField);

        // Dish Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(cookDishNameField, c);
        contentPane.add(cookDishNameField);

        // Order Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(cookOrderNumberField, c);
        contentPane.add(cookOrderNumberField);

        // Insert cook button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertCookBtn, c);
        contentPane.add(insertCookBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////INSERT CHEF/////////////////////////////////
        // Chef label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertChefLabel, c);
        contentPane.add(insertChefLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertChefAttributeLabel, c);
        contentPane.add(insertChefAttributeLabel);

        // Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefNameField, c);
        contentPane.add(chefNameField);

        // SIN field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefSINField, c);
        contentPane.add(chefSINField);

        // Contact Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefContactNumberField, c);
        contentPane.add(chefContactNumberField);

        // Home Address field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefHomeAddressField, c);
        contentPane.add(chefHomeAddressField);

        // Branch Address field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefBranchAddressField, c);
        contentPane.add(chefBranchAddressField);

        // Insert chef button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertChefBtn, c);
        contentPane.add(insertChefBtn);

        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////INSERT DELIVERY PEOPLE///////////////////////////
        // Delivery People label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertDeliveryPeopleLabel, c);
        contentPane.add(insertDeliveryPeopleLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertDeliveryPeopleAttributeLabel, c);
        contentPane.add(insertDeliveryPeopleAttributeLabel);

        // SIN field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleSINField, c);
        contentPane.add(deliveryPeopleSINField);

        // Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleNameField, c);
        contentPane.add(deliveryPeopleNameField);

        // Contact Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleContactNumberField, c);
        contentPane.add(deliveryPeopleContactNumberField);

        // Address field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleAddressField, c);
        contentPane.add(deliveryPeopleAddressField);

        // Insert delivery people button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertDeliveryPeopleBtn, c);
        contentPane.add(insertDeliveryPeopleBtn);

        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////INSERT CONSUME///////////////////////////////
        // CONSUME label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertConsumeLabel, c);
        contentPane.add(insertConsumeLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertConsumeAttributeLabel, c);
        contentPane.add(insertConsumeAttributeLabel);

        // Dish Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(consumeDishNameField, c);
        contentPane.add(consumeDishNameField);

        // Order# field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(consumeOrderNumberField, c);
        contentPane.add(consumeOrderNumberField);

        // Lot# field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(consumeLotNumberField, c);
        contentPane.add(consumeLotNumberField);

        // Quantity field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(consumeQuantityField, c);
        contentPane.add(consumeQuantityField);

        // Insert Consume button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertConsumeBtn, c);
        contentPane.add(insertConsumeBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////INSERT FOOD SUPPLIER////////////////////////////
        // FOOD SUPPLIER label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertFoodSupplierLabel, c);
        contentPane.add(insertFoodSupplierLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertFoodSupplierAttributeLabel, c);
        contentPane.add(insertFoodSupplierAttributeLabel);

        // Company Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierCompanyNameField, c);
        contentPane.add(foodSupplierCompanyNameField);

        // Address field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierAddressField, c);
        contentPane.add(foodSupplierAddressField);

        // Contact Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierContactNumberField, c);
        contentPane.add(foodSupplierContactNumberField);

        // Email field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierEmailField, c);
        contentPane.add(foodSupplierEmailField);

        // Insert Food Supplier button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertFoodSupplierBtn, c);
        contentPane.add(insertFoodSupplierBtn);

        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////INSERT PROVIDE///////////////////////////////
        // Provide label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertProvideLabel, c);
        contentPane.add(insertProvideLabel);

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(insertProvideAttributeLabel, c);
        contentPane.add(insertProvideAttributeLabel);

        // Company Name field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(provideCompanyNameField, c);
        contentPane.add(provideCompanyNameField);

        // Lot Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(provideLotNumberField, c);
        contentPane.add(provideLotNumberField);

        // Insert Provide button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertProvideBtn, c);
        contentPane.add(insertProvideBtn);

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


        insertDishBtn.addActionListener(this);
        insertOrderBtn.addActionListener(this);
        insertIngredientBtn.addActionListener(this);
        insertCookBtn.addActionListener(this);
        insertChefBtn.addActionListener(this);
        insertDeliveryPeopleBtn.addActionListener(this);
        insertConsumeBtn.addActionListener(this);
        insertFoodSupplierBtn.addActionListener(this);
        insertProvideBtn.addActionListener(this);
        backBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

        insertDishBtn.setActionCommand("dish");
        insertOrderBtn.setActionCommand("order");
        insertIngredientBtn.setActionCommand("ingredient");
        insertCookBtn.setActionCommand("cook");
        insertChefBtn.setActionCommand("chef");
        insertDeliveryPeopleBtn.setActionCommand("delivery people");
        insertConsumeBtn.setActionCommand("consume");
        insertFoodSupplierBtn.setActionCommand("food supplier");
        insertProvideBtn.setActionCommand("provide");
        backBtn.setActionCommand("back");
        refreshBtn.setActionCommand("refresh");

        pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        //tb = new TableWindow(dbhandler.getAllOrder(),dbhandler.getOrderColumn(),"order");

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
            case "ingredient":
                //break;
            case "cook":
                //;
            case "chef":
                //break;
            case "delivery people":
                //break;
            case "consume":
                //break;
            case "food supplier":
                //break;
            case "provide":
                //break;
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