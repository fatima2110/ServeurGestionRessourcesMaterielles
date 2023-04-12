package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition,Integer>{

    @Query("select p from Proposition  p where p.id =:id")
    Proposition getById(@Param("id") Integer id);
    Proposition save(Proposition prop);


    List<Proposition> findPropositionByFournisseur(Fournisseur fou);

    void  deleteById(Integer propid);


}
