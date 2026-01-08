package com.ra.hn_jpn_jv250619_tranthilinh.controller;

import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.ApiResponse;
import com.ra.hn_jpn_jv250619_tranthilinh.model.dto.RequestBuildingDTO;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import com.ra.hn_jpn_jv250619_tranthilinh.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping
    public ApiResponse<?>getAllBuildings(@PageableDefault(size = 5) Pageable pageable) {
        return new ApiResponse<>(
                true,
                "List building",
                buildingService.getAllBuildings(pageable),
                null,
                200
        );
    }
    @PostMapping
    public ApiResponse<?> createBuilding(
            @Valid @ModelAttribute RequestBuildingDTO dto,
            @RequestPart(value = "images",required = false) MultipartFile images
            ) {
        return new ApiResponse<>(
                true,
                "Thêm building thành công ",
                buildingService.createBuilding(dto,images),
                null,
                201
        );
    }
    @PutMapping(value = "/{id}",consumes = "multipart/form-data")
    public ApiResponse<?> updateBuilding(
            @PathVariable Integer id,
            @Valid @ModelAttribute RequestBuildingDTO dto,
    @RequestPart(value = "images" ,required = false)MultipartFile images
    ){
        return new ApiResponse<>(
                true,
                "Cập nhật building thành công " ,
                buildingService.updateBuilding(id,dto,images),
                null,
                200
        );
    }
    @GetMapping("/search")
    public ApiResponse<?>search(
            @RequestParam (required = false)String buildingName,
            @RequestParam(required = false) Status status,
            Pageable pageable
    ){
        if(buildingName != null){
            return new ApiResponse<>(
                    true,
                    "Search by building name",
                    buildingService.searchByBuildingName(buildingName,pageable),
                    null,
                    200
            );
        }
        return new ApiResponse<>(
                true,
                "Search by status",
                buildingService.searchByStatus(status,pageable),
                null,
                200
        );
    }
}
