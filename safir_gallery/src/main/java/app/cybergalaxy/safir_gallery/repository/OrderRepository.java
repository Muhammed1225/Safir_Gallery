package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
