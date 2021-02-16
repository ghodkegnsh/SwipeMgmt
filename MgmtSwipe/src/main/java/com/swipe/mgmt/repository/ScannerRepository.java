package com.swipe.mgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swipe.mgmt.entity.Scanner;

public interface ScannerRepository extends JpaRepository<Scanner,Integer> {

	Optional<Scanner> findByScannerId(int scannerId);

}
