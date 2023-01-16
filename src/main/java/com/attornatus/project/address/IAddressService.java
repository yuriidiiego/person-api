package com.attornatus.project.address;

import com.attornatus.project.address.payload.request.CreateAddressRequest;
import com.attornatus.project.address.payload.response.AddressResponse;
import java.util.List;

public interface IAddressService {
  AddressResponse createAddress(
    Long personId,
    CreateAddressRequest addressPostRequest
  );
  List<AddressResponse> listAddresses(Long personId, int page, int size);
  boolean setPrimaryAddress(Long personId, long addressId);
}
