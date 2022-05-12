package com.revature.noel.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.noel.bank.model.Account;
import com.revature.noel.bank.utils.ConnectionUtils;

public class AccountDBDAO implements AccountDAO {
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	@Override
	public boolean createAccount(Account account) {
			try(Connection conn = ConnectionUtils.createConnection()){
			
			String addAccount = "insert into accounts (client_id, account_type, balance) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(addAccount);
			ps.setInt(1, account.getClientId());
			ps.setString(2, account.getAccType());
			ps.setInt(3, account.getBalance());
			ps.execute();
			return true;
		}catch (SQLException e) {
			return false;
		}
	}
	
	
	@Override
	public Set<Account> getAllAccounts(int id) {
		try(Connection conn = ConnectionUtils.createConnection()) {
			String selectAllAccounts = "select * from accounts where client_id =?";
		ps= conn.prepareStatement(selectAllAccounts);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		Set<Account> aList = new HashSet<Account>();
		Account account;
		while(rs.next()) {
			int balance = rs.getInt("balance");
			String accType = rs.getString("account_type");
			int accNum = rs.getInt("account_number");
			int clientId= rs.getInt("client_id");
			account = new Account(accNum, clientId, accType, balance);
			aList.add(account);
			}
		return aList;
		}catch (SQLException e) {
			return null;
			}
	}

	@Override
	public Account getAccountById(int id) {
		try(Connection conn = ConnectionUtils.createConnection()){
			String selectAcc = "select * from accounts WHERE account_number =?";
			ps = conn.prepareStatement(selectAcc);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			Account a = null;
				while(rs.next()) {
					int balance = rs.getInt("balance");
					String accType = rs.getString("account_type");
					int clientId = rs.getInt("client_id");
					int accNum = rs.getInt("account_number");
					a = new Account(accNum, clientId, accType, balance);}
				return a;
		}catch(SQLException e) {
				return null;}
	}
	@Override
	public Set<Account> findFilteredAccounts(int min,int max) {
		try(Connection conn = ConnectionUtils.createConnection()) {
			String selectAllAccounts = "select * from accounts where balance >= ? and balance <= ?";
		ps= conn.prepareStatement(selectAllAccounts);
		ps.setInt(1, min);
		ps.setInt(2, max);
		rs = ps.executeQuery();
		Set<Account> fList = new HashSet<Account>();
		Account account;
			while(rs.next()) {
				int balance = rs.getInt("balance");
				String accType = rs.getString("account_type");
				int accNum = rs.getInt("account_number");
				int clientId= rs.getInt("client_id");
				account = new Account(accNum, clientId, accType, balance);
				fList.add(account);}
			return fList;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		
	}
	@Override
	public Account updateAccount(Account account) {
		
		try(Connection conn = ConnectionUtils.createConnection()) {
			
			String updateAcc = "update accounts set balance=? where account_number=?";
				ps = conn.prepareStatement(updateAcc);
				ps.setInt(1, account.getBalance());
				ps.setInt(2, account.getAccNum());
			if(ps.executeUpdate()==0)
				return null;
			else
				ps.execute();
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Account depositAccount(Account account) {
		try(Connection conn = ConnectionUtils.createConnection()){
			String selectAcc = "select balance from accounts WHERE account_number =?";
			ps = conn.prepareStatement(selectAcc);
			ps.setInt(1,account.getAccNum());
			rs = ps.executeQuery();
				while(rs.next()) {
					int balance = rs.getInt("balance");
					String updateAcc = "update accounts set balance=? where account_number=?";
						ps = conn.prepareStatement(updateAcc);
					int amount = account.getAmount();
					int newBalance= balance+amount;
						ps.setInt(1, newBalance);
						ps.setInt(2, account.getAccNum());
					if(ps.executeUpdate()==0)
						return null;
					else
						ps.execute();}
				return account;
		} catch (SQLException e) {
			return null;
		}
	}
	
	@Override
	public Account withdrawAccount(Account account) {
		try(Connection conn = ConnectionUtils.createConnection()){
			String selectAcc = "select balance from accounts WHERE account_number =?";
			ps = conn.prepareStatement(selectAcc);
			ps.setInt(1,account.getAccNum());
			rs = ps.executeQuery();
				while(rs.next()) {
					int balance = rs.getInt("balance");
					String updateAcc = "update accounts set balance=? where account_number=?";
						ps = conn.prepareStatement(updateAcc);
						int amount = account.getAmount();
						int newBalance= balance-amount;
					if(newBalance<0) {
						account.setBalance(balance);
						account.setAmount(amount);
						return account;}
					else {
						account.setBalance(newBalance);
						account.setAmount(0);
						ps.setInt(1, newBalance);
						ps.setInt(2, account.getAccNum());
							if(ps.executeUpdate()==0)
								return null;
							else
								ps.execute();}}
				return account;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean deleteAccountById(int accNum) {
		try(Connection conn = ConnectionUtils.createConnection();) {
			String deleteAccount = "delete from accounts WHERE account_number = ?";
			ps = conn.prepareStatement(deleteAccount);
			ps.setInt(1, accNum);
				if(ps.executeUpdate()== 0)
					return false;
				else
					ps.execute();
				return true;
		} catch (SQLException e) {
			return false;}
	}

	@Override
	public Account getAccountByAccNum(int accNum) {
		try(Connection conn = ConnectionUtils.createConnection()){
			String selectAccount = "select * from accounts WHERE account_number =?";
				ps = conn.prepareStatement(selectAccount);
				ps.setInt(1,accNum);
				rs = ps.executeQuery();
			int balance = rs.getInt("balance");
			String accType = rs.getString("accType");
			int id = rs.getInt("clientId");
			Account a = new Account(balance, accType, id);
				return a;
		}catch(SQLException e) {
			return null;}
	}


	
}
