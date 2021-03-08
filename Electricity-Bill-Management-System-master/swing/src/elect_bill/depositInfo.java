package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;



public class depositInfo extends JFrame implements ActionListener {
    JLabel meterId;
    JTextField enterMeterId;
    JPanel p,p1;
    JButton cancel,display;
    JTextArea show;
    depositInfo(){
        super("Deposit Info");


        meterId=new JLabel("Meter Id");
        enterMeterId=new JTextField(8);
        p=new JPanel();
        p.add(meterId);
        p.add(enterMeterId);
        p.setLayout(new GridLayout(1,2));
        p.setBounds(20,10,200,25);
        add(p);

        cancel=new JButton("Cancel");
        display=new JButton("Display");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        display.setBackground(Color.black);
        display.setForeground(Color.WHITE);
        p1=new JPanel();
        p1.add(display);
        p1.add(cancel);
        cancel.addActionListener(this);
        display.addActionListener(this);
        p1.setLayout(new GridLayout(1,2));
        p1.setBounds(20,40,200,25);
        add(p1);


        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocation(50,80);
        setSize(255,390);

        show=new JTextArea(20,15);
        show.setBounds(10,80,217,260);
        add(show);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == display) {
                connectToMysql c = new connectToMysql();
                int det = Integer.parseInt(enterMeterId.getText());
                String q = "SELECT * FROM elect_bill.customer where meterid='" + det + "'";
                ResultSet rs = c.st.executeQuery(q);
                if (rs.next()) {
                    String c_name=rs.getString("first")+" "+rs.getString("last");
                    show.setText("                      XYZ Power Limited\n===============================");
                    show.append("\n\n Customer Name : "+c_name);
                    show.append("\n Meter Id : "+det);
                    show.append("\n\n------------------------------------------------------");
                    show.append("\n             Previous Payment History");
                    show.append("\n------------------------------------------------------");
                    String s="SELECT * FROM elect_bill.pay where meterid='"+det+"'";
                    rs=c.st.executeQuery(s);
                    if(rs.next()){
                        int amount=Integer.parseInt(rs.getString("totalamount"))-Integer.parseInt(rs.getString("alreadypay"));
                        show.append("\n Date : "+rs.getString("dateofpay"));
                        show.append("\n Month : "+rs.getString("month"));
                        show.append("\n Amount : "+rs.getString("amount"));
                        show.append("\n\n===============================");
                        show.append("\n               DUE AMOUNT IS "+amount);

                    }

                }
            }else
                setVisible(false);
        } catch (Exception ex){
        ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new depositInfo();
    }
}
