package com.project.personapi.dto.request;

import com.project.personapi.enums.PhoneType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe DTO(data transfer object) usada para representar um telefone.
 * DTOs são usados para representar objetos que serão enviados para ou retornados de uma requisição.
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

  private Long id;

  @Enumerated(EnumType.STRING)
  private PhoneType type;

  @NotEmpty(message = "The phone number is required")
  @Size(
    min = 8,
    max = 15,
    message = "The phone number must be between 8 and 15 characters"
  )
  private String number;
}
