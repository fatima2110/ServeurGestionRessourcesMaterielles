package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Integer> {
}
