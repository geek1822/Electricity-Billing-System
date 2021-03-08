package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customerInfo extends JFrame implements ActionListener {
    JLabel meterNo;
    JTextField enterMeterNo;
    JPanel p1,p3;
    JButton cancel,show;
    customerInfo() {
        super("Customer Info");
        setLayout(null);
        setLocation(550, 150);
        setSize(350, 485);
        setVisible(true);
        setResizable(false);

        p1 = new JPanel();
        meterNo = new JLabel("Enter Meter No");
        enterMeterNo = new JTextField(8);

        show = new JButton("Show");
        cancel = new JButton("Cancel");
        show.addActionListener(this);
        cancel.addActionListener(this);

        p1.add(meterNo);
        p1.add(enterMeterNo);
        p1.setBounds(70,20,200,35);
        add(p1);

        show.setBounds(30,400,100,30);
        cancel.setBounds(195,400,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        show.setBackground(Color.black);
        show.setForeground(Color.WHITE);
        add(show);
        add(cancel);

        p3=new JPanel();
        p3.setBounds(10,60,315,330);
        p3.setOpaque(true);
        p3.setBackground(Color.gray);
        add(p3);
    }
    public void actionPerformed(ActionEvent e){
        try {
            if(e.getSource()==show){
                connectToMysql c=new connectToMysql();
                int det=Integer.parseInt( enterMeterNo.getText());
                String q="SELECT * FROM elect_bill.customer where meterid='"+det+"'";
                ResultSet rs= c.st.executeQuery(q);
                if(rs.next()){
                    String name=rs.getString("first");
                    String last=rs.getString("last");
                    String address=rs.getString("address");
                    String phone=rs.getString("phoneNo");
                    String pincode=rs.getString("pincode");
                    String emailId = rs.getString("emailId");
                    String nearestPoliceStation = rs.getString("nearestpolicestation");
                    String district = rs.getString("district");
                    String gender = rs.getString("gender");
                    String age =rs.getString("age");

                    JLabel j_meter=new JLabel("Meter Number :           "+det);
                    JLabel j_first=new JLabel("Customer Name :    "+name+" "+last);
                    JLabel j_address =new JLabel("Customer Address :    "+address);
                    JLabel j_phone=new JLabel("Customer Phone No :      "+phone);
                    JLabel j_pincode=new JLabel("Customer Pincode :     "+pincode);
                    JLabel j_emailId = new JLabel("Email Id :           "+emailId);
                    JLabel j_nearestPoliceStation = new JLabel("Nearest police station :    "+nearestPoliceStation);
                    JLabel j_district = new JLabel("District :           "+district);
                    JLabel j_gender = new JLabel("Gender :                "+gender);
                    JLabel j_age = new JLabel("Age :                      "+age);


                    p3=new JPanel();
                    p3.setBounds(10,60,315,330);
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

                }else
                    JOptionPane.showMessageDialog(null,"Invalid Meter Id");
            }else
                this.setVisible(false);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new customerInfo();
    }
}
