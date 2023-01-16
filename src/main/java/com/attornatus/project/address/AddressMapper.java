package com.attornatus.project.address;

import com.attornatus.project.address.payload.request.CreateAddressRequest;
import com.attornatus.project.address.payload.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
  @Mapping(target = "persons", ignore = true)
  @Mapping(target = "primary", ignore = true)
  Address toEntity(CreateAddressRequest addressDto);

  AddressResponse toDto(Address address);
}
