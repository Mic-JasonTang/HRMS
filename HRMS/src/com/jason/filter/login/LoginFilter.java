package com.jason.filter.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.util.DBCon;

public class LoginFilter implements Filter {

	List<String> excludedPages = new ArrayList<String>(); 
	
	Connection conn;
	
	Statement stmt;
	
	ResultSet rs;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest reqeust, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpReqeust = (HttpServletRequest)reqeust;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpReqeust.getSession();
		String url = httpReqeust.getRequestURI();
//		System.out.println(url);
		url = url.substring(httpReqeust.getRequestURI().lastIndexOf("/") + 1);
//		System.out.println(url);
		if(!excludedPages.contains(url)) {
			conn = DBCon.getConn();
			stmt = DBCon.createStmt(conn);
			String sql = "select * from role";
			rs = DBCon.exeQuery(stmt, sql);
			String isAdmin = (String) session.getAttribute("isAdmin");
			try {
				if(isAdmin != null) {
					while(rs.next()) {
						String roleName = rs.getString("roleName");
						if(isAdmin.equals(roleName)) {
							if(roleName.contains("管理员")) { //如果查询到的角色名包裹管理员字样
								filterChain.doFilter(reqeust, response);
								break;
							}
						}
					}
				} else {
					httpResponse.sendRedirect(httpReqeust.getContextPath() + "/login.jsp");
				}
			} catch (SQLException e) {
				System.out.println("LoginFilter -> 查询role表出错 session.getAttribute('isAdmin')=" + session.getAttribute("isAdmin"));
				e.printStackTrace();
			} finally {
				DBCon.close(rs);
				DBCon.close(stmt);
				DBCon.close(conn);
			}
		} else { //如果请求了login.jsp就直接放行
			System.out.println(httpReqeust.getRequestURI());
			filterChain.doFilter(reqeust, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String[] pages = filterConfig.getInitParameter("excludedPages").split(",");
		for(String page : pages) {
			excludedPages.add(page);
			System.out.println(page);
		}
		System.out.println("excludedPages数组初始化完成");
	}

}
