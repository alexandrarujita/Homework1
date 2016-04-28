package bank;

import java.util.List;
import java.util.Set;

public interface BankInterface {

	/**
	 * @PreCondition p != null and acc != null
	 * @PostCondtion preSizeAccounts = postSizeAccount + 1
	 */
	public void addAccForPerson(Person p, Account assocAcc);

	/**
	 * @PreCondition pers != null and sum > 0 and accID > 0
	 * @PostCondition postSum = preSum + sum
	 * @param pers
	 * @param accID
	 * @param sum
	 */
	public void depositMoney(Person pers, int accID, double sum);

	/**
	 * 
	 * @param pers
	 * @param accID
	 * @param sum
	 * @PreCondition pers != null and sum > 0 and accID > 0 && sum <= preSum
	 * @PostCondition postSum = preSum - sum
	 */
	public void withdrawMoney(Person pers, int accID, double sum);

	/**
	 * 
	 * @param pers
	 * @PreCondition pers exists && pers != null
	 * @PostCondition postNrPersons = preNrPersons - 1
	 */
	public void removePerson(Person pers);

	/**
	 * 
	 * @param pers
	 * @param accId
	 * @PreCondition pers exists && pers != null && acc belongs to pers
	 * @PostCondition preNrAcc - 1 = postNrAcc && if postNrAcc == 0
	 *                removePerson(pers)
	 */

	public void removeAccount(Person pers, int accId);

	public List<Person> seePerson();

	/**
	 * 
	 * @param pers
	 * @return
	 * @PreCondition pers != null
	 */
	public Set<Account> seeAccounts(Person pers);

	public boolean isWellFormed();

}
