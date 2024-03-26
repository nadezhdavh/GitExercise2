import db.JDBCConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            JDBCConnectionManager connectionManager = JDBCConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            // Example insert query
            String insertQuery = "INSERT INTO academy(name) VALUES(?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString( 1,"test 2");
            int rowsInserted = insertStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

            // Example select query
            String selectQuery = "SELECT * FROM academy";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                // Process each row
                String column1Value = resultSet.getString("name");
                System.out.println("Name: " + column1Value);
            }

            connectionManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
