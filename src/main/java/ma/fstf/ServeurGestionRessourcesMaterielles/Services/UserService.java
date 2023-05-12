package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PassWordDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.UserDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Utils.UserNotFoundException;
import ma.fstf.ServeurGestionRessourcesMaterielles.Utils.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AffectationRepository affectationRepository;

    @Transactional(readOnly = true)
    public User getUserByLogin(String login){
        Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findNonEnseignants();
        return getUserDTOS(users);
    }
    public List<UserDTO> getEnseignants(String departement) {
        List<Ensiegnant> ensiegnants = enseignantRepository.findEnsiegnantByDepartementEquals(departement);
        List<Integer> ids = new ArrayList<>();
        for (int i =0; i<ensiegnants.size();i++){
            ids.add(ensiegnants.get(i).getId());
        }
        List<User> users = new ArrayList<>();
        for(int i=0;i<ids.size();i++){
            users.add(userRepository.findUserById(ids.get(i)));
        }
        return getUserDTOS(users);
    }

    private List<UserDTO> getUserDTOS(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for(int i= 0;i<users.size();i++){
            UserDTO userDTO = UserDTO.builder()
                    .login(users.get(i).getLogin())
                    .id(users.get(i).getId())
                    .nom(users.get(i).getNom())
                    .prenom(users.get(i).getPrenom())
                    .role(users.get(i).getRole())
                    .telephone(users.get(i).getTelephone())
                    .build();
            Optional<Ensiegnant> enseignantOptional = enseignantRepository.findById(users.get(i).getId());
            if (enseignantOptional.isPresent()) {
                Ensiegnant enseignant = enseignantOptional.get();
                userDTO.setDepartement(enseignant.getDepartement());
            }
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
    @Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        tokenRepository.deleteTokenByUser(user);
        System.err.println("token deleted");
        if(user.getRole().name().equals("ENSEIGNANT") || user.getRole().name().equals("CHEF_DEPARTEMENT")){
            Ensiegnant ensiegnant = enseignantRepository.findById(user.getId()).get();
            List<Materiel> materiels = matereilRepository.findMaterielByEnsiegnant(ensiegnant);
            List<Message> messages = messageRepository.findAllMessageByEmetteurOrRecepteurOrFournisseur(user.getId());
            List<Affectation> affectations = affectationRepository.findAffectationByEnsiegnant(ensiegnant);
            //affectationRepository.deleteAll(affectations);
            messageRepository.deleteAll(messages);

            for(int i = 0;i<affectations.size();i++){
                affectations.get(i).setEnsiegnant(null);
                affectationRepository.save(affectations.get(i));
            }

            for(int i = 0;i<materiels.size();i++){
               Materiel materiel = matereilRepository.findMaterielById(materiels.get(i).getId());
               materiel.setEnsiegnant(null);
               matereilRepository.save(materiel);
           }
        }
        userRepository.deleteById(id);
    }

    public void editUser(UserDTO userDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        Role role = tokenRepository.findTokenByToken(jwt).getUser().getRole();
        User user = userRepository.findUserById(userDTO.getId());
        // le chef de departement a le droit de modifier le compte
        if(role.name().equals("CHEF_DEPARTEMENT")){
            setAccount(userDTO, user);
        }
        // pour les autres utilisateurs on doit verifier le mot de passe
        else{
            var oldPass = user.getPass();
            var newPass = userDTO.getPassword();
            if (passwordEncoder.matches(newPass, oldPass)){
                setAccount(userDTO, user);
            }
            else
                throw new WrongPasswordException();
        }
    }

    public UserDTO getUser(Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        UserDTO userDTO = UserDTO.builder()
                .photo(user.getPhoto_profile())
                .login(user.getLogin())
                .id(user.getId())
                .telephone(user.getTelephone())
                .role(user.getRole())
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .build();
        if(userDTO.getRole().name().equals("ENSEIGNANT") || userDTO.getRole().name().equals("CHEF_DEPARTEMENT")){
            Ensiegnant ensiegnant = enseignantRepository.findById(id).get();
            if(ensiegnant != null){
                userDTO.setDepartement(ensiegnant.getDepartement());
            }
        }
        return userDTO;
    }

    private void setAccount(UserDTO userDTO, User user) {
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setRole(userDTO.getRole());
        user.setTelephone(userDTO.getTelephone());
        user.setLogin(userDTO.getLogin());
        user.setPass(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    public void editProfile(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setTelephone(userDTO.getTelephone());
        user.setLogin(userDTO.getLogin());
        System.err.println(userDTO.getPhoto());
        user.setPhoto_profile(userDTO.getPhoto());
        System.err.println(user.getPhoto_profile());
        userRepository.save(user);
    }

    public void changePassword(PassWordDTO passWordDTO, @NonNull HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User user = tokenRepository.findTokenByToken(jwt).getUser();
        if(user!=null){
            var oldPass = user.getPass();
            var oldPassFromRequest = passWordDTO.getCurrentPassword();
            if (passwordEncoder.matches(oldPassFromRequest, oldPass)){
                user.setPass(passwordEncoder.encode(passWordDTO.getNewPassword()));
            }
            else{
                throw new WrongPasswordException();
            }
        }
        else
            throw new UserNotFoundException();
    }

    public List<UserDTO> getChefs() {
        List<User> users = userRepository.findUsersByRole(Role.CHEF_DEPARTEMENT);
        return getUserDTOS(users);
    }

    public List<UserDTO> getTechniciens() {
        List<User> users = userRepository.findUsersByRole(Role.TECHNICIEN);
        return getUserDTOS(users);
    }
}
