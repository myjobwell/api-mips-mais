package com.adventistas.apimipsmais.service;

import com.adventistas.apimipsmais.dto.UniaoDTO;
import com.adventistas.apimipsmais.entity.Divisao;
import com.adventistas.apimipsmais.entity.Uniao;
import com.adventistas.apimipsmais.repository.DivisaoRepository;
import com.adventistas.apimipsmais.repository.UniaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniaoService {

    @Autowired
    private UniaoRepository uniaoRepository;

    @Autowired
    private DivisaoRepository divisaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UniaoDTO save(UniaoDTO dto) {
        Uniao uniao = convertToEntity(dto);
        Uniao saved = uniaoRepository.save(uniao);
        return convertToDTO(saved);
    }

    public UniaoDTO update(Long id, UniaoDTO dto) {
        Uniao existente = uniaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("União não encontrada"));
        modelMapper.map(dto, existente);
        existente.setDivisao(findDivisao(dto.getIdDivisao()));
        return convertToDTO(uniaoRepository.save(existente));
    }

    public void delete(Long id) {
        uniaoRepository.deleteById(id);
    }

    public UniaoDTO findById(Long id) {
        Uniao uniao = uniaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("União não encontrada"));
        return convertToDTO(uniao);
    }

    public List<UniaoDTO> findAll() {
        return uniaoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 👇 Helpers para manter o código limpo
    private Uniao convertToEntity(UniaoDTO dto) {
        Uniao uniao = modelMapper.map(dto, Uniao.class);
        uniao.setDivisao(findDivisao(dto.getIdDivisao()));
        return uniao;
    }

    private UniaoDTO convertToDTO(Uniao uniao) {
        UniaoDTO dto = modelMapper.map(uniao, UniaoDTO.class);
        dto.setIdDivisao(uniao.getDivisao().getId());
        return dto;
    }

    private Divisao findDivisao(Long idDivisao) {
        return divisaoRepository.findById(idDivisao)
                .orElseThrow(() -> new RuntimeException("Divisão não encontrada"));
    }
}


