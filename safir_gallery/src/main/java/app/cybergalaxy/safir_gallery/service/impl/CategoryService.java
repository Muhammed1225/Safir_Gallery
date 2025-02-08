package app.cybergalaxy.safir_gallery.service.impl;

import app.cybergalaxy.safir_gallery.dto.request.CategoryAddRequest;
import app.cybergalaxy.safir_gallery.dto.request.CategoryUpdateRequest;
import app.cybergalaxy.safir_gallery.dto.response.CategoryResponse;
import app.cybergalaxy.safir_gallery.dto.response.CategorySingleResponse;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.model.CategoryEntity;
import app.cybergalaxy.safir_gallery.repository.CategoryRepository;
import app.cybergalaxy.safir_gallery.service.inter.CategoryInter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryInter {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void add(CategoryAddRequest request) {
        CategoryEntity category = new CategoryEntity();
        mapper.map(request, category);
        repository.save(category);
    }

    @Override
    public CategoryResponse findAll() {
        CategoryResponse response = new CategoryResponse();
        List<CategoryEntity> entities = repository.findAll();
        List<CategorySingleResponse> responseList = new ArrayList<>();

        for (CategoryEntity e : entities) {
            CategorySingleResponse singleResponse = new CategorySingleResponse();
            mapper.map(e, singleResponse);
            responseList.add(singleResponse);
        }

        response.setCategories(responseList);
        return response;
    }

    @Override
    public void update(CategoryUpdateRequest request) {
        Optional<CategoryEntity> optional = repository.findById(request.getId());
        if (optional.isPresent()) {
            CategoryEntity category = optional.get();
            mapper.map(request, category);
            repository.save(category);
        } else {
            throw new MyException("The id wasn't found!");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<CategoryEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new MyException("The id wasn't found!");
        }
    }

    @Override
    public CategoryResponse findById(Integer id) {
        CategoryResponse response = new CategoryResponse();
        Optional<CategoryEntity> optional = repository.findById(id);
        List<CategorySingleResponse> responseList = new ArrayList<>();

        if (optional.isPresent()){
            CategoryEntity entity = optional.get();
            CategorySingleResponse singleResponse = new CategorySingleResponse();
            mapper.map(entity, singleResponse);
            responseList.add(singleResponse);
        }

        response.setCategories(responseList);
        return response;
    }
}
