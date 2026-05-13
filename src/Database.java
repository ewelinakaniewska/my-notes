import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database extends JDialog {
    protected static Connection getCon()
    {
        final String DB_URL = "jdbc:postgresql://viaduct.proxy.rlwy.net:41565/railway";
        final String USERNAME = "postgres";
        final String PASSWORD = "NVLoWvrRLbbXbLjhzUMsSDMexaddwjji";
        try {
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
