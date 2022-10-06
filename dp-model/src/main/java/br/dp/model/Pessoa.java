package br.dp.model;

import lombok.Data;

@Data
public class Pessoa extends Usuario {

    private String telefone;
    private String logradouro;
    private String numero;
    private String cep;
    private Long municipioId;

}
