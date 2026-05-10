import javax.swing.*;
import java.sql.*;

public class User extends MainPanel{
    public static int user_id;
    public static String login;
   public  static String email;
    public static String password;
    public static int getUser_id() throws SQLException {
        Connection conn = Database.getCon();
        Statement stmt = conn.createStatement();
        String sql2 = "SELECT * from Users WHERE login = ? AND email = ?";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
        preparedStatement2.setString(1,login);
        preparedStatement2.setString(2,email);
        ResultSet x = preparedStatement2.executeQuery();
        while (x.next()) {
            user_id=x.getInt("user_id");
        }
        stmt.close();
        conn.close();
        return user_id;
    }

}
