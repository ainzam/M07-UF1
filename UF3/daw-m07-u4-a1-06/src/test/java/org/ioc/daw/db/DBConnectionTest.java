package org.ioc.daw.db;

import org.ioc.daw.db.DBConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionTest {
    DBConnection dBConnection;
    Connection connection;
    private String connectionProperties = "db.properties";

    @Before
    public void setUp(){
        dBConnection = new DBConnection(connectionProperties);
    }

    @After
    public void cleanUp() throws SQLException {
        if(connection != null){
            connection.close();   
        }        
    }

    @Test
    public void dbConnection() throws IOException, SQLException {   
        connection = dBConnection.getConnection();
        Assert.assertEquals("H2 JDBC Driver", connection.getMetaData().getDriverName());
        Assert.assertEquals("SOCIOC_DB", connection.getCatalog());
    }
    
    @Test
    public void dbConnectionWrongDriver() throws IOException, SQLException {  
        dBConnection = new DBConnection("db_wrong_driver.properties");
        connection = dBConnection.getConnection();
        Assert.assertNull(connection);
    }
}
