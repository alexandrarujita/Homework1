package main;
import processing.Generator;

public class mainC {

	
	public static void main(String[] args){
		Generator g = new Generator();
		Thread th = new Thread(g);
		th.start();
	}
}
