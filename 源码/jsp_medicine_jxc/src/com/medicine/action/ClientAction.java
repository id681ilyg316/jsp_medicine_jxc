package com.medicine.action;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.ClientDao;
import com.medicine.model.PageBean;
import com.medicine.model.Client;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class ClientAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private ClientDao clientDao = new ClientDao();

	private String page;
	private int total;
	private String pageCode;
	private Client s_client;
	private Client client;
	private String clientId;
	private String clientName;
	private String mainPage; 
	private String navCode;
	
	
	

	

	private List<Client> clientList=new ArrayList<Client>();
	
	private List<Client> s_clientList_n;
	private List<Client> s_clientList_a;
	private List<Client> s_clientList_p;


	
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public Client getS_client() {
		return s_client;
	}

	public void setS_client(Client s_client) {
		this.s_client = s_client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	public List<Client> getS_clientList_n() {
		return s_clientList_n;
	}

	public void setS_clientList_n(List<Client> s_clientList_n) {
		this.s_clientList_n = s_clientList_n;
	}

	public List<Client> getS_clientList_a() {
		return s_clientList_a;
	}

	public void setS_clientList_a(List<Client> s_clientList_a) {
		this.s_clientList_a = s_clientList_a;
	}

	public List<Client> getS_clientList_p() {
		return s_clientList_p;
	}

	public void setS_clientList_p(List<Client> s_clientList_p) {
		this.s_clientList_p = s_clientList_p;
	}

	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_client!=null){
			session.setAttribute("s_client", s_client);
		}else{
			Object o=session.getAttribute("s_client");
			if(o!=null){
				s_client=(Client)o;
			}else{
				s_client=new Client();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			clientList=clientDao.getClientList(con,s_client,pageBean);
			
			navCode=NavUtil.getNavgation("客戶信息管理", "客戶信息");	
			total=clientDao.ClientCount(con, s_client);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/client!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			s_client.setClientName("11");
			 s_clientList_n=clientDao.getClientList(con, s_client, null);
			mainPage="client/clientList.jsp";
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
			clientDao.ClientDelete(con, clientId);
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
			if(StringUtil.isNotEmpty(clientId)){
				client=clientDao.getClientById(con,clientId);
			}
			if(StringUtil.isNotEmpty(clientId)){
				navCode=NavUtil.getNavgation("客户信息管理", "客户修改");				
			}else{
				navCode=NavUtil.getNavgation("客户信息管理", "客户添加");				
			}
			mainPage="client/clientSave.jsp";
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
			
			if(StringUtil.isNotEmpty(clientId)){
				client.setClientId(clientId);
				clientDao.ClientUpdate(con, client);
			}else{
				clientDao.AddClient(con, client);
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
			client=clientDao.getClientByName(con, clientName);
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
