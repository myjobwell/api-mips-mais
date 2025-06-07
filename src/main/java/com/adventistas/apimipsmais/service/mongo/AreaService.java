package com.adventistas.apimipsmais.service.mongo;


import com.adventistas.apimipsmais.dto.mongo.AreaDTO;
import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Area;
import com.adventistas.apimipsmais.mapper.AreaMapper;
import com.adventistas.apimipsmais.repository.mongo.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    public List<AreaDTO> getAll() {
        return areaRepository.findAll()
                .stream()
                .map(areaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ Inserir novo registro
    public AreaDTO insert(AreaDTO dto) {
        Area area = areaMapper.toEntity(dto);
        Area saved = areaRepository.save(area);
        return areaMapper.toDTO(saved);
    }


    // ✅ Buscar por ID da colleção
    public Optional<AreaDTO> getById(String id) {
        return areaRepository.findById(id)
                .map(areaMapper::toDTO);
    }

    // ✅ Atualizar um registro por id da coleçção
    public Optional<AreaDTO> update(String id, AreaDTO dto) {
        return areaRepository.findById(id).map(existing -> {
            Area updated = areaMapper.toEntity(dto);
            updated.setId(id); // garante que o ID não seja sobrescrito
            Area saved = areaRepository.save(updated);
            return areaMapper.toDTO(saved);
        });
    }

    // ✅ Deletar por ID da colleção
    public boolean delete(String id) {
        if (areaRepository.existsById(id)) {
            areaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<AreaDTO> getByIdArea (Integer idArea) {
        return areaRepository.findByIdArea(idArea)
                .map(areaMapper::toDTO);
    }












}

