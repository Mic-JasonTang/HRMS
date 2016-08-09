package com.jason.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.util.DBCon;

public class InsertAdminServlet extends HttpServlet {

	Connection conn;
	
	ResultSet rs;
	
	/**
	 * 
	* @Title: getRoleName 
	* @Description: 通过roleId去查找role表，返回roleName 
	* @param roleId
	* @return     参数
	* @return String    返回roleName
	* @throws
	 */
	public int getRoleId(String roleName) {
		int roleId = 0;
		conn = DBCon.getConn();
		String sql_role = "select roleId from role where roleName = ?";
		PreparedStatement pstmt;
		pstmt = DBCon.createPstmt(conn, sql_role);
		ResultSet rs_role = null;
		try {
			pstmt.setString(1, roleName);
			rs_role= pstmt.executeQuery();
			rs_role.next();
			roleId = rs_role.getInt("roleId");
		} catch (SQLException e) {
System.out.println("UserListServlet -> getRoleId查询role表出错，传进来的roleName=" + roleName);
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt);
			DBCon.close(rs_role);
		}
		return roleId;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String adminName = request.getParameter("adminName");
		String adminRole = request.getParameter("adminRole");
		System.out.println(adminName);
		System.out.println(adminRole);
		if(adminName.equals("0")) {
			out.write("请选择管理员");
			return;
		}
		if(adminRole == null) {
			out.write("请选择管理员角色");
			return;
		}
		conn = DBCon.getConn();
		String sql = "update employee set employeeRoleId = ? where employeeName = ?";
		PreparedStatement pstmt;
		pstmt = DBCon.createPstmt(conn, sql);
		try {
			pstmt.setInt(1, getRoleId(adminRole));
			pstmt.setString(2, adminName);
			if(pstmt.executeUpdate() != 0) {
System.out.println("管理员设置成功");	
				out.write("管理员设置成功");
			} else {
				out.write("管理员设置失败，请重新设置");
			}
//response.sendRedirect(request.getContextPath() + "/system-admin-add.jsp");
		} catch (SQLException e) {
			System.out.println("InsertAdminServlet -> 查询employee表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
	}

}
