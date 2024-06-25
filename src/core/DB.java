package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static DB instance = null;       // singleton tasarım deseni
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismAgency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "gonca1";

    private DB() {
        try {
            this.connection = DriverManager.getConnection(
                    DB_URL,
                    DB_USERNAME,
                    DB_PASSWORD
            );

        }catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }


    // create new Instance !
    public static Connection getInstance() {
        try{
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DB();   // yeni bir db
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return instance.getConnection();
    }


    public Connection getConnection() {
        return connection;
    }



}
