package com.revature.noel.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.noel.bank.model.Clients;
import com.revature.noel.bank.utils.ConnectionUtils;

public class ClientDBDAO implements ClientDAO {
	static ResultSet rs;
	static PreparedStatement ps;
	
	@Override
	public boolean createClient(Clients client) {
		try(Connection conn = ConnectionUtils.createConnection()){
			
			String addClient = "insert into clients (client_id,fname,lname) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(addClient);
			ps.setInt(1, client.getId());
			ps.setString(2, client.getFname());
			ps.setString(3, client.getLname());
			ps.execute();
			return true;
		}catch (SQLException e) {
			return false;
		}
		
	}


	@Override
	public Set<Clients> getAllClients() {
		
		try(Connection conn = ConnectionUtils.createConnection()) {
			String selectAllClients = "select * from clients";
		ps= conn.prepareStatement(selectAllClients);
		rs = ps.executeQuery();
		Set<Clients> cList = new HashSet<Clients>();
		Clients clients;
		while(rs.next()) {
			int id = rs.getInt("client_id");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			clients= new Clients(id,fname,lname);
			cList.add(clients);
			}
		return cList;
		}catch (SQLException e) {
			return null;
			}
	
	}

	@Override
	public Clients getClientById(int id) {
		try(Connection conn = ConnectionUtils.createConnection()){
			String selectClient = "select * from clients WHERE client_id =?";
			ps = conn.prepareStatement(selectClient);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			Clients c = null;
				while(rs.next()) {
					int clientId = rs.getInt("client_id");
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					c = new Clients(clientId, fname, lname);}
			return c;
		}catch(SQLException e) {
			return null;}
		
	}


	@Override
	public Clients updateClient(Clients client) {
		
		try(Connection conn = ConnectionUtils.createConnection()) {
			String updateClient = "update clients set fname=? ,lname = ? where client_id=?";
			ps = conn.prepareStatement(updateClient);
			
			ps.setString(1, client.getFname());
			ps.setString(2, client.getLname());
			ps.setInt(3, client.getId());
			if(ps.executeUpdate()==0) 
				return null;
			else
			ps.execute();
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean deleteClientById(int id) {
		try(Connection conn = ConnectionUtils.createConnection();) {
			String deleteClient = "delete from clients where client_id = ?";
			ps = conn.prepareStatement(deleteClient);
			ps.setInt(1, id);
			if(ps.executeUpdate() == 0) 
				return false;
			else
			ps.execute();
			return true;
		} catch (SQLException e) {
		return false;}
	}
	
}
