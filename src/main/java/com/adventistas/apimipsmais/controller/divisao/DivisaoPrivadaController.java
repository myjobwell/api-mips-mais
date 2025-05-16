package com.adventistas.apimipsmais.controller.divisao;

import com.adventistas.apimipsmais.dto.DivisaoDTO;
import com.adventistas.apimipsmais.service.DivisaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/divisoes")
public class DivisaoPrivadaController {

    @Autowired
    private DivisaoService service;

    @PostMapping
    public ResponseEntity<DivisaoDTO> create(@RequestBody DivisaoDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DivisaoDTO> update(@PathVariable Long id, @RequestBody DivisaoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

