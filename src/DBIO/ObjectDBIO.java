package DBIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ObjectDBIO {
    private final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String MYSQL_URL = "jdbc:mysql://localhost:3306/SELLPICK";
    private final String MYSQL_ID = "root"; // TODO : env 파일로 변경
    private final String MYSQL_PW = "0"; // TODO : env 파일로 변경

    // METHOD
    protected Connection open() {
        try {
            Class.forName(MYSQL_DRIVER);
            return DriverManager.getConnection(MYSQL_URL, MYSQL_ID, MYSQL_PW);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(Connection conn) {
        if (conn != null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}