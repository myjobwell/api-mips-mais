package com.adventistas.apimipsmais.mapper;

import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDTO toDTO(Question entity);

    Question toEntity(QuestionDTO dto);

    List<QuestionDTO> toDTOList(List<Question> entities);
}
