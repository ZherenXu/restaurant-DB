package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.model.CategoryModel;
import ca.ubc.cs304.model.IngredientsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;

public class SelectWindow extends JFrame implements ActionListener {
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();

    JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberBranchField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberDateStartField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberDateEndField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberTimeStartField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField orderNumberTimeEndField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField dishesLotNumberField = new JTextField(TEXT_FIELD_WIDTH);

    public SelectWindow(DatabaseConnectionHandler dbhandler) {
        super("Select Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("SELECT DATA");
        JLabel findByOrderNumberLabel = new JLabel("Find other information by order number:");
        JLabel findTheOrderNumberLabel = new JLabel("Find The Order Number");
        JLabel orderNumberBranchLabel = new JLabel("Branch: ");
        JLabel orderNumberDateStartLabel = new JLabel("Date(dd-mm-yyyy hh:mm:ss)             Start: ");
        JLabel orderNumberDateEndLabel = new JLabel("End: ");
        JLabel findDishesLabel = new JLabel("Find Dishes");
        JLabel dishesLotNumberLabel = new JLabel("Lot Number: ");
        JLabel findIngredientsByAllChefLabel = new JLabel("Find Ingredients Used By All Chefs");

        JButton findChefBtn = new JButton("Chef information");
        JButton findIngredientBtn = new JButton("Ingredient information");
        JButton findDeliveryPeopleBtn = new JButton("Delivery People information");
        JButton findFoodSupplierBtn = new JButton("Food Supplier information");
        JButton findStorageTemperatureBtn = new JButton("Storage Temperature");
        JButton selectOrderNumberBtn = new JButton("Select");
        JButton selectDishesBtn = new JButton("Select");
        JButton selectIngredientsBtn = new JButton("Select");
        JButton backBtn = new JButton("Back");
        JButton branchBtn = new JButton(" All Branch Info ");
//        JButton shelfBtn = new JButton("   Shelf    ");
//        JButton refrigeratorBtn = new JButton("Refrigerator");
//        JButton freezerBtn = new JButton("  Freezer   ");
        JButton storageBtn = new JButton("All Storage Info");

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
        c.gridwidth = GridBagConstraints.HORIZONTAL;
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

        // Date Start
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumberDateStartLabel, c);
        contentPane.add(orderNumberDateStartLabel);

        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumberDateStartField, c);
        contentPane.add(orderNumberDateStartField);

        // Date End
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(orderNumberDateEndLabel, c);
        contentPane.add(orderNumberDateEndLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderNumberDateEndField, c);
        contentPane.add(orderNumberDateEndField);

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
        // Branch button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(branchBtn, c);
        contentPane.add(branchBtn);

        // Storage button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(storageBtn, c);
        contentPane.add(storageBtn);
//        // Shelf button
//        c.gridwidth = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(5, 10, 10, 5);
//        gb.setConstraints(shelfBtn, c);
//        contentPane.add(shelfBtn);
//        // Refrigerator button
//        c.gridwidth = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(5, 10, 10, 5);
//        gb.setConstraints(refrigeratorBtn, c);
//        contentPane.add(refrigeratorBtn);
//        // freezer button
//        c.gridwidth = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(5, 10, 10, 5);
//        gb.setConstraints(freezerBtn, c);
//        contentPane.add(freezerBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        backBtn.addActionListener(this);
        findChefBtn.addActionListener(this);
        findIngredientBtn.addActionListener(this);
        findDeliveryPeopleBtn.addActionListener(this);
        findFoodSupplierBtn.addActionListener(this);
        findStorageTemperatureBtn.addActionListener(this);
        selectOrderNumberBtn.addActionListener(this);
        selectDishesBtn.addActionListener(this);
        selectIngredientsBtn.addActionListener(this);
        branchBtn.addActionListener(this);
        storageBtn.addActionListener(this);
//        shelfBtn.addActionListener(this);
//        refrigeratorBtn.addActionListener(this);
//        freezerBtn.addActionListener(this);

