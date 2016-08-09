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

import com.jason.employee.entity.Employee;
import com.jason.util.DBCon;

/**
 * 
* @ClassName: EmployeeListServlet 
* @Description: employee-leave-list.jsp
* employee-record-list.jsp
* @author: Jason
* @date: 2016年7月14日 下午5:40:54 
*
 */
public class EmployeeListServlet extends HttpServlet {
	
	List<Employee> employees;
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	/**
	 * 
	* @Title: getEmployeeData 
	* @Description: 在employee中查询相关信息填写employess数组
	* @return void    返回类型 
	* @throws
	 */
	public boolean getEmployeeData() {
		boolean isQuerySuccess = false;
		String sql_employee = "select * from employee";
		employees = new ArrayList<Employee>();
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		ResultSet rs_employee = DBCon.exeQuery(stmt, sql_employee);
		try {
			while(rs_employee.next()) {
				String employeeId = rs_employee.getString("employeeId");
				
			    String employeeName = rs_employee.getString("employeeName");
			    
			    String employeePass = rs_employee.getString("employeePass");
			    
			    String employeeSex = rs_employee.getString("employeeSex");
			    
			    String employeeAge = rs_employee.getString("employeeAge");
			    
			    String employeeIdCard = rs_employee.getString("employeeIdCard");
			    
			    String employeeBirth = rs_employee.getString("employeeBirth");
			    
			    String employeePhone = rs_employee.getString("employeePhone");
			    
			    String employeeEmail = rs_employee.getString("employeeEmail");
			    
			    String employeeAddress = rs_employee.getString("employeeAddress");
			    
			    String employeeBranchName = getBranchName(rs_employee.getInt("employeeBranchId"));
			    
			    String employeeEnterTime = rs_employee.getString("employeeEnterTime");
			    
			    String employeeLeaveTime = rs_employee.getString("employeeLeaveTime");
			    
			    String employeeRoleName = getRoleName(rs_employee.getInt("employeeRoleId"));
			    
			    String employeeStatus = rs_employee.getString("employeeStatus");
			    
			    String employeeLastLoginTime = rs_employee.getString("employeeLastLoginTime");
			    
			    String employeeLastLoginIP = rs_employee.getString("employeeLastLoginIP");
			    Employee employee = new Employee(employeeId, employeeName, 
			    		employeePass, employeeSex, employeeAge, employeeIdCard, 
			    		employeeBirth, employeePhone, employeeEmail, employeeAddress, 
			    		employeeBranchName, employeeEnterTime, employeeLeaveTime, 
			    		employeeRoleName, employeeStatus, employeeLastLoginTime, employeeLastLoginIP);
			    employees.add(employee);
			}
			isQuerySuccess = true;
System.out.println("employees数组添加完毕");
		} catch (SQLException e) {
			System.out.println("AddEmployeeServlet -> queryEmployee查询employee表出错");
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
System.out.println("AddEmployeeServlet -> getRoleName查询role表出错，传进来的roleId=" + roleId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_role);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return roleName;
	}
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
System.out.println("AddEmployeeServlet -> getBranchName查询branch表出错，传进来的branchId=" + branchId);
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(getEmployeeData()) {
			session.setAttribute("employees", employees);
		} else {
			out.print("employees表设置错误");
		}
	}

}
