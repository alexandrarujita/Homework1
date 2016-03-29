package BLL;

import java.util.ArrayList;

import DAL.CustomerAccess;
import model.Customer;

public class CustomerBLL {

	private CustomerAccess custAcc = new CustomerAccess();

	public boolean okUsername(Customer cust) {

		if (existingUsername(cust))
			return true;
		return false;
	}

	public boolean existingUsername(Customer newCust) {
		ArrayList<Customer> existingC = (ArrayList<Customer>) custAcc.getCustomers().clone();

		for (Customer c : existingC)
			if (c.getName().equals(newCust.getName())){
				newCust.setAdress(c.getAdress());
				newCust.setId(c.getId());
				return true;
			}
		return false;

	}
	
	public void updateAddress(Customer c){
		custAcc.update(c);
	}

	public void addUsername(Customer c) {
		custAcc.add(c);
	}

	public void remove(Customer c) {
		custAcc.delete(c);

	}

}
