package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel_Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Materiel_PropositionReposetory extends JpaRepository<Materiel_Proposition,Integer> {

   List<Materiel_Proposition> findByProposition(Proposition prop);

   Materiel_Proposition save(Materiel_Proposition MatProp);

   //@Override
   //void deleteByMaterielAndAndMarqueAndPrix();
}
