package com.adventistas.apimipsmais.controller.mongo.area;

import com.adventistas.apimipsmais.dto.mongo.AreaDTO;
import com.adventistas.apimipsmais.service.mongo.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/public/area")
@RequiredArgsConstructor
public class AreaPublicaController {

    private final AreaService areaService;

    @GetMapping
    public ResponseEntity<List<AreaDTO>> getAll() {
        return ResponseEntity.ok(areaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDTO> getById(@PathVariable String id) {
        return areaService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id-area")
    public ResponseEntity<AreaDTO> getByIdArea(@RequestParam Integer idArea) {
        return areaService.getByIdArea(idArea)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
