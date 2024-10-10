package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/schoolmanagmentsystem";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "1542";

    public Connection connectToDatabase () throws SQLException {
        return DriverManager.getConnection(DATABASE_URL , DATABASE_USERNAME , DATABASE_PASSWORD);
    }
    public Statement getStatement () throws SQLException {
        return this.connectToDatabase().createStatement();
    }
}
