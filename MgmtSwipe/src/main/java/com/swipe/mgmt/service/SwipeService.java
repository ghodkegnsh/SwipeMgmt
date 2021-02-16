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
import com.swipe.mgmt.entity.Scanner;
import com.swipe.mgmt.entity.Swipe;
import com.swipe.mgmt.repository.EmployeeRepository;
import com.swipe.mgmt.repository.ScannerRepository;
import com.swipe.mgmt.repository.SwipeRepository;

import com.swipe.mgmt.repository.FacilityRepository;

@Service
public class SwipeService {

	@Autowired
	SwipeRepository swipeRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ScannerRepository scannerRepository;

	@Autowired
	FacilityRepository facilityRepository;

	LocalDateTime instance = LocalDateTime.now();

	public String empSigning(int empId, int facilityId, int scannerId) {
		Optional<Employee> employee = employeeRepository.findByEmpId(empId);
		System.out.println(employee);
		Optional<Scanner> scanner = scannerRepository.findByScannerId(scannerId);
		Optional<Facility> facility = facilityRepository.findByFacilityId(facilityId);

		if (employee.isPresent() && scanner.isPresent()&& facility.isPresent()) {
			if (scanner.get().getScannerType().equals("IN")) {
				Swipe newSwipe = new Swipe();
				newSwipe.setEmployee(employee.get());
				newSwipe.setFacility(facility.get());
				newSwipe.setScanner(scanner.get());
				newSwipe.setSignInTime(instance);
				swipeRepository.save(newSwipe);
				return "Signed In Successfully!";
			} else if (scanner.get().getScannerType().equals("OUT")) {
				List<Swipe> swipeOut = swipeRepository.findByEmpIdAndFacilityId(empId, facilityId);
				for (Swipe swipe : swipeOut) {
					if (swipe.getSignOutTime() == null) {

						if (swipe.getScanner().getScannerType().equals("IN")) {

							LocalDateTime tempDateTime = swipe.getSignInTime();
							long hours = tempDateTime.until(instance, ChronoUnit.HOURS);
							tempDateTime = tempDateTime.plusHours(hours);
							long minutes = tempDateTime.until(instance, ChronoUnit.MINUTES);
							tempDateTime = tempDateTime.plusMinutes(minutes);
							long seconds = tempDateTime.until(instance, ChronoUnit.SECONDS);
							LocalTime retrievedTime = LocalTime.of((int) hours, (int) minutes, (int) seconds);
							swipe.setWorkHr(retrievedTime);
							swipe.setScanner(scanner.get());
							swipe.setSignOutTime(instance);
							swipeRepository.save(swipe);
							return "Signed Out Successfully!";
						}
						return "Contact admin :-" + facility.get().getFacilityContact();

					}
				}
			}
			return "Check wheather you have entered correct details.";

		}
		return "Check wheather you have entered correct details.";
	}

}
