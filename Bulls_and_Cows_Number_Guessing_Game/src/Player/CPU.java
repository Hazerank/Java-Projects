package Player;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Game.Game;

public class CPU implements Player {
	public int level;
	public int num;
	public ArrayList<Integer> history;
	public ArrayList<String> values;
	public ArrayList<Integer> poss;
	Random rand;
	
	public CPU(int level) {
		this.level = level;
		rand = new Random();
		history = new ArrayList<Integer>();
		values = new ArrayList<String>();
		poss = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++) {
			poss.add(i);
		}
	}
	
	public int move() {
		switch (this.level) {
		
		case(1):
			return move1();

		case(2):
			return move2();
		
		case(3):
			return move3();
		
		case(4):
			return move4();
		
		case(5):
			return move5();
		
		default:
			return 123;
		}
		
	}

	private int move1() {
		int number;
		do {
			number = rand.nextInt((int) (9 * Math.pow(10, Game.gameNumber - 1))) + (int) ( Math.pow(10, Game.gameNumber - 1));
		} while (!Game.valid(number));
		return number;
	}
	private int move2() {
		int numberTot = 0 ;
		do {
			numberTot = 0 ;
			for(int i = 0 ; i < Game.gameNumber; i++) {
				numberTot +=  poss.get(rand.nextInt(poss.size())) * ( Math.pow(10, i));
			}
		} while (!Game.valid(numberTot));
		return numberTot;	
		}
	
	private int move3() {
		int numberTot = 0 ;
		do {
			numberTot = 0 ;
			for(int i = 0 ; i < Game.gameNumber; i++) {
				numberTot +=  poss.get(rand.nextInt(poss.size())) * ( Math.pow(10, i));
			}
		} while (!Game.valid(numberTot));
		return numberTot;	
	}
	
	private int move4() {
		if(history.size() < 2) {
			int numberTot = 0 ;
			do {
				numberTot = 0 ;
				for(int i = 0 ; i < Game.gameNumber; i++) {
					numberTot +=  poss.get(rand.nextInt(poss.size())) * ( Math.pow(10, i));
				}
			} while (!Game.valid(numberTot));
			return numberTot;	
		}
		ArrayList<Integer> answer = deepThought(0);
		return answer.get((rand.nextInt(answer.size())));
		}
	
	private int move5() {
		if(history.size() < 2) {
			int numberTot = 0 ;
			do {
				numberTot = 0 ;
				for(int i = 0 ; i < Game.gameNumber; i++) {
					numberTot +=  poss.get(rand.nextInt(poss.size())) * ( Math.pow(10, i));
				}
			} while (!Game.valid(numberTot));
			return numberTot;	
		}
		ArrayList<Integer> answer = deepThought(0);
		return answer.get((rand.nextInt(answer.size())));
		
		
	}
	
	public void addHistory(int num) {
		this.history.add(num);
	}
	
	public void getHistory() {
		if(history.isEmpty()) return;
		System.out.println("Computer's old guesses are like that: ");
		Iterator<Integer> itera = history.iterator();
		Iterator<String> iterva = values.iterator();
		while(itera.hasNext()) {
			System.out.println(itera.next() + " -->   " + iterva.next());
		}
		System.out.println();
	}
	
	public void addValue(String value) {
		this.values.add(value);
		if(this.level != 1) think(value);
	}

	private void think(String value) {
		if(value.equals("0")) {
			String check = "" + history.get(history.size()-1);
			for(int i = 0 ; i < check.length();i++) 
				poss.remove((Integer)(check.charAt(i) - 48));
		}
		int tot = 0;
		for(int i = 0 ; i < value.length() ; i++) {
			char c = value.charAt(i);
			if("+-".contains("" + c)) continue;
			tot += (c - 48);
		}
		boolean find = (tot == Game.gameNumber) ? true : false;
		if(find){ 
			String check = "" + history.get(history.size()-1); 	poss.clear();
			for(int i = 0 ; i < check.length();i++) 
				poss.add((check.charAt(i) - 48));
		}
		
	}
	private ArrayList<Integer> deepThought(int loc) {
		
		ArrayList<Integer> result= new ArrayList<>();
		String tempVal = values.get(loc);
		int lowe = (int) Math.pow(10 , Game.gameNumber - 1);
		for(int i = lowe ; i < lowe * 10-1  ; i++) {
			if(Game.valid(i) && Game.process(history.get(loc), i ).equals(tempVal)) {
				result.add(i);
			}
		}
		if(history.size() != loc + 1) result.retainAll(deepThought(loc+1));
		return result;
		
	}
	
	@Override
	public void clearHistory() {
		this.history.clear();
		this.values.clear();
		this.poss.clear();
		for(int i = 0 ; i < 10 ; i++) {
			poss.add(i);
		}
	}

	@Override
	public void win(Player player) {
		System.out.println("Not this time, but no doubt that Computers are clever beings :)");	
		System.out.println("Computer's number was: " + num + "\n");
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
