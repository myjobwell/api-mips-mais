package com.adventistas.apimipsmais.service.mongo;

import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Question;
import com.adventistas.apimipsmais.mapper.QuestionMapper;
import com.adventistas.apimipsmais.repository.mongo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    public List<QuestionDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuestionDTO> getById(String id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public QuestionDTO create(QuestionDTO dto) {
        Question question = mapper.toEntity(dto);
        question.setDhUltimaModificacao(OffsetDateTime.now());
        Question saved = repository.save(question);
        return mapper.toDTO(saved);
    }

    public Optional<QuestionDTO> update(String id, QuestionDTO dto) {
        return repository.findById(id).map(existing -> {
            Question updated = mapper.toEntity(dto);
            updated.setId(existing.getId()); // mantém o _id do Mongo
            updated.setDhUltimaModificacao(OffsetDateTime.now());
            return mapper.toDTO(repository.save(updated));
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Optional<QuestionDTO> getByIdPergunta(Integer idPergunta) {
        return repository.findByIdPergunta(idPergunta)
                .map(mapper::toDTO);
    }
}
