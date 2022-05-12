package com.revature.noel.main;

import com.revature.noel.bank.controller.AccountController;
import com.revature.noel.bank.controller.ClientController;

import io.javalin.Javalin;

public class Main {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();

		//GET /clients => gets all clients return 200
		app.get("/clients", ClientController.getAllClientsHandler);
		
		//GET /clients/10 => get client with id of 10 return 404 if no such client exist
		app.get("/clients/{id}", ClientController.getClientByIdHandler);
		
		//GET /clients/7/accounts => get all accounts for client 7 return 404 if no client exists
		app.get("/clients/{id}/accounts", AccountController.getAllAccountsHandler);
		
		//(Optional) GET /accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for between 400 and 200
		app.get("/accounts/{min}/{max}", AccountController.filterAccountHandler);
		
		//GET /accounts/4 => get account with id 4 return 404 if no account or client exists
		app.get("/accounts/{id}", AccountController.getAccountByIdHandler);
		
		//POST /clients => Creates a new client return a 201 status code
		app.post("/clients", ClientController.addNewClientHandler);
		
		//POST /clients/5/accounts =>creates a new account for client with the id of 5 return a 201 status code
		app.post("/clients/{id}/accounts", AccountController.addNewAccountHandler);
		
		//PUT /clients/12 => updates client with id of 12 return 404 if no such client exist
		app.put("/clients/{id}", ClientController.updateClientHandler);
		
		//PUT /accounts/3 => update account with the id 3 return 404 if no account or client exists 
		app.put("/accounts/{accNum}", AccountController.updateAccountHandler);
		
		//DELETE /clients/15 => deletes client with the id of 15 return 404 if no such client exist return 205 if success
		app.delete("/clients/{id}", ClientController.deleteClientHandler);
		
		//DELETE /accounts/6 => delete account 6 return 404 if no account or client exists
		app.delete("/accounts/{accNum}", AccountController.deleteAccountHandler);
		
		// PATCH /accounts/12/deposit => deposit given amount (Body {"amount":500} ) return 404 if no account exists
		app.patch("/accounts/{accNum}/deposit", AccountController.depositHandler);
		
		//PATCH /accounts/12/withdraw => deposit given amount (Body {"amount":500} ) return 404 if no account exists return 422 if insufficient funds #
		app.patch("/accounts/{accNum}/withdraw", AccountController.withdrawHandler);
		
	}

}
