package com.attornatus.project.domain.person;

import com.attornatus.project.domain.person.payload.request.CreatePersonRequest;
import com.attornatus.project.domain.person.payload.request.UpdatePersonRequest;
import com.attornatus.project.domain.person.payload.response.PersonResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PersonServiceImpl implements IPersonService {
  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  public PersonServiceImpl(
    PersonRepository personRepository,
    PersonMapper personMapper
  ) {
    this.personRepository = personRepository;
    this.personMapper = personMapper;
  }

  @Override
  public PersonResponse createPerson(CreatePersonRequest createPersonRequest) {
    Person person = personMapper.toEntity(createPersonRequest);
    return personMapper.toDto(personRepository.save(person));
  }

  @Override
  public PersonResponse editPerson(
    Long id,
    UpdatePersonRequest updatePersonRequest
  ) {
    Person person = findPersonById(id);
    personMapper.updateEntityFromDto(updatePersonRequest, person);
    return personMapper.toDto(personRepository.save(person));
  }

  @Override
  public PersonResponse getPerson(Long id) {
    return personMapper.toDto(findPersonById(id));
  }

  @Override
  public List<PersonResponse> listPersons() {
    Page<Person> person = personRepository.findAll(
      PageRequest.of(0, 20, Sort.by("name"))
    );
    return person
      .stream()
      .map(personMapper::toDto)
      .collect(Collectors.toList());
  }

  private Person findPersonById(Long id) {
    return personRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found")
      );
  }
}
