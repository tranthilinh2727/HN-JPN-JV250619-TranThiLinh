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
    private Cloudinary cloudinary;
    @Override
    public Page<ResponseBuildingDTO> getAllBuildings(Pageable pageable) {
        return buildingRepository.findAll(pageable)
                .map(building -> {
                    ResponseBuildingDTO dto = new ResponseBuildingDTO();
                    dto.setId(building.getId());
                    dto.setBuildingName(building.getBuildingName());
                    dto.setBuildingArea(building.getBuildingArea());
                    dto.setAreaUnit(building.getAreaUnit());
                    dto.setStartDate(building.getStartDate());
                    dto.setTime(building.getTime());
                    dto.setTimeUnit(building.getTimeUnit());
                    dto.setDesign(building.getDesign());
                    dto.setContent(building.getContent());
                    dto.setStatus(building.getStatus());
                    return dto;
                });
    }
    @Override
    public ResponseBuildingDTO createBuilding(RequestBuildingDTO request, MultipartFile file) {
        if (buildingRepository.existsByBuildingName(request.getBuildingName())) {
            throw new RuntimeException("Building đã tồn tại");
        }
        Building building = new Building();
        building.setBuildingName(request.getBuildingName());
        building.setBuildingArea(request.getBuildingArea());
        building.setAreaUnit(request.getAreaUnit());
        building.setStartDate(request.getStartDate());
        building.setTime(request.getTime());
        building.setTimeUnit(request.getTimeUnit());
        building.setContent(request.getContent());
        building.setStatus(request.getStatus());
        String url = uploadImage(file);
        building.setDesign(url);
        Building savedBuilding = buildingRepository.save(building);
        ResponseBuildingDTO dto = new ResponseBuildingDTO();
        dto.setId(savedBuilding.getId());
        dto.setBuildingName(savedBuilding.getBuildingName());
        dto.setBuildingArea(savedBuilding.getBuildingArea());
        dto.setAreaUnit(savedBuilding.getAreaUnit());
        dto.setStartDate(savedBuilding.getStartDate());
        dto.setTime(savedBuilding.getTime());
        dto.setTimeUnit(savedBuilding.getTimeUnit());
        dto.setDesign(savedBuilding.getDesign());
        dto.setContent(savedBuilding.getContent());
        dto.setStatus(savedBuilding.getStatus());
        return dto;
    }

    @Override
    public ResponseBuildingDTO updateBuilding(int id, RequestBuildingDTO request, MultipartFile file) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));
        if (request.getBuildingName() != null) {
            building.setBuildingName(request.getBuildingName());
        }
        if (request.getBuildingArea() != null) {
            building.setBuildingArea(request.getBuildingArea());
        }
        if (request.getAreaUnit() != null) {
            building.setAreaUnit(request.getAreaUnit());
        }
        if (request.getStartDate() != null) {
            building.setStartDate(request.getStartDate());
        }
        if (request.getTime() != null) {
            building.setTime(request.getTime());
        }
        if (request.getTimeUnit() != null) {
            building.setTimeUnit(request.getTimeUnit());
        }
        if (request.getContent() != null) {
            building.setContent(request.getContent());
        }
        if (request.getStatus() != null) {
            building.setStatus(request.getStatus());
        }
        if (file != null && !file.isEmpty()) {
            String url = uploadImage(file);
            building.setDesign(url);
        }

        Building savedBuilding = buildingRepository.save(building);

        ResponseBuildingDTO dto = new ResponseBuildingDTO();
        dto.setId(savedBuilding.getId());
        dto.setBuildingName(savedBuilding.getBuildingName());
        dto.setBuildingArea(savedBuilding.getBuildingArea());
        dto.setAreaUnit(savedBuilding.getAreaUnit());
        dto.setStartDate(savedBuilding.getStartDate());
        dto.setTime(savedBuilding.getTime());
        dto.setTimeUnit(savedBuilding.getTimeUnit());
        dto.setDesign(savedBuilding.getDesign());
        dto.setContent(savedBuilding.getContent());
        dto.setStatus(savedBuilding.getStatus());

        return dto;
    }


    @Override
    public Page<ResponseBuildingDTO> searchByBuildingName(String name, Pageable pageable) {
        return buildingRepository.findByBuildingNameContainingIgnoreCase(name.trim(), pageable)
                .map(building -> {
                    ResponseBuildingDTO dto = new ResponseBuildingDTO();
                    dto.setId(building.getId());
                    dto.setBuildingName(building.getBuildingName());
                    dto.setBuildingArea(building.getBuildingArea());
                    dto.setAreaUnit(building.getAreaUnit());
                    dto.setStartDate(building.getStartDate());
                    dto.setTime(building.getTime());
                    dto.setTimeUnit(building.getTimeUnit());
                    dto.setDesign(building.getDesign());
                    dto.setContent(building.getContent());
                    dto.setStatus(building.getStatus());
                    return dto;
                });
    }

    @Override
    public Page<ResponseBuildingDTO> searchByStatus(Status status, Pageable pageable) {
        return buildingRepository.findByStatus(status, pageable)
                .map(building -> {
                    ResponseBuildingDTO dto = new ResponseBuildingDTO();
                    dto.setId(building.getId());
                    dto.setBuildingName(building.getBuildingName());
                    dto.setBuildingArea(building.getBuildingArea());
                    dto.setAreaUnit(building.getAreaUnit());
                    dto.setStartDate(building.getStartDate());
                    dto.setTime(building.getTime());
                    dto.setTimeUnit(building.getTimeUnit());
                    dto.setDesign(building.getDesign());
                    dto.setContent(building.getContent());
                    dto.setStatus(building.getStatus());
                    return dto;
                });
    }

    private String uploadImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            Map uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Upload image failed");
        }
    }
    @Override
    public ResponseBuildingDTO changeStatus(Integer id, Status newStatus) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy toà nhà"));

        if (building.getStatus() == Status.COMPLETE) {
            throw new RuntimeException("Toà nhà đã hoàn thành, không thể thay đổi trạng thái");
        }

        building.setStatus(newStatus);
        Building updated = buildingRepository.save(building);

        ResponseBuildingDTO dto = new ResponseBuildingDTO();
        dto.setId(updated.getId());
        dto.setBuildingName(updated.getBuildingName());
        dto.setBuildingArea(updated.getBuildingArea());
        dto.setAreaUnit(updated.getAreaUnit());
        dto.setStartDate(updated.getStartDate());
        dto.setTime(updated.getTime());
        dto.setTimeUnit(updated.getTimeUnit());
        dto.setDesign(updated.getDesign());
        dto.setContent(updated.getContent());
        dto.setStatus(updated.getStatus());

        return dto;
    }

}
