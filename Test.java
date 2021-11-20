package LessonDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame implements ActionListener{

    /**
     * @param args
     */
    JFrame frame=new JFrame("another");
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test a=new Test();
        a.setVisible(true);
        a.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Test(){
        JButton button=new JButton("anotherWindow");
        button.addActionListener(this);
        getContentPane().add(button);
        setTitle("one");
        setSize(300, 200);
    }


    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        frame.setSize(300, 200);
        frame.setLocation(400, 0);
        frame.setVisible(true);
    }


}