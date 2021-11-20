package LessonDesign;
import javafx.scene.layout.BorderRepeat;

import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.nio.file.*;
import java.io.*;

public class insertDialog extends JDialog {
    private Container container;
    private JPanel panel,panel1,panel2,panel3,panel4,panel5;
    private JButton okButton, cancelButton;
    private JLabel idLabel, nameLabel, genderLabel, majorLabel, scoreLabel;
    private JTextField idTextfield, nameTextfield, majorTextfield, scoreTextfield;
    private JRadioButton maleRadiobutton, femaleRadiobutton;
    private String id;
    private String name;
    private String gender;
    private String major;
    private int score;
    private static int DEFAULT_WIDTH = 1000;
    private static int DEFAULT_HEIGHT = 800;
    private Connection con;
    private PreparedStatement stat;
    private static String insert
            ="INSERT INTO TABLE1 ( ID, NAME, GENDER, MAJOR, SCORE ) VALUES ( ?, ?, ?, ?,?)";
    private static String searchAll
            = "SELECT * FROM TABLE1";
    private String[] IDNum;
    public insertDialog(TheGUI theGUI){
        container = new Container();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("江苏大学校徽.JPG"));
        this.setIconImage(image);
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        okButton = new JButton("Okay");
        cancelButton = new JButton("Cancel");
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        genderLabel = new JLabel("Gender");
        majorLabel = new JLabel("Major");
        scoreLabel = new JLabel("Score");
        idTextfield = new JTextField("input the ID",10);
        nameTextfield = new JTextField("input the name",10);
        majorTextfield = new JTextField("input the major",10);
        scoreTextfield = new JTextField("input the score",10);
        maleRadiobutton = new JRadioButton("Male");
        femaleRadiobutton = new JRadioButton("Female");
        panel1.add(idLabel);
        panel1.add(idTextfield);
        panel2.add(nameLabel);
        panel2.add(nameTextfield);
        panel3.add(majorLabel);
        panel3.add(majorTextfield);
        panel4.add(scoreLabel);
        panel4.add(scoreTextfield);
        panel5.add(genderLabel);
        panel5.add(new JLabel(""));
        panel5.add(maleRadiobutton);
        panel5.add(femaleRadiobutton);
        container.setLayout(new GridLayout(5,1));
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
        container.add(panel5);
        panel.add(okButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.SOUTH);
        add(container);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = idTextfield.getText();
                name = nameTextfield.getText();
                if(maleRadiobutton.isSelected()) {
                    gender = "男";
                    System.out.println(gender);
                }
                else if (femaleRadiobutton.isSelected()) {
                    gender = "女";
                    System.out.println(gender);
                }
                major = majorTextfield.getText();
                score = Integer.parseInt(scoreTextfield.getText());
                try {
                    boolean statement = false;
                    selectAll();
                    for(int i=0; i<200; i++)
                    {
                        if(id!=IDNum[i]){
                            insertAction(id, name, gender, major, score);
                            statement = true;

                        }

                    }
                    if (statement)
                    {
                        JOptionPane.showMessageDialog(null, "The ID already exists");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        };
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        okButton.addActionListener(okListener);
        cancelButton.addActionListener(cancelListener);


    }
    public void insertAction(String id, String name, String gender, String major, int score) throws SQLException{
        stat = con.prepareStatement(insert);
        stat.setString(1, id);
        stat.setString(2, name);
        stat.setString(3, gender);
        stat.setString(4, major);
        stat.setInt(5, score);
        stat.executeUpdate();

    }
    public void selectAll() throws SQLException
    {
        getConnection();
        IDNum = new String[200];
        stat = con.prepareStatement(searchAll);
        ResultSet rs = stat.executeQuery();
        int i =0;
        while(rs.next())
        {
                IDNum[i] = rs.getString("ID");
                //System.out.println(IDNum[i]);
                i++;
            }
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
