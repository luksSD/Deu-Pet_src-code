package br.dp.web.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    boolean uploadFiles(List<MultipartFile> file, Long id);
    boolean uploadFile(MultipartFile file, Long id, String type);
    String downloadUserFile(Long id);
    List<String> downloadAnimalFiles(Long id);
    String downloadCampainFile(Long id);

}
