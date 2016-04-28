package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI {

	private JFrame frame;
	public JTextField nameField;
	public JTextField idField;
	public JTextField accIdField;
	public JTextField sumField;
	private JTable clientsTable;
	private JTable accountTable;
	public JButton btnSeeClients;
	public JButton btnSeeAccounts;
	public JButton btnAddSavingAccount, btnAddMoney, btnWithdrawMoney;
	public JButton btnRemoveAccount, btnRemovePerson, btnAddSpendingAccount;
	private Object[] columnAccount = { "ID", "Balance", "Type" };
	private Object[] columnClients = { "ID", "Name" };
	private Object[][] dataClients = new Object[0][0];
	private Object[][] dataAccount;// = new Object[0][0];
	public DefaultTableModel modelAcc, modelClients;

	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnSeeClients = new JButton("See clients");
		btnSeeClients.setBounds(303, 215, 164, 38);
		frame.getContentPane().add(btnSeeClients);

		btnSeeAccounts = new JButton("See accounts");
		btnSeeAccounts.setBounds(303, 264, 164, 38);
		frame.getContentPane().add(btnSeeAccounts);

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(21, 30, 43, 14);
		frame.getContentPane().add(lblName);

		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(21, 67, 46, 14);
		frame.getContentPane().add(lblId);

		JLabel lblAccountId = new JLabel("Account ID:");
		lblAccountId.setBounds(21, 103, 80, 14);
		frame.getContentPane().add(lblAccountId);

		JLabel lblSum = new JLabel("Sum:");
		lblSum.setBounds(21, 139, 46, 14);
		frame.getContentPane().add(lblSum);

		btnAddSavingAccount = new JButton("Add savings account");
		btnAddSavingAccount.setBounds(327, 41, 164, 38);
		frame.getContentPane().add(btnAddSavingAccount);

		btnAddMoney = new JButton("Add money");
		btnAddMoney.setBounds(98, 264, 164, 38);
		frame.getContentPane().add(btnAddMoney);

		btnWithdrawMoney = new JButton("Withdraw money");
		btnWithdrawMoney.setBounds(98, 215, 164, 38);
		frame.getContentPane().add(btnWithdrawMoney);

		btnRemoveAccount = new JButton("Remove account");
		btnRemoveAccount.setBounds(562, 41, 164, 38);
		frame.getContentPane().add(btnRemoveAccount);

		btnRemovePerson = new JButton("Remove person");
		btnRemovePerson.setBounds(562, 84, 164, 38);
		frame.getContentPane().add(btnRemovePerson);

		btnAddSpendingAccount = new JButton("Add spending account");
		btnAddSpendingAccount.setBounds(327, 84, 164, 38);
		frame.getContentPane().add(btnAddSpendingAccount);

		nameField = new JTextField();
		nameField.setBounds(86, 27, 138, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		idField = new JTextField();
		idField.setText("0");
		idField.setColumns(10);
		idField.setBounds(86, 64, 138, 20);
		frame.getContentPane().add(idField);

		accIdField = new JTextField();
		accIdField.setText("0");
		accIdField.setColumns(10);
		accIdField.setBounds(86, 100, 138, 20);
		frame.getContentPane().add(accIdField);

		sumField = new JTextField();
		sumField.setText("0.0");
		sumField.setColumns(10);
		sumField.setBounds(86, 136, 138, 20);
		frame.getContentPane().add(sumField);

		frame.setVisible(true);
	}

	public void displayAcc() {
		JFrame tableFrame = new JFrame();

		modelAcc = new DefaultTableModel(dataAccount, columnAccount);
		accountTable = new JTable(modelAcc);
		accountTable.setPreferredScrollableViewportSize(accountTable.getPreferredSize());
		accountTable.setVisible(true);
		JScrollPane accountPane = new JScrollPane(accountTable);
		accountPane.setBounds(581, 294, 122, -213);
		accountPane.setVisible(true);
		accountPane.setViewportView(accountTable);

		tableFrame.getContentPane().add(accountPane);
		tableFrame.pack();
		tableFrame.setSize(400, 300);
		tableFrame.setVisible(true);

	}

	public void displayClients() {
		JFrame clientsFrame = new JFrame();

		modelClients = new DefaultTableModel(dataClients, columnClients);
		clientsTable = new JTable(modelClients);
		clientsTable.setPreferredScrollableViewportSize(clientsTable.getPreferredSize());
		clientsTable.setVisible(true);
		JScrollPane accountPane = new JScrollPane(clientsTable);
		accountPane.setBounds(581, 294, 122, -213);
		accountPane.setVisible(true);
		accountPane.setViewportView(clientsTable);

		clientsFrame.getContentPane().add(accountPane);
		clientsFrame.pack();
		clientsFrame.setSize(400, 300);
		clientsFrame.setVisible(true);

	}
}
