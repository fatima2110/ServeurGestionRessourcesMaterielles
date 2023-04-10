package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    default Fournisseur findByNom_societeAndPass() {
        return null;
    }


}
