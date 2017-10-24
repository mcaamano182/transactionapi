package test;

import org.junit.Test;

import com.google.gson.Gson;

import model.Currency;
import model.Currency;
import service.CurrencyService;
import service.CurrencyService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import spark.Request;
import spark.Response;
import web.CurrencyController;
import web.CurrencyController;

public class CurrencyTest {
	
	private final static Gson gson = new Gson();
	
	@Test
	public void createCurrencyOKTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        CurrencyService service = new CurrencyService();
		CurrencyController controller = new CurrencyController(service, Currency.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		when(request.body()).thenReturn(gson.toJson(currency));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		assertTrue(service.getAll().size() == 1);
		
		assertTrue(service.getById(1L).getConversionRatio() == currency.getConversionRatio());
		assertEquals(service.getById(1L).getName(), currency.getName());
		assertEquals(service.getById(1L).getSymbol(), currency.getSymbol());
		
		
	}
	
	@Test
	public void createCurrencyERRORTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        

        CurrencyService service = new CurrencyService();
		CurrencyController controller = new CurrencyController(service, Currency.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setSymbol("mocked-symbol");
		
		
		when(request.body()).thenReturn(gson.toJson(currency));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		//the currency must not be created if does not have name
		assertTrue(service.getAll().size() == 0);
		
		
	}
	
	@Test
	public void updateCurrencyTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        CurrencyService service = new CurrencyService();
		CurrencyController controller = new CurrencyController(service, Currency.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		when(request.body()).thenReturn(gson.toJson(currency));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		currency = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		currency.setConversionRatio(2.1);
		currency.setName("mocked-name2");
		currency.setSymbol("mocked-symbol2");
		
		when(request.body()).thenReturn(gson.toJson(currency));
		
		controller.update(request, response);
		
		Currency updated = service.getById(1L); 
		
		assertTrue(updated.getConversionRatio() == currency.getConversionRatio());
		assertEquals(updated.getName(), currency.getName());
		assertEquals(updated.getSymbol(), currency.getSymbol());
		
	}
	
	@Test
	public void deleteCurrencyTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        CurrencyService service = new CurrencyService();
		CurrencyController controller = new CurrencyController(service, Currency.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		when(request.body()).thenReturn(gson.toJson(currency));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		currency = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		when(request.params(":id")).thenReturn("2");
		
		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 1);

		when(request.params(":id")).thenReturn("1");

		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 0);
		
	}

}
