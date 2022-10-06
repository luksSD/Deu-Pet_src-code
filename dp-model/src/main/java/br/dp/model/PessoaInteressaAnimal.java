package br.dp.model;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
public class PessoaInteressaAnimal {

    private String pessoaId;
    private String animalId;
    private Timestamp data;

}
