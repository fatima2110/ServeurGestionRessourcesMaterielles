package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Representations.RepresentationMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatereilRepository extends JpaRepository<Materiel,Integer> {


    @Query("SELECT m.marque,m.code_barre,m.date_livraison FROM Materiel m WHERE m.ensiegnant = :id")
    List<Materiel> getMatereilsEnsiegnant(int id);
}
