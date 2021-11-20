package LessonDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ Leon zhu
 */

public class deleteDialog extends JDialog {
    private Container container;
    private JPanel panel, panel1, panel2, panel3;
    private JLabel label1, label2, outputLabel;
    private JTextField textField;
    private JButton okButton, cancelButton;
    private static int DEFAULT_WIDTH = 1000;
    private static int DEFAULT_HEIGHT = 800;
    private static String delete
            ="DELETE FROM TABLE1 WHERE ID = ?";
    public deleteDialog(TheGUI theGUI)
    {
        container = new Container();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("江苏大学校徽.JPG"));
        this.setIconImage(image);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel = new JPanel();
        label1 = new JLabel("Please input the ID of the student you want to delete");
        label2 = new JLabel("");
        outputLabel = new JLabel();
        textField = new JTextField(10);
        okButton = new JButton("Okay");
        cancelButton = new JButton("Cancel");
        panel1.add(label1);
        panel2.add(label2);
        panel2.add(textField);
        panel3.add(outputLabel);
        panel.add(okButton);
        panel.add(cancelButton);
        container.setLayout(new GridLayout(3,1));
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        add(container);
        add(panel, BorderLayout.SOUTH);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField.getText());
                try {
                    deleteAction(id);
                    outputLabel.setText("Action successfully!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        };
        okButton.addActionListener(okListener);
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        cancelButton.addActionListener(cancelListener);


    }
    public static void deleteAction(int id) throws SQLException {
        String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String connectDB="jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
        String user="sa";
        String password="zlk";
        Connection con = DriverManager.getConnection(connectDB, user, password);
        System.out.println("连接数据库成功");
        PreparedStatement stat = con.prepareStatement(delete);
        stat.setInt(1, id);
        stat.executeUpdate();
        stat.close();
    }

}
