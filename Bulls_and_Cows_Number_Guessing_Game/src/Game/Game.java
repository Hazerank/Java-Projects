package Game;
import java.util.ArrayList;
import java.util.Scanner;

import Player.Player;
import Player.User;

public class Game {
	public static int userWin = 0;
	public static int cpuWin = 0;
	public static boolean play = true;
	public static Scanner scan;
	public int userNum;
	public int cpuNum;
	public static int gameNumber;
	
	
	public Game(Scanner scan) {
		Game.scan = scan;
	}
	
	public static  String starting() {
		System.out.println("Welcome to the White Lotus !!!");
		System.out.println();
		System.out.println("I hope you will enjoy the journey..");
		System.out.println();
		System.out.println("You may enter 'Start' in order to play game");
		System.out.println("If you don't know rules or not sure, please enter 'help' to reach guide");
		System.out.println("If you want to exit, enter 'Exit'.");
		String input;
		do {
			input = scan.next().toLowerCase();
			if(input.contains("start")) 
				return "start";
			else if(input.contains("help"))
				return "help";
			else if(input.contains("exit")) {
				return "exit";
			}
			System.out.println("Please enter a valid input:");
			scan.nextLine();
		}	while(true);
		
	}
	
	public static String help() {
		System.out.println("\n"
				+ "The game is usually played with 4 digits, but can also be played with 3 or any other number of digits.\r\n" + 
				"\r\n" + 
				"The players each determine a 4-digit secret number. The digits must be all different. \n"
				+ "Then, in turn, the players try to guess their opponent's number who gives the number of matches. \n"
				+ "If the matching digits are in their right positions, they are \"+\", if in different positions, they are \"-\". Example:\r\n" + 
				"\r\n" + 
				"Secret number: 4271\r\n" + 
				"Opponent's try: 1234\r\n" + 
				"Answer: 1 '+' and 2 '-'. (The '+' is \"2\", the '-' are \"4\" and \"1\".)\r\n" + 
				"The first one to reveal the other's secret number in the least number of guesses wins the game.");
		System.out.println();
		System.out.println("There is a bar in the guessing screen. You can adjust number with the comments \"add\" , \"delete\" and \"clear\"."
				+ "\nAfter comments \"add\" and \"delete\", insert the numbers and than type \"-\" at the and in order to finalize comment section.\n"
				+ "In order to reset bar, just type \"clear\". \n"
				+ "You can continue guessing.");
		System.out.println();
		System.out.println("You can write 'Start' to play game now if you'd understood.");
		System.out.println("If you couldn't get the rules still and/or if you think that you are a \"funny\" person and printing 'help' makes you happier"
				+ " please enter 'help' to write rules again.");
		System.out.println("If you want to exit, enter 'Exit' anytime you want. This closes the game !");
		String input;
		do {
			input = scan.next().toLowerCase();
			if(input.contains("start")) 
				return "start";
			else if(input.contains("help"))
				return "help";
			else if(input.contains("exit")) {
				return "exit";
			}
			System.out.println("Please enter a valid input:");
			scan.nextLine();
		}	while(true);
	}
	
	public static String setName() {
		System.out.println("So what is your name: ");
		String name = scan.next();
		System.out.println("Hello " + name + ". Have a good game !! ");
		return name;
	}
	
	public static int setDifficulty() {
		System.out.println("Have you ever played this game ?");
		System.out.println("Please choose a difficulty level :\n 1 - I don't know game and why am I here? \n 2 - Well, I read the 'help' section and came here\n"
				+ " 3 - I know this game, C'mon !\n 4 - I don't think this computer can beat me\n 5 - I don't think \"any\" computer can beat me ! ");
		int num = 0;
		while(scan.hasNext()) {
			if(!scan.hasNextInt()) {
				System.out.println("Please choose one of them.");
				scan.nextLine();
				continue;
			}
			num = scan.nextInt();
			if(num > 0 && num < 6) {
				break;
			}
			else {
				System.out.println("Please choose one of them.");
				continue;
			}
			
			
		}
		return num;
	}

