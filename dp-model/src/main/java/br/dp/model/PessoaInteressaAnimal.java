package br.dp.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PessoaInteressaAnimal extends BasePojo {

    private Long pessoaId;
    private Long animalId;
    private String status;
    private Timestamp data;
    private Animal animal;
    private Pessoa pessoa;
}
