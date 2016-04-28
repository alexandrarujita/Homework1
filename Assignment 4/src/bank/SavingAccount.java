package bank;

public class SavingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int interest = 5;
	private int noOfWithdraws;

	public SavingAccount(int accId, double sum, String type) {
		super(accId, sum, type);
	}

	public SavingAccount() {

	}

	@Override
	public void addMoney(double money) {

		if (money < 1000)
			notifyObservers("Sum to deposit is to small for saving account!");
		else
			this.money += money + money * interest / 100;
		setChanged();
		notifyObservers("Sum " + money + " added to saving account " + this.getAccId());

	}

	@Override
	public void withdrawMoney(double money) {

		if (getNoOfWithdraws() > 3)
			notifyObservers("You reached the maximum nr of withdraws for this 6 months !");
		else {
			this.money -= money;
			setChanged();
			notifyObservers("Sum " + money + " withdraw form the saving account " + this.getAccId());
		}

	}

	public int getNoOfWithdraws() {
		return noOfWithdraws;
	}

	public void setNoOfWithdraws(int noOfWithdraws) {
		this.noOfWithdraws = noOfWithdraws;
	}

}
