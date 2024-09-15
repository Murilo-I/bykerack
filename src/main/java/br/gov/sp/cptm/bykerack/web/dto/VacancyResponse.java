package br.gov.sp.cptm.bykerack.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VacancyResponse {

    private String message;
    private Boolean isRetrieval;
}
