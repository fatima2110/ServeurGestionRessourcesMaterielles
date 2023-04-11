package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatereilRepository extends JpaRepository<Materiel,Integer> {
    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNotNull(Ensiegnant ens);
    Materiel findMaterielById(int id);
    List<Materiel> findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(Ensiegnant ens);

}
