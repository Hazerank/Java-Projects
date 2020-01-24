package CargoTrain;

import java.util.Queue;

import Util.Cargo;

public class Train{
	
	private final int carCapacity;
	
	private int length;
	
	private Carriage head;
	
	private Carriage tail;
	
	public Train(int length, int carCapacity) {
		this.length = length;
		this.carCapacity = carCapacity;	
		createTrain(length, carCapacity);
	
	}
	
	public int getLength() {
		return this.length;
	}
	
	private void createTrain(int length, int carCapacity) {
		if(length == 1) {
			Carriage carriage = new Carriage(carCapacity);
			head = tail = carriage;
			return;
		}
		
		Carriage carriage = new Carriage(carCapacity);
		Carriage current = head = carriage;
		
		for(int i = 0 ; i < length - 1 ; i++) {
			Carriage temp = new Carriage(carCapacity);
			current.next = temp;
			temp.prev = current;
			current = temp;
		}
		tail = current;
	}
	
	public void load(Queue<Cargo> cargos) {
		for(Cargo cargo: cargos){
			Carriage current = head;
			if(current == null) {
				Carriage carriage = new Carriage(carCapacity);
				head = tail = carriage;
				carriage.push(cargo);
				carriage.emptySlot -= cargo.getSize();
				this.length++;
				continue;
			}
			while(current != null) {
				if(cargo.getSize() <= current.emptySlot) {
					current.push(cargo);
					current.emptySlot -= cargo.getSize();
					break;
				}
				else {
					current = current.next;
				}
			}
			if(current == null) {
				Carriage carriage = new Carriage(carCapacity);
				tail.next = carriage;
				carriage.prev = tail;
				tail = carriage;
				carriage.push(cargo);
				carriage.emptySlot -= cargo.getSize();
				this.length++;
			}	
		}
		Carriage temp = head;
		while(temp != null) {
			if(temp.emptySlot == temp.capacity) {
				if(temp == head) {
					head = tail = null;
					this.length = 0;
					break;
				}
				if(temp == tail) {
					tail = temp.prev;
					temp.prev.next = null;
					this.length--;
				}
				else {
					temp.prev.next = temp.next;
					temp.next.prev = temp.prev;
					this.length--;
				}
			}
			temp = temp.next;
		}
	}
	
	
	public void unload(Queue<Cargo> cargos) {
		Carriage current = head;
		while(current != null) {
			while(!current.getStack().isEmpty()) {
				cargos.offer(current.getStack().pop());
			}
			current.emptySlot = current.capacity;
			current = current.next;
		}
	}
	
	public void printTrain() {
		Carriage current = head;
		System.out.print("[ ");
		while(current.next != null) {
			System.out.print(current + ", ");
			current = current.next;
		}
		System.out.print(current + " ]");
	}
	
}