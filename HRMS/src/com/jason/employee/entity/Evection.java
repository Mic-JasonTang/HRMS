package com.jason.employee.entity;

import java.io.Serializable;

public class Evection implements Serializable{
	
	private String evectionId;
	
    private String evectionEmployeeId;
    
    private String evectionEmployeeSex;
    
    private String evectionEmployeeName;
    
    private String evectionEmployeeBranchName;
    
    private String evectionReason;
    
    private String evectionStart;
    
    private String evectionEnd;
    
    private String evectionStatus;

	public Evection(String evectionId, String evectionEmployeeId,
			String evectionEmployeeSex, String evectionEmployeeName,
			String evectionEmployeeBranchName, String evectionReason,
			String evectionStart, String evectionEnd, String evectionStatus) {
		super();
		this.evectionId = evectionId;
		this.evectionEmployeeId = evectionEmployeeId;
		this.evectionEmployeeSex = evectionEmployeeSex;
		this.evectionEmployeeName = evectionEmployeeName;
		this.evectionEmployeeBranchName = evectionEmployeeBranchName;
		this.evectionReason = evectionReason;
		this.evectionStart = evectionStart;
		this.evectionEnd = evectionEnd;
		this.evectionStatus = evectionStatus;
	}

	public String getEvectionId() {
		return evectionId;
	}

	public void setEvectionId(String evectionId) {
		this.evectionId = evectionId;
	}

	public String getEvectionEmployeeId() {
		return evectionEmployeeId;
	}

	public void setEvectionEmployeeId(String evectionEmployeeId) {
		this.evectionEmployeeId = evectionEmployeeId;
	}

	public String getEvectionEmployeeSex() {
		return evectionEmployeeSex;
	}

	public void setEvectionEmployeeSex(String evectionEmployeeSex) {
		this.evectionEmployeeSex = evectionEmployeeSex;
	}

	public String getEvectionEmployeeName() {
		return evectionEmployeeName;
	}

	public void setEvectionEmployeeName(String evectionEmployeeName) {
		this.evectionEmployeeName = evectionEmployeeName;
	}

	public String getEvectionEmployeeBranchName() {
		return evectionEmployeeBranchName;
	}

	public void setEvectionEmployeeBranchName(String evectionEmployeeBranchName) {
		this.evectionEmployeeBranchName = evectionEmployeeBranchName;
	}

	public String getEvectionReason() {
		return evectionReason;
	}

	public void setEvectionReason(String evectionReason) {
		this.evectionReason = evectionReason;
	}

	public String getEvectionStart() {
		return evectionStart;
	}

	public void setEvectionStart(String evectionStart) {
		this.evectionStart = evectionStart;
	}

	public String getEvectionEnd() {
		return evectionEnd;
	}

	public void setEvectionEnd(String evectionEnd) {
		this.evectionEnd = evectionEnd;
	}

	public String getEvectionStatus() {
		return evectionStatus;
	}

	public void setEvectionStatus(String evectionStatus) {
		this.evectionStatus = evectionStatus;
	}

	@Override
	public String toString() {
		return "Evection [evectionId=" + evectionId + ", evectionEmployeeId="
				+ evectionEmployeeId + ", evectionEmployeeSex="
				+ evectionEmployeeSex + ", evectionEmployeeName="
				+ evectionEmployeeName + ", evectionEmployeeBranchName="
				+ evectionEmployeeBranchName + ", evectionReason="
				+ evectionReason + ", evectionStart=" + evectionStart
				+ ", evectionEnd=" + evectionEnd + ", evectionStatus="
				+ evectionStatus + "]";
	}

    
}
