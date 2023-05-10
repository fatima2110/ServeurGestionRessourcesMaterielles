package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.AppelOffre;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.MaterielState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatereilRepository extends JpaRepository<Materiel,Integer> {
    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNotNull(Ensiegnant ens);
    Materiel findMaterielById(int id);
    Materiel findMaterielByCodeBarre(String s);
    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(Ensiegnant ens);
    Materiel findMaterielByCodeBarreAndMaterielState(String code_barre, MaterielState state);
    List<Materiel> findMaterielByEnsiegnant(Ensiegnant ensiegnant);

    List<Materiel> findMaterielByAppelOffre(AppelOffre app);
    List<Materiel> findAllByCodeBarreIsNotNull();
    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNull(Ensiegnant ens);

    Materiel findMaterielById(Integer idMateriel);
    /*************************************************/
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE materiel SET appel_offre_id = :idoffre WHERE materiel.id = :id")
    void update(@Param("id") Integer id, @Param("idoffre") Integer idoffre);
    Materiel findMaterielByid(Integer id);

    List<Materiel> findAllByAppelOffre(AppelOffre appelOffre);

}
