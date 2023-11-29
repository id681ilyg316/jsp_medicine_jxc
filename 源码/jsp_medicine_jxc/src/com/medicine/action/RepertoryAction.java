package com.medicine.action;


import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.RepertoryDao;
import com.medicine.model.PageBean;
import com.medicine.model.Repertory;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class RepertoryAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private RepertoryDao repertoryDao = new RepertoryDao();

	private String page;
	private int total;

	private String pageCode;
	private Repertory s_repertory;
	private Repertory repertory;
	private String repertoryId;
	private String repertoryName;
	
	

	public String getRepertoryName() {
		return repertoryName;
	}

	public void setRepertoryName(String repertoryName) {
		this.repertoryName = repertoryName;
	}
	private String mainPage; 
	private String navCode;
	
	private List<Repertory> repertoryList=new ArrayList<Repertory>();

	

	

	

	public RepertoryDao getRepertoryDao() {
		return repertoryDao;
	}

	public void setRepertoryDao(RepertoryDao repertoryDao) {
		this.repertoryDao = repertoryDao;
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

	public Repertory getS_repertory() {
		return s_repertory;
	}

	public void setS_repertory(Repertory s_repertory) {
		this.s_repertory = s_repertory;
	}

	public Repertory getRepertory() {
		return repertory;
	}

	public void setRepertory(Repertory repertory) {
		this.repertory = repertory;
	}

	public String getRepertoryId() {
		return repertoryId;
	}

	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
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

	public List<Repertory> getRepertoryList() {
		return repertoryList;
	}

	public void setRepertoryList(List<Repertory> repertoryList) {
		this.repertoryList = repertoryList;
	}

	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_repertory!=null){
			session.setAttribute("s_repertory", s_repertory);
		}else{
			Object o=session.getAttribute("s_repertory");
			if(o!=null){
				s_repertory=(Repertory)o;
			}else{
				s_repertory=new Repertory();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			repertoryList=repertoryDao.getRepertoryList(con,s_repertory,pageBean);
			
			navCode=NavUtil.getNavgation("库存信息管理", "库存修改");	
			total=repertoryDao.repertoryCount(con, s_repertory);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/repertory!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			
			
			mainPage="repertory/repertoryList.jsp";
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
			repertoryDao.repertoryDelete(con, repertoryId);
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
			if(StringUtil.isNotEmpty(repertoryId)){
				repertory=repertoryDao.getProviderById(con, repertoryId);
			}
			if(StringUtil.isNotEmpty(repertoryId)){
				navCode=NavUtil.getNavgation("库存信息管理", "库存修改");				
			}else{
				navCode=NavUtil.getNavgation("库存信息管理", "库存添加");				
			}
			mainPage="repertory/repertorySave.jsp";
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
			
			if(StringUtil.isNotEmpty(repertoryId)){
				repertory.setRepertoryId(repertoryId);
				repertoryDao.RepertoryUpdate(con, repertory);
			}else{
				repertoryDao.AddRepetory(con, repertory);
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
			repertory=repertoryDao.getRepertoryByName(con, repertoryName);
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
