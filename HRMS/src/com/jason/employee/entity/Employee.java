package com.jason.employee.entity;

import java.io.Serializable;

public class Employee implements Serializable{
	
	private String employeeId;
	
    private String employeeName;
    
    private String employeePass;
    
    private String employeeSex;
    
    private String employeeAge;
    
    private String employeeIdCard;
    
    private String employeeBirth;
    
    private String employeePhone;
    
    private String employeeEmail;
    
    private String employeeAddress;
    
    private String employeeBranchName;
    
    private String employeeEnterTime;
    
    private String employeeLeaveTime;
    
    private String employeeRoleName;
    
    private String employeeStatus;
    
    private String employeeLastLoginTime;
    
    private String employeeLastLoginIP;

	public Employee(String employeeId, String employeeName,
			String employeePass, String employeeSex, String employeeAge,
			String employeeIdCard, String employeeBirth, String employeePhone,
			String employeeEmail, String employeeAddress,
			String employeeBranchName, String employeeEnterTime,
			String employeeLeaveTime, String employeeRoleName,
			String employeeStatus, String employeeLastLoginTime,
			String employeeLastLoginIP) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeePass = employeePass;
		this.employeeSex = employeeSex;
		this.employeeAge = employeeAge;
		this.employeeIdCard = employeeIdCard;
		this.employeeBirth = employeeBirth;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.employeeAddress = employeeAddress;
		this.employeeBranchName = employeeBranchName;
		this.employeeEnterTime = employeeEnterTime;
		this.employeeLeaveTime = employeeLeaveTime;
		this.employeeRoleName = employeeRoleName;
		this.employeeStatus = employeeStatus;
		this.employeeLastLoginTime = employeeLastLoginTime;
		this.employeeLastLoginIP = employeeLastLoginIP;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePass() {
		return employeePass;
	}

	public void setEmployeePass(String employeePass) {
		this.employeePass = employeePass;
	}

	public String getEmployeeSex() {
		return employeeSex;
	}

	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}

	public String getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeIdCard() {
		return employeeIdCard;
	}

	public void setEmployeeIdCard(String employeeIdCard) {
		this.employeeIdCard = employeeIdCard;
	}

	public String getEmployeeBirth() {
		return employeeBirth;
	}

	public void setEmployeeBirth(String employeeBirth) {
		this.employeeBirth = employeeBirth;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeBranchName() {
		return employeeBranchName;
	}

	public void setEmployeeBranchName(String employeeBranchName) {
		this.employeeBranchName = employeeBranchName;
	}

	public String getEmployeeEnterTime() {
		return employeeEnterTime;
	}

	public void setEmployeeEnterTime(String employeeEnterTime) {
		this.employeeEnterTime = employeeEnterTime;
	}

	public String getEmployeeLeaveTime() {
		return employeeLeaveTime;
	}

	public void setEmployeeLeaveTime(String employeeLeaveTime) {
		this.employeeLeaveTime = employeeLeaveTime;
	}

	public String getEmployeeRoleName() {
		return employeeRoleName;
	}

	public void setEmployeeRoleName(String employeeRoleName) {
		this.employeeRoleName = employeeRoleName;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeeLastLoginTime() {
		return employeeLastLoginTime;
	}

	public void setEmployeeLastLoginTime(String employeeLastLoginTime) {
		this.employeeLastLoginTime = employeeLastLoginTime;
	}

	public String getEmployeeLastLoginIP() {
		return employeeLastLoginIP;
	}

	public void setEmployeeLastLoginIP(String employeeLastLoginIP) {
		this.employeeLastLoginIP = employeeLastLoginIP;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeePass=" + employeePass
				+ ", employeeSex=" + employeeSex + ", employeeAge="
				+ employeeAge + ", employeeIdCard=" + employeeIdCard
				+ ", employeeBirth=" + employeeBirth + ", employeePhone="
				+ employeePhone + ", employeeEmail=" + employeeEmail
				+ ", employeeAddress=" + employeeAddress
				+ ", employeeBranchName=" + employeeBranchName
				+ ", employeeEnterTime=" + employeeEnterTime
				+ ", employeeLeaveTime=" + employeeLeaveTime
				+ ", employeeRoleName=" + employeeRoleName
				+ ", employeeStatus=" + employeeStatus
				+ ", employeeLastLoginTime=" + employeeLastLoginTime
				+ ", employeeLastLoginIP=" + employeeLastLoginIP + "]";
	}
    
    
}
