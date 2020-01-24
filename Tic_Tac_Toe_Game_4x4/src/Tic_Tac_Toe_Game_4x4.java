import java.util.*;
import java.io.*;
public class Tic_Tac_Toe_Game_4x4 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner user = new Scanner(System.in); /* Scanner that reads from console */
		Random rand = new Random(); /* This random to determine who will start the game. */
		String tempTable = "", table = "", load, token, replay; /* "table" is the actual game board, "tempTable" is to process moves to the table, it is a temporary table, "load","token" and "replay" are for the inputs that user enters*/
		int userWin = 0, cpuWin = 0, userPosition = 0, cpuPosition = 0; /* "userWin" and "cpuWin" holds the numbers of winning games, "userPosition" and "cpuPosition" holds the values of the positions of the sides. */
		boolean loaded = false, starter, toke = true; /* If load process have done, "loaded" becomes true. "starter" is to determine who will start the game and "toke" is to choose token. True means user is 'x' and false means user is 'o'.It only affects the draw method. */
		System.out.println("Welcome to the XOX Game.");
		do {
			System.out.print("Would you like to load the board from file or create a new one? (L or C) ");
			load = user.next().toUpperCase();
			user.nextLine();
		} while (!(load.equals("L") || load.equals("C"))); /* This do-while loop ensure that the user enter one of the valid inputs.*/
		if (load.equals("L")) {
			table = loadProcess(); /* If user choose to load a file, "loadProcces" method process the loaded file*/
			loaded = !table.equals("eeeeeeeeeeeeeeee") ? true : false; /* The "loaded" boolean turns true if there is a successful load process  */
		} else
			table = "eeeeeeeeeeeeeeee"; /* This is creating a new empty game */
		do {
			System.out.print("Enter your symbol: (X or O) ");
			token = user.next().toUpperCase();
			user.nextLine();
		} while (!(token.equals("X") || token.equals("O")));  /* This do-while loop ensure that the user enter one of the valid inputs.*/
		if (token.equals("O")) {
			if (loaded == true) { /* If it is a loaded game and player choose to play "O", I need to swap 'x's and 'o' in the file in order to play game */
				String t = "";
				for (int i = 0; i < 16; i++) {
					char let = table.charAt(i);
					if (let == 'x')   	   t += 'o';
					else if (let == 'o')   t += 'x';
					else				   t += let;
				}
				table = t;
			}
			toke = false; /* In loaded game, "toke" turns false*/
		}
		System.out.printf("You are player %s and the computer is player %s.\n", toke ? "X" : "O", toke ? "O" : "X");
		while (true) {
			starter = rand.nextBoolean(); /* Starter is a boolean which determines which player will start game. If it is true, user will start, vice versa. It is determined randomly. */
			if (loaded == true) { /* This if determine the first side in case of it is a loaded game regarding to the numbers of 'x's and'o's.*/
				int xNum = 0, oNum = 0;
				for (int i = 0; i < 16; i++) {
					char let = table.charAt(i);
					if (let == 'x')
						xNum++;
					else if (let == 'o')
						oNum++;
				}
				if (xNum > oNum)
					starter = false;
				else if (oNum > xNum)
					starter = true;
			}
			if (starter) {
				System.out.println("You will start: ");
				draw(table, toke); /* This draws an empty board*/
			} else
				System.out.println("Computer will start: ");
			while (true) {
				if (starter) { /* If starter is false, which means computer will start, this user turn part will be skipped.*/ 
					System.out.print("Enter coordinates: ");
					userPosition = chooseOfUser(table); /* userPosition will be determined by the method "chooseOfUser". */
					tempTable = process(userPosition, table, 'x'); /* userPosition will be processed to the table by the method "process".*/
					int isFinished = control(tempTable,toke); /* I control the table in case of any finish situation with method "control" and equalize its return to isFinished variable*/
					if ( isFinished !=-1) { /* I behave regarding to value of isFinished here */
						userWin += isFinished > 0 ? 1:0; /* If the game finished by a winning, I count it with userWin.*/
						break;
					}
					table = tempTable; /* I here equalize tempTable value to table value because my game playing on table string.*/
				}
				cpuPosition = chooseOfCpu(table, rand); /* cpuPosition will be determined by the method "chooseOfCpu". */
				tempTable = process(cpuPosition, table, 'o');  /* cpuPosition will be processed to the table by the method "process".*/
				int isFinished =control(tempTable,toke); /* I control the table in case of any finish situation with method "control" and equalize its return to isFinished variable*/
				if ( isFinished !=-1) {/* I behave regarding to value of isFinished here */
					cpuWin += isFinished > 0 ? 1:0; /* If the game finished by a winning, I count it with cpuWin. */
					break;				
				}
				table = tempTable; /* /* I here equalize tempTable value to table value because my game playing on table string. **/
				draw(table, toke); /* I draw the current table to the console */ 
				starter = true; /* I return true in case of computer starts to make user be able to play his/her turn */
			}
			do {
				System.out.print("Do you want to play again? (Y or N) ");
				replay = user.next().toUpperCase();
				user.nextLine();
			} while (!(replay.equals("Y") || replay.equals("N"))); /* This do-while loop ensure that the user enter one of the valid inputs.*/
			if (replay.equals("Y")) {
				loaded = false; /* I reset the situation of loaded to its default because in new game, table will be empty*/ 
				table = "eeeeeeeeeeeeeeee"; /* I clear the table.*/
				continue; /* It goes back to the beginning of the game.*/
			} else {
				System.out.printf("You: %d Computer: %d\n", userWin, cpuWin); /* Program print the statistics to the console.*/
				System.out.println("Thanks for playing! "); /* Closing...*/ 
				break;
			}
		}
	}
	/* This method process the load file. It controls whether the file can be read or the data in the file is appropriate to play.
	 * If the file is in valid form, method returns the string that represent the board */
	public static String loadProcess() throws FileNotFoundException {
		Scanner user = new Scanner(System.in);
		String result = ""; /* This is the table that is read from file.*/
		boolean suitable = true; /* This boolean suitable is true when the file is suitable to process.*/
		System.out.print("Please enter the file name: ");
		File file = new File(user.nextLine()); /* This is the file that will be examined.*/
		while ( true) {
			while (!file.canRead() || !suitable ) {
				if(suitable) 
					System.out.println("File not found.");
				else
					System.out.println("The board is not appropriate to play!");
				System.out.print("You may try again to load a file or you can create a new game ( <file name> or (C) ): ");
				String input = user.nextLine();
				if (input.equalsIgnoreCase("c")) return "eeeeeeeeeeeeeeee";
				file = new File(input);
				suitable = true;
			}
			Scanner input = new Scanner(file);
			int numX = 0, numO = 0, numSlash =0; /* These are counters that makes me able to check the file that it has a valid data. */
			String before = ""; /* This is for the avoid consecutive repeats */
			while (input.hasNext()) { /* This while process the file and check it whether there is an invalid character or consecutive repeats*/
				String temp = input.next();
				if (temp.equals("E"))       
					result += "e";
				else if (temp.equals("O")) {   
					result += "o";
					numO++;
				}
				else if (temp.equals("X")) { 
					result += "x";
					numX++;
				}
				else if(temp.equals("|")) {
					numSlash++;
				}
				else {
					suitable = false;
					result = "";
					break;
				}
				if(before.equalsIgnoreCase(temp) && !temp.equals("|")) {
					suitable = false;
					result = "";
					break;
				}
				before = temp;
			}
			if(!suitable) continue;
			if(Math.abs(numX-numO)<=1 && numSlash == 20 && result.length() == 16 && !((win(result, 'x') || win(result, 'o') || tie(result) )) ) break;
			/*These are the conditions that needs to be satisfied by the file, if it, programs returns the "result" table as loaded file */
			else {
				suitable = false;
				result = "";
				continue;
			}
		}
		return result;
	}
	/* This method controls the current status of the table whether it is finished by one of the players or it is draw. 
	 * If there is a winner,it prints the table and announce he winner and return 1.*/
	public static int control(String table,boolean toke) {
		if (win(table, 'x') || win(table, 'o')) { /* Here I check board with "win" method if it is finished*/
			draw(table, toke);                    /* If the game is finished, this line prints the board to console via "draw" method*/
			System.out.println(win(table, 'x') ? "You win!" : "Computer wins!");
			return 1;
		}
		if (tie(table)) { /* Here I check board with "tie" method if it is finished*/
			draw(table, toke); /* If the game is finished, this line prints the board to console via "draw" method*/
			System.out.println("It's tie!");
			return 0;
		}
		return -1;
	}
	/* This method draws the current table to the console regarding the "toke" boolean which indicates the token that user choose from my "table" string */
	public static void draw(String table, boolean toke) {
		String e = " E |";
		String x = toke ? " X |" : " O |";
		String o = toke ? " O |" : " X |";
		for (int i = 0; i < 16; i++) {
			char let = table.charAt(i);
			if (i % 4 == 0)
				System.out.print("|");
			if (let == 'e')
				System.out.print(e);
			else if (let == 'x')
				System.out.print(x);
			else if (let == 'o')
				System.out.print(o);
			if (i % 4 == 3)
				System.out.println();
		}
	}
	/* This method controls the table whether it is able to play or it is a draw */
	public static boolean tie(String table) {
		for (int i = 0; i < 16; i++) {
			if (table.charAt(i) == 'e')
				return false;
		}
		return true;
	}
	/* This method process the given "token" to the given "position" in the given "table" string and return it.*/
	public static String process(int position, String table, char token) {
		String tempTable = ""; /* This variable is a temporary table, which will have been the processed version of the table  */
		for (int i = 0; i < 16; i++) {
			char let = table.charAt(i); /* The letter which is at i'th letter of the table string.*/
			if (i == position)
				tempTable += token;
			else
				tempTable += let;
		}
		return tempTable;
	}
	/* This method controls the table whether there is a winner or not. 
	 * It checks the first half of the board with "checkHalf" method for given token with some sort of algorithm,
	 * and then it implies same method to the second half of the table. */
	public static boolean win(String table, char token) {
		boolean firstHalf = checkHalf(table, token); /* This boolean keeps the value of winning game for the first half of the table, if there is any, it becomes true */
		String tableReverse = ""; /* This is reversed version of table, it is created in order to check the second half of the table */
		for (int i = 15; i >= 0; i--) 
			tableReverse += table.charAt(i);
		boolean secondHalf = checkHalf(tableReverse, token); /* This boolean keeps the value of winning game for the second half of the table, if there is any, it becomes true */
		return firstHalf || secondHalf;
	}
	/* This method checks the first half of the table and checks the winning conditions if they satisfy.
	 * I define the wining conditions regarding the position of the token controlled 
	 * If there is any triple, method returns true. */
	public static boolean checkHalf(String table, char a) {
		for (int i = 0; i < 8; i++) {
			char let = table.charAt(i);
			if (let == a) {
				if (i % 4 < 2) {
					if (let == table.charAt(i + 1) && let == table.charAt(i + 2)) /* This line for consecutive winning conditions */
						return true;
					if (let == table.charAt(i + 4) && let == table.charAt(i + 8)) /* This line for vertical winning conditions */
						return true;
					if (let == table.charAt(i + 5) && let == table.charAt(i + 10)) /* This line for the right-diagonal winning conditions */
						return true;
				} else {
					if (let == table.charAt(i + 4) && let == table.charAt(i + 8)) /* This line for vertical winning conditions */
						return true;
					if (let == table.charAt(i + 3) && let == table.charAt(i + 6)) /* This line for left-diagonal winning conditions */
						return true;
				}
			}
		}
		return false;
	}
	/* This method takes valid input from user. If input values are not in suitable format, those which aren't integer, 
	 * or the given position is not appropriate because of being out of index or because of being occupied by other tokens,
	 * the method repeats the request of coordinates till those conditions satisfy. It returns chosen positions coordinate.*/
	public static int chooseOfUser(String table) {
		while (true) {
			Scanner user = new Scanner(System.in);
			if (!user.hasNextInt()) {
				System.out.print("Wrong input! Try again: ");
				continue;
			}
			int userX = user.nextInt(); /* userX is the x coordinate of the position of user */ 
			if (!user.hasNextInt()) {
				System.out.print("Wrong input! Try again: ");
				continue;
			}
			int userY = user.nextInt(); /* userY is the y coordinate of the position of user */ 
			int userPosition = 4 * userX + userY - 5; /* This line convert coordinates to a position value in the game string */
			if (userX > 4 || userY > 4 || userX < 1 || userY < 1 || table.charAt(userPosition) != 'e') { /* This line controls that the position that is chosen is appropriate*/
				System.out.print("Wrong input! Try again: ");
				continue;
			}
			return userPosition;
		}
	}
	/* This method determines the move of the computer. It uses "chooseWisely" and "move" methods to choose coordinate.
	 * "chooseWisely" method is to choose a position which satisfies a winning condition for given "token" and "table".
	 * Computer prefers to win a game if it is possible, if it is not, it chooses to block users triple.
	 * If there is also no triple situation in next move for both cpu and user, computer chooses coordinates regarding to "move" method,
	 * which finds the most logical move.*/
	public static int chooseOfCpu(String table, Random rand) {
		int defensePosition = chooseWisely(table, 'x'); /* It is the optimal move to defend user */
		int attackPosition = chooseWisely(table, 'o'); /* It is the position of the point that computer wins the game */
		if (attackPosition != -1) /* Computer prefers to win a game if it is possible */
			return attackPosition;
		if (defensePosition != -1) /* If it is not, it chooses to block users triple. */
			return defensePosition;
		else
			return moveOfCpu(rand, table); /* If there is nothing to do directly in next move, computer calculates the most logical position to move. */ 
	}
	/* This method checks the table and find the point that given "token" could win in one move*/
	public static int chooseWisely(String table, char token) {
		for (int i = 0; i < 16; i++) {
			if (table.charAt(i) == 'e') {
				String temp = process(i, table, token); /* It process the given "token" for every possible position in the table.*/
				if (win(temp, token)) /* And it checks that in that position, could "token" win the game.*/
					return i; /* It returns the winning position */ 
			}
		}
		return -1; /* If there is no winning position, it returns "-1"*/
	}
	/* This method is to determine the most logical move to done by controlling 2 move after.
	 * It controls two move after with moveWiselly method and in the end, it returns a position to move */
	public static int moveOfCpu(Random rand, String table) {
		int initialPosition, finalPosition = 0; /* Those are for the initial position and final position*/ 
		if (table.equals("eeeeeeeeeeeeeeee"))  										    /* If the table is empty, which means when computer starts the game*/
			return rand.nextBoolean() ? (rand.nextInt(2) + 5) : (rand.nextInt(2) + 9);  /* It chooses a random position amongst the middle four position and returns it */
		initialPosition = (moveWisely(table, 'o') != -1) ? moveWisely(table, 'o') : moveWisely(table, 'x'); /* It prefers the logical move that itself wins rather than that it defends user.*/
		do {																								/* However, there must exist a logical position to move, which is not -1*/
			finalPosition = (initialPosition == -1) ? rand.nextBoolean() ? (rand.nextInt(2) + 5) : (rand.nextInt(2) + 9) : initialPosition; /* If there is no logical move to do, computer plays random amongst the middle four position*/
		} while (table.charAt(finalPosition) != 'e');
		return finalPosition;
	}
	/* This method counts 2 after move and detect that is there any precise winning condition, 
	 * especially the situation that there is 2 consecutive token that both sides of them are available to play and cause a winning. */
	public static int moveWisely(String table, char token) {
		int idMax = -1; /* This is the value of the expected position */
		for (int i = 0; i < 16; i++) {
			if (table.charAt(i) == 'e') {
				int count = 0;
				String temp = process(i, table, token); /* Computer plays one time to the every possible position here.*/
				for (int k = 0; k < 16; k++) {
					if (temp.charAt(k) == 'e') {
						String tempTable = process(k, temp, token); /* Computer plays second time to the every possible position.*/
						if (win(tempTable, token))  /* It check the table if there is a winning condition.*/       
							count++; /* I count the winning situations.*/ 
					}
				}
				if (count > 1) /* If there are more than one winning condition, this position is the optimal point of move.*/
					idMax = i; /* It keeps the position in idMax variable.*/
			}
		}
		return idMax ; /* It returns idMax. If there is no optimal move, it returns -1 as it has defined above.*/
	}
}