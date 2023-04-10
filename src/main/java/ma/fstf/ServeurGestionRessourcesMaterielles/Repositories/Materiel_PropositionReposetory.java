package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel_Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Materiel_PropositionReposetory extends JpaRepository<Materiel_Proposition,Integer> {


}
