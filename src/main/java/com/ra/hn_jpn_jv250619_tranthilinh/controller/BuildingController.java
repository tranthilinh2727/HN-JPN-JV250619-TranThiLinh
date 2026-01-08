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

@RestController
@RequestMapping(value = "/api/buildings", produces = "application/json")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    @GetMapping
    public ApiResponse<?> getAllBuildings(@PageableDefault(size = 5) Pageable pageable) {
        return new ApiResponse<>(
                true,
                "Danh sách toà nhà",
                buildingService.getAllBuildings(pageable),
                null,
                200
        );
    }
    @PostMapping(consumes = "multipart/form-data")
    public ApiResponse<?> createBuilding(
            @Valid @ModelAttribute RequestBuildingDTO dto,
            @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        return new ApiResponse<>(
                true,
                "Thêm building thành công",
                buildingService.createBuilding(dto, image),
                null,
                201
        );
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ApiResponse<?> updateBuilding(
            @PathVariable Integer id,
            @ModelAttribute RequestBuildingDTO dto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return new ApiResponse<>(
                true,
                "Cập nhật building thành công",
                buildingService.updateBuilding(id, dto, image),
                null,
                200
        );
    }
    @GetMapping("/search")
    public ApiResponse<?> search(
            @RequestParam(required = false) String buildingName,
            @RequestParam(required = false) Status status,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        if (buildingName != null && !buildingName.trim().isEmpty()) {
            return new ApiResponse<>(
                    true,
                    "Tìm kiếm theo tên toà nhà",
                    buildingService.searchByBuildingName(buildingName, pageable),
                    null,
                    200
            );
        } else if (status != null) {
            return new ApiResponse<>(
                    true,
                    "Tìm kiếm theo trạng thái",
                    buildingService.searchByStatus(status, pageable),
                    null,
                    200
            );
        } else {
            return new ApiResponse<>(
                    false,
                    "Vui lòng cung cấp tên hoặc trạng thái để tìm kiếm",
                    null,
                    null,
                    400
            );
        }
    }
    @PatchMapping("/{id}/status")
    public ApiResponse<?> changeStatus(
            @PathVariable Integer id,
            @RequestParam Status status
    ) {
        return new ApiResponse<>(
                true,
                "Cập nhật trạng thái thành công",
                buildingService.changeStatus(id, status),
                null,
                200
        );
    }
}
