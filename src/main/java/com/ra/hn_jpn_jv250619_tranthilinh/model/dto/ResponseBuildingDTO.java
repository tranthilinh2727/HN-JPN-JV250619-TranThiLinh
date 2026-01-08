package com.ra.hn_jpn_jv250619_tranthilinh.model.dto;

import com.ra.hn_jpn_jv250619_tranthilinh.model.entity.Status;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBuildingDTO {
    private Integer id;
    private String buildingName;
    private Double buildingArea;
    private String areaUnit;
    private LocalDate startDate;
    private Integer time;
    private String timeUnit;
    private String design;
    private String content;
    private Status status;
}
