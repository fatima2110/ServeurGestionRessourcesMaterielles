package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    Fournisseur findFournisseurById(Integer id);

    void deleteById(Integer id);
    List<Fournisseur> findFournisseurByListeNoirTrue();
    /****************************************/
    @Query(nativeQuery = true,value = "" +
            "select f.* from fournisseur f,materiel_proposition m,proposition p " +
            "where m.materiel_id= :id and m.proposition_id=p.id and f.id=p.fournisseur_id")
    Fournisseur getFournisseur(@Param("id") Integer id);

    default Fournisseur findByNom_societeAndPass(Fournisseur fou) {
        return null;
    }

    @Query("select f from Fournisseur f where f.id = :id")
    Fournisseur findByid(@Param("id") Integer id);
}
