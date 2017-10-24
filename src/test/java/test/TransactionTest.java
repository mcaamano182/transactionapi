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
import model.Transaction;
import service.SavingService;
import service.TransactionService;
import spark.Request;
import spark.Response;
import web.TransactionController;

public class TransactionTest {
	
	private final static Gson gson = new Gson();
	
	@Test
	public void createTransactionOKTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService savingService = new SavingService();
        TransactionService service = new TransactionService(savingService);
		TransactionController controller = new TransactionController(service, Transaction.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(0.1);
		saving.setType(SavingType.SAVINGS);
		
		Saving saving2 = new Saving();
		saving2.setBalance(0.1);
		saving2.setType(SavingType.SAVINGS);
		
		Transaction transaction = new Transaction();
		transaction.setNumber("1231243354234234");
		transaction.setAmmount(0.1);
		transaction.setFee(0.0);
		transaction.setCurrency(currency);
		transaction.setSavingDestination(saving2);
		transaction.setSavingSource(saving2);
		
		when(request.body()).thenReturn(gson.toJson(transaction));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		assertTrue(service.getAll().size() == 1);
		
		assertEquals(service.getById(1L).getNumber(), transaction.getNumber());
		assertTrue(service.getById(1L).getAmmount() == transaction.getAmmount());
		
	}
	
	@Test
	public void createTransactionERRORTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService savingService = new SavingService();
        TransactionService service = new TransactionService(savingService);
		TransactionController controller = new TransactionController(service, Transaction.class);

		Transaction transaction = new Transaction();
		transaction.setNumber("q23132r435423");

		when(request.body()).thenReturn(gson.toJson(transaction));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		//the transaction must not be created if does not have source or destination saving
		assertTrue(service.getAll().size() == 0);
		
		
	}
	
	@Test
	public void updateTransactionTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService savingService = new SavingService();
        TransactionService service = new TransactionService(savingService);
		TransactionController controller = new TransactionController(service, Transaction.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(100.1);
		saving.setType(SavingType.SAVINGS);
		saving.setCurrency(currency);
		
		Saving saving2 = new Saving();
		saving2.setBalance(0.1);
		saving2.setType(SavingType.SAVINGS);
		saving2.setCurrency(currency);
		
		Transaction transaction = new Transaction();
		transaction.setNumber("1231243354234234");
		transaction.setAmmount(0.2);
		transaction.setFee(0.0);
		transaction.setCurrency(currency);
		transaction.setSavingDestination(saving2);
		transaction.setSavingSource(saving);
		
		when(request.body()).thenReturn(gson.toJson(transaction));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		transaction = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		transaction.setNumber("2");
		
		when(request.body()).thenReturn(gson.toJson(transaction));
		
		controller.update(request, response);
		
		Transaction updated = service.getById(1L); 
		
		assertTrue(updated.getNumber() == transaction.getNumber());
		
	}
	
	@Test
	public void deleteTransactionTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        SavingService savingService = new SavingService();
        TransactionService service = new TransactionService(savingService);
		TransactionController controller = new TransactionController(service, Transaction.class);

		Currency currency = new Currency();
		currency.setConversionRatio(0.1);
		currency.setName("mocked-name");
		currency.setSymbol("mocked-symbol");
		
		Saving saving = new Saving();
		saving.setBalance(0.1);
		saving.setType(SavingType.SAVINGS);
		
		Saving saving2 = new Saving();
		saving2.setBalance(0.1);
		saving2.setType(SavingType.SAVINGS);
		
		Transaction transaction = new Transaction();
		transaction.setNumber("1231243354234234");
		transaction.setAmmount(0.1);
		transaction.setFee(0.0);
		transaction.setCurrency(currency);
		transaction.setSavingDestination(saving2);
		transaction.setSavingSource(saving2);
		
		when(request.body()).thenReturn(gson.toJson(transaction));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		transaction = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		when(request.params(":id")).thenReturn("2");
		
		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 1);

		when(request.params(":id")).thenReturn("1");

		controller.delete(request, response);
		
		//the transactions cannot be deleted
		assertTrue(service.getAll().size() == 1);
		
	}

}
