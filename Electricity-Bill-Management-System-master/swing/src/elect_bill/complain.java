package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;


public class complain extends JFrame implements ActionListener {
    JLabel meter_no,box;
    JTextArea enter_complaint;
    JTextField enterMeter_number;
    JPanel p1,p2;
    JButton check,clear,submit;
    complain()
    {
        super("Complain");
        setLayout(null);
        setLocation(450,150);
        setSize(290,440);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p1 = new JPanel();
        meter_no = new JLabel("Enter your meter number");
        enterMeter_number = new JTextField(8);

        p1.add(meter_no);
        p1.add(enterMeter_number);
        p1.setBounds(60,10,145,50);
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
        p2.setBounds(35,90,200,130);
        p2.setOpaque(true);
        p2.setBackground(Color.LIGHT_GRAY);
        add(p2);

        setResizable(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        try {
            if (e.getSource() == check) {
                connectToMysql c=new connectToMysql();
                int det=Integer.parseInt( enterMeter_number.getText());
                String q="SELECT * FROM elect_bill.customer where meterid='"+det+"'";
                ResultSet rs= c.st.executeQuery(q);
                if(rs.next()){
                    String c_name=rs.getString("first")+" "+rs.getString("last");
                    JLabel name = new JLabel("Customer Name");
                    JLabel j=new JLabel(c_name);
                    p2 = new JPanel();
                    p2.setBounds(35,90,200,130);
                    p2.setOpaque(true);
                    p2.setBackground(Color.LIGHT_GRAY);
                    add(p2);
                    p2.setLayout(new GridLayout(2,1));
                    p2.add(name);
                    p2.add(j);
                    p2.revalidate();
                    p2.repaint();
                }
                box = new JLabel("* Enter  your  complaint  below:");
                box.setBounds(45,240,300,20);
                add(box);
                box.repaint();
                enter_complaint = new JTextArea();
                enter_complaint.setBounds(15,270,243,70);
                add(enter_complaint);
                enter_complaint.repaint();



                submit = new JButton();
                submit = new JButton("Submit");
                submit.setBackground(Color.BLACK);
                submit.setForeground(Color.WHITE);
                submit.setBounds(20, 350, 240, 20);
                add(submit,BorderLayout.SOUTH);
                submit.repaint();
                submit.addActionListener(ae -> {
                    JOptionPane.showMessageDialog(null, "Your Complain is Successfully Updated.");
                    setVisible(false);
                });
            } else
                setVisible(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
        public static void main(String[] args)
    {
        new complain();
    }
}

