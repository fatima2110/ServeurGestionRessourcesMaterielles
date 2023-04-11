package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
    User findUserById(int id);
}
