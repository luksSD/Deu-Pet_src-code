package br.dp.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Campanha extends BasePojo {

    private String titulo;
    private String descricao;
    private String requisitos;
    private Timestamp dataInicio;
    private Timestamp dataFim;
    private Long instituicaoId;


}
