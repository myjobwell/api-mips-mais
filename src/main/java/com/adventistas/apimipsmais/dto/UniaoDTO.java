package com.adventistas.apimipsmais.dto;

public class UniaoDTO {

    private Long id;
    private String nome;

    private String sigla;

    private Long idDivisao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getIdDivisao() {
        return idDivisao;
    }

    public void setIdDivisao(Long idDivisao) {
        this.idDivisao = idDivisao;
    }
}
