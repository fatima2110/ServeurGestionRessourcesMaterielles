package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Ensiegnant, Integer> {
    @Override
    List<Ensiegnant> findAll();

    List<Ensiegnant> findEnsiegnantByDepartementEquals(String deparetemt);

    Ensiegnant getById(Integer id);


}
