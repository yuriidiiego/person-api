package com.attornatus.project.person;

import com.attornatus.project.config.error.ErrorResponse;
import com.attornatus.project.person.payload.request.CreatePersonRequest;
import com.attornatus.project.person.payload.request.UpdatePersonRequest;
import com.attornatus.project.person.payload.response.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Person", description = "API for managing persons")
@RequestMapping("/api/v1/persons")
@RestController
public class PersonController {
  private final IPersonService service;

  public PersonController(IPersonService service) {
    this.service = service;
  }

  @Operation(
    summary = "Create a person",
    operationId = "createPerson",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Person created",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Name and birthdate are required"
      ),
    }
  )
  @PostMapping
  public ResponseEntity<PersonResponse> createPerson(
    @Valid @RequestBody CreatePersonRequest personPostRequest,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    PersonResponse personResponse = service.createPerson(personPostRequest);
    return ResponseEntity
      .created(
        uriComponentsBuilder
          .path("/api/v1/persons/{id}")
          .buildAndExpand(personResponse.getId())
          .toUri()
      )
      .body(personResponse);
  }

  @PatchMapping("/{id}")
  @Operation(
    summary = "Edit a person",
    operationId = "editPerson",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Person edited",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Person not found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
    }
  )
  public ResponseEntity<PersonResponse> editPerson(
    @Valid @RequestBody UpdatePersonRequest personPatchRequest,
    @PathVariable @Parameter(description = "Person id", example = "1") Long id
  ) {
    PersonResponse personResponse = service.editPerson(id, personPatchRequest);
    return ResponseEntity.ok(personResponse);
  }

  @GetMapping("/{id}")
  @Operation(
    summary = "Get a person",
    operationId = "getPerson",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Person found",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Person not found",
        content = @Content(
          mediaType = "application/json",
          schema = @Schema(implementation = ErrorResponse.class)
        )
      ),
    }
  )
  public ResponseEntity<PersonResponse> getPerson(
    @PathVariable @Parameter(description = "Person id", example = "1") Long id
  ) {
    PersonResponse personResponse = service.getPerson(id);
    return ResponseEntity.ok(personResponse);
  }

  @Operation(
    summary = "List all persons",
    operationId = "listPersons",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Persons found",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PersonResponse.class)
          ),
        }
      ),
    }
  )
  @GetMapping
  public ResponseEntity<List<PersonResponse>> listPersons() {
    List<PersonResponse> personResponses = service.listPersons();
    return ResponseEntity.ok(personResponses);
  }
}
