package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.Order;
import model.Product;

public class OrderAccess {

	private Statement myStm;

	public OrderAccess() {
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "alexandra", null);
			myStm = (Statement) myConn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(Order p) {
		try {
			for (Product prod : p.getProdList())
				myStm.executeUpdate(
						"INSERT INTO `test`.`order` (`idorder`, `customerID`, `productID`, `quantity`) VALUES ('"
								+ p.getId() + "', '" + p.getC().getId() + "', '" + prod.getId() + "', '"
								+ prod.getQuantity() + "');");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Order p) {
		// UPDATE `test`.`order` SET `customerID`='5' WHERE `idIntern`='2';
		try {
			ResultSet myRes=myStm.executeQuery("Select idIntern,idorder from `test`.`order`; ");
			int k=0;
			while(myRes.next()){
				k++;
				if(p.getId()==myRes.getInt("idorder"))
					break;
			}
				myStm.executeUpdate("UPDATE `test`.`order` SET `quantity`='" + p.getProdList().size()
						+ "' WHERE `idIntern`='" +k+ "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Order p) {
		try {
			ResultSet myRes=myStm.executeQuery("Select idIntern,idorder from `test`.`order`; ");
			int k=0;
			while(myRes.next()){
				k++;
				if(p.getId()==myRes.getInt("idorder"))
					break;
			}
				myStm.executeUpdate("DELETE FROM `test`.`order` WHERE `idIntern`='" +k+ "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
