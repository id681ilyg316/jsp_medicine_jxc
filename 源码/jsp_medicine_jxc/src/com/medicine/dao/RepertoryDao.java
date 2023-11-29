package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.medicine.model.PageBean;
import com.medicine.model.Provider;
import com.medicine.model.Repertory;
import com.medicine.util.DbUtil;
import com.medicine.util.StringUtil;

public class RepertoryDao {

	public List<Repertory> getRepertoryList(Connection con,Repertory s_repertory,PageBean pageBean) throws Exception{
		List<Repertory> repertoryList=new ArrayList<Repertory>();
		StringBuffer sb=new StringBuffer("select * from t_repertory");	
		if(StringUtil.isNotEmpty(s_repertory.getRepertoryName())){
			sb.append(" and repertoryName like '%"+s_repertory.getRepertoryName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_repertory.getRepertoryQuantity())){
			sb.append(" and repertoryQuantity like '%"+s_repertory.getRepertoryQuantity()+"%'");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Repertory repertory = new Repertory();
			repertory.setRepertoryId(rs.getString("repertoryId"));
			repertory.setRepertoryName(rs.getString("repertoryName"));
			repertory.setRepertoryQuantity(rs.getString("repertoryQuantity"));	
			repertoryList.add(repertory);
		}
		return repertoryList;	
	}
	
	
	/**
	 * 获取记录总数
	 * */
	public int repertoryCount(Connection con,Repertory s_repertory)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_repertory");
		if(StringUtil.isNotEmpty(s_repertory.getRepertoryName())){
			sb.append(" and repertoryName like '%"+s_repertory.getRepertoryName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_repertory.getRepertoryQuantity())){
			sb.append(" and repertoryQuantity like '%"+s_repertory.getRepertoryQuantity()+"%'");
		}		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	

	
	public int repertoryDelete(Connection con,String repertoryId)throws Exception{
		String sql="delete from t_repertory where repertoryId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, repertoryId);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 添加
	 * */
	public static int AddRepetory(Connection con,Repertory repertory)throws Exception{
		String sql="insert into t_repertory(repertoryName,repertoryQuantity) values(?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, repertory.getRepertoryName());
		pstmt.setString(2, repertory.getRepertoryQuantity());
		return pstmt.executeUpdate();
	}
	
	public Repertory getRepertoryByName(Connection con,String repertoryName)throws Exception{
		String sql="select * from t_repertory where repertoryName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, repertoryName);
		ResultSet rs=pstmt.executeQuery();
		Repertory repertory=null;
		if(rs.next()){
			repertory=new Repertory();
			repertory.setRepertoryName(rs.getString("repertoryName"));
			repertory.setRepertoryQuantity(rs.getString("repertoryQuantity"));
		}
		return repertory;
	}


	public Repertory getProviderById(Connection con,String repertoryId)throws Exception{
		String sql="select * from t_repertory where repertoryId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, repertoryId);
		ResultSet rs=pstmt.executeQuery();
		Repertory  repertory =null;
		if(rs.next()){
			repertory =new Repertory ();
			repertory.setRepertoryId(rs.getString("repertoryId"));
			repertory.setRepertoryName(rs.getString("repertoryName"));
			repertory.setRepertoryQuantity(rs.getString("repertoryQuantity"));	
		}
		return repertory;
	}
	
	public  Provider getProviderByName(Connection con,String providerName)throws Exception{
		String sql="select * from t_ provider where providerName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, providerName);
		ResultSet rs=pstmt.executeQuery();
		Provider provider =null;
		if(rs.next()){
			provider =new Provider ();
			provider.setProviderNo(rs.getString("providerNo"));
			provider.setProviderAddress(rs.getString("providerAddress"));
			provider.setProviderlegal(rs.getString("providerlegal"));
			provider.setProviderPhone(rs.getString("providerPhone"));
			
				
		}
		return provider;
	}
	
	public int RepertoryUpdate(Connection con,Repertory repertory)throws Exception{
		String sql="update t_repertory set repertoryName=?, repertoryQuantity=? where repertoryId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, repertory.getRepertoryName());
		pstmt.setString(2, repertory.getRepertoryQuantity());
		pstmt.setString(3, repertory.getRepertoryId());
		return pstmt.executeUpdate();
	}
	
	public static void main(String[] args) throws Exception {
		Connection con = new DbUtil().getCon();
		Repertory r = new Repertory();
		r.setRepertoryName("1");
		r.setRepertoryQuantity("255");
		new RepertoryDao().AddRepetory(con, r);
		
	}
	
	
}
