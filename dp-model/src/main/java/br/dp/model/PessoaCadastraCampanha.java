package br.dp.model;

import lombok.Data;

import java.security.Timestamp;

@Data
public class PessoaCadastraCampanha {

    private String pessoaId;
    private String campanhaId;
    private String situacao;
    private Timestamp data;

}