package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.Customer;
import model.Product;

public class CustomerAccess {

	private Statement myStm;

	public CustomerAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "alexandra", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Customer> getCustomers() {

		Customer c;
		ArrayList<Customer> existingCustomer = new ArrayList<Customer>();
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select idcustomer,username,password,address from `test`.`Customer`");
			while (myRes.next()) {
				c = new Customer();
				c.setAdress(myRes.getString("address"));
				c.setName(myRes.getString("username"));
				c.setId(Integer.valueOf(myRes.getString("idcustomer")));
				existingCustomer.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existingCustomer;
	}

	public void add(Customer c) {
		try {
			
			myStm.executeUpdate("INSERT INTO `test`.`customer` (`username`, `password`, `address`) VALUES ('"
					+ c.getName() + "', '" + String.valueOf(c.getPassword()) + "', '" + c.getAdress() + "');");
			
			// ResultSet myRes = myStm.executeQuery("Select idcustomer from
			// `test`.`costumer`");
			// c.setId(myRes.getInt("idcustomer"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Customer c) {

		try {
			myStm.executeUpdate("UPDATE `test`.`customer` SET `address`='" + c.getAdress() + "' WHERE `idcustomer`='"
					+ c.getId() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Customer c) {

		try {
			myStm.executeUpdate("DELETE FROM `test`.`customer` WHERE `idcustomer`='" + c.getId() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
