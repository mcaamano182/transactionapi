package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Currency;
import model.InvalidOperationException;
import spark.utils.StringUtils;

public class CurrencyService extends GenericService<Currency>{
	
	private Map<Long,Currency> list = new HashMap<Long, Currency>();
	
	public List<Currency>getAll() {
		return list.values().stream().collect(Collectors.toList());
	}

	public Currency getById(Long id) {
		return list.get(id);
	}

	public Currency update(Currency currency) throws InvalidOperationException {
		
		this.validateUpdate(currency);
		
		Currency toUpdate = list.get(currency.getId());
		toUpdate.setConversionRatio(currency.getConversionRatio());
		toUpdate.setName(currency.getName());
		toUpdate.setSymbol(currency.getSymbol());
		return toUpdate;
	}

	public Long delete(Long id) throws InvalidOperationException {
		
		Currency currency = this.getById(id);
		
		this.validateDelete(currency);
		
		list.remove(id);
		return id;
	}

	public Currency create(Currency currency) throws InvalidOperationException {
		
		this.validateCreate(currency);
		currency.setId(list.keySet().stream().max(Long::compareTo).orElse(0L) + 1);
		list.put(currency.getId(), currency);
		return currency;
	}

	@Override
	public void validateCreate(Currency obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Currency to be created cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getConversionRatio() <= 0) {
			message = "A conversion ratio must greater than 0 be especified for the currency";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getName())) {
			message = "A name must be specified for the currency";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getSymbol())) {
			message = "A symbol must be specified for the currency";
			throw new InvalidOperationException(message);
		}
		
		
	}

	@Override
	public void validateUpdate(Currency obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Currency to be updated cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getConversionRatio() <= 0) {
			message = "A conversion ratio must greater than 0 be especified for the currency.";
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the currency to update cannot be null.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getName())) {
			message = "A name must be specified for the currency.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getSymbol())) {
			message = "A symbol must be specified for the currency.";
			throw new InvalidOperationException(message);
		}		
	}

	@Override
	public void validateDelete(Currency obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Account to be deleted cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Account cannot be null.";
			throw new InvalidOperationException(message);
		}		
	}

}
