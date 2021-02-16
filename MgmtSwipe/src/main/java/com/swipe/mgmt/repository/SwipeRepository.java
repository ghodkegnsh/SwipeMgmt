package com.swipe.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swipe.mgmt.entity.Swipe;

public interface SwipeRepository extends JpaRepository<Swipe, Integer> {

	@Query(value="Select swipe_id,sign_in_time,sign_out_time,scanner_id from swipe where emp_id=empId AND facility_id=facilityId",nativeQuery = true)
	Swipe findByEmpIdAndFacilityId(int empId, int facilityId);

}
