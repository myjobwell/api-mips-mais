package com.adventistas.apimipsmais.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Pergunta")
public class Question {

    @Id
    private String id;

    @Field("id_pergunta")
    private Integer idPergunta;
    private String pergunta;
    private String imagem;
    private String alternativa1;
    private String alternativa2;
    private String alternativa3;
    private String alternativa4;
    @Field("nr_alternativa_certa")
    private Integer nrAlternativaCerta;
    @Field("nr_nivel_dificuldade")
    private Integer nrNivelDificuldade;
    @Field("cd_idioma")
    private String cdIdioma;
    @Field("fk_area")
    private Integer fkArea;
    @Field("dh_ultima_modificacao")
    private Date dhUltimaModificacao;
}
