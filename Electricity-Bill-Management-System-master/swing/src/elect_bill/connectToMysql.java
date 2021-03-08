package elect_bill;

import java.sql.*;

public class connectToMysql {
    Connection con;
    Statement st;
    String ulr = "jdbc:mysql://localhost:3306/elect_bill", user = "root", pass = "admin";
    connectToMysql(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(ulr, user, pass);
            st = con.createStatement();
        }catch (SQLException | ClassNotFoundException ee){
            System.out.println(ee.getMessage());
        }
    }
    public static void main(String[] args) {
        new connectToMysql();
    }
}
