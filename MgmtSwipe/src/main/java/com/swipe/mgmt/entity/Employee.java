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
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int empId;
	@Column
	private String empName;
	@Column
	private String empDesignation;
	@Column
	private long empContact;

	@OneToMany(mappedBy = "employee")
	private Set<Swipe> swipe;

	public Employee() {
		super();

	}

	public Employee(int empId, String empName, String empDesignation, long empContact) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empDesignation = empDesignation;
		this.empContact = empContact;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public long getEmpContact() {
		return empContact;
	}

	public void setEmpContact(long empContact) {
		this.empContact = empContact;
	}

}
