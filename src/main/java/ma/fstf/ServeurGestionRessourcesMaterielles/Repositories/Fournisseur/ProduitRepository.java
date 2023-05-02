package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}
