package processing;
import java.io.IOException;
import java.util.ArrayList;

import others.FileOp;
import others.GUI;

public class Generator implements Runnable {

	private int finishTime; // 20:00
	private int maxProcessTime, minProcessTime, nrOfRegisters;
	private int[] avgWaitingTime, clients;
	private int openRegister = 15;
	private FileOp fop = new FileOp();
	private ArrayList<CashRegister> register;
	// private ArrayList<Task> peakHour;
	private GUI frame;

	public Generator() {
		register = new ArrayList<CashRegister>();
		// peakHour = new ArrayList<Task>();
		frame = new GUI();

		finishTime = fop.getFinishTime();
		maxProcessTime = fop.getMaxTime();
		minProcessTime = fop.getMinTime();
		nrOfRegisters = fop.getNrQueues();

		avgWaitingTime = new int[nrOfRegisters];
		clients = new int[nrOfRegisters];

		for (int i = 0; i < nrOfRegisters; i++) {
			CashRegister cr = new CashRegister();
			register.add(cr);
		}

	}

	@Override
	public void run() {
		int currTime = 0;
		int pos = 0;
		while (currTime < finishTime) {
			currTime++; // 5 min
			int processTime = (int) (Math.random() * (maxProcessTime - minProcessTime) + minProcessTime);
			Task t = new Task(currTime, processTime);

			if (totalTime(register.get(pos)) > openRegister && pos < nrOfRegisters - 1) {
				int minCashReg = minProcessTime(register, pos);
				if ((pos < nrOfRegisters - 1) && totalTime(register.get(minCashReg)) > openRegister) {
					pos++;
					register.get(pos).processTask(t);
					clients[pos]++;
					avgWaitingTime[pos] += totalTime(register.get(minCashReg));
				} else {
					register.get(minCashReg).processTask(t);
					clients[minCashReg]++;
					avgWaitingTime[pos] += totalTime(register.get(minCashReg));
				}
			} else {
				pos = minProcessTime(register, pos);
				register.get(pos).processTask(t);
				clients[pos]++;
				avgWaitingTime[pos] += totalTime(register.get(pos));
			}

			for (int i = 0; i <= pos; i++)
				frame.chooseQueue(i, register.get(i).getServer().getTasks());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i <= pos; i++) {
			while (!register.get(i).getServer().isEmpty()) {
				frame.chooseQueue(i, register.get(i).getServer().getTasks());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		write();
	}
	
	
	private void write(){
		for (int i = 0; i < nrOfRegisters; i++) {
			try {
				if (clients[i] != 0)
					fop.write(i, avgWaitingTime[i] / clients[i], clients[i]);
				else
					fop.write(i, 0, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private int totalTime(CashRegister cr) {
		int time = 0;
		for (Task t : cr.getServer().getTasks())
			time += t.getProcessTime();
		return time;

	}

	private int minProcessTime(ArrayList<CashRegister> cr, int pos) {
		int min = Integer.MAX_VALUE, minCrt = 0, posMin = 0, i = 0;

		for (CashRegister c : cr) {
			if (pos < nrOfRegisters) {
				if (!c.getServer().isEmpty()) {
					minCrt = totalTime(c);
					if (minCrt < min) {
						min = minCrt;
						posMin = i;
					}
				}
			} else {
				minCrt = totalTime(c);
				if (minCrt < min) {
					min = minCrt;
					posMin = i;
				}
			}
			i++;
		}
		return posMin;
	}

}
