package com.attornatus.project.address;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
  Optional<Address> findByZipCode(String zipCode);

  boolean existsByZipCode(String value);
}
