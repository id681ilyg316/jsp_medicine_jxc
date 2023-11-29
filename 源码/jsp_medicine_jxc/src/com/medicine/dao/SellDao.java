package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.medicine.model.Client;
import com.medicine.model.PageBean;
import com.medicine.model.Sell;

import com.medicine.util.StringUtil;

public class SellDao {

	public List<Sell> getSellList(Connection con,Sell s_Sell,PageBean pageBean) throws Exception{
		List<Sell> sellList=new ArrayList<Sell>();
		StringBuffer sb=new StringBuffer("select * from t_sell");	
		if(StringUtil.isNotEmpty(s_Sell.getSellName())){
			sb.append(" andSellName like '%"+s_Sell.getSellName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getPn())){
			sb.append(" and pn like '%"+s_Sell.getPn()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getCount())){
			sb.append(" and count like '%"+s_Sell.getCount()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getPrice())){
			sb.append(" and price like '%"+s_Sell.getPrice()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getType())){
			sb.append(" and type like '%"+s_Sell.getType()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getSaleman())){
			sb.append(" and saleman like '%"+s_Sell.getSaleman()+"%'");
		}	
		if(StringUtil.isNotEmpty(s_Sell.getCn())){
			sb.append(" and cn like '%"+s_Sell.getCn()+"%'");
		}	
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=
		con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Sell Sell = new Sell();
			Sell.setSellId(rs.getString("sellId"));
			Sell.setSellName(rs.getString("sellName"));
			Sell.setCount(rs.getString("count"));
			Sell.setPrice(rs.getString("price"));
			Sell.setType(rs.getString("type"));
			Sell.setSaleman(rs.getString("saleman"));
			Sell.setPn(rs.getString("pn"));
			Sell.setCn(rs.getString("cn"));

			sellList.add(Sell);
		}
		return sellList;	
	}
	/**
	 * 添加商品
	 * */
	public int AddSell(Connection con,Sell Sell)throws Exception{
		String sql="insert into t_sell(sellName,pn,cn,type,price,count,saleman) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Sell.getSellName());
		pstmt.setString(2,Sell.getPn());
		pstmt.setString(3,Sell.getCount());
		pstmt.setString(4,Sell.getSaleman());
		pstmt.setString(5,Sell.getType());
		pstmt.setString(6,Sell.getPrice());
		pstmt.setString(7,Sell.getCn());
		return pstmt.executeUpdate();
	}
	
	
	
	
	/**
	 * 获取记录总数
	 * */
	public int SellCount(Connection con,Sell s_Sell)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_sell");
		if(StringUtil.isNotEmpty(s_Sell.getSellName())){
			sb.append(" andSellName like '%"+s_Sell.getSellName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getPn())){
			sb.append(" and pn like '%"+s_Sell.getPn()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getCount())){
			sb.append(" and count like '%"+s_Sell.getCount()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getPrice())){
			sb.append(" and price like '%"+s_Sell.getPrice()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getType())){
			sb.append(" and type like '%"+s_Sell.getType()+"%'");
		}
		if(StringUtil.isNotEmpty(s_Sell.getSaleman())){
			sb.append(" and saleman like '%"+s_Sell.getSaleman()+"%'");
		}	
		if(StringUtil.isNotEmpty(s_Sell.getCn())){
			sb.append(" and cn like '%"+s_Sell.getCn()+"%'");
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
	 * 删除
	 * */
	
	public int SellDelete(Connection con,String sellId)throws Exception{
		String sql="delete from t_sell where sellId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,sellId);
		return pstmt.executeUpdate();
	}
	
	
	
	
	public Sell getSellById(Connection con,String SellId)throws Exception{
		String sql="select * from t_sell where sellId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,SellId);
		ResultSet rs=pstmt.executeQuery();
		Sell Sell=null;
		if(rs.next()){
			Sell=new Sell();
			Sell.setSellId(rs.getString("sellId"));
			Sell.setSellName(rs.getString("sellName"));
			Sell.setCount(rs.getString("count"));
			Sell.setPrice(rs.getString("price"));
			Sell.setType(rs.getString("type"));
			Sell.setPn(rs.getString("pn"));
			Sell.setCn(rs.getString("cn"));
			Sell.setSaleman(rs.getString("saleman"));
		
		}
		return Sell;
	}
	
	
	public int sellUpdate(Connection con,Sell Sell)throws Exception{
		String sql="update t_sell set sellName=?, count=?, price=?, type=?, pn=? ,saleman=?,cn=? where sellId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,Sell.getSellName());
		pstmt.setString(2,Sell.getCount());
		pstmt.setString(3,Sell.getPrice());
		pstmt.setString(4,Sell.getType());
		pstmt.setString(5,Sell.getPn());
		pstmt.setString(6,Sell.getSaleman());
		pstmt.setString(7,Sell.getCn());
		pstmt.setString(8,Sell.getSellId());
		return pstmt.executeUpdate();
	}
	
	
	
	public Sell getSellName(Connection con,String sellName)throws Exception{
		String sql="select * from t_sell where sellName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, sellName);
		ResultSet rs=pstmt.executeQuery();
		Sell sell=null;
		if(rs.next()){
			sell=new Sell();
			sell.setSellId(rs.getString("sellId"));
			sell.setSellName(rs.getString("sellName"));
			sell.setCount(rs.getString("count"));
			sell.setPrice(rs.getString("price"));
			sell.setType(rs.getString("type"));
			sell.setPn(rs.getString("pn"));
			sell.setCn(rs.getString("cn"));
			sell.setSaleman(rs.getString("saleman"));
			
		}
		return sell;
	}
}
