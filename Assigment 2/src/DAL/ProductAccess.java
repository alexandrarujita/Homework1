package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.Product;

public class ProductAccess {

	private Statement myStm;
	private WarehouseAccess wa = new WarehouseAccess();

	public ProductAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "alexandra", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProd() {

		ArrayList<Product> prodInStock = new ArrayList<Product>();
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select idproduct,name, price from `test`.`product`");
			while (myRes.next()) {
				Product p = new Product();
				p.setName(myRes.getString("name"));
				p.setPrice(myRes.getDouble("price"));
				p.setId(myRes.getInt("idproduct"));
				p.setQuantity(wa.getQuantity(p));
				prodInStock.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prodInStock;

	}

	public void add(Product p) {
		try {
			myStm.executeUpdate("INSERT INTO `test`.`product` (`name`, `price`) VALUES ('" + p.getName() + "','"
					+ p.getPrice() + "')");
			ResultSet myRes = myStm.executeQuery("Select idproduct,name from `test`.`product`");
			while (myRes.next()) {
				if (myRes.getString("name").equals(p.getName())) {
					p.setId(myRes.getInt("idProduct"));
					break;
				}
			}
			wa.add(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Product p) {

		try {
			myStm.executeUpdate("UPDATE `test`.`product` SET `price`='" + p.getPrice() + "' WHERE `idproduct`='"
					+ p.getId() + "';");
			myStm.executeUpdate("UPDATE `test`.`warehouse` SET `quantity`='" + p.getQuantity() + "' WHERE `id`='"
					+ p.getId() + "';");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Product p) {
		try {
			myStm.executeUpdate("DELETE FROM `test`.`product` WHERE `idproduct`='" + p.getId() + "';");
			//wa.delete(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
