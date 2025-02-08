package app.cybergalaxy.safir_gallery.service.inter;

import app.cybergalaxy.safir_gallery.dto.response.FlowerResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlowerInter {

    void add(String text, Integer categoryId, List<MultipartFile> file);

    FlowerResponse findByCategory(Integer categoryId);

    void update(Integer id, String text, Integer categoryId, List<MultipartFile> files);

    void delete(Integer id);

    FlowerResponse findAll();

    FlowerResponse findById(Integer id);

}
