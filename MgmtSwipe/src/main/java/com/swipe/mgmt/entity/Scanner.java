package com.swipe.mgmt.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Scanner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int scannerId;
	@Column
	private String scannerType; 
	
	@OneToMany(mappedBy = "scanner")
	private Set<Swipe> swipe;

	public Scanner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Scanner(int scannerId, String scannerType, Set<Swipe> swipe) {
		super();
		this.scannerId = scannerId;
		this.scannerType = scannerType;
		this.swipe = swipe;
	}

	public int getScannerId() {
		return scannerId;
	}

	public void setScannerId(int scannerId) {
		this.scannerId = scannerId;
	}

	public String getScannerType() {
		return scannerType;
	}

	public void setScannerType(String scannerType) {
		this.scannerType = scannerType;
	}

	public Set<Swipe> getSwipe() {
		return swipe;
	}

	public void setSwipe(Set<Swipe> swipe) {
		this.swipe = swipe;
	}

	@Override
	public String toString() {
		return "Scanner [scannerId=" + scannerId + ", scannerType=" + scannerType + ", swipe=" + swipe + "]";
	}
	
	
}
