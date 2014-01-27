package encapsulation;
 
public class Account {
	private double balance;
	private double interestRate;
   
	public Account(double balance, double interestRate) {
		if (balance < 0 || interestRate < 0 ) {
			throw new IllegalArgumentException("Input must be positive!");
		}
		this.balance = balance;
		this.interestRate = interestRate;
	}
   
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			throw new IllegalArgumentException("Not a positive amount!");
		}
	}
   
	public double getInterestRate() {
		return interestRate;
	}
 
	public void setInterestRate(double interestRate) {
		if (interestRate > 0) {
			this.interestRate = interestRate;      
    	 } else {
    		 throw new IllegalArgumentException("Not a positive interest rate!");
    	 }
	}
 
	public double getBalance() {
		return balance;
	}
 
	public void withdraw(double amount) throws Exception {
		if (amount <= balance) {
			balance -= amount;
		} else {
			throw new IllegalStateException("Your account does not have coverage, please try a smaller amount");
		}
	}
   
	public void addInterest() {
		balance += balance*interestRate/100;
	}
}