package com.jason.system.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: Role 
* @Description: 查询role表，将结果放在
* @author: Jason
* @date: 2016年7月12日 下午3:58:24 
*
 */
public class Role implements Serializable{
	
	private String roleId;
	
	private String roleName;
	
	private String roleDesc;

	public Role(String roleId, String roleName, String roleDesc) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + "]";
	}
	
}
