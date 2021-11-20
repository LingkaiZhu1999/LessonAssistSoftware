package LessonDesign;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 * A password chooser that is shown inside a dialog
 */
public class PasswordChooser extends JFrame {
    private Container container;
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private static char[] Spassword = {'N', 'K', 'V', 'D'};
    private String user = "zlk";
    private JPanel backgroundPanel;
    private static int DEFAULT_WIDTH = 1500;
    private static int DEFAULT_HEIGHT = 1000;

    public PasswordChooser() {
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("江苏大学校徽.JPG"));
        this.setIconImage(image);
        Font font = new Font("微软雅黑", Font.PLAIN, 20);
        InitGlobalFont(font);
        BackgroundPanel();

        setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        container = new Container();
        container.setLayout(new GridLayout(3, 1));
        panel1.add(new JLabel("user name:"));
        panel1.add(username = new JTextField("", 10));
        panel1.setOpaque(false);
        panel2.add(new JLabel("Password:"));
        panel2.add(password = new JPasswordField("",10));
        panel2.setOpaque(false);
        container.add(panel1);
        container.add(panel2);
        //container.add(backgroundPanel);
        add(backgroundPanel);

        add(container, BorderLayout.CENTER);
        okButton = new JButton(" Okay ");
        okButton.addActionListener(event -> {

            if (user.equals(username.getText()) && Arrays.equals(Spassword, password.getPassword())) {
                JOptionPane.showMessageDialog(null, "Login in successfully");
                setVisible(false);
                //Intial intial = new Intial();
                //intial.Intialization();
                TheGUI theGUI = new TheGUI();
                theGUI.setExtendedState(getExtendedState()|TheGUI.MAXIMIZED_BOTH);
                //theGUI.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
                theGUI.setVisible(true);
                theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theGUI.setTitle("课堂助手");
            } else {
                JOptionPane.showMessageDialog(null, "password or user name is not correct, Please input again!");
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> {
            setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        BackgroundPanel();
    }
    public void BackgroundPanel()
    {
        backgroundPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(TheGUI.class.getResource("江苏大学.JPG"));
                img.paintIcon(this, g, 0, 0);
            }
        };
        backgroundPanel.setOpaque(true);
        backgroundPanel.setSize(400, 400);
    }
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}

