package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Message;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void vue(int id_msg){
        Message message = messageRepository.findMessageByid(id_msg);
        if( message != null){
            message.setVue(true);
            messageRepository.save(message);
        }
    }
}
