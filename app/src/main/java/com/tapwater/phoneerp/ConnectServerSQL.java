package com.tapwater.phoneerp;

import android.database.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

///**
// * Created by Tapwater on 17-4-22.
// */
//
//public class ConnectServerSQL {
//}



public class ConnectServerSQL extends Thread {

    String UserName ;//用户名
    String Password ;//密码
    Connection con ;

    public ConnectServerSQL()
    {
        UserName = "root";//用户名
        Password = "89e9b4b10b";//密码
        Connection con = null;
    }

    @Override
    public void run() {
        try { // 加载驱动程序
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:jtds:sqlserver://112.74.45.176:3306/android_test", UserName, Password);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序出错");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        try {
            testConnection(con);//测试数据库连接
        } catch (java.sql.SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        String UserName = "test";//用户名
//        String Password = "test";//密码
//        Connection con = null;
//
//        try { // 加载驱动程序
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            con = DriverManager.getConnection(
//                    "jdbc:jtds:sqlserver://192.168.1.2:1433/testDB", UserName,
//                    Password);
//        } catch (ClassNotFoundException e) {
//            System.out.println("加载驱动程序出错");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }
//        try {
//            testConnection(con);//测试数据库连接
//        } catch (java.sql.SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public void testConnection(Connection con) throws java.sql.SQLException {

        try {

            String sql = "SELECT * FROM connect";//查询表名为“table_test”的所有内容
            Statement stmt = con.createStatement();//创建Statement
            ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Cursor

            while (rs.next()) {//<code>ResultSet</code>最初指向第一行
                System.out.println(rs.getString("di"));//输出第n行，列名为“test_id”的值
                System.out.println(rs.getString("phone"));

            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage().toString());
        } finally {
            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                }
        }
    }
}
