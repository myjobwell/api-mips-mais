package com.adventistas.apimipsmais.controller.uniao;

import com.adventistas.apimipsmais.dto.UniaoDTO;
import com.adventistas.apimipsmais.service.UniaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/private/unioes")
public class UniaoPrivadaController {

    @Autowired
    private UniaoService service;

    @PostMapping
    public ResponseEntity<UniaoDTO> create(@RequestBody UniaoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniaoDTO> update(@PathVariable Long id, @RequestBody UniaoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 👇 Faltava isso aqui:
    @GetMapping
    public ResponseEntity<List<UniaoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}


