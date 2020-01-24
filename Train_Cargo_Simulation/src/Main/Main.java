package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import CargoTrain.Train;
import Util.Cargo;
import Util.Station;

public class Main{
	
	public static Station[] stats;
	public static Train train;
	public static PrintStream print;
	public static Scanner scan;
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File(args[0]);  
		File output = new File(args[1]);
		
		scan = new Scanner(input);   
		print = new PrintStream(output);
		
		readAndInitialize();
		execute();
		
	}
	
	public static void readAndInitialize() {
		int length = scan.nextInt(); 
		int capacity = scan.nextInt();
		int stationNum = scan.nextInt();
		
		train = new Train(length, capacity);
		stats = new Station[stationNum];
		
		for(int i = 0; i < stationNum; i++) {
			Station stat = new Station(i,print);
			stats[i] = stat;
		}
		
		while(scan.hasNextLine() && scan.hasNext() ) {
			int id = scan.nextInt(); int base = scan.nextInt();
			int target = scan.nextInt(); int size = scan.nextInt(); 
			Cargo cargo = new Cargo(id, base, size, target);
			stats[base].getQueue().offer(cargo);
		}
		
	}
	
	public static void execute() {
		for(Station temp : stats) {
			temp.process(train);
		}
	}
	
}