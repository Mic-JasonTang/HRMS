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

import com.jason.system.entity.Log;
import com.jason.util.DBCon;

/**
 * 
* @ClassName: LogListServlet 
* @Description: system-log-list.jsp
* @author: Jason
* @date: 2016年7月14日 下午5:43:02 
*
 */
public class LogListServlet extends HttpServlet {

	List<Log> logs;
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	ResultSet rs;
	
	/**
	 * 
	* @Title: getLogData 
	* @Description: 去查询role表，将结果放入roles数组中
	* @return void    返回类型 
	* @throws
	 */
	public boolean getLogData () {
		boolean isQuerySuccess = false;
		logs = new ArrayList<Log>();
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		String sql_log = "select * from sys_log";
		rs = DBCon.exeQuery(stmt, sql_log);
		try {
			while(rs.next()) {
				String logId = String.valueOf(rs.getInt("sys_logId"));
				String logUserId = rs.getString("sys_logUserId");
				String logUserName = getUserName(rs.getString("sys_logUserId"));
				String logType = rs.getString("sys_logType");
				String logContent = rs.getString("sys_logContent");
				String logIp = rs.getString("sys_logIp");
				String logTime = rs.getString("sys_logTime");
				Log log = new Log(logId, logUserId, logUserName, logType, logContent, logIp, logTime);
				logs.add(log);
			}
System.out.println("logs数组添加完毕");
			isQuerySuccess = true;
		} catch (SQLException e) {
System.out.println("LogListServlet -> getLogData查询log表出错");
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
	* @Title: getUserName 
	* @Description: 通过userId去Employee表中查询userName
	* @param userId
	* @return     参数
	* @return String    返回类型 
	* @throws
	 */
	public String getUserName(String userId) {
		String userName = null;
		conn = DBCon.getConn();
		String sql_employee = "select employeeName from employee where employeeId = ?";
		pstmt = DBCon.createPstmt(conn, sql_employee);
		ResultSet rs_employee = null;
		try {
			pstmt.setString(1, userId);
			rs_employee = pstmt.executeQuery();
			rs_employee.next();
			userName = rs_employee.getString("employeeName");
		} catch (SQLException e) {
			System.out
					.println("UserListServlet -> getUserName查询employee表出错，传进来的userId="
							+ userId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_employee);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return userName;
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
		if(getLogData()) {
			session.setAttribute("logs", logs);
			for(Log log : logs) {
				System.out.println(log.toString());
			}
			print.write("查询成功，请刷新页面");
		} else {
			print.write("logs数组设置失败");
		}
	}

}
