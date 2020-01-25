package Player;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Game.Game;
import Runnable.Main;

public class User implements Player {
	public static Scanner scan;
	public String name;
	public ArrayList<Integer> history;
	public ArrayList<String> values;
	public ArrayList<Integer> work;
	public int num;
	
	public User(String name,Scanner scan) {
		this.name = name;
		User.scan = scan;
		history = new ArrayList<Integer>();
		values = new ArrayList<String>();
		work = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++) {
			work.add(i);
		}
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int move() {
		int num = 0;
		while(scan.hasNext()) {

			while(!scan.hasNextInt()) {
				String temp = scan.next().toLowerCase();
				if(temp.equals("restart") || temp.equals("res")) {
					return -1;
				}
				if(temp.equals("exit")) return -2;
				if(temp.equals("clear")) {
					clearWork();
					System.out.println(work + "\n");
					scan.nextLine();
				}
				else if(temp.equals("add")) {
					while(true) {
						while(scan.hasNextInt()) {
							addWork(scan.nextInt());
							System.out.println();
						}
						if(scan.next().equals("-")) {
							System.out.println(work + "\n");
							break;
						}
					}
				}
				
				else if(temp.equals("delete") || temp.equals("temp")) {
					while(true) {
						while(scan.hasNextInt()) {
							work.remove((Integer)scan.nextInt());
						}
						if(scan.next().equals("-")) {
							System.out.println(work + "\n");
							break;
						}
					}
				}
				
				else {
					System.out.println("Please enter a valid number.");
					scan.nextLine();
				}
			}
			num = User.scan.nextInt();
			if(Game.valid(num) ) {
				break;
			}
			else 
				System.out.println("Please enter a valid number");
			scan.nextLine();
		}
		return num;
	}
	
	private void addWork(int n) {
		if(work.contains(n) || n > 9 || n < 0) return;
		for(int i = 0; i < work.size() ; i++) {
			if(n < work.get(i)) {
			work.add(i, n);
			break;
			}
		}
	}


	public void getHistory() {
		if(history.isEmpty()) return;
		System.out.println("Your old guesses are like that: ");
		Iterator<Integer> itera = history.iterator();
		Iterator<String> iterva = values.iterator();
		while(itera.hasNext()) {
			System.out.println(itera.next() + " --> " + iterva.next());
		}
		System.out.println();
	}
	
	public void addValue(String value) {
		this.values.add(value);
	}
	
	public void clearWork() {
		work.clear();
		for(int i = 0 ; i < 10 ; i++) {
			work.add(i);
		}
	}

	@Override
	public void clearHistory() {
		this.history.clear();
		this.values.clear();
		
	}

	public ArrayList<Integer> getWork() {
		return work;
	}
	@Override
	public void addHistory(int num) {
		this.history.add(num);
	}

	@Override
	public void win(Player player) {
		if(player instanceof CPU) {
			System.out.println("Wow, you did it, Nice Job !!");
		}
		else
			System.out.println("Yey, you did it " + this.name + "! Congratulations !!");
	}

}
