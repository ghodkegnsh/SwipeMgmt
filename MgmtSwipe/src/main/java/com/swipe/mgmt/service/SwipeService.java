package com.swipe.mgmt.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swipe.mgmt.entity.Employee;
import com.swipe.mgmt.entity.Facility;
import com.swipe.mgmt.entity.Swipe;
import com.swipe.mgmt.repository.EmployeeRepository;
import com.swipe.mgmt.repository.SwipeRepository;

import com.swipe.mgmt.repository.FacilityRepository;

@Service
public class SwipeService {

	@Autowired
	SwipeRepository swipeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	FacilityRepository facilityRepository;

	private static LocalDateTime instance = LocalDateTime.now();

	public String empSignIn(int empId, int facilityId) {
		Optional<Employee> employee = employeeRepository.findByEmpId(empId);
		Optional<Facility> facility = facilityRepository.findByFacilityId(facilityId);
		List<Swipe> swipeOut = swipeRepository.findByEmpId(empId);
		if (!swipeOut.isEmpty()) {
			for (Swipe swipe : swipeOut) {
				if (swipe.getEmployee() == employee.get() && swipe.getSignOutTime() == null) {
					return "You need to SignOut First from " + swipe.getFacility().getFacilityName();
				}
			}
		}
		if (employee.isPresent() && facility.isPresent()) {
			Swipe newSwipe = new Swipe();
			newSwipe.setEmployee(employee.get());
			newSwipe.setFacility(facility.get());
			newSwipe.setSignInTime(instance);
			swipeRepository.save(newSwipe);
			return "Signed In Successfully!";

		}
		return "Check wheather you have entered correct details.";

	}

	public String empSignOut(int empId, int facilityId) {
		Optional<Facility> facility = facilityRepository.findByFacilityId(facilityId);
		List<Swipe> swipeOut = swipeRepository.findByEmpId(empId);
		for (Swipe swipe : swipeOut) {
			if (swipe.getSignOutTime() == null) {
				LocalDateTime tempDateTime = swipe.getSignInTime();
				long hours = tempDateTime.until(instance, ChronoUnit.HOURS);
				tempDateTime = tempDateTime.plusHours(hours);
				long minutes = tempDateTime.until(instance, ChronoUnit.MINUTES);
				tempDateTime = tempDateTime.plusMinutes(minutes);
				long seconds = tempDateTime.until(instance, ChronoUnit.SECONDS);
				LocalTime retrievedTime = LocalTime.of((int) hours, (int) minutes, (int) seconds);
				swipe.setWorkHr(retrievedTime);
				swipe.setSignOutTime(instance);
				swipeRepository.save(swipe);
				return "Signed Out Successfully!";
			}

		}
		return "You Need to SignIn to " + facility.get().getFacilityName();

	}

}
