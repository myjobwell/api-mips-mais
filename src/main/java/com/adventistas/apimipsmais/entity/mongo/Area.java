package com.adventistas.apimipsmais.entity.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Area")

public class Area {

    @Id
    private String id;
    @Field ("id_area")
    private Integer idArea;

    @Field("cd_area")
    private String cdArea;

    @Field("nm_area")
    private String nmArea;

    @Field("nm_area_es")
    private String nmAreaEs;

    @Field("cd_imagem")
    private String cdImagem;

    @Field("fk_area_superior")
    private Integer fkAreaSuperior;

    @Field("fl_disponivel_pt")
    private Boolean flDisponivelPt;

    @Field("fl_disponivel_es")
    private boolean flDisponivelEs;

    @Field("dh_ultima_modificacao")
    private Date dhUltimaModificacao;

}

