package app.cybergalaxy.safir_gallery.service.impl;

import app.cybergalaxy.safir_gallery.dto.response.FlowerResponse;
import app.cybergalaxy.safir_gallery.dto.response.FlowerSingleResponse;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.model.CategoryEntity;
import app.cybergalaxy.safir_gallery.model.FlowerEntity;
import app.cybergalaxy.safir_gallery.repository.CategoryRepository;
import app.cybergalaxy.safir_gallery.repository.FlowerRepository;
import app.cybergalaxy.safir_gallery.service.inter.FlowerInter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlowerService implements FlowerInter {

    @Autowired
    private FlowerRepository repository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void add(String text, Integer categoryId, List<MultipartFile> files) {
        FlowerEntity flower = new FlowerEntity();

        Optional<CategoryEntity> optional = categoryRepository.findById(categoryId);
        if (optional.isPresent()) {
            List<String> paths = new ArrayList<>();

            for (MultipartFile f : files) {
                String path = fileUploadService.uploadFile(f);
                paths.add(path);
            }

            flower.setCategoryId(categoryId);
            flower.setText(text);
            flower.setImages(paths);
            repository.save(flower);
        } else {
            throw new MyException("The category wasn't found");
        }
    }

    @Override
    public FlowerResponse findByCategory(Integer categoryId) {
        FlowerResponse response = new FlowerResponse();
        List<FlowerEntity> entities = repository.findByCategory(categoryId);
        List<FlowerSingleResponse> responseList = new ArrayList<>();

        for (FlowerEntity e : entities) {
            FlowerSingleResponse singleResponse = new FlowerSingleResponse();
            mapper.map(e, singleResponse);
            responseList.add(singleResponse);
        }

        response.setFlowers(responseList);
        return response;
    }

    @Override
    public void update(Integer id, String text, Integer categoryId, List<MultipartFile> files) {
        Optional<FlowerEntity> optional = repository.findById(id);

        if (optional.isPresent()) {
            FlowerEntity flower = optional.get();
            Optional<CategoryEntity> optionalCategory = categoryRepository.findById(categoryId);

            if (optionalCategory.isPresent()) {
                List<String> paths = new ArrayList<>();

                for (MultipartFile f : files) {
                    String path = fileUploadService.uploadFile(f);
                    paths.add(path);
                }

                flower.setCategoryId(categoryId);
                flower.setText(text);
                flower.setImages(paths);
                repository.save(flower);
            } else {
                throw new MyException("The category wasn't found");
            }
        } else {
            throw new MyException("The id wasn't found");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<FlowerEntity> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new MyException("The flower was deleted");
        }
    }
}
