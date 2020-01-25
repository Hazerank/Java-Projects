package Runnable;
import java.util.Scanner;

import Game.Game;
import Player.CPU;
import Player.Player;
import Player.User;

public class Main {
	static Game game;
	static Scanner scan = new Scanner(System.in);
	static Player user;
	static Player cpu;
	static boolean play = true;

	public static void main(String[] args) {
		game = new Game(scan);
		
		String input = Game.starting(); // input is what user want to do, there are 3 option as in switch.
		
		switch (input){
		
		case("exit"):
			game.exit("");
			return;
		case("help"):
			String temp;
			do{
				temp = Game.help();
			} while(temp.equals("help"));
			
			if(temp.equals("exit")) {
				game.exit("");
				return;
			}
			
			else input = temp; // Which is "start".
			
		case("start"):
			
			String name = Game.setName(); // Determines User's name.
			user = new User(name,scan);
			
			game.setNum();
			
			int difficulty = Game.setDifficulty(); // Determines Cpu's difficulty.
			cpu = new CPU(difficulty);
			
			while(play) {
				play = game.play(user,cpu);
				if(play) Game.replay(user,cpu);
			}
			
			game.exit(name);
			
			
		}
	}

}
