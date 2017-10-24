package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.Saving;
import service.GenericService;

public class SavingController extends GenericController<Saving>{

	private static final String SAVINGS_URL = "/savings";
	
	public SavingController(GenericService<Saving> service, Class<Saving> typeParameterClass) {
		super(service, typeParameterClass);

		get(SAVINGS_URL, (req, res) -> getAll(req,res));
		
		get(SAVINGS_URL + ID_PARAM_URL, (req, res) -> getById(req,res));
		
		post(SAVINGS_URL, (req, res) -> create(req,res));
		
		put(SAVINGS_URL, (req, res) -> update(req,res));
		
		spark.Spark.delete(SAVINGS_URL + ID_PARAM_URL, (req, res) -> delete(req,res));
	}
}
