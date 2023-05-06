package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Affectation;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Integer> {
    List<Affectation> findAffectationByEnsiegnant(Ensiegnant ensiegnant);

}
