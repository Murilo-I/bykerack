package br.gov.sp.cptm.bykerack.web.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BicycleDTO {

    private Long bicycleId;
    private String model;
    private String color;
    private String chassis;
    private Integer year;
    private String details;
}
