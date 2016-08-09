package com.jason.system.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: UserList 
* @Description: 系统设置中用户管理页面中显示的内容
* @author: Jason
* @date: 2016年7月12日 下午2:22:13 
*
 */
public class User implements Serializable{
	
	private String userId;
	
	private String userName;
	
	private String userStartTime;
	
	private String userRoleName;

	public User(String userId, String userName, String userStartTime,
			String userRoleName) {
		this.userId = userId;
		this.userName = userName;
		this.userStartTime = userStartTime;
		this.userRoleName = userRoleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStartTime() {
		return userStartTime;
	}

	public void setUserStartTime(String userStartTime) {
		this.userStartTime = userStartTime;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userStartTime=" + userStartTime + ", userRoleName="
				+ userRoleName + "]";
	}
	
}
