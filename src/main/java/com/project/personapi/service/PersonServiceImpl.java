package com.project.personapi.service;

import com.project.personapi.dto.request.PersonDTO;
import com.project.personapi.dto.response.MessageResponseDTO;
import com.project.personapi.entity.Person;
import com.project.personapi.exception.PersonNotFoundException;
import com.project.personapi.mapper.PersonMapper;
import com.project.personapi.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository repository; // Repositório de dados

  private static final PersonMapper mapper = PersonMapper.INSTANCE; // Mapeamento de objetos

  @Override
  public MessageResponseDTO createPerson(PersonDTO personDTO) {
    Person personToSave = mapper.toModel(personDTO); // Mapeia o DTO para a entidade

    Person savePerson = repository.save(personToSave); // Salva a entidade no banco de dados
    return MessageResponseDTO
      .builder()
      .message("Person created with id " + savePerson.getId())
      .build();
  }

  @Override
  public List<PersonDTO> getAll() {
    List<Person> all = repository.findAll();

    if (all.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "No person found"
      );
    }
    return all
      .stream() // Transforma a lista de entidades em lista de DTOs
      .map(mapper::toDTO) // Mapeia a entidade para o DTO
      .collect(Collectors.toList()); // Transforma a lista de DTOs em lista de DTOs
  }

  @Override
  public PersonDTO findById(Long id) throws PersonNotFoundException {
    Person person = verifyIfExist(id);
    return mapper.toDTO(person); // Mapeia a entidade para o DTO
  }

  @Override
  public void deletePersonById(Long id) throws PersonNotFoundException {
    Person person = verifyIfExist(id); // Verifica se a entidade existe
    repository.delete(person); // Deleta a entidade do banco de dados
  }

  public Person verifyIfExist(Long id) throws PersonNotFoundException {
    return repository // Busca a entidade no banco de dados
      .findById(id) // Busca a entidade pelo id
      .orElseThrow(() -> new PersonNotFoundException(id)); // Caso não encontre, lança uma exceção
  }

  @Override
  public MessageResponseDTO updatePersonById(Long id, PersonDTO personDTO)
    throws PersonNotFoundException {
    verifyIfExist(id);

    Person personToUpdate = mapper.toModel(personDTO); // Mapeia o DTO para a entidade
    Person updatedPerson = repository.save(personToUpdate); // Salva a entidade no banco de dados
    return MessageResponseDTO
      .builder()
      .message("Person updated with id " + updatedPerson.getId())
      .build();
  }
}
