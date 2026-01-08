package com.ra.hn_jpn_jv250619_tranthilinh.model.dto;

import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBuildingDTO {
    private int id;
    private String buildingName;
    private double buildingArea;
    private String areaUnit;
    private LocalDate startDate;
    private int time;
    private String timeUnit;
    private String design; //ảnh
    private String content;
    private Status status;
}

