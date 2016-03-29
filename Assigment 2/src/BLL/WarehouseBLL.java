package BLL;

import java.util.ArrayList;

import DAL.WarehouseAccess;
import model.Product;

public class WarehouseBLL {

	private WarehouseAccess wa = new WarehouseAccess();
	private ProductBLL pb=new ProductBLL();

	public void update(Product p) {
		if (exProd(p))
			wa.update(p);

	}

	public void remove(Product p) {
		if (exProd(p))
			wa.delete(p);

	}

	private boolean exProd(Product p) {
		ArrayList<Product> availableProd = new ArrayList<Product>();
		availableProd.addAll(wa.getProducts());
		for (Product prod : availableProd) {
			if (prod.getId() == p.getId())
				return true;
		}
		return false;

	}

	public int getQuantity(Product p) {
		pb.setId(p);
		return wa.getQuantity(p);
	}

}
