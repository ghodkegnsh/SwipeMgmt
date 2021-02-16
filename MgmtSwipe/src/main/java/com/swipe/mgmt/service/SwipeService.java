package com.swipe.mgmt.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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
	
	LocalDateTime instance=LocalDateTime.now();
	
	public String empSigning(int empId, int facilityId, int scannerId) {
		Optional<Employee> employee=employeeRepository.findByEmpId(empId);
		System.out.println(employee);
		Optional<Scanner> scanner=scannerRepository.findByScannerId(scannerId);
		Optional<Facility> facility=facilityRepository.findByFacilityId(facilityId);
		
		if(employee.isPresent() && scanner.isPresent()) {
			System.out.println(employee.isPresent() && scanner.isPresent());
			
			System.out.println(scanner.get().getScannerType().equals("IN"));
			if(scanner.get().getScannerType().equals("IN")){
				
				Swipe newSwipe=new Swipe();
				newSwipe.setEmployee(employee.get());
				newSwipe.setFacility(facility.get());
				newSwipe.setScanner(scanner.get());
				newSwipe.setSignInTime(instance);
				swipeRepository.save(newSwipe);
				return "Signed In Successfully!";
			}else if(scanner.get().getScannerType().equals("OUT")){
				LocalDateTime signOutTime = null;
				Swipe swipeOut=swipeRepository.findByEmpIdAndFacilityIdAndScannerId(empId,facilityId,scannerId);
				System.out.println(swipeOut.getSwipeId()); 
				if(swipeOut!=null && swipeOut.getScanner().getScannerType().equals("IN")) {
					
					LocalDateTime tempDateTime=swipeOut.getSignInTime();
					long hours = tempDateTime.until( instance, ChronoUnit.HOURS );
					long minutes = tempDateTime.until( instance, ChronoUnit.MINUTES );
					long seconds = tempDateTime.until( instance, ChronoUnit.SECONDS );
					LocalTime retrievedTime =LocalTime.of((int)hours, (int)minutes, (int)seconds);
					swipeOut.setWorkHr(retrievedTime);
					swipeOut.setScanner(scanner.get());
					swipeOut.setSignOutTime(instance);
					swipeRepository.save(swipeOut);
					return "Signed Out Successfully!";
				}
				return "Contact admin :-"+facility.get().getFacilityContact();
			}
			return "Check wheather you have entered correct details.";
			
		}
		return "Check wheather you have entered correct details.";
	}

}
