package com.patientsessiontracker.ui.audithistory;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.patientsessiontracker.core.AuditHistory;

public class AuditHistoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	public static final int DATE_TIME_COL = 0;
	private static final int ACTION_COL = 1;
	private static final int USER_FIRST_NAME_COL = 2;
	private static final int USER_LAST_NAME_COL = 3;
	private static final int AQR_COL = 4;

	private String[] columnNames = { "Date/Time", "Action", "User First Name", "User Last Name", 
			                         "AQR" };
	
	private List<AuditHistory> auditHistoryList;

	public AuditHistoryTableModel(List<AuditHistory> theAuditHistoryList) {
		auditHistoryList = theAuditHistoryList;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return auditHistoryList.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		AuditHistory tempAuditHistory = auditHistoryList.get(row);

		switch (col) {
		case DATE_TIME_COL:			
			return tempAuditHistory.getActionDateTime();
		case ACTION_COL:
			return tempAuditHistory.getAction();
		case USER_FIRST_NAME_COL:
			return tempAuditHistory.getUserFirstName();
		case USER_LAST_NAME_COL:
			return tempAuditHistory.getUserLastName();
		case AQR_COL:
			return tempAuditHistory.getAQR();
		case OBJECT_COL:
			return tempAuditHistory;
		default:
			return tempAuditHistory.getUserLastName();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
