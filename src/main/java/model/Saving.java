package model;

public class Saving {

	private Long id;
	private double balance;
	private Currency currency;
	private SavingType type;
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public SavingType getType() {
		return type;
	}
	public void setType(SavingType type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
