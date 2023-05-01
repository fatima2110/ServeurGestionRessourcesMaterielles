package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    @Query("select u.role from User u where u.login=:login")
    String findRoleByLogin(String login);
    @Query("select u.id from User u where u.login=:login")
    Integer findIdByLogin(String login);
    User findUserById(int id);

    @Query("SELECT u FROM User u LEFT JOIN Ensiegnant e ON u.id = e.id WHERE e.id IS NULL")
    List<User> findNonEnseignants();
}
