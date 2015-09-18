package com.patientsessiontracker.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.patientsessiontracker.core.AuditHistory;
import com.patientsessiontracker.core.Patient;
import com.patientsessiontracker.core.User;
import com.patientsessiontracker.dao.PatientDAO;
import com.patientsessiontracker.dao.UserDAO;
import com.patientsessiontracker.ui.audithistory.AuditHistoryDialog;
import com.patientsessiontracker.ui.patients.PatientDialog;
import com.patientsessiontracker.ui.patients.PatientTableModel;
import com.patientsessiontracker.ui.users.ChangePasswordDialog;
import com.patientsessiontracker.ui.users.UserDialog;
import com.patientsessiontracker.ui.users.UserLoginDialog;
import com.patientsessiontracker.ui.users.UserTableModel;

public class PatientApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchLastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable patientTable;

	private UserDAO userDAO;
	private PatientDAO patientDAO;
	private JPanel patientsButtonPanel;
	private JButton btnAddPatient;
	private JButton btnUpdatePatient;
	private JButton viewHistoryButton;
	
	private int userId;
	private JPanel topPanel;
	private JPanel searchPanel;
	private JLabel lblLoggedIn;
	private JLabel loggedInUserLabel;
	private JPanel patientPanel;
	private JTabbedPane tabbedPane;
	private JPanel usersPanel;
	private JScrollPane userScrollPane;
	private JTable userTable;
	private JPanel panel;
	private JButton addUserButton;
	private JButton updateUser;
	private JButton changePasswordButton;
	private JPanel tablePanel;
	
	private boolean admin;
	private JLabel lblSearchFirstName;
	private JTextField searchFirstNameTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					PatientDAO patientDAO = new PatientDAO();
					UserDAO userDAO = new UserDAO();
					
					// Get users
					List<User> users = userDAO.getUsers(true, 0);

					// Show login dialog
					UserLoginDialog dialog = new UserLoginDialog();
					dialog.populateUsers(users);
					dialog.setPatientDAO(patientDAO);
					dialog.setUserDAO(userDAO);
					
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientApp(int theUserId, boolean theAdmin, PatientDAO thePatientDAO, UserDAO theUserDAO) {
		
		userId = theUserId;
		admin = theAdmin;
		
		patientDAO = thePatientDAO;
		userDAO = theUserDAO;
		
		setTitle("Patient App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		patientPanel = new JPanel();
		tabbedPane.addTab("Patients", null, patientPanel, null);
		patientPanel.setLayout(new BorderLayout(0, 0));
		
		tablePanel = new JPanel();
		patientPanel.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		patientTable = new JTable();
		tablePanel.add(patientTable.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(patientTable, BorderLayout.CENTER);
		
		searchPanel = new JPanel();
		patientPanel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setBorder(null);
		FlowLayout flowLayout_1 = (FlowLayout) searchPanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		lblSearchFirstName = new JLabel("First Name");
		searchPanel.add(lblSearchFirstName);
		
		searchFirstNameTextField = new JTextField();
		searchPanel.add(searchFirstNameTextField);
		searchFirstNameTextField.setColumns(20);
		
		JLabel lblSearchLastName = new JLabel("Last Name");
		searchPanel.add(lblSearchLastName);
		
		searchLastNameTextField = new JTextField();
		searchPanel.add(searchLastNameTextField);
		searchLastNameTextField.setColumns(20);
		
		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get patients for the last name

				// If last name is empty, then get all patients

				// Print out patients				
				
				try {
					String firstName = searchFirstNameTextField.getText();
					String lastName = searchLastNameTextField.getText();

					List<Patient> patients = null;

					if (((firstName != null) && (firstName.trim().length() > 0)) ||
							((lastName != null) && (lastName.trim().length() > 0))) {
						patients = patientDAO.searchPatients(firstName, lastName);
					} else {
						patients = patientDAO.getAllPatients();
					}
					
					// create the model and update the "table"
					PatientTableModel model = new PatientTableModel(patients);
					
					patientTable.setModel(model);
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(PatientApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		setScrollPane(new JScrollPane());
		
		patientsButtonPanel = new JPanel();
		patientPanel.add(patientsButtonPanel, BorderLayout.SOUTH);
		
		btnAddPatient = new JButton("Add Patient");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				PatientDialog dialog = new PatientDialog(PatientApp.this, patientDAO, userId);

				// show dialog
				dialog.setVisible(true);
			}
		});
		patientsButtonPanel.add(btnAddPatient);
		
		btnUpdatePatient = new JButton("Update Patient");
		btnUpdatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get the selected item
				int row = patientTable.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(PatientApp.this, "You must select a patient!", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current patient
				Patient tempPatient = (Patient) patientTable.getValueAt(row, PatientTableModel.OBJECT_COL);
				
				// create dialog
				PatientDialog dialog = new PatientDialog(PatientApp.this, patientDAO, 
															tempPatient, true, userId);

				// show dialog
				dialog.setVisible(true);
			
			}
		});
		patientsButtonPanel.add(btnUpdatePatient);
		
		viewHistoryButton = new JButton("View History");
		viewHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// get the selected item
				int row = patientTable.getSelectedRow();
				
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(PatientApp.this, "You must select a patient!", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;
				}
				
				// get the current patient
				Patient tempPatient = (Patient) patientTable.getValueAt(row, PatientTableModel.OBJECT_COL);

				try {
					// get audit history for this employee
					int patientId = tempPatient.getId();
					List<AuditHistory> auditHistoryList = patientDAO.getAuditHistory(patientId);

					// show audit history dialog
					AuditHistoryDialog dialog = new AuditHistoryDialog();
					dialog.populate(tempPatient, auditHistoryList);
					
					dialog.setVisible(true);
				}
				catch (Exception exc) {
					exc.printStackTrace();
					JOptionPane.showMessageDialog(PatientApp.this, "Error retrieving audit history", "Error",
							JOptionPane.ERROR_MESSAGE);				
					return;					
				}
				
			}
		});
		patientsButtonPanel.add(viewHistoryButton);
		
		usersPanel = new JPanel();
		tabbedPane.addTab("Users", null, usersPanel, null);
		usersPanel.setLayout(new BorderLayout(0, 0));
		
		userScrollPane = new JScrollPane();
		usersPanel.add(userScrollPane, BorderLayout.CENTER);
		
		userTable = new JTable();
		usersPanel.add(userTable.getTableHeader(), BorderLayout.NORTH);
		usersPanel.add(userTable, BorderLayout.CENTER);
		
		panel = new JPanel();
		usersPanel.add(panel, BorderLayout.SOUTH);
		
		addUserButton = new JButton("Add User");
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});
		panel.add(addUserButton);
		
		updateUser = new JButton("Update User");
		updateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		panel.add(updateUser);
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword();
			}
		});
		panel.add(changePasswordButton);
		
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		FlowLayout flowLayout_2 = (FlowLayout) topPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		lblLoggedIn = new JLabel("Logged In:");
		topPanel.add(lblLoggedIn);
		
		loggedInUserLabel = new JLabel("New label");
		topPanel.add(loggedInUserLabel);

		addUserButton.setEnabled(admin);
		
		refreshUsersView();
	}

	public void refreshUsersView() {

		try {
			List<User> users = userDAO.getUsers(admin, userId);

			// create the model and update the "table"
			UserTableModel model = new UserTableModel(users);

			userTable.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void refreshPatientsView() {

		try {
			List<Patient> patients = patientDAO.getAllPatients();

			// create the model and update the "table"
			PatientTableModel model = new PatientTableModel(patients);

			patientTable.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void setLoggedInUserName(String firstName, String lastName) {
		loggedInUserLabel.setText(firstName + " " + lastName);
	}
	
	private void addUser() {
		UserDialog userDialog = new UserDialog(PatientApp.this, userDAO, false);
		
		userDialog.setVisible(true);
	}
	
	protected void updateUser() {

		// get the selected item
		int row = userTable.getSelectedRow();
		
		// make sure a row is selected
		if (row < 0) {
			JOptionPane.showMessageDialog(PatientApp.this, "You must select a user", "Error",
					JOptionPane.ERROR_MESSAGE);				
			return;
		}
		
		// get the current employee
		User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
		
		// create dialog
		UserDialog dialog = new UserDialog(PatientApp.this, userDAO, tempUser);

		// show dialog
		dialog.setVisible(true);		
	}
	
	protected void changePassword() {
		// get the selected item
		int row = userTable.getSelectedRow();
		
		// make sure a row is selected
		if (row < 0) {
			JOptionPane.showMessageDialog(PatientApp.this, "You must select a user", "Error",
					JOptionPane.ERROR_MESSAGE);				
			return;
		}
		
		// get the current patient
		User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);

		// create dialog
		ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog(tempUser, userDAO);
		
		// show dialog
		changePasswordDialog.setVisible(true);
	}
	
	public int getCurrentUserId() {
		return userId;
	}
	
	public void setAdmin(boolean theFlag) {
		admin = theFlag;
		addUserButton.setEnabled(theFlag);		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
}
