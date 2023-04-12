package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Message;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MessageReposetory extends JpaRepository<Message,Integer> {
    //List<Message> findAllByRecepteur();

}
