package com.patientsessiontracker.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.patientsessiontracker.core.AuditHistory;
import com.patientsessiontracker.core.Patient;

/**
 * 
 * @author Lamp Technologies
 *
 */
public class PatientDAO {

	private Connection myConn;
	
	public PatientDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("Patient DAO - DB connection successful to: " + dburl);
	}
	
	@SuppressWarnings("resource")
	public void updatePatient(Patient thePatient, int userId) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update patient"
					+ " set first_name=?, middle_name=?, last_name=?, AQR=?, AT=?, AWT=?, CCT=?, CMT=?, EMS=?, GT=?, HKM=?, IST=?, LASER=?, NFB=?, NRT=?, NT=?, PMT=?, RIFE=?, RRT=?, SRT=?, TRX=?, US=?, VIBE=?"
					+ " where id=?");
			
			// set params
			myStmt.setString(1, thePatient.getFirstName());
			myStmt.setString(2, thePatient.getMiddleName());
			myStmt.setString(3, thePatient.getLastName());
			myStmt.setString(4, thePatient.getAQR());
			myStmt.setString(5, thePatient.getAT());
			myStmt.setString(6, thePatient.getAWT());
			myStmt.setString(7, thePatient.getCCT());
			myStmt.setString(8, thePatient.getCMT());
			myStmt.setString(9, thePatient.getEMS());
			myStmt.setString(10, thePatient.getGT());
			myStmt.setString(11, thePatient.getHKM());
			myStmt.setString(12, thePatient.getIST());
			myStmt.setString(13, thePatient.getLASER());
			myStmt.setString(14, thePatient.getNFB());
			myStmt.setString(15, thePatient.getNRT());
			myStmt.setString(16, thePatient.getNT());
			myStmt.setString(17, thePatient.getPMT());
			myStmt.setString(18, thePatient.getRIFE());
			myStmt.setString(19, thePatient.getRRT());
			myStmt.setString(20, thePatient.getSRT());
			myStmt.setString(21, thePatient.getTRX());
			myStmt.setString(22, thePatient.getUS());
			myStmt.setString(23, thePatient.getVIBE());
			myStmt.setInt(24, thePatient.getId());
			
			// execute SQL
			myStmt.executeUpdate();
			
			/*
			 * Audit History
			 */

			// prepare statement
			myStmt = myConn.prepareStatement("insert into audit_history"
					+ " (user_id, patient_id, action, action_date_time, AQR)"
					+ " values (?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setInt(1, userId);
			myStmt.setInt(2, thePatient.getId());
			myStmt.setString(3, "Updated patient.");			
			myStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			myStmt.setString(5, thePatient.getAQR());

			// execute SQL
			myStmt.executeUpdate();	
			
		} finally {
			DAOUtils.close(myStmt);
		}
		
	}
	
	@SuppressWarnings("resource")
	public void addPatient(Patient thePatient, int userId) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into patient"
					+ " (first_name, middle_name, last_name, AQR, AT, AWT, CCT, CMT, EMS, GT, HKM, IST, LASER, NFB, NRT, NT, PMT, RIFE, RRT, SRT, TRX, US, VIBE)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			// set params
			myStmt.setString(1, thePatient.getFirstName());
			myStmt.setString(2, thePatient.getMiddleName());
			myStmt.setString(3, thePatient.getLastName());
			myStmt.setString(4, thePatient.getAQR());
			myStmt.setString(5, thePatient.getAT());
			myStmt.setString(6, thePatient.getAWT());
			myStmt.setString(7, thePatient.getCCT());
			myStmt.setString(8, thePatient.getCMT());
			myStmt.setString(9, thePatient.getEMS());
			myStmt.setString(10, thePatient.getGT());
			myStmt.setString(11, thePatient.getHKM());
			myStmt.setString(12, thePatient.getIST());
			myStmt.setString(13, thePatient.getLASER());
			myStmt.setString(14, thePatient.getNFB());
			myStmt.setString(15, thePatient.getNRT());
			myStmt.setString(16, thePatient.getNT());
			myStmt.setString(17, thePatient.getPMT());
			myStmt.setString(18, thePatient.getRIFE());
			myStmt.setString(19, thePatient.getRRT());
			myStmt.setString(20, thePatient.getSRT());
			myStmt.setString(21, thePatient.getTRX());
			myStmt.setString(22, thePatient.getUS());
			myStmt.setString(23, thePatient.getVIBE());
			
			// execute SQL
			myStmt.executeUpdate();	
			
			// get the generated patient id
			ResultSet generatedKeys = myStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				thePatient.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Error generating key for patient");
			}
	            
			/*
			 * Audit History
			 */
			
			// prepare statement
			myStmt = myConn.prepareStatement("insert into audit_history"
					+ " (user_id, patient_id, action, action_date_time, AQR)"
					+ " values (?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setInt(1, userId);
			myStmt.setInt(2, thePatient.getId());
			myStmt.setString(3, "Added new patient.");
			myStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			myStmt.setString(5, thePatient.getAQR());
			
			// execute SQL
			myStmt.executeUpdate();	
			
		} finally {
			DAOUtils.close(myStmt);
		}
		
	}
		
	public List<Patient> getAllPatients() throws Exception {
		List<Patient> list = new ArrayList<Patient>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from patient order by last_name");
			
			while (myRs.next()) {
				Patient tempPatient = convertRowToPatient(myRs);
				list.add(tempPatient);
			}

			return list;		
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	public List<Patient> searchPatients(String firstName, String lastName) throws Exception {
		List<Patient> list = new ArrayList<Patient>(); 

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			firstName += "%";
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from patient where first_name like ? and last_name like ? order by last_name");
			
			myStmt.setString(1, firstName);
			myStmt.setString(2, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Patient tempPatient = convertRowToPatient(myRs);
				list.add(tempPatient);
			}
			
			return list;
			
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	private Patient convertRowToPatient(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String firstName = myRs.getString("first_name");
		String middleName = myRs.getString("middle_name");
		String lastName = myRs.getString("last_name");
		
		String aQR =myRs.getString("AQR");
		String aT = myRs.getString("AT");
		String aWT = myRs.getString("AWT");
		String cCT = myRs.getString("CCT");
		String cMT = myRs.getString("CMT");
		String eMS = myRs.getString("EMS");
		String gT = myRs.getString("GT");
		String hKM = myRs.getString("HKM");
		String iST = myRs.getString("IST");
		String lASER = myRs.getString("LASER");
		String nFB = myRs.getString("NFB");
		String nRT = myRs.getString("NRT");
		String nT = myRs.getString("NT");
		String pMT = myRs.getString("PMT");
		String rIFE = myRs.getString("RIFE");
		String rRT = myRs.getString("RRT");
		String sRT = myRs.getString("SRT");
		String tRX = myRs.getString("TRX");
		String uS = myRs.getString("US");
		String vIBE = myRs.getString("VIBE");
		
		Patient tempPatient = new Patient(id, firstName, middleName, lastName, aQR, aT, aWT, cCT, cMT, eMS, gT, hKM, iST, lASER,
				                          nFB, nRT, nT, pMT, rIFE, rRT, sRT, tRX, uS, vIBE);
		
		return tempPatient;
	}


	public List<AuditHistory> getAuditHistory(int patientId) throws Exception {
		List<AuditHistory> list = new ArrayList<AuditHistory>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			
			String sql = "SELECT history.user_id, history.patient_id, history.action, history.action_date_time, history.AQR, "
					+ "users.first_name, users.last_name  "
					+ "FROM audit_history history, users users "
					+ "WHERE history.user_id=users.id AND history.patient_id=" + patientId;
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				
				int userId = myRs.getInt("history.user_id");
				String action = myRs.getString("history.action");
				
				Timestamp timestamp = myRs.getTimestamp("history.action_date_time");
				java.util.Date actionDateTime = new java.util.Date(timestamp.getTime());
				
				String aQR = myRs.getString("history.AQR");
				
				String userFirstName = myRs.getString("users.first_name");
				String userLastName = myRs.getString("users.last_name");
				
				AuditHistory temp = new AuditHistory(userId, patientId, action, actionDateTime, aQR, userFirstName, userLastName);
				
				list.add(temp);
			}

			return list;
			
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		PatientDAO dao = new PatientDAO();

		System.out.println(dao.getAllPatients());
		
		System.out.println(dao.getAuditHistory(4));
	}

}
