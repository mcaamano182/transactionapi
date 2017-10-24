package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import model.Transaction;
import service.GenericService;

public class TransactionController extends GenericController<Transaction>{

	private static final String TRANSACTIONS_URL = "/transactions";

	public TransactionController(GenericService<Transaction> service, Class<Transaction> typeParameterClass) {
		super(service, typeParameterClass);

		get(TRANSACTIONS_URL, (req, res) -> getAll(req,res));
		
		get(TRANSACTIONS_URL + ID_PARAM_URL, (req, res) -> getById(req,res));
		
		post(TRANSACTIONS_URL, (req, res) -> create(req,res));
		
		put(TRANSACTIONS_URL, (req, res) -> update(req,res));
		
		spark.Spark.delete(TRANSACTIONS_URL + ID_PARAM_URL, (req, res) -> delete(req,res));
	}
}