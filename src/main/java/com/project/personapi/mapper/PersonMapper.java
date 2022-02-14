package com.project.personapi.mapper;

import com.project.personapi.dto.request.PersonDTO;
import com.project.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //
public interface PersonMapper {
  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Mapping( // Anotação para mapear o nome da propriedade do DTO para o nome da propriedade da entidade
    target = "birthDate", // Nome da propriedade do DTO
    source = "birthDate", // Nome da propriedade da entidade
    dateFormat = "dd-MM-yyyy" // Formato da data
  )
  Person toModel(PersonDTO personDTO);

  PersonDTO toDTO(Person person);
}
