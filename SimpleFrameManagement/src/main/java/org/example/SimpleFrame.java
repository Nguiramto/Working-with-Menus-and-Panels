package org.example;

import javax.swing.*;

public class SimpleFrame extends JFrame {

    public SimpleFrame() {

        //-----------Setting Size of the Frame------------
        this.setSize(600,600);
        this.setLocation(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

    }

    //--------Making the Frame visible and setting title----------
    public void showIt() {
        this.setTitle("Graphical User Interface");
        this.setVisible(true);
    }

}
