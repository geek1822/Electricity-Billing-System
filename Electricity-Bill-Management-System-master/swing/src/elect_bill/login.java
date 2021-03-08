package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {
    JLabel user,pass;
    JButton cancel,login;
    JTextField enterUser;
    JPasswordField  enterPass;
    JPanel p1,p2;
    login(){
        super("login");
        setLayout(new BorderLayout() );
        user =new JLabel("Username");
        pass=new JLabel("Password");
        enterUser=new JTextField(15);
        enterPass=new JPasswordField(15);
        login =new JButton("login");
        cancel=new JButton("cancel");
        p1=new JPanel();
        p1.add(user);
        p1.add(enterUser);
        p1.add(pass);
        p1.add(enterPass);
        add(p1, BorderLayout.CENTER);
        p2=new JPanel();
        p2.add(login);
        p2.add(cancel);
        add(p2,BorderLayout.SOUTH);
        login.addActionListener(this);
        cancel.addActionListener(this);
        setVisible(true);
        setSize(300,150);
        setResizable(false);
        setLocation(530,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        try {
            if(e.getSource()==login) {
                connectToMysql c=new connectToMysql();
                String use = enterUser.getText();
                String pas = new String(enterPass.getPassword());
                String q="SELECT username,password from elect_bill.login where username ='"+use+"' && password='"+pas+"'";
                ResultSet rs= c.st.executeQuery(q);
                if(rs.next()){
                    new Home();
                    this.setVisible(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }else
                    JOptionPane.showMessageDialog(null,"Invalid login");
            }
            else
               setVisible(false);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new login();
    }
}
