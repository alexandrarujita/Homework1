package files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bank.Account;
import bank.Person;

public class FileOp {

	public FileOp() {

	}

	public void write(Map<Person, Set<Account>> bankRecord) {
		try {
			FileOutputStream fileOut = new FileOutputStream("Bank.ser", true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(bankRecord);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Person, Set<Account>> read() {
		FileInputStream fileIn;
		Map<Person, Set<Account>>  b = new HashMap<Person, Set<Account>>();
		try {
			fileIn = new FileInputStream("Bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			b = (HashMap<Person, Set<Account>>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;

	}

}
