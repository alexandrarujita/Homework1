package bank;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int accId;
	protected double money;
	protected String type;
	
	public Account(){
		
	}
	
	public Account(int accId, double money, String type) {
		super();
		this.accId = accId;
		this.money = money;
		this.type = type;
	}

	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public abstract void addMoney(double money);
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract void withdrawMoney(double money);
	
	@Override
	public String toString() {
		return "Account [accId=" + accId + ", money=" + money + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accId;
		result = prime * result ;
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accId != other.accId)
			return false;
		return true;
	}
	
	
	

}
