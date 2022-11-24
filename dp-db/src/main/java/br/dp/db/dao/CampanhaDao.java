package br.dp.db.dao;

import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;

import java.util.List;

public interface CampanhaDao {

    List<Campanha> readAll();

    Campanha readById(Long id);

    Long create(Campanha entity);

    boolean update(Campanha entity);

    boolean delete(Long id);

    Long saveFileAttributes(CampainsArquives imagePath);

    CampainsArquives loadImage(long id);

    boolean deleteFile(long id);

    boolean updateFileAttributes(CampainsArquives file);
}
