package com.medicine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.medicine.model.Admin;

public class AdminDao {

	/**
	 * 管理员登陆
	 * @throws Exception 
	 * */
	public Admin login(Admin admin,Connection con) throws Exception{
		Admin resultAdmin = null;
		String sql = "select * from t_admin where adminName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, admin.getAdminName());
		pstmt.setString(2, admin.getPassword());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			resultAdmin = new Admin();
			resultAdmin.setAdminId(rs.getInt("adminId"));
			resultAdmin.setAdminName(rs.getString("adminName"));
			resultAdmin.setPassword(rs.getString("password"));
		}
		return resultAdmin;	
	}
	/**
	 * 修改密码
	 * */
	public int modifyPassword(Connection con,Admin admin)throws Exception{
		String sql="update t_admin set password=? where adminId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, admin.getPassword());
		pstmt.setInt(2, admin.getAdminId());
		return pstmt.executeUpdate();
	}
}

