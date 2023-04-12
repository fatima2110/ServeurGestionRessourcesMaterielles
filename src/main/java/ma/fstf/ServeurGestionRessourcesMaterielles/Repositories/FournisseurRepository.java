package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    default Fournisseur findByNom_societeAndPass(Fournisseur fou) {
        return null;
    }

    @Query("select f from Fournisseur f where f.id = :id")
    Fournisseur findByid(@Param("id") Integer id);


}
