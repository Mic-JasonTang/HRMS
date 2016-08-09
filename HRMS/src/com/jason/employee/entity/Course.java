package com.jason.employee.entity;

import java.io.Serializable;

public class Course implements Serializable{
	
	private String courseId;
	
    private String courseName;
    
    private String courseContent;
    
    private String courseDay;
    
    private String courseStart;
    
    private String courseEnd;
    
    private String courseEmployeeId;
    
    private String courseEmployeeName;
    
    private String courseBranchName;

	public Course(String courseId, String courseName, String courseContent,
			String courseDay, String courseStart, String courseEnd,
			String courseEmployeeId, String courseEmployeeName,
			String courseBranchName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseContent = courseContent;
		this.courseDay = courseDay;
		this.courseStart = courseStart;
		this.courseEnd = courseEnd;
		this.courseEmployeeId = courseEmployeeId;
		this.courseEmployeeName = courseEmployeeName;
		this.courseBranchName = courseBranchName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public String getCourseDay() {
		return courseDay;
	}

	public void setCourseDay(String courseDay) {
		this.courseDay = courseDay;
	}

	public String getCourseStart() {
		return courseStart;
	}

	public void setCourseStart(String courseStart) {
		this.courseStart = courseStart;
	}

	public String getCourseEnd() {
		return courseEnd;
	}

	public void setCourseEnd(String courseEnd) {
		this.courseEnd = courseEnd;
	}

	public String getCourseEmployeeId() {
		return courseEmployeeId;
	}

	public void setCourseEmployeeId(String courseEmployeeId) {
		this.courseEmployeeId = courseEmployeeId;
	}

	public String getCourseEmployeeName() {
		return courseEmployeeName;
	}

	public void setCourseEmployeeName(String courseEmployeeName) {
		this.courseEmployeeName = courseEmployeeName;
	}

	public String getCourseBranchName() {
		return courseBranchName;
	}

	public void setCourseBranchName(String courseBranchName) {
		this.courseBranchName = courseBranchName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName
				+ ", courseContent=" + courseContent + ", courseDay="
				+ courseDay + ", courseStart=" + courseStart + ", courseEnd="
				+ courseEnd + ", courseEmployeeId=" + courseEmployeeId
				+ ", courseEmployeeName=" + courseEmployeeName
				+ ", courseBranchName=" + courseBranchName + "]";
	}
    
    
}
