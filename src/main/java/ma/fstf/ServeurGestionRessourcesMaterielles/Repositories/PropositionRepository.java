package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition,Integer>{

    default Proposition getById(Integer id) {
        return null;
    }
}
