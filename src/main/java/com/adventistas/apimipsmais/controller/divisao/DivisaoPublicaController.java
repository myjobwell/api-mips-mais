package com.adventistas.apimipsmais.controller.divisao;

import com.adventistas.apimipsmais.dto.DivisaoDTO;
import com.adventistas.apimipsmais.service.DivisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publico/divisoes")
public class DivisaoPublicaController {

    @Autowired
    private DivisaoService service;

    @GetMapping
    public ResponseEntity<List<DivisaoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}

