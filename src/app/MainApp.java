package app;

import java.util.Scanner;

import lib.CustomerDao;

public class MainApp {

	public static void main(String[] args) {

		// Welcome message
		System.out.println("Welcome... \n");

		// Program Entry
		mainMenu();

	}

	// main menu function
	public static void mainMenu() {
		
		//Displaying main menu
		System.out.println("Bank Managment System \n");
		System.out.println(
				"1. Bank Portal \n2. Customer Portal \n3. Exit");

		// Creating input
		Scanner input = new Scanner(System.in);
	
		//Asking for choice
		System.out.println("Enter your choice (1 to 3)");
		int choice = Integer.parseInt(input.nextLine());
		
		switch(choice) {
		case 1:
			CustomerDao.bankMenu();
			break;
			
		case 2:
			CustomerDao.customerMenu();
			break;
			
		case 3: 
			System.exit(choice);
		}
	}

}
