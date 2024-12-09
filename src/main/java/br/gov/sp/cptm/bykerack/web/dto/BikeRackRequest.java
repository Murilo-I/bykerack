package br.gov.sp.cptm.bykerack.web.dto;

import lombok.Data;

@Data
public class BikeRackRequest {

    private Integer bikeRackId;
    private String userDocument;
    private String employeeDocument;
}
