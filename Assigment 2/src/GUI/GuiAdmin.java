package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class GuiAdmin {

	private JFrame frame;
	public JTextField quantity;
	public JButton btnUpdateProduct;
	public JButton btnRemove;
	public JTextField product;
	public JButton btnAdd;
	public JTextField price;
	public JTextField Id;
	private Object[] columnNames = { "Id", "Name", "Price", "Quantity" };
	public Object[][] data ;
	private JTable table;
	public DefaultTableModel model;

	public GuiAdmin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 523, 337);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnAdd = new JButton("Add new product");
		btnAdd.setBounds(317, 214, 134, 40);
		frame.getContentPane().add(btnAdd);

		quantity = new JTextField();
		quantity.setText("0");
		quantity.setBounds(167, 58, 86, 20);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);

		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(43, 33, 86, 14);
		frame.getContentPane().add(lblProduct);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(167, 33, 86, 14);
		frame.getContentPane().add(lblQuantity);

		btnUpdateProduct = new JButton("Update Products");
		btnUpdateProduct.setBounds(317, 157, 134, 38);
		frame.getContentPane().add(btnUpdateProduct);

		btnRemove = new JButton("Delete product");
		btnRemove.setBounds(317, 108, 134, 38);
		frame.getContentPane().add(btnRemove);

		product = new JTextField();
		product.setBounds(22, 57, 107, 20);
		frame.getContentPane().add(product);
		product.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(317, 33, 46, 14);
		frame.getContentPane().add(lblPrice);

		price = new JTextField();
		price.setText("0");
		price.setBounds(293, 58, 86, 20);
		frame.getContentPane().add(price);
		price.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(425, 33, 46, 14);
		frame.getContentPane().add(lblId);

		Id = new JTextField();
		Id.setBounds(405, 58, 54, 20);
		frame.getContentPane().add(Id);
		Id.setColumns(10);

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
