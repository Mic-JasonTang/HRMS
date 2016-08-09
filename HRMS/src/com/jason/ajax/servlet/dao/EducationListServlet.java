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
import com.jason.employee.entity.Course;
import com.jason.employee.entity.Education;

/**
 * 
* @ClassName: EducationListServlet 
* @Description: education-courses-list.jsp
* @author: Jason
* @date: 2016年7月14日 下午5:40:26 
*
 */
public class EducationListServlet extends HttpServlet {

	List<Education> educations;
	
	/**
	 * 
	* @Title: getEducationData 
	* @Description: 获取培训信息数据，需要查询培训表和课表，部门表，员工表
	* @return     参数
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean getEducationData() {
		boolean isQuerySuccess = false;
		Connection conn = DBCon.getConn();
		educations = new ArrayList<Education>();
		
		String sql_course = "select * from course where courseId = ?";
		PreparedStatement pstmt_course = DBCon.createPstmt(conn, sql_course);
		ResultSet rs_course = null;

		Statement stmt_education = DBCon.createStmt(conn);
		String sql_education = "select * from education";
		ResultSet rs_education = null;
		
		rs_education = DBCon.exeQuery(stmt_education, sql_education);
		try {
			while(rs_education.next()) {
				
				String educationId = String.valueOf(rs_education.getInt("educationId"));
				
			    String educationCourseId = String.valueOf(rs_education.getInt("educationCourseId"));
			    
			    String educationStatus = rs_education.getString("educationStatus");
			    //查询课表
			    pstmt_course.setInt(1, rs_education.getInt("educationCourseId"));
			    
			    rs_course = pstmt_course.executeQuery();
			    
			    rs_course.next();
			    
			    String educationCourseName = rs_course.getString("courseName");
			    
			    String educationCourseContent = rs_course.getString("courseContent");
			    
			    String educationCourseDay = rs_course.getString("courseDay");
			    
			    String educationCourseStart = rs_course.getString("courseStart");
			    
			    String educationCourseEnd = rs_course.getString("courseEnd");
			    
			    String educationCourseEmployeeId = rs_course.getString("courseChargeEmployeeId");
			    
			    String educationCourseEmployeeName = getEmployeeName(educationCourseEmployeeId);
			    
			    String educationCourseBranchName = getBranchName(getEmployeeBranchId(educationCourseEmployeeId));
			    
			    Education education = new Education(educationId, educationCourseId, educationCourseName, 
			    		educationCourseContent, educationCourseDay, educationCourseStart, educationCourseEnd, 
			    		educationCourseEmployeeId, educationCourseEmployeeName, educationCourseBranchName, 
			    		educationStatus);
			    educations.add(education);
			}
			isQuerySuccess = true;
			System.out.println("educations数组设置完成");
		} catch (SQLException e) {
			System.out.println("EducationListServlet -> getEducationData时,查询educaiton/course表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs_course);
			DBCon.close(rs_education);
			DBCon.close(pstmt_course);
			DBCon.close(stmt_education);
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
					.println("EducationListServlet -> getEmployeeName查询employee表出错，传进来的employeeId="
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
					.println("EducationListServlet -> getEmployeeBranchId查询employee表出错，传进来的employeeId="
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
System.out.println("EducationListServlet -> getBranchName查询branch表出错，传进来的branchId=" + branchId);
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
		if(getEducationData()) {
			session.setAttribute("educations", educations);
			out.write("查询成功，请刷新页面");
		} else {
			out.write("educations表设置出错");
		}
	}

}
