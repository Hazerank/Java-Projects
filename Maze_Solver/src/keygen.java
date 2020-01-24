import java.util.*;
import java.io.*;
public class keygen {
	public static void main(String[] args) throws FileNotFoundException{
		Random rand = new Random(52);
		PrintStream write = new PrintStream(new File("input.txt"));
		for(int i = 0 ; i < 100000 ; i++) {
			int sizeColumn = rand.nextInt(9) + 1;
			int sizeRow = rand.nextInt(9) + 1;
			write.println(sizeRow + "x"+ sizeColumn);
			drawTable(rand, sizeRow, sizeColumn,write );
			write.println();
		}
	}
	public static void drawTable(Random rand,int row, int column,PrintStream write) {
		drawRow(rand,row,column,write);
		write.println();
		if (row == 1) return;
		drawTable(rand,row-1,column,write);
	}
	public static void drawRow(Random rand,int row,int column,PrintStream write) {
		int harf = rand.nextInt(5);
		String  x = "";
		if (harf == 0) x += 'R';
		if (harf == 1) x += 'N';
		if (harf == 2) x += 'C';
		if (harf == 3) x += 'F';
		if (harf == 4) x += 'D';
		write.print( x + (rand.nextInt(9) + 1) + " ");
		if (column == 1) return;
		drawRow(rand,row,column-1,write);
	}
}
