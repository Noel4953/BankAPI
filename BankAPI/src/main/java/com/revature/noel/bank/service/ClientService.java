package com.revature.noel.bank.service;

import java.util.Set;

import com.revature.noel.bank.model.Clients;

public interface ClientService {
//Implement CRUD
	//Create
	boolean uploadClient(Clients client);
	
	
	//Read
	Set<Clients> getAllClients();
	//Set<Clients> getClientsByName(String name);
	Clients getClientById(int id);
	
	//update
	Clients updateClient(Clients client);
	
	//Delete
	boolean deleteClientById(int id);


}
