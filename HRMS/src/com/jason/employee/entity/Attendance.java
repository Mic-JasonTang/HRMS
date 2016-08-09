package com.jason.employee.entity;

import java.io.Serializable;

public class Attendance implements Serializable{
	
	private String attendanceId;
	
    private String attendanceEmployeeId;
    
    private String attendanceEmployeeName;
    
    private String attendanceEmployeeBranchName;
    
    private String attendanceStart;
    
    private String attendanceEnd;
    
    private String attendanceStatus;
    
    private String attendanceDesc;

	public Attendance(String attendanceId, String attendanceEmployeeId,
			String attendanceEmployeeName, String attendanceEmployeeBranchName,
			String attendanceStart, String attendanceEnd,
			String attendanceStatus, String attendanceDesc) {
		super();
		this.attendanceId = attendanceId;
		this.attendanceEmployeeId = attendanceEmployeeId;
		this.attendanceEmployeeName = attendanceEmployeeName;
		this.attendanceEmployeeBranchName = attendanceEmployeeBranchName;
		this.attendanceStart = attendanceStart;
		this.attendanceEnd = attendanceEnd;
		this.attendanceStatus = attendanceStatus;
		this.attendanceDesc = attendanceDesc;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getAttendanceEmployeeId() {
		return attendanceEmployeeId;
	}

	public void setAttendanceEmployeeId(String attendanceEmployeeId) {
		this.attendanceEmployeeId = attendanceEmployeeId;
	}

	public String getAttendanceEmployeeName() {
		return attendanceEmployeeName;
	}

	public void setAttendanceEmployeeName(String attendanceEmployeeName) {
		this.attendanceEmployeeName = attendanceEmployeeName;
	}

	public String getAttendanceEmployeeBranchName() {
		return attendanceEmployeeBranchName;
	}

	public void setAttendanceEmployeeBranchName(String attendanceEmployeeBranchName) {
		this.attendanceEmployeeBranchName = attendanceEmployeeBranchName;
	}

	public String getAttendanceStart() {
		return attendanceStart;
	}

	public void setAttendanceStart(String attendanceStart) {
		this.attendanceStart = attendanceStart;
	}

	public String getAttendanceEnd() {
		return attendanceEnd;
	}

	public void setAttendanceEnd(String attendanceEnd) {
		this.attendanceEnd = attendanceEnd;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getAttendanceDesc() {
		return attendanceDesc;
	}

	public void setAttendanceDesc(String attendanceDesc) {
		this.attendanceDesc = attendanceDesc;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId
				+ ", attendanceEmployeeId=" + attendanceEmployeeId
				+ ", attendanceEmployeeName=" + attendanceEmployeeName
				+ ", attendanceEmployeeBranchName="
				+ attendanceEmployeeBranchName + ", attendanceStart="
				+ attendanceStart + ", attendanceEnd=" + attendanceEnd
				+ ", attendanceStatus=" + attendanceStatus
				+ ", attendanceDesc=" + attendanceDesc + "]";
	}

	
}
