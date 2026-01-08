package com.ra.hn_jpn_jv250619_tranthilinh.repository;

import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Building;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

    boolean existsByBuildingName(String name);
    Page<Building> findByBuildingNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Building> findByStatus(Status status, Pageable pageable);
}
