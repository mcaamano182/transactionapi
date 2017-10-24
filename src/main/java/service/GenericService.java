package service;

import java.util.List;

import model.InvalidOperationException;

public abstract class GenericService <T>{
	
	public abstract List<T>getAll();
	
	public abstract T getById(Long id);;

	public abstract T update(T obj) throws InvalidOperationException;

	public abstract Long delete(Long id) throws InvalidOperationException;
	
	public abstract T create(T obj) throws InvalidOperationException;
	
	public abstract void validateCreate(T obj) throws InvalidOperationException;
	
	public abstract void validateUpdate(T obj) throws InvalidOperationException;

	public abstract void validateDelete(T obj) throws InvalidOperationException;
}
