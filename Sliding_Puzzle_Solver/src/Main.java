import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static Scanner scan;
	public static PrintStream write;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		
		int[] tempArray = new int[2];
		scan = new Scanner(new File(args[0]));
		write = new PrintStream(new File(args[1]));
		Solve solution = new Solve(inputConverter(tempArray),tempArray);  
		write.println(solution.solve());

    }

	private static String inputConverter(int[] tempArray) {
		String input = scan.nextLine();
		Scanner temp = new Scanner(input.replace('-', ' '));
		String real = "";
		int i = 0; 
		while(temp.hasNextInt()) {
			int tempo = temp.nextInt();
			real += (char)(tempo + 48);
			if(tempo == 0)
				tempArray[0] = i;
			i++;
		}
		temp.close();
		tempArray[1] = (int) Math.sqrt(i);
		return real;
		
	}

}
