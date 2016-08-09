package com.jason.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * 
* @ClassName: DBCon 
* @Description: 数据库常用操作封装 
* @author: Jason
* @date: 2016年7月11日 下午4:25:20 
*
 */
public class DBCon {
	
	/**
	 * 
	* @Title: getConn 
	* @Description: 返回数据库连接
	* @return Connection    返回类型 
	* @throws
	 */
	public static Connection getConn() {
		Connection conn = null;
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/hrms", "root", "361239731");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * 
	* @Title: createStmt 
	* @Description: 通过conn来创建Stmt 
	* @param conn
	* @return Statement    返回类型 
	* @throws
	 */
	public static Statement createStmt(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("DB--加载语句出错");
			e.printStackTrace();
		}
		return stmt;
	}
	/**
	 * 
	* @Title: getPstmtKey 
	* @Description: 创建预处理语句后返回产生的主键 
	* @param conn
	* @param sql
	* @return int    返回类型 
	* @throws
	 */
	public static int getPstmtKey(Connection conn, String sql) {
		ResultSet rsKey = null;
		int key = -1;
		PreparedStatement pstmt = null;
		if(pstmt == null) {
			try {
				pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				rsKey = pstmt.getGeneratedKeys();
				rsKey.next();
				key = rsKey.getInt(1);
			} catch (SQLException e) {
				System.out.println("DB--创建预处理语句,返回主键时出错");
				e.printStackTrace();
			}
		}
		return key;
	}
	/**
	 * 
	* @Title: createPstmt
	* @Description: 通过conn和sql来创建预处理语句
	* @param conn
	* @param sql语句
	* @return PreparedStatement    返回类型 
	* @throws
	 */
	public static PreparedStatement createPstmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		if (pstmt == null) {
			try {
				pstmt = conn.prepareStatement(sql);
			} catch (SQLException e) {
				System.out.println("DB--加载预处理语句出错");
				e.printStackTrace();
			}
		}
		return pstmt;
	}
	/**
	 * 
	* @Title: exeQuery 
	* @Description: 通过stmt和sql执行查询 
	* @param stmt
	* @param sql
	* @return    设定文件 
	* @return ResultSet    返回类型 
	* @throws
	 */
	public static ResultSet exeQuery(Statement stmt, String sql) {
		ResultSet rs = null;
		if (rs == null) {
			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println("DB--数据查询出错");
				e.printStackTrace();
			}
		}
		return rs;
	}
	/**
	 * 
	* @Title: exeInsert 
	* @Description: 通过conn和sql执行插入 
	* @param conn
	* @param sql    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void exeInsert(Connection conn, String sql) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println("DB--数据插入出错");
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: exeUpdate 
	* @Description: 通过conn和sql执行更新
	* @param conn
	* @param sql    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void exeUpdate(Connection conn, String sql) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("DB--数据更新出错");
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: close 
	* @Description: 关闭conn
	* @param conn     
	* @return void    返回类型 
	* @throws
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @Title: close 
	* @Description: 关闭stmt
	* @param stmt     
	* @return void    返回类型 
	* @throws
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @Title: close 
	* @Description: 关闭rs
	* @param rs    
	* @return void    返回类型 
	* @throws
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @Title: close 
	* @Description: 关闭pstmt
	* @param pstmt     参数
	* @return void    返回类型 
	* @throws
	 */
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
