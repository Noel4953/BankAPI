package com.revature.noel.bank.controller;

import java.util.Set;

import com.revature.noel.bank.daos.AccountDBDAO;
import com.revature.noel.bank.model.Account;
import com.revature.noel.bank.service.AccountService;
import com.revature.noel.bank.service.AccountServiceImplement;

import io.javalin.http.Handler;

public class AccountController {
	
private static AccountService accountService = new AccountServiceImplement(new AccountDBDAO());

public static Handler getAllAccountsHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("id"));
	try{Set<Account> aList = accountService.getAllAccounts(id);
		if(aList.size()==0)
			ctx.status(404).result("There are no Accounts");
		else {
		ctx.json(aList);
		ctx.status(201);}
	}catch(Exception e) {
		ctx.status(404).result("There are no Accounts");
	}
	};
	
public static Handler getAccountByIdHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("id"));
	
	try {Account accountInfo = accountService.getAccountById(id);
		ctx.json(accountInfo);}
	catch(Exception e) {	
	ctx.status(404).result("Account Doesn't Exist");}
	};

public static Handler filterAccountHandler = ctx->{
	int min = Integer.parseInt(ctx.pathParam("min"));
	int max = Integer.parseInt(ctx.pathParam("max"));
	Set<Account> fList = accountService.findFilteredAccounts(min,max);
	
		try {
			ctx.json(fList);
			ctx.status(201);
		}catch(Exception e) {
			ctx.status(404).result("No Account Match These Parameters");
		}
	
	};
	
public static Handler addNewAccountHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("id"));
	Account a1 = ctx.bodyAsClass(Account.class);
	a1.setClientId(id);
	try{
		boolean newAccount = accountService.createAccount(a1);
		if(newAccount)
			ctx.status(201).result("Congrats, New Account Created");
		else
			ctx.status(404).result("Unavailable");
		}catch(Exception e) {
			ctx.status(404);	}
	};


public static Handler updateAccountHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("accNum"));
	Account a1 = ctx.bodyAsClass(Account.class);
	a1.setAccNum(id);
	Account editAccount;
	try{
		editAccount= accountService.updateAccount(a1);
		if(editAccount == null)
			ctx.status(404).result("No User Exist");
		else
		ctx.status(201).result("Account Updated");
	
	}catch (Exception e) { 
		ctx.status(404);
		}	
	};

public static Handler deleteAccountHandler = ctx->{
	int accNum = Integer.parseInt(ctx.pathParam("accNum"));
	boolean deleteAccount;
	try{
		deleteAccount = accountService.deleteAccountById(accNum);
		
		if(deleteAccount) {
			ctx.status(205).result("Account Successfully Deleted");
		}
		else {
			ctx.status(404).result("User Account Doesnt Exist");
		}
	}catch(Exception e){
		ctx.status(404);
	}
	};
	
public static Handler depositHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("accNum"));
	Account a1 = ctx.bodyAsClass(Account.class);
	a1.setAccNum(id);
	Account editAccount;
	try{
		editAccount= accountService.depositAccount(a1);
		if(editAccount == null)
			ctx.status(404).result("No User Exist");
		else
		ctx.status(201).result("Money Successfully Deposited");
	
	}catch (Exception e) { 
		ctx.status(404);
		}	
};

@SuppressWarnings("unused")
public static Handler withdrawHandler = ctx->{
	int id = Integer.parseInt(ctx.pathParam("accNum"));
	Account a1 = ctx.bodyAsClass(Account.class);
	a1.setAccNum(id);
	Account editAccount;
	try{
		editAccount= accountService.withdrawAccount(a1);
		if(editAccount.getBalance() < editAccount.getAmount())
			ctx.status(404).result("Insufficiant Funds");
		else if(editAccount == null)
			ctx.status(404).result("No User Exist");
		else
		ctx.status(201).result("Account Updated");
	
	}catch (Exception e) { 
		ctx.status(404);
		}	
};


}
