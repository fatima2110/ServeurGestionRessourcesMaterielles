package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Constat;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstatRepository extends JpaRepository<Constat,Integer> {
    List<Constat> findAllBySendIsTrue();
    List<Constat> findAllBySendIsFalse();
    List<Constat> findAllByPanne(Panne panne);
    Constat findConstatByPanne(Panne panne);
}
