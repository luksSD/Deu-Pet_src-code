package br.dp.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class Usuario extends BasePojo {

    private String nome;
    private String senha;
    private String email;
    private String celular;
    private boolean situacao;
    private Timestamp dataCadastro;
    private boolean aceite;
    private String tipo;

}
