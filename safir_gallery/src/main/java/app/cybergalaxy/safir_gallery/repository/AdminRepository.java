package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
}
