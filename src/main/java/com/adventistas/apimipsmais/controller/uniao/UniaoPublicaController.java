package com.adventistas.apimipsmais.controller.uniao;

import com.adventistas.apimipsmais.dto.UniaoDTO;
import com.adventistas.apimipsmais.service.UniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publico/unioes")
public class UniaoPublicaController {

    @Autowired
    private UniaoService service;

    @GetMapping
    public ResponseEntity<List<UniaoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
