package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.medicine.model.PageBean;
import com.medicine.model.Staff;
import com.medicine.model.Stock;

public class StockDao {

	public List<Stock> getStockList(Connection con,Stock s_stock,PageBean pageBean) throws Exception{
		List<Stock> stockList=new ArrayList<Stock>();
		StringBuffer sb=new StringBuffer("select * from t_stock");	
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Stock stock = new Stock();
			stock.setStockId(rs.getString("stockId"));
			stock.setType(rs.getString("type"));
			stock.setCount(rs.getString("count"));
			stock.setPn(rs.getString("pn"));
			stock.setDate(rs.getString("date"));
			stock.setPrice(rs.getString("price"));
			stock.setSaleman(rs.getString("saleman"));
			stock.setStockName(rs.getString("stockName"));
			stockList.add(stock);
		}
		return stockList;	
	}
	
	
	/**
	 * 获取记录总数
	 * */
	public int stockCount(Connection con)throws Exception{
		String sb="select count(*) as total from t_stock";
		PreparedStatement pstmt=con.prepareStatement(sb);
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
	
	public int stockDelete(Connection con,String stockId)throws Exception{
		String sql="delete from t_stock where stockId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stockId);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 添加员工
	 * */
	public int addStock(Connection con,Stock stock)throws Exception{
		String sql="insert into t_stock(stockName,type,count,pn,date,price,saleman) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stock.getStockName());
		pstmt.setString(2, stock.getType());
		pstmt.setString(3, stock.getCount());
		pstmt.setString(4, stock.getPn());
		pstmt.setString(5, stock.getDate());
		pstmt.setString(6, stock.getPrice());
		pstmt.setString(7, stock.getSaleman());
		return pstmt.executeUpdate();
	}
	
	
	

	
	public Stock getStockById(Connection con,String stockId)throws Exception{
		String sql="select * from t_stock where stockId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stockId);
		ResultSet rs=pstmt.executeQuery();
		Stock stock=null;
		if(rs.next()){
			stock=new Stock();
			stock.setStockId(rs.getString("stockId"));
			stock.setType(rs.getString("type"));
			stock.setCount(rs.getString("count"));
			stock.setPn(rs.getString("pn"));
			stock.setDate(rs.getString("date"));
			stock.setPrice(rs.getString("price"));
			stock.setSaleman(rs.getString("saleman"));
			stock.setStockName(rs.getString("stockName"));
		}
		return stock;
	}
	
	
	public int StockUpdate(Connection con,Stock stock)throws Exception{
		String sql="update t_stock set stockName=? , type=? , count=? ,pn=? ,date=? ,price=?,saleman=? where stockId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stock.getStockName());
		pstmt.setString(2, stock.getType());
		pstmt.setString(3, stock.getCount());
		pstmt.setString(4, stock.getPn());
		pstmt.setString(5, stock.getDate());
		pstmt.setString(6, stock.getPrice());
		pstmt.setString(7, stock.getSaleman());
		pstmt.setString(8, stock.getStockId());
		return pstmt.executeUpdate();
	}
	public Stock getStockByName(Connection con, String stockName) throws Exception {
		String sql="select * from t_stock where stockName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, stockName);
		ResultSet rs=pstmt.executeQuery();
		Stock stock=null;
		if(rs.next()){
			stock=new Stock();
			stock.setStockName(rs.getString("stockName"));
			stock.setCount(rs.getString("count"));
			stock.setPn(rs.getString("pn"));
			stock.setPrice(rs.getString("price"));
			stock.setDate(rs.getString("date"));
			stock.setSaleman(rs.getString("saleman"));
			stock.setType(rs.getString("Type"));
		}
		return stock;
	
}
}

