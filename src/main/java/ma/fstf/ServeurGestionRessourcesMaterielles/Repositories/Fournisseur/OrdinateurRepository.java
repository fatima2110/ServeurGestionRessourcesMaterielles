package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinateurRepository extends JpaRepository<Ordinateur,Integer> {
    Ordinateur findOrdinateurById(int id);
    Ordinateur findOrdinateurByEnsiegnantId(int id);
}
