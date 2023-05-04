package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinateurRepositoryResponsable extends JpaRepository<Ordinateur,Integer> {


//@Query(nativeQuery = true , value =
//        "SELECT o.cpu,o.disque,o.ecran,o.ram FROM Ordinateur o INNER JOIN Ensiegnant e ON o.ensiegnant_id = e.id " +
//                "WHERE e.departement = :departement " +
//                "AND o.appel_offre_id IS NULL"
//)
//
//List<Ordinateur> findOrdinateurByDepartement(String departement);
 Ordinateur   findOrdinateurById(Integer id);
 /*******************************************************************/
 @Query(nativeQuery = true,value = "" +
         "select im.*,m.code_barre,verifie,m.date_livraison,duree_garentie,marque,prix,appel_offre_id,ensiegnant_id,materiel_state, panne from materiel m,ordinateur im where im.id=m.id and m.verifie=1 and m.appel_offre_id IS null GROUP BY im.id")
 List<Ordinateur> getBesoinOrd();
 @Query(nativeQuery = true,value = "" +
         "select im.*,m.code_barre,verifie,materiel_state, panne, m.date_livraison,duree_garentie,matp.marque," +
         "              matp.prix,appel_offre_id,ensiegnant_id " +
         "             from ordinateur im,materiel m,materiel_proposition matp,proposition p " +
         "            WHERE im.id=m.id and m.id=matp.materiel_id and matp.proposition_id=p.id and status='accepte' and m.code_barre is  null")
 List<Ordinateur> getMaterialord();
 @Query(nativeQuery = true,value = "" +
         "select im.*,verifie,m.code_barre,m.date_livraison,duree_garentie," +
         "              appel_offre_id,ensiegnant_id,panne, materiel_state,m.prix,m.marque" +
         "             from ordinateur im,materiel m " +
         "             WHERE im.id=m.id  and m.code_barre is not  null")
 List<Ordinateur> getResourceOR();

 Ordinateur findOrdinateurByid(Integer id);

}
