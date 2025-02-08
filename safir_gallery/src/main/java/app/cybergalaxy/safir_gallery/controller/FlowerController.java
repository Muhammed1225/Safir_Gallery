package app.cybergalaxy.safir_gallery.controller;

import app.cybergalaxy.safir_gallery.dto.response.FlowerResponse;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.service.impl.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    @Autowired
    private FlowerService service;

    @GetMapping("/category/{categoryId}")
    public FlowerResponse findByCategory(@PathVariable Integer categoryId) {
        return service.findByCategory(categoryId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public FlowerResponse findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public FlowerResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> add(
            @RequestParam("text") String text,
            @RequestParam("categoryId") Integer categoryId,
            @RequestPart("files") List<MultipartFile> files
    ) {
        if (files.isEmpty()) {
            throw new MyException("File is required!");
        }
        service.add(text, categoryId, files);
        return ResponseEntity.ok("The flower was added");
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> update(
            @RequestParam("id") Integer id,
            @RequestParam("text") String text,
            @RequestParam("categoryId") Integer categoryId,
            @RequestPart("files") List<MultipartFile> files
    ) {
        if (files.isEmpty()) {
            throw new MyException("File is required!");
        }
        service.update(id, text, categoryId, files);
        return ResponseEntity.ok("The flower was updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("The flower was deleted");
    }

}