	public boolean play(Player user,Player cpu) {
		
		System.out.println("Please choose your number: ");
		userNum = user.move();
		if(userNum == -1) return true; if(userNum == -2) return false;
		user.setNum(userNum);
		cpu.setNum(cpuNum = cpu.move());
		
		while(play) {
			
			user.getHistory();
			System.out.println("Please make a guess: \n");
			System.out.println(((User)user).getWork() + " (add)  (delete)  (clear) (-)" + "\n");
			int move = user.move();
			if(move == -1) return true; if(move == -2) return false;
			user.addHistory(move);
			String value = process(move,cpuNum);
			System.out.println(move +" ==> "+ value);
			user.addValue(value);
			play = (move != cpuNum);
			
			if(!play) {
				user.win(cpu);
				userWin++;
				break;
			}
			
			System.out.println();
			cpu.getHistory();
			move = cpu.move();
			cpu.addHistory(move);
			value = process(move,userNum);
			System.out.println("Computer's move is: \n" +move + " ==> " + value );
			cpu.addValue(value);
			play = (move != userNum);
			
			if(!play) {
				cpu.win(user);
				cpuWin++;
				break;
				}

			System.out.println();
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}
		play = askReplay();
		return play;
	}
	
	public static boolean valid(int num) {
		String check = "" + num;
		if(check.length() != gameNumber) return false;
		ArrayList<Character> list = new ArrayList<>();
		for(int i = 0 ; i < check.length();i++) {
			if(list.contains(check.charAt(i))) return false;
			list.add(check.charAt(i));
		}
		return true;
	}
	
	private boolean askReplay() {
		System.out.println("Do you want to play again ?");
		do {
			String input = scan.next().toLowerCase();
			if(input.contains("yes")) 
				return true;
			else if(input.contains("no"))
				return false;
			System.out.println("Please enter a valid input:");
			scan.nextLine();
		}	while(true);
	}
	
	
	public static String process(int num, int enemy) {
		String check = "" + num;
		String key = "" + enemy;
		int plus = 0;
		int minus = 0;
		for(int i = 0 ; i < gameNumber; i++) {
			char first = check.charAt(i);
			for(int k = 0; k < gameNumber; k++) {
				char second = key.charAt(k);
				if(first == second) {
					if(i == k) plus++;
					else minus++;
				}
			}
		}
		if(minus == 0 && plus == 0) return "0";
		else if(minus == 0) return "+" + plus;
		else if(plus == 0) return "-" + minus;
		else return "+" + plus + " -" + minus;
	}
	
	
	public static void replay(Player player1,Player player2) {
		player1.clearHistory();
		player2.clearHistory();
		((User)player1).clearWork();
	}
	
	public void setNum() {
		System.out.println("With how many digit do you play ? ");
		while(scan.hasNext()) {
			if(!scan.hasNextInt()) {
				System.out.println("Please enter a valid number.");
				scan.nextLine();
				continue;
			}
			int num = scan.nextInt();
			if(num > 0 && num < 11 ) {
				gameNumber = num;
				break;
			}
			else {
				System.out.println("Please enter a valid number");
				scan.nextLine();
				continue;
			}
		}
	}
	
	public void exit(String name) {
		if(userWin == 0 && cpuWin == 0) {
			System.out.println("Maybe this time you have choosed not to play but.."
					+ "\nWho knows ?\n\nHave a good day my fellow friend..");
			return;
			
		}
		
		System.out.println("Thank you for playing game :)\n");
		System.out.println("Totally " + ((int)userWin + (int)cpuWin) + " games have played !");
		System.out.println("You won " + userWin + " of them.");
		System.out.println("Computer won " + Game.cpuWin + " of them.");
		if(cpuWin > userWin) {
			System.out.println("\nBetter luck next time, " + name);
		}
		else {
			System.out.println("\nIt was a pleasure to play with you " + name +".. !!" );
		}
	}
}
