package com.revature.noel.bank.controller;

import java.util.Set;

import org.eclipse.jetty.util.ajax.JSON;

import com.revature.noel.bank.daos.ClientDBDAO;
import com.revature.noel.bank.model.Clients;
import com.revature.noel.bank.service.ClientService;
import com.revature.noel.bank.service.ClientServiceImplement;

import io.javalin.http.Handler;


public class ClientController {
	
	private static ClientService clientService = new ClientServiceImplement(new ClientDBDAO());
	
	public static Handler getAllClientsHandler = ctx->{
		try{Set<Clients> cList = clientService.getAllClients();
			if(cList.size()==0)
				ctx.status(404).result("No Clients Available");
			else {
		ctx.json(cList);
		ctx.status(200);}
		}catch(Exception e){
			ctx.status(400);
		}
		};
		
	public static Handler getClientByIdHandler = ctx->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Clients clientInfo = clientService.getClientById(id);
		
		try {ctx.json(clientInfo);}
		catch(Exception e) {	
		ctx.status(404).result("Client Doesn't Exist");}
		};
	
	public static Handler addNewClientHandler = ctx->{
		Clients c1 = ctx.bodyAsClass(Clients.class);
		try{boolean newClient = clientService.uploadClient(c1);
		ctx.status(201).result(JSON.toString(newClient));
		if(newClient) {
			ctx.status(201).result("Client Created");}
		else {
			ctx.status(404).result("User Already Exist");}
		}catch(Exception e){
			ctx.status(404);
		}
		};
	
	
	public static Handler updateClientHandler = ctx->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Clients c1 = ctx.bodyAsClass(Clients.class);
		c1.setId(id);
		Clients editClient;
		try{
			editClient= clientService.updateClient(c1);
			if(editClient == null)
				ctx.status(404).result("No User Exist");
			else
			ctx.status(201).result("Client Updated");
		
		}catch (Exception e) { 
			ctx.status(404);
			}	
		};
	
	public static Handler deleteClientHandler = ctx->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		boolean	clientDeleted;
		try {
		clientDeleted = clientService.deleteClientById(id);
		
		if(clientDeleted) {
			ctx.status(205).result("Client and Accounts Successfully Deleted");}
		else {
			ctx.status(404).result("User Doesnt Exist");}}
		catch(Exception e){
			ctx.status(404);
			
		}
		};
	
	
}
