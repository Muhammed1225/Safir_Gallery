package app.cybergalaxy.safir_gallery.repository;

import app.cybergalaxy.safir_gallery.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
}
