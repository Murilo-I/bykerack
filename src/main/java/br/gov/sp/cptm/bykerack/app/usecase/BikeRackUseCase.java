package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.data.model.BikeRack;
import br.gov.sp.cptm.bykerack.web.dto.BikeRackDTO;
import br.gov.sp.cptm.bykerack.web.dto.VacancyResponse;

import java.util.List;

public interface BikeRackUseCase {

    VacancyResponse saveVacancy(BikeRackDTO request);

    List<BikeRack> findAll();
}
