package com.adventistas.apimipsmais.service;

import com.adventistas.apimipsmais.dto.DivisaoDTO;
import com.adventistas.apimipsmais.entity.Divisao;
import com.adventistas.apimipsmais.repository.DivisaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// service/DivisaoService.java
@Service
public class DivisaoService {

    @Autowired
    private DivisaoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public DivisaoDTO save(DivisaoDTO dto) {
        Divisao entidade = modelMapper.map(dto, Divisao.class);
        Divisao salvo = repository.save(entidade);
        return modelMapper.map(salvo, DivisaoDTO.class);
    }

    public DivisaoDTO update(Long id, DivisaoDTO dto) {
        Divisao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Divisão não encontrada"));
        modelMapper.map(dto, existente);
        Divisao atualizado = repository.save(existente);
        return modelMapper.map(atualizado, DivisaoDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public DivisaoDTO findById(Long id) {
        Divisao div = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Divisão não encontrada"));
        return modelMapper.map(div, DivisaoDTO.class);
    }

    public List<DivisaoDTO> findAll() {
        return repository.findAll().stream()
                .map(div -> modelMapper.map(div, DivisaoDTO.class))
                .collect(Collectors.toList());
    }
}




