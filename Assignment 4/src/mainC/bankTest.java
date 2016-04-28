package mainC;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bank.Account;
import bank.Bank;
import bank.Person;
import bank.SpendingAccount;

public class bankTest {
	private Bank toTest = new Bank();
	private Person pers = new Person();

	@Test
	public final void addAccTest() {
		Account acc = new SpendingAccount(1, 10, "Saving");
		int accId = 1;
		toTest.addAccForPerson(pers, acc);
		assertEquals(toTest.seeAccounts(pers).size(),1);
		toTest.depositMoney(pers, accId, 10);
		//assertEquals(toTest.getRecord().get(p)
	}

}
