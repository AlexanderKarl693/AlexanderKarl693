package TestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Windows.LoginWindow;

class TestLoginWindow {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	

	public static int counter = 0;
	public static Component getChildNamed(Component parent,String name) {
		
		if (name.equals(parent.getName())) {
			return parent;
		}
		if (parent instanceof java.awt.Container) {
			Component[] children = (parent instanceof JMenu) ?
						((JMenu)parent).getMenuComponents() :
						((Container)parent).getComponents();
			for (int i = 0; i < children.length; i++) {
				Component child = getChildNamed(children[i],name);
				if(child != null)
				{
					return child;
				}
			}
		}
		return null;
	};
	public static Component getChildIndex(Component parent,String name,int index) {
		counter = 0;
		if (parent instanceof JPanel) {
			Component[] children = ((JPanel)parent).getComponents();
			for (int i = 0; i < children.length; i++) {
				if (children[i] instanceof JPanel && !children[i].isVisible()) {
					continue;					
				}
				Component child = getChildIndexInternal(children[i], name,index) ;
				if (child!= null) {
					return child;
				}
			}
		}
		return null;
	};
	public static Component getChildIndexInternal(Component parent,String name,int index) {
		if (parent.getClass().toString().endsWith(name)) {
			if (counter == index) {
				return parent;
			}
			++counter;	
		}
		if (parent instanceof Container) {
			Component[] children= (parent instanceof JMenu)?
					((JMenu)parent).getMenuComponents():
						((Container)parent).getComponents();
			for (int i = 0; i < children.length; i++) {
				Component child = getChildIndexInternal(children[i], name, index);
				if (child!=null) {
					return child;
				}
			}
		}
		return null;
	}
	
	public static Component getComponent(Container parent,String name) {
	
		if (parent instanceof Container) {
			Component[] children = parent.getComponents();	
			for (int i = 0; i < children.length; i++) {
				if (children[i].getName() != name) {
					continue;					
				}
				return children[i];
			}
		}
		return null;
	};
	
	@Test
	void TestLogInWithWrongCredentiels()
	{
		LoginWindow window = new LoginWindow();
		Container container = window.getContentPane();
		String loginSucceed = "";
		window.setVisible(true);
		window.setBounds(10, 10, 350, 800);
		JLabel loginStatusLabel = (JLabel) getComponent(container,"loginStatusLabel");
		JTextField userNameTextField     = (JTextField)getComponent(container, "userTexfield");
		JTextField userPasswordTextField = (JTextField)getComponent(container, "passwortTextField");
		JButton loginButton = (JButton)getComponent(container, "loginButton");
		sleep(1);
		userNameTextField.setText("y");
		sleep(1);
		userPasswordTextField.setText("y");
		sleep(1);
		loginButton.doClick();	
		String loginMessage = loginStatusLabel.getText();
		Assertions.assertEquals(window.loginMessageFailed, loginMessage);
		sleep(1);
	}
	
	
	
	void sleep(int sec)
	{
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
