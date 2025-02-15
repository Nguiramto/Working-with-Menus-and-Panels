package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuFrame extends SimpleFrame {

    private JLabel display;
    private JMenuItem testItem;
    private Timer textMover; // Timer for text animation
    private int textPosition = 0; // Tracks text movement position


    public MenuFrame() {

        display = new JLabel("No menu selected.", JLabel.CENTER);

        //------Creating a menu bar and add it to the fame----------
        JMenuBar menuBar = new JMenuBar();
        setTitle("Menu Window");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //-------Creating and adding the menus
        JMenu firstMenu = new JMenu("List Menu");
        JMenu secondMenu = new JMenu("Test Menu");
        menuBar.add(firstMenu);
        menuBar.add(secondMenu);


        //-------Creating the menu items and add thrm to the menus---------
        JMenuItem firstItem = new JMenuItem("Bader");
        JMenuItem secondItem = new JMenuItem("Silva");
        JMenuItem thirdItem = new JMenuItem("Tala");
        JMenuItem fourthItem = new JMenuItem("Dj Khalid");
        JMenuItem secretWordItem = new JMenuItem("Secret Word");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem enableItem = new JMenuItem("Enable Test");
        JMenuItem disableItem = new JMenuItem("Disable Test");

        firstMenu.add(firstItem);
        firstMenu.add(secondItem);
        firstMenu.add(thirdItem);
        firstMenu.add(fourthItem);
        firstMenu.add(secretWordItem);
        firstMenu.addSeparator();
        firstMenu.add(enableItem);

        secondMenu.add(enableItem);
        secondMenu.add(disableItem);
        secondMenu.add(exitItem);

        //-----Creating a listener and add it to the menu items----------
        ActionListener itemListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //----------Getting the name of the clicked menu item---------
                String command = e.getActionCommand();

                //-------Updating label with selected item------------
                display.setText("Selected: " + command);

                //-------If "Exit" is clicked, close the program---------
                if ("Exit".equals(command)) {
                    System.exit(0);
                }
            }
        };

        //--------Adding ActionListener to menu items---------
        firstItem.addActionListener(itemListener);
        secretWordItem.addActionListener(itemListener);
        exitItem.addActionListener(itemListener);
        enableItem.addActionListener(itemListener);
        disableItem.addActionListener(itemListener);


        //-------------Second ActionListener (Handles enabling/disabling text movement)-----------------
        ActionListener animationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();

                if ("Enable Test".equals(command)) {
                    display.setFont(new Font("Arial", Font.BOLD, 18)); // Make text bold
                    startMovingText(); // Start movement
                }
                else if ("Disable Test".equals(command)) {
                    stopMovingText(); // Stop movement
                    display.setFont(new Font("Arial", Font.PLAIN, 16)); // Reset font
                }
            }

            //-------------------Method to start moving text from left to right------------------
            private void startMovingText() {
                if (textMover == null || !textMover.isRunning()) {
                    textMover = new Timer(300, new ActionListener() { // Runs every 100ms
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            textPosition += 5;
                            if (textPosition > getWidth()) {
                                textPosition = -100; // Reset position when reaching the edge
                            }
                            display.setText("<html><div style='margin-left:" + textPosition + "px'>"
                                    + display.getText() + "</div></html>");
                        }
                    });
                    textMover.start();
                }
            }

            //-------------Method to stop text movement-------------------
            private void stopMovingText() {
                if (textMover != null) {
                    textMover.stop();
                }
            }

        };

        enableItem.addActionListener(animationListener);
        disableItem.addActionListener(animationListener);

        //---------------Open new panel when "Secret Word" is clicked---------------
        secretWordItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSecretWordPanel(); // Open the SecretPanel
            }
        });


        //-------- Setting the menu bar---------
        setJMenuBar(menuBar);

        //---------Adding the label to the frame--------------
        this.getContentPane().add(display, BorderLayout.CENTER);

    }

    //----------Method to set the text in the label-----------
    public void setText (String text) {
        display.setText(text);
    }

    //----------Method to enable the label----------
    public void enableTest () {
        testItem.setEnabled(true);
    }

    //---------Method to disable the label-------------
    public void disableTest () {
        testItem.setEnabled(false);
    }


    //--------------Method to open the SecretPanel-----------------
    private void openSecretWordPanel() {
        // Create a new frame to display the JPanel
        JFrame secretFrame = new JFrame("Secret Word Panel");
        secretFrame.setSize(400, 200);
        secretFrame.setLocationRelativeTo(this); // Center the new window
        secretFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //-------------Add the custom SecretPanel to the frame--------------
        SecretPanel secretPanel = new SecretPanel();
        secretFrame.add(secretPanel);

        secretFrame.setVisible(true);
    }
}