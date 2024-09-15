package br.gov.sp.cptm.bykerack.web.service;

import br.gov.sp.cptm.bykerack.app.usecase.AddressUseCase;
import br.gov.sp.cptm.bykerack.data.model.Address;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.data.respository.AddressRepository;
import br.gov.sp.cptm.bykerack.util.mapper.Mapper;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService implements AddressUseCase {

    Mapper mapper;
    AddressRepository repository;


    @Autowired
    public AddressService(Mapper mapper, AddressRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    @Transactional
    public Address updateAddress(User user, UserDTO request) {
        Address mappedAddress = mapper.mapAddress(request);
        Address address = user.getAddress();
        address.setAddress(mappedAddress.getAddress());
        address.setNumber(mappedAddress.getNumber());
        address.setNeighborhood(mappedAddress.getNeighborhood());
        address.setCity(mappedAddress.getCity());
        address.setState(mappedAddress.getState());
        address.setPostalCode(mappedAddress.getPostalCode());
        address.setComplement(mappedAddress.getComplement());
        return address;
    }
}
