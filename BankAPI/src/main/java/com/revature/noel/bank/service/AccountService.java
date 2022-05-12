package com.revature.noel.bank.service;

import java.util.Set;

import com.revature.noel.bank.model.Account;

public interface AccountService {
	//Implementt CRUD
		//Create
		boolean createAccount(Account account);
		
	//Read
		Set<Account> getAllAccounts(int id);
		Account getAccountById(int id);
		Account getAccountByAccNum(int accNum);
		Set<Account> findFilteredAccounts(int min, int max);
		
	//Update
		Account updateAccount(Account account);
		Account withdrawAccount(Account account);
		Account depositAccount(Account account);
		
	//Delete
		boolean deleteAccountById(int id);


}
