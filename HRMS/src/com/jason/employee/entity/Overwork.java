package com.jason.employee.entity;

import java.io.Serializable;

public class Overwork implements Serializable{
	
	private String overworkId;
	
    private String overworkEmployeeId;
    
    private String overworkEmployeeSex;
    
    private String overworkEmployeeName;
    
    private String overworkEmployeeBranchName;
    
    private String overworkReason;
    
    private String overworkStart;
    
    private String overworkEnd;
    
    private String overworkStatus;

	public Overwork(String overworkId, String overworkEmployeeId,
			String overworkEmployeeSex, String overworkEmployeeName,
			String overworkEmployeeBranchName, String overworkReason,
			String overworkStart, String overworkEnd, String overworkStatus) {
		super();
		this.overworkId = overworkId;
		this.overworkEmployeeId = overworkEmployeeId;
		this.overworkEmployeeSex = overworkEmployeeSex;
		this.overworkEmployeeName = overworkEmployeeName;
		this.overworkEmployeeBranchName = overworkEmployeeBranchName;
		this.overworkReason = overworkReason;
		this.overworkStart = overworkStart;
		this.overworkEnd = overworkEnd;
		this.overworkStatus = overworkStatus;
	}

	public String getOverworkId() {
		return overworkId;
	}

	public void setOverworkId(String overworkId) {
		this.overworkId = overworkId;
	}

	public String getOverworkEmployeeId() {
		return overworkEmployeeId;
	}

	public void setOverworkEmployeeId(String overworkEmployeeId) {
		this.overworkEmployeeId = overworkEmployeeId;
	}

	public String getOverworkEmployeeSex() {
		return overworkEmployeeSex;
	}

	public void setOverworkEmployeeSex(String overworkEmployeeSex) {
		this.overworkEmployeeSex = overworkEmployeeSex;
	}

	public String getOverworkEmployeeName() {
		return overworkEmployeeName;
	}

	public void setOverworkEmployeeName(String overworkEmployeeName) {
		this.overworkEmployeeName = overworkEmployeeName;
	}

	public String getOverworkEmployeeBranchName() {
		return overworkEmployeeBranchName;
	}

	public void setOverworkEmployeeBranchName(String overworkEmployeeBranchName) {
		this.overworkEmployeeBranchName = overworkEmployeeBranchName;
	}

	public String getOverworkReason() {
		return overworkReason;
	}

	public void setOverworkReason(String overworkReason) {
		this.overworkReason = overworkReason;
	}

	public String getOverworkStart() {
		return overworkStart;
	}

	public void setOverworkStart(String overworkStart) {
		this.overworkStart = overworkStart;
	}

	public String getOverworkEnd() {
		return overworkEnd;
	}

	public void setOverworkEnd(String overworkEnd) {
		this.overworkEnd = overworkEnd;
	}

	public String getOverworkStatus() {
		return overworkStatus;
	}

	public void setOverworkStatus(String overworkStatus) {
		this.overworkStatus = overworkStatus;
	}

	@Override
	public String toString() {
		return "Overwork [overworkId=" + overworkId + ", overworkEmployeeId="
				+ overworkEmployeeId + ", overworkEmployeeSex="
				+ overworkEmployeeSex + ", overworkEmployeeName="
				+ overworkEmployeeName + ", overworkEmployeeBranchName="
				+ overworkEmployeeBranchName + ", overworkReason="
				+ overworkReason + ", overworkStart=" + overworkStart
				+ ", overworkEnd=" + overworkEnd + ", overworkStatus="
				+ overworkStatus + "]";
	}

    
}
