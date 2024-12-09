package br.gov.sp.cptm.bykerack.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class VacancyInfo {

    private BikeRackResponse bikeRack;
    private LocalDateTime entryDate;
    private String attendant;
}
