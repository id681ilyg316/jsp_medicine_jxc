package com.medicine.action;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.ProviderDao;

import com.medicine.model.PageBean;
import com.medicine.model.Provider;

import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class ProviderAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private ProviderDao providerDao = new ProviderDao();

	private String page;
	private int total;
	private String pageCode;
	private Provider s_provider;
	private Provider provider;
	private String providerId;
	private String providerNo;
	
	
	
	public String getproviderNo() {
		return providerNo;
	}

	public void setproviderNo(String providerNo) {
		this.providerNo = providerNo;
	}
	private String mainPage; 
	private String navCode;

	private List<Provider> providerList=new ArrayList<Provider>();
	
	
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public Provider getS_provider() {
		return s_provider;
	}

	public void setS_provider(Provider s_provider) {
		this.s_provider = s_provider;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
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

	public List<Provider> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<Provider> providerList) {
		this.providerList = providerList;
	}

	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_provider!=null){
			session.setAttribute("s_provider", s_provider);
		}else{
			Object o=session.getAttribute("s_provider");
			if(o!=null){
				s_provider=(Provider)o;
			}else{
				s_provider=new Provider();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			providerList=providerDao.getProviderList(con, s_provider, pageBean);
			
			navCode=NavUtil.getNavgation("供应商信息管理", "供应商信息");	
			total=providerDao.providerCount(con, s_provider);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/provider!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			
			
			mainPage="provider/providerList.jsp";
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
			providerDao.providerDelete(con, providerId);
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
			if(StringUtil.isNotEmpty(providerId)){
				provider=providerDao.getProviderById(con,providerId);
			}
			if(StringUtil.isNotEmpty(providerId)){
				navCode=NavUtil.getNavgation("供应商管理", "供应商修改");				
			}else{
				navCode=NavUtil.getNavgation("供应商管理", "供应商添加");				
			}
			mainPage="provider/providerSave.jsp";
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
			
			if(StringUtil.isNotEmpty(providerId)){
				provider.setProviderId(providerId);
				providerDao.ProviderUpdate(con, provider);
			}else{
				providerDao.AddProvider(con, provider);
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
			provider=providerDao.getProviderByNo(con, providerNo);
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

