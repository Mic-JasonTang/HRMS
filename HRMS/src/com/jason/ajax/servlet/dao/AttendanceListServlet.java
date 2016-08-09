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

import com.jason.util.DBCon;
import com.jason.employee.entity.Attendance;

/**
 * 
* @ClassName: AttendanceListServlet 
* @Description: employee-attendance-list.jsp调用
* @author: Jason
* @date: 2016年7月14日 下午5:39:32 
*
 */
public class AttendanceListServlet extends HttpServlet {

	List<Attendance> attendances;
	
	public boolean getAttendanceData() {
		boolean isQuerySuccess = false;
		attendances = new ArrayList<Attendance>();
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql = "select * from attendance";

		ResultSet rs = DBCon.exeQuery(stmt, sql); 
		try {
			while(rs.next()) {
				
				String attendanceId = String.valueOf(rs.getInt("attendanceId"));
				
			    String attendanceEmployeeId = rs.getString("attendanceEmployeeId");
			    
			    String attendanceEmployeeName = getEmployeeName(attendanceEmployeeId);
			    
			    String attendanceEmployeeBranchName = getBranchName(getEmployeeBranchId(attendanceEmployeeId));
			    
			    String attendanceStart = rs.getString("attendanceStart");
			    
			    String attendanceEnd = rs.getString("attendanceEnd");
			    
			    String attendanceStatus = rs.getString("attendanceStatus");
			    
			    String attendanceDesc = rs.getString("attendanceDesc");
			    
			    Attendance attendance = new Attendance(attendanceId, attendanceEmployeeId, attendanceEmployeeName,
			    		attendanceEmployeeBranchName, attendanceStart, attendanceEnd, attendanceStatus, 
			    		attendanceDesc);
			    attendances.add(attendance);
			}
			isQuerySuccess = true;
			System.out.println("attendances数组设置成功");
		} catch (SQLException e) {
			System.out.println("AttendanceListServlet -> getAttendanceData查询attendance表出错");
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
		Connection conn = DBCon.getConn();
		String sql_employee = "select employeeName from employee where employeeId = ?";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql_employee);
		ResultSet rs_employee = null;
		try {
			pstmt.setString(1, employeeId);
			rs_employee = pstmt.executeQuery();
			rs_employee.next();
			employeeName = rs_employee.getString("employeeName");
		} catch (SQLException e) {
			System.out
					.println("AttendanceListServlet -> getEmployeeName查询employee表出错，传进来的employeeId="
							+ employeeId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return employeeName;
	}
	/**
	 * 
	* @Title: getEmployeeBranchId 
	* @Description: 通过employeeId去Employee表中查询employeeBranchId
	* @param employeeId
	* @return     参数
	* @return String    返回类型 
	* @throws
	 */
	public int getEmployeeBranchId(String employeeId) {
		int employeeBranchId = 0;
		Connection conn = DBCon.getConn();
		String sql_employee = "select employeeBranchId from employee where employeeId = ?";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql_employee);
		ResultSet rs_employee = null;
		try {
			pstmt.setString(1, employeeId);
			rs_employee = pstmt.executeQuery();
			rs_employee.next();
			employeeBranchId = rs_employee.getInt("employeeBranchId");
		} catch (SQLException e) {
			System.out
					.println("AttendanceListServlet -> getEmployeeBranchId查询employee表出错，传进来的employeeId="
							+ employeeId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return employeeBranchId;
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
		Connection conn = DBCon.getConn();
		String sql_branch = "select branchName from branch where branchId = ?";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql_branch);
		ResultSet rs_branch = null;
		try {
			pstmt.setInt(1, branchId);
			rs_branch= pstmt.executeQuery();
			rs_branch.next();
			branchName = rs_branch.getString("branchName");
		} catch (SQLException e) {
System.out.println("AttendanceListServlet -> getBranchName查询branch表出错，传进来的branchId=" + branchId);
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
		if(getAttendanceData()) {
			session.setAttribute("attendances", attendances);
			out.write("查询成功,请刷新页面");
		} else {
			out.write("attendances数组设置错误");
		}
	}

}
