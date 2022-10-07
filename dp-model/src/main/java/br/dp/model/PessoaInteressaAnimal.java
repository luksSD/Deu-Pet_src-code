package br.dp.model;

import lombok.Data;

import java.security.Timestamp;

@Data
public class PessoaInteressaAnimal {

    private String pessoaId;
    private String animalId;
    private Timestamp data;

}
