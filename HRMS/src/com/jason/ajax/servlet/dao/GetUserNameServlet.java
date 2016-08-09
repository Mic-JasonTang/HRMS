package com.jason.ajax.servlet.dao;

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

import com.jason.util.DBCon;

/**
 * 
* @ClassName: GetUserNameServlet 
* @Description: change-password.jsp  Ajax请求
* @author: Jason
* @date: 2016年7月14日 下午5:38:47 
*
 */
public class GetUserNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
//System.out.println("userId = " + userId);
		Connection conn = DBCon.getConn();
		String selectSql = "select employeeName from employee where employeeId = ?";
		PreparedStatement pstmt_select = DBCon.createPstmt(conn, selectSql); 
		ResultSet rs_select = null;
		
		try {
			pstmt_select.setString(1, userId);
			rs_select = pstmt_select.executeQuery();
			if(rs_select.next()) {
				String employeeName = rs_select.getString("employeeName");
				out.write(employeeName);
			} else {
				out.write("userId" + userId + "不能被识别");
			}
		} catch (SQLException e) {
			System.out.println("GetUserNameServlet -> 查询employee时出错,userId = " + userId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_select);
			DBCon.close(pstmt_select);
			DBCon.close(conn);
		}
	}

}
