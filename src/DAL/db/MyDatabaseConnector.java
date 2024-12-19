package DAL.db;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class MyDatabaseConnector {

    private static final String PROP_FILE = "config/config.settings";
    private SQLServerDataSource dataSource;

    public MyDatabaseConnector() throws IOException {
        // Create a Properties object to store database connection properties
        Properties databaseProperties = new Properties();
        // Load the properties from the specified configuration file
        databaseProperties.load(new FileInputStream(new File(PROP_FILE)));

        // Initialize the SQLServerDataSource object
        dataSource = new SQLServerDataSource();

        // Set the database connection properties using values from the configuration file
        dataSource.setServerName("10.176.111.34");
        dataSource.setDatabaseName("wilras01_MyTunes_light");
        dataSource.setUser("CSe2023a_e_27");
        dataSource.setPassword("CSe2024aE27#24");
        dataSource.setPortNumber(1433);
        dataSource.setTrustServerCertificate(true);

    }


    // Method to obtain a connection to the database
    public Connection getConnection() throws SQLServerException {
        // Return a connection from the initialized SQLServerDataSource
        return dataSource.getConnection();

    }

    public static void main(String[] args) throws SQLException, IOException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try (Connection connection = databaseConnector.getConnection()) {

            System.out.println("Is it open? " + !connection.isClosed());
        }

        //Connection gets closed here
    }


}


