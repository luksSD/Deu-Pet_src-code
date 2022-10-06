package br.dp.model;

import lombok.Data;

@Data
public class Animal extends BasePojo {

    private String tipo;

    private String nome;
    private String sexo;
    private Double peso;
    private String porte;
    private String raca;
    private boolean situacaoAodocao;
    private String temperamento;
    private String pelagemPrimaria;
    private String pelagemSecundaria;
    private Long idInstituicao;

}
