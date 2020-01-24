package Util;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import CargoTrain.Train;

public class Station{
	
	private int id;
	
	private Queue<Cargo> cargoQueue;
	
	private PrintStream printStream;
	
	public Station(int id, PrintStream printStream) {
		this.id = id;
		cargoQueue = new LinkedList<Cargo>();	
		this.printStream = printStream; 
	}
	
	public Queue<Cargo> getQueue() {
		return this.cargoQueue;
	}
	
	public void process(Train train) {
		train.unload(cargoQueue);
		System.out.println(cargoQueue);
		Queue<Cargo> send = new LinkedList<Cargo>();
		int size = cargoQueue.size();
		for(int i = 0 ; i < size; i++) {
			Cargo current = cargoQueue.poll();
			if(current.getTargetStation() == this.id) {
				printStream.println(current);
				cargoQueue.offer(current);
			}
			else {
				send.offer(current);
			}
		}
		train.load(send);
		printStream.println(this.id + " " + train.getLength());
	}
	public int getID() {
		return this.id;
	}
}