package br.dp.model;

import lombok.Data;

@Data
public class UsersArquives extends BasePojo {

    private Long userId;
    private String path;
    private String file;
    private String type;
    private String key;

}
