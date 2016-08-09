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
 * 
* @ClassName: GetBranchChargeName 
* @Description: branch-add.jsp中显示部门负责人信息
* @author: Jason
* @date: 2016年7月14日 下午6:08:16 
*
 */
public class GetBranchChargeName extends HttpServlet {

	/**
	 * 
	* @Title: getBranchChargeName 
	* @Description: 获得部门负责人的姓名 
	* @param out     参数
	* @return void    返回类型 
	* @throws
	 */
	public void getBranchChargeName(PrintWriter out) {
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql = "select employeeId, employeeName from employee";
		ResultSet rs = DBCon.exeQuery(stmt, sql);
		StringBuffer employeeNames = new StringBuffer();
		String employeeId = "";
		String employeeName = "";
		try {
			while(rs.next()) {
				employeeId = rs.getString("employeeId");
				if(!isBranchCharge(employeeId)) { //如果不是部门负责人
					employeeName = rs.getString("employeeName");
					employeeNames.append(employeeId).append(",").append(employeeName).append(";");
				}
			}
			employeeNames.deleteCharAt(employeeNames.lastIndexOf(";"));
System.out.println(employeeNames.toString());
			out.write(employeeNames.toString());
		} catch (SQLException e) {
			System.out.println("GetBranchChargeName -> 查询employee表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		
	}
	/**
	 * 
	* @Title: isBranchCharge 
	* @Description: 根据employeeId去查询此员工是否为部门负责人 
	* @param employeeId
	* @return     参数
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean isBranchCharge(String employeeId) {
		boolean isCharge = false;
		Connection conn = DBCon.getConn();
		String sql = "select branchId from branch where branchChargeId = ?";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql);
		ResultSet rs = null;
		try {
			pstmt.setString(1, employeeId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isCharge = true;
System.out.println(employeeId + "是部门负责人");
			} else {
				System.out.println(employeeId + "不是部门负责人");
			}
		} catch (SQLException e) {
			System.out.println("GetBranchChargeName -> isBranchCharge查询branch时出错,employeeId = " + employeeId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return isCharge;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		getBranchChargeName(out);
	}

}
