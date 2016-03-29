package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class GuiCustomer {

	public JFrame frame;
	public JTextField quantity,updateAddress;
	public JButton btnAdd,btnUpdateAdr,btnDeleteAccount,btnRemove, btnSubmitOrder,updateOrder;
	public JLabel lblTotal,lblUpdateAdress;
	public JTable order;
	public JComboBox<String> productList;
	private Object[] columnNames = {"Name", "Price", "Quantity" };
	public Object[][] data ;
	private JTable table;
	public DefaultTableModel model;

	public GuiCustomer() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Customer");
		frame.setBounds(100, 100, 438, 408);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(29, 30, 46, 14);
		frame.getContentPane().add(lblProduct);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(152, 30, 46, 14);
		frame.getContentPane().add(lblQuantity);

		quantity = new JTextField();
		quantity.setBounds(152, 63, 86, 20);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(301, 62, 89, 23);
		frame.getContentPane().add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.setBounds(301, 107, 89, 23);
		frame.getContentPane().add(btnRemove);

		btnSubmitOrder = new JButton("Submit New Order");
		btnSubmitOrder.setBounds(284, 174, 123, 23);
		frame.getContentPane().add(btnSubmitOrder);

		lblTotal = new JLabel("0.0");
		lblTotal.setBounds(323, 149, 46, 14);
		frame.getContentPane().add(lblTotal);

		updateOrder = new JButton("Update order");
		updateOrder.setBounds(284, 208, 123, 23);
		frame.getContentPane().add(updateOrder);

		order = new JTable();
		order.setBounds(29, 233, 209, -115);
		frame.getContentPane().add(order);

		productList = new JComboBox<String>();
		productList.setBounds(10, 63, 108, 20);
		frame.getContentPane().add(productList);
		
		lblUpdateAdress = new JLabel("Update Adress");
		lblUpdateAdress.setBounds(284, 263, 123, 14);
		frame.getContentPane().add(lblUpdateAdress);
		
		updateAddress = new JTextField();
		updateAddress.setBounds(209, 288, 198, 20);
		frame.getContentPane().add(updateAddress);
		updateAddress.setColumns(10);
		
		btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setBounds(10, 287, 137, 23);
		frame.getContentPane().add(btnDeleteAccount);
		
		btnUpdateAdr = new JButton("Update");
		btnUpdateAdr.setBounds(284, 334, 89, 23);
		frame.getContentPane().add(btnUpdateAdr);
		
		JFrame tableFrame = new JFrame();
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(43, 239, 173, -103);
		scrollPane.setVisible(true);
		scrollPane.setViewportView(table);
		tableFrame.add(scrollPane);
		tableFrame.pack();
		tableFrame.setSize(400,300);
		tableFrame.setVisible(true);
		
		frame.setVisible(true);
	}
}
