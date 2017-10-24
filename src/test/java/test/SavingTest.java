package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.google.gson.Gson;

import model.Currency;
import model.Saving;
import model.SavingType;
import service.SavingService;
import spark.Request;
import spark.Response;
import web.SavingController;

public class SavingTest {
	
	private final static Gson gson = new Gson();
	
	@Test
	public void createSavingOKTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService service = new SavingService();
		SavingController controller = new SavingController(service, Saving.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(100.1);
		saving.setCurrency(currency);
		saving.setType(SavingType.SAVINGS);
		
		when(request.body()).thenReturn(gson.toJson(saving));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		assertTrue(service.getAll().size() == 1);
		
		assertTrue(service.getById(1L).getBalance() == saving.getBalance());
		assertEquals(service.getById(1L).getType(), saving.getType());
		assertEquals(service.getById(1L).getCurrency().getName(), saving.getCurrency().getName());
		
		
	}
	
	@Test
	public void createSavingERRORTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        

        SavingService service = new SavingService();
		SavingController controller = new SavingController(service, Saving.class);

		Saving saving = new Saving();
		saving.setBalance(0.1);
		saving.setType(SavingType.SAVINGS);
		
		
		when(request.body()).thenReturn(gson.toJson(saving));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		//the saving must not be created if does not hahave a currency specified
		assertTrue(service.getAll().size() == 0);
		
		
	}
	
	@Test
	public void updateSavingTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService service = new SavingService();
		SavingController controller = new SavingController(service, Saving.class);


		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(100.1);
		saving.setCurrency(currency);
		saving.setType(SavingType.SAVINGS);
		
		when(request.body()).thenReturn(gson.toJson(saving));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		saving = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		saving.setBalance(2100.1);
		saving.setCurrency(currency);
		saving.setType(SavingType.CHECKING);
		
		
		when(request.body()).thenReturn(gson.toJson(saving));
		
		controller.update(request, response);
		
		Saving updated = service.getById(1L); 
		
		assertTrue(updated.getBalance() == saving.getBalance());
		assertEquals(updated.getType(), saving.getType());
		
	}
	
	@Test
	public void deleteSavingTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService service = new SavingService();
		SavingController controller = new SavingController(service, Saving.class);


		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(100.1);
		saving.setCurrency(currency);
		saving.setType(SavingType.SAVINGS);
		
		
		when(request.body()).thenReturn(gson.toJson(saving));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		saving = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		when(request.params(":id")).thenReturn("2");
		
		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 1);

		when(request.params(":id")).thenReturn("1");

		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 0);
		
	}

}
