package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
