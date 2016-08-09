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
* @ClassName: AddEmployeeServlet 
* @Description: employee-add.jsp 接受他的数据，并访问数据库
* @author: Jason
* @date: 2016年7月14日 下午5:48:07 
*
 */
public class AddEmployeeServlet extends HttpServlet {

	/**
	 * 
	* @Title: addBranchPeople 
	* @Description: 给定部门人数 + 1 ,更新部门人数
	* @param branchId
	* @return void    返回类型 
	* @throws
	 */
	public boolean addBranchPeople(int branchId) {
		boolean isUpdateSuccess = false;
		Connection conn = DBCon.getConn();
		String sql_select = "select branchPeople from branch where branchId = ?";
		String sql_update = "update branch set branchPeople = ? where branchId = ?";
		ResultSet rs = null;
		//先进行查询
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql_select);
		try {
			pstmt.setInt(1, branchId);
			rs = pstmt.executeQuery();
			rs.next();
			String branchPeople = rs.getString("branchPeople");
System.out.println("从数据中获取到的人数是 :" + branchPeople);
			int number = Integer.parseInt(branchPeople);
			number ++;
			pstmt = DBCon.createPstmt(conn, sql_update);
			pstmt.setString(1, String.valueOf(number));
			pstmt.setInt(2, branchId);
			if(pstmt.executeUpdate() != 0) {
				isUpdateSuccess = true;
				System.out.println("部门人数增加成功");
			} else {
				System.out.println("部门人数增加失败");
			}
		} catch (SQLException e) {
			System.out.println("NewEmployeeIdServlet -> addBranchPeople查询branch表出错,branchId=" + branchId);
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		return isUpdateSuccess;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//从添加页面中获取数据
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String employeePass = request.getParameter("employeePass");
		String employeeSex = request.getParameter("employeeSex");
		String employeeAge = request.getParameter("employeeAge");
		String employeeIdCard = request.getParameter("employeeIdCard");
		String employeeBirth = request.getParameter("employeeBirth");
		String employeePhone = request.getParameter("employeePhone");
		String employeeEmail = request.getParameter("employeeEmail");
		String employeeAddress = request.getParameter("employeeAddress");
		String employeeBranchId = request.getParameter("employeeBranchId");
		String employeeEnterTime = request.getParameter("employeeEnterTime");
		int roleId = 2; //2表示普通用户
		String employeeStatus = request.getParameter("employeeStatus");
		
		System.out.println(employeeId + "," + employeeName + "," + employeePass + "," + employeeSex + "," + employeeAge
				+ "," + employeeIdCard + "," + employeeBirth + "," + employeePhone + "," + employeeEmail 
				+ "," + employeeAddress + "," + employeeBranchId + "," + employeeEnterTime + "," + employeeStatus);
		Connection conn = DBCon.getConn();
		String sql="insert into employee (employeeId,employeeName,employeePass,employeeSex,employeeAge,"
				+ "employeeIdCard,employeeBirth,employeePhone,employeeEmail,employeeAddress,employeeBranchId,employeeEnterTime,"
				+ "employeeRoleId,employeeStatus) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = DBCon.createPstmt(conn, sql);
		
		try {
			pstmt.setString(1, employeeId);
			pstmt.setString(2, employeeName);
			pstmt.setString(3, employeePass);
			pstmt.setString(4, employeeSex);
			pstmt.setString(5, employeeAge);
			pstmt.setString(6, employeeIdCard);
			pstmt.setString(7, employeeBirth);
			pstmt.setString(8, employeePhone);
			pstmt.setString(9, employeeEmail);
			pstmt.setString(10, employeeAddress);
			pstmt.setInt   (11, Integer.parseInt(employeeBranchId));
			pstmt.setString(12, employeeEnterTime);
			pstmt.setInt(13, roleId);
			pstmt.setString(14, employeeStatus);
			
			if(pstmt.executeUpdate() != 0) {
				if(addBranchPeople(Integer.parseInt(employeeBranchId))) {
					out.write("添加成功");
				}
			} else {
				out.write("添加失败，请重新添加");
			}
			
		} catch (SQLException e) {
			System.out.println("AddEmployeeServlet -> 添加员工时，插入employee表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		
	}

}
