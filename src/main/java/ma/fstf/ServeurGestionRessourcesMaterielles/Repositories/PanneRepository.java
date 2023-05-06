package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.MaterielState;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Panne;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanneRepository extends JpaRepository<Panne, Integer> {
    Panne findByMateriel(Materiel materiel);
    List<Panne> findAllByTreatedIsFalse();

    Panne findPanneByMaterielAndTreatedIsFalse(Materiel materiel);
    List<Panne> findPanneByMaterielAndTreatedIsTrueOrderByDatePanneDesc(Materiel materiel);
    Panne findPanneByTechnicien(User user);
    List<Panne> findAllByTechnicien(User user);

}
