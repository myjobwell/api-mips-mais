package com.adventistas.apimipsmais.service.mongo;
import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.entity.mongo.Question;
import com.adventistas.apimipsmais.repository.mongo.QuestionRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public List<QuestionDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<QuestionDTO> getById(String id) {
        return repository.findById(id).map(this::toDTO);
    }

    public QuestionDTO create(QuestionDTO dto) {
        Question question = toEntity(dto);
        question.setDhUltimaModificacao(OffsetDateTime.now());
        Question saved = repository.save(question);
        return toDTO(saved);
    }

    public Optional<QuestionDTO> update(String id, QuestionDTO dto) {
        return repository.findById(id).map(existing -> {
            BeanUtils.copyProperties(dto, existing, "id");
            existing.setDhUltimaModificacao(OffsetDateTime.now());
            return toDTO(repository.save(existing));
        });
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    private QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        BeanUtils.copyProperties(question, dto);
        return dto;
    }

    private Question toEntity(QuestionDTO dto) {
        Question question = new Question();
        BeanUtils.copyProperties(dto, question);
        return question;
    }

    public Optional<QuestionDTO> getByIdPergunta(Integer idPergunta) {
        return repository.findByIdPergunta(idPergunta)
                .map(mapper::toDTO); // Adapte se você não estiver usando mapper
    }



}
