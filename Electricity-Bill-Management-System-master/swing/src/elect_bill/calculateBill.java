package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class calculateBill extends JFrame implements ActionListener {
    JLabel meterId,month,units,title;
    Choice months;
    JTextField enterMeterId,enterUnits;
    JPanel p,p1,p2,p3;
    JButton cancel,calculate,save;
    JTextArea display;
    calculateBill(){
        super("Calculate Electricity Bill");
        p=new JPanel();
        title=new JLabel("Calculate Electricity Bill");
        meterId=new JLabel("Meter Id");
        month=new JLabel("Month");
        units=new JLabel("Units Consumed");

        enterMeterId=new JTextField(8);
        enterUnits=new JTextField(10);
        months=new Choice();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

        cancel=new JButton("Cancel");
        calculate=new JButton("Calculate");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        calculate.setBackground(Color.black);
        calculate.setForeground(Color.WHITE);

        setLayout(null);

        title.setFont(new Font("",Font.PLAIN,26));
        title.setBounds(170,2,1000,50);
        add(title);

        p.setLayout(new GridLayout(1,2));
        p.add(meterId);
        p.add(enterMeterId);
        p.setBounds(40,90,200,25);
        add(p);

        p1=new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p1.add(month);
        p1.add(months);
        p1.setBounds(40,140,200,25);
        add(p1);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,2));
        p2.add(units);
        p2.add(enterUnits);
        p2.setBounds(40,190,200,25);
        add(p2);

        p3=new JPanel();
        p3.setLayout(new GridLayout(1,2));
        p3.add(cancel);
        p3.add(calculate);
        p3.setBounds(40,240,200,25);
        add(p3);

        display=new JTextArea(20,15);
        display.setBounds(300,90,230,250);
        add(display);

        save=new JButton("Save");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(40,290,200,25);

        setVisible(true);
        setLocation(50,80);
        setSize(600,410);
        setResizable(false);

        calculate.addActionListener(this);
        cancel.addActionListener(this);

    }
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==calculate){
                connectToMysql c=new connectToMysql();
                int meterNo=Integer.parseInt(enterMeterId.getText());
                String q="SELECT * FROM elect_bill.customer where meterid='"+meterNo+"'";
                ResultSet rs= c.st.executeQuery(q);
                if(rs.next()){
                    String m=months.getSelectedItem();
                    int consumed_units=Integer.parseInt(enterUnits.getText());
                    calculateAmount ca=new calculateAmount(consumed_units);
                    int amount=ca.show();
                    System.out.println(amount+" "+consumed_units);
                    Date d=new Date();
                    int year=d.getYear()+1900;
                    display.setText("                         XYZ Power Limited\n            Electricity Bill for "+m+","+year+"\n=================================");
                    display.append("\n\n\n         Meter Rent :                           98");
                    display.append(  "\n         MCB Rent :                            42");
                    display.append(  "\n         Service Tax :                        112");
                    display.append(  "\n         GST@9% :                             48");
                    display.append(  "\n         Amount :                               "+amount);
                    display.append("\n\n---------------------------------------------------------");
                    int TotalAmount=(98+42+112+48+amount);
                    display.append("\n\n         Total Amount :                     "+TotalAmount);
                    add(save);
                    payamount p=new payamount(TotalAmount,meterNo);
                    p.save1();
                    save.addActionListener(ae -> {
                        insertDataINUnits i=new insertDataINUnits(meterNo,TotalAmount,m,consumed_units);
                        try {
                            i.save();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "Successfully Updated.");
                    });
                }else
                    JOptionPane.showMessageDialog(null,"Invalid Meter Id");
            }else
                setVisible(false);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new calculateBill();
    }
}
