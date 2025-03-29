package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.FlowerViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlowerViewRepository extends JpaRepository<FlowerViewEntity, Integer> {

    @Query(value = "select f.id, f.text, f.price, f.images, c.name as category, c.id as category_id from flowers f join categories c " +
            "on f.category_id = c.id", nativeQuery = true)
    List<FlowerViewEntity> findAllFlowers();

    @Query(value = "select f.id, f.text, f.price, f.images, c.name as category, c.id as category_id from flowers f join categories c " +
            "on f.category_id = c.id and f.id = ?1", nativeQuery = true)
    Optional<FlowerViewEntity> findFlowerById(Integer id);

}
