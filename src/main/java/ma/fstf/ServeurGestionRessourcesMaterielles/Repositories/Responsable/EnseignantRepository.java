package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Ensiegnant, Integer> {

//    @Query(nativeQuery = true , value =
//            "SELECT e.* FROM Ensiegnant e INNER JOIN Materiel m ON m.ensiegnant_id = e.id"
//    )
//    List<Ensiegnant> findEnsiegnantByMateriel();
List<Ensiegnant> findEnsiegnantByDepartementEquals(String departement);
/**************************************/
@Query(nativeQuery = true,value="select ens.departement,user.* from ensiegnant ens,user user, materiel m where user.id=ens.id and ens.id=m.ensiegnant_id and m.id= :id ")
Ensiegnant getEnsiegnant(@Param("id") Integer id);





}
