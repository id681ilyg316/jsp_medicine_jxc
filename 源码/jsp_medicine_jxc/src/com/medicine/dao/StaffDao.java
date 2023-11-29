package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicine.model.PageBean;
import com.medicine.model.Sell;
import com.medicine.model.Staff;
import com.medicine.util.DbUtil;
import com.medicine.util.StringUtil;

public class StaffDao {

	public List<Staff> getStaffList(Connection con,Staff s_staff,PageBean pageBean) throws Exception{
		List<Staff> staffList=new ArrayList<Staff>();
		StringBuffer sb=new StringBuffer("select * from t_staff");	
		if(StringUtil.isNotEmpty(s_staff.getStaffName())){
			sb.append(" and staffName like '%"+s_staff.getStaffName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_staff.getStaffAddress())){
			sb.append(" and staffAddress like '%"+s_staff.getStaffAddress()+"%'");
		}
		if(StringUtil.isNotEmpty(s_staff.getStaffPhone())){
			sb.append(" and staffPhone like '%"+s_staff.getStaffPhone()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Staff staff = new Staff();
			staff.setStaffId(rs.getString("staffId"));
			staff.setStaffName(rs.getString("staffName"));
			staff.setStaffPhone(rs.getString("staffPhone"));
			staff.setStaffAddress(rs.getString("staffAddress"));
			staffList.add(staff);
		}
		return staffList;	
	}
	
	
	/**
	 * 获取记录总数
	 * */
	public int staffCount(Connection con,Staff s_staff)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_staff");
		if(StringUtil.isNotEmpty(s_staff.getStaffName())){
			sb.append(" and staffName like '%"+s_staff.getStaffName()+"%'");
		}
		if(StringUtil.isNotEmpty(s_staff.getStaffAddress())){
			sb.append(" and staffAddress like '%"+s_staff.getStaffAddress()+"%'");
		}
		if(StringUtil.isNotEmpty(s_staff.getStaffPhone())){
			sb.append(" and staffPhone like '%"+s_staff.getStaffPhone()+"%'");
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
	 * 删除员工
	 * */
	
	public int staffDelete(Connection con,String staffId)throws Exception{
		String sql="delete from t_staff where staffId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, staffId);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 添加员工
	 * */
	public int Addstaff(Connection con,Staff staff)throws Exception{
		String sql="insert into t_staff(staffName,staffAddress,staffPhone) values(?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, staff.getStaffName());
		pstmt.setString(2, staff.getStaffAddress());
		pstmt.setString(3, staff.getStaffPhone());
		return pstmt.executeUpdate();
	}
	
	
	public static void main(String[] args) throws Exception {
		Connection con = new DbUtil().getCon();
		Staff staff = new Staff();
		staff.setStaffName("1");
		staff.setStaffAddress("1");
		staff.setStaffPhone("1");
		new StaffDao().Addstaff(con, staff);
	}
	
	public Staff getStaffById(Connection con,String staffId)throws Exception{
		String sql="select * from t_staff where staffId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, staffId);
		ResultSet rs=pstmt.executeQuery();
		Staff staff=null;
		if(rs.next()){
			staff=new Staff();
			staff.setStaffId(rs.getString("staffId"));
			staff.setStaffName(rs.getString("staffName"));
			staff.setStaffPhone(rs.getString("staffPhone"));
			staff.setStaffAddress(rs.getString("staffAddress"));
		}
		return staff;
	}
	
	
	public int staffUpdate(Connection con,Staff staff)throws Exception{
		String sql="update t_staff set staffName=?,staffPhone=?,staffAddress=? where staffId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, staff.getStaffName());
		pstmt.setString(2, staff.getStaffPhone());
		pstmt.setString(3, staff.getStaffAddress());
		pstmt.setString(4,staff.getStaffId());
		return pstmt.executeUpdate();
	}


	public Staff getStaffByName(Connection con, String staffName) throws Exception {
			String sql="select * from t_staff where staffName=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, staffName);
			ResultSet rs=pstmt.executeQuery();
			Staff staff=null;
			if(rs.next()){
				staff=new Staff();
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffAddress(rs.getString("staffAddress"));
				staff.setStaffPhone(rs.getString("staffPhone"));
				
			}
			return staff;
		
	}
}
