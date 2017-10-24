package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.User;
import service.GenericService;


public class UserController extends GenericController<User>{

	private static final String USERS_URL = "/users";
	
	public UserController(GenericService<User> service, Class<User> typeParameterClass) {
		super(service, typeParameterClass);

		get(USERS_URL, (req, res) -> getAll(req,res));
		
		get(USERS_URL + ID_PARAM_URL, (req, res) -> getById(req,res));
		
		post(USERS_URL, (req, res) -> create(req,res));
		
		put(USERS_URL, (req, res) -> update(req,res));
		
		spark.Spark.delete(USERS_URL + ID_PARAM_URL, (req, res) -> delete(req,res));
	}

}
