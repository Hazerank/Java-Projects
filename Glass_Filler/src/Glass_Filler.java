public class Glass_Filler {	
	
	public static void main(String[] args) {
		int glassSize = Integer.parseInt(args[0]);
		int strawPos  = Integer.parseInt(args[1]);
		drink(glassSize,strawPos);
	}
	
	/*This method drink beverage from glass ,whose size are given, with straw ,whose position is given, as much as possible */
	public static void drink(int glassSize, int strawPos) {
		for (int time = 0; time <= times(glassSize,strawPos); time++) // time is the number of the figure that will be drawn in the console for drinking process 
		glass(glassSize,strawPos,time); // This method draws the wanted process
	}
	
	/*This method draws the situation of our glass for given size and straw position after wanted time of drinking process*/
	public static void glass(int glassSize,int strawPos,int time) {
		straw(strawPos); // straw draws the initial and static straw part
		body( glassSize , strawPos , time);
		bottom(glassSize); // Bottom method draws the bottom of the glass
	}
	
	/*This method draw the initial situation of the straw for given position */
	public static void straw(int strawPos) {
		for (int i = 1 ; i <= strawPos ; i++) { // This loop run for strawPos times and makes program able to draw the initial straw  
			System.out.printf("%" + i + "s\n" , "o");
		}
	}
	
	/*This method draw the body part of the glass*/
	public static void body(int glassSize,int strawPos,int time) {
		for (int line = 0; line < glassSize; line++) { // line is the number of the line that will be written 
			if (line < time) { // if means here, when line is smaller than time, the program will write a drank line. This line program is drawing the "time"st figure.
				write(line," "); // Writes method write line times " "
				System.out.print("\\");
				write((strawPos - 1)," "); // Write method writes (strawPos - 1) times " "
				System.out.print("o");
				write((2*(glassSize +1) - line - 1) - (strawPos + (line + 1))," "); // Write method writes ((2*(glassSize +1) - line - 1) - (strawPos + (line + 1)) times " "
				System.out.println("/");
			} else {
				write(line," "); // Write method writes line times " "
				System.out.print("\\"); 
				write((2*(glassSize-line)),"*"); // Write method writes (2*(glassSize-line)) times "*"
				System.out.println("/");
			}
		}
	}
	
	/*This method draw the bottom of the glass */
	public static void bottom(int glassSize) {
		System.out.printf("%" + glassSize + "s--\n", " "); // This statement write the first part of the bottom of the glass
		for (int i = 1; i <= glassSize; i++) // This loop determine the length of the bottom of the glass
			System.out.printf("%" + glassSize + "s||\n", " "); // This statement write the remaining part of the bottom of the glass
	}

	/*This method find the maximum number of drinking process which can be done for given position of straw */
	public static int times(int glassSize, int strawPos) {
		int end = glassSize*2 + 2; // end is the position of the end of the glass at that line
		int result = 1; // result is the result of the times method which is the number of figures
		for (strawPos = strawPos + 1; ; strawPos++) { // This for statement increases strawPos ,which is the position of straw, one by one 
			if (strawPos == (end - 2) ||strawPos == (end - 1) ) // if statement checks the position of the straw whether it is 2 units far from end or 1 unit far away
				return result; // Method return result as the number of drinking step or as the same time number of the figures that will be drawn
			end  -= 1; // When the if statement isn't satisfied, the end moves one unit left which means the program will check the next line of glass
			result +=1; // for each try, result will increase 1
		}
	}
	
	/*This method is in order not to repeat the same for loop in the body method*/
	public static void write(int number, String element) { // number is the number of how many times will the given element be written and element is the string that is wanted to be written as much as given number
		for (int i = 0 ; i < number ; i++)
			System.out.print(element);
	}
}
