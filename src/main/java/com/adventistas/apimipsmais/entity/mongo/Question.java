package com.adventistas.apimipsmais.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Pergunta")
public class Question {

    @Id
    private String id;

    private Integer idPergunta;
    private String pergunta;
    private String imagem;
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;
    private String alternativa4;
    private Integer nrAlternativaCerta;
    private Integer nrNivelDificuldade;
    private String cdIdioma;
    private Integer fkArea;
    private OffsetDateTime dhUltimaModificacao;
}
