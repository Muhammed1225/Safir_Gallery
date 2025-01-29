package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Integer> {

    @Query(value = "select * from flowers where category_id = ?1", nativeQuery = true)
    List<FlowerEntity> findByCategory(Integer categoryId);

}
