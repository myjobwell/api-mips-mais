package com.adventistas.apimipsmais.controller.mongo.question;

//package com.adventistas.apimipsmais.controller.mongo;

import com.adventistas.apimipsmais.dto.mongo.QuestionDTO;
import com.adventistas.apimipsmais.service.mongo.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/questions")
@RequiredArgsConstructor
public class QuestionPublicaController {

    private final QuestionService service;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id-pergunta/{idPergunta}")
    public ResponseEntity<QuestionDTO> getByIdPergunta(@PathVariable Integer idPergunta) {
        return service.getByIdPergunta(idPergunta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
