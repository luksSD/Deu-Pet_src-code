package br.dp.model;

import lombok.Data;

@Data
public class Instituicao extends Usuario {

    private String telefone;
    private String cpfCnpj;
    private String logradouro;
    private String numero;
    private String cep;
    private String muinicipioNome;
    private String uf;
    private Long municipioId;
    private Long usuarioId;

}
