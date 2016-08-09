package com.jason.servlet.login;

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
import javax.servlet.http.HttpSession;

import com.jason.util.DBCon;
import com.jason.util.GetNowDateTime;

public class LoginServlet extends HttpServlet {
	
	Connection conn;
	
	String sql_employee = "select employeeId,employeeName,employeePass,employeeRoleId,employeeLastLoginTime,employeeLastLoginIP from employee";
	
	String sql_role = "select * from role where roleId = ?";
	
	PreparedStatement pstmt_role;
	
	Statement stmt_employee;
	
	ResultSet rs_employee; //查询employee的结果
	
	ResultSet rs_role; //查询角色的结果
	
	public void saveLog(String employeeId, String ip, String time) {
		conn = DBCon.getConn();
		String sql = "insert into sys_log (sys_logId,sys_logUserId,sys_logType,"
				+ "sys_logContent,sys_logIp,sys_logTime) "
				+ "values(null,?,?,?,?,?)";
		PreparedStatement pstmt = DBCon.createPstmt(conn,sql);
		try {
			pstmt.setString(1, employeeId);
			pstmt.setString(2, "登录日志");
			pstmt.setString(3, "登陆成功");
			pstmt.setString(4, ip);
			pstmt.setString(5, time);
			pstmt.execute();
			System.out.println("log记录成功");
		} catch (SQLException e) {
			System.out.println("LoginServlet -> saveLog出错 的ip、time出错");
System.out.println("employeeId=" + employeeId + ",ip=" + ip + ",time=" + time);
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt);
			DBCon.close(conn);
		}
		
	}
	/**
	 * 
	* @Title: updateIpAndTime 
	* @Description: 更新ip和时间 
	* @param employeeId
	* @param ip
	* @param time     参数
	* @return void    返回类型 
	* @throws
	 */
	public void updateIpAndTime(String employeeId, String ip, String time) {
		conn = DBCon.getConn();
		String sql_ip_time = "update employee set employeeLastLoginTime=?,employeeLastLoginIP=? "
				+ "where employeeId = ?";
		PreparedStatement pstmt_employee = DBCon.createPstmt(conn,sql_ip_time);
		try {
			pstmt_employee.setString(1, time);
			pstmt_employee.setString(2, ip);
			pstmt_employee.setString(3, employeeId);
			pstmt_employee.execute();
			System.out.println("ip和time更新成功");
		} catch (SQLException e) {
			System.out.println("LoginServlet -> 更新employe 的ip、time出错");
			System.out.println("employeeId=" + employeeId + ",ip=" + ip + ",time=" + time);
			e.printStackTrace();
		} finally {
			DBCon.close(pstmt_employee);
			DBCon.close(conn);
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean isLoginSuccess = false; //判断是否登录成功
		
		conn = DBCon.getConn();
		
		pstmt_role = DBCon.createPstmt(conn, sql_role); //加载预处理
		
		stmt_employee = DBCon.createStmt(conn); //创建employee查询语句
		
		rs_employee = DBCon.exeQuery(stmt_employee, sql_employee); //进行查询
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter print = response.getWriter(); //获得打印流
		HttpSession session = request.getSession();
		String picCode = (String) session.getAttribute("picCode"); //从session中获取验证码的值
		System.out.println("从session中获取验证码的值为:" + picCode);
		String msg = ""; //Ajax返回的信息
		String lastTime = ""; //上次登录时间
		String lastIp = ""; //上次登录ip
		
		//获取参数并去掉空格
		String username = request.getParameter("username");
		if(username != null) {
			username = username.trim();
		}
		String password = request.getParameter("password");
		if(password != null) {
			password = password.trim();
		}
		String checkCode = request.getParameter("checkCode");
		if(checkCode != null) {
			checkCode = checkCode.trim();
		}
System.out.println("Ajax传来的数据：username = " + username + ",password = " + password + ",checkCode = " + checkCode);
		try {
			while(rs_employee.next()) {
				
				if(username.equals(rs_employee.getString("employeeName"))) {

					if(password.equals(rs_employee.getString("employeePass"))) {
					
						if(checkCode.equalsIgnoreCase(picCode)) {
					
							lastTime = rs_employee.getString("employeeLastLoginTime");
							session.setAttribute("lastTime", lastTime);
							
							lastIp = rs_employee.getString("employeeLastLoginIP");
							session.setAttribute("lastIp", lastIp);
							
							pstmt_role.setString(1, rs_employee.getString("employeeRoleId"));  //设置roleId
							
							rs_role = pstmt_role.executeQuery();  //查询role表
							
							rs_role.next();  //游标下移，否则获取不到数据
							
							if(rs_employee.getInt("employeeRoleId") == rs_role.getInt("roleId")) { //如果roleId匹配
								session.setAttribute("isAdmin", rs_role.getString("roleName")); //管理员身份登录
								session.setAttribute("username", username); //将用户名放到session，用一个filter来检查session是否有这个值
								session.setAttribute("employeeId", rs_employee.getString("employeeId")); //用于主页中点击登录用户，查看个人信息
								
								lastTime = GetNowDateTime.getDateTime();
								lastIp = request.getRemoteHost();
								updateIpAndTime(rs_employee.getString("employeeId"),lastIp,lastTime);
								saveLog(rs_employee.getString("employeeId"),lastIp,lastTime);
								isLoginSuccess = true;
							} else {
								session.setAttribute("isAdmin", "非法用户"); //非法身份登录
								break;
							}
System.out.println(rs_role.getString("roleName") + "登录");
						} else {
							System.out.println("用户输入的验证码:" + checkCode + ",服务器中生成的验证码:" + picCode);
							msg = "验证码不正确";
							break;
						}
					} else {
System.out.println("数据库中employeePass=" + rs_employee.getString("employeePass") + ",password=" + password);
						msg = "密码不正确";
						break;
					}
				} else {
System.out.println("数据库中employeeName=" + rs_employee.getString("employeeName") + ",username=" + username);
					msg = "用户名不正确";
				}
			} 
		} catch (SQLException e) {
			System.out.println("LoginServlet -> 查询employee表出错");
			e.printStackTrace();
		} finally {
			closeAll(); //释放资源
		}
		if(isLoginSuccess) {
			print.write("success"); //登录成功。这里由于使用Ajax请求传送数据，将请求头信息给改变了，
									//所以用sendRedirect无法跳转
		} else {
System.out.println(msg);
			print.write(msg);
		}
	}
	
	/**
	 * 
	* @Title: closeAll 
	* @Description: 关闭所有连接
	* @return void    返回类型 
	* @throws
	 */
	public void closeAll() {
		DBCon.close(rs_employee);
		DBCon.close(rs_role);
		DBCon.close(stmt_employee);
		DBCon.close(pstmt_role);
		DBCon.close(conn);
	}
}
