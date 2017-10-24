package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.Account;
import service.GenericService;

public class AccountController extends GenericController<Account>{

	private static final String ACCOUNTS_URL = "/accounts";
	
	public AccountController(GenericService<Account> service, Class<Account> typeParameterClass) {
		super(service, typeParameterClass);

		get(ACCOUNTS_URL, (req, res) -> getAll(req,res));
		
		get(ACCOUNTS_URL + ID_PARAM_URL, (req, res) -> getById(req,res));
			
		post(ACCOUNTS_URL, (req, res) -> create(req,res));
		
		put(ACCOUNTS_URL, (req, res) -> update(req,res));
		
		spark.Spark.delete(ACCOUNTS_URL + ID_PARAM_URL, (req, res) -> delete(req,res));
	}
}