package elect_bill;


import java.sql.ResultSet;
import java.sql.SQLException;

public class payamount {
    int total,meter;
    String month;
    public payamount(int totalAmount, int meterNo) {
        total = totalAmount;
        meter=meterNo;
    }
    public payamount(String month,int amount,int meterNo){
        total=amount;
        this.month=month;
        meter=meterNo;
    }

    public void save1() throws SQLException {
        int amount;
        connectToMysql c=new connectToMysql();
        String q="SELECT * FROM elect_bill.pay where meterid='"+meter+"';";
        ResultSet rs= c.st.executeQuery(q);
        if(rs.next()){
            amount=Integer.parseInt(rs.getString("totalamount"));
            total=total+amount;
            String sq="UPDATE elect_bill.pay SET totalamount ="+total+" WHERE meterid="+meter+"";
            c.st.executeUpdate(sq);
        }else{
            String sq="insert into elect_bill.pay (meterid,totalamount)values("+meter+","+total+")";
            c.st.executeUpdate(sq);
        }
    }

    public void save2() throws SQLException {
        int alreadypay;
        connectToMysql c=new connectToMysql();
        String q="SELECT * FROM elect_bill.pay where meterid='"+meter+"'";
        ResultSet rs= c.st.executeQuery(q);
        if(rs.next()){
            String t=(rs.getString("alreadypay"));
            String sq="UPDATE elect_bill.pay SET month='"+month+"' WHERE meterid="+meter+"";
            c.st.executeUpdate(sq);
            sq="update elect_bill.pay set amount="+total+" where meterid="+meter+"";
            c.st.executeUpdate(sq);
            if(t==null)
                alreadypay=0;
            else
                alreadypay=Integer.parseInt(t);
            alreadypay=total+alreadypay;
            sq="update elect_bill.pay set alreadypay="+alreadypay+" where meterid="+meter+"";
            c.st.executeUpdate(sq);
            java.util.Date date=new java.util.Date();
            java.sql.Date sqlDate=new java.sql.Date(date.getTime());
            sq="update elect_bill.pay set dateofpay ='"+sqlDate+"' where meterid="+meter+"";
            c.st.executeUpdate(sq);
        }
    }
}