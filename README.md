# Transactions API

API for transfers between bank accounts. The API offers five different endpoints for create, update, or delete the entities:

    - Users ("/users") :
        GET ("/users") returns all the users created.
        GET ("/users/id") returns the specific user.
        POST ("/users") create a new user.
        PUT ("/users") update the specified user.
        DELETE ("/users/id") delete the specified user.
        
    - Currencies ("/currencies") :
        GET ("/currencies") returns all the currencies created.
        GET ("/currencies/id") returns the specific currency.
        POST ("/currencies") create a new currency.
        PUT ("/currencies") update the specified currency.
        DELETE ("/currencies/id") delete the specified currency.
        
     - Accounts ("/accounts") :
        GET ("/accounts") returns all the accounts created.
        GET ("/accounts/id") returns the specific account.
        POST ("/accounts") create a new account.
        PUT ("/accounts") update the specified account.
        DELETE ("/accounts/id") delete the specified account.
        
     - Savings ("/savings") :
        GET ("/savings") returns all the savings created.
        GET ("/savings/id") returns the specific saving.
        POST ("/savings") create a new saving.
        PUT ("/savings") update the specified saving.
        DELETE ("/savings/id") delete the specified saving.
        
    - Transactions ("/transactions") :
        GET ("/transactions") returns all the transactions created.
        GET ("/transactions/id") returns the specific transaction
        POST ("/transactions") create a new transaction.
        PUT ("/transactions") update the specified transaction.
        NOTE: Transactions cannot be deleted.

 The main controller is located in web.MainController and the endpoints are hosted on localhost:4567
