package com.adventistas.apimipsmais.mapper;

import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = DateMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface QuestionMapper {

    @Mappings({
            @Mapping(source = "dhUltimaModificacao", target = "dhUltimaModificacao")
    })
    QuestionDTO toDTO(Question entity);

    @Mappings({
            @Mapping(source = "dhUltimaModificacao", target = "dhUltimaModificacao")
    })
    Question toEntity(QuestionDTO dto);
}



/*package com.adventistas.apimipsmais.mapper;

import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionDTO toDTO(Question question);

    Question toEntity(QuestionDTO dto);
}*/
