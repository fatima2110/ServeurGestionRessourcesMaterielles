package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur;



import ma.fstf.ServeurGestionRessourcesMaterielles.Models.AppelOffre;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Repository
public interface Appel_OffreRep extends JpaRepository<AppelOffre,Integer> {

    List<AppelOffre> findAll();

    AppelOffre getById(Integer id);
}
