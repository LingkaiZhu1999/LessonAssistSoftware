package LessonDesign;

import java.awt.*;

public class Execute {
    private static int DEFAULT_WIDTH = 1200;
    private static int DEFAULT_HEIGHT = 600;
    public static void main(String[] args)
    {
        //Intial intial = new Intial();
        //intial.Intialization();

        EventQueue.invokeLater(()->
        {
            PasswordChooser passwordChooser = new PasswordChooser();
            passwordChooser.setVisible(true);
            passwordChooser.setTitle("Log in");
            passwordChooser.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            //TheGUI demo=new TheGUI();
            //demo.setDefaultCloseOperation(TheGUI.EXIT_ON_CLOSE);
            //demo.setTitle("课堂助手");
            //demo.setSize(2000,1800);
            //demo.setVisible(true);
        });

    }
}
