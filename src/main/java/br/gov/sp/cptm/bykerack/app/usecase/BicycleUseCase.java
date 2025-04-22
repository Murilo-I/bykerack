package br.gov.sp.cptm.bykerack.app.usecase;

import br.gov.sp.cptm.bykerack.web.dto.BicycleDTO;

import java.util.List;

public interface BicycleUseCase {

    List<BicycleDTO> findAll(Long userId);

    BicycleDTO save(BicycleDTO bicycleDto, Long userId);

    BicycleDTO update(BicycleDTO bicycleDto, Long userId, Long bikeId);

    void delete(Long userId, Long bikeId);
}
