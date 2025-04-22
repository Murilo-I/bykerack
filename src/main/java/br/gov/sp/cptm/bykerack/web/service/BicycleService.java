package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.BicycleUseCase;
import br.gov.sp.cptm.bykerack.data.model.Bicycle;
import br.gov.sp.cptm.bykerack.data.respository.BicycleRepository;
import br.gov.sp.cptm.bykerack.data.respository.UserRepository;
import br.gov.sp.cptm.bykerack.util.exception.BicycleNotFoundException;
import br.gov.sp.cptm.bykerack.util.exception.BikeNotBelongToUserException;
import br.gov.sp.cptm.bykerack.util.exception.UserNotFoundException;
import br.gov.sp.cptm.bykerack.web.dto.BicycleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleService implements BicycleUseCase {

    UserRepository userRepository;
    BicycleRepository bikeRepository;

    @Autowired
    public BicycleService(UserRepository userRepository, BicycleRepository bikeRepository) {
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
    }

    @Override
    public List<BicycleDTO> findAll(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return bikeRepository.findByUser(user).stream()
                .map(bike -> BicycleDTO.builder()
                        .year(bike.getYear())
                        .color(bike.getColor())
                        .details(bike.getDetails())
                        .model(bike.getModel())
                        .chassis(bike.getChassis())
                        .build())
                .toList();
    }

    @Override
    public void save(BicycleDTO bicycleDto, Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var bike = new Bicycle(user, bicycleDto);
        bikeRepository.save(bike);
    }

    @Override
    public void update(BicycleDTO bicycleDto, Long userId, Long bikeId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var bike = bikeRepository.findById(bikeId).orElseThrow(BicycleNotFoundException::new);

        if (!user.equals(bike.getUser()))
            throw new BikeNotBelongToUserException();

        bikeRepository.save(bike.fromDto(bicycleDto));
    }

    @Override
    public void delete(Long userId, Long bikeId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var bike = bikeRepository.findById(bikeId).orElseThrow(BicycleNotFoundException::new);

        if (!user.equals(bike.getUser()))
            throw new BikeNotBelongToUserException();

        bikeRepository.delete(bike);
    }
}
