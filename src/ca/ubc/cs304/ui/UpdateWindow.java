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

    public UpdateWindow(DatabaseConnectionHandler dbhandler) {
        super("Update Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("UPDATE DATA");
        JLabel insertOrderLabel = new JLabel("Order:");
        JLabel chefLabel = new JLabel(" Chef: ");
        JLabel dishLabel = new JLabel(" Dish: ");
        JLabel orderNumLabel = new JLabel(" Order #: ");
        JLabel insertDishLabel = new JLabel("Dish:");
        JLabel ingredientLabel = new JLabel("ingredient: ");
        JLabel dishChefLabel = new JLabel("Chef: ");

        JTextField chefField = new JTextField(TEXT_FIELD_WIDTH);
        JTextField orderDishField = new JPasswordField(TEXT_FIELD_WIDTH);
        JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);
        JTextField ingredientField = new JTextField(TEXT_FIELD_WIDTH);
        JTextField dishChefField = new JTextField(TEXT_FIELD_WIDTH);

        JButton updateOrderBtn = new JButton("Update");
        JButton updateDishBtn = new JButton("Update");
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
        ////////////////////////////////UPDATE ORDERS///////////////////////////////
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

        // Chef(order) label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(chefLabel, c);
        contentPane.add(chefLabel);

        // Chef(order) text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(chefField, c);
        contentPane.add(chefField);

        // Dish(order) label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishLabel, c);
        contentPane.add(dishLabel);

        // Dish(order) text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(orderDishField, c);
        contentPane.add(orderDishField);

        // Update order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(updateOrderBtn, c);
        contentPane.add(updateOrderBtn);

        ////////////////////////////////////////////////////////////////////////////
        //////////////////////////////UPDATE DISHES/////////////////////////////////
        // Dish label
        insertDishLabel.setFont(insertDishLabel.getFont().deriveFont(Font.BOLD));
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 3, 0);
        gb.setConstraints(insertDishLabel, c);
        contentPane.add(insertDishLabel);

        // Ingredient label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(ingredientLabel, c);
        contentPane.add(ingredientLabel);

        // Ingredient text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(ingredientField, c);
        contentPane.add(ingredientField);

        // Chef(dish) label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(dishChefLabel, c);
        contentPane.add(dishChefLabel);

        // Chef(dish) text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(dishChefField, c);
        contentPane.add(dishChefField);

        // Update dish button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(updateDishBtn, c);
        contentPane.add(updateDishBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        updateDishBtn.addActionListener(this);
        updateOrderBtn.addActionListener(this);
        backBtn.addActionListener(this);

        updateDishBtn.setActionCommand("dish");
        updateOrderBtn.setActionCommand("order");
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
            case "back":
                dispose();
                new MenuWindow(dbhandler);
                break;
            default:
                break;
        }
    }
}

