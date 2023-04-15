package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdinateurRepository extends JpaRepository<Ordinateur,Integer> {


//@Query(nativeQuery = true , value =
//        "SELECT o.cpu,o.disque,o.ecran,o.ram FROM Ordinateur o INNER JOIN Ensiegnant e ON o.ensiegnant_id = e.id " +
//                "WHERE e.departement = :departement " +
//                "AND o.appel_offre_id IS NULL"
//)
//
//List<Ordinateur> findOrdinateurByDepartement(String departement);
 Ordinateur   findOrdinateurById(Integer id);

}
