import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Diamond diamond;
		Scanner scanner = new Scanner(System.in);
		
		Integer levels = requestInput(scanner);
		diamond = new Diamond(levels);
		System.out.println("Printing diamond...");
		diamond.printDiamondLogic(1);
	}

	/*
	 * Get input from user
	 */
	public static Integer requestInput(Scanner scanner) {
		System.out.println("Please enter a number between 1 and 9");
		try {
			Integer answer = Integer.parseInt(scanner.nextLine());
			return ((answer > 0 && answer < 10) ? answer : requestInput(scanner));
		} catch (NumberFormatException e) {
			requestInput(scanner);
		}
		return null;
	}
}
