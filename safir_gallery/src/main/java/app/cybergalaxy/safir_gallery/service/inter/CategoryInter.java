package app.cybergalaxy.safir_gallery.service.inter;

import app.cybergalaxy.safir_gallery.dto.request.CategoryAddRequest;
import app.cybergalaxy.safir_gallery.dto.request.CategoryUpdateRequest;
import app.cybergalaxy.safir_gallery.dto.response.CategoryResponse;

public interface CategoryInter {

    void add(CategoryAddRequest request);

    CategoryResponse findAll();

    void update(CategoryUpdateRequest request);

    void delete(Integer id);

    CategoryResponse findById(Integer id);

}
