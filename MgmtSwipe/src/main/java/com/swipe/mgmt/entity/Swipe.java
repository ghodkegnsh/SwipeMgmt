package com.swipe.mgmt.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Swipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int swipeId;
	@Column
	private LocalDateTime signInTime;
	@Column
	private LocalDateTime signOutTime;
	@Column
	private LocalTime workHr;

	@ManyToOne
	@JoinColumn(name = "empId", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "scannerId", nullable = false)
	private Scanner scanner;

	@ManyToOne
	@JoinColumn(name = "facilityId", nullable = false)
	private Facility facility;

	public Swipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Swipe(int swipeId, LocalDateTime signInTime, LocalDateTime signOutTime,LocalTime workHr,
			Employee employee, Facility facility, Scanner scanner) {
		super();
		this.swipeId = swipeId;
		this.signInTime = signInTime;
		this.signOutTime = signOutTime;
		this.workHr=workHr;
		this.employee = employee;
		this.facility = facility;
		this.scanner=scanner;
	}

	public LocalTime getWorkHr() {
		return workHr;
	}

	public void setWorkHr(LocalTime workHr) {
		this.workHr = workHr;
	}

	public int getSwipeId() {
		return swipeId;
	}

	public void setSwipeId(int swipeId) {
		this.swipeId = swipeId;
	}

	public LocalDateTime getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(LocalDateTime signInTime) {
		this.signInTime = signInTime;
	}

	public LocalDateTime getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(LocalDateTime signOutTime) {
		this.signOutTime = signOutTime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Override
	public String toString() {
		return "Swipe [swipeId=" + swipeId + ", scanner=" + scanner + ", signInTime=" + signInTime
				+ ", signOutTime=" + signOutTime + ", employee=" + employee + ", facility=" + facility + "]";
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	

}
