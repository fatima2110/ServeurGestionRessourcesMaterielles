package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(nativeQuery = true,value = "select * from message where recepteur_id= :id  or fournisseur_id= :id ORDER BY date desc")
    List<Message> findAllMessageByid(@Param("id") Integer id);
Message findMessageByid(Integer id);

@Query(nativeQuery = true,value = "select count(*) from message where vue=0 and recepteur_id= :id or fournisseur_id= :id")
    Integer numbermessage(@Param("id") Integer id);

}
