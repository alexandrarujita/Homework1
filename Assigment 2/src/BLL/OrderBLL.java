package BLL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import DAL.OrderAccess;
import DAL.ProductAccess;
import DAL.WarehouseAccess;
import model.Order;
import model.Product;

public class OrderBLL {
	int totalPrice;
	ProductAccess prodDAL = new ProductAccess();
	OrderAccess ordDAL = new OrderAccess();
	WarehouseAccess wa = new WarehouseAccess();

	public int calculateOrder(Order ord) {

		for (Product p : ord.getProdList()) {
			totalPrice += p.getPrice() * p.getQuantity();
		}
		return totalPrice;
	}

	public void addOrder(Order ord) {
		ord.setId(ord.getC().getId());
		ArrayList<Product> availableProd = new ArrayList<Product>();
		availableProd.addAll(prodDAL.getProd());
		for (Product p : availableProd) {
			for (Product p2 : ord.getProdList()) {
				if (p.getName().equals(p2.getName())){
					p2.setId(p.getId());
					p2.setPrice(p.getPrice());
				}
			}
		}
	}

	public void submitOrder(Order order) {
		try {
			addToFile(order);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Product p : order.getProdList())
			wa.update(p);
		ordDAL.add(order);
		// order.setId(id);
		// create new order with same customer

	}

	public void updateOrder(Order order) {
		ordDAL.update(order);

	}

	public void remove(Order order) {
		ordDAL.delete(order);
	}

	private void addToFile(Order order) throws IOException {

		File fout = new File("Order.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write("Customer: " + order.getC().getName() + "\n");
		for (Product p : order.getProdList()) {
			bw.write(p.getName() + " Price/piece: " + p.getPrice() + " Quantity: " + p.getQuantity());
			bw.newLine();
		}
		bw.write("Total price is: "+calculateOrder(order));
		bw.close();

	}
}
