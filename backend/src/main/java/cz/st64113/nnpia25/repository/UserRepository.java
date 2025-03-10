package cz.st64113.nnpia25.repository;

import cz.st64113.nnpia25.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmail(String email);
    boolean existsByIdOrEmail(Long id, String email);
}
