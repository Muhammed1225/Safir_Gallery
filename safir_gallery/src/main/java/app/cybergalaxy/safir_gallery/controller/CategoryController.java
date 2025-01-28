package app.cybergalaxy.safir_gallery.controller;

import app.cybergalaxy.safir_gallery.dto.request.CategoryAddRequest;
import app.cybergalaxy.safir_gallery.dto.request.CategoryUpdateRequest;
import app.cybergalaxy.safir_gallery.dto.response.CategoryResponse;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.service.impl.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public CategoryResponse findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> add(@Valid @RequestBody CategoryAddRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("There is a problem in sent data!", br);
        }
        service.add(request);
        return ResponseEntity.ok("The category was added");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> update(@Valid @RequestBody CategoryUpdateRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("There is a problem in sent data!", br);
        }
        service.update(request);
        return ResponseEntity.ok("The category was updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("The category was deleted");
    }

}
