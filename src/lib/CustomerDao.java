package lib;

import java.util.ArrayList;
import java.util.Scanner;

import app.MainApp;
import bean.Bank;

public class CustomerDao {
	static ArrayList<Bank> customerList = new ArrayList<>();

	// Menu Driven
	public static void bankMenu() {

		// bank portal display message.
		System.out.println("You are in bank portal \n");
		System.out.println(
				"1. Add a new customer \n2. Delete a customer \n3. Update a existing customer \n4. List all customers \n5. Specific customer details \n6. Back to main menu");
		System.out.println("Enter choice between (1 to 6) \n");
		Scanner input = new Scanner(System.in);

		int choice = Integer.parseInt(input.nextLine());

		switch (choice) {
		case 1:
			// Go to add method
			addCustomer();
			break;

		case 2:
			// Go to delete method
			deleteCustomer();
			break;

		case 3:
			// Go to update method
			updateCustomer();
			break;

		case 4:
			// Go to list method
			listCustomers();
			break;

		case 5:
			// Go to main menu
			customerDetails();
			break;

		case 6:
			// Go to main menu
			MainApp.mainMenu();
			break;

		default:
			System.out.println("Enter valid choice between (1 to 5");
			break;
		}
	}

	public static void customerMenu() {
		// Displaying welcome message
		System.out.println("Welcome to Customer portal");

		System.out.println("1. Withdrawl \n2. Deposite \n3. Back to main menu");

		// Creating input
		Scanner input = new Scanner(System.in);
		int choice = Integer.parseInt(input.nextLine());

		switch (choice) {
		case 1:
			// Calling withdraw function
			withdrawAmount();
			break;

		case 2:
			// Calling deposit function
			depositAmount();
			break;

		case 3:
			// Back to main menu
			MainApp.mainMenu();
		}
	}

	// Deposit function
	private static void depositAmount() {
		try {
			// Asking to enter account number
			System.out.println("Enter Your Account number");

			// Creating input
			Scanner input = new Scanner(System.in);

			// Entering account number
			long accountNumber = Long.parseLong(input.nextLine());

			// Searching in customer list for the account number
			for (Bank bank : customerList) {
				if (bank.getAccountNumber() == accountNumber) {
					// If account number found welcome that person
					System.out.println("Welcome " + bank.getCustomerName());

					// Asking the amount to deposit
					System.out.println("Enter amount to deposit : ");

					// Entering amount to deposit
					double depositAmount = Double.parseDouble(input.nextLine());

					// If amount is greater than 50000 asking for pan number
					if (depositAmount > 50000) {
						System.out.println("Enter your PAN Number : ");

						// Entering pan number
						String panNumber = input.nextLine();

						bank.setPanNumber(panNumber);
					}

					// Before transaction ask for PIN
					System.out.println("Enter your PIN : ");
					int atmPin = input.nextInt();

					// If pin is matching deposit the amount in balance
					if (atmPin == bank.getAtmPin()) {
						double previousAmount = bank.getBalance();
						double currentBalance = previousAmount + depositAmount;

						// Set the new balance
						bank.setBalance(currentBalance);
						System.out.println("Now total balance is " + bank.getBalance());
					} else {
						System.out.println("Your PIN is Wrong");
						depositAmount();
					}
				}
			}

		} catch (Exception ex) {
			System.out.println("Account Not found!! Enter Existing account");
			depositAmount();
		}

		customerMenu();
	}

	private static void withdrawAmount() {
		// Asking to enter account number
		System.out.println("Enter Your Account number");

		// Creating input
		Scanner input = new Scanner(System.in);

		// Entering account number
		long accountNumber = Long.parseLong(input.nextLine());

		// Searching in customer list for the account number
		for (Bank bank : customerList) {
			try {
				if (bank.getAccountNumber() == accountNumber) {
					// If account number found welcome that person
					System.out.println("Welcome " + bank.getCustomerName());

					// Showing available balance
					double availableAmount = bank.getBalance() - bank.getMinimumBalance();
					System.out.println("Your available balance : " + availableAmount);

					// Asking the amount to deposit
					System.out.println("Enter amount to withdraw : ");
					double withdrawAmount = Double.parseDouble(input.nextLine());

					if (withdrawAmount > availableAmount) {
						System.out.println("Insufficient Funds");
						customerMenu();
					}
					
					//Before transaction ask for pin
					System.out.println("Enter Your PIN");
					int atmPin = input.nextInt();
					
					// If pin is matching withdraw the amount from available balance
					if (atmPin == bank.getAtmPin()) {
						double previousAmount = availableAmount;
						double currentBalance = previousAmount - withdrawAmount;

						// Set the new balance
						bank.setBalance(currentBalance);
						System.out.println("Now total balance is " + currentBalance);
					}
					else {
						System.out.println("Wrong PIN... Try again");
						withdrawAmount();
					}
				}
			}catch(Exception ex) {
				System.out.println("");
			}
		}
	}

