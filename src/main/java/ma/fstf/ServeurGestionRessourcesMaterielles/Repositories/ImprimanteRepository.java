package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImprimanteRepository extends JpaRepository<Imprimente,Integer> {

//    @Query(nativeQuery = true , value =
//            "SELECT i.resolution,i.vitesse FROM Imprimente i INNER JOIN Ensiegnant e ON i.ensiegnant_id = e.id " +
//                    "WHERE e.departement = :departement " +
//                    "AND i.appel_offre_id IS NULL"
//    )
//    List<Imprimente> findImprimenteByDepartement(String departement);

    Imprimente findImprimenteById(Integer id);
}
