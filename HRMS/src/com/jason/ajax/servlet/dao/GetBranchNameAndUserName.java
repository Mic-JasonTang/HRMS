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
* @ClassName: GetBranchNameAndUserName 
* @Description: education-course-add.jsp 将部门名和人员传送过去
* @author: Jason
* @date: 2016年7月14日 下午7:15:39 
*
 */
public class GetBranchNameAndUserName extends HttpServlet {

	StringBuffer datas = null;
	
	/**
	 * 
	* @Title: getEmployeeData 
	* @Description: 得到员工Id，名字，部门
	* @param out     参数
	* @return void    返回类型 
	* @throws
	 */
	public void GetBranchNameAndUserName(PrintWriter out) {
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql_employee = "select employeeId, employeeName, employeeBranchId from employee";
		
		ResultSet rs = DBCon.exeQuery(stmt, sql_employee);
		String employeeId = "";
		String employeeName = "";
		String branchName = "";
		int branchId = 0;
		try {
			while(rs.next()) {
				employeeId = rs.getString("employeeId");
				employeeName = rs.getString("employeeName");
				branchId = rs.getInt("employeeBranchId");
				branchName = getBranchName(branchId);
				datas.append(employeeId).append(",").append(employeeName).append(",").
					append(branchName).append(";");
			}
			datas.deleteCharAt(datas.lastIndexOf(";"));
System.out.println(datas.toString());
			out.write(datas.toString());
		} catch (SQLException e) {
			System.out.println("GetBranchNameAndUserName -> 查询employee表时出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
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
System.out.println("GetBranchNameAndUserName -> getBranchName查询branch表出错，传进来的branchId=" + branchId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs_branch);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return branchName;
	}
	

	/**
	 * 
	* @Title: getBranchChargeName 
	* @Description: 获得部门负责人的姓名 
	* @param out     参数
	* @return void    返回类型 
	* @throws
	 */
	public void getBranchChargeName() {
		datas = new StringBuffer();
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql = "select employeeId, employeeName, employeeBranchId from employee";
		ResultSet rs = DBCon.exeQuery(stmt, sql);
		StringBuffer employeeNames = new StringBuffer();
		String employeeId = "";
		String employeeName = "";
		String branchName = "";
		try {
			while(rs.next()) {
				employeeId = rs.getString("employeeId");
				if(isBranchCharge(employeeId)) { //如果是部门负责人
					employeeName = rs.getString("employeeName");
					branchName = getBranchName(rs.getInt("employeeBranchId"));
					employeeNames.append(employeeId).append(",").append(employeeName).append(",").append(branchName).append(";");
				}
			}
			employeeNames.deleteCharAt(employeeNames.lastIndexOf(";")); //删除最后一个分号
			employeeNames.append("#");                       //用#代替
			datas.append(employeeNames.toString());
System.out.println(employeeNames.toString());
		} catch (SQLException e) {
			System.out.println("GetBranchNameAndUserName -> 查询employee表出错");
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
//System.out.println(employeeId + "是部门负责人");
//			} else {
//				System.out.println(employeeId + "不是部门负责人");
			}
		} catch (SQLException e) {
			System.out.println("GetBranchNameAndUserName -> isBranchCharge查询branch时出错,employeeId = " + employeeId);
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
		
		getBranchChargeName();  //首先得到部门负责人的信息，再去执行下面，用#做结束符
		
		GetBranchNameAndUserName(out);
	}

}
