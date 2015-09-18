package com.patientsessiontracker.core;

/**
 * 
 * @author Lamp
 *
 */
public class Patient {

	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String aQR;
	private String aT;
	private String aWT;
	private String cCT;
	private String cMT;
	private String eMS;
	private String gT;
	private String hKM;
	private String iST;
	private String lASER;
	private String nFB;
	private String nRT;
	private String nT;
	private String pMT;
	private String rIFE;
	private String rRT;
	private String sRT;
	private String tRX;
	private String uS;
	private String vIBE;

	public Patient(String firstName, String middleName, String lastName, String aQR, String aT, String aWT, String cCT, String cMT, String eMS, String gT, String hKM, String iST, String lASER,
			       String nFB, String nRT, String nT, String pMT, String rIFE, String rRT, String sRT, String tRX, String uS, String vIBE) {

		this(0, firstName, middleName, lastName, aQR, aT, aWT, cCT, cMT, eMS, gT, hKM, iST, lASER, nFB, nRT, nT, pMT, rIFE, rRT, sRT, tRX, uS, vIBE);
	}
	
	public Patient(int id, String firstName, String middleName, String lastName, String aQR, String aT, String aWT, String cCT, String cMT, String eMS, String gT, String hKM, String iST, String lASER,
			       String nFB, String nRT, String nT, String pMT, String rIFE, String rRT, String sRT, String tRX, String uS, String vIBE) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.aQR = aQR;
		this.aT = aT;
		this.aWT = aWT;
		this.cCT = cCT;
		this.cMT = cMT;
		this.eMS = eMS;
		this.gT = gT;
		this.hKM = hKM;
		this.iST = iST;
		this.lASER = lASER;
		this.nFB = nFB;
		this.nRT = nRT;
		this.nT = nT;
		this.pMT = pMT;
		this.rIFE = rIFE;
		this.rRT = rRT;
		this.sRT = sRT;
		this.tRX = tRX;
		this.uS = uS;
		this.vIBE = vIBE;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAQR() {
		return aQR;
	}
	
	public void setAQR(String aQR) {
		this.aQR = aQR;
	}
	
	public String getAT() {
		return aT;
	}
	
	public void setAT(String aT) {
		this.aT = aT;
	}
	
	public String getAWT() {
		return aWT;
	}
	
	public void setAWT(String aWT) {
		this.aWT = aWT;
	}
	
	public String getCCT() {
		return cCT;
	}
	
	public void setCCT(String cCT) {
		this.cCT = cCT;
	}
	
	public String getCMT() {
		return cMT;
	}
	
	public void setCMT(String cMT) {
		this.cMT = cMT;
	}
	
	public String getEMS() {
		return eMS;
	}
	
	public void setEMS(String eMS) {
		this.eMS = eMS;
	}
	
	public String getGT() {
		return gT;
	}
	
	public void setGT(String gT) {
		this.gT = gT;
	}
	
	public String getHKM() {
		return hKM;
	}
	
	public void setHKM(String hKM) {
		this.hKM = hKM;
	}
	
	public String getIST() {
		return iST;
	}
	
	public void setIST(String iST) {
		this.iST = iST;
	}
	
	public String getLASER() {
		return lASER;
	}
	
	public void setLASER(String lASER) {
		this.lASER = lASER;
	}
	
	public String getNFB() {
		return nFB;
	}
	
	public void setNFB(String nFB) {
		this.nFB = nFB;
	}
	
	public String getNRT() {
		return nRT;
	}
	
	public void setNRT(String nRT) {
		this.nRT = nRT;
	}
	
	public String getNT() {
		return nT;
	}
	
	public void setNT(String nT) {
		this.nT = nT;
	}
	
	public String getPMT() {
		return pMT;
	}
	
	public void setPMT(String pMT) {
		this.pMT = pMT;
	}
	
	public String getRIFE() {
		return rIFE;
	}
	
	public void setRIFE(String rIFE) {
		this.rIFE = rIFE;
	}
	
	public String getRRT() {
		return rRT;
	}
	
	public void setRRT(String rRT) {
		this.rRT = rRT;
	}
	
	public String getSRT() {
		return sRT;
	}
	
	public void setSRT(String sRT) {
		this.sRT = sRT;
	}
	
	public String getTRX() {
		return tRX;
	}
	
	public void setTRX(String tRX) {
		this.tRX = tRX;
	}
	
	public String getUS() {
		return uS;
	}
	
	public void setUS(String uS) {
		this.uS = uS;
	}
	
	public String getVIBE() {
		return vIBE;
	}
	
	public void setVIBE(String vIBE) {
		this.vIBE = vIBE;
	}

	@Override
	public String toString() {
		return String
				.format("Patient [id=%s, firstName=%s, middleName=%s, lastName=%s, aQR=%s, aT=%s, aWT=%s, cCT=%s, cMT=%s, eMS=%s, gT=%s, hKM=%s, iST=%s, lASER=%s, nFB=%s, nRT=%s, nT=%s, pMT=%s, rIFE=%s, rRT=%s, sRT=%s, tRX=%s, uS=%s, vIBE=%s]",
						id, firstName, middleName, lastName, aQR, aT, aWT, cCT, cMT, eMS, gT, hKM, iST, lASER, nFB, nRT, nT, pMT, rIFE, rRT, sRT, tRX, uS, vIBE);
	}		
}
