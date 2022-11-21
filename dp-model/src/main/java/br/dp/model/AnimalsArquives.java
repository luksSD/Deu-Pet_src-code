package br.dp.model;

import lombok.Data;

@Data
public class AnimalsArquives extends BasePojo {

    private Long animalID;
    private String path;
    private String key;
    private String file;
    private String type;
    private boolean primary;


}
