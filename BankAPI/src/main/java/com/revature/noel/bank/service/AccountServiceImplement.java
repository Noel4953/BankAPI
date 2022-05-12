package com.revature.noel.bank.service;

import java.util.Set;

import com.revature.noel.bank.daos.AccountDAO;
import com.revature.noel.bank.model.Account;

public class AccountServiceImplement implements AccountService {
	private AccountDAO dao;
	
	
	public AccountServiceImplement(AccountDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean createAccount(Account account) {
		return this.dao.createAccount(account);
	}

	@Override
	public Set<Account> getAllAccounts(int id) {
		return this.dao.getAllAccounts(id);
	}

	@Override
	public Account getAccountById(int id) {
		return this.dao.getAccountById(id);
	}

	@Override
	public Set<Account> findFilteredAccounts(int min, int max) {
		// TODO Auto-generated method stub
		return this.dao.findFilteredAccounts(min,max);
	}
	
	@Override
	public Account getAccountByAccNum(int accNum) {
		return this.dao.getAccountByAccNum(accNum);
	}

	@Override
	public Account updateAccount(Account account) {
		return this.dao.updateAccount(account);
	}
	
	
	@Override
	public boolean deleteAccountById(int accNum) {
		return this.dao.deleteAccountById(accNum);
	}

	@Override
	public Account withdrawAccount(Account account) {
		return this.dao.withdrawAccount(account);
	}

	@Override
	public Account depositAccount(Account account) {
		return this.dao.depositAccount(account);
	}


}
