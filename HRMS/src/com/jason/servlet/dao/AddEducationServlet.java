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
* @ClassName: AddEducationServlet 
* @Description: education-course-add.jsp接受它的数据，并插入数据库
* @author: Jason
* @date: 2016年7月14日 下午10:59:35 
*
 */
public class AddEducationServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String courseName = request.getParameter("courseName");
		String courseContent = request.getParameter("courseContent");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String courseBranch = request.getParameter("courseBranch");
		String courseSpeaker = request.getParameter("courseSpeaker");
		String courseMaxNumber = request.getParameter("courseMaxNumber");
		String educationEmployeeIds = request.getParameter("educationEmployeeName");
		
		Connection conn = DBCon.getConn();
		String sql_course = "insert into course (courseId,courseName,courseContent,"
				+ "courseStart,courseEnd,courseDay,courseChargeEmployeeId,courseMaxNumber) values(null,?,?,?,?,?,?,?)";
		int courseId = 0;
		PreparedStatement pstmt_course = null;
		PreparedStatement pstmt_education = null;
		ResultSet rsKey = null;
		try {
			pstmt_course = conn.prepareStatement(sql_course,
					PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e1) {
			System.out.println("AddEducationServlet -> 产生主键错误");
			e1.printStackTrace();
		};
		try {
			pstmt_course.setString(1, courseName);
			pstmt_course.setString(2, courseContent);
			pstmt_course.setString(3, startDate);
			pstmt_course.setString(4, endDate);
			String couserDay = startTime + " ~ " + endTime;
			pstmt_course.setString(5, couserDay);
			pstmt_course.setString(6, courseSpeaker);
			pstmt_course.setString(7, courseMaxNumber);
			if(pstmt_course.executeUpdate() != 0) {
System.out.println("课程插入成功");
				rsKey = pstmt_course.getGeneratedKeys();
				rsKey.next(); //向下移动
				courseId = rsKey.getInt(1);
System.out.println("获取到的courseId：" + courseId);
				String sql_education = "insert into education (educationId,educationCourseId,educationEmployeeId,educationStatus) "
						+ " values(null,?,?,?)";
				pstmt_education = DBCon.createPstmt(conn, sql_education);
				pstmt_education.setInt(1, courseId);
				pstmt_education.setString(2, educationEmployeeIds);
				pstmt_education.setString(3, "正在进行中");
				if(pstmt_education.executeUpdate() != 0) {
System.out.println("培训信息插入成功，点击右上角关闭页面");
					out.write("培训信息插入成功，点击右上角关闭页面");
				} else {
System.out.println("培训信息插入失败");
					out.write("培训信息插入失败");
				}
			} else {
System.out.println("课程信息插入失败");
				out.write("课程信息插入失败");
			}
		} catch (SQLException e) {
			System.out.println("AddEducationServlet -> 增加培训信息时出错");
			e.printStackTrace();
		} finally {
			DBCon.close(rsKey);
			DBCon.close(pstmt_education);
			DBCon.close(pstmt_course);
			DBCon.close(conn);
		}
		
	}

}
