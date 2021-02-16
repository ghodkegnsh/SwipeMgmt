package com.swipe.mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swipe.mgmt.service.SwipeService;
@RestController
@RequestMapping("/swipe")
public class SwipeController {
	
	@Autowired
	SwipeService swipeService;
	
	@PostMapping("/signin")
	public String empSignIn(@RequestParam int empId,@RequestParam int facilityId,@RequestParam int scannerId) {
		return swipeService.empSigning(empId,facilityId,scannerId);
		
	}
	
	@PostMapping("/signout")
	public String empSignOut(@RequestParam int empId,@RequestParam int facilityId,@RequestParam int scannerId) {
		return swipeService.empSigning(empId,facilityId,scannerId);
		
	}
	
	/*
	 * Services
	 * 
	 * Swipe Service
	 * 
	 * Sign In Method:
	 * 
	 * Input: Employee ID, Facility ID
	 * 
	 * Output: String[Message]
	 * 
	 * Sign Out:
	 * 
	 * Input: Employee ID, Facility ID
	 * 
	 * Output: String[Message]
	 * 
	 * Report Service
	 * 
	 * Input: From Date, To Date
	 * 
	 * Output: List of Records
	 * 
	 * Note: If u
	 */
}
