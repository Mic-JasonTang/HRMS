package com.jason.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.util.DBCon;
import com.jason.util.GetNowDateTime;

/**
 * 
* @ClassName: AddBranchServlet 
* @Description: branch-add.jsp 接受页面数据，插入branch表
* @author: Jason
* @date: 2016年7月14日 下午6:58:39 
*
 */
public class AddBranchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String branchName = request.getParameter("branchName");
		String branchDescribe = request.getParameter("branchDescribe");
		String branchCharge = request.getParameter("branchCharge");
		String branchMaxPeople = request.getParameter("branchMaxPeople");
		
System.out.println(branchName + "," + branchDescribe + "," + branchCharge + "," + branchMaxPeople);

		Connection conn = DBCon.getConn();
		String sql = "insert into branch (branchId,branchName,branchDescribe,branchChargeId,branchPeople,"
				+ "branchMaxPeople,branchStartTime) values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql);
		try {
			pstmt.setString(1, branchName);
			pstmt.setString(2, branchDescribe);
			pstmt.setString(3, branchCharge);
			pstmt.setString(4, "1");
			pstmt.setString(5, branchMaxPeople);
			pstmt.setString(6, GetNowDateTime.getDate());
			if(pstmt.executeUpdate() != 0) {
				out.write("增加部门成功，点击右上角关闭本页");
			} else {
				out.write("增加部门失败");
			}
		} catch (SQLException e) {
			System.out.println("AddBranchServlet -> 插入branch表时出错");
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		
	}

}
