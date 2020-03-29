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

public class MenuWindow extends JFrame implements ActionListener{
    private DatabaseConnectionHandler dbhandler;
    public MenuWindow(DatabaseConnectionHandler dbhandler) {
        super("Menu Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("MENU");

        JButton insertBtn = new JButton("Insert");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton selectBtn = new JButton("Select");
        JButton statBtn = new JButton("Statistic");

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

        // Insert button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(insertBtn, c);
        contentPane.add(insertBtn);

        // Update button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(updateBtn, c);
        contentPane.add(updateBtn);

        // Delete button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteBtn, c);
        contentPane.add(deleteBtn);

        // Select button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(selectBtn, c);
        contentPane.add(selectBtn);

        // Statistic button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(statBtn, c);
        contentPane.add(statBtn);

        // Test button
        JButton testBtn = new JButton("Test");
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(testBtn, c);
        contentPane.add(testBtn);
        testBtn.addActionListener(this);
        testBtn.setActionCommand("test");

        // Set btn action listener
        insertBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        selectBtn.addActionListener(this);
        statBtn.addActionListener(this);
        insertBtn.setActionCommand("insert");
        updateBtn.setActionCommand("update");
        deleteBtn.setActionCommand("delete");
        selectBtn.setActionCommand("select");
        statBtn.setActionCommand("statistic");

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
        switch(cmd) {
            case "insert":
                dispose();
                new InsertWindow(dbhandler);
                break;
            case "update":
                dispose();
                new UpdateWindow(dbhandler);
                break;
            case "delete":
                dispose();
                new DeleteWindow(dbhandler);
                break;
            case "select":
                dispose();
                new SelectWindow(dbhandler);
                break;
            case "statistic":
                dispose();
                new StatWindow(dbhandler);
            case "test":
                System.out.println("click test");
                dbhandler.deleteOrder(0);
                break;
            default:
                break;
        }
    }
}
