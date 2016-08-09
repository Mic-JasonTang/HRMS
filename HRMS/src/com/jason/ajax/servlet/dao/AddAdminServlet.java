package com.jason.ajax.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.util.DBCon;

/** 
* @ClassName: AddAdminServlet 
* @Description: Ajax请求响应类，使用Ajax来返回部门名和人名数据 system-admin-add.jsp调用
* @author: Jason
* @date: 2016年7月13日 上午10:45:27 
*  
*/
public class AddAdminServlet extends HttpServlet {
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	/**
	 * 
	* @Title: getBranchName 
	* @Description: 通过branchId去查找branch表，返回branchName 
	* @param branchId
	* @return     参数
	* @return String    返回branchName
	* @throws
	 */
	public String getBranchName(int branchId) {
		String branchName = null;
		conn = DBCon.getConn();
		String sql_branch = "select branchName from branch where branchId = ?";
		pstmt = DBCon.createPstmt(conn, sql_branch);
		ResultSet rs_branch = null;
		try {
			pstmt.setInt(1, branchId);
			rs_branch= pstmt.executeQuery();
			rs_branch.next();
			branchName = rs_branch.getString("branchName");
		} catch (SQLException e) {
System.out.println("AddAdminServlet -> getBranchName查询branch表出错，传进来的branchId=" + branchId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_branch);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return branchName;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		ResultSet rs_employee;
		StringBuffer datas = new StringBuffer();
		String employeeName = "";
//		String branchName = "";
		int count = 0; //记录符合条件的个数
		boolean isAdmin = false; //判断员工的roleId是不是等于1
		
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		String sql_employee = "select employeeName,employeeBranchId,employeeRoleId,employeeStatus from employee";
		
		rs_employee = DBCon.exeQuery(stmt, sql_employee);
		try {
			while(rs_employee.next()) {
				isAdmin = rs_employee.getInt("employeeRoleId") == 1 ? true : false;
				String employeeStatus = rs_employee.getString("employeeStatus");
				if(! isAdmin && !employeeStatus.contains("离职")) {
					employeeName = rs_employee.getString("employeeName");
//					branchName = getBranchName(rs_employee.getInt("employeeBranchId"));
					count ++;
//					if(!datas.toString().contains(branchName)) { //格式是 技术部：张三,立式,王五;开发部。。。。
//						datas = datas.append(branchName).append(":");
//					}
					datas = datas.append(employeeName).append(",");
				}
			}
			datas.deleteCharAt(datas.lastIndexOf(","));
//System.out.println(datas);
			datas.insert(0, count);
//System.out.println(datas);
		print.write(datas.toString());
		} catch (SQLException e) {
			System.out.println("AddAdminServlet -> 查询employee表时出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		
	}

}
