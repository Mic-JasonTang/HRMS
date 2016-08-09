package com.jason.employee.entity;

import java.io.Serializable;

public class Branch implements Serializable{
	
	private String branchId;
	
    private String branchName;
    
    private String branchDescribe;
    
    private String branchChargeId;
    
    private String branchChargeName;
    
    private String branchPeople;
    
    private String branchMaxPeople;
    
    private String branchPhotoes;
    
    private String branchPhotoName;
    
    private String branchPerform;
    
    private String branchStartTime;

	public Branch(String branchId, String branchName, String branchDescribe,
			String branchChargeId, String branchChargeName,
			String branchPeople, String branchMaxPeople, String branchPhotoes,
			String branchPhotoName, String branchPerform, String branchStartTime) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchDescribe = branchDescribe;
		this.branchChargeId = branchChargeId;
		this.branchChargeName = branchChargeName;
		this.branchPeople = branchPeople;
		this.branchMaxPeople = branchMaxPeople;
		this.branchPhotoes = branchPhotoes;
		this.branchPhotoName = branchPhotoName;
		this.branchPerform = branchPerform;
		this.branchStartTime = branchStartTime;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchDescribe() {
		return branchDescribe;
	}

	public void setBranchDescribe(String branchDescribe) {
		this.branchDescribe = branchDescribe;
	}

	public String getBranchChargeId() {
		return branchChargeId;
	}

	public void setBranchChargeId(String branchChargeId) {
		this.branchChargeId = branchChargeId;
	}

	public String getBranchChargeName() {
		return branchChargeName;
	}

	public void setBranchChargeName(String branchChargeName) {
		this.branchChargeName = branchChargeName;
	}

	public String getBranchPeople() {
		return branchPeople;
	}

	public void setBranchPeople(String branchPeople) {
		this.branchPeople = branchPeople;
	}

	public String getBranchMaxPeople() {
		return branchMaxPeople;
	}

	public void setBranchMaxPeople(String branchMaxPeople) {
		this.branchMaxPeople = branchMaxPeople;
	}

	public String getBranchPhotoes() {
		return branchPhotoes;
	}

	public void setBranchPhotoes(String branchPhotoes) {
		this.branchPhotoes = branchPhotoes;
	}

	public String getBranchPhotoName() {
		return branchPhotoName;
	}

	public void setBranchPhotoName(String branchPhotoName) {
		this.branchPhotoName = branchPhotoName;
	}

	public String getBranchPerform() {
		return branchPerform;
	}

	public void setBranchPerform(String branchPerform) {
		this.branchPerform = branchPerform;
	}

	public String getBranchStartTime() {
		return branchStartTime;
	}

	public void setBranchStartTime(String branchStartTime) {
		this.branchStartTime = branchStartTime;
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName
				+ ", branchDescribe=" + branchDescribe + ", branchChargeId="
				+ branchChargeId + ", branchChargeName=" + branchChargeName
				+ ", branchPeople=" + branchPeople + ", branchMaxPeople="
				+ branchMaxPeople + ", branchPhotoes=" + branchPhotoes
				+ ", branchPhotoName=" + branchPhotoName + ", branchPerform="
				+ branchPerform + ", branchStartTime=" + branchStartTime + "]";
	}

    
    
}
