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

import com.jason.employee.entity.Branch;
import com.jason.util.DBCon;

/**
 * 
* @ClassName: BranchListServlet 
* @Description: branch-list.jsp
* @author: Jason
* @date: 2016年7月14日 下午5:40:11 
*
 */
public class BranchListServlet extends HttpServlet {

	List<Branch> branchs;
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	/**
	 * 
	* @Title: getBranchData 
	* @Description: 获取部门数据
	* @return     参数
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean getBranchData() {
		boolean isQuerySuccess = false;
		branchs = new ArrayList<Branch>();
		conn = DBCon.getConn();
		String sql = "select * from branch";
		stmt = DBCon.createStmt(conn);
		ResultSet rs = DBCon.exeQuery(stmt, sql);
		try {
			while (rs.next()) {
				String branchId = String.valueOf(rs.getInt("branchId"));
				
			    String branchName = rs.getString("branchName");
			    
			    String branchDescribe = rs.getString("branchDescribe");
			    
			    String branchChargeId = rs.getString("branchChargeId");
			    
			    String branchChargeName = getEmployeeName(branchChargeId);
			    
			    String branchPeople = rs.getString("branchPeople");
			    
			    String branchMaxPeople = rs.getString("branchMaxPeople");
			    
			    String branchPhotoes = rs.getString("branchPhotoes");
			    
			    String branchPhotoName = rs.getString("branchPhotoName");
			    
			    String branchStartTime = rs.getString("branchStartTime");
			    
			    String branchPerform = rs.getString("branchPerform");
			    
			    Branch branch = new Branch(branchId, branchName, branchDescribe, branchChargeId, 
			    		branchChargeName, branchPeople, branchMaxPeople, branchPhotoes, 
			    		branchPhotoName, branchPerform, branchStartTime);
			    branchs.add(branch);
			}
			isQuerySuccess = true;
			System.out.println("branchs数组设置成功");
		} catch (SQLException e) {
			System.out.println("BranchListServlet -> getBranchData出错,查询branch表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		return isQuerySuccess;
	}
	
	/**
	 * 
	* @Title: getEmployeeName 
	* @Description: 通过employeeId去Employee表中查询employeeName
	* @param employeeId
	* @return     参数
	* @return String    返回类型 
	* @throws
	 */
	public String getEmployeeName(String employeeId) {
		String employeeName = null;
		conn = DBCon.getConn();
		String sql_employee = "select employeeName from employee where employeeId = ?";
		pstmt = DBCon.createPstmt(conn, sql_employee);
		ResultSet rs_employee = null;
		try {
			pstmt.setString(1, employeeId);
			rs_employee = pstmt.executeQuery();
			rs_employee.next();
			employeeName = rs_employee.getString("employeeName");
		} catch (SQLException e) {
			System.out
					.println("BranchListServlet -> getEmployeeName查询employee表出错，传进来的employeeId="
							+ employeeId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return employeeName;
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
		if(getBranchData()) {
			session.setAttribute("branchs", branchs);
		} else {
			out.write("branchs数组设置错误");
		}
	}

}
