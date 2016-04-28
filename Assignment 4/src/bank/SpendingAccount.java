package bank;

public class SpendingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SpendingAccount(int accId, double sum, String type) {
		super(accId, sum, type);
	}

	@Override
	public void addMoney(double money) {
		this.money += money;
		setChanged();
		notifyObservers("Sum " + money + " added to spending account " + this.getAccId());

	}

	@Override
	public void withdrawMoney(double money) {
		this.money -= money;
		setChanged();
		notifyObservers("Sum " + money + " withdraw form the spending account " + this.getAccId());

	}

}
