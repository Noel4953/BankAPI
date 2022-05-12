package com.revature.noel.bank.daos;
import java.util.Set;

import com.revature.noel.bank.model.Clients;
public interface ClientDAO {
//Implement CRUD
	//Create
	boolean createClient(Clients client);
	
	//Read
	Set<Clients> getAllClients();
	Clients getClientById(int id);
	//Clients getClientByName(String name);
	
	//Update
	Clients updateClient(Clients client);
	
	//Delete
	boolean deleteClientById(int id);
}
