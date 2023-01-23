package com.attornatus.project.domain.address;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
  Optional<Address> findByZipCode(String zipCode);

  boolean existsByZipCode(String value);
}
