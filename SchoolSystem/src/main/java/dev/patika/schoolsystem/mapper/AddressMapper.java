package dev.patika.schoolsystem.mapper;

import dev.patika.schoolsystem.dto.AddressDTO;
import dev.patika.schoolsystem.entity.Address;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public abstract List<Address> mapAddressListToAddressDTOList(Collection<AddressDTO> addressDTOS);
    public abstract List<AddressDTO> mapAddressDTOListToAddressList(Collection<Address> addresses);
    public abstract Address mapAddressToAddressDTO(AddressDTO addressDTO);
    public abstract AddressDTO mapAddressDTOToAddress(Address address);

}