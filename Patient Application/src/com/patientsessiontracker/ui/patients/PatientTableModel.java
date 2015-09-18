package com.patientsessiontracker.ui.patients;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.patientsessiontracker.core.Patient;

public class PatientTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	public static final int OBJECT_COL = -1;
	private static final int FIRST_NAME_COL = 0;
	private static final int MIDDLE_NAME_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int AQR_COL = 3;
	private static final int AT_COL = 4;
	private static final int AWT_COL = 5;
	private static final int CCT_COL = 6;
	private static final int CMT_COL = 7;
	private static final int EMS_COL = 8;
	private static final int GT_COL = 9;
	private static final int HKM_COL = 10;
	private static final int IST_COL = 11;
	private static final int LASER_COL = 12;
	private static final int NFB_COL = 13;
	private static final int NRT_COL = 14;
	private static final int NT_COL = 15;
	private static final int PMT_COL = 16;
	private static final int RIFE_COL = 17;
	private static final int RRT_COL = 18;
	private static final int SRT_COL = 19;
	private static final int TRX_COL = 20;
	private static final int US_COL = 21;
	private static final int VIBE_COL = 22;

	private String[] columnNames = { "First Name", "Middle Name", "Last Name", "AQR", "AT",
            "AWT", "CCT", "CMT", "EMS", "G T", "HKM", "IST", "LASER", "NFB", "NRT",
            "NT", "PMT", "RIFE", "RRT", "SRT", "TRX", "US", "VIBE" };
	
	private List<Patient> patients;

	public PatientTableModel(List<Patient> thePatients) {
		patients = thePatients;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Patient tempPatient = patients.get(row);

		switch (col) {
		case FIRST_NAME_COL:
			return tempPatient.getFirstName();
		case MIDDLE_NAME_COL:
			return tempPatient.getMiddleName();
		case LAST_NAME_COL:
			return tempPatient.getLastName();
		case AQR_COL:
			return tempPatient.getAQR();
		case AT_COL:
			return tempPatient.getAT();
		case AWT_COL:
			return tempPatient.getAWT();
		case CCT_COL:
			return tempPatient.getCCT();
		case CMT_COL:
			return tempPatient.getCMT();
		case EMS_COL:
			return tempPatient.getEMS();
		case GT_COL:
			return tempPatient.getGT();
		case HKM_COL:
			return tempPatient.getHKM();
		case IST_COL:
			return tempPatient.getIST();
		case LASER_COL:
			return tempPatient.getLASER();
		case NFB_COL:
			return tempPatient.getNFB();
		case NRT_COL:
			return tempPatient.getNRT();
		case NT_COL:
			return tempPatient.getNT();
		case PMT_COL:
			return tempPatient.getPMT();
		case RIFE_COL:
			return tempPatient.getRIFE();
		case RRT_COL:
			return tempPatient.getRRT();
		case SRT_COL:
			return tempPatient.getSRT();
		case TRX_COL:
			return tempPatient.getTRX();
		case US_COL:
			return tempPatient.getUS();
		case VIBE_COL:
			return tempPatient.getVIBE();
		case OBJECT_COL:
			return tempPatient;
		default:
			return tempPatient.getLastName();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
