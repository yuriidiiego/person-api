package com.attornatus.project.domain.person;

import com.attornatus.project.domain.person.payload.request.CreatePersonRequest;
import com.attornatus.project.domain.person.payload.request.UpdatePersonRequest;
import com.attornatus.project.domain.person.payload.response.PersonResponse;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PersonMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "addresses", ignore = true)
  @Mapping(target = "mainAddress", ignore = true)
  Person toEntity(CreatePersonRequest personPostRequest);

  PersonResponse toDto(Person person);

  @BeanMapping(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "addresses", ignore = true)
  @Mapping(target = "mainAddress", ignore = true)
  void updateEntityFromDto(
    UpdatePersonRequest personPatchRequest,
    @MappingTarget Person person
  );

  List<PersonResponse> toDto(List<Person> persons);
}
