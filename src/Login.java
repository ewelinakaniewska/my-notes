import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends Register{
    private JPasswordField passwordField;
    private JTextField loginField;
    private JButton clearButton;
    private JButton registerButton;
    private JButton logInButton;
    private JPanel JPanelLogin;

    public static void main(String[] args) {
        Login loginPanel = new Login(null);
        loginPanel.setVisible(true);
        loginPanel.setSize(500,600);
    }
    public Login(JFrame parent)
    {
        super(parent);
        setTitle("Login");
        setContentPane(JPanelLogin);
        int width = 600, height = 700;
        setMinimumSize(new Dimension(width,height));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register(null);
                register.setVisible(true);
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String paasowrd = String.valueOf(passwordField.getPassword());

              boolean x = getAutenticateUser(login,paasowrd);

                if(x) {
                    dispose();
                    MainPanel main = new MainPanel();
                    main.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Login or password invalied",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginField.setText("");
                passwordField.setText("");
            }
        });
    }
private boolean getAutenticateUser(String login, String paasowrd) {
    boolean x = false;
        try{
        Connection conn = Database.getCon();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM Users WHERE login=? AND password=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,paasowrd);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
        x=true;
        User.user_id = User.getUser_id();
        User.login = resultSet.getString("login");
        User.email = resultSet.getString("email");
        User.password = resultSet.getString("password");
        }

        stmt.close();
        conn.close();

        }
        catch (Exception e){
        e.printStackTrace();
        }

        return x;

        }

}

