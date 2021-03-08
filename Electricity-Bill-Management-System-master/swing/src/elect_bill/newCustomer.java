package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class newCustomer extends JFrame implements ActionListener {

    JButton save, cancel;
    JLabel CustomerFirstName, CustomerLastName, CustomerPhone, CustomerAddress,
            CustomerPincode, CustomerMeterNo, CustomerEmailId, CustomerDistrict,
            CustomerNearestPoliceStation, CustomerGender, CustomerAge,title;
    JTextField enterCustomerFirstName, enterCustomerLastName, enterCustomerPhone,
            enterCustomerAddress, enterCustomerPincode, enterCustomerMeterNo,
            enterCustomerEmailId, enterCustomerNearestPoliceStation,
            enterCustomerDistrict, enterCustomerAge;
    JPanel p1, p2,p3,p4,p5,p6,p7,p8,p9,p10,p12;
    ButtonGroup bg;
    JRadioButton r1,r2;

    newCustomer() {
        super("New Customer");
        setLayout(null);
        save = new JButton("SAVE");
        cancel = new JButton("CANCEL");


        title=new JLabel("New Customer");
        CustomerFirstName = new JLabel("First Name");
        CustomerLastName = new JLabel("Last Name");
        CustomerPhone = new JLabel("Phone Number");
        CustomerAddress = new JLabel("Address");
        CustomerPincode = new JLabel("Pincode Number");
        CustomerMeterNo = new JLabel("Meter Number");
        CustomerEmailId = new JLabel("Email Id");
        CustomerNearestPoliceStation = new JLabel("Nearest police station");
        CustomerDistrict = new JLabel("District");
        CustomerGender = new JLabel("Gender");
        CustomerAge = new JLabel("Age");

        enterCustomerFirstName = new JTextField(10);
        enterCustomerLastName = new JTextField(10);
        enterCustomerPhone = new JTextField(10);
        enterCustomerAddress = new JTextField(100);
        enterCustomerPincode = new JTextField(6);
        enterCustomerMeterNo = new JTextField(8);
        enterCustomerEmailId = new JTextField(30);
        enterCustomerNearestPoliceStation = new JTextField(10);
        enterCustomerDistrict = new JTextField(15);
        enterCustomerAge = new JTextField(2);


        title.setFont(new Font("",Font.PLAIN,26));
        title.setBounds(170,2,1000,50);
        add(title);

        //CONSTRUCTING JPANEL 1 AND SETTING ITS LAYOUT.
        p1=new JPanel();
        p1.add(CustomerFirstName);
        p1.add(enterCustomerFirstName);
        p1.setLayout(new GridLayout(1,2));
        p1.setBounds(60,60,400,25);
        add(p1);

        p2=new JPanel();
        p2.add(CustomerLastName);
        p2.add(enterCustomerLastName);
        p2.setLayout(new GridLayout(1,2));
        p2.setBounds(60,90,400,25);
        add(p2);

        p3=new JPanel();
        p3.add(CustomerAddress);
        p3.add(enterCustomerAddress);
        p3.setLayout(new GridLayout(1,2));
        p3.setBounds(60,150,400,25);
        add(p3);

        p4=new JPanel();
        p4.add(CustomerMeterNo);
        p4.add(enterCustomerMeterNo);
        p4.setLayout(new GridLayout(1,2));
        p4.setBounds(60,120,400,25);
        add(p4);

        p5=new JPanel();
        p5.add(CustomerDistrict);
        p5.add(enterCustomerDistrict);
        p5.setLayout(new GridLayout(1,2));
        p5.setBounds(60,180,400,25);
        add(p5);

        p6=new JPanel();
        p6.add(CustomerPincode);
        p6.add(enterCustomerPincode);
        p6.setLayout(new GridLayout(1,2));
        p6.setBounds(60,210,400,25);
        add(p6);

        p7=new JPanel();
        p7.add(CustomerNearestPoliceStation);
        p7.add(enterCustomerNearestPoliceStation);
        p7.setLayout(new GridLayout(1,2));
        p7.setBounds(60,240,400,25);
        add(p7);

        p8=new JPanel();
        p8.add(CustomerPhone);
        p8.add(enterCustomerPhone);
        p8.setLayout(new GridLayout(1,2));
        p8.setBounds(60,270,400,25);
        add(p8);

        p9=new JPanel();
        p9.add(CustomerEmailId);
        p9.add(enterCustomerEmailId);
        p9.setLayout(new GridLayout(1,2));
        p9.setBounds(60,300,400,25);
        add(p9);

        p10=new JPanel();
        p10.add(CustomerAge);
        p10.add(enterCustomerAge);
        p10.setLayout(new GridLayout(1,2));
        p10.setBounds(60,330,400,25);
        add(p10);

        p12=new JPanel();
        p12.add(save);
        p12.add(cancel);
        p12.setLayout(new GridLayout(1,2));
        p12.setBounds(60,390,400,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        save.setBackground(Color.black);
        save.setForeground(Color.WHITE);
        add(p12);


        CustomerGender.setBounds(60,360,200,25);
        add(CustomerGender);
        r1=new JRadioButton("Male");
        r2=new JRadioButton("Female");
        bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);
        r1.setBounds(260,360,100,25);
        r2.setBounds(380,360,100,25);
        add(r1);add(r2);




        setVisible(true);
        save.addActionListener(this);
        cancel.addActionListener(this);
        setResizable(false);
        setSize(520, 490);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == save) {
                connectToMysql c=new connectToMysql();
                String customer_first_name = enterCustomerFirstName.getText();
                String customer_last_name = enterCustomerLastName.getText();
                String customer_phone_no = enterCustomerPhone.getText();
                String customer_address = enterCustomerAddress.getText();
                String customer_pincode = enterCustomerPincode.getText();
                String customer_meter_no = enterCustomerMeterNo.getText();
                String customer_EmailId = enterCustomerEmailId.getText();
                String customer_nearestPoliceStation = enterCustomerNearestPoliceStation.getText();
                String customer_district = enterCustomerDistrict.getText();
                String customer_gender;
                if(r1.isSelected())
                    customer_gender ="Male";
                else
                    customer_gender="Female";
                String customer_Age = enterCustomerAge.getText();
                String q="insert into elect_bill.customer(meterid,first,last,address,phoneNo,pincode,emailId,nearestpolicestation," +
                        "district,gender,age) values( "+customer_meter_no+",'"+customer_first_name+"','"+customer_last_name+"','"+customer_address+"','"+customer_phone_no+"','"+customer_pincode+"','"+customer_EmailId+"','"+customer_nearestPoliceStation+"','"+customer_district+"','"+customer_gender+"','"+customer_Age+"')";
                c.st.executeUpdate(q);

                JOptionPane.showMessageDialog(null, "Successfully Updated.");

            }
            setVisible(false);
        } catch (Exception ob) {
            ob.printStackTrace();
        }
    }


    public static void main(String[] args) {
         new newCustomer().setVisible(true);
    }
}


