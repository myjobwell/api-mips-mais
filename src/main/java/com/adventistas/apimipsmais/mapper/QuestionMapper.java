package com.adventistas.apimipsmais.mapper;

import com.adventistas.apimipsmais.dto.mongo.AreaDTO;
import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Area;
import com.adventistas.apimipsmais.entity.mongo.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = "spring",
        uses = DateMapper.class, // só se você estiver realmente usando o DateMapper
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface QuestionMapper {
    QuestionDTO toDTO(Question entity);
    Question toEntity(QuestionDTO dto);
}



///


/*
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

*/


