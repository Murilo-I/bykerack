package br.gov.sp.cptm.bykerack.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private String type;
    private Long userId;
    private Date issuedAt;
    private Date expiration;
}
