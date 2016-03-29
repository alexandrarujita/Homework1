package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import BLL.CustomerBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import BLL.WarehouseBLL;
import model.Customer;
import model.Order;
import model.Product;

public class Controller {

	private GuiAdmin admin;
	private GuiLogin login;
	private GuiCustomer customer;
	private Order order;
	private CustomerBLL custBLL;
	private OrderBLL ordBLL;
	private ProductBLL prodBLL;
	private WarehouseBLL wb;
	private Customer cust = new Customer();

	public Controller() {
		login = new GuiLogin();

		login.btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = login.loginName.getText();
				if (name.equals("Admin")) {
					login.frame.dispose();
					adminWindow();
				} else {

					cust.setName(name);
					char[] pswd = login.userPassword.getPassword();
					cust.setPassword(pswd);
					CustomerBLL custBLL = new CustomerBLL();
					if (custBLL.okUsername(cust)) {
						login.frame.dispose();
						customerWindow(cust);
					} else
						JOptionPane.showMessageDialog(null, "Wrong username or password", "Error",
								JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		login.btnCreateAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Customer newCust = new Customer();
				String name = login.newUsername.getText();
				char[] pswd = login.newPassword.getPassword();
				String address = login.txtAddress.getText();
				newCust.setName(name);
				newCust.setPassword(pswd);
				newCust.setAdress(address);
				CustomerBLL custBLL = new CustomerBLL();
				if (!custBLL.existingUsername(newCust)) {
					login.frame.dispose();
					custBLL.addUsername(newCust);
					customerWindow(newCust);
				} else
					JOptionPane.showMessageDialog(null, "Username already exists", "Error",
							JOptionPane.WARNING_MESSAGE);
				login.frame.dispose();
			}

		});

	}

	public void adminWindow() {

		admin = new GuiAdmin();
		Product p = new Product();
		prodBLL = new ProductBLL();
		wb=new WarehouseBLL();

		admin.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setProduct(p);
				if (p.getName().isEmpty())
					JOptionPane.showMessageDialog(null, "Empty name", "Error", JOptionPane.WARNING_MESSAGE);
				else if (p.getQuantity() < 0)
					JOptionPane.showMessageDialog(null, "Wrong quantity", "Error", JOptionPane.WARNING_MESSAGE);
				else if (p.getPrice() <= 0)
					JOptionPane.showMessageDialog(null, "Wrong price", "Error", JOptionPane.WARNING_MESSAGE);
				else
					prodBLL.newProduct(p);

			}

		});

		admin.btnUpdateProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setProduct(p);
				p.setId(Integer.valueOf(admin.Id.getText()));
				if (!prodBLL.existingProd(p))
					JOptionPane.showMessageDialog(null, "There is no such product", "Error",
							JOptionPane.WARNING_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null, "Succesfully updated", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
					prodBLL.update(p);
					wb.update(p);
				}
			}

		});

		admin.btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setProduct(p);
				p.setId(Integer.valueOf(admin.Id.getText()));
				if (!prodBLL.existingProd(p))
					JOptionPane.showMessageDialog(null, "There is no such product", "Error",
							JOptionPane.WARNING_MESSAGE);
				else {
					JOptionPane.showMessageDialog(null, "Succesfully removed", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
					prodBLL.delete(p);
					wb.remove(p);

				}

			}

		});

		for (Product prod : prodBLL.prodForSale()) {
			Object ob[] = { prod.getId(), prod.getName(), prod.getPrice(), prod.getQuantity() };
			admin.model.addRow(ob);
		}
	}

	private void setProduct(Product product) {

		String prodName = admin.product.getText();
		String quantity = admin.quantity.getText();
		String price = admin.price.getText();

		product.setName(prodName);
		product.setQuantity(Integer.valueOf(quantity));
		product.setPrice(Double.valueOf(price));
	}

	private void customerWindow(Customer c) {
		customer = new GuiCustomer();
		order = new Order();
		Product p = new Product();
		ProductBLL prodBLL = new ProductBLL();
		ordBLL = new OrderBLL();
		custBLL = new CustomerBLL();
		WarehouseBLL wb = new WarehouseBLL();

		ArrayList<Product> forSale = prodBLL.prodForSale();
		for (Product p1 : forSale)
			customer.productList.addItem(p1.getName());

		customer.btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = Integer.valueOf(customer.quantity.getText());
				p.setQuantity(quantity);
				p.setName((String) customer.productList.getSelectedItem());
				prodBLL.setId(p);
				prodBLL.setPrice(p);
				if (quantity <= 0)
					JOptionPane.showMessageDialog(null, "Wrong quantity", "Succes", JOptionPane.INFORMATION_MESSAGE);
				else if (wb.getQuantity(p) < quantity)
					JOptionPane.showMessageDialog(null, "Only " + wb.getQuantity(p) + " products in stock", "Succes",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					order.addProd(p);
					order.setC(c);
					ordBLL.addOrder(order);
					double totalPrice = ordBLL.calculateOrder(order);
					customer.lblTotal.setText(String.valueOf(totalPrice));
				}
				Object ob[]={p.getName(),p.getPrice(),p.getQuantity()};
				customer.model.addRow(ob);
			}

		});

		customer.btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int quantity = Integer.valueOf(customer.quantity.getText());
				p.setQuantity(quantity);
				p.setName((String) customer.productList.getSelectedItem());

				order.removeProd(p);
				order.setC(c);
				ordBLL.updateOrder(order);
				double totalPrice = ordBLL.calculateOrder(order);
				customer.lblTotal.setText(String.valueOf(totalPrice));
				//customer.model.removeRow();

			}

		});

		customer.btnSubmitOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ordBLL.submitOrder(order);
				JOptionPane.showMessageDialog(null, "Order with ID " + order.getId() + " submitted", "Succes",
						JOptionPane.INFORMATION_MESSAGE);
				customer.frame.dispose();
			}
		});

		customer.updateOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ordBLL.updateOrder(order);
				for(Product prod:order.getProdList()){
					Object ob[]={prod.getName(),prod.getPrice(),prod.getQuantity()};
					customer.model.addRow(ob);
				}
			}

		});

		customer.btnUpdateAdr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.setAdress(customer.updateAddress.getText());
				custBLL.updateAddress(c);
				JOptionPane.showMessageDialog(null, "Address updated to: " + customer.updateAddress.getText(), "Succes",
						JOptionPane.INFORMATION_MESSAGE);

			}

		});

		customer.btnDeleteAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				custBLL.remove(c);
				JOptionPane.showMessageDialog(null, "Account deleted ", "Succes", JOptionPane.INFORMATION_MESSAGE);
				customer.frame.dispose();
			}

		});
		
//		for(Product prod:order.getProdList()){
//			Object ob[]={prod.getName(),prod.getPrice(),prod.getQuantity()};
//			customer.model.addRow(ob);
//		}

	}

}
