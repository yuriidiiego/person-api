package com.attornatus.project.address.payload.request;

import com.attornatus.project.config.validator.CEP;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAddressRequest {
  @NotBlank(message = "Street is mandatory")
  @Schema(description = "Street name", example = "Rua das Flores")
  private String street;

  @NotBlank(message = "ZipCode is mandatory")
  @Schema(description = "ZipCode", example = "12345-678")
  @CEP
  private String zipCode;

  @NotNull(message = "Number is mandatory")
  @Schema(description = "Number of the address", example = "123")
  private int number;

  @NotBlank(message = "City is mandatory")
  @Schema(description = "City", example = "São Paulo")
  private String city;

  public String getCity() {
    return city;
  }

  public int getNumber() {
    return number;
  }

  public String getStreet() {
    return street;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }
}
