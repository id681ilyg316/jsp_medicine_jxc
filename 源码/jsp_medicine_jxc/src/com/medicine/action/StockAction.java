package com.medicine.action;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.StockDao;
import com.medicine.model.PageBean;
import com.medicine.model.Stock;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class StockAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private StockDao stockDao = new StockDao();

	private String page;
	private int total;
	private String pageCode;
	private Stock s_stock;
	private Stock stock;
	private String stockId;
	private String stockName;
	private String mainPage; 
	private String navCode;

	
	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	private List<Stock> stockList=new ArrayList<Stock>();

	
	

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public Stock getS_stock() {
		return s_stock;
	}

	public void setS_stock(Stock s_stock) {
		this.s_stock = s_stock;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_stock!=null){
			session.setAttribute("s_stock", s_stock);
		}else{
			Object o=session.getAttribute("s_stock");
			if(o!=null){
				s_stock=(Stock)o;
			}else{
				s_stock=new Stock();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			stockList=stockDao.getStockList(con,s_stock,pageBean);
			
			navCode=NavUtil.getNavgation("进货信息管理", "进货信息");	
			total=stockDao.stockCount(con);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/stock!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			
			
			mainPage="stock/stockList.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String delete(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject resultJson=new JSONObject();
			stockDao.stockDelete(con, stockId);
			resultJson.put("success", true);
			ResponseUtil.write(resultJson, ServletActionContext.getResponse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	public String preSave(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(stockId)){
				stock=stockDao.getStockById(con,stockId);
			}
			if(StringUtil.isNotEmpty(stockId)){
				navCode=NavUtil.getNavgation("进货信息管理", "销售修改");				
			}else{
				navCode=NavUtil.getNavgation("进货信息管理", "销售添加");				
			}
			mainPage="stock/stockSave.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String chaxun(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			stock=stockDao.getStockByName(con, stockName);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "chaxun";
	}
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
 			stockDao.addStock(con, stock);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}