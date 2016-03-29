package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class GuiLogin {

	public JFrame frame;
	public JButton btnEnter;
	public JTextField newUsername;
	public JTextField loginName;
	public JTextField txtAddress;
	public JLabel lblPassword_1;
	public JPasswordField newPassword;
	public JPasswordField userPassword;
	public JButton btnCreateAccount;

	public GuiLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 500, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblPassword = new JLabel("Name");
		lblPassword.setBounds(335, 81, 89, 14);
		frame.getContentPane().add(lblPassword);

		btnEnter = new JButton("Login");
		btnEnter.setBounds(316, 210, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JLabel lblNewCustomer = new JLabel("New Customer");
		lblNewCustomer.setBounds(23, 25, 125, 14);
		frame.getContentPane().add(lblNewCustomer);
		
		newUsername = new JTextField();
		newUsername.setBounds(23, 75, 125, 20);
		frame.getContentPane().add(newUsername);
		newUsername.setColumns(10);
		
		loginName = new JTextField();
		loginName.setBounds(299, 106, 125, 20);
		frame.getContentPane().add(loginName);
		loginName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(23, 50, 46, 14);
		frame.getContentPane().add(lblName);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(23, 193, 125, 57);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(23, 168, 46, 14);
		frame.getContentPane().add(lblAddress);
		
		lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(23, 106, 46, 14);
		frame.getContentPane().add(lblPassword_1);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(23, 131, 125, 26);
		frame.getContentPane().add(newPassword);
		
		JLabel lblPassword_2 = new JLabel("Password");
		lblPassword_2.setBounds(316, 137, 65, 14);
		frame.getContentPane().add(lblPassword_2);
		
		userPassword = new JPasswordField();
		userPassword.setBounds(299, 173, 125, 26);
		frame.getContentPane().add(userPassword);
		
		btnCreateAccount = new JButton("Create account");
		btnCreateAccount.setBounds(23, 272, 141, 23);
		frame.getContentPane().add(btnCreateAccount);
		frame.setVisible(true);
	}
}
