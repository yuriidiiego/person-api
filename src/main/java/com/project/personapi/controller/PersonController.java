package com.project.personapi.controller;

import com.project.personapi.dto.request.PersonDTO;
import com.project.personapi.dto.response.MessageResponseDTO;
import com.project.personapi.exception.PersonNotFoundException;
import com.project.personapi.service.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(description = "Person API", name = "Person")
@RestController
@RequestMapping("/api/v1/person") // http://localhost:8080/api/v1/person
@AllArgsConstructor
public class PersonController {

  private final PersonServiceImpl service;

  @Operation(summary = "Create a person")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "201", description = "Person created"),
      @ApiResponse(responseCode = "400", description = "Invalid person data"),
    }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public MessageResponseDTO createPerson(
    @RequestBody @Valid PersonDTO personDTO
  ) {
    return service.createPerson(personDTO);
  }

  @Operation(summary = "Get all persons")
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "200", description = "Persons found"),
      @ApiResponse(responseCode = "404", description = "No person found"),
    }
  )
  @GetMapping
  public List<PersonDTO> getAll() {
    return service.getAll();
  }

  @Operation(summary = "Get a person by id")
  @GetMapping("/{id}")
  public PersonDTO findById(@PathVariable Long id)
    throws PersonNotFoundException {
    return service.findById(id);
  }

  @Operation(summary = "Delete a person")
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) throws PersonNotFoundException {
    service.deletePersonById(id);
  }

  @Operation(summary = "Update a person")
  @PutMapping("/{id}")
  public MessageResponseDTO updatePersonById(
    @PathVariable Long id,
    @RequestBody PersonDTO personDTO
  )
    throws PersonNotFoundException {
    return service.updatePersonById(id, personDTO);
  }
}
