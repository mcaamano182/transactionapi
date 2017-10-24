package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Currency;
import model.InvalidOperationException;
import model.Saving;
import spark.utils.StringUtils;

public class SavingService extends GenericService<Saving>{
	
	private Map<Long,Saving> list = new HashMap<Long, Saving>();

	public List<Saving>getAll() {
		return list.values().stream().collect(Collectors.toList());
	}

	public Saving getById(Long id) {
		return list.get(id);
	}

	public Saving update(Saving saving) throws InvalidOperationException {
		
		this.validateUpdate(saving);
		
		Saving toUpdate = list.get(saving.getId());
		toUpdate.setBalance(saving.getBalance());
		toUpdate.setCurrency(saving.getCurrency());
		toUpdate.setType(saving.getType());
		return toUpdate;
	}

	public Long delete(Long id) throws InvalidOperationException {
		
		Saving saving = this.getById(id);
		
		this.validateDelete(saving);
		
		list.remove(id);
		return id;
	}

	public Saving create(Saving saving) throws InvalidOperationException {
		
		this.validateCreate(saving);
		saving.setId(list.keySet().stream().max(Long::compareTo).orElse(0L) + 1);
		list.put(saving.getId(), saving);
		return saving;
	}

	@Override
	public void validateCreate(Saving obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Currency to be created cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getBalance() < 0) {
			message = "The balance of the saving must be greater or equal to 0.";
			throw new InvalidOperationException(message);
		}else if((obj.getType() == null)) {
			message = "A type must be specified for the saving";
			throw new InvalidOperationException(message);
		}else if((obj.getCurrency() == null)) {
			message = "A currency must be specified for the saving";
			throw new InvalidOperationException(message);
		}
		
		
	}

	@Override
	public void validateUpdate(Saving obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Saving to be updated cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getBalance() < 0) {
			message = "The balance of the saving must be greater or equal to 0.";
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Saving cannot be null.";
			throw new InvalidOperationException(message);
		}else if((obj.getType() == null)) {
			message = "A type must be specified for the saving";
			throw new InvalidOperationException(message);
		}else if((obj.getCurrency() == null)) {
			message = "A currency must be specified for the saving";
			throw new InvalidOperationException(message);
		}
	}

	@Override
	public void validateDelete(Saving obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Saving to be deleted cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Saving cannot be null.";
			throw new InvalidOperationException(message);
		}		
	}

	public void updateSavingBalance(Saving savingSource, double toSum, double toSubstract, Currency currency) {

		double initialBalance = savingSource.getBalance();
		double origConversionRatio = savingSource.getCurrency().getConversionRatio();
		
		double initialBalanceConverted = initialBalance * origConversionRatio;
		
		double toSumConverted = toSum * currency.getConversionRatio();
		double toSubstractConverted = toSubstract * currency.getConversionRatio();
		
		double finalBalanceConverted = initialBalanceConverted + toSumConverted - toSubstractConverted;
		
		double finalBalance = finalBalanceConverted / origConversionRatio;
		
		savingSource.setBalance(finalBalance);
		
	}

}
