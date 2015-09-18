package com.patientsessiontracker.ui.users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.patientsessiontracker.core.User;
import com.patientsessiontracker.dao.UserDAO;
import com.patientsessiontracker.ui.PatientApp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UserDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JCheckBox adminCheckBox;

	private PatientApp patientApp;
	private UserDAO userDAO;	
	private boolean updateMode = false;
	private User previousUser;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;

	/**
	 * Create the dialog.
	 */
	public UserDialog(PatientApp thePatientApp, UserDAO theUserDAO, User theUser) {
		this(thePatientApp, theUserDAO, true);
		previousUser = theUser;
		
			setTitle("Update User");						
			populateGui(previousUser);
			
			// hide password fields
			hidePasswordFields();
	}
	
	/**
	 * Create the dialog.
	 */
	public UserDialog(PatientApp thePatientApp, UserDAO theUserDAO, boolean theUpdateMode) {
		
		this();
		
		patientApp = thePatientApp;
		userDAO = theUserDAO;
		updateMode = theUpdateMode;
	}
	
	/**
	 * Create the dialog.
	 */
	public UserDialog() {
		setTitle("Add User");
		
		setBounds(100, 100, 450, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("First Name");
			lblNewLabel.setBounds(46, 14, 73, 14);
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBounds(129, 11, 300, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Last Name");
			lblNewLabel_1.setBounds(47, 40, 72, 14);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(129, 37, 300, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Email");
			lblNewLabel_2.setBounds(73, 66, 46, 14);
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_2);
		}
		{
			emailTextField = new JTextField();
			emailTextField.setBounds(129, 63, 300, 20);
			contentPanel.add(emailTextField);
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Admin");
			lblNewLabel_3.setBounds(11, 92, 108, 14);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_3);
		}
		{
			adminCheckBox = new JCheckBox("");
			adminCheckBox.setBounds(129, 89, 300, 21);
			contentPanel.add(adminCheckBox);
		}
		{
			passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(51, 119, 68, 14);
			passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(passwordLabel);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(129, 116, 300, 20);
			contentPanel.add(passwordField);
		}
		{
			confirmPasswordLabel = new JLabel("Confirm Password");
			confirmPasswordLabel.setBounds(11, 145, 108, 14);
			confirmPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(confirmPasswordLabel);
		}
		{
			confirmPasswordField = new JPasswordField();
			confirmPasswordField.setBounds(129, 142, 300, 20);
			contentPanel.add(confirmPasswordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveUser();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void populateGui(User theUser) {

		firstNameTextField.setText(theUser.getFirstName());
		lastNameTextField.setText(theUser.getLastName());
		emailTextField.setText(theUser.getEmail());
		adminCheckBox.setSelected(theUser.isAdmin());
	}
	
	private void saveUser() {

		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String email = emailTextField.getText();
		boolean admin = adminCheckBox.isSelected();
				
		User theUser = null;
		
		if (updateMode) {
			theUser = previousUser;
			
			theUser.setLastName(lastName);
			theUser.setFirstName(firstName);
			theUser.setEmail(email);
			theUser.setAdmin(admin);
			
		} else {
			String password = new String(passwordField.getPassword());
			String confirmPassword = new String(confirmPasswordField.getPassword());

			if (!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(this,
						"Passwords do not match.", "Error",
						JOptionPane.ERROR_MESSAGE);				
				return;
			}
			
			theUser = new User(lastName, firstName, email, admin, password);
		}

		try {
			// save to the database
			if (updateMode) {
				userDAO.updateUser(theUser);
				
				// check to see if we need to update GUI fields for current user
				if (patientApp.getCurrentUserId() == theUser.getId()) {
					patientApp.setAdmin(theUser.isAdmin());
				}
			} else {
				userDAO.addUser(theUser);
			}

			// close dialog
			setVisible(false);

			// refresh gui list
			patientApp.refreshUsersView();

			// show success message
			JOptionPane.showMessageDialog(patientApp,
					"User saved succesfully.", "User Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(patientApp,
					"Error saving user: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void hidePasswordFields() {
		
		passwordField.setVisible(false);
		confirmPasswordField.setVisible(false);
	
		passwordLabel.setVisible(false);
		confirmPasswordLabel.setVisible(false);
	}


}
