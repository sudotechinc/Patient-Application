package com.patientsessiontracker.ui.users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.patientsessiontracker.core.User;
import com.patientsessiontracker.dao.PatientDAO;
import com.patientsessiontracker.dao.UserDAO;
import com.patientsessiontracker.ui.PatientApp;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class UserLoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JComboBox<?> userComboBox;

	private PatientDAO patientDAO;
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO theUserDAO) {
		userDAO = theUserDAO;
	}
	
	public void setPatientDAO(PatientDAO thePatientDAO) {
		patientDAO = thePatientDAO;
	}

	public void populateUsers(List<User> users) {
		userComboBox.setModel(new DefaultComboBoxModel(users.toArray(new User[0])));
	}

	/**
	 * Create the dialog.
	 */
	public UserLoginDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("User Login");
		setBounds(100, 100, 450, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUser = new JLabel("User");
			lblUser.setBounds(35, 14, 60, 14);
			contentPanel.add(lblUser);
		}
		{
			userComboBox = new JComboBox();
			userComboBox.setBounds(105, 11, 324, 20);
			contentPanel.add(userComboBox);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(11, 40, 60, 14);
			contentPanel.add(lblPassword);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(105, 37, 324, 20);
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						performUserLogin();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public JComboBox<?> getUserComboBox() {
		return userComboBox;
	}

	private void performUserLogin() {
		
		try {
			// get the user id
			if (userComboBox.getSelectedIndex() == -1) {						
				JOptionPane.showMessageDialog(UserLoginDialog.this, "You must select a user.", "Error", JOptionPane.ERROR_MESSAGE);				
				return;
			}

			User theUser = (User) userComboBox.getSelectedItem();
			int userId = theUser.getId();
			boolean admin = theUser.isAdmin();
			
			// get the password
			String plainTextPassword = new String(passwordField.getPassword());
			theUser.setPassword(plainTextPassword);

			// check the user's password against the encrypted version in the database
			boolean isValidPassword = userDAO.authenticate(theUser);

			if (isValidPassword) {
				// hide the login window
				setVisible(false);

				// now show the main app window
				PatientApp frame = new PatientApp(userId, admin, patientDAO, userDAO);
				frame.setLoggedInUserName(theUser.getFirstName(), theUser.getLastName());

				frame.setVisible(true);
			}
			else {
				// show error message
				JOptionPane.showMessageDialog(this, "Invalid login", "Invalid Login",
						JOptionPane.ERROR_MESSAGE);

				return;			
			}
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error during login", "Error",
					JOptionPane.ERROR_MESSAGE);			
		}
	}
}
