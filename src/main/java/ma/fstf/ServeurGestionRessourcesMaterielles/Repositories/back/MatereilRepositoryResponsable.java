package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.back;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatereilRepositoryResponsable extends JpaRepository<Materiel,Integer> {
    List<Materiel> findMaterielByEnsiegnant(Ensiegnant ens);
//    @Query(nativeQuery = true , value =
//            "SELECT m.id,m.marque FROM Materiel m INNER JOIN Ensiegnant e ON m.ensiegnant_id = e.id WHERE e.departement = :departement AND m.appel_offre_id IS NULL"
//    )

    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNull(Ensiegnant ens);

    Materiel findMaterielById(Integer idMateriel);
    /*************************************************/
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE materiel SET appel_offre_id = :idoffre WHERE materiel.id = :id")
    void update(@Param("id") Integer id, @Param("idoffre") Integer idoffre);
    Materiel findMaterielByid(Integer id);



}
