package br.gov.sp.cptm.bykerack.web.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String complement;
}
