import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;


public class MainPanel extends Database {
    private JButton logOutButton;
    private JButton noteButton;
    private JButton showAllButton;
    private JPanel MainPanel;
    private JButton importFromFileButton;

    public MainPanel()
    {
        setTitle("Main panel");
        setContentPane(MainPanel);
        int width = 800, height = 600;
        setMinimumSize(new Dimension(width,height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        noteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note note = null;

                try {
                    note = new Note("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                note.setVisible(true);
                note.setSize(600,800);

            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = Database.getCon();
                    Statement stmt = conn.createStatement();
                    int user_id = User.getUser_id();
                    String sql = "SELECT * from Notes WHERE user_id = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1,user_id);
                    ResultSet y = preparedStatement.executeQuery();
                    while (y.next()) {
                        Note note = new Note(y.getString("content"));
                        note.setVisible(true);
                        note.setSize(600,800);
                    }
                    stmt.close();
                    conn.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        importFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "file.csv";

                try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                    List<String[]> records = reader.readAll();

                    for (String[] record : records) {
                        Note note = new Note(record[0]);
                        note.setVisible(true);
                        note.setSize(600,800);
                    }
                } catch (IOException | CsvException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
