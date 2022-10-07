package br.dp.model;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class PessoaCadastraCampanha {

    private String pessoaId;
    private String campanhaId;
    private String situacao;
    private Timestamp data;

}
