package ca.ubc.cs304.ui;

import ca.ubc.cs304.database.DatabaseConnectionHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StatWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;

    private JTextField resultField = new JTextField(TEXT_FIELD_WIDTH);
    private TableWindow tb = new TableWindow();

    public StatWindow(DatabaseConnectionHandler dbhandler) {
        super("Stat Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("STATISTIC");
        JLabel maxMinLabel = new JLabel("Max & Min Value:");
        JLabel aveLabel = new JLabel("Average Value:");
        JLabel countNumLabel = new JLabel("Count Number:");
        JLabel resultLabel = new JLabel("Result: ");

        JButton maxOfConsumeBtn = new JButton("Popular ingredients among customers");
        JButton minOfConsumeBtn = new JButton("Unpopular ingredients among customers");
        JButton aveShelfTempBtn = new JButton("Average Temperature in Shelf");
        JButton aveRefrigeratorTempBtn = new JButton("Average Temperature in Refrigerator");
        JButton aveFreezerTempBtn = new JButton("Average Temperature in Freezer");
        JButton countIngredientsDeliveryBtn = new JButton("Delivering frequency for each Delivery People");
        JButton countDishByIngredientsBtn = new JButton("# of Dishes Used by each Ingredient");
        JButton backBtn = new JButton("Back");

        TableWindow tb = new TableWindow();

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
        //////////////////////////////MAX/MIN CONSUME///////////////////////////////
        // Max Min Label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(maxMinLabel, c);
        contentPane.add(maxMinLabel);

        // Max Consume button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(maxOfConsumeBtn, c);
        contentPane.add(maxOfConsumeBtn);

        // Min Consume button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(minOfConsumeBtn, c);
        contentPane.add(minOfConsumeBtn);

        ////////////////////////////////////////////////////////////////////////////
        //////////////////////////////AVE TEMPERATURE///////////////////////////////
        // Average Label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(aveLabel, c);
        contentPane.add(aveLabel);

        // Ave Shelf Temp Button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(aveShelfTempBtn, c);
        contentPane.add(aveShelfTempBtn);

        // Ave Refrigerator Temp Button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(aveRefrigeratorTempBtn, c);
        contentPane.add(aveRefrigeratorTempBtn);

        // Ave Freezer Temp Button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(aveFreezerTempBtn, c);
        contentPane.add(aveFreezerTempBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////COUNT NUMBER////////////////////////////////
        // Count# Label
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(countNumLabel, c);
        contentPane.add(countNumLabel);

        // # of Ingredients for Delivery People button
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(countIngredientsDeliveryBtn, c);
        contentPane.add(countIngredientsDeliveryBtn);

        // # of Dishes used by Certain Ingredients
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 10, 1, 0);
        gb.setConstraints(countDishByIngredientsBtn, c);
        contentPane.add(countDishByIngredientsBtn);

        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Result Label
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        gb.setConstraints(resultLabel, c);
        contentPane.add(resultLabel);
        // Result field
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1, 0, 1, 10);
        gb.setConstraints(resultField, c);
        contentPane.add(resultField);
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        // Button Listener
        backBtn.addActionListener(this);
        maxOfConsumeBtn.addActionListener(this);
        minOfConsumeBtn.addActionListener(this);
        aveShelfTempBtn.addActionListener(this);
        aveRefrigeratorTempBtn.addActionListener(this);
        aveFreezerTempBtn.addActionListener(this);
        countIngredientsDeliveryBtn.addActionListener(this);
        countDishByIngredientsBtn.addActionListener(this);
        // Button Command
        backBtn.setActionCommand("back");
        maxOfConsumeBtn.setActionCommand("get max consume");
        minOfConsumeBtn.setActionCommand("get min consume");
        aveShelfTempBtn.setActionCommand("get average shelf temperature");
        aveRefrigeratorTempBtn.setActionCommand("get average refrigerator temperature");
        aveFreezerTempBtn.setActionCommand("get average freezer temperature");
        countIngredientsDeliveryBtn.setActionCommand("# of Ingredients for Delivery People");
        countDishByIngredientsBtn.setActionCommand("# of Dishes Used by Certain Ingredients");

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
            case "get max consume":
                tb.updateTable(dbhandler.maxIngredient(),dbhandler.minMaxColumn(),"popular ingredients");
                break;
            case "get min consume":
                tb.updateTable(dbhandler.minIngredient(),dbhandler.minMaxColumn(),"unpopular ingredients");
                break;
            case "get average shelf temperature":
                resultField.setText(Float.toString(dbhandler.avgTempShelf()));
                break;
            case "get average refrigerator temperature":
                resultField.setText(Float.toString(dbhandler.avgTempRef()));
                break;
            case "get average freezer temperature":
                resultField.setText(Float.toString(dbhandler.avgTempFreezer()));    //Change this//
                break;
            case "# of Ingredients for Delivery People":
                tb.updateTable(dbhandler.DPCountIngredient(),dbhandler.DPCountColumn(),"# of Ingredients for Delivery People");
                break;
            case "# of Dishes Used by Certain Ingredients":
                tb.updateTable(dbhandler.ICountDishes(),dbhandler.ICountColumn(),"# of Dishes Used by Certain Ingredients");
                break;
            case "back":
                tb.closeTable();
                dispose();
                new MenuWindow(dbhandler);
                break;
            default:
                break;
        }
    }
}
