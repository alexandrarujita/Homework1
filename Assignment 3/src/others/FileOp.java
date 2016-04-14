package others;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileOp {

	private int finishTime, maxTime, minTime, nrQueues, threshold;

	public FileOp() {
		read();
	}

	private void read() {
		String fileName = "data.txt";
		int values[] = new int[5];
		int i = 0;
		try {

			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			while ((line = bufferReader.readLine()) != null) {
				values[i] = Integer.valueOf(line);
				i++;
			}

			setFinishTime(values[0]);
			setMaxTime(values[1]);
			setMinTime(values[2]);
			setNrQueues(values[3]);
			setThreshold(values[4]);

			bufferReader.close();
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}

	}

	public void write(int cashReg, int avgTime, int clients ) throws IOException {

		File fout = new File("Summary.txt");
		if(!fout.exists() )
			fout.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(fout, true));
		out.append("Cash register no  " + cashReg + " had "+clients+" customers, with average waiting time: "+avgTime+" \n");
		out.println();
		out.close();
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public int getNrQueues() {
		return nrQueues;
	}

	public void setNrQueues(int nrQueues) {
		this.nrQueues = nrQueues;
	}

	public int getMinTime() {
		return minTime;
	}

	public void setMinTime(int minTime) {
		this.minTime = minTime;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
