package org.example;

import javax.swing.*;
import java.awt.*;

public class SecretPanel extends JPanel {

    private JTextField inputField;
    private JButton submitButton;
    private JLabel resultLabel;

    public SecretPanel() {

        setLayout(new FlowLayout());

        //-------Setting background color-------------
        setBackground(new Color(0, 128, 128));

        //---------------Label and text field for input----------------
        JLabel label = new JLabel("Enter your secret word:");
        label.setForeground(Color.WHITE); // Set label text color to white
        inputField = new JTextField(15);
        submitButton = new JButton("Submit");

        //------------Result label to display the entered word----------------
        resultLabel = new JLabel("Your secret word will appear here.");
        resultLabel.setForeground(Color.WHITE);

        //----------------Add components to the panel------------------
        add(label);
        add(inputField);
        add(submitButton);
        add(resultLabel);

        //----------------ActionListener for the submit button---------------
        submitButton.addActionListener(e -> {
            String secretWord = inputField.getText();
            resultLabel.setText("Entered Secret Word: " + secretWord);
            System.out.println("Secret Word entered: " + secretWord); // Print to console
        });
    }
}
