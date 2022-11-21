package br.dp.model;

import lombok.Data;

@Data
public class CampainsArquives extends BasePojo {

    private Long campainId;
    private String path;
    private String type;
    private String key;

}
