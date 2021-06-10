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
    Optional<User> findAllByUsernameIsContaining(String username);
    Optional<User> findAllByFirstnameIsContaining(String firstname);
    Optional<User> findAllByLastnameIsContaining(String lastname);
    Optional<User> findAllByEmailIsContaining(String email);
    }
