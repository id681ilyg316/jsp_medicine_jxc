package com.medicine.action;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.SellDao;
import com.medicine.dao.SellDao;
import com.medicine.model.PageBean;
import com.medicine.model.Sell;
import com.medicine.model.Sell;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class SellAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private SellDao sellDao = new SellDao();

	private String page;
	private int total;
	private String pageCode;
	private Sell s_sell;
	private Sell sell;
	private String sellId;
	
	private String mainPage; 
	private String navCode;
	private String sellName;
	private List<Sell> sellList=new ArrayList<Sell>();

	

	
	

	

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

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

	public Sell getS_sell() {
		return s_sell;
	}

	public void setS_sell(Sell s_sell) {
		this.s_sell = s_sell;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public String getSellId() {
		return sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
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

	public List<Sell> getSellList() {
		return sellList;
	}

	public void setSellList(List<Sell> sellList) {
		this.sellList = sellList;
	}

	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_sell!=null){
			session.setAttribute("s_sell", s_sell);
		}else{
			Object o=session.getAttribute("s_sell");
			if(o!=null){
				s_sell=(Sell)o;
			}else{
				s_sell=new Sell();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			sellList=sellDao.getSellList(con,s_sell,pageBean);
			
			navCode=NavUtil.getNavgation("销售信息管理", "销售信息");	
			total=sellDao.SellCount(con, s_sell);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/sell!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			
			
			mainPage="sell/sellList.jsp";
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
			sellDao.SellDelete(con, sellId);
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
			if(StringUtil.isNotEmpty(sellId)){
				sell=sellDao.getSellById(con,sellId);
			}
			if(StringUtil.isNotEmpty(sellId)){
				navCode=NavUtil.getNavgation("销售信息管理", "销售修改");				
			}else{
				navCode=NavUtil.getNavgation("销售信息管理", "销售添加");				
			}
			mainPage="sell/sellSave.jsp";
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
	
	public String save(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			
			if(StringUtil.isNotEmpty(sellId)){
				sell.setSellId(sellId);
				sellDao.sellUpdate(con, sell);
			}else{
				sellDao.AddSell(con, sell);
			}
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
		return "save";
	}
	
	
	public String chaxun(){
		Connection con=null;
		try{
			con=dbUtil.getCon();
			sell=sellDao.getSellName(con, sellName);
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
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}

