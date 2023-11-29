package com.medicine.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.AdminDao;
import com.medicine.model.Admin;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class AdminAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private Admin admin;
	private String error;
	private String imageCode;

	private int adminId;
	private String password;
	private String mainPage; 
	private String navCode;
	
	
	
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	private AdminDao adminDao = new AdminDao() ;
	private DbUtil dbUtil=new DbUtil();
	
	/**
	 * 登陆
	 * */
	public String login(){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(admin.getAdminName())||StringUtil.isEmpty(admin.getPassword())){
			error = "用户名或密码不能为空";
			return ERROR;
		}
		if(StringUtil.isEmpty(imageCode)){
			error="验证码为空！";
			return ERROR;
		}
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			return ERROR;
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Admin currentAdmin = adminDao.login(admin, con);
			if(currentAdmin==null){
				error="用户名或密码错误！";
				return ERROR;
			}else{
				session.setAttribute("currentAdmin", currentAdmin);
				return SUCCESS;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String logOut(){
		HttpSession session = request.getSession();
		session.removeAttribute("currentAdmin");
		return "logOut";
	}
	
	public String preSave(){
		navCode=NavUtil.getNavgation("系统管理", "修改密码");
		mainPage="admin/modifyPassword.jsp";
		return SUCCESS;
	}
	
	public String save(){
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setPassword(password);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int updateNums=adminDao.modifyPassword(con, admin);
			JSONObject resultJson=new JSONObject();
			if(updateNums>0){
				resultJson.put("success", true);
			}else{
				resultJson.put("errorMsg", "修改密码失败");
			}
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
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

}
