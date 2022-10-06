package br.dp.model;

import lombok.Data;

@Data
public class Instituicao extends Usuario {

    private String telefone;
    private String cnpj;
    private String logradouro;
    private String numero;
    private String cep;
    private String cpf;
    private Long municipioId;
    private Long usuarioId;

}
