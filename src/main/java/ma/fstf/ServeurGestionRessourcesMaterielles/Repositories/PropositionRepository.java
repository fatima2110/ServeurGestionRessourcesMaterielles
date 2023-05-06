package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PropositionRepository extends JpaRepository<Proposition,Integer> {
    List<Proposition> findPropositionByFournisseur(Fournisseur fournisseur);
    Proposition findPropositionById(Integer id);
    @Query(nativeQuery = true,value = "" +
            "select p.*,mat.* from proposition p,materiel_proposition mat where  p.id=mat.proposition_id and mat.materiel_id= :id group by p.id")
    Proposition getPropostion(@Param("id") Integer id);

    @Query("select p from Proposition  p where p.id =:id")
    Proposition getById(@Param("id") Integer id);
    Proposition save(Proposition prop);
    void  deleteById(Integer propid);

}
