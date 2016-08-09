package com.jason.system.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: Admin 
* @Description: 管理员列表，在system-admin.list.jsp中显示
* @author: Jason
* @date: 2016年7月12日 下午3:39:17 
*
 */
public class Admin implements Serializable{
	
	private String adminId;
	
	private String adminName;
	
	private String adminPhone;
	
	private String adminEmail;
	
	private String adminRoleName;
	
	private String adminStartTime;

	public Admin(String adminId, String adminName, String adminPhone,
			String adminEmail, String adminRoleName, String adminStartTime) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminEmail = adminEmail;
		this.adminRoleName = adminRoleName;
		this.adminStartTime = adminStartTime;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminRoleName() {
		return adminRoleName;
	}

	public void setAdminRoleName(String adminRoleName) {
		this.adminRoleName = adminRoleName;
	}

	public String getAdminStartTime() {
		return adminStartTime;
	}

	public void setAdminStartTime(String adminStartTime) {
		this.adminStartTime = adminStartTime;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName
				+ ", adminPhone=" + adminPhone + ", adminEmail=" + adminEmail
				+ ", adminRoleName=" + adminRoleName + ", adminStartTime="
				+ adminStartTime + "]";
	}
	
}
