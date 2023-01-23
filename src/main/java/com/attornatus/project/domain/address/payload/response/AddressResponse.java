package com.attornatus.project.domain.address.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddressResponse", description = "Payload to return an address")
public class AddressResponse {
  @Schema(
    name = "street",
    description = "Street name",
    example = "Rua dos campos"
  )
  private String street;

  @Schema(name = "city", description = "City name", example = "São Paulo")
  private String city;

  @Schema(name = "zipCode", description = "ZipCode", example = "12345-678")
  private String zipCode;

  @Schema(
    name = "number",
    description = "Number of the address",
    example = "123"
  )
  private int number;

  @Schema(name = "primary", description = "Primary address", example = "true")
  private boolean primaryAddress;

  public boolean isPrimaryAddress() {
    return primaryAddress;
  }

  public AddressResponse(
    String street,
    String city,
    String zipCode,
    int number,
    boolean primary
  ) {
    this.street = street;
    this.city = city;
    this.zipCode = zipCode;
    this.number = number;
    this.primaryAddress = primary;
  }

  public AddressResponse() {}

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setPrimaryAddress(boolean primary) {
    this.primaryAddress = primary;
  }
}
