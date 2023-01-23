package com.attornatus.project.domain.address;

import com.attornatus.project.config.error.ErrorResponse;
import com.attornatus.project.domain.address.payload.request.CreateAddressRequest;
import com.attornatus.project.domain.address.payload.response.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "Address", description = "API for managing addresses")
@RequestMapping("/api/v1/addresses")
@RestController
public class AddressController {
  private final AddressServiceImpl service;

  public AddressController(AddressServiceImpl service) {
    this.service = service;
  }

  @Operation(
    summary = "Create an address for a person",
    operationId = "createAddress",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Address created",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AddressResponse.class)
          ),
        }
      ),
      @ApiResponse(responseCode = "400", description = "Invalid input"),
      @ApiResponse(
        responseCode = "409",
        description = "Address already exists",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
          ),
        }
      ),
    }
  )
  @PostMapping("/person/{personId}")
  public ResponseEntity<AddressResponse> createAddress(
    @Valid @RequestBody CreateAddressRequest createAddressRequest,
    @PathVariable @Parameter(
      description = "Person ID",
      example = "1"
    ) Long personId,
    UriComponentsBuilder uriComponentsBuilder
  ) {
    AddressResponse addressResponse = service.createAddressForPerson(
      personId,
      createAddressRequest
    );
    return ResponseEntity
      .created(
        uriComponentsBuilder
          .path("/api/v1/addresses/{id}")
          .buildAndExpand(addressResponse.getZipCode())
          .toUri()
      )
      .body(addressResponse);
  }

  @GetMapping("/person/{personId}")
  @Operation(
    summary = "List addresses for a person",
    operationId = "listAddresses",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Addresses listed",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AddressResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Invalid input",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Person not found",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
          ),
        }
      ),
    }
  )
  public ResponseEntity<List<AddressResponse>> listAddresses(
    @PathVariable @Parameter(
      description = "Person ID",
      example = "1"
    ) Long personId
  ) {
    List<AddressResponse> addressResponses = service.listAddressesForPerson(
      personId
    );

    return ResponseEntity.ok(addressResponses);
  }

  @Operation(
    summary = "Set an address as main for a person",
    operationId = "setMainAddress",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Address set as main",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AddressResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Invalid input",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "404",
        description = "Address or person not found",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)
          ),
        }
      ),
    }
  )
  @PutMapping("/main")
  public ResponseEntity<AddressResponse> setMainAddress(
    @RequestParam("personId") @Validated @Parameter(
      description = "Person ID",
      example = "1"
    ) Long personId,
    @RequestParam("addressId") @Validated @Parameter(
      description = "Address ID",
      example = "67145318"
    ) String addressId
  ) {
    AddressResponse addressResponse = service.setMainAddressForPerson(
      personId,
      addressId
    );

    return ResponseEntity.ok(addressResponse);
  }
}
