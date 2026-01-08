package com.ra.hn_jpn_jv250619_tranthilinh.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestBuildingDTO {
    @NotBlank(message = "Tên toà nhà không được để trống và tối đa 100 ký tự")
    private String buildingName;
    @NotNull(message = "Diện tích không được để trống")
    @Min(value = 1, message = "Diện tích phải lớn hơn 0")
    private Double buildingArea;
    @NotBlank(message = "Đơn vị diện tích không được để trống")
    private String areaUnit;
    @NotNull(message = "Ngày khởi công không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "Thời gian xây dựng không được để trống")
    @Min(value = 1, message = "Thời gian phải lớn hơn 0")
    private Integer time;
    @NotBlank(message = "Đơn vị thời gian không được để trống")
    private String timeUnit;
    private MultipartFile design;
    @NotBlank(message = "Nội dung không được để trống")
    private String content;
    @NotNull(message = "Trạng thái không được để trống")
    private Status status;
}
