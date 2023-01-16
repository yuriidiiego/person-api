package com.attornatus.project.person.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(name = "PersonResponse", description = "Payload to return a person")
public class PersonResponse {
  @Schema(name = "id", description = "Person id", example = "1")
  private Long id;

  @Schema(name = "name", description = "Person name", example = "Daniel Costa")
  private String name;

  @Schema(
    name = "birthDate",
    description = "Person birth date",
    example = "01/01/2000"
  )
  private LocalDate birthDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }
}
