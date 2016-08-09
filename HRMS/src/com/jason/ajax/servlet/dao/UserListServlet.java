package com.jason.ajax.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.system.entity.Admin;
import com.jason.system.entity.User;
import com.jason.util.DBCon;

/**
 * 
* @ClassName: UserListServlet 
* @Description: 从employee中获取用户列表中的信息
* system-user-list.jsp
* @author: Jason
* @date: 2016年7月12日 下午2:20:07 
*
 */
public class UserListServlet extends HttpServlet {

	List<User> users;
	
	List<Admin> admins;
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	/**
	 * 
	* @Title: getEmployeeData 
	* @Description: 在employee中查询相关信息填写users数组在system-user-list.jsp中显示
	* @return void    返回类型 
	* @throws
	 */
	public boolean getEmployeeData() {
		boolean isQuerySuccess = false;
		String sql_employee = "select employeeId,employeeName,employeePhone,employeeEmail,employeeEnterTime,employeeRoleId,employeeStatus from employee";
		users = new ArrayList<User>();
		admins = new ArrayList<Admin>();
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		ResultSet rs_employee = DBCon.exeQuery(stmt, sql_employee);
		try {
			while(rs_employee.next()) {
				String userId = rs_employee.getString("employeeId");
				String userName = rs_employee.getString("employeeName");
				String userStartTime = rs_employee.getString("employeeEnterTime");
				String userRoleName = getRoleName(rs_employee.getInt("employeeRoleId"));
				String employeeStatus = rs_employee.getString("employeeStatus");
				if(!employeeStatus.contains("离职")) {  //离职的不与添加
					if(userRoleName.contains("管理员")) {
						String adminId = userId;
						String adminName = userName;
						String adminPhone = rs_employee.getString("employeePhone");
						String adminEmail = rs_employee.getString("employeeEmail");
						String adminStartTime = userStartTime;
						String adminRoleName = userRoleName;
						Admin admin = new Admin(adminId, adminName, adminPhone, adminEmail, adminRoleName, adminStartTime);
						admins.add(admin);
	System.out.println("admins数组添加完毕");
					}
					User user = new User(userId, userName, userStartTime, userRoleName);
					users.add(user);
				}
			}
			isQuerySuccess = true;
System.out.println("users数组添加完毕");
		} catch (SQLException e) {
			System.out.println("UserListServlet -> queryEmployee查询employee表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		return isQuerySuccess;
	}
	
	/**
	 * 
	* @Title: getRoleName 
	* @Description: 通过roleId去查找role表，返回roleName 
	* @param roleId
	* @return     参数
	* @return String    返回roleName
	* @throws
	 */
	public String getRoleName(int roleId) {
		String roleName = null;
		conn = DBCon.getConn();
		String sql_role = "select roleName from role where roleId = ?";
		pstmt = DBCon.createPstmt(conn, sql_role);
		ResultSet rs_role = null;
		try {
			pstmt.setInt(1, roleId);
			rs_role= pstmt.executeQuery();
			rs_role.next();
			roleName = rs_role.getString("roleName");
		} catch (SQLException e) {
System.out.println("UserListServlet -> getRoleName查询role表出错，传进来的roleId=" + roleId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_role);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return roleName;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		HttpSession session = request.getSession();
		if(getEmployeeData()) {
			session.setAttribute("users", users);
			session.setAttribute("admins", admins);
			for(User user : users) {
				System.out.println(user.toString());
			}
			for(Admin admin : admins) {
				System.out.println(admin.toString());
			}
			print.write("查询成功，请刷新页面");
		} else {
			print.write("users/admins数组设置出错");
		}
	}

}
