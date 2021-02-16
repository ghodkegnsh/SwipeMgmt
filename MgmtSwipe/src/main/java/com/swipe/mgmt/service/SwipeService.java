package com.swipe.mgmt.service;

import java.time.LocalDateTime;
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
				Swipe swipeOut=swipeRepository.findByEmpIdAndFacilityId(empId,facilityId);
				if(swipeOut!=null && swipeOut.getScanner().getScannerType()=="IN") {
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
