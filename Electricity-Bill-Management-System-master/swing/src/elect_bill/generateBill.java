package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class generateBill extends JFrame implements ActionListener {
    JLabel meterId,month;
    Choice months;
    JTextField enterMeterId;
    JPanel p,p1;
    JButton generate,save;
    JTextArea display;
    generateBill(){
        super("Generate Bill");
        meterId=new JLabel("Meter Id");
        month=new JLabel("Month");
        enterMeterId=new JTextField(8);
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

        generate=new JButton("Generate Bill");
        save=new JButton("Save");
        generate.setBackground(Color.BLACK);
        generate.setForeground(Color.WHITE);
        save.setBackground(Color.black);
        save.setForeground(Color.WHITE);

        setLayout(null);
        p=new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(meterId);
        p.add(enterMeterId);
        p.setBounds(30,15,150,22);
        add(p);

        p1=new JPanel();
        p1.setLayout(new GridLayout(1,2));
        p1.add(month);
        p1.add(months);
        p1.setBounds(210,15,150,25);
        add(p1);

        display=new JTextArea(20,15);
        display.setBounds(10,60,552,300);
        add(display);

        generate.setBounds(400,2,150,25);
        add(generate);
        save.setBounds(400,30,150,25);
        add(save);

        generate.addActionListener(this);

        setVisible(true);
        setResizable(false);
        setLocation(50,80);
        setSize(590,410);

    }
    public void actionPerformed(ActionEvent e) {
        String name,address,district,pincode,phoneNo,emailId,units,m;
        int meterNo;
        try {
            if(e.getSource()==generate) {
                connectToMysql c = new connectToMysql();
                meterNo = Integer.parseInt(enterMeterId.getText());
                String q = "SELECT * FROM elect_bill.unitsconsumed2020 where meterid='" + meterNo + "'";
                ResultSet rs = c.st.executeQuery(q);
                int amount=0,year=1900;
                if (rs.next()) {
                    units=rs.getString(months.getSelectedItem());
                    if(units==null)
                        JOptionPane.showMessageDialog(null, "Reading is not take");
                    else {
                        String s="SELECT * FROM elect_bill.unit2020 where meterid='" + meterNo + "'";
                        rs=c.st.executeQuery(s);
                        if(rs.next()) {
                            m=months.getSelectedItem();
                            Date d=new Date();
                            year=d.getYear()+year;
                            amount = Integer.parseInt(rs.getString(months.getSelectedItem()));
                            display.setText("      +--------------------------------------------------------------------------------------------------------------------------+      " +
                                    "\n      |\t\t          XYZ Power Limited\t\t                         |\n      |\t                               Electricity Bill for "+m+","+year
                            +"\t                                                      |\n      +--------------------------------------------------------------------------------------------------------------------------+      ");
                        }
                        int det=Integer.parseInt(enterMeterId.getText());
                        s="SELECT * FROM elect_bill.customer where meterid='"+det+"'";
                        rs= c.st.executeQuery(s);
                        if(rs.next()) {
                            name = rs.getString("first") + " " + rs.getString("last");
                            address = rs.getString("address");
                            district = rs.getString("district");
                            pincode = rs.getString("pincode");
                            phoneNo = rs.getString("phoneNo");
                            emailId = rs.getString("emailId");
                            display.append("\n      |\t\t\t\t\t                         |");
                            display.append("\n      |  Customer Name : " + name + "\t\t ||   Meter Rent :                     98 |");
                            display.append("\n      |  Meter No : " + det + "\t\t\t ||   MCB Rent :                      42 |");
                            display.append("\n      |  Address : " + address + "\t\t ||   Service Tax :                 112 |");
                            display.append("\n      |  District : " + district + "\t\t\t ||   GST@9% :                      48 |");
                            display.append("\n      |  Pincode : " + pincode + "\t\t\t ||   Current Month :     " + months.getSelectedItem() + " |");
                            display.append("\n      |  Phone No : " + phoneNo + "\t\t\t ||   Units Consumed :       " + units + " |");
                            display.append("\n      |  EmailId : " + emailId + "\t\t ||   Total Payable :             " + amount + " |");
                            display.append("\n      |\t\t\t\t\t                         |");
                            display.append("\n      +--------------------------------------------------------------------------------------------------------------------------+      ");
                            display.append("\n      |         FOR " + months.getSelectedItem().toUpperCase() + "," + year + " THE CONSUMED UNITS IS " + units + " AND TOTAL PAYABLE IS " + amount + "      |");
                            display.append("\n      +--------------------------------------------------------------------------------------------------------------------------+      ");
                            pdf p = new pdf(name, meterNo, address, district, pincode, phoneNo, emailId,months.getSelectedItem(), units,amount);
                            p.set();
                        }
                        save.addActionListener(ae -> JOptionPane.showMessageDialog(null, "Successfully Updated."));


                    }

                } else
                    JOptionPane.showMessageDialog(null, "Invalid Meter Id");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new generateBill();
    }


}
