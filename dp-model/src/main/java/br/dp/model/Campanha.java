package br.dp.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Campanha extends BasePojo {

    private String titulo;
    private String descricao;
    private String requisitos;
    private Date dataInicio;
    private Date dataFim;
    private Long instituicaoId;
    private String urlForm;
    private String campainImg;

}
