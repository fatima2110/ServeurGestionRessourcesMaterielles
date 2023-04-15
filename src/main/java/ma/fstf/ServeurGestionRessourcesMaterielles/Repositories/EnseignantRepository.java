package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Ensiegnant, Integer> {

//    @Query(nativeQuery = true , value =
//            "SELECT e.* FROM Ensiegnant e INNER JOIN Materiel m ON m.ensiegnant_id = e.id"
//    )
//    List<Ensiegnant> findEnsiegnantByMateriel();
List<Ensiegnant> findEnsiegnantByDepartementEquals(String departement);

}
