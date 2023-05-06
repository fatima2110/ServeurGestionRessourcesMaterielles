package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImprimanteRepository extends JpaRepository<Imprimente,Integer> {
    Imprimente findImprimenteById(int id);
    //    @Query(nativeQuery = true , value =
//            "SELECT i.resolution,i.vitesse FROM Imprimente i INNER JOIN Ensiegnant e ON i.ensiegnant_id = e.id " +
//                    "WHERE e.departement = :departement " +
//                    "AND i.appel_offre_id IS NULL"
//    )
//    List<Imprimente> findImprimenteByDepartement(String departement);

    Imprimente findImprimenteById(Integer id);
    /****************************************************/
    @Query(nativeQuery = true,value = "" +
            "select im.*,m.code_barre,m.date_livraison,verifie,duree_garentie,marque,prix,appel_offre_id,ensiegnant_id, panne, materiel_state from materiel m,imprimente im  where im.id=m.id and m.verifie=1 and m.appel_offre_id IS null  GROUP BY im.id")
    List<Imprimente> getBesoinIM();
    @Query(nativeQuery = true,value = "" +
            "select im.*,m.code_barre,m.date_livraison,verifie,duree_garentie,matp.marque," +
            "   matp.prix,appel_offre_id,ensiegnant_id, panne, materiel_state" +
            "  from imprimente im,materiel m,materiel_proposition matp,proposition p " +
            "  WHERE im.id=m.id and m.id=matp.materiel_id and matp.proposition_id=p.id and status='accepte'  and m.code_barre is  null")
    List<Imprimente> getMaterialIM();
    @Query(nativeQuery = true,value = "" +
            "select im.*,m.code_barre,m.date_livraison,duree_garentie," +
            "              appel_offre_id,ensiegnant_id,verifie,panne, materiel_state ,m.prix,m.marque" +
            "             from imprimente im,materiel m " +
            "             WHERE im.id=m.id  and m.code_barre is not  null")
    List<Imprimente> getResourceIM();
    Imprimente findImprimenteByid(Integer id);


}
