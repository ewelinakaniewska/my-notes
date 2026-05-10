import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database extends JDialog {
    protected static Connection getCon()
    {
        final String DB_URL = "jdbc:postgresql://horton.db.elephantsql.com:5432/enuldsvo";
        final String USERNAME = "enuldsvo";
        final String PASSWORD = "P8Ni8mGMIJDPI3Xh7f67u-XKLV7bvN30";
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
