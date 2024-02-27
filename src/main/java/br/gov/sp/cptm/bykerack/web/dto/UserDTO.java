package br.gov.sp.cptm.bykerack.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;
    private Integer docType;
    private String document;
    private LocalDate birthDate;
    private String email;
    private String password;
    private AddressDTO addressDTO;
}
