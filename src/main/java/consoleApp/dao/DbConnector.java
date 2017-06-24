package consoleApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by PavelGrudina on 11.02.2017.
 */
public class DbConnector {

    private Class<?> driverClass;

    private String url;
    private String username;
    private String password;


    public Connection con;
    public Statement statement;


    public DbConnector() {
        init();
    }

    private void init() {
        try {

            driverClass = Class.forName("org.h2.Driver");

            url = "jdbc:h2:tcp://localhost/~/IdeaProjects/MyProject/testtask/DB";
            username = "admin";
            password = "admin";


            con = DriverManager.getConnection(url, username, password);
            statement = con.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Database connection not found.");
        } catch (SQLException e) {
            System.out.println("Please start database and try again!");
            Thread.currentThread().stop();
        }
    }

}
