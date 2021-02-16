package com.swipe.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swipe.mgmt.entity.Swipe;



public interface SwipeRepository extends JpaRepository<Swipe, Integer> {

	@Query(value="Select * from swipe where emp_id=:empId AND facility_id=:facilityId",nativeQuery = true)
	List<Swipe> findByEmpIdAndFacilityId(int empId, int facilityId);

}
