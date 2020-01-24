import java.io.*;
import java.util.*;
public class Maze_Solver {
	public static void main(String[] args) throws FileNotFoundException {
		int row = 0, column = 0;  File file = new File("input.txt"); /* I identify row and column values and create a File which is our file. */
		Scanner local = new Scanner(file); 
		String size = space(local.next()); /* I read the first line of the input file. I first rearrange the line with "space" method which puts space between numbers and X. */
		Scanner liner = new Scanner(size); 
		row = liner.nextInt(); liner.next(); column = liner.nextInt(); /* I assign the row and column values according to the input file. */
		int[][][] table = new int [row][column][]; /* I create a 3D array which store the table as an array. */
		fillArray(local, table, 0);
		drawTable(table, 0);
		mainControl(table, 0);
		System.out.println();
		drawTable(table, 0);
	}
	/* This method puts spaces between numbers and X. It works recursively.*/
	public static String space(String str) {
		if (str.length() == 1) return str;
		/* This line identify the numbers and "x" character and put spaces where is necessary. */
		String temp = str.charAt(0) + ((str.charAt(1) <=  '9' && str.charAt(1) >='0' && str.charAt(0) <= '9' && str.charAt(0) >='0' ) ? "":" ") + space(str.substring(1));
		return temp ;
	}
	/* This method converts "F4" style input to an array having two component.*/
	public static int[] toThirdArray(String str) {
		int[] array = new int[2];
		array[0] = str.charAt(1)- '0';
		array[1] = str.charAt(0);
		return array;
	}	
	/* This method fills the array with the data in the table. It works recursively. */
	public static void fillArray(Scanner local, int[][][] table, int counter) {
		int column = table[0].length , row = table.length; /* These are the boundaries of the table. */ 
		table[counter / column][counter % column] = toThirdArray(local.next()); /* I am filling the 3rd dimension of my array with "toThirdArray" method. */
		if (counter == column*row - 1) return;
		fillArray(local,table,++counter);
	}
	/* This method print the table to the console with the data in the table. It works recursively. */
	public static void drawTable(int[][][]  table,int counter) {
		int column = table[0].length , row = table.length; /* These are the boundaries of the table. */ 
		System.out.print(table[counter / column][counter % column][0] + " "); /* This line prints every element to the console. */ 
		if (counter % column == column - 1) System.out.println();
		if (counter == column*row - 1) return;
		drawTable(table,++counter);
	}
	/* This method control the table element by element. It works recursively. */
	public static void mainControl(int[][][] table,int counter) {
		int column = table[0].length , row = table.length; /* These are the boundaries of the table. */ 
		check(table[counter / column][counter % column][1], table, counter/column, counter%column); /* This line checks every element with "check" method and learn its letter value */ 
		if (counter == column*row - 1) return;
		mainControl(table,++counter);
	}
	/* This method identifies every cell and call the appropriate method to process this cell. It uses "switch" structure. */
	public static void check(int code,int[][][] table, int rowActual,int columnActual) {
		switch (code) {
		case 'F':
			methodF();
			break;
		case 'N':
			methodN(table,rowActual,columnActual);
			break;
		case 'R':
			methodR(table,rowActual,columnActual);
			break;
		case 'D':
			methodD(table,rowActual,columnActual);
			break;
		case 'C':
			methodC(table,rowActual,columnActual);
			break;
		}
	}	
	/* This method is empty because F cells don't need any rearrangement. */ 
	public static void methodF() {
	}
	/* This method process the R cells with the help of "fixR" method.*/
	public static void methodR(int[][][] table, int rowActual,int columnActual) {
		int[] tempArray = {Integer.MIN_VALUE}; /* tempArray stores the maximum value of the row.*/
		fixR(table,rowActual,table[0].length,tempArray);	
		table[rowActual][columnActual][0] = tempArray[0]; /* I assign the maximum value of the row to the "R" cell. */
	}
	/* This method finds the cell which has the maximum value and store it in the tempArray. It works recursively. */
	public static void fixR(int[][][] table ,int rowActual,int column,int[] tempArray) {
		int columnActual = table[0].length-column;
		tempArray[0]= tempArray[0] < table[rowActual][columnActual][0] ? table[rowActual][columnActual][0] : tempArray[0] ;
		if (column == 1) return;
		fixR(table,rowActual,column-1,tempArray);
	}
	/* This method process the "D" cells with the help of "fixC" method.*/
	public static void methodD(int[][][] table, int rowActual,int columnActual) {
		int[] tempArray = {table[rowActual][columnActual][0],1}; /* I create an array with tow elements. First one stores the sum of the diagonal cells and the second one stores the quantity of them. */
		fixD(table, table.length, table[0].length, rowActual, columnActual, tempArray); 
		table[rowActual][columnActual][0] = tempArray[0]/tempArray[1]; /* I assign the average value of the all diagonal cells to the original "D" cell. */
	}
	/* With this method and "fixDRow" method, I control every element of the array one by one recursively. */
	public static void fixD(int[][][] table, int row, int column,int rowActual,int columnActual, int[] tempArray) {
		fixDRow(table, row, column, rowActual, columnActual, tempArray);
		if (row == 1) return;
		fixD(table, row-1, column, rowActual, columnActual, tempArray);
	}
	/* Actually in these two method, I find the slope between every cell and our "D" cell and store the ones which has a slope as "1" or "-1" . */
	public static void fixDRow(int[][][] table, int row, int column,int rowActual,int columnActual, int[] tempArray) {
		int rowTemp = table.length-row, columnTemp = table[0].length-column; /* These are the actual values of the row and column. */
		double slope = ((double) columnTemp - columnActual)/(rowTemp - rowActual); /* In here I find the slope value. */
		tempArray[0] += (slope == 1 ||  slope == -1) ? table[rowTemp][columnTemp][0] : 0; /* If slope is "1" or "-1", I process this cell as a diagonal cell. */
		tempArray[1] += (slope == 1 ||  slope == -1) ? 1 : 0;
		if (column == 1) return; 
		fixDRow(table, row, column-1, rowActual, columnActual, tempArray);
	}
	/* This method process the "C" cells with the help of "fixC" method.*/
	public static void methodC(int[][][] table, int rowActual, int columnActual) {
		int[] tempArray = new int[table.length]; /* This array will store the column as an array.*/
		fixC(table,table.length,columnActual,tempArray);
		Arrays.sort(tempArray); /* I sorted the tempArray to find the median value. */
		table[rowActual][columnActual][0] = table.length% 2 == 0 ? tempArray[table.length/2-1]: tempArray[table.length/2]; /* I assign the median value of the column to the "C" cell. */
	}
	/* This method store the column of the "C" cell in the tempArray. It works recursively. */
	public static void fixC(int [][][] table, int row, int columnActual, int[] tempArray) {
		int rowActual = table.length - row ;
		tempArray[rowActual] = table[rowActual][columnActual][0];
		if (row == 1) return;
		fixC(table,row-1,columnActual,tempArray);
	}
	/* This method process the "N" cells recursively. It checks the four neighbor cell whether they are "N" cell or not. If it, it calls itself to check the neighbor cells. It changes the original 
	 * "N" cells letter value to the something different than "N" which is "n" in this case. This is due to prevent the endless loops could occur in the recursion process. */
	public static void methodN(int[][][] table,int row,int column) {
		if(row > 0)
			if(table[row-1][column][1] == 'N') {
				table[row-1][column][0] = table[row][column][0];
				table[row-1][column][1] = 'n';
				methodN(table,row-1,column);
			}
		if(row < table.length - 1)
			if(table[row+1][column][1] == 'N') {
				table[row+1][column][0] = table[row][column][0];
				table[row+1][column][1] = 'n';
				methodN(table,row+1,column);
			}
		if(column > 0)
			if(table[row][column-1][1] == 'N') {
				table[row][column-1][0] = table[row][column][0];
				table[row][column-1][1] = 'n';
				methodN(table,row,column-1);
			}
		if(column < table[0].length - 1)
			if(table[row][column+1][1] == 'N') {
				table[row][column+1][0] = table[row][column][0];
				table[row][column+1][1] = 'n';
				methodN(table,row,column+1);
			}
	}	
}