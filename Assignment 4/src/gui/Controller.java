package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bank.Account;
import bank.Bank;
import bank.Person;
import bank.SavingAccount;
import bank.SpendingAccount;

public class Controller {

	private GUI administrate;
	private Bank bank;
	private int persId, accId;
	private String name;
	private double sum;
	//private FileOp fop;
	private final String save = "Saving";
	private final String spend = "Spending";

	public Controller() {
		administrate = new GUI();
		bank = new Bank();
		//fop = new FileOp();
		manageButtons();
	}

	private void manageButtons() {

		administrate.btnAddMoney.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setValues();

				Person pers = new Person(persId, name);
				bank.depositMoney(pers, accId, sum);
			}

		});

		administrate.btnWithdrawMoney.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setValues();

				Person pers = new Person(persId, name);
				bank.withdrawMoney(pers, accId, sum);

			}

		});

		administrate.btnAddSavingAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				setValues();

				Person pers = new Person(persId, name);
				Account acc = new SavingAccount(accId, sum, save);
				bank.addAccForPerson(pers, acc);
				//fop.write(bank.getRecord());

			}

		});

		administrate.btnAddSpendingAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setValues();

				Person pers = new Person(persId, name);
				Account acc = new SpendingAccount(accId, sum, spend);
				bank.addAccForPerson(pers, acc);
				//fop.write(bank.getRecord());

			}
		});

		administrate.btnRemoveAccount.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setValues();
				Person pers = new Person(persId, name);
				bank.removeAccount(pers, accId);
			//	fop.write(bank.getRecord());
			}

		});

		administrate.btnRemovePerson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setValues();
				Person pers = new Person(persId, name);
				bank.removePerson(pers);

			}

		});

		administrate.btnSeeAccounts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setValues();
				Person pers = new Person(persId, name);
				administrate.displayAcc();
				for (Account acc : bank.seeAccounts(pers)) {
					Object ob[] = { acc.getAccId(), acc.getMoney(), acc.getType() };
					administrate.modelAcc.addRow(ob);
				}
			}
		});

		administrate.btnSeeClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				administrate.displayClients();
				for (Person p : bank.seePerson()) {
					Object ob[] = { p.getId(), p.getName() };
					administrate.modelClients.addRow(ob);
				}

			}

		});

	}

	private void setValues() {
		persId = Integer.valueOf(administrate.idField.getText());
		accId = Integer.valueOf(administrate.accIdField.getText());
		name = administrate.nameField.getText();
		sum = Double.valueOf(administrate.sumField.getText());
	}

}
