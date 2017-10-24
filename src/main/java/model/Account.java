package model;

import java.util.List;

public class Account {
	
	private Long id;
	private String number;
	private User owner;
	private List<Saving> savings;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<Saving> getSavings() {
		return savings;
	}
	public void setSavings(List<Saving> savings) {
		this.savings = savings;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
