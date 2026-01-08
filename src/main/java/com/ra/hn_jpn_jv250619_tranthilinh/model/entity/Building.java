package com.ra.hn_jpn_jv250619_tranthilinh.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "building_name", length = 100, nullable = false, unique = true)
    private String buildingName;
    @Column(nullable = false)
    private double buildingArea;
    @Column(nullable = false, length = 10)
    private String areaUnit;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private int time;
    @Column(nullable = false, length = 10)
    private String timeUnit;
    @Column(nullable = false, length = 255)
    private String design;
    @Column(nullable = false, length = 255)
    private String content;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.IN_PROGRESS;
}
