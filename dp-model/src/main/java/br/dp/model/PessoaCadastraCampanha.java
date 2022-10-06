package br.dp.model;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
public class PessoaCadastraCampanha {

    private String pessoaId;
    private String campanhaId;
    private String situacao;
    private Timestamp data;

}
