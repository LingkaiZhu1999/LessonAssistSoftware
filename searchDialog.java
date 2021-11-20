package LessonDesign;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.sql.*;

public class searchDialog extends JDialog {
    private JLabel idLabel, nameLabel;
    private JLabel labelOutput;
    private JTextField idTextField, nameTextField;
    private JButton IDButton, nameButton, cancelButton;
    private JPanel panel1,panel2,panel3, panel4;
    private Container container;
    private int id1;
    private String name;
    private static int DEFAULT_WIDTH = 1000;
    private static int DEFAULT_HEIGHT = 800;
    private Connection con;
    private PreparedStatement stat;
    private ResultSet rs;
    private static String searchID
            = "SELECT ID, NAME, GENDER, MAJOR, SCORE FROM TABLE1 WHERE ID = ?";
    private static String searchName
            = "SELECT ID, NAME, GENDER, MAJOR, SCORE FROM TABLE1 WHERE NAME = ?";
    public searchDialog(TheGUI theGUI)
    {
        super(theGUI, "Search", true);
        container = new Container();
        setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        idLabel = new JLabel("Please input the ID:");
        nameLabel = new JLabel("Or input the name:");
        labelOutput = new JLabel("Present the information");
        idTextField = new JTextField(10);
        nameTextField = new JTextField(10);
        container.setLayout(new GridLayout(4,1));
        panel1.add(idLabel);
        panel1.add(idTextField);
        panel2.add(nameLabel);
        panel2.add(nameTextField);
        panel3.add(labelOutput);
        IDButton = new JButton("Search ID");
        nameButton = new JButton("Search name");
        cancelButton = new JButton("Cancel");
        panel4.add(IDButton);
        panel4.add(nameButton);
        panel4.add(cancelButton);
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
        add(container);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        ActionListener IDListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    getConnection();
                    stat = con.prepareStatement(searchID);
                    id1 = Integer.parseInt(idTextField.getText());
                    stat.setInt(1,id1);
                    rs = stat.executeQuery();
                    boolean result = false;
                    while(rs.next())
                    {

                        int id = rs.getInt("ID");
                        String name = rs.getString("NAME");
                        String gender = rs.getString("GENDER");
                        String major = rs.getString("MAJOR");
                        labelOutput.setText(id+" "+name+" "+gender+" "+major);
                        result = true;
                        break;
                    }
                    if(!result)
                    {
                        JOptionPane.showMessageDialog(null, "Cannot find the ID, please check the ID");
                    }
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.print(e.getErrorCode());
                    System.out.println("数据库连接错误");
                    System.exit(0);
                }

            }

        };
        IDButton.addActionListener(IDListener);
        ActionListener nameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    getConnection();
                    stat = con.prepareStatement(searchName);
                    name = nameTextField.getText();
                    stat.setString(1, name);
                    rs = stat.executeQuery();
                    boolean result = false;
                    while(rs.next()) {
                        String targetID = rs.getString("ID");
                        String targetName = rs.getString("NAME");
                        String targetGender = rs.getString("GENDER");
                        String targetMajor = rs.getString("MAJOR");
                        String targetScore = rs.getString("SCORE");
                        labelOutput.setText(targetID + " " + targetName + " " + targetGender + " " + targetMajor + " " + targetScore);
                        result = true;
                    }
                    if(!result)
                    {
                        JOptionPane.showMessageDialog(null, "Cannot find the name, Please check the name.");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        };
        nameButton.addActionListener(nameListener);
        ActionListener cancelAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        cancelButton.addActionListener(cancelAction);

    }
    public void getConnection() throws SQLException {
        String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String connectDB = "jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
        String user = "sa";
        String password = "zlk";
        con = DriverManager.getConnection(connectDB, user, password);
        System.out.println("连接数据库成功");
    }

}
