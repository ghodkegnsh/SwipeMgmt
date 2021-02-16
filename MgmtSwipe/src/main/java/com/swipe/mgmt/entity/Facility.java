package com.swipe.mgmt.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Facility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int facilityId;
	@Column
	private String facilityName;
	@Column
	private String facilityAddress;
	@Column
	private String facilityContact;

	@OneToMany(mappedBy = "facility")
	private Set<Swipe> swipe;

	public Facility() {
		super();

	}

	public Facility(int facilityId, String facilityName, String facilityAddress, String facilityContact) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.facilityAddress = facilityAddress;
		this.facilityContact = facilityContact;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	public String getFacilityContact() {
		return facilityContact;
	}

	public void setFacilityContact(String facilityContact) {
		this.facilityContact = facilityContact;
	}

	@Override
	public String toString() {
		return "Facility [facilityId=" + facilityId + ", facilityName=" + facilityName + ", facilityAddress="
				+ facilityAddress + ", facilityContact=" + facilityContact + "]";
	}

}
