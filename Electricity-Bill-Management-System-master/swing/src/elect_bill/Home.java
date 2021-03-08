package elect_bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Home extends JFrame implements ActionListener {
    Home() {

        super("Home");
        setLocation(250,100);
        setSize(850, 600);
        setVisible(true);


        JMenuBar menuBar=new JMenuBar();
        JMenu customer =new JMenu("Customer");
        JMenuItem customer_info=new JMenuItem("Customer Info");
        JMenuItem new_customer =new JMenuItem("New Customer");
        JMenuItem deposit_info=new JMenuItem("Deposit Info");

        customer_info.setFont(new Font("monospaced",Font.PLAIN,12));
        customer_info.setMnemonic('C');
        customer_info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        customer_info.setBackground(Color.white);

        new_customer.setFont(new Font("monospaced",Font.PLAIN,12));
        new_customer.setMnemonic('N');
        new_customer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        new_customer.setBackground(Color.white);

        deposit_info.setFont(new Font("monospaced",Font.PLAIN,12));
        deposit_info.setMnemonic('D');
        deposit_info.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        deposit_info.setBackground(Color.white);

        customer_info.addActionListener(this);
        new_customer.addActionListener(this);
        deposit_info.addActionListener(this);


        JMenu bill =new JMenu("Bill");
        JMenuItem pay_bill=new JMenuItem("Pay Bill");
        JMenuItem calculate_bill=new JMenuItem("Calculate Bill");
        JMenuItem generate_bill=new JMenuItem("Generate Bill");

        pay_bill.setFont(new Font("monospaced",Font.PLAIN,12));
        pay_bill.setMnemonic('P');
        pay_bill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        pay_bill.setBackground(Color.white);

        calculate_bill.setFont(new Font("monospaced",Font.PLAIN,12));
        calculate_bill.setMnemonic('B');
        calculate_bill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        calculate_bill.setBackground(Color.white);

        generate_bill.setFont(new Font("monospaced",Font.PLAIN,12));
        generate_bill.setMnemonic('G');
        generate_bill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generate_bill.setBackground(Color.white);

        pay_bill.addActionListener(this);
        calculate_bill.addActionListener(this);
        generate_bill.addActionListener(this);


        JMenu report =new JMenu("Report");
        JMenuItem request=new JMenuItem("Request");
        JMenuItem complain=new JMenuItem("Complain");

        request.setFont(new Font("monospaced",Font.PLAIN,12));
        request.setMnemonic('R');
        request.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        request.setBackground(Color.white);

        complain.setFont(new Font("monospaced",Font.PLAIN,12));
        complain.setBackground(Color.white);


        request.addActionListener(this);
        complain.addActionListener(this);


        JMenu tool =new JMenu("Tool");
        JMenuItem google=new JMenuItem("Google");
        JMenuItem notepad=new JMenuItem("Notepad");
        JMenuItem calculate=new JMenuItem("Calculate");

        google.setFont(new Font("monospaced",Font.PLAIN,12));
        google.setMnemonic('W');
        google.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        google.setBackground(Color.white);

        notepad.setFont(new Font("monospaced",Font.PLAIN,12));
        notepad.setMnemonic('K');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
        notepad.setBackground(Color.white);

        calculate.setFont(new Font("monospaced",Font.PLAIN,12));
        calculate.setBackground(Color.white);

        google.addActionListener(this);
        notepad.addActionListener(this);
        calculate.addActionListener(this);


        JMenu help =new JMenu("Help");
        JMenuItem about=new JMenuItem("About");
        JMenuItem logout=new JMenuItem("Logout");

        about.setFont(new Font("monospaced",Font.PLAIN,12));
        about.setMnemonic('A');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        about.setBackground(Color.white);

        logout.setFont(new Font("monospaced",Font.PLAIN,12));
        logout.setMnemonic('L');
        logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        logout.setBackground(Color.white);

        about.addActionListener(this);
        logout.addActionListener(this);


        customer.add(customer_info);
        customer.add(new_customer);
        customer.add(deposit_info);

        bill.add(pay_bill);
        bill.add(calculate_bill);
        bill.add(generate_bill);

        report.add(request);
        report.add(complain);

        tool.add(google);
        tool.add(notepad);
        tool.add(calculate);

        help.add(about);
        help.add(logout);

        menuBar.add(customer);
        menuBar.add(bill);
        menuBar.add(report);
        menuBar.add(tool);
        menuBar.add(help);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e) {
        String click=e.getActionCommand();
        switch (click) {
            case "Customer Info":
                new customerInfo();
                break;
            case "New Customer":
                new newCustomer();
                break;
            case "Pay Bill":
                new payBill();
                break;
            case "Calculate Bill":
                new calculateBill();
                break;
            case "Deposit Info":
                new depositInfo();
                break;
            case "Generate Bill":
                new generateBill();
                break;
            case "Request":
                new Customer_request();
                break;
            case "Complain":
                new complain();
                break;
            case "Google":
                try {
                    Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                } catch (Exception ignored) { }
                break;
            case "Notepad":
                try {
                    Runtime.getRuntime().exec("notepad.exe");
                } catch (Exception ignored) { }
                break;
            case "Calculate":
                try {
                    Runtime.getRuntime().exec("calc.exe");
                } catch (Exception ignored) { }
                break;
            case "About":
                new about();
                break;
            case "Logout":
                setVisible(false);
                new login();
                break;
        }


    }
    public static void main(String[] args) {
        new Home();
    }
}
