package com.jason.employee.entity;

import java.io.Serializable;

public class RePub implements Serializable{
	
	private String reward_publishId;
	
    private String reward_publishType;
    
    private String reward_publishName;
    
    private String reward_publishReason;
    
    private String reward_publishEmployeeId;
    
    private String reward_publishEmployeeName;
    
    private String reward_publishEmployeeBranchName;
    
    private String reward_publishTime;

    private String reward_publishMoney;

	public RePub(String reward_publishId, String reward_publishType,
			String reward_publishName, String reward_publishReason,
			String reward_publishEmployeeId, String reward_publishEmployeeName,
			String reward_publishEmployeeBranchName, String reward_publishTime,
			String reward_publishMoney) {
		super();
		this.reward_publishId = reward_publishId;
		this.reward_publishType = reward_publishType;
		this.reward_publishName = reward_publishName;
		this.reward_publishReason = reward_publishReason;
		this.reward_publishEmployeeId = reward_publishEmployeeId;
		this.reward_publishEmployeeName = reward_publishEmployeeName;
		this.reward_publishEmployeeBranchName = reward_publishEmployeeBranchName;
		this.reward_publishTime = reward_publishTime;
		this.reward_publishMoney = reward_publishMoney;
	}

	public String getReward_publishId() {
		return reward_publishId;
	}

	public void setReward_publishId(String reward_publishId) {
		this.reward_publishId = reward_publishId;
	}

	public String getReward_publishType() {
		return reward_publishType;
	}

	public void setReward_publishType(String reward_publishType) {
		this.reward_publishType = reward_publishType;
	}

	public String getReward_publishName() {
		return reward_publishName;
	}

	public void setReward_publishName(String reward_publishName) {
		this.reward_publishName = reward_publishName;
	}

	public String getReward_publishReason() {
		return reward_publishReason;
	}

	public void setReward_publishReason(String reward_publishReason) {
		this.reward_publishReason = reward_publishReason;
	}

	public String getReward_publishEmployeeId() {
		return reward_publishEmployeeId;
	}

	public void setReward_publishEmployeeId(String reward_publishEmployeeId) {
		this.reward_publishEmployeeId = reward_publishEmployeeId;
	}

	public String getReward_publishEmployeeName() {
		return reward_publishEmployeeName;
	}

	public void setReward_publishEmployeeName(String reward_publishEmployeeName) {
		this.reward_publishEmployeeName = reward_publishEmployeeName;
	}

	public String getReward_publishEmployeeBranchName() {
		return reward_publishEmployeeBranchName;
	}

	public void setReward_publishEmployeeBranchName(
			String reward_publishEmployeeBranchName) {
		this.reward_publishEmployeeBranchName = reward_publishEmployeeBranchName;
	}

	public String getReward_publishTime() {
		return reward_publishTime;
	}

	public void setReward_publishTime(String reward_publishTime) {
		this.reward_publishTime = reward_publishTime;
	}

	public String getReward_publishMoney() {
		return reward_publishMoney;
	}

	public void setReward_publishMoney(String reward_publishMoney) {
		this.reward_publishMoney = reward_publishMoney;
	}

	@Override
	public String toString() {
		return "RePub [reward_publishId=" + reward_publishId
				+ ", reward_publishType=" + reward_publishType
				+ ", reward_publishName=" + reward_publishName
				+ ", reward_publishReason=" + reward_publishReason
				+ ", reward_publishEmployeeId=" + reward_publishEmployeeId
				+ ", reward_publishEmployeeName=" + reward_publishEmployeeName
				+ ", reward_publishEmployeeBranchName="
				+ reward_publishEmployeeBranchName + ", reward_publishTime="
				+ reward_publishTime + ", reward_publishMoney="
				+ reward_publishMoney + "]";
	}
    

}
