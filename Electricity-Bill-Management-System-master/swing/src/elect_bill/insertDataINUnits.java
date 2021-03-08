package elect_bill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class insertDataINUnits {
    int meter;
    int amount;
    String month;
    int consumed_units;
    insertDataINUnits(int meter,int amount,String month,int consumed_units){
        this.amount=amount;
        this.meter=meter;
        this.month=month;
        this.consumed_units=consumed_units;
    }
    public void save() throws SQLException {
        connectToMysql c= new connectToMysql();

        //saving the amount to the unit2020
        String s="SELECT * FROM elect_bill.unit2020 where meterid="+meter+"";
        ResultSet rs= c.st.executeQuery(s);
        if(rs.next()){
            String sq="UPDATE elect_bill.unit2020 SET "+month+"="+amount+" WHERE meterid="+meter+"";
            c.st.executeUpdate(sq);
        }else {
            String sq="insert into elect_bill.unit2020(meterid,"+month+")values("+meter+","+amount+")";
            c.st.executeUpdate(sq);
        }

        //saving the units consumed  to the unitsconsumed2020
        String q="SELECT * FROM elect_bill.unitsconsumed2020 where meterid="+meter+"";
        rs=c.st.executeQuery(q);
        if(rs.next()){
            String t="UPDATE elect_bill.unitsconsumed2020 SET "+month+"="+consumed_units+" WHERE meterid="+meter+"";
            c.st.executeUpdate(t);
        }else{
            String t="insert into elect_bill.unitsconsumed2020(meterid,"+month+")values("+meter+","+consumed_units+")";
            c.st.executeUpdate(t);
        }


    }
}
