package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Fournisseur.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUserByLogin(String login){
        Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }
}
