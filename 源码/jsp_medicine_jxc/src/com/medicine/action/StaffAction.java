package com.medicine.action;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.medicine.dao.StaffDao;
import com.medicine.model.PageBean;
import com.medicine.model.Staff;
import com.medicine.util.DbUtil;
import com.medicine.util.NavUtil;
import com.medicine.util.PageUtil;
import com.medicine.util.PropertiesUtil;
import com.medicine.util.ResponseUtil;
import com.medicine.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class StaffAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private DbUtil dbUtil=new DbUtil();
	private StaffDao staffDao = new StaffDao();

	private String page;
	private int total;
	private String staffName;
	
	public String getStaffName() {
		return staffName;
	}



	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}



	public Staff getS_staff() {
		return s_staff;
	}



	public void setS_staff(Staff s_staff) {
		this.s_staff = s_staff;
	}



	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	private String pageCode;
	private Staff s_staff;
	private Staff staff;
	private String staffId;
	
	
	
	public String getStaffId() {
		return staffId;
	}

	

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
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

	private String mainPage; 
	private String navCode;
	
	private List<Staff> staffList=new ArrayList<Staff>();
	private List<Staff> s_staffList_n;
	private List<Staff> s_staffList_a;
	private List<Staff> s_staffList_p;
	
	

	public List<Staff> getS_staffList_n() {
		return s_staffList_n;
	}



	public void setS_staffList_n(List<Staff> s_staffList_n) {
		this.s_staffList_n = s_staffList_n;
	}



	public List<Staff> getS_staffList_a() {
		return s_staffList_a;
	}



	public void setS_staffList_a(List<Staff> s_staffList_a) {
		this.s_staffList_a = s_staffList_a;
	}



	public List<Staff> getS_staffList_p() {
		return s_staffList_p;
	}



	public void setS_staffList_p(List<Staff> s_staffList_p) {
		this.s_staffList_p = s_staffList_p;
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



	public List<Staff> getStaffList() {
		return staffList;
	}



	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}



	public String list(){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(s_staff!=null){
			session.setAttribute("s_staff", s_staff);
		}else{
			Object o=session.getAttribute("s_staff");
			if(o!=null){
				s_staff=(Staff)o;
			}else{
				s_staff=new Staff();
			}
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			staffList=staffDao.getStaffList(con,s_staff,pageBean);
			
			navCode=NavUtil.getNavgation("员工信息管理", "员工修改");	
			total=staffDao.staffCount(con, s_staff);
			pageCode=PageUtil.genPagation(request.getContextPath()+"/staff!list", total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			
			
			mainPage="staff/staffList.jsp";
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
			staffDao.staffDelete(con, staffId);
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
			if(StringUtil.isNotEmpty(staffId)){
				staff=staffDao.getStaffById(con, staffId);
			}
			if(StringUtil.isNotEmpty(staffId)){
				navCode=NavUtil.getNavgation("员工信息管理", "员工修改");				
			}else{
				navCode=NavUtil.getNavgation("员工信息管理", "员工添加");				
			}
			mainPage="staff/staffSave.jsp";
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
			
			if(StringUtil.isNotEmpty(staffId)){
				staff.setStaffId(staffId);
				staffDao.staffUpdate(con, staff);
			}else{
				staffDao.Addstaff(con,staff);
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
			staff=staffDao.getStaffByName(con,staffName);
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