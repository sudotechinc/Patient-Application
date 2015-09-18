package com.patientsessiontracker.ui.patients;

import javax.swing.text.AbstractDocument;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.patientsessiontracker.core.Patient;
import com.patientsessiontracker.core.ErrorHandling;
import com.patientsessiontracker.dao.PatientDAO;
import com.patientsessiontracker.ui.PatientApp;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField middleNameTextField;
	private JTextField lastNameTextField;
	
	private JTextField aQRTextField;
	private JTextField aTTextField;
	private JTextField aWTTextField;
	private JTextField cCTTextField;
	private JTextField cMTTextField;
	private JTextField eMSTextField;
	private JTextField gTTextField;
	private JTextField hKMTextField;
	private JTextField iSTTextField;
	private JTextField lASERTextField;
	private JTextField nFBTextField;
	private JTextField nRTTextField;
	private JTextField nTTextField;
	private JTextField pMTTextField;
	private JTextField rIFETextField;
	private JTextField rRTTextField;
	private JTextField sRTTextField;
	private JTextField tRXTextField;
	private JTextField uSTextField;
	private JTextField vIBETextField;

	private PatientDAO patientDAO;

	private PatientApp patientApp;

	private Patient previousPatient = null;
	private boolean updateMode = false;

	private int userId;
	
	public PatientDialog(PatientApp thePatientApp,
			PatientDAO thePatientDAO, Patient thePreviousPatient, boolean theUpdateMode, int theUserId) {
		
		this();
		patientDAO = thePatientDAO;
		patientApp = thePatientApp;

		previousPatient = thePreviousPatient;
		
		updateMode = theUpdateMode;

		userId = theUserId;
		
		if (updateMode) {
			setTitle("Update Patient");
			
			populateGui(previousPatient);
		}
	}

	private void populateGui(Patient thePatient) {

		firstNameTextField.setText(thePatient.getFirstName());
		middleNameTextField.setText(thePatient.getMiddleName());
		lastNameTextField.setText(thePatient.getLastName());
		
		aQRTextField.setText(thePatient.getAQR());
		aTTextField.setText(String.valueOf(thePatient.getAT()));
		aWTTextField.setText(String.valueOf(thePatient.getAWT()));
		cCTTextField.setText(String.valueOf(thePatient.getCCT()));
		cMTTextField.setText(String.valueOf(thePatient.getCMT()));
		eMSTextField.setText(String.valueOf(thePatient.getEMS()));
		gTTextField.setText(String.valueOf(thePatient.getGT()));
		hKMTextField.setText(String.valueOf(thePatient.getHKM()));
		iSTTextField.setText(String.valueOf(thePatient.getIST()));
		lASERTextField.setText(String.valueOf(thePatient.getLASER()));
		nFBTextField.setText(String.valueOf(thePatient.getNFB()));
		nRTTextField.setText(String.valueOf(thePatient.getNRT()));
		nTTextField.setText(String.valueOf(thePatient.getNT()));
		pMTTextField.setText(String.valueOf(thePatient.getPMT()));
		rIFETextField.setText(String.valueOf(thePatient.getRIFE()));
		rRTTextField.setText(String.valueOf(thePatient.getRRT()));
		sRTTextField.setText(String.valueOf(thePatient.getSRT()));
		tRXTextField.setText(String.valueOf(thePatient.getTRX()));
		uSTextField.setText(String.valueOf(thePatient.getUS()));
		vIBETextField.setText(String.valueOf(thePatient.getVIBE()));
	}

	public PatientDialog(PatientApp thePatientApp,
			PatientDAO thePatientDAO, int theUserId) {
		this(thePatientApp, thePatientDAO, null, false, theUserId);
	}

	/**
	 * Create the dialog.
	 */
	public PatientDialog() {
		setTitle("Add Patient");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("<html>First Name<font color='red'>*</font></html>");
			lblFirstName.setBounds(11, 14, 108, 14);
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBounds(114, 11, 315, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblMiddleName = new JLabel("Middle Name");
			lblMiddleName.setBounds(11, 40, 107, 14);
			contentPanel.add(lblMiddleName);
		}
		{
			middleNameTextField = new JTextField();
			middleNameTextField.setBounds(114, 37, 315, 20);
			contentPanel.add(middleNameTextField);
			middleNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("<html>Last Name<font color='red'>*</font></html>");
			lblLastName.setBounds(11, 66, 108, 14);
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBounds(114, 63, 315, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		
		JLabel lblAqr = new JLabel("AQR");
		lblAqr.setBounds(11, 91, 46, 14);
		contentPanel.add(lblAqr);
		
		aQRTextField = new JTextField();
		aQRTextField.setText("0");
		aQRTextField.setBounds(46, 88, 25, 20);
		contentPanel.add(aQRTextField);
		aQRTextField.setColumns(10);
		((AbstractDocument)aQRTextField.getDocument()).setDocumentFilter(new ErrorHandling());
		
		JLabel lblAt = new JLabel("AT");
		lblAt.setBounds(81, 91, 46, 14);
		contentPanel.add(lblAt);
		
		aTTextField = new JTextField();
		aTTextField.setText("0");
		aTTextField.setColumns(10);
		aTTextField.setBounds(114, 88, 25, 20);
		((AbstractDocument)aTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
		contentPanel.add(aTTextField);
		
		JLabel lblAwt = new JLabel("AWT");
		lblAwt.setBounds(149, 91, 46, 14);
		contentPanel.add(lblAwt);
		
		aWTTextField = new JTextField();
		aWTTextField.setText("0");
		aWTTextField.setColumns(10);
		aWTTextField.setBounds(180, 88, 25, 20);
		((AbstractDocument)aWTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
		contentPanel.add(aWTTextField);
		{
			JLabel lblCct = new JLabel("CCT");
			lblCct.setBounds(215, 91, 46, 14);
			contentPanel.add(lblCct);
		}
		{
			cCTTextField = new JTextField();
			cCTTextField.setText("0");
			cCTTextField.setColumns(10);
			cCTTextField.setBounds(257, 88, 25, 20);
			((AbstractDocument)cCTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(cCTTextField);
		}
		{
			JLabel lblCmt = new JLabel("CMT");
			lblCmt.setBounds(311, 91, 46, 14);
			contentPanel.add(lblCmt);
		}
		{
			cMTTextField = new JTextField();
			cMTTextField.setText("0");
			cMTTextField.setColumns(10);
			cMTTextField.setBounds(343, 88, 25, 20);
			((AbstractDocument)cMTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(cMTTextField);
		}
		{
			JLabel lblEms = new JLabel("EMS");
			lblEms.setBounds(378, 91, 46, 14);
			contentPanel.add(lblEms);
		}
		{
			eMSTextField = new JTextField();
			eMSTextField.setText("0");
			eMSTextField.setColumns(10);
			eMSTextField.setBounds(404, 88, 25, 20);
			((AbstractDocument)eMSTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(eMSTextField);
		}
		{
			JLabel lblGT = new JLabel("G T");
			lblGT.setBounds(11, 116, 46, 14);
			contentPanel.add(lblGT);
		}
		{
			gTTextField = new JTextField();
			gTTextField.setText("0");
			gTTextField.setColumns(10);
			gTTextField.setBounds(46, 113, 25, 20);
			((AbstractDocument)gTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(gTTextField);
		}
		{
			JLabel lblHkm = new JLabel("HKM");
			lblHkm.setBounds(81, 116, 46, 14);
			contentPanel.add(lblHkm);
		}
		{
			hKMTextField = new JTextField();
			hKMTextField.setText("0");
			hKMTextField.setColumns(10);
			hKMTextField.setBounds(114, 113, 25, 20);
			((AbstractDocument)hKMTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(hKMTextField);
		}
		{
			JLabel lblIst = new JLabel("IST");
			lblIst.setBounds(149, 116, 46, 14);
			contentPanel.add(lblIst);
		}
		{
			iSTTextField = new JTextField();
			iSTTextField.setText("0");
			iSTTextField.setColumns(10);
			iSTTextField.setBounds(180, 113, 25, 20);
			((AbstractDocument)iSTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(iSTTextField);
		}
		{
			lASERTextField = new JTextField();
			lASERTextField.setText("0");
			lASERTextField.setColumns(10);
			lASERTextField.setBounds(257, 113, 25, 20);
			((AbstractDocument)lASERTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(lASERTextField);
		}
		{
			JLabel lblLaser = new JLabel("LASER");
			lblLaser.setBounds(215, 116, 46, 14);
			contentPanel.add(lblLaser);
		}
		{
			JLabel lblNfb = new JLabel("NFB");
			lblNfb.setBounds(311, 116, 46, 14);
			contentPanel.add(lblNfb);
		}
		{
			nFBTextField = new JTextField();
			nFBTextField.setText("0");
			nFBTextField.setColumns(10);
			nFBTextField.setBounds(343, 113, 25, 20);
			((AbstractDocument)nFBTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(nFBTextField);
		}
		{
			nRTTextField = new JTextField();
			nRTTextField.setText("0");
			nRTTextField.setColumns(10);
			nRTTextField.setBounds(404, 113, 25, 20);
			((AbstractDocument)nRTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(nRTTextField);
		}
		{
			JLabel lblNrt = new JLabel("NRT");
			lblNrt.setBounds(378, 116, 46, 14);
			contentPanel.add(lblNrt);
		}
		{
			JLabel lblNt = new JLabel("NT");
			lblNt.setBounds(11, 141, 46, 14);
			contentPanel.add(lblNt);
		}
		{
			nTTextField = new JTextField();
			nTTextField.setText("0");
			nTTextField.setColumns(10);
			nTTextField.setBounds(46, 138, 25, 20);
			((AbstractDocument)nTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(nTTextField);
		}
		{
			JLabel lblPmt = new JLabel("PMT");
			lblPmt.setBounds(81, 141, 46, 14);
			contentPanel.add(lblPmt);
		}
		{
			pMTTextField = new JTextField();
			pMTTextField.setText("0");
			pMTTextField.setColumns(10);
			pMTTextField.setBounds(114, 138, 25, 20);
			((AbstractDocument)pMTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(pMTTextField);
		}
		{
			rIFETextField = new JTextField();
			rIFETextField.setText("0");
			rIFETextField.setColumns(10);
			rIFETextField.setBounds(180, 138, 25, 20);
			((AbstractDocument)rIFETextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(rIFETextField);
		}
		{
			JLabel lblRife = new JLabel("RIFE");
			lblRife.setBounds(149, 141, 46, 14);
			contentPanel.add(lblRife);
		}
		{
			JLabel lblRrt = new JLabel("RRT");
			lblRrt.setBounds(215, 141, 46, 14);
			contentPanel.add(lblRrt);
		}
		{
			rRTTextField = new JTextField();
			rRTTextField.setText("0");
			rRTTextField.setColumns(10);
			rRTTextField.setBounds(257, 138, 25, 20);
			((AbstractDocument)rRTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(rRTTextField);
		}
		{
			JLabel lblSrt = new JLabel("SRT");
			lblSrt.setBounds(311, 141, 46, 14);
			contentPanel.add(lblSrt);
		}
		{
			sRTTextField = new JTextField();
			sRTTextField.setText("0");
			sRTTextField.setColumns(10);
			sRTTextField.setBounds(343, 138, 25, 20);
			((AbstractDocument)sRTTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(sRTTextField);
		}
		{
			tRXTextField = new JTextField();
			tRXTextField.setText("0");
			tRXTextField.setColumns(10);
			tRXTextField.setBounds(404, 138, 25, 20);
			((AbstractDocument)tRXTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(tRXTextField);
		}
		{
			JLabel lblTrx = new JLabel("TRX");
			lblTrx.setBounds(378, 141, 46, 14);
			contentPanel.add(lblTrx);
		}
		{
			JLabel lblUs = new JLabel("US");
			lblUs.setBounds(11, 166, 46, 14);
			contentPanel.add(lblUs);
		}
		{
			uSTextField = new JTextField();
			uSTextField.setText("0");
			uSTextField.setColumns(10);
			uSTextField.setBounds(46, 163, 25, 20);
			((AbstractDocument)uSTextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(uSTextField);
		}
		{
			JLabel lblVibe = new JLabel("VIBE");
			lblVibe.setBounds(81, 166, 46, 14);
			contentPanel.add(lblVibe);
		}
		{
			vIBETextField = new JTextField();
			vIBETextField.setText("0");
			vIBETextField.setColumns(10);
			vIBETextField.setBounds(114, 163, 25, 20);
			((AbstractDocument)vIBETextField.getDocument()).setDocumentFilter(new ErrorHandling());
			contentPanel.add(vIBETextField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						savePatient();
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

	protected void savePatient() {

		// get the patient info from gui
		String firstName = firstNameTextField.getText();
		String middleName = middleNameTextField.getText();
		String lastName = lastNameTextField.getText();
		
		String aQR = aQRTextField.getText(); 
		String aT = aTTextField.getText();
		String aWT = aWTTextField.getText();
		String cCT = cCTTextField.getText();
		String cMT = cMTTextField.getText();
		String eMS = eMSTextField.getText();
		String gT = gTTextField.getText();
		String hKM = hKMTextField.getText();
		String iST = iSTTextField.getText();
		String lASER = lASERTextField.getText();
		String nFB = nFBTextField.getText();
		String nRT = nRTTextField.getText();
		String nT = nTTextField.getText();
		String pMT = pMTTextField.getText();
		String rIFE = rIFETextField.getText();
		String rRT = rRTTextField.getText();
		String sRT = sRTTextField.getText();
		String tRX = tRXTextField.getText();
		String uS = uSTextField.getText();
		String vIBE = vIBETextField.getText();
		
		Patient tempPatient = null;

		if (updateMode) {
			tempPatient = previousPatient;
			
			tempPatient.setFirstName(firstName);
			tempPatient.setMiddleName(middleName);
			tempPatient.setLastName(lastName);
			
			tempPatient.setAQR(aQR);
			tempPatient.setAT(aT);
			tempPatient.setAWT(aWT);
			tempPatient.setCCT(cCT);
			tempPatient.setCMT(cMT);
			tempPatient.setEMS(eMS);
			tempPatient.setGT(gT);
			tempPatient.setHKM(hKM);
			tempPatient.setIST(iST);
			tempPatient.setLASER(lASER);
			tempPatient.setNFB(nFB);
			tempPatient.setNRT(nRT);
			tempPatient.setNT(nT);
			tempPatient.setPMT(pMT);
			tempPatient.setRIFE(rIFE);
			tempPatient.setRRT(rRT);
			tempPatient.setSRT(sRT);
			tempPatient.setTRX(tRX);
			tempPatient.setUS(uS);
			tempPatient.setVIBE(vIBE);
			
		} else {
			tempPatient = new Patient(firstName, middleName, lastName, aQR, aT, aWT, cCT, cMT, eMS, gT, hKM, iST, lASER, 
					                  nFB, nRT, nT, pMT, rIFE, rRT, sRT, tRX, uS, vIBE);
		}
		
		if ((firstName.trim().length() != 0) && (lastName.trim().length() != 0) && !aQR.isEmpty() && !aT.isEmpty() && !aWT.isEmpty()
			 && !cCT.isEmpty() && !cMT.isEmpty() && !eMS.isEmpty() && !gT.isEmpty() && !hKM.isEmpty() && !iST.isEmpty() && !lASER.isEmpty()
			 && !nFB.isEmpty() && !nRT.isEmpty() && !nT.isEmpty() && !pMT.isEmpty() && !rIFE.isEmpty() && !rRT.isEmpty()
			 && !sRT.isEmpty() && !tRX.isEmpty() && !uS.isEmpty() && !vIBE.isEmpty()) {
			try {
				// save to the database
				if (updateMode) {
					patientDAO.updatePatient(tempPatient, userId);
				} else {
					patientDAO.addPatient(tempPatient, userId);
				}

				// close dialog
				setVisible(false);

				// refresh gui list
				patientApp.refreshPatientsView();

				// show success message
				JOptionPane.showMessageDialog(patientApp,
					"Patient saved succesfully.", "Patient Saved",
					JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(patientApp,
					"Error saving patient: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			}
		} else 
			JOptionPane.showMessageDialog(patientApp, "<html>Please fill in all required fields as denoted by the <font color='red'>*</font>.</html>");
	}
}
