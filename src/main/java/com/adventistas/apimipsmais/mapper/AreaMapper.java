package com.adventistas.apimipsmais.mapper;


import com.adventistas.apimipsmais.dto.mongo.AreaDTO;
import com.adventistas.apimipsmais.entity.mongo.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = DateMapper.class, // só se você estiver realmente usando o DateMapper
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AreaMapper {
    AreaDTO toDTO(Area entity);
    Area toEntity(AreaDTO dto);
}






/*public interface AreaMapper {

    @Mappings({
            @Mapping(source = "dhUltimaModificacao", target = "dhUltimaModificacao")
    })
    AreaDTO toDTO(Area entity);

    @Mappings({
            @Mapping(source = "dhUltimaModificacao", target = "dhUltimaModificacao")
    })
    Area toEntity(AreaDTO dto);
}*/
