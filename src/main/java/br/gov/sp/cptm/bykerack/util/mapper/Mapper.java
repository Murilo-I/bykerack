package br.gov.sp.cptm.bykerack.util.mapper;

import br.gov.sp.cptm.bykerack.data.model.Access;
import br.gov.sp.cptm.bykerack.data.model.Address;
import br.gov.sp.cptm.bykerack.data.model.Bicycle;
import br.gov.sp.cptm.bykerack.data.model.User;
import br.gov.sp.cptm.bykerack.web.dto.BicycleDTO;
import br.gov.sp.cptm.bykerack.web.dto.UserDTO;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mapping(source = "document", target = "document")
    @Mapping(source = "birthDate", target = "birthDate")
    User toEntity(UserDTO request);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password", qualifiedByName = "passwordEncoder")
    Access mapAccess(UserDTO request);

    @Mapping(source = "addressDTO.address", target = "address")
    @Mapping(source = "addressDTO.number", target = "number")
    @Mapping(source = "addressDTO.neighborhood", target = "neighborhood")
    @Mapping(source = "addressDTO.city", target = "city")
    @Mapping(source = "addressDTO.state", target = "state")
    @Mapping(source = "addressDTO.postalCode", target = "postalCode")
    @Mapping(source = "addressDTO.complement", target = "complement")
    Address mapAddress(UserDTO request);

    @Mapping(source = "documentType.documentTypeId", target = "docType")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "access.email", target = "email")
    @Mapping(source = "access.password", target = "password")
    @Mapping(source = "address.address", target = "addressDTO.address")
    @Mapping(source = "address.number", target = "addressDTO.number")
    @Mapping(source = "address.neighborhood", target = "addressDTO.neighborhood")
    @Mapping(source = "address.city", target = "addressDTO.city")
    @Mapping(source = "address.state", target = "addressDTO.state")
    @Mapping(source = "address.postalCode", target = "addressDTO.postalCode")
    @Mapping(source = "address.complement", target = "addressDTO.complement")
    UserDTO toDTO(User user);

    BicycleDTO mapBike(Bicycle bicycle);

    @Named("passwordEncoder")
    static String passwordEncoder(String grossPassword) {
        return new BCryptPasswordEncoder().encode(grossPassword);
    }
}
