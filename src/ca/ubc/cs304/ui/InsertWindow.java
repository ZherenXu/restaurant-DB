package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;

public class InsertWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();
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
        JLabel orderNumLabel = new JLabel("     Order # ");
        JLabel dishOrderNumLabel = new JLabel("      Order #                       " +
                                                        "Dish                      " +
                                                        "Taste Preference");
        JLabel insertIngredientAttributeLabel = new JLabel("        Lot#                               " +
                                                                    "Name             " +
                                                                "Date(dd-mm-yyyy hh:mm:ss)       " +
                                                                "Quantity                           " +
                                                                "Type                                " +
                                                                "PosID                               " +
                                                                "SIN");
        JLabel insertCookAttributeLabel = new JLabel("            SIN                       " +
                                                            "Dish Name                     " +
                                                            "Order#");
        JLabel insertChefAttributeLabel = new JLabel("      Name                            " +
                                                            "SIN                           " +
                                                            "Contact Number            " +
                                                            "Home Address           " +
                                                            "Branch Address");
        JLabel insertDeliveryPeopleAttributeLabel = new JLabel("        SIN                               " +
                                                                    "Name                           " +
                                                                    "Contact Number                 " +
                                                                    "Address");
        JLabel insertConsumeAttributeLabel = new JLabel("   Dish Name                     " +
                                                            "Order#                             " +
                                                            "Lot#                               " +
                                                            "Quantity");
        JLabel insertFoodSupplierAttributeLabel = new JLabel("Company Name                  " +
                                                                "Address                  " +
                                                                "Contact Number                    " +
                                                                "Email                            ");
        JLabel insertProvideAttributeLabel = new JLabel("Company Name                       " +
                                                                "Lot#");

        // BUTTONS
        JButton insertOrderBtn = new JButton("Insert Order");
        JButton insertDishBtn = new JButton("Insert Dish");
        JButton insertIngredientBtn = new JButton("Insert Ingredient");
        JButton insertCookBtn = new JButton("Insert Cook");
        JButton insertChefBtn = new JButton("Insert Chef");
        JButton insertDeliveryPeopleBtn = new JButton("Insert Delivery People");
        JButton insertConsumeBtn = new JButton("Insert Consume");
        JButton insertFoodSupplierBtn = new JButton("Insert Food Supplier");
        JButton insertProvideBtn = new JButton("Insert Provide");
        JButton backBtn = new JButton("Back");
        JButton showOrderBtn = new JButton("Show");
        JButton showDishBtn = new JButton("Show");
        JButton showIngredientBtn = new JButton("Show");
        JButton showCookBtn = new JButton("Show");
        JButton showChefBtn = new JButton("Show");
        JButton showDeliveryPeopleBtn = new JButton("Show");
        JButton showConsumeBtn = new JButton("Show");
        JButton showFoodSupplierBtn = new JButton("Show");
        JButton showProvideBtn = new JButton("Show");

        // Set the Window
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////INSERT ORDERS///////////////////////////////
        // Order# label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumLabel, c);
        contentPane.add(orderNumLabel);

        // Order# text field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumField, c);
        contentPane.add(orderNumField);

        // Insert order button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertOrderBtn, c);
        contentPane.add(insertOrderBtn);

        // show order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showOrderBtn, c);
        contentPane.add(showOrderBtn);

        ////////////////////////////////////////////////////////////////////////////
        //////////////////////////////INSERT DISHES/////////////////////////////////

        // Dish Order# label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishOrderNumLabel, c);
        contentPane.add(dishOrderNumLabel);

        // Dish Order# text field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishOrderNumField, c);
        contentPane.add(dishOrderNumField);

        // Dish text field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishField, c);
        contentPane.add(dishField);

        // Taste Preference field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(tastePreferenceField, c);
        contentPane.add(tastePreferenceField);

        // Insert dish button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertDishBtn, c);
        contentPane.add(insertDishBtn);

        // show dish button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showDishBtn, c);
        contentPane.add(showDishBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////INSERT INGREDIENTS//////////////////////////////

        // Attributes label
        insertIngredientAttributeLabel.setFont(insertIngredientAttributeLabel.getFont().deriveFont(Font.BOLD,11));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertIngredientBtn, c);
        contentPane.add(insertIngredientBtn);

        // show ingredient button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showIngredientBtn, c);
        contentPane.add(showIngredientBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////INSERT COOK//////////////////////////////

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertCookBtn, c);
        contentPane.add(insertCookBtn);

        // show cook button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showCookBtn, c);
        contentPane.add(showCookBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////INSERT CHEF/////////////////////////////////

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertChefBtn, c);
        contentPane.add(insertChefBtn);

        // show chef button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showChefBtn, c);
        contentPane.add(showChefBtn);

        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////INSERT DELIVERY PEOPLE///////////////////////////
        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertDeliveryPeopleBtn, c);
        contentPane.add(insertDeliveryPeopleBtn);

        // show delivery people button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showDeliveryPeopleBtn, c);
        contentPane.add(showDeliveryPeopleBtn);
        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////INSERT CONSUME///////////////////////////////
        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertConsumeBtn, c);
        contentPane.add(insertConsumeBtn);

        // show Consume button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showConsumeBtn, c);
        contentPane.add(showConsumeBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////INSERT FOOD SUPPLIER////////////////////////////

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertFoodSupplierBtn, c);
        contentPane.add(insertFoodSupplierBtn);

        // Show Food Supplier button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showFoodSupplierBtn, c);
        contentPane.add(showFoodSupplierBtn);
        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////INSERT PROVIDE///////////////////////////////

        // Attributes label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertProvideBtn, c);
        contentPane.add(insertProvideBtn);

        // Show Provide button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(showProvideBtn, c);
        contentPane.add(showProvideBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);


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
        showOrderBtn.addActionListener(this);
        showDishBtn.addActionListener(this);
        showIngredientBtn.addActionListener(this);
        showCookBtn.addActionListener(this);
        showChefBtn.addActionListener(this);
        showDeliveryPeopleBtn.addActionListener(this);
        showConsumeBtn.addActionListener(this);
        showFoodSupplierBtn.addActionListener(this);
        showProvideBtn.addActionListener(this);


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
        showOrderBtn.setActionCommand("showOrder");
        showDishBtn.setActionCommand("showDish");
        showIngredientBtn.setActionCommand("showIngredient");
        showCookBtn.setActionCommand("showCook");
        showChefBtn.setActionCommand("showChef");
        showDeliveryPeopleBtn.setActionCommand("showDeliveryPeople");
        showConsumeBtn.setActionCommand("showConsume");
        showFoodSupplierBtn.setActionCommand("showFoodSupplier");
        showProvideBtn.setActionCommand("showProvide");

        pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        tb = new TableWindow();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch(cmd){
            case "order":
                OrdersModel order = new OrdersModel(Integer.valueOf(orderNumField.getText()), new Timestamp(System.currentTimeMillis()));
                dbhandler.insertOrder(order);
                break;
            case "dish":
                DishesModel dish = new DishesModel(dishField.getText(), Integer.valueOf(dishOrderNumField.getText()), tastePreferenceField.getText());
                dbhandler.insertDish(dish);
                break;
            case "ingredient":
                try {
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date date = formatter.parse(ingredientsDateField.getText());
                    IngredientsModel ingred = new IngredientsModel(ingredientsLotField.getText(),
                            ingredientsNameField.getText(),
                            new java.sql.Date(date.getTime()),
                            Integer.valueOf(ingredientsQuantityField.getText()),
                            Integer.valueOf(ingredientsPosIDField.getText()),
                            ingredientsSINField.getText()
                    );
                    CategoryModel cate = new CategoryModel(ingredientsNameField.getText(),ingredientsTypeField.getText());
                    dbhandler.insertIngredient(ingred, cate);
                } catch(Exception g){
                    System.out.println("Exception :" + g);
                }
                break;
            case "cook":
                CookModel cook = new CookModel(cookSINField.getText(), cookDishNameField.getText(), Integer.valueOf(cookOrderNumberField.getText()));
                dbhandler.insertCook(cook);
                break;
            case "chef":
                ChefModel chef = new ChefModel(chefNameField.getText(), chefSINField.getText(), chefContactNumberField.getText(),chefHomeAddressField.getText());
                ChefAddressModel chefAddr = new ChefAddressModel(chefSINField.getText(),chefBranchAddressField.getText());
                dbhandler.insertChef(chef,chefAddr);
                break;
            case "delivery people":
                DeliveryPeopleModel delivPep = new DeliveryPeopleModel(deliveryPeopleSINField.getText(), deliveryPeopleNameField.getText(),
                        deliveryPeopleContactNumberField.getText(), deliveryPeopleAddressField.getText());
                dbhandler.insertDeliveryPeople(delivPep);
                break;
            case "consume":
                ConsumeModel consume = new ConsumeModel(consumeDishNameField.getText(),
                        Integer.valueOf(consumeOrderNumberField.getText()), consumeLotNumberField.getText(),
                        Integer.valueOf(consumeQuantityField.getText())
                );
                dbhandler.insertConsume(consume);
                break;
            case "food supplier":
                FoodSupplierModel fSupplier = new FoodSupplierModel(foodSupplierCompanyNameField.getText(),foodSupplierAddressField.getText(),
                foodSupplierContactNumberField.getText(),foodSupplierEmailField.getText());
                dbhandler.insertFoodSupplier(fSupplier);
                break;
            case "provide":
                ProvideModel provide = new ProvideModel(provideCompanyNameField.getText(),provideLotNumberField.getText());
                dbhandler.insertProvide(provide);
                break;
            case "showOrder":
                tb.updateTable(dbhandler.getAllOrder(), dbhandler.getOrderColumn(), "Orders");
                break;
            case "showDish":
                tb.updateTable(dbhandler.getAllDishes(), dbhandler.getDishesColumn(), "Dishes");
                break;
            case "showIngredient":
                tb.updateTable(dbhandler.getAllIngredients(),dbhandler.getIngredientsColumn(),"Ingredients");
                break;
            case "showCook":
                tb.updateTable(dbhandler.getAllCook(),dbhandler.getCookColumn(),"Cook");
                break;
            case "showChef":
                tb.updateTable(dbhandler.getAllChef(),dbhandler.getChefColumn(),"Chefs");
                break;
            case "showDeliveryPeople":
                tb.updateTable(dbhandler.getAllDeliveryPeople(),dbhandler.getDeliveryPeopleColumn(),"Delivery People");
                break;
            case "showConsume":
                tb.updateTable(dbhandler.getAllConsume(),dbhandler.getConsumeColumn(),"Consume");
                break;
            case "showFoodSupplier":
                tb.updateTable(dbhandler.getAllFoodSupplier(),dbhandler.getFoodSupplierColumn(),"Food suppliers");
                break;
            case "showProvide":
                tb.updateTable(dbhandler.getAllProvide(),dbhandler.getProvideColumn(), "Provide");
                break;
            case "back":
                dispose();
                new MenuWindow(dbhandler);
                break;
            default:
                break;
        }
    }

}