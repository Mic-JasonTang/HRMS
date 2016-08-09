package com.jason.ajax.servlet.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.util.DBCon;
import com.jason.util.GetNowDateTime;

/**
 * 
* @ClassName: NewEmployeeIdServlet 
* @Description: employee-add.jsp中显示employeeId和branchName
* @author: Jason
* @date: 2016年7月14日 下午5:43:20 
*
 */
public class NewEmployeeIdServlet extends HttpServlet {

	
	/**
	 * 
	* @Title: getBranchName 
	* 
	* @Description: 将部门名查询出来,并返回
	* 
	* @return StringBuffer    返回类型 
	* 格式为:  1,技术部;2研发部;……
	* @throws
	 */
	public String getBranchName() {
		StringBuffer branchNames = new StringBuffer();
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql = "select branchId, branchName from branch";
		int count = 0;
		ResultSet rs = DBCon.exeQuery(stmt, sql);
		try {
			while(rs.next()) {
				String branchId = String.valueOf(rs.getInt("branchId"));
				String branchName = rs.getString("branchName");
				branchNames.append(branchId).append(",").append(branchName).append(";");
				count ++;
			}
System.out.println("部门数据准备完毕");
		} catch (SQLException e) {
			System.out.println("NewEmployeeIdServlet -> getBranchName查询branch表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		branchNames.insert(0, count); //第一个数字为数组长度
		return branchNames.toString();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Connection conn = DBCon.getConn();
		Statement stmt = DBCon.createStmt(conn);
		String sql = "select employeeId from employee";
		String employeeId = ""; 
		Integer number = 0;
		
		//保存 number 和 employeeId编号，使number(末3位编号)为key
		LinkedHashMap<Integer,String> employeeIds = new LinkedHashMap<Integer,String>();
//		List<Integer> numbers = new ArrayList<Integer>();
		
		ResultSet rs = DBCon.exeQuery(stmt, sql);
		try {
			while(rs.next()) {
				employeeId = rs.getString("employeeId");
				number = Integer.parseInt(employeeId.substring(8, employeeId.length())); //取出每一个编号
				employeeIds.put(number, employeeId); //放入数组
System.out.println("employeeId = " + employeeId);
			}
			if(employeeId.length() == 11) {
				
				Collection<Integer> numbers = employeeIds.keySet();
				
				number = Collections.max(numbers); //找到最大值
				
				employeeId = employeeIds.get(number);
System.out.println("找到的最大编号对应的ID是" + employeeId);
				String year = employeeId.substring(0, 4);
				String month = employeeId.substring(4, 6);
				String day = employeeId.substring(6, 8);
System.out.println("year" + year + ",month" + month + ",day" + day + ",number" + number);
				String newYear = GetNowDateTime.getYear();
				String newMonth = GetNowDateTime.getMonth();
				String newDay = GetNowDateTime.getDay();
				if(!year.equals(newYear)) { //如果年不相等
					year = newYear;
				}
				if(!month.equals(newMonth)) { //如果月不相等
					month = newMonth;
				}
				if(!day.equals(newDay)) { //如果日不相等
					day = newDay;
				}
				number = number + 1;
				if(number < 99) {
					employeeId = year + month + day + "0" + number; //加0拼接
				} else {
					employeeId = year + month + day + number; //拼接
				}
System.out.println("处理之后得到的employeeId = " + employeeId);
				
				String branchData = getBranchName();
				
				out.write(employeeId + branchData);
			} else {
				out.write("id格式不对,请检查数据库已有数据");
			}
		} catch (SQLException e) {
			System.out.println("NewEmployeeIdServlet -> 查询employee获取最新Id出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
	}
}
