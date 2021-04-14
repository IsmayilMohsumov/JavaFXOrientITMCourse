package az.com.course.dbConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    public static Connection open() throws SQLException {
      //  return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ismayil","12345");
        Connection conn
                = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "ismayil", "12345");
        if (conn != null) {
            System.out.println("Connected to the database!");
        } else {
            System.err.println("Failed to make connection!");
        }
        return conn;
    }
}
/*public static PreparedStatement connect(String sql) throws Exception {

        PreparedStatement pst = conn.prepareStatement(sql);
        return pst;
    }*/