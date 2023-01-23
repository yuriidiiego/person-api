package com.attornatus.project.domain.address;

import com.attornatus.project.domain.address.payload.request.CreateAddressRequest;
import com.attornatus.project.domain.address.payload.response.AddressResponse;
import java.util.List;

public interface IAddressService {
  AddressResponse createAddress(
    Long personId,
    CreateAddressRequest createAddressRequest
  );
  List<AddressResponse> listAddresses(Long personId, int page, int size);
  boolean setPrimaryAddress(Long personId, long addressId);
}
