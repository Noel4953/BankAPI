package com.revature.noel.bank.tests.account;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.noel.bank.daos.AccountDAO;
import com.revature.noel.bank.daos.AccountDBDAO;
import com.revature.noel.bank.daos.ClientDAO;
import com.revature.noel.bank.daos.ClientDBDAO;
import com.revature.noel.bank.model.Account;
import com.revature.noel.bank.model.Clients;

@ExtendWith(MockitoExtension.class)

public class HandlerControllerTest {
private static AccountDAO dao = new AccountDBDAO();
private static ClientDAO cdao = new ClientDBDAO();
private static Account a1;
private static Clients c1;

@Test
@BeforeAll
static public void ClReset() {
	c1 = new Clients(25,"Leon","Jenkins");
	a1 = new Account(2, 25, "Checkings", 1500);
	cdao.createClient(c1);
	dao.createAccount(a1);
}
@Test
@AfterAll
static void DBReset() {
	dao.deleteAccountById(c1.getId());
	cdao.deleteClientById(c1.getId());
}
@Test
@Order(1)
public void CreateClientTest() {
	Clients d = new Clients(21,"Lone","Junk");
	cdao.createClient(d);
	Assertions.assertEquals(21, d.getId());
	Assertions.assertEquals("Lone", d.getFname());
	Assertions.assertEquals("Junk", d.getLname());
	cdao.deleteClientById(21);
}
@Test
@Order(2)
public void CreateAccountTest() {
	Account a = new Account(2, 5, "Checkings", 1500);
	dao.createAccount(a);
	a1 = a;
	Assertions.assertEquals(2, a1.getAccNum());
	Assertions.assertEquals(5, a1.getClientId());
	Assertions.assertEquals("Checkings", a1.getAccType());
	Assertions.assertEquals(1500, a1.getBalance());
}
@Test
@Order(3)
public void sadCreatAccountTest() {
	Account a = new Account(2, 5, "Checkings", 1500);
	dao.createAccount(a);
	a1 = a;
	Assertions.assertNotEquals(3, a1.getAccNum());
}
@Test
@Order(4)
public void getAccountByIdTest() {
	Account a = new Account(2, 5, "Checkings", 1500);
	a.setAccNum(2);
	a.setAmount(1500);
	a.setAccType("Checkings");
	a.setClientId(5);
	a1 = dao.getAccountById(5);
	Assertions.assertEquals(null, a1);
	
	
}

@Test
@Order(5)
public void getAllAccountsTest() {
	Account c4 = new Account(1,25, "Shakira", 99);
	Account c5 = new Account(9,25, "john", 33);
	dao.createAccount(c4);
	dao.createAccount(c5);
		Set<Account> aList1 = dao.getAllAccounts(25);
		Set<Account> aList = new HashSet<Account>();
		aList.add(c4);
		aList.add(c5);
		aList.add(a1);
		Assertions.assertEquals(aList.size(),aList1.size());
		
	}
@Test
@Order(5)
public void getAllClientsTest() {
	Clients c4 = new Clients(99,"Shakira","Tommy");
	Clients c5 = new Clients(45,"john","Darien");
	cdao.createClient(c5);
	cdao.createClient(c4);
		Set<Clients> cList1 = cdao.getAllClients();
		Set<Clients> cList = new HashSet<Clients>();
		cList.add(c1);
		cList.add(c5);
		cList.add(c4);
		Assertions.assertNotEquals(cList.size(),cList1.size());
		
	}
@Test
@Order(6)
public void sadGetAllAccountsTest() {
	Account c4 = new Account(1,12, "Shakira", 99);
	Account c5 = new Account(9,12, "john", 33);

		Set<Account> cList1 = dao.getAllAccounts(12);
		Set<Account> cList = new HashSet<Account>();
		cList.add(c4);
		cList.add(c5);
		
		Assertions.assertNotEquals(cList1.toString(), cList.toString());
	
}



}
