package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.medicine.model.Client;
import com.medicine.model.PageBean;
import com.medicine.model.Provider;
import com.medicine.util.StringUtil;

public class ProviderDao {

	public List<Provider> getProviderList(Connection con,Provider s_provider,PageBean pageBean) throws Exception{
		List<Provider> providerList=new ArrayList<Provider>();
		StringBuffer sb=new StringBuffer("select * from t_provider");	
		if(StringUtil.isNotEmpty(s_provider.getProviderNo())){
			sb.append(" and providerNo like '%"+s_provider.getProviderNo()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderAddress())){
			sb.append(" and providerAddress like '%"+s_provider.getProviderAddress()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderPhone())){
			sb.append(" and providerPhone like '%"+s_provider.getProviderPhone()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderlegal())){
			sb.append(" and providerlegal like '%"+s_provider.getProviderlegal()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Provider provider = new Provider();
			provider.setProviderId(rs.getString("providerId"));
			provider.setProviderNo(rs.getString("providerNo"));
			provider.setProviderPhone(rs.getString("providerPhone"));
			provider.setProviderAddress(rs.getString("providerAddress"));
			provider.setProviderlegal(rs.getString("providerlegal"));
			providerList.add(provider);
		}
		return providerList;	
	}
	
	
	/**
	 * 获取记录总数
	 * */
	public int providerCount(Connection con,Provider s_provider)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_provider");
		if(StringUtil.isNotEmpty(s_provider.getProviderNo())){
			sb.append(" and providerName like '%"+s_provider.getProviderNo()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderAddress())){
			sb.append(" and providerAddress like '%"+s_provider.getProviderAddress()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderPhone())){
			sb.append(" and providerPhone like '%"+s_provider.getProviderPhone()+"%'");
		}
		if(StringUtil.isNotEmpty(s_provider.getProviderlegal())){
			sb.append(" and providerlegal like '%"+s_provider.getProviderlegal()+"%'");
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
	
	public int providerDelete(Connection con,String providerId)throws Exception{
		String sql="delete from t_provider where providerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, providerId);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 添加员工
	 * */
	public int AddProvider(Connection con,Provider provider)throws Exception{
		String sql="insert into t_provider(providerNo,providerAddress,providerPhone,providerlegal) values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, provider.getProviderNo());
		pstmt.setString(2, provider.getProviderAddress());
		pstmt.setString(3, provider.getProviderPhone());
		pstmt.setString(4, provider.getProviderlegal());
		return pstmt.executeUpdate();
	}
	

	
	
	public Provider getProviderById(Connection con,String providerId)throws Exception{
		String sql="select * from t_provider where providerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, providerId);
		ResultSet rs=pstmt.executeQuery();
		Provider provider=null;
		if(rs.next()){
			provider=new Provider();
			provider.setProviderId(rs.getString("providerId"));
			provider.setProviderNo(rs.getString("providerNo"));
			provider.setProviderPhone(rs.getString("providerPhone"));
			provider.setProviderAddress(rs.getString("providerAddress"));
			provider.setProviderlegal(rs.getString("providerlegal"));
		}
		return provider;
	}
	
	
	public int ProviderUpdate(Connection con,Provider provider)throws Exception{
		String sql="update t_provider set providerNo=?,providerPhone=?,providerAddress=?,providerlegal=? where providerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, provider.getProviderNo());
		pstmt.setString(2, provider.getProviderPhone());
		pstmt.setString(3, provider.getProviderAddress());
		pstmt.setString(4, provider.getProviderlegal());
		pstmt.setString(5, provider.getProviderId());
		return pstmt.executeUpdate();
	}
	
	public Provider getProviderByNo(Connection con,String providerNo)throws Exception{
		String sql="select * from t_provider where providerNo=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, providerNo);
		ResultSet rs=pstmt.executeQuery();
		Provider provider=null;
		if(rs.next()){
			provider=new Provider();
			provider.setProviderNo(rs.getString("providerNo"));
			provider.setProviderAddress(rs.getString("providerAddress"));
			provider.setProviderlegal(rs.getString("providerlegal"));
			provider.setProviderPhone(rs.getString("providerPhone"));
		}
		return provider;
	}
}
