package elect_bill;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class about extends JFrame implements ActionListener {

    JLabel content;
    JTextArea show;
    JButton okay;

    about()
    {
        super("Abstract");
        setLayout(null);
        setLocation(400,300);
        setSize(590,500);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);

        content = new JLabel("ABSTRACT");
        content.setBounds(210,30,150,30);
        content.setFont(new Font("Serif", Font.BOLD, 25));
        add(content);


        show = new JTextArea();
        show.setFont(new Font("Serif", Font.BOLD, 16));
        show.setText(" Nowadays the billing system integrated with smart meter is used by staffs,");
        show.append("\n residents and those who use electricity to retrieve the price rate and meter");
        show.append("\n value of power consumption. The administrator can analyze the customer's ");
        show.append("\n power consumption data and generate the report from the data online. The");
        show.append("\n prototype will be able to introduce the billing system to the customers, get");
        show.append("\n the power consumption from smart meter, keep the data in centralized");
        show.append("\n database and generate the report. It will help the user to access the data");
        show.append("\n and report easily through the report. The customer can request for queries");
        show.append("\n like change of meter, upgrade meter and  for default meter reading. The");
        show.append("\n software provides for customers to be able to register complaint regarding");
        show.append("\n their meter.");


        show.setBounds(25,80,516,270);
        add(show);

        okay = new JButton("OK");
        okay.setBounds(190,400,200,30);
        okay.setBackground(Color.BLACK);
        okay.setForeground(Color.WHITE);
        add(okay);
        okay.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okay)
            setVisible(false);
    }
    public static void main(String[] args)
    {
        new about();
    }

}
