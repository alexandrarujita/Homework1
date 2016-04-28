package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import files.FileOp;

public class Bank implements BankInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Person, Set<Account>> record;
	// private final String save = "Saving";
	private final String spend = "Spending";
	private FileOp fop = new FileOp();

	public Bank() {
		record = fop.read();
	}

	public void addAccForPerson(Person p, Account assocAcc) {

		assert p != null : "Person is null";
		int preSizeAccount;
		assert isWellFormed() : "The bank is not consistent";

		if (record.containsKey(p)) {
			Set<Account> acc = new HashSet<Account>();
			acc = record.get(p);
			preSizeAccount = acc.size();
			acc.add(assocAcc);
			record.put(p, acc);
		} else {
			Set<Account> newAcc = new HashSet<Account>();
			preSizeAccount = 0;
			newAcc.add(assocAcc);
			record.put(p, newAcc);
		}

		int postSizeAccount = record.get(p).size();
		assert preSizeAccount + 1 == postSizeAccount : "Account not added";
		assert isWellFormed() : "The bank is not consistent";
		fop.write(record);
		assocAcc.addObserver(p);
	}

	@Override
	public void depositMoney(Person pers, int accID, double sum) {

		assert isWellFormed() : "The bank is not consistent";
		assert pers != null && sum >= 0 && accID > 0 : "Something went wrong";
		double preSum = 0, postSum = 0;

		if (record.containsKey(pers)) {
			Set<Account> accounts = record.get(pers);
			for (Account a : accounts) {
				if (a.getAccId() == accID) {
					preSum = a.getMoney();
					if (a.getType().equals(spend)) {
						a.addMoney(sum);
						postSum = a.getMoney();
						assert preSum + sum == postSum : "Sum was not added";
					} else {
						a.addMoney(sum);
						assert sum > 1000 : "Amount is to small";
						postSum = a.getMoney();
					}
				}
			}
		}
		fop.write(record);
		// assert preSum + sum == postSum : "Sum was not added";
		assert isWellFormed() : "The bank is not consistent";

	}

	@Override
	public void withdrawMoney(Person pers, int accID, double sum) {
		double preSum = 0, postSum = 0;
		assert isWellFormed() : "The bank is not consistent";
		assert pers != null && sum >= 0 && accID > 0 : "Something went wrong";

		if (record.containsKey(pers)) {
			Set<Account> accounts = record.get(pers);
			for (Account a : accounts) {
				if (a.getAccId() == accID) {
					preSum = a.getMoney();
					assert preSum >= sum : "Not enough money in account";
					a.withdrawMoney(sum);
					postSum = a.getMoney();
				}
			}
		}
		assert preSum - sum == postSum : "Error while withdrawing";
		assert isWellFormed() : "The bank is not consistent";
		fop.write(record);
	}

	@Override
	public void removePerson(Person pers) {
		assert pers != null : "Person is null";
		assert isWellFormed() : "The bank is not consistent";
		int preSize = record.size();

		record.remove(pers);

		int postSize = record.size();
		assert postSize == preSize - 1 : "Person was not removed";
		fop.write(record);
	}

	@Override
	public void removeAccount(Person pers, int accId) {
		assert pers != null && accId > 0 : "Person is null";
		assert isWellFormed() : "The bank is not consistent";
		int preNrAcc = record.get(pers).size();

		if (record.containsKey(pers)) {
			for (Account a : record.get(pers)) {
				if (a.getAccId() == accId)
					record.get(pers).remove(a);
				if (record.get(pers).size() == 0)
					removePerson(pers);
			}
		}
		
		int postNrAcc = record.get(pers).size();
		assert preNrAcc - 1 == postNrAcc || record.get(pers) == null : "Account not removed";
		fop.write(record);
	}

	@Override
	public String toString() {
		return "Bank [record=" + record + "]";
	}

	@Override
	public boolean isWellFormed() {

		for (Entry<Person, Set<Account>> entry : record.entrySet()) {
			if (entry.getValue() == null || entry.getValue().isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public List<Person> seePerson() {

		List<Person> persons = new ArrayList<Person>();
		for (Entry<Person, Set<Account>> entry : record.entrySet())
			persons.add(entry.getKey());
		return persons;
	}

	@Override
	public Set<Account> seeAccounts(Person pers) {

		Set<Account> accounts = new HashSet<Account>();
		for (Account entry : record.get(pers))
			accounts.add(entry);
		return accounts;
	}

	public Map<Person, Set<Account>> getRecord() {
		return record;
	}

	public void setRecord(Map<Person, Set<Account>> record) {
		this.record = record;
	}

}
