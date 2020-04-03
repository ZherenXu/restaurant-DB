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

public class UpdateWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;

    JTextField chefSINField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField chefAddressField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField chefContactNumberField = new JTextField(TEXT_FIELD_WIDTH);

    JTextField foodSupplierCompanyNameField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField foodSupplierEmailField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField foodSupplierContactNumberField = new JTextField(TEXT_FIELD_WIDTH);

    JTextField deliveryPeopleSINField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField deliveryPeopleContactNumberField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField deliveryPeopleAddressField = new JTextField(TEXT_FIELD_WIDTH);

    JTextField branchAddressField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField branchManagerNameField = new JTextField(TEXT_FIELD_WIDTH);
    JTextField branchContactNumberField = new JTextField(TEXT_FIELD_WIDTH);

    public UpdateWindow(DatabaseConnectionHandler dbhandler) {
        super("Update Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("UPDATE DATA");
        JLabel updateChefLabel = new JLabel("Chef:  SIN: ");
        JLabel updateChefAddressLabel = new JLabel("New Address: ");
        JLabel updateChefContactNumberLabel = new JLabel("New Contact Number: ");
        JLabel updateFoodSupplierLabel = new JLabel("Food Supplier:  Company Name: ");
        JLabel updateFoodSupplierEmailLabel = new JLabel("New Email: ");
        JLabel updateFoodSupplierContactNumberLabel = new JLabel("New Contact Number: ");
        JLabel updateDeliveryPeopleLabel = new JLabel("Delivery People:  SIN: ");
        JLabel updateDeliveryPeopleContactNumberLabel = new JLabel("New Contact Number: ");
        JLabel updateDeliveryPeopleAddressLabel = new JLabel("New Address: ");
        JLabel updateBranchLabel = new JLabel("Branch:  Address:");
        JLabel updateBranchManagerNameLabel = new JLabel("New Manager Name: ");
        JLabel updateBranchContactNumberLabel = new JLabel("New Contact Number: ");

        JButton updateChefBtn = new JButton("Update");
        JButton updateFoodSupplierBtn = new JButton("Update");
        JButton updateDeliveryPeopleBtn = new JButton("Update");
        JButton updateBranchBtn = new JButton("Update");
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
        /////////////////////////////////////CHEF///////////////////////////////////
        // CHEF SIN label
        updateChefLabel.setFont(updateChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(updateChefLabel, c);
        contentPane.add(updateChefLabel);

        // Chef SIN text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefSINField, c);
        contentPane.add(chefSINField);

        // Chef Address label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateChefAddressLabel, c);
        contentPane.add(updateChefAddressLabel);

        // Chef Address text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefAddressField, c);
        contentPane.add(chefAddressField);

        // Contact Number label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateChefContactNumberLabel, c);
        contentPane.add(updateChefContactNumberLabel);

        // Contact Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefContactNumberField, c);
        contentPane.add(chefContactNumberField);

        // Chef button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(1, 0, 10, 10);
        gb.setConstraints(updateChefBtn, c);
        contentPane.add(updateChefBtn);
        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////FOOD SUPPLIER////////////////////////////////
        // FoodSupplier Company Name label
        updateChefLabel.setFont(updateChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(updateFoodSupplierLabel, c);
        contentPane.add(updateFoodSupplierLabel);

        // FoodSupplier Company Name text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierCompanyNameField, c);
        contentPane.add(foodSupplierCompanyNameField);

        // FoodSupplier Email label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateFoodSupplierEmailLabel, c);
        contentPane.add(updateFoodSupplierEmailLabel);

        // FoodSupplier Email text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierEmailField, c);
        contentPane.add(foodSupplierEmailField);

        // Contact Number label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateFoodSupplierContactNumberLabel, c);
        contentPane.add(updateFoodSupplierContactNumberLabel);

        // Contact Number field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(foodSupplierContactNumberField, c);
        contentPane.add(foodSupplierContactNumberField);

        // FoodSupplier button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(1, 0, 10, 10);
        gb.setConstraints(updateFoodSupplierBtn, c);
        contentPane.add(updateFoodSupplierBtn);
        ////////////////////////////////////////////////////////////////////////////
        //////////////////////////////DELIVERY PEOPLE///////////////////////////////
        // DeliveryPeople SIN label
        updateChefLabel.setFont(updateChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(updateDeliveryPeopleLabel, c);
        contentPane.add(updateDeliveryPeopleLabel);

        // DeliveryPeople SIN text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleSINField, c);
        contentPane.add(deliveryPeopleSINField);

        // Contact Number label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateDeliveryPeopleContactNumberLabel, c);
        contentPane.add(updateDeliveryPeopleContactNumberLabel);

        // Contact Number field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleContactNumberField, c);
        contentPane.add(deliveryPeopleContactNumberField);

        // DeliveryPeople Email label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateDeliveryPeopleAddressLabel, c);
        contentPane.add(updateDeliveryPeopleAddressLabel);

        // DeliveryPeople Email text field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(deliveryPeopleAddressField, c);
        contentPane.add(deliveryPeopleAddressField);

        // DeliveryPeople button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(1, 0, 10, 10);
        gb.setConstraints(updateDeliveryPeopleBtn, c);
        contentPane.add(updateDeliveryPeopleBtn);
        ////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////BRANCH///////////////////////////////////
        // Branch Address label
        updateChefLabel.setFont(updateChefLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(updateBranchLabel, c);
        contentPane.add(updateBranchLabel);

        // Branch Address field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(branchAddressField, c);
        contentPane.add(branchAddressField);

        // Branch ManagerName label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateBranchManagerNameLabel, c);
        contentPane.add(updateBranchManagerNameLabel);

        // Branch ManagerName field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(branchManagerNameField, c);
        contentPane.add(branchManagerNameField);

        // Branch ContactNumber label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(updateBranchContactNumberLabel, c);
        contentPane.add(updateBranchContactNumberLabel);

        // Branch ContactNumber field
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(branchContactNumberField, c);
        contentPane.add(branchContactNumberField);

        // Branch button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(1, 0, 10, 10);
        gb.setConstraints(updateBranchBtn, c);
        contentPane.add(updateBranchBtn);
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


        updateChefBtn.addActionListener(this);
        updateFoodSupplierBtn.addActionListener(this);
        updateDeliveryPeopleBtn.addActionListener(this);
        updateBranchBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        backBtn.addActionListener(this);

        updateChefBtn.setActionCommand("chef");
        updateFoodSupplierBtn.setActionCommand("food supplier");
        updateDeliveryPeopleBtn.setActionCommand("delivery people");
        updateBranchBtn.setActionCommand("branch");
        refreshBtn.setActionCommand("refresh");
        backBtn.setActionCommand("back");

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
            case "chef":
                //break;
            case "food supplier":
                //break;
            case "delivery people":
                //break;
            case "branch":
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

