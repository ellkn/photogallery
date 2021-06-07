package RGR.photogallery.repository;

import RGR.photogallery.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    long countByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabledTrue(String email);

}
