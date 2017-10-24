package web;

import model.Account;
import model.Currency;
import model.Saving;
import model.Transaction;
import model.User;
import service.AccountService;
import service.CurrencyService;
import service.SavingService;
import service.TransactionService;
import service.UserService;

public class MainController {
	
	public static void main(String[] args) {
		UserService userService = new UserService();
		UserController userController = new UserController(userService, User.class);
		
		CurrencyService currencyService = new CurrencyService();
		CurrencyController currencyController = new CurrencyController(currencyService, Currency.class);
		
		AccountService accountService = new AccountService(userService);
		AccountController accountController = new AccountController(accountService, Account.class);

		SavingService savingService = new SavingService();
		SavingController savingController = new SavingController(savingService, Saving.class);
		
		TransactionService transactionService = new TransactionService(savingService);
		TransactionController transactionController = new TransactionController(transactionService, Transaction.class);
	}

}
