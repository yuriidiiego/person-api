package com.project.personapi.service;

import com.project.personapi.dto.request.PersonDTO;
import com.project.personapi.dto.response.MessageResponseDTO;
import com.project.personapi.exception.PersonNotFoundException;
import java.util.List;

public interface PersonService {
  MessageResponseDTO createPerson(PersonDTO personDTO);

  List<PersonDTO> getAll();

  PersonDTO findById(Long id) throws PersonNotFoundException;

  void deletePersonById(Long id) throws PersonNotFoundException;

  MessageResponseDTO updatePersonById(Long id, PersonDTO personDTO)
    throws PersonNotFoundException;
}
