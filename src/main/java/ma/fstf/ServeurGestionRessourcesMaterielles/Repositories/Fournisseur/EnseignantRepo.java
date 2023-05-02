package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.AppelOffre;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnseignantRepo extends JpaRepository<Ensiegnant,Integer> {
    Ensiegnant getById(Integer id);
    List<Ensiegnant> findAll();
}
