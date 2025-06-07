package com.adventistas.apimipsmais.dto.mongo;
import com.adventistas.apimipsmais.entity.mongo.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaDTO {
    private Integer idArea;
    private String cdArea;
    private String nmArea;
    private String nmAreaEs;
    private String cdImagem;
    private Integer fkAreaSuperior;
    private Boolean flDisponivelPt;
    private Boolean flDisponivelEs;
    private Date dhUltimaModificacao;

}
