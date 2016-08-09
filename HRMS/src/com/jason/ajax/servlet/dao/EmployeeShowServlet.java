package com.jason.ajax.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.util.DBCon;

/**
 * 
* @ClassName: EmployeeShowServlet 
* @Description: employee-show.jsp
* @author: Jason
* @date: 2016年7月14日 下午5:42:06 
*
 */
public class EmployeeShowServlet extends HttpServlet {

	Connection conn;

	PreparedStatement pstmt;

	ResultSet rs;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter print = response.getWriter();
		HttpSession session = request.getSession();
//		employee_showes = new ArrayList<Employee_Show>();
		
System.out.println("请求的uri = " + request.getRequestURI());
		String userId = request.getParameter("userId");
		conn = DBCon.getConn();
		String sql = "select employeeName,employeeAge,employeeSex,employeePhone,employeeEmail,"
				+ "employeeAddress,employeeEnterTime,employeeStatus from employee where employeeId = ?";
		pstmt = DBCon.createPstmt(conn, sql);
		try {
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			String userName = rs.getString("employeeName");
			String userAge = rs.getString("employeeAge");
			String userSex = rs.getString("employeeSex");
			String userPhone = rs.getString("employeePhone");
			String userEmail = rs.getString("employeeEmail");
			String userAddr = rs.getString("employeeAddress");
			String userStartTime = rs.getString("employeeEnterTime");
			String userStatus = rs.getString("employeeStatus");
//			Employee_Show es = new Employee_Show(userId, userName, userAge, userSex,
//					userPhone, userEmail, userAddr, userStatus, userStartTime);
//			employee_showes.add(es);
//			session.setAttribute("employee_showes", employee_showes);
//			System.out.println("employee_showes数组初始化完成");
//			System.out.println(employee_showes.toString());
			System.out.println(userId +","+userName+","+userAge+","+userSex+","+
					userPhone+","+userEmail+","+userAddr+","+userStatus+","+userStartTime);
			print.write(userId +","+userName+","+userAge+","+userSex+","+
					userPhone+","+userEmail+","+userAddr+","+userStatus+","+userStartTime);
		} catch (SQLException e) {
			System.out.println("EmployeeShowServlet -> 在查询employee表显示employee-show.jsp时出错,"
					+ "userId=" + userId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
