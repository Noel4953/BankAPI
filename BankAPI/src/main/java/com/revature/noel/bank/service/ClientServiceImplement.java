package com.revature.noel.bank.service;

import java.util.Set;

import com.revature.noel.bank.daos.ClientDAO;
import com.revature.noel.bank.model.Clients;

public class ClientServiceImplement implements ClientService {
	private ClientDAO dao;
	
	public ClientServiceImplement(ClientDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean uploadClient(Clients client) {
		
		return this.dao.createClient(client);
	}

	@Override
	public Set<Clients> getAllClients() {
		return this.dao.getAllClients();
	}

//	@Override
//	public Set<Clients> getClientsByName(String name) {
//		
//		return null;
//	}

	@Override
	public Clients getClientById(int id) {
		return this.dao.getClientById(id);
	}

	@Override
	public Clients updateClient(Clients client) {
		return this.dao.updateClient(client);
	}

	@Override
	public boolean deleteClientById(int id) {
		// TODO Auto-generated method stub
		return this.dao.deleteClientById(id);
	}

	
	

}
