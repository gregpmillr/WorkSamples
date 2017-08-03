import java.util.Scanner;

/*
 * Program to draw a diamond using recursive functions. Usually the ones on the interwebs
 * only do odd or even numbers, this works for both. Just define the levels you want and the diamond
 * is created.
 * 
 * Created By: Greg Miller
 */

public class Diamond {
	private int maxLevel;
	private int levels;
	
	Diamond(int levels) {
		this.maxLevel = levels+1;
		this.levels = levels;
	}
	
	/*
	 * Returns an int primitive just for the purpose of the way I used recursion. This could be implemented
	 * without the int return. Calculates the level, and spaces to use on each level.
	 * 
	 * @param level the current level which is to be printed
	 * @return int used to calculate the levels through recursion
	 */
	public int printDiamondLogic(int level) {	
		int spaces;
		
		if (level == 1) {
			spaces = levels-1;
			printDiamond(1, spaces);
			return printDiamondLogic(level+1);
		} else if (level == this.maxLevel) {
			return this.maxLevel-(level-1);
		} else { 
			spaces = (this.maxLevel-1)-level;
			printDiamond(level,spaces);
			spaces = this.maxLevel-level;
			return printDiamond(level-(printDiamondLogic(level+1)),spaces);
		}
	}
	
	/*
	 * Prints the diamond using dots (*). Calculates the number of dots to use for every row and prints that row
	 * with correct spacing.
	 * 
	 * @param level the current level being printed
	 * @param spaces the spaces needed for this level
	 * @return int to help calculation with printing the next level
	 */
	public int printDiamond(int level, int spaces) {
		int dots = level*2-1;

		if(level == 1) {
			for(int j = 0;j < spaces; j++) {
				System.out.print(" ");
			}
			System.out.println("*");
			return 1;
		}
		for(int j = 0;j < spaces; j++) {
			System.out.print(" ");
		}
		for(int i = 0; i < dots; i++) {
			System.out.print("*");
		}
		System.out.println("");
		return 1;
	}
}
