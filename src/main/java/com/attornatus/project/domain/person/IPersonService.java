package com.attornatus.project.domain.person;

import java.util.List;

import com.attornatus.project.domain.person.payload.request.CreatePersonRequest;
import com.attornatus.project.domain.person.payload.request.UpdatePersonRequest;
import com.attornatus.project.domain.person.payload.response.PersonResponse;

public interface IPersonService {
  PersonResponse createPerson(CreatePersonRequest personPostRequest);
  PersonResponse editPerson(Long id, UpdatePersonRequest personPatchRequest);
  PersonResponse getPerson(Long id);
  List<PersonResponse> listPersons();
}
