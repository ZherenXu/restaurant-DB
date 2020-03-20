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

public class DeleteWindow extends JFrame implements ActionListener{
    private static final int TEXT_FIELD_WIDTH = 10;

    public DeleteWindow() {
        super("Delete Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel testLabel = new JLabel("TEST");

        JLabel titleLabel = new JLabel("DELETE DATA");
        JLabel insertOrderLabel = new JLabel("Order:");
        JLabel orderNumLabel = new JLabel(" Order #: ");

        JTextField orderNumField = new JTextField(TEXT_FIELD_WIDTH);

        JButton deleteOrderBtn = new JButton("Delete");
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

        // Delete order button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(deleteOrderBtn, c);
        contentPane.add(deleteOrderBtn);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        // Back button
        c.gridwidth = GridBagConstraints.PAGE_END;
        c.insets = new Insets(5, 10, 10, 5);
        gb.setConstraints(backBtn, c);
        contentPane.add(backBtn);

        backBtn.addActionListener(this);
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
        if (cmd.equals("back")) {
            dispose();
            new MenuWindow();
        }
    }
}
