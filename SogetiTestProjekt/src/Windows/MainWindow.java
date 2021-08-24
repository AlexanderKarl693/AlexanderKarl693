package Windows;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import Database.MySql;
import net.proteanit.sql.DbUtils;
public class MainWindow extends JFrame implements ActionListener{

	public static String mavenPath="C:\\Users\\mvasic\\git\\Sogeti\\SogetiTestProjekt\\rs2xml.jar";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmMainWindow;
	private JPanel panel ;
	private JButton btn_Login;
	private JButton btnRegister;
    private JTable table;
	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BuildFrame();
		BuildButtons();
		BuildTable();
	}

	/*
	 *  Tabellenobjekt wird hier aktualisiert.
	 * */
    public void DisplayOnlineUser() throws SQLException
    {	
    	if(table == null)
    	{
    		table = new JTable();    		
    	}
    	ResultSet rs = null;
		try {
			rs = MySql.SelectAllOnlineUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    	if(rs != null)
    	{
    		table.setModel(DbUtils.resultSetToTableModel(rs));    		
    	}
    }
    
    public void BuildFrame()
    {
		frmMainWindow = new JFrame();
		frmMainWindow.setTitle("Main Window");
		frmMainWindow.setBounds(100, 100, 1196, 926);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		panel = new JPanel();
		frmMainWindow.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBounds(100, 100, 100, 100);
		panel.setLayout(null);
    }
    
    public void BuildButtons()
    {
		// Login Button
		btn_Login = new JButton("Login");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        LoginWindow frame = new LoginWindow();
		        frame.setVisible(true);
		        frame.setBounds(10, 10, 370, 600);
		       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setResizable(false);
		        frame.setVisible(true);
			}
		});
		btn_Login.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Login.setBounds(41, 29, 85, 39);
		panel.add(btn_Login);
		
		//Register Button
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        RegisterWindow frame = new RegisterWindow();
		        frame.setVisible(true);
		        frame.setBounds(10, 10, 370, 600);
		       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setResizable(false);
		        frame.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(161, 29, 85, 39);
		panel.add(btnRegister);
    }
    
    
    public void BuildTable()
    {
		table = new JTable();
		table.setBounds(41, 192, 644, 191);
		panel.add(table);
		
		JLabel lblUserOnline = new JLabel("User Online");
		lblUserOnline.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserOnline.setBounds(41, 150, 85, 39);
		panel.add(lblUserOnline);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DisplayOnlineUser();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRefresh.setBounds(119, 161, 85, 21);
		panel.add(btnRefresh);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
