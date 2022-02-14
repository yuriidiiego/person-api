package com.project.personapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

  private Long id;

  @Schema(description = "First name of the person", example = "Yuri")
  @NotEmpty(message = "First Name is required")
  @Size(
    min = 2,
    max = 50,
    message = "First Name must be between 2 and 50 characters"
  )
  private String firstName;

  @Schema(description = "Last name of the person", example = "Nascimento")
  @NotEmpty(message = "Last Name is required")
  @Size(
    min = 2,
    max = 50,
    message = "Last Name must be between 2 and 50 characters"
  )
  private String lastName;

  @Schema(description = "CPF of the person", example = "70989098230")
  @CPF(message = "CPF is invalid")
  private String cpf;

  @Schema(description = "Date of birth of the person", example = "02-10-1995")
  private String birthDate;

  @Schema(
    description = "List of phones of the person",
    example = "[{\"type\":\"HOME\",\"number\":\"(91) 98900-6113\"}]"
  )
  @Valid
  @NotEmpty
  private List<PhoneDTO> phones;
}
