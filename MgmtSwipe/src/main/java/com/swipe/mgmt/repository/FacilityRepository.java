package com.swipe.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipe.mgmt.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility,Integer> {

	Optional<Facility> findByFacilityId(int facilityId);

}
