package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImprimanteRepository extends JpaRepository<Imprimente,Integer> {
    Imprimente findImprimenteById(int id);
}
