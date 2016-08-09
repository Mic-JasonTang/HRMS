package com.jason.employee.entity;

import java.io.Serializable;

public class Education implements Serializable {
	
	private String educationId;
	
    private String educationCourseId;
    
    private String educationCourseName;
    
    private String educationCourseContent;
    
    private String educationCourseDay;
    
    private String educationCourseStart;
    
    private String educationCourseEnd;
    
    private String educationCourseEmployeeId;
    
    private String educationCourseEmployeeName;
    
    private String educationCourseBranchName;
    
    private String educationStatus;

	public Education(String educationId, String educationCourseId,
			String educationCourseName, String educationCourseContent,
			String educationCourseDay, String educationCourseStart,
			String educationCourseEnd, String educationCourseEmployeeId,
			String educationCourseEmployeeName,
			String educationCourseBranchName, String educationStatus) {
		super();
		this.educationId = educationId;
		this.educationCourseId = educationCourseId;
		this.educationCourseName = educationCourseName;
		this.educationCourseContent = educationCourseContent;
		this.educationCourseDay = educationCourseDay;
		this.educationCourseStart = educationCourseStart;
		this.educationCourseEnd = educationCourseEnd;
		this.educationCourseEmployeeId = educationCourseEmployeeId;
		this.educationCourseEmployeeName = educationCourseEmployeeName;
		this.educationCourseBranchName = educationCourseBranchName;
		this.educationStatus = educationStatus;
	}

	public String getEducationId() {
		return educationId;
	}

	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	public String getEducationCourseId() {
		return educationCourseId;
	}

	public void setEducationCourseId(String educationCourseId) {
		this.educationCourseId = educationCourseId;
	}

	public String getEducationCourseName() {
		return educationCourseName;
	}

	public void setEducationCourseName(String educationCourseName) {
		this.educationCourseName = educationCourseName;
	}

	public String getEducationCourseContent() {
		return educationCourseContent;
	}

	public void setEducationCourseContent(String educationCourseContent) {
		this.educationCourseContent = educationCourseContent;
	}

	public String getEducationCourseDay() {
		return educationCourseDay;
	}

	public void setEducationCourseDay(String educationCourseDay) {
		this.educationCourseDay = educationCourseDay;
	}

	public String getEducationCourseStart() {
		return educationCourseStart;
	}

	public void setEducationCourseStart(String educationCourseStart) {
		this.educationCourseStart = educationCourseStart;
	}

	public String getEducationCourseEnd() {
		return educationCourseEnd;
	}

	public void setEducationCourseEnd(String educationCourseEnd) {
		this.educationCourseEnd = educationCourseEnd;
	}

	public String getEducationCourseEmployeeId() {
		return educationCourseEmployeeId;
	}

	public void setEducationCourseEmployeeId(String educationCourseEmployeeId) {
		this.educationCourseEmployeeId = educationCourseEmployeeId;
	}

	public String getEducationCourseEmployeeName() {
		return educationCourseEmployeeName;
	}

	public void setEducationCourseEmployeeName(String educationCourseEmployeeName) {
		this.educationCourseEmployeeName = educationCourseEmployeeName;
	}

	public String getEducationCourseBranchName() {
		return educationCourseBranchName;
	}

	public void setEducationCourseBranchName(String educationCourseBranchName) {
		this.educationCourseBranchName = educationCourseBranchName;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	@Override
	public String toString() {
		return "Education [educationId=" + educationId + ", educationCourseId="
				+ educationCourseId + ", educationCourseName="
				+ educationCourseName + ", educationCourseContent="
				+ educationCourseContent + ", educationCourseDay="
				+ educationCourseDay + ", educationCourseStart="
				+ educationCourseStart + ", educationCourseEnd="
				+ educationCourseEnd + ", educationCourseEmployeeId="
				+ educationCourseEmployeeId + ", educationCourseEmployeeName="
				+ educationCourseEmployeeName + ", educationCourseBranchName="
				+ educationCourseBranchName + ", educationStatus="
				+ educationStatus + "]";
	}

    
}
