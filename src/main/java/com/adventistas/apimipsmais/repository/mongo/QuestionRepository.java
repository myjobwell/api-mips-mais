package com.adventistas.apimipsmais.repository.mongo;

import com.adventistas.apimipsmais.entity.mongo.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findByCdIdiomaAndIdPergunta(String cdIdioma, Integer idPergunta);


}
