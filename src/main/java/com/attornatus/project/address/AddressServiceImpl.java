package com.attornatus.project.address;

import com.attornatus.project.address.payload.request.CreateAddressRequest;
import com.attornatus.project.address.payload.response.AddressResponse;
import com.attornatus.project.person.Person;
import com.attornatus.project.person.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressServiceImpl {
  private final AddressRepository addressRepository;
  private final PersonRepository personRepository;
  private final AddressMapper addressMapper;

  public AddressServiceImpl(
    AddressRepository addressRepository,
    PersonRepository personRepository,
    AddressMapper addressMapper
  ) {
    this.addressRepository = addressRepository;
    this.personRepository = personRepository;
    this.addressMapper = addressMapper;
  }

  public AddressResponse createAddressForPerson(
    long personId,
    CreateAddressRequest addressPostRequest
  ) {
    Person person = getPersonById(personId);
    Address address = addressMapper.toEntity(addressPostRequest);
    address = addressRepository.save(address);
    person.addAddress(address);
    personRepository.save(person);
    return addressMapper.toDto(address);
  }

  public List<AddressResponse> listAddressesForPerson(long personId) {
    Person person = getPersonById(personId);
    return person
      .getAddresses()
      .stream()
      .map(addressMapper::toDto)
      .collect(Collectors.toList());
  }

  public AddressResponse setMainAddressForPerson(
    long personId,
    String addressId
  ) {
    Person person = getPersonById(personId);
    Address address = getAddressById(addressId);
    person.setMainAddress(address);
    personRepository.save(person);
    return addressMapper.toDto(address);
  }

  private Person getPersonById(long personId) {
    return personRepository
      .findById(personId)
      .orElseThrow(
        () ->
          new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found")
      );
  }

  private Address getAddressById(String addressId) {
    return addressRepository
      .findByZipCode(addressId)
      .orElseThrow(
        () ->
          new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found")
      );
  }
}
