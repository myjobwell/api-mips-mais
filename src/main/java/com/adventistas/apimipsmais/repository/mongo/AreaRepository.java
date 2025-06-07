package com.adventistas.apimipsmais.repository.mongo;

import com.adventistas.apimipsmais.entity.mongo.Area;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AreaRepository extends MongoRepository<Area, String> {
    Optional<Area> findByIdArea(Integer idArea);
}
