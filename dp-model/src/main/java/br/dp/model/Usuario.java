package br.dp.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Usuario extends BasePojo {

    private String nome;
    private String senha;
    private String email;
    private String celularTelefone;
    private boolean situacao;
    private Timestamp dataCadastro;
    private boolean aceite;
    private String tipo;
    private String profileImg;

}
