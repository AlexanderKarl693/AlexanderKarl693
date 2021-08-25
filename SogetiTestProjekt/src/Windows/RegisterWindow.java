package Windows;

import javax.swing.*;
import Database.MySql;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registerButton = new JButton("SAVE");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    private final JTextField ageTextField = new JTextField("");
    private final JLabel lblAge = new JLabel("AGE");

    /***********************************************************
     * Konstructor                                   		   *
     * *********************************************************/
    RegisterWindow() {
    	setTitle("Register");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
    	ageTextField.setBounds(150, 297, 150, 30);
    	ageTextField.setColumns(10);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 225, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 225, 150, 30);
        showPassword.setBounds(150, 255, 150, 30);
        registerButton.setBounds(50, 379, 100, 30);
        resetButton.setBounds(200, 379, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(registerButton);
        container.add(resetButton);
        container.add(ageTextField);
        lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblAge.setBounds(50, 305, 45, 13);
        
        getContentPane().add(lblAge);
                
    }

    /***********************************************************
     * EventListener werden den Controls hinzugefügt		   *
     * *********************************************************/
    public void addActionEvent() {
    	registerButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    /***********************************************************
     * Klick-Events werden hier verarbeitet         		   *
     * *********************************************************/
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String userText;
            String pwdText;
            int age;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            age = Integer.parseInt(ageTextField.getText());
            try {
            	if( age > 15)
            	{
                	MySql.AddUser(userText, pwdText);
                	JOptionPane.showMessageDialog(this, "Welcome to Sogeti!");            		
            	}
            	else {
            		JOptionPane.showMessageDialog(this, "Registration requires a minimum age of 16!");
            	}
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}