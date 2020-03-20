package ca.ubc.cs304.ui;

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
    public MenuWindow() {
        super("Menu Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("MENU");

        JButton insertBtn = new JButton("Insert");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

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

        // Set btn action listener
        insertBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        insertBtn.setActionCommand("insert");
        updateBtn.setActionCommand("update");
        deleteBtn.setActionCommand("delete");

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
                new InsertWindow();
                break;
            case "update":
                dispose();
                new UpdateWindow();
                break;
            case "delete":
                dispose();
                new DeleteWindow();
                break;
            default:
                break;
        }
    }
}
