import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Register extends MainPanel  {
    private JPasswordField confirmPasswordField;
    private JTextField loginField;
    private JButton clearButton;
    private JButton registerButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPanel JPanelRegister;

    public Register(JFrame parent)
    {
        setTitle("MyNotes-Register");
        setContentPane(JPanelRegister);
        setMinimumSize(new Dimension(500,800));
        setModal(true);
        setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailField.setText("");
                loginField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            }
        });
    }
    public void registerUser()
    {
        String login = loginField.getText();
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String password2 = String.valueOf(confirmPasswordField.getPassword());
        try{
        Connection con = Database.getCon();
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM Users WHERE login=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            JOptionPane.showMessageDialog(this,
                    "This username is unavailable",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        stmt.close();
        con.close();
    }
        catch (Exception e){
        e.printStackTrace();
    }
        if(login.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty())
        {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(password2))
        {
            JOptionPane.showMessageDialog(this,
                    "Confirm Passowrd does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean x = addUserToDatabase(login, email, password);
        if (x) {
            dispose();
            Login login1 = new Login(null);
            login1.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean addUserToDatabase(String login,String email,String password){
        try {
            Connection conn = Database.getCon();
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO Users (login,email,password) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.executeUpdate();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}
