package com.attornatus.project.person.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

@Schema(
  name = "UpdatePersonRequest",
  description = "Payload to update a person"
)
public class UpdatePersonRequest {
  @Schema(description = "Name of the person", example = "Estevão Correia")
  @Pattern(
    regexp = "[A-Za-z ]+",
    message = "Name must contain only alphabets and spaces"
  )
  private String name;

  @Past(message = "Birthdate can not be in future")
  @Schema(description = "BirthDate", example = "01/01/2000")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate birthDate;

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getName() {
    return name;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public void setName(String name) {
    this.name = name;
  }
}
