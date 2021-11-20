package LessonDesign;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.*;
import java.sql.*;

public class TheGUI extends JFrame {
    private PreparedStatement stat;
    private Connection con;
    private ResultSet rs = null;
    private JFrame frame;
    private JPanel panel, panel1, panel2;
    private ImageIcon imageIcon;
    private JButton button_start, button_terminate;
    private JLabel label_number, label_demo;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private int startID = 129386001;
    private int endID = 129386100;
    private static int DEFAULT_WIDTH = 1500;
    private static int DEFAULT_HEIGHT = 1000;
    private static String select
            = "SELECT ID, NAME, GENDER, MAJOR, SCORE FROM TABLE1";
    private static String selectOne
            = "SELECT ID, NAME, GENDER, MAJOR, SCORE FROM TABLE1 WHERE ID = ?";
    private static String addScore
            = "UPDATE TABLE1 SET SCORE = ? WHERE ID = ?";

    public TheGUI(){
        setIcon();
        frame = new JFrame();
        table = new JTable();
        model = new DefaultTableModel();
        table.setRowHeight(30);
        table.setModel(model);
        scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label_number = new JLabel("Make a roll call. ");
        label_number.setFont(new Font("微软雅黑", Font.BOLD, 120));
        label_demo = new JLabel("Present the result.");
        panel1.add(label_number);
        panel1.add(label_demo);
        button_start = new JButton("开始");
        button_terminate = new JButton("结束");
        add(panel1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        panel.add(button_start);
        panel.add(button_terminate);
        setVisible(true);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu editMenu = new JMenu("Edit");
        JMenu ViewMenu = new JMenu("View");
        JMenu setMenu = new JMenu("Set");
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(editMenu);
        menuBar.add(ViewMenu);
        menuBar.add(setMenu);
        menuBar.add(aboutMenu);
        //setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        add(panel, BorderLayout.SOUTH);
        editMenu.add(new AbstractAction("Search") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchDialog(TheGUI.this).setVisible(true);
            }
        });

        editMenu.add(new AbstractAction("Change") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new changeDialog(TheGUI.this).setVisible(true);

            }
        });

        editMenu.add(new AbstractAction("Delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteDialog(TheGUI.this).setVisible(true);

            }
        });

        editMenu.add(new AbstractAction("Insert") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new insertDialog(TheGUI.this).setVisible(true);

            }
        });
        ViewMenu.add(new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UpdateTable();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        setMenu.add(new AbstractAction("set range") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRange();
            }
        });
        aboutMenu.add(new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Team work by Zhu Lingkai, Bao Chengwei, Miu Tiange");
            }
        });


        panel2.add(button_start);
        panel2.add(button_terminate);
        add(panel2, BorderLayout.SOUTH);
        try {
            getConnection();
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    getPerson();
                } catch(SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        };
        button_start.addActionListener(okListener);
    }

    public void getData() throws SQLException {
        stat = con.prepareStatement(select);
        rs = stat.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();

        int col = rsmd.getColumnCount();
        System.out.println(col);
        for (int i = 1; i <= col; i++) {
            model.addColumn(rsmd.getColumnLabel(i));
        }
        String[] row = new String[col];
        while (rs.next()) {
            for (int i = 0; i < col; i++) {
                row[i] = rs.getString(i + 1);
            }
            model.addRow(row);
        }
        rs.close();
    }

    public void getConnection() throws SQLException {
        String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//SQL数据库引擎
        String connectDB = "jdbc:sqlserver://DESKTOP-ODSKH3J\\SQLEXPRESS:1433;DatabaseName=master";
        String user = "sa";
        String password = "zlk";
        con = DriverManager.getConnection(connectDB, user, password);
//连接数据库对象
        System.out.println("连接数据库成功");
    }

    public void getPerson() throws SQLException {
        int resultNum =RandomNumber(startID, endID);
        label_number.setText("学号：" + resultNum);
        stat = con.prepareStatement(selectOne);
        String resultInfo = String.valueOf(resultNum);
        stat.setString(1, resultInfo);
        rs = stat.executeQuery();
        rs.next();
        label_demo.setText(rs.getString("ID")+" "+rs.getString("NAME")+" "+rs.getString("GENDER")+" "+rs.getString("MAJOR")+" "+rs.getString("SCORE"));

        String ID = rs.getString("ID");
        int n = JOptionPane.showConfirmDialog(null, "Is your answer correct? (Add 5 points if your answer is correct.)", "标题",JOptionPane.YES_NO_OPTION);
        if(n==0)
        {
            System.out.println("ifdsoijf");
            getConnection();
            PreparedStatement statement = con.prepareStatement(addScore);
            int score = rs.getInt("SCORE") +5;
            System.out.println(score);
            statement.setInt(1, score);
            statement.setString(2, ID);
            statement.executeUpdate();
            statement.close();

        }

    }

    public int RandomNumber(int min, int max) throws SQLException {
        int Num = 0;
        int[] num = new int[100];
        boolean state = true;
        while (state)
            if (max > min) {
                int d = max - min;
                Random random = new Random();
                Num = (random.nextInt(d)) + min;
                state = false;
            } else if (min == max) {
                state = false;
                Num = min;
            } else if (min > max) {
                JOptionPane.showMessageDialog(null, "Max must be bigger than min");
                return 0;
            }
        return Num;
    }
    public void UpdateTable() throws SQLException {
        getConnection();
        model = new DefaultTableModel();
        getData();
        table = new JTable();
        table.setModel(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }
    public void setRange()
    {
        String inputValue1 = JOptionPane.showInputDialog("Please input the ID you want to begin from. ");
        startID = Integer.parseInt(inputValue1);
        String inputValue2 = JOptionPane.showInputDialog("Please input the ID you want to end.");
        endID = Integer.parseInt(inputValue2);
    }
    public void setIcon()
    {
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("江苏大学校徽.JPG"));
        this.setIconImage(image);
    }

}

