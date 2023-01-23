package com.attornatus.project.domain.person.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
  name = "CreatePersonRequest",
  description = "Payload to create a new person"
)
public class CreatePersonRequest {
  @Schema(description = "Person name", example = "Daniel Costa")
  @Pattern(
    regexp = "[A-Za-z ]+",
    message = "Name must contain only alphabets and spaces"
  )
  @NotBlank(message = "Name is mandatory")
  private String name;

  @NotNull(message = "BirthDate is mandatory")
  @Past(message = "Birthdate can not be in future")
  @Schema(description = "BirthDate", example = "01/01/2000")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate birthDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}
