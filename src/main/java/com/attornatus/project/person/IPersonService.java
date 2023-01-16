package com.attornatus.project.person;

import com.attornatus.project.person.payload.request.CreatePersonRequest;
import com.attornatus.project.person.payload.request.UpdatePersonRequest;
import com.attornatus.project.person.payload.response.PersonResponse;
import java.util.List;

public interface IPersonService {
  PersonResponse createPerson(CreatePersonRequest personPostRequest);
  PersonResponse editPerson(Long id, UpdatePersonRequest personPatchRequest);
  PersonResponse getPerson(Long id);
  List<PersonResponse> listPersons();
}
