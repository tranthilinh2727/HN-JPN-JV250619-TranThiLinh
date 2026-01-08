package com.ra.hn_jpn_jv250619_tranthilinh.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.RequestBuildingDTO;
import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.ResponseBuildingDTO;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Building;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import com.ra.hn_jpn_jv250619_tranthilinh.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private  Cloudinary cloudinary;
    @Override
    public Page<ResponseBuildingDTO> getAllBuildings(Pageable pageable) {
        return buildingRepository.findAll(pageable)
                .map(building -> ResponseBuildingDTO.builder()
                        .id(building.getId())
                        .buildingName(building.getBuildingName())
                        .buildingArea(building.getBuildingArea())
                        .areaUnit(building.getAreaUnit())
                        .startDate(building.getStartDate())
                        .time(building.getTime())
                        .timeUnit(building.getTimeUnit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build());
    }

    @Override
    public ResponseBuildingDTO createBuilding(RequestBuildingDTO request, MultipartFile file) {
      if (buildingRepository.existsByBuildingName(request.getBuildingName())) {
          throw new RuntimeException("Building đã tồn tại");
      }
        Building building=new Building();
      building.setBuildingName(request.getBuildingName());
      building.setBuildingArea(request.getBuildingArea());
      building.setAreaUnit(request.getAreaUnit());
      building.setStartDate(request.getStartDate());
      building.setTime(request.getTime());
      building.setTimeUnit(request.getTimeUnit());
      String url=uploadImage(file);
      building.setDesign(url);
      building.setContent(request.getContent());
      building.setStatus(request.getStatus());

      Building savedBuilding = buildingRepository.save(building);
        return ResponseBuildingDTO.builder()
                .id(savedBuilding.getId())
                .buildingName(savedBuilding.getBuildingName())
                .buildingArea(savedBuilding.getBuildingArea())
                .areaUnit(savedBuilding.getAreaUnit())
                .startDate(savedBuilding.getStartDate())
                .time(savedBuilding.getTime())
                .timeUnit(savedBuilding.getTimeUnit())
                .design(savedBuilding.getDesign())
                .content(savedBuilding.getContent())
                .status(savedBuilding.getStatus())
                .build();
    }

    @Override
    public ResponseBuildingDTO updateBuilding(int id, RequestBuildingDTO request, MultipartFile file) {
        Building building=buildingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("building not found"));
        building.setBuildingName(request.getBuildingName());
        building.setBuildingArea(request.getBuildingArea());
        building.setAreaUnit(request.getAreaUnit());
        building.setStartDate(request.getStartDate());
        building.setTime(request.getTime());
        building.setTimeUnit(request.getTimeUnit());
        building.setContent(request.getContent());
        building.setStatus(request.getStatus());
        if(file!=null&&!file.isEmpty()) {
            String url=uploadImage(file);
            building.setDesign(url);
        }
        Building savedBuilding = buildingRepository.save(building);
        return ResponseBuildingDTO.builder()
                .id(savedBuilding.getId())
                .buildingName(savedBuilding.getBuildingName())
                .buildingArea(savedBuilding.getBuildingArea())
                .areaUnit(savedBuilding.getAreaUnit())
                .startDate(savedBuilding.getStartDate())
                .time(savedBuilding.getTime())
                .timeUnit(savedBuilding.getTimeUnit())
                .design(savedBuilding.getDesign())
                .content(savedBuilding.getContent())
                .status(savedBuilding.getStatus())
                .build();
    }

    @Override
    public Page<ResponseBuildingDTO> searchByBuildingName(String name, Pageable pageable) {
        return buildingRepository.findByBuildingName(name.trim(),pageable)
                .map(building ->ResponseBuildingDTO.builder()
                        .id(building.getId())
                        .buildingName(building.getBuildingName())
                        .buildingArea(building.getBuildingArea())
                        .areaUnit(building.getAreaUnit())
                        .startDate(building.getStartDate())
                        .time(building.getTime())
                        .timeUnit(building.getTimeUnit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build());
    }

    @Override
    public Page<ResponseBuildingDTO> searchByStatus(Status status, Pageable pageable) {
        return buildingRepository.findBuildingByStatus(status,pageable)
                .map(building ->ResponseBuildingDTO.builder()
                        .id(building.getId())
                        .buildingName(building.getBuildingName())
                        .buildingArea(building.getBuildingArea())
                        .areaUnit(building.getAreaUnit())
                        .startDate(building.getStartDate())
                        .time(building.getTime())
                        .timeUnit(building.getTimeUnit())
                        .design(building.getDesign())
                        .content(building.getContent())
                        .status(building.getStatus())
                        .build());
    }
    private String uploadImage(MultipartFile file) {
        if (file == null&&file.isEmpty()) {
            return null;
        }
        try{
            Map uloadResult=cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap());
            return uloadResult.get("secure_url").toString();
        }
        catch (Exception e) {throw new RuntimeException("Upload image failled");}
    }
}
