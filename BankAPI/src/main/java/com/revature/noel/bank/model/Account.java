package com.revature.noel.bank.model;

public class Account {
	private int accNum;
	private int clientId;
	private String accType;
	private int balance;
	private int amount;



public Account() {
	super();
}

public Account(int accNum, int clientId, String accType, int balance, int amount) {
	super();
	this.accNum = accNum;
	this.clientId = clientId;
	this.accType = accType;
	this.balance = balance;
	this.amount = amount;
}

public Account(int accNum, int clientId, String accType, int balance) {
	super();
	this.accNum = accNum;
	this.clientId = clientId;
	this.accType = accType;
	this.balance = balance;
}

public Account(int balance, String accType, int clientId) {
	super();
	this.balance = balance;
	this.accType = accType;
	this.clientId = clientId;
}

public int getBalance() {
	return balance;
}



public void setBalance(int balance) {
	this.balance = balance;
}



public String getAccType() {
	return accType;
}



public void setAccType(String accType) {
	this.accType = accType;
}




public int getAccNum() {
	return accNum;
}






public void setAccNum(int accNum) {
	this.accNum = accNum;
}






public int getClientId() {
	return clientId;
}






public void setClientId(int clientId) {
	this.clientId = clientId;
}






public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

@Override
public String toString() {
	return "Account [balance=" + balance + ", accType=" + accType + ", accNum=" + accNum + "]";
}



















}
