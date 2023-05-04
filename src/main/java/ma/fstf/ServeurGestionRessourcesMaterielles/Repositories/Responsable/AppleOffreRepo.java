package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppleOffreRepo extends JpaRepository<AppelOffre,Integer> {
    @Query(nativeQuery = true,value = "SELECT *" +
            "FROM appel_offre " +
            "WHERE id = (SELECT MAX(id) FROM appel_offre)")
    AppelOffre getAppleOffre();


}
