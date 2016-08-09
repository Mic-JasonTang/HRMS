package com.jason.system.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: Log 
* @Description: 系统日志的JavaBean
* @author: Jason
* @date: 2016年7月12日 下午5:11:50 
*
 */
public class Log implements Serializable{
	
	private String logId;
	
	private String logUserId;
	
	private String logUserName;
	
	private String logType;
	
	private String logContent;
	
	private String logIp;
	
	private String logTime;

	public Log(String logId, String logUserId, String logUserName,
			String logType, String logContent, String logIp, String logTime) {
		super();
		this.logId = logId;
		this.logUserId = logUserId;
		this.logUserName = logUserName;
		this.logType = logType;
		this.logContent = logContent;
		this.logIp = logIp;
		this.logTime = logTime;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogUserName() {
		return logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	@Override
	public String toString() {
		return "Log [logId=" + logId + ", logUserId=" + logUserId
				+ ", logUserName=" + logUserName + ", logType=" + logType
				+ ", logContent=" + logContent + ", logIp=" + logIp
				+ ", logTime=" + logTime + "]";
	}

	
}
