package com.ra.hn_jpn_jv250619_tranthilinh.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestBuildingDTO {
    @NotBlank(message = "building name not null,unique,100 character ")
    private String buildingName;
    @NotNull(message = "building area not null")
    @Min(value = 0,message = "building area >0")
    private double buildingArea;
    @NotBlank(message = "area unit not null , 10 character ")
    private String areaUnit;
    @NotNull(message = "start date not null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull(message = "time not null, 10 character")
    @Min(value = 0,message = "time >=0")
    private int time;
    @NotBlank(message = "time unit not null , 10 character")
    private String timeUnit;
    private MultipartFile design; //ảnh
    @NotBlank(message = "content not null")
    private String content;
    private Status status;
}

