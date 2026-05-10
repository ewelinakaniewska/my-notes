import javax.swing.*;
import java.sql.*;

public class nNote  extends MainPanel{
    public int notes_id;
    public String content;
    public int user_id;

    public int getNote_id() throws SQLException {
        User.getUser_id();
        Connection conn = Database.getCon();
        Statement stmt = conn.createStatement();
        String sql2 = "SELECT * from Notes WHERE content = ? AND user_id = ?";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
        preparedStatement2.setString(1,content);
        preparedStatement2.setInt(2,user_id);
        ResultSet x = preparedStatement2.executeQuery();
        while (x.next()) {
            notes_id =x.getInt("notes_id");
        }
        stmt.close();
        conn.close();
        return notes_id;
    }
}
