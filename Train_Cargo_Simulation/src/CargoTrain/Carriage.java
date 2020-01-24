package CargoTrain;

import java.util.Stack;

import Util.Cargo;

public class Carriage{
	
	protected int capacity;
	
	protected int emptySlot;

	private Stack<Cargo> cargos;
	
	protected Carriage next = null;
	
	protected Carriage prev = null;
	
	public Carriage(int capacity) {
		this.capacity = this.emptySlot = capacity;
		cargos = new Stack<Cargo>();
	}
	
	public Stack<Cargo> getStack() {
		return this.cargos;
	}
	
	public boolean isFull() {
		return this.emptySlot == 0 ? true : false;
	}
	
	public void push(Cargo cargo) {
		this.cargos.push(cargo);
	}
	
	public Cargo pop() {
		return this.cargos.pop();
	}
}