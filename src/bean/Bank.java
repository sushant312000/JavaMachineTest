package bean;

import java.util.Random;

import lib.CustomerDao;

//Customer Class
public class Bank {

	// Instance variables
	private long accountNumber;
	private String customerName;
	private String accountType;
	private static double balance;
	private double minimumBalance;
	private long mobileNumber;
	private String customerEmailId;
	private int atmPin;
	private String panNumber;

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	// Default constructor;
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
	//Getting account number
	public long getAccountNumber() {
		return accountNumber;
	}

	// Generating random account number and assign the value to the accountNumber
	public void setAccountNumber() {
		Random rand = new Random();
		int accountNumber = 100000000 + rand.nextInt(900000000);
		this.accountNumber = accountNumber;
	}
	
	//Getting customer name
	public String getCustomerName() {
		return customerName;
	}
	
	//setting customer name
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	//getting account type
	public String getAccountType() {
		return accountType;
	}

	//setting account type
	public void setAccountType(String accountType) {
		if (accountType.equalsIgnoreCase("Savings") || accountType.equalsIgnoreCase("Current")) {
			this.accountType = accountType;
		} else {
			System.out.println("Enter account type as savings or current");
			CustomerDao.bankMenu();
		}
	}
	
	//Getting balance
	public static double getBalance() {
		return balance;
	}

	//setting balance
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	//getting minimum balance
	public double getMinimumBalance() {
		return minimumBalance;
	}
	
	//setting minimum balance
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
	//getting mobile number
	public long getMobileNumber() {
		return mobileNumber;
	}
	
	//setting mobile number
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	//getting customer email ID
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	
	//Setting customer email id
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	
	//getting atm pin
	public int getAtmPin() {
		return atmPin;
	}
	
	//setting atm pin
	public void setAtmPin() {
		Random rand = new Random();
		int atmPin = 1000 + rand.nextInt(9000);
		this.atmPin = atmPin;
	}
	
//	//Custom methods 
//	public static void depositAmount(double amountToBeDeposit) {
//		 double currentBalance = getBalance();
//		 System.out.println("Your previous balance was : " + currentBalance);
//		 currentBalance += amountToBeDeposit;
//		 System.out.println("After depositing, Now your balance is: " + currentBalance);
//	}
	
	@Override
	public String toString() {
		return "Bank [accountNumber=" + accountNumber + ", customerName=" + customerName + ", accountType="
				+ accountType + ", balance=" + balance + ", minimumBalance=" + minimumBalance + ", mobileNumber="
				+ mobileNumber + ", customerEmailId=" + customerEmailId + ", atmPin=" + atmPin + "]";
	}

}
