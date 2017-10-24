package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Account;
import model.InvalidOperationException;
import model.User;
import spark.utils.StringUtils;

public class AccountService extends GenericService<Account> {

	private UserService userService;

	private Map<Long, Account> list = new HashMap<Long, Account>();

	public AccountService(UserService userService) {
		super();
		this.userService = userService;
	}

	public List<Account> getAll() {
		return list.values().stream().collect(Collectors.toList());
	}

	public Account getById(Long id) {
		return list.get(id);
	}

	public Account update(Account obj) throws InvalidOperationException {

		this.validateUpdate(obj);

		Account toUpdate = this.getById(obj.getId());

		User user = this.userService.getById(obj.getOwner().getId());

		toUpdate.setNumber(obj.getNumber());
		toUpdate.setOwner(user);
		toUpdate.setSavings(obj.getSavings());

		return toUpdate;
	}

	public Long delete(Long id) throws InvalidOperationException {

		Account obj = this.getById(id);

		this.validateDelete(obj);

		list.remove(id);
		return id;
	}

	public Account create(Account obj) throws InvalidOperationException {

		this.validateCreate(obj);
		obj.setId(list.keySet().stream().max(Long::compareTo).orElse(0L) + 1);
		list.put(obj.getId(), obj);

		return obj;
	}

	@Override
	public void validateCreate(Account obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Account to be created cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getOwner() == null) {
			message = "The Account must have an owner.";
			throw new InvalidOperationException(message);
		}
	}

	@Override
	public void validateUpdate(Account obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Account to be updated cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Account cannot be null or zero.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getNumber())) {
			message = "The Account number cannot be null.";
			throw new InvalidOperationException(message);
		}
		else if(obj.getOwner() == null) {
			message = "The Account must have an owner.";
			throw new InvalidOperationException(message);
		}
	}

	@Override
	public void validateDelete(Account obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The Account to be deleted cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the Account cannot be null.";
			throw new InvalidOperationException(message);
		}
	}

	public UserService getUserService() {
		return userService;
	}
	
}
