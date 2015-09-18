package com.patientsessiontracker.core;

import java.util.Date;

public class AuditHistory {

	private int userId;
	private int patientId;
	private String action;
	private Date actionDateTime;
	
	private String aQR;
	
	private String userFirstName;
	private String userLastName;
	
	
	public AuditHistory(int userId, int patientId, String action,
			Date actionDateTime, String aQR, String userFirstName, String userLastName) {
		super();
		this.userId = userId;
		this.patientId = patientId;
		this.action = action;
		this.actionDateTime = actionDateTime;
		this.aQR = aQR;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Date getActionDateTime() {
		return actionDateTime;
	}
	public void setActionDateTime(Date actionDateTime) {
		this.actionDateTime = actionDateTime;
	}
	public String getAQR() {
		return aQR;
	}
	public void setAQR(String aQR) {
		this.aQR = aQR;
	}

	@Override
	public String toString() {
		return String
				.format("AuditHistory [userId=%s, patientId=%s, action=%s, actionDateTime=%s, AQR=%s, userFirstName=%s, userLastName=%s]",
						userId, patientId, action, actionDateTime, aQR,
						userFirstName, userLastName);
	}
}
