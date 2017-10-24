package model;

import java.time.OffsetDateTime;

public class Transaction {
	
	private Long id;
	private String number;
	private Saving savingSource;
	private Saving savingDestination;
	private double ammount;
	private double fee;
	private Currency currency;
	private OffsetDateTime creationDate;
	private OffsetDateTime finalizationDate;
	private OffsetDateTime lastUpdate;
	private TransactionStatus status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public OffsetDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public OffsetDateTime getFinalizationDate() {
		return finalizationDate;
	}
	public void setFinalizationDate(OffsetDateTime finalizationDate) {
		this.finalizationDate = finalizationDate;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Saving getSavingSource() {
		return savingSource;
	}
	public void setSavingSource(Saving savingSource) {
		this.savingSource = savingSource;
	}
	public Saving getSavingDestination() {
		return savingDestination;
	}
	public void setSavingDestination(Saving savingDestination) {
		this.savingDestination = savingDestination;
	}
	public OffsetDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(OffsetDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
}


