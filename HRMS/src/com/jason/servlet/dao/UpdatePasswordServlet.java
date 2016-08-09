package com.jason.servlet.dao;

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
* @ClassName: UpdatePasswordServlet 
* @Description: change-password.jsp接受密码，更新数据库
* @author: Jason
* @date: 2016年7月14日 下午5:48:38 
*
 */
public class UpdatePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection conn = DBCon.getConn();
		String updateSql = "update employee set employeePass = ? where employeeId = ?";
		PreparedStatement pstmt_update = null; 
		pstmt_update = DBCon.createPstmt(conn, updateSql);
		String userId = request.getParameter("userId");
//System.out.println("userId = " + userId);
		String newPass1 = request.getParameter("new-password");
		String newPass2 = request.getParameter("new-password2");
//System.out.println("newPass2 = " + newPass2);
		if(newPass1.equals(newPass2)) {
			try {
				pstmt_update.setString(1, newPass1);
				pstmt_update.setString(2, userId);
				if(pstmt_update.executeUpdate() != 0) {
					System.out.println("密码修改成功");
					out.write("密码修改成功");
				} else {
					System.out.println("密码修改失败");
					out.write("密码修改失败");
				}
			} catch (SQLException e) {
				System.out.println("UpdatePasswordServlet -> 查询employe表获取密码出错,new-password = " + newPass1);
				e.printStackTrace();
			} finally {
				DBCon.close(pstmt_update);
				DBCon.close(conn);
			}
		} else {
			out.write("两次输入的密码不一致");
		}
	}

}
