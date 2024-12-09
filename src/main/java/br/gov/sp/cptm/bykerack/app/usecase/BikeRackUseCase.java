package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackRequest;
import br.gov.sp.cptm.bykerack.web.dto.VacancyInfo;
import br.gov.sp.cptm.bykerack.web.dto.VacancyResponse;

import java.util.List;

public interface BikeRackUseCase {

    VacancyResponse saveVacancy(BikeRackRequest request);

    List<BikeRack> findAll();

    VacancyInfo getVacancyInfo(Long userId);
}
