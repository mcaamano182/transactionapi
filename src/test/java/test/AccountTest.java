package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.Gson;

import model.Account;
import model.Currency;
import model.User;
import service.AccountService;
import service.UserService;
import spark.Request;
import spark.Response;
import web.AccountController;

public class AccountTest {
	
	private final static Gson gson = new Gson();
	
	@Test
	public void createAccountOKTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
        AccountService service = new AccountService(userService);
		AccountController controller = new AccountController(service, Account.class);

		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		
		Account account = new Account();
		account.setNumber("1231243354234234");
		account.setOwner(user);
		account.setSavings(new ArrayList<>());
		
		when(request.body()).thenReturn(gson.toJson(account));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		assertTrue(service.getAll().size() == 1);
		
		assertEquals(service.getById(1L).getNumber(), account.getNumber());
		assertEquals(service.getById(1L).getOwner().getName(), account.getOwner().getName());
		
	}
	
	@Test
	public void createAccountERRORTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
        AccountService service = new AccountService(userService);
		AccountController controller = new AccountController(service, Account.class);

		Account account = new Account();
		account.setNumber("q23132r435423");

		when(request.body()).thenReturn(gson.toJson(account));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		//the account must not be created if does not have an owner
		assertTrue(service.getAll().size() == 0);
		
		
	}
	
	@Test
	public void updateAccountTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
        AccountService service = new AccountService(userService);
		AccountController controller = new AccountController(service, Account.class);


		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		
		Account account = new Account();
		account.setNumber("1231243354234234");
		account.setOwner(user);
		account.setSavings(new ArrayList<>());
		
		when(request.body()).thenReturn(gson.toJson(account));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		account = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		account.setNumber("2");
		
		when(request.body()).thenReturn(gson.toJson(account));
		
		controller.update(request, response);
		
		Account updated = service.getById(1L); 
		
		assertTrue(updated.getNumber() == account.getNumber());
		
	}
	
	@Test
	public void deleteAccountTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
        AccountService service = new AccountService(userService);
		AccountController controller = new AccountController(service, Account.class);

		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		
		Account account = new Account();
		account.setNumber("1231243354234234");
		account.setOwner(user);
		account.setSavings(new ArrayList<>());
		
		when(request.body()).thenReturn(gson.toJson(account));
	
		assertTrue(service.getAll().size() == 0);
		
		controller.create(request, response);
		
		account = service.getById(1L); 
		
		assertTrue(service.getAll().size() == 1);
		
		when(request.params(":id")).thenReturn("2");
		
		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 1);

		when(request.params(":id")).thenReturn("1");

		controller.delete(request, response);
		
		assertTrue(service.getAll().size() == 0);
		
	}

}
