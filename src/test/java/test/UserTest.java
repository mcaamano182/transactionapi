package test;

import org.junit.Test;

import com.google.gson.Gson;

import model.User;
import service.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import spark.Request;
import spark.Response;
import web.UserController;

public class UserTest {
	
	private final static Gson gson = new Gson();
	
	@Test
	public void createUserOKTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
		UserController userController = new UserController(userService, User.class);

		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		when(request.body()).thenReturn(gson.toJson(user));
	
		assertTrue(userService.getAll().size() == 0);
		
		userController.create(request, response);
		
		assertTrue(userService.getAll().size() == 1);
		
	}
	
	@Test
	public void createUserERRORTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
		UserController userController = new UserController(userService, User.class);

		User user = new User();
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		when(request.body()).thenReturn(gson.toJson(user));
	
		assertTrue(userService.getAll().size() == 0);
		
		userController.create(request, response);
		
		//the user must not be created if does not have document
		assertTrue(userService.getAll().size() == 0);
		
		
	}
	
	@Test
	public void updateUserTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
		UserController userController = new UserController(userService, User.class);

		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		when(request.body()).thenReturn(gson.toJson(user));
	
		assertTrue(userService.getAll().size() == 0);
		
		userController.create(request, response);
		
		user = userService.getById(1L); 
		
		assertTrue(userService.getAll().size() == 1);
		
		user.setDocument("mocked-doc2");
		user.setEmail("mocked-email2");
		user.setLastname("mocked-lastname2");
		user.setName("mocked-name2");
		user.setPhone("mocked-phone2");
		user.setPin("mocekd-pin2");
	
		when(request.body()).thenReturn(gson.toJson(user));
		
		userController.update(request, response);
		
		User updated = userService.getById(1L); 
		
		assertEquals(updated.getDocument(), user.getDocument());
		assertEquals(updated.getEmail(), user.getEmail());
		assertEquals(updated.getId(), user.getId());
		assertEquals(updated.getLastname(), user.getLastname());
		assertEquals(updated.getName(), user.getName());
		assertEquals(updated.getPhone(), user.getPhone());
		assertEquals(updated.getPin(), user.getPin());
		
	}
	
	@Test
	public void deleteUserTest() {
		Request request = mock(Request.class);       
        Response response = mock(Response.class);    
        
        UserService userService = new UserService();
		UserController userController = new UserController(userService, User.class);

		User user = new User();
		user.setDocument("mocked-doc");
		user.setEmail("mocked-email");
		user.setLastname("mocked-lastname");
		user.setName("mocked-name");
		user.setPhone("mocked-phone");
		user.setPin("mocekd-pin");
		
		when(request.body()).thenReturn(gson.toJson(user));
	
		assertTrue(userService.getAll().size() == 0);
		
		userController.create(request, response);
		
		user = userService.getById(1L); 
		
		assertTrue(userService.getAll().size() == 1);
		
		when(request.params(":id")).thenReturn("2");
		
		userController.delete(request, response);

		assertTrue(userService.getAll().size() == 1);

		when(request.params(":id")).thenReturn("1");

		userController.delete(request, response);
		
		assertTrue(userService.getAll().size() == 0);
		
	}

}
