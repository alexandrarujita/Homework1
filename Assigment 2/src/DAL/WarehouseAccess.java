/**
 * 
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import model.Product;

/**
 * @author Alexandra
 *
 */
public class WarehouseAccess {
	
	private Statement myStm, myStm2;
	
	public WarehouseAccess(){
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "alexandra", null);
			myStm = (Statement) myConn.createStatement();
			myStm2= (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getQuantity(Product p) {
		  ResultSet myRes, myRes2;
		  try {
			myRes=myStm.executeQuery("SELECT idProduct,quantity FROM test.warehouse;");
			while(myRes.next()){
				if(p.getId()==myRes.getInt("idProduct") )
					return myRes.getInt("quantity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Product> getProducts(){
		ArrayList<Product> wProd= new ArrayList<Product>();
		Product p ;
		ResultSet myRes;
		try {
			myRes = myStm.executeQuery("Select idproduct,quantity from `test`.`warehouse`");
			while (myRes.next()) {
				p = new Product();
				p.setId(myRes.getInt("idproduct"));
				p.setQuantity(myRes.getInt("quantity"));
				wProd.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wProd;
	}
	
	public void add(Product p) {
		try {
			myStm.executeUpdate("INSERT INTO `test`.`warehouse` (`idProduct`, `quantity`) VALUES ('" + p.getId()
					+ "', '" + p.getQuantity() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void update(Product p) {
		try {
			ResultSet myRes=myStm2.executeQuery("Select idProduct,quantity from `test`.`warehouse`;");
			int q=0;
			while(myRes.next())
				if(p.getId()==myRes.getInt("idProduct"))
					q=myRes.getInt("quantity")-p.getQuantity();
			
			myStm.executeUpdate("UPDATE `test`.`warehouse` SET `quantity`='" + q + "' WHERE `id`='"
					+ p.getId() + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Product p) {
		try {
			myStm.executeUpdate("DELETE FROM `test`.`warehouse` WHERE `id`='"+p.getId()+"';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
