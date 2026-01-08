package com.ra.hn_jpn_jv250619_tranthilinh.service;

import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.RequestBuildingDTO;
import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.ResponseBuildingDTO;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import com.ra.hn_jpn_jv250619_tranthilinh.repository.BuildingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface BuildingService {
    Page<ResponseBuildingDTO>getAllBuildings(Pageable pageable);
    ResponseBuildingDTO createBuilding(RequestBuildingDTO request, MultipartFile file);
    ResponseBuildingDTO updateBuilding(int id,RequestBuildingDTO request, MultipartFile file);
    Page<ResponseBuildingDTO>searchByBuildingName(String name, Pageable pageable);
    Page<ResponseBuildingDTO>searchByStatus(Status status, Pageable pageable);
}
