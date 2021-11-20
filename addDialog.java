package LessonDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addDialog extends JDialog {
    private JLabel label,idLabel,nameLabel,genderLabel,majorLabel;
    private JLabel labelOutput;
    private JTextField idTextField,nameTextfield,genderTextfield,majorTextField;
    private JButton button;
    private JPanel panel1,panel2,panel3;
    private Container container;
    private int id;
    private String name, gender, major;
    private static int DEFAULT_WIDTH=300;
    private static int DEAFAULT_HEIGHT=400;
    public addDialog(TheGUI theGUI)
    {
        super(theGUI, "Search", true);
        Container con = getContentPane();
        setLayout(new BorderLayout());
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label= new JLabel("Please input the data:");
        panel1.setLayout(new GridLayout(4,2));
        idLabel = new JLabel("ID:");
        nameLabel = new JLabel("NAME");
        genderLabel = new JLabel("GENDER");
        majorLabel = new JLabel("MAJOR");
        labelOutput = new JLabel("Present the information");
        idTextField = new JTextField(10);
        nameTextfield = new JTextField(10);
        genderTextfield = new JTextField(10);
        majorTextField = new JTextField(10);
        panel1.add(idLabel);
        panel1.add(idTextField);
        panel1.add(nameLabel);
        panel1.add(nameTextfield);
        panel1.add(genderLabel);
        panel1.add(genderTextfield);
        panel1.add(majorLabel);
        panel1.add(majorTextField);
        panel2.add(labelOutput);
        button =new JButton("Okay");
        panel3.add(button);
        con.add(panel3,BorderLayout.SOUTH);
        con.add(panel1,BorderLayout.CENTER);
        con.add(panel2,BorderLayout.NORTH);
        setSize(1000,800);

        ActionListener listener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                String connectDB = "jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
                try {
                    Class.forName(JDriver);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("加载数据库引擎失败");
                    System.exit(0);
                }
                System.out.println("数据库驱动成功");
                try {

                    String user = "sa";
                    String password = "zlk";
                    Connection con = DriverManager.getConnection(connectDB, user, password);
                    System.out.println("连接数据库成功");
                    String sql = "INSERT INTO TABLE1(ID, NAME, GENDER, MAJOR)" + " VALUES(?,?,?,?)";
                    PreparedStatement stat = con.prepareStatement(sql);
                    id = Integer.parseInt(idTextField.getText());
                    name = nameTextfield.getText();
                    gender = genderTextfield.getText();
                    major = majorTextField.getText();
                    stat.setInt(1, id);
                    stat.setString(2, name);
                    stat.setString(3, gender);
                    stat.setString(4, major);
                    ResultSet rs = stat.executeQuery(sql);
                    int row = stat.executeUpdate();
                    if (row > 0)
                        labelOutput.setText("Already add: " + rs.getInt("ID") + " " +rs.getString("NAME") + " " + rs.getString("GENDER") + " " + rs.getString("MAJOR"));
                    stat.close();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.print(e.getErrorCode());
                    System.out.println("数据库连接错误");
                    System.exit(0);
                }

            }

        };
        button.addActionListener(listener);

    }
}
