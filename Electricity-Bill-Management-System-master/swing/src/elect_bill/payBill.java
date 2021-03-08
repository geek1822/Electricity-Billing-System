package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class payBill extends JFrame implements ActionListener {
    JLabel meterNo,month;
    JTextField enterMeterNo;
    JPanel p,p1,p2,p3;
    JButton cancel,display,pay;
    Choice months;
    payBill(){
        super("Pay Bill");
        setLayout(null);
        setLocation(550, 150);
        setSize(350, 540);
        setVisible(true);
        setResizable(false);

        meterNo = new JLabel("Enter Meter No");
        enterMeterNo = new JTextField(8);

        month=new JLabel("Month");
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

        display= new JButton("Display");
        cancel = new JButton("Cancel");
        display.addActionListener(this);
        cancel.addActionListener(this);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.WHITE);


        p1=new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p1.add(meterNo);
        p1.add(enterMeterNo);
        p1.setBounds(70,15,200,22);
        add(p1);

        p=new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(month);
        p.add(months);
        p.setBounds(70,40,200,22);
        add(p);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,2));
        p2.add(display);
        p2.add(cancel);
        p2.setBounds(20,70,300,22);
        add(p2);

        pay=new JButton("pay");
        pay.setBounds(20,455,100,22);
        pay.setBackground(Color.black);
        pay.setForeground(Color.WHITE);

        p3=new JPanel();
        p3.setBounds(10,105,315,330);
        p3.setOpaque(true);
        p3.setBackground(Color.gray);
        add(p3);
    }
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource()==display) {
                connectToMysql c = new connectToMysql();
                int det = Integer.parseInt(enterMeterNo.getText());
                String q = "SELECT * FROM elect_bill.customer where meterid='" + det + "'";
                ResultSet rs = c.st.executeQuery(q);
                if (rs.next()) {
                    String name = rs.getString("first");
                    String last = rs.getString("last");
                    String address = rs.getString("address");
                    String phone = rs.getString("phoneNo");
                    String pincode = rs.getString("pincode");
                    String emailId = rs.getString("emailId");
                    String nearestPoliceStation = rs.getString("nearestpolicestation");
                    String district = rs.getString("district");
                    String gender = rs.getString("gender");
                    String age =rs.getString("age");
                    int amount=0;

                    JLabel j_meter = new JLabel("Meter Number :           " + det);
                    JLabel j_first=new JLabel("Customer Name :    "+name+" "+last);
                    JLabel j_address = new JLabel("Customer Address :     " + address);
                    JLabel j_phone = new JLabel("Customer Phone No :      " + phone);
                    JLabel j_pincode = new JLabel("Customer Pincode :     " + pincode);
                    JLabel j_emailId = new JLabel("Email Id :           "+emailId);
                    JLabel j_nearestPoliceStation = new JLabel("Nearest police station :    "+nearestPoliceStation);
                    JLabel j_district = new JLabel("District :           "+district);
                    JLabel j_gender = new JLabel("Gender :                "+gender);
                    JLabel j_age = new JLabel("Age :                      "+age);

                    p3=new JPanel();
                    p3.setBounds(10,105,315,330);
                    p3.setOpaque(true);
                    p3.setBackground(Color.gray);
                    add(p3);
                    p3.setLayout(new GridLayout(10,2));
                    p3.add(j_meter);
                    p3.add(j_first);
                    p3.add(j_address);
                    p3.add(j_phone);
                    p3.add(j_pincode);
                    p3.add(j_emailId);
                    p3.add(j_nearestPoliceStation);
                    p3.add(j_district);
                    p3.add(j_gender);
                    p3.add(j_age);
                    p3.revalidate();
                    p3.repaint();


                    String t="SELECT "+months.getSelectedItem()+" FROM elect_bill.unit2020 where meterid='" + det + "'";
                    rs=c.st.executeQuery(t);
                    if(rs.next()){
                        String j=rs.getString(months.getSelectedItem());
                        if(j==null)
                            JOptionPane.showMessageDialog(null, "Bill is not Generate");
                        else
                            amount=Integer.parseInt(j);
                    }
                    JLabel j_amount=new JLabel("Billing amount :     $"+amount);

                    add(pay);
                    pay.repaint();
                    int finalAmount = amount;
                    pay.addActionListener(ae -> {
                        if (finalAmount >0)
                            if(JOptionPane.showConfirmDialog(null, "Check the details ?")==0) {
                                JOptionPane.showMessageDialog(null, "Successfully Updated.");

                                payamount p=new payamount(months.getSelectedItem(),finalAmount,det);
                                try {
                                    p.save2();
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                setVisible(false);
                            }
                    });

                    j_amount.setBounds(190,450,200,30);
                    add(j_amount);
                    j_amount.repaint();


                }else
                    JOptionPane.showMessageDialog(null, "Meter no is invalid");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new payBill();
    }
}
