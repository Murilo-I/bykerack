package br.gov.sp.cptm.bykerack.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessDTO {

    private String email;
    private String password;
}
