package ma.fstf.ServeurGestionRessourcesMaterielles.Repositories;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface MessageReposetory extends JpaRepository<Message,Integer> {
    ArrayList<Message> findAllByRecepteur();

}
