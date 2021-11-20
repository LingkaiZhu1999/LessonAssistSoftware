package LessonDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class changeDialog extends JDialog {
    private Container container;
    private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6;
    private JButton findButton, changeButton, cancelButton;
    private JLabel label, idLabel, nameLabel, genderLabel, majorLabel, scoreLabel;
    private JTextField idTextfield, nameTextfield, majorTextfield, scoreTextfield, genderTextfield;
    private JRadioButton maleRadiobutton, femaleRadiobutton;
    private String id1, id_p;
    private String name, name_p;
    private String gender,gender_p;
    private String major, major_p;
    private int score, score_p;
    private static String updateInformation
            = "UPDATE TABLE1 SET ID = ?, NAME = ?, GENDER = ?, MAJOR = ?, SCORE = ? WHERE ID = ?";
    private static String searchInformation
            = "SELECT * FROM TABLE1 WHERE ID = ?";
    public changeDialog(TheGUI theGUI)
    {
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("江苏大学校徽.JPG"));
        this.setIconImage(image);
        container = new Container();
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        findButton = new JButton("Search");
        changeButton = new JButton("Change");
        cancelButton = new JButton("Cancel");
        label = new JLabel("Please input the student's ID that you want to edit");
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Name");
        genderLabel = new JLabel("Gender");
        majorLabel = new JLabel("Major");
        scoreLabel = new JLabel("Score");
        idTextfield = new JTextField("input the ID",10);
        nameTextfield = new JTextField("input the name",10);
        majorTextfield = new JTextField("input the major",10);
        scoreTextfield = new JTextField("input the score",10);
        genderTextfield = new JTextField("display the gender",10);
        maleRadiobutton = new JRadioButton("Male",false);
        femaleRadiobutton = new JRadioButton("Female",false);
        panel.add(findButton);
        panel.add(changeButton);
        panel.add(cancelButton);
        panel1.add(label);
        panel2.add(idLabel);
        panel2.add(idTextfield);
        panel3.add(nameLabel);
        panel3.add(nameTextfield);
        panel4.add(genderLabel);
        panel4.add(genderTextfield);
        panel4.add(maleRadiobutton);
        panel4.add(femaleRadiobutton);
        panel5.add(majorLabel);
        panel5.add(majorTextfield);
        panel5.add(scoreLabel);
        panel5.add(scoreTextfield);
        container.setLayout(new GridLayout(6,1));
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
        container.add(panel5);
        container.add(panel6);
        add(container);
        add(panel, BorderLayout.SOUTH);
        setSize(1000,800);
        ActionListener findListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id1 = idTextfield.getText();
                try {
                    Search(id1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        };
        findButton.addActionListener(findListener);
        ActionListener changeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextfield.getText();
                if(maleRadiobutton.isSelected())
                    gender = "男";
                else if (femaleRadiobutton.isSelected())
                    gender = "女";
                major = majorTextfield.getText();
                score = Integer.parseInt(scoreTextfield.getText());
                try {
                    Change(id_p, id1, name, gender, major, score);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        };
        changeButton.addActionListener(changeListener);
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        cancelButton.addActionListener(cancelListener);

    }
    public static void Change(String id1, String id2, String name, String gender, String major, int score) throws SQLException {
        String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String connectDB="jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
        String user="sa";
        String password="zlk";
        Connection con = DriverManager.getConnection(connectDB, user, password);
        System.out.println("连接数据库成功");
        PreparedStatement stat = con.prepareStatement(updateInformation);
        stat.setString(1, id1);
        stat.setString(2, name);
        stat.setString(3, gender);
        stat.setString(4, major);
        stat.setInt(5, score);
        stat.setString(6, id2);
        stat.executeUpdate();
        stat.close();
    }
    public void Search(String id) throws SQLException {
        String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String connectDB = "jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
        String user = "sa";
        String password = "zlk";
        Connection con = DriverManager.getConnection(connectDB, user, password);
        System.out.println("连接数据库成功");
        PreparedStatement stat = con.prepareStatement(searchInformation);
        stat.setString(1, id);
        ResultSet rs = stat.executeQuery();
        while(rs.next()) {
            id_p = rs.getString("ID");
            name_p = rs.getString("NAME");
            gender_p = rs.getString("GENDER");
            System.out.println(gender_p);
            major_p = rs.getString("MAJOR");
            score_p = rs.getInt("SCORE");
            idTextfield.setText(""+id_p);
            nameTextfield.setText(name_p);
            genderTextfield.setText(gender_p);
            majorTextfield.setText(major_p);
            scoreTextfield.setText(""+score_p);
        }

        stat.close();
    }

}
