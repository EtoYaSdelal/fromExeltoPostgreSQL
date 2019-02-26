package bizlogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Employees";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "admin";

    public Connection getConnection(String msg) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Connection " + msg + " successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error onnection");
        }
        return connection;
    }

    public Connection getConnection(){
        return getConnection("root");
    }

}
