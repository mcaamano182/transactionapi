package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.Currency;
import service.GenericService;

public class CurrencyController extends GenericController<Currency>{

	private static final String CURRENCIES_URL = "/currencies";
	
	public CurrencyController(GenericService<Currency> service, Class<Currency> typeParameterClass) {
		super(service, typeParameterClass);

		get(CURRENCIES_URL, (req, res) -> getAll(req,res));
		
		get(CURRENCIES_URL + ID_PARAM_URL, (req, res) -> getById(req,res));
		
		post(CURRENCIES_URL, (req, res) -> create(req,res));
		
		put(CURRENCIES_URL, (req, res) -> update(req,res));
		
		spark.Spark.delete(CURRENCIES_URL + ID_PARAM_URL, (req, res) -> delete(req,res));
	}
}