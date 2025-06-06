package com.adventistas.apimipsmais.dto.mongo;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {

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
