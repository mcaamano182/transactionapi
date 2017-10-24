package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.InvalidOperationException;
import model.Saving;
import model.User;
import spark.utils.StringUtils;

public class UserService extends GenericService<User> {

private Map<Long,User> list = new HashMap<Long, User>();
	
	public List<User>getAll() {
		return list.values().stream().collect(Collectors.toList());
	}

	public User getById(Long id) {
		return list.get(id);
	}

	public User update(User obj) throws InvalidOperationException {
		
		this.validateUpdate(obj);
		
		User toUpdate = list.get(obj.getId());
		toUpdate.setDocument(obj.getDocument());
		toUpdate.setEmail(obj.getEmail());
		toUpdate.setLastname(obj.getLastname());
		toUpdate.setName(obj.getName());
		toUpdate.setPhone(obj.getPhone());
		toUpdate.setPin(obj.getPin());		
		return toUpdate;
	}

	public Long delete(Long id) throws InvalidOperationException {
		
		User obj = this.getById(id);
		
		this.validateDelete(obj);
		
		list.remove(id);
		return id;
	}

	public User create(User user) throws InvalidOperationException {
		this.validateCreate(user);
		user.setId(list.keySet().stream().max(Long::compareTo).orElse(0L) + 1);
		list.put(user.getId(), user);
		return user;
	}

	@Override
	public void validateCreate(User obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The User to be created cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getDocument())) {
			message = "The document of the user must be specified.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getName())) {
			message = "A name must be specified for the User";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getLastname())) {
			message = "A lastname must be specified for the User";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getPin())) {
			message = "A pin must be specified for the User";
			throw new InvalidOperationException(message);
		}
		
		
	}

	@Override
	public void validateUpdate(User obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The User to be updated cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the User cannot be null.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getDocument())) {
			message = "The document of the user must be specified.";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getName())) {
			message = "A name must be specified for the User";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getLastname())) {
			message = "A lastname must be specified for the User";
			throw new InvalidOperationException(message);
		}else if(StringUtils.isEmpty(obj.getPin())) {
			message = "A pin must be specified for the User";
			throw new InvalidOperationException(message);
		}
	}

	@Override
	public void validateDelete(User obj) throws InvalidOperationException {
		String message = null;
		if(obj == null) {
			message = "The User to be deleted cannot be null."; 
			throw new InvalidOperationException(message);
		}else if(obj.getId() == null) {
			message = "The ID of the User cannot be null.";
			throw new InvalidOperationException(message);
		}		
	}

}
