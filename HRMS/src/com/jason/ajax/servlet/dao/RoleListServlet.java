package com.jason.ajax.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import com.jason.system.entity.Role;
import com.jason.util.DBCon;

/**
 * 
* @ClassName: RoleListServlet 
* @Description: 在数据库中去查询role表，结果放在roles中
* system-admin-role-list.jsp
* @author: Jason
* @date: 2016年7月12日 下午4:05:25 
*
 */
public class RoleListServlet extends HttpServlet {

	List<Role> roles; //roles数组
	
	Connection conn;
	
	Statement stmt;
	
	ResultSet rs;
	
	/**
	 * 
	* @Title: getRoleData 
	* @Description: 去查询role表，将结果放入roles数组中
	* @return void    返回类型 
	* @throws
	 */
	public boolean getRoleData () {
		boolean isQuerySuccess = false;
		roles = new ArrayList<Role>();
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		String sql_role = "select * from role";
		rs = DBCon.exeQuery(stmt, sql_role);
		try {
			while(rs.next()) {
				String roleId = String.valueOf(rs.getInt("roleId"));
				String roleName = rs.getString("roleName");
				String roleDesc = rs.getString("roleDesc");
				Role role = new Role(roleId, roleName, roleDesc);
				roles.add(role);
			}
System.out.println("roles数组添加完毕");
			isQuerySuccess = true;
		} catch (SQLException e) {
System.out.println("RoleListServlet -> getRoleData查询角色表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		return isQuerySuccess;
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
		if(getRoleData()) {
			session.setAttribute("roles", roles);
			for(Role role : roles) {
				System.out.println(role.toString());
			}
			print.write("查询成功，请刷新页面");
		} else {
			print.write("roles数组设置失败");
		}
	}

}
