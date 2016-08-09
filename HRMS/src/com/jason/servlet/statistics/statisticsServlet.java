package com.jason.servlet.statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.util.DBCon;

/**
 * 
* @ClassName: statisticsServlet 
* @Description: 查询各个部门的总人数,查询考勤信息
* welcome.jsp
* @author: Jason
* @date: 2016年7月11日 下午11:38:22 
*
 */
public class statisticsServlet extends HttpServlet implements Serializable{
	
	LinkedHashMap<String,String> branch_people_num; //存放部门人数
	
	LinkedHashMap<String,String> branch_people_Maxnum; //存放部门最大人数
	
	LinkedHashMap<String,String> attendance_normal_num; //考勤正常的人数
	
	LinkedHashMap<String,String> attendance_late_num; //考勤迟到的人数
	
	Connection conn;
	
	Statement stmt;
	
	PreparedStatement pstmt;
	
	ResultSet rs_attendance;
	
	/**
	 * 
	* @Title: queryBranchNumber 
	* @Description: 查询部门的现有的人数
	* @param session 通过session来传递people_list
	* @return void    返回类型 
	* @throws
	 */
	public void queryBranchPeople(HttpSession session) {
		conn = DBCon.getConn();
		String sql = "select branchName,branchPeople,branchMaxPeople from branch";
		stmt = DBCon.createStmt(conn);
		rs_attendance = DBCon.exeQuery(stmt, sql);
		branch_people_num = new LinkedHashMap<String,String>();
		branch_people_Maxnum = new LinkedHashMap<String,String>();
		try {
			while (rs_attendance.next()) {
				branch_people_num.put(rs_attendance.getString("branchName"), rs_attendance.getString("branchPeople"));
				branch_people_Maxnum.put(rs_attendance.getString("branchName"), rs_attendance.getString("branchMaxPeople"));
			}

		} catch (SQLException e) {
			System.out.println("statisticsServlrt -> 查询branch表出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs_attendance);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
		
	}
	/**
	 * 
	* @Title: queryAttendancePeople 
	* @Description: 查询考勤表中迟到的人和正常出勤的人 
	* @param session     参数
	* @return void    返回类型 
	* @throws
	 */
	public void queryAttendancePeople(HttpSession session) {
		conn = DBCon.getConn();
		stmt = DBCon.createStmt(conn);
		String[] branchNames = branch_people_num.keySet().toString().split(",");
		//去掉数组前面的[和后面的]
		for(int i=0; i<branchNames.length; i ++) {
			if(branchNames[i].contains("[") || branchNames[i].contains("]")) {
				branchNames[i] = branchNames[i].replace("[", "");
				branchNames[i] = branchNames[i].replace("]", "");
			}
			branchNames[i] = branchNames[i].trim();
		}
		int[] normal_count = new int[branchNames.length]; //考勤正常人数计数器
		int[] late_count = new int[branchNames.length]; //考勤迟到人数计数器
		attendance_normal_num = new LinkedHashMap<String,String>();
		attendance_late_num = new LinkedHashMap<String,String>();
		String sql_attendance = "select attendanceStatus,attendanceEmployeeId from attendance";
		String sql_employee = "select employeeBranchId from employee where employeeId = ?";
		String sql_branch = "select branchName from branch where branchId = ?";
		rs_attendance = DBCon.exeQuery(stmt, sql_attendance); //查询考勤表
		ResultSet rs_employee = null;
		ResultSet rs_branch = null;
		try {
			while(rs_attendance.next()) { //考勤结果集，每次通过一条考勤结果来看
				pstmt = DBCon.createPstmt(conn, sql_employee); 
				pstmt.setString(1, rs_attendance.getString("attendanceEmployeeId"));//设置employee表
				rs_employee = pstmt.executeQuery(); //查询employee表
				rs_employee.next(); //查询employee的结果集
				pstmt = DBCon.createPstmt(conn, sql_branch);
				pstmt.setString(1, rs_employee.getString("employeeBranchId")); //设置部门表
				rs_branch = pstmt.executeQuery();
				rs_branch.next(); 
				if(rs_attendance.getString("attendanceStatus").equals("正常")) {  //考勤正常的人
					for(int i=0; i<branchNames.length; i++) {
						String branchName = rs_branch.getString("branchName"); //获取部门名
						if(branchName.equals(branchNames[i])) { //如果当前的部门名字与数组中的部门名匹配，相应位计数
							normal_count[i] ++; //该部门的正常计数器+1
						}
//System.out.println(branchName + ",正常:" + String.valueOf(normal_count[i]));
//						attendance_normal_num.put(branchName, String.valueOf(normal_count[i]));
					}
				} else if(rs_attendance.getString("attendanceStatus").equals("迟到")) {
					for(int i=0; i<branchNames.length; i++) {
						String branchName = rs_branch.getString("branchName"); //获取部门名
						if(branchName.equals(branchNames[i])) { //如果当前的部门名字与数组中的部门名匹配，相应位计数
							late_count[i] ++; //该部门的迟到计数器+1
						}
//System.out.println(branchName + ",迟到:" + String.valueOf(late_count[i]));
//						attendance_late_num.put(branchName, String.valueOf(late_count[i]));
					}
				}
			}
System.out.println(Arrays.toString(normal_count));
System.out.println(Arrays.toString(late_count));
			for(int i=0; i<branchNames.length; i++) {
				attendance_normal_num.put(branchNames[i], String.valueOf(normal_count[i]));
				attendance_late_num.put(branchNames[i], String.valueOf(late_count[i]));
			}
		} catch (SQLException e) {
			System.out.println("statisticsServlet -> queryAttendancePeople出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rs_branch);
			DBCon.close(rs_employee);
			DBCon.close(rs_attendance);
			DBCon.close(pstmt);
			DBCon.close(stmt);
			DBCon.close(conn);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter print = response.getWriter();
		//查询部门人数
		queryBranchPeople(session);
		session.setAttribute("branch_people_num", branch_people_num);
		session.setAttribute("branch_people_Maxnum", branch_people_Maxnum);
System.out.println("branch_people_num 和 branch_people_Maxnum设置成功");
		//查询考情情况
		queryAttendancePeople(session);
System.out.println("正常情况：" + attendance_normal_num.keySet() + "=" + attendance_normal_num.values());
System.out.println("迟到情况：" + attendance_late_num.keySet() + "=" + attendance_late_num.values());
		session.setAttribute("attendance_normal_num", attendance_normal_num);
		session.setAttribute("attendance_late_num", attendance_late_num);
System.out.println("attendance_normal_num 和 attendance_late_num设置成功");

		//将数据放入到data的stringbuffer中
		StringBuffer data = new StringBuffer();
		data.append(branch_people_num.size()).append(",").append(branch_people_num.keySet()).append(",")
			.append(branch_people_num.values()).append(",").append(branch_people_Maxnum.values())
			.append(",").append(attendance_normal_num.values()).append(",")
			.append(attendance_late_num.values());
		//去掉其中所有的[]
		while(data.indexOf("[") != -1) {
			data.deleteCharAt(data.indexOf("["));
		}
		while(data.indexOf("]") != -1) {
			data.deleteCharAt(data.indexOf("]"));
		}
		System.out.println(data);
		System.out.println(data.toString());
		print.write(data.toString());
	}

}
