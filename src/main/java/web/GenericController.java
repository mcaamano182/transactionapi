package web;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;

import model.Error;
import model.InvalidOperationException;
import service.GenericService;
import spark.Request;
import spark.Response;
import spark.utils.CollectionUtils;
import spark.utils.StringUtils;


public abstract class GenericController<T> {

	public static final String ID_PARAM_URL = "/:id";
	
	final Class<T> typeParameterClass;
	private GenericService<T> service;
	private final static Gson gson = new Gson();
	
	public GenericController (GenericService<T> service, Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
		this.service = service;
    }
	
	public Object update(Request req, Response res) {
		String body = req.body();
		T t = gson.fromJson(body,typeParameterClass);
		try {
			this.service.update(t);
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.type("application/json");
		return gson.toJson(t);
	}

	public Object delete(Request req, Response res){
		String id = req.params(":id");
		try {
			return service.delete(Long.valueOf(id));
		} catch (InvalidOperationException e) {
			Error error = new Error();
			res.status(HttpStatus.BAD_REQUEST_400);
			error.setCode(String.valueOf(res.status()));
			error.setMessage(e.getMessage());
			return gson.toJson(error);
		}
	}

	public Object create(Request req, Response res) {
		String body = req.body();
		T obj = gson.fromJson(body, this.typeParameterClass);
		res.type("application/json");
		T created =  null;
		try{
			created = service.create(obj);
		}catch (RuntimeException e) {
			res.status(HttpStatus.BAD_REQUEST_400);
			return gson.toJson(e);
		} catch (InvalidOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(created);
	}

	public Object getById(Request req, Response res) {
		String id = req.params(":id");
		res.type("application/json");
		if(StringUtils.isNotEmpty(id)) {
			T obj = service.getById(Long.valueOf(id));
			return  gson.toJson(obj);
		}
		else {
			return null;
		}
	}
	
	public Object getAll(Request req, Response res) {
		 List<String> target = new LinkedList<String>();
		 List<T> all = service.getAll();
		 if(!CollectionUtils.isEmpty(all)) {
			 all.stream().forEach(e -> target.add(gson.toJson(e)));
		 }
		return target;
	}
}