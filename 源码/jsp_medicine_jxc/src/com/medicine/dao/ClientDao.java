package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.medicine.model.PageBean;
import com.medicine.model.Client;

import com.medicine.util.StringUtil;

public class ClientDao {

	public List<Client> getClientList(Connection con,Client s_client,PageBean pageBean) throws Exception{
		List<Client> clientList=new ArrayList<Client>();
		StringBuffer sb=new StringBuffer("select * from t_client");	
		if(StringUtil.isNotEmpty(s_client.getClientName())){
			sb.append(" where clientName like '%"+s_client.getClientName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Client client = new Client();
			client.setClientId(rs.getString("clientId"));
			client.setClientName(rs.getString("clientName"));
			client.setClientPhone(rs.getString("clientPhone"));
			client.setClientAddress(rs.getString("clientAddress"));
			clientList.add(client);
		}
		return clientList;	
	}
	
	/**
	 * 获取记录总数
	 * */
	public int ClientCount(Connection con,Client s_client)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_client");
		if(StringUtil.isNotEmpty(s_client.getClientName())){
			sb.append(" and clientName like '%"+s_client.getClientName()+"%'");
		}	
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	/**
	 * 删除员工
	 * */
	
	public int ClientDelete(Connection con,String clientId)throws Exception{
		String sql="delete from t_client where clientId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, clientId);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 添加员工
	 * */
	public int AddClient(Connection con,Client client)throws Exception{
		String sql="insert into t_client(clientName,clientPhone,clientAddress) values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, client.getClientName());
		pstmt.setString(2, client.getClientAddress());
		pstmt.setString(3, client.getClientPhone());
		return pstmt.executeUpdate();
	}
	
	
	
	public Client getClientById(Connection con,String clientId)throws Exception{
		String sql="select * from t_client where clientId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, clientId);
		ResultSet rs=pstmt.executeQuery();
		Client Client=null;
		if(rs.next()){
			Client=new Client();
			Client.setClientId(rs.getString("clientId"));
			Client.setClientName(rs.getString("clientName"));
			Client.setClientPhone(rs.getString("clientPhone"));
			Client.setClientAddress(rs.getString("clientAddress"));
		}
		return Client;
	}
	
	public Client getClientByName(Connection con,String clientName)throws Exception{
		String sql="select * from t_client where clientName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, clientName);
		ResultSet rs=pstmt.executeQuery();
		Client client=null;
		if(rs.next()){
			client=new Client();
			client.setClientId(rs.getString("clientId"));
			client.setClientName(rs.getString("clientName"));
			client.setClientPhone(rs.getString("clientPhone"));
			client.setClientAddress(rs.getString("clientAddress"));
		}
		return client;
	}
	

	
	
	public int ClientUpdate(Connection con,Client client)throws Exception{
		String sql="update t_client set clientName=?,clientPhone=?,clientAddress=? where clientId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,client.getClientName());
		pstmt.setString(2, client.getClientPhone());
		pstmt.setString(3, client.getClientAddress());
		pstmt.setString(4,client.getClientId());
		return pstmt.executeUpdate();
	}

}