        backBtn.setActionCommand("back");

        findChefBtn.setActionCommand("find chef");
        findIngredientBtn.setActionCommand("find ingredient");
        findDeliveryPeopleBtn.setActionCommand("find delivery people");
        findFoodSupplierBtn.setActionCommand("find food supplier");
        findStorageTemperatureBtn.setActionCommand("find storage temperature");

        selectOrderNumberBtn.setActionCommand("select order number");
        selectDishesBtn.setActionCommand("select dishes");
        selectIngredientsBtn.setActionCommand("select ingredients");

        branchBtn.setActionCommand("branch");
        storageBtn.setActionCommand("storage");
//        shelfBtn.setActionCommand("shelf");
//        refrigeratorBtn.setActionCommand("refrigerator");
//        freezerBtn.setActionCommand("freezer");

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
                tb.updateTable(dbhandler.findChefByOrder(Integer.valueOf(orderNumField.getText())), dbhandler.chefOrderColumn(), "chefs");
                break;
            case "find ingredient":
                tb.updateTable(dbhandler.findIngredientByOrder(Integer.valueOf(orderNumField.getText())), dbhandler.ingredientOrderColumn(), "Ingredients");
                break;
            case "find delivery people":
                tb.updateTable(dbhandler.findDeliveryByOrder(Integer.valueOf(orderNumField.getText())), dbhandler.deliveryOrderColumn(), "Delivery People");
                break;
            case "find food supplier":
                tb.updateTable(dbhandler.findSupplierByOrder(Integer.valueOf(orderNumField.getText())), dbhandler.supplierOrderColumn(), "Food Suppliers");
                break;
            case "find storage temperature":
                tb.updateTable(dbhandler.findTempByOrder(Integer.valueOf(orderNumField.getText())), dbhandler.tempOrderColumn(), "Temperatures");
                break;
            case "select order number":
                try {
                    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                    java.util.Date startDate = formatter.parse(orderNumberDateStartField.getText());
                    java.util.Date endDate = formatter.parse(orderNumberDateEndField.getText());
                    Timestamp startTs = new Timestamp(startDate.getTime());
                    Timestamp endTs = new Timestamp(endDate.getTime());
                    System.out.println(startTs.toString());
                    System.out.println(endTs.toString());
                    tb.updateTable(dbhandler.findOrder(new Timestamp(startDate.getTime()), new Timestamp(endDate.getTime()), orderNumberBranchField.getText()), dbhandler.orderColumn(), "Orders");
                } catch(Exception g){
                    System.out.println("Exception :" + g);
                }
                break;
            case "select dishes":
                tb.updateTable(dbhandler.findDishesByIngredient(dishesLotNumberField.getText()), dbhandler.dishesIngredientColumn(), "Dishes");
                break;
            case "select ingredients":
                tb.updateTable(dbhandler.division(), dbhandler.divisionColumn(), "Ingredients");
                break;
            case "back":
                tb.closeTable();
                dispose();
                new MenuWindow(dbhandler);
                break;
            case "branch":
                tb.updateTable(dbhandler.getAllBranch(),dbhandler.getBranchColumn(),"Branches");
                break;
//            case "shelf":
//                tb.updateTable(dbhandler.getAllShelf(), dbhandler.getStorageColumn(), "Shelves");
//                break;
//            case "refrigerator":
//                tb.updateTable(dbhandler.getAllRefrigerator(), dbhandler.getStorageColumn(), "Refrigerators");
//                break;
//            case "freezer":
//                tb.updateTable(dbhandler.getAllFreezer(), dbhandler.getStorageColumn(), "Freezers");
//                break;
            case "storage":
                tb.updateTable(dbhandler.getAllStorage(),dbhandler.getStorageColumn(), "Storage");
                break;
            default:
                break;
        }
    }
}
