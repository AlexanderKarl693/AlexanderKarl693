package Windows;
import javax.swing.*;
import Database.MySql;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container = getContentPane();
	private JLabel loginStatusLabel = new JLabel("");
	private JLabel userLabel = new JLabel("USERNAME");
	private JLabel passwordLabel = new JLabel("PASSWORD");
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton("LOGIN");
	private JButton resetButton = new JButton("RESET");
	private JCheckBox showPassword = new JCheckBox("Show Password");
	public String loginMessageFailed = "Invalid Username or Password!!!";
	public JOptionPane loginStatus = new JOptionPane();
	JDialog dialog;
    public LoginWindow() {
    	setTitle("Login");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        SetComponentNames();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(loginStatusLabel);
    	container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    // KlickEvent für den Login-Button 
    // Bei korrekten Eingaben wird der Status des Users als Online gesetzt. 
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
            try { 
   
            	  boolean c = MySql.LoginUser(userText,pwdText);  
				  if( c == true)
				  {
					  MySql.UpdateUser(userText, pwdText);
					  CloseDialog( "Welcome "+userText);					  
				  }
				  else
				  {
					  CloseDialog(loginMessageFailed);
				  }

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
    
    private void SetComponentNames()
    {
	  userLabel.setName("labelUsername"); 
	  passwordLabel.setName("labelPassword");
	  userTextField.setName("userTexfield"); 
	  passwordField.setName("passwortTextField"); 
	  loginButton.setName("loginButton"); 
	  resetButton.setName("resetButton"); 
	  showPassword.setName("showPasswordCheckbox");
	  loginStatus.setName("loginStatus");
	  loginStatusLabel.setName("loginStatusLabel");	  
    }

    public void CloseDialog(String message)
    {
        final JOptionPane pane = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        final JDialog dialog = pane.createDialog(null, "Hello world");
        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.dispose();
            }

        });
        timer.start();
        dialog.setVisible(true);        
        Integer choice = (Integer) (pane.getValue() == JOptionPane.UNINITIALIZED_VALUE ? JOptionPane.OK_OPTION : pane.getValue());        
        loginStatusLabel.setText(message);
        dialog.dispose();        
    }
}