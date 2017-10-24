package service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.InvalidOperationException;
import model.Transaction;
import model.TransactionStatus;

public class TransactionService extends GenericService<Transaction> {

	private SavingService savingService;
	
	private Map<Long,Transaction> list = new HashMap<Long, Transaction>();
	
	
	public TransactionService(SavingService savingService) {
		this.savingService = savingService;
	}
	
	public List<Transaction>getAll() {
		return list.values().stream().collect(Collectors.toList());
	}

	public Transaction getById(Long id) {
		return list.get(id);
	}

	public Transaction update(Transaction obj) throws InvalidOperationException {
		
		this.validateUpdate(obj);
		
		Transaction toUpdate = list.get(obj.getId());
		toUpdate.setFinalizationDate(obj.getFinalizationDate());
		toUpdate.setStatus(obj.getStatus());		
		return toUpdate;
	}

	public Long delete(Long id) throws InvalidOperationException {
		
		Transaction obj = this.getById(id);
		
		this.validateDelete(obj);
		
		list.remove(id);
		return id;
	}

	public Transaction create(Transaction transaction) throws InvalidOperationException {
		
		this.validateCreate(transaction);
		
		this.createTransaction(transaction);
		
		if(list.get(transaction.getId()) != null) {
			throw new RuntimeException("ID allready used, to update the object please use update method (PUT)");
		}
		else {
			list.put(transaction.getId(), transaction);
		}
		return transaction;
		
	}


	@Override
	public void validateCreate(Transaction obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Transaction to be created cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getAmmount() == 0) {
			message = "The ammount to be transferred must be specified.";
			throw new InvalidOperationException(message);
		}else if(obj.getCurrency() ==  null) {
			message = "The currency of the transaction must be specified.";
			throw new InvalidOperationException(message);
		}else if(obj.getSavingDestination() == null) {
			message = "A Saving destination must be Specified";
			throw new InvalidOperationException(message);
		}else if(obj.getSavingSource() == null) {
			message = "A Saving source must be Specified";
			throw new InvalidOperationException(message);
		}
		else {
			if(obj.getSavingSource().getBalance() - obj.getAmmount() - obj.getFee() < 0) {
				obj.setCreationDate(OffsetDateTime.now());
				obj.setStatus(TransactionStatus.FINALIZED_ERROR);
				obj.setLastUpdate(OffsetDateTime.now());
				list.put(obj.getId(), obj);	
				
				message = "Insufficient founds in the source account for the transaction!";
				throw new InvalidOperationException(message);
			}
		}
	}

	@Override
	public void validateUpdate(Transaction obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Transaction to be updated cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Transaction cannot be null.";
			throw new InvalidOperationException(message);
		}else if(obj.getStatus() == null) {
			message = "The Status of the Transaction cannot be null";
			throw new InvalidOperationException(message);
		}
	}

	@Override
	public void validateDelete(Transaction obj) throws InvalidOperationException {
		String message = "The Transaction cannot be deleted."; 
		throw new InvalidOperationException(message);
	}

	private void createTransaction(Transaction transaction) {
		transaction.setCreationDate(OffsetDateTime.now());
		transaction.setStatus(TransactionStatus.CREATED);
		transaction.setLastUpdate(OffsetDateTime.now());
		
		transaction.setId(list.keySet().stream().max(Long::compareTo).orElse(0L) + 1);

		list.put(transaction.getId(), transaction);
		
		this.savingService.updateSavingBalance(transaction.getSavingSource(), 0, transaction.getAmmount() + transaction.getFee(), transaction.getCurrency());
		this.savingService.updateSavingBalance(transaction.getSavingDestination(), transaction.getAmmount(), 0, transaction.getCurrency());
		
		transaction.setStatus(TransactionStatus.FINALIZED_OK);
		
	}
}
