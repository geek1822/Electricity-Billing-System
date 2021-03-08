package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;


public class Customer_request extends JFrame implements ActionListener {
    JTextField enterMeter_number;
    JLabel meter_no,box;
    JPanel p1,p2;
    ButtonGroup meter;
    JRadioButton upgrade_meter,change_meter,default_reading;
    JButton check,clear,submit;

    public Customer_request()
    {
        setLayout(null);
        setLocation(450,150);
        setSize(290,490);
        setVisible(true);
        setResizable(false);

        p1 = new JPanel();
        meter_no = new JLabel("Enter your meter number");
        enterMeter_number = new JTextField(8);
        p1.add(meter_no);
        p1.add(enterMeter_number);
        p1.setBounds(60,10,150,50);
        add(p1);


        check  = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        clear = new JButton("Clear");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);

        check.addActionListener(this);
        clear.addActionListener(this);

        check.setBounds(20,60,70,20);
        clear.setBounds(180,60,70,20);
        add(check);
        add(clear);

        p2 = new JPanel();
        p2.setBounds(35,90,200,150);
        p2.setOpaque(true);
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setForeground(Color.BLACK);
        add(p2);


    }

    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == check) {
                connectToMysql c = new connectToMysql();
                int det = Integer.parseInt(enterMeter_number.getText());
                String q = "SELECT * FROM elect_bill.customer where meterid='" + det + "'";
                ResultSet rs = c.st.executeQuery(q);
                if (rs.next()) {
                    String c_name = rs.getString("first") + " " + rs.getString("last");
                    JLabel name = new JLabel("Customer Name");
                    JLabel j = new JLabel(c_name);
                    p2 = new JPanel();
                    p2.setBounds(35, 90, 200, 130);
                    p2.setOpaque(true);
                    p2.setBackground(Color.LIGHT_GRAY);
                    add(p2);
                    p2.setLayout(new GridLayout(2, 1));
                    p2.add(name);
                    p2.add(j);
                    p2.revalidate();
                    p2.repaint();
                }
                box = new JLabel("* Select your request below:");
                box.setBounds(20, 260, 300, 20);
                add(box);
                box.repaint();

                upgrade_meter = new JRadioButton();
                change_meter = new JRadioButton();
                default_reading = new JRadioButton();

                meter = new ButtonGroup();

                upgrade_meter.setText("Upgrade meter");
                add(upgrade_meter);
                upgrade_meter.setBounds(15, 290, 300, 30);

                change_meter.setText("Change of meter");
                add(change_meter);
                change_meter.setBounds(15, 330, 300, 30);

                default_reading.setText("Default meter reading");
                add(default_reading);
                default_reading.setBounds(15, 370, 300, 30);

                meter.add(upgrade_meter);
                meter.add(change_meter);
                meter.add(default_reading);


                submit = new JButton();
                submit = new JButton("Submit");
                submit.setBackground(Color.BLACK);
                submit.setForeground(Color.WHITE);
                submit.setBounds(20, 420, 240, 20);
                add(submit, BorderLayout.SOUTH);
                submit.repaint();
                submit.addActionListener(ae -> {
                    JOptionPane.showMessageDialog(null, "Your Request is Successfully Updated.");
                    setVisible(false);
                });
            }else
                setVisible(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
       new Customer_request();

    }
}

