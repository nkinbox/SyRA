package SyraServerPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database extends MainServer {
    static String MysqlUser;
    static String MysqlPass;
    Connection connect;
    PreparedStatement statement;
    ResultSet result;
    boolean error;
    public database() {
        connect = null;
        statement = null;
        result = null;
        error = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/syra?user="+ MysqlUser +"&password="+ MysqlPass);
        } catch (ClassNotFoundException | SQLException ex) {
            error = true;
            System.out.println(ex.toString());
        }
    }
    void close() {
        try {
          if (result != null) {
            result.close();
          }
          if (statement != null) {
            statement.close();
          }
          if (connect != null) {
            connect.close();
          }
        } catch (java.lang.Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
