package com.attornatus.project.config.validator;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

import com.attornatus.project.domain.address.AddressRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CEPValidator implements ConstraintValidator<CEP, String> {
  private static final String CEP_PATTERN = "^\\d{5}-?\\d{3}$";
  private final AddressRepository addressRepository;

  public CEPValidator(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (!isCEPFormatValid(value)) {
      throw new ResponseStatusException(BAD_REQUEST, "Invalid CEP format");
    }

    if (addressRepository.existsByZipCode(value)) {
      throw new ResponseStatusException(CONFLICT, "Address already exists");
    }

    return true;
  }

  private boolean isCEPFormatValid(String value) {
    return value.matches(CEP_PATTERN);
  }
}
