package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.entity.Address;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * Using the mapstruct, the address object is mapped to the addressDTO object.
 */
@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public abstract List<Address> mapAddressDTOListToAddressList(Collection<AddressDTO> addressDTOS);
    public abstract List<AddressDTO> mapAddressListToAddressDTOList(Collection<Address> addresses);
    public abstract Address mapAddressDTOToAddress(AddressDTO addressDTO);
    public abstract AddressDTO mapAddressToAddressDTO(Address address);

}