	private static void customerDetails() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter account number of customer : ");
			Scanner input = new Scanner(System.in);
			long accountNumber = Long.parseLong(input.nextLine());

			if (customerList.size() == 0) {
				System.out.println("No Customers are available \n");
			} else {
				for (Bank bank : customerList) {
					if (bank.getAccountNumber() == accountNumber) {
						System.out.println("Bank Account Number -> " + bank.getAccountNumber());
						System.out.println("Customer Name -> " + bank.getCustomerName());
						System.out.println("Account Type -> " + bank.getAccountType());
						System.out.println("Balance -> " + Bank.getBalance());
						System.out.println("Mobile Number -> " + bank.getMobileNumber());
						System.out.println("Email ID -> " + bank.getCustomerEmailId());
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Account number not found...Enter existing account number");
			customerDetails();
		}

	}

	// Listing all the customers
	private static void listCustomers() {
		System.out.println("All the customers are here listed :- \n");
		if (customerList.size() == 0) {
			System.out.println("No customers are available \n");
		} else {
			for (Bank bank : customerList) {
				System.out.println(bank.toString() + "\n");
			}
		}

		bankMenu();
	}

	// Update customer function
	private static void updateCustomer() {
		System.out.println("Updating existing customer");

		char choice = 'n';

		do {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter account number to update");
			long accountNumber = Long.parseLong(input.nextLine());

			try {
				for (Bank bank : customerList) {
					if (bank.getAccountNumber() == accountNumber) {
						updateCustomerDetails(bank);
					}
				}
			} catch (Exception ex) {
				System.out.println("404 Error");
			}
		} while (choice == 'y' || choice == 'Y');

		bankMenu();
	}

	// Called from updateCustomer function to update details
	private static void updateCustomerDetails(Bank bank) {
		Scanner input = new Scanner(System.in);

		System.out.println("-> Enter 1 for Mobile Number \n-> Enter 2 for Email ID");
		System.out.println("Enter the choice you want to update");
		int updateChoiceInput = Integer.parseInt(input.nextLine());

		switch (updateChoiceInput) {
		case 1:
			System.out.println("Enter new mobile number");
			bank.setMobileNumber(Long.parseLong(input.nextLine()));
			break;

		case 2:
			System.out.println("Enter new Email ID");
			bank.setCustomerEmailId(input.nextLine());
			break;

		default:
			System.out.println("Enter the valid input 1 or 2");
			break;
		}

		bankMenu();
	}

	// Delete a customer
	private static void deleteCustomer() {
		try {
			Scanner input = new Scanner(System.in);

			// Asking for account number
			System.out.println("Enter account number to delete");
			long accountNumber = Long.parseLong(input.nextLine());

			if (customerList.size() == 0) {
				System.out.println("No Customers are available");
			} else {
				for (int i = 0; i < customerList.size(); i++) {
					Bank bank = customerList.get(i);
					if (bank.getAccountNumber() == accountNumber) {
						customerList.remove(i);
						System.out.println();
						break;
					}
				}
			}
			
			listCustomers();
		} 
		catch (Exception ex) {
			System.out.println("404 Error");
		}
	}

	// Add a customer
	private static void addCustomer() {
		System.out.println("Adding a new customer...");

		char choice = 'n';

		do {
			Bank customer = new Bank();
			Scanner input = new Scanner(System.in);

			// Entering customer name
			System.out.println("Enter customer name");
			String name = input.nextLine();
			customer.setCustomerName(name);

			// Entering account type
			System.out.println("Enter account type");
			String accountType = input.nextLine();
			customer.setAccountType(accountType);

			// Entering balance
			System.out.println("Enter balance");
			double balance = input.nextDouble();
			customer.setBalance(balance);

			// Entering min balance
			System.out.println("Enter minimum balance");
			double minBalance = input.nextDouble();
			customer.setMinimumBalance(minBalance);

			// Entering email ID
			input.nextLine();
			System.out.println("Enter customer Email Id");
			String email = input.nextLine();
			customer.setCustomerEmailId(email);

			// Entering phone number
			System.out.println("Enter customer's phone number");
			long phoneNum = Long.parseLong(input.nextLine());
			customer.setMobileNumber(phoneNum);

			// Auto-generating Account number
//			System.out.println("Generating account number");
			customer.setAccountNumber();

			// Generating ATM Pin
//			System.out.println("Generating ATM pin");
			customer.setAtmPin();

			// Adding customer details in customer array list
			customerList.add(customer);

			System.out.println("Do you want to continue(Y/N)");

			choice = input.nextLine().charAt(0);

		} while (choice == 'y' || choice == 'Y');

		// After adding showing the list
		listCustomers();

		// And going to main menu
		bankMenu();
	}
}
