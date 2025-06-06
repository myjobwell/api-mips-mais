package com.adventistas.apimipsmais.dto.mongo;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
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
