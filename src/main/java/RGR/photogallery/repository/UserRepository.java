package RGR.photogallery.repository;

import RGR.photogallery.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    long countByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);
    User findByEnabled(String code);
    Optional<User> findById(Long id);
    User findByUsername(String username);
    List<User> findByUsernameIsContaining(String username);
    }
