package ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.FournisseurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MessageRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.back.OrdinateurRepositoryResponsable;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.PropositionRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.back.ImprimanteRepositoryResponsable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropositionService {
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MaterielPropositionRepository materielPropositionRepository;
    @Autowired
    private OrdinateurRepositoryResponsable ordinateurRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ImprimanteRepositoryResponsable imprimanteRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private PropositionRepository propositionRepository;

    public List<Fournisseur> getFournisseurs() throws Exception {
        List<Fournisseur> list = new ArrayList<>();
        List<Proposition> propositions = propositionRepository.findAll();
        for (int i = 0; i < propositions.size(); i++) {
            if (propositions.get(i).getStatus().equals(StatusPropo.En_cour)) {
                Fournisseur fournisseur = propositions.get(i).getFournisseur();
//            FournisseurDto fournisseurDto = FournisseurDto.builder()
//                    .nom_societe(fournisseur.getNom_societe())
//                    .adresse(fournisseur.getAdresse())
//                    .email(fournisseur.getEmail())
//                    .id(fournisseur.getId())
//                    .build();
//            list.add(fournisseurDto);
                list.add(fournisseur);

            }
        }


        return list;
    }

    public List<PropositionOrdinateurDTO> getOrdinateurProposition(int id) throws Exception {
        List<PropositionOrdinateurDTO> list = new ArrayList<>();

        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(fournisseur);
        System.out.println("list proposition" + proposition);
        for (int j = 0; j < proposition.size(); j++) {
            if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {

                List<Materiel_Proposition> materiel_Proposition = materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));
                System.out.println("material de proposition" + materiel_Proposition);

                System.out.println(materiel_Proposition.size());

                for (int i = 0; i < materiel_Proposition.size(); i++) {

                    Materiel mat = matereilRepository.findMaterielById(materiel_Proposition.get(i).getMateriel().getId());

                    Ordinateur ordinateur = ordinateurRepository.findOrdinateurById(mat.getId());
                    System.out.println("material" + mat);
                    if (ordinateur == null)
                        continue;
                    System.out.println(ordinateur);
                    PropositionOrdinateurDTO propositionOrdinateurDTO = PropositionOrdinateurDTO.builder()
                            .cpu(ordinateur.getCpu())
                            .ram(ordinateur.getRam())
                            .ecran(ordinateur.getEcran())
                            .disque(ordinateur.getDisque())
                            .marque(materiel_Proposition.get(i).getMarque())
                            .prix(materiel_Proposition.get(i).getPrix())
                            .build();
                    list.add(propositionOrdinateurDTO);
                    System.out.println("list dans boucle" + list);
                    System.out.println(propositionOrdinateurDTO.getCpu());
                    System.out.println(list.size());
                }
            }
        }

        return list;
    }

    public List<PropositionImprimenteDTO> getImprimenteProposition(int id) throws Exception {
        List<PropositionImprimenteDTO> list1 = new ArrayList<>();

        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {

                List<Materiel_Proposition> materiel_Proposition = materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));

                for (int i = 0; i < materiel_Proposition.size(); i++) {


                    Materiel mat = matereilRepository.findMaterielById(materiel_Proposition.get(i).getMateriel().getId());
                    Imprimente imprimente = imprimanteRepository.findImprimenteById(mat.getId());
                    if (imprimente == null)
                        continue;
                    System.out.println(imprimente.getResolution());
                    PropositionImprimenteDTO propositionImprimenteDTO = PropositionImprimenteDTO.builder()
                            .vitesse(imprimente.getVitesse())
                            .resolution(imprimente.getResolution())
                            .marque(materiel_Proposition.get(i).getMarque())
                            .prix(materiel_Proposition.get(i).getPrix())

                            .build();
                    list1.add(propositionImprimenteDTO);
                }
            }
        }


        return list1;
    }

    public void deleteFournisseur(Integer id) {
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);

        List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            List<Materiel_Proposition> materiel_Proposition = materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));

            for (int i = 0; i < materiel_Proposition.size(); i++) {

                System.out.println(materiel_Proposition.get(i).getId());
                materielPropositionRepository.deleteById(materiel_Proposition.get(i).getId());
            }


            propositionRepository.deleteById(proposition.get(j).getId());
        }
        fournisseur.setListeNoir(true);
        fournisseurRepository.save(fournisseur);
        //  fournisseurRepository.deleteById(fournisseur.getId());

    }

    public void accepterProposition(Integer id) throws Exception {

        LocalDate lt = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = lt.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);

        List<Fournisseur> Fournisseurs = getFournisseurs();
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        for (int i = 0; i < Fournisseurs.size(); i++) {
            if (fournisseur.equals(Fournisseurs.get(i))) {
                List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(fournisseur);
                for (int j = 0; j < proposition.size(); j++) {
                    if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {
                        proposition.get(j).setStatus(StatusPropo.accepte);
                        propositionRepository.save(proposition.get(j));
                    }


                }
            } else {
                List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(Fournisseurs.get(i));
                for (int j = 0; j < proposition.size(); j++) {
                    if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {
                        proposition.get(j).setStatus(StatusPropo.refuse);
                        propositionRepository.save(proposition.get(j));
                        MessageDTO messageDTO = new MessageDTO();
                        messageDTO.setIdFournisseur(Fournisseurs.get(i).getId());
                        messageDTO.setMessage(NOTIFICATION.Refus.getValue());
                        messageDTO.setDate(parsedDate);
                        envoyerMotif(messageDTO);
                    }

                }
            }
        }
    }

    public void rejeterProposition(Integer id) {
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition = propositionRepository.findPropositionByFournisseurOrderByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {
                proposition.get(j).setStatus(StatusPropo.refuse);
                propositionRepository.save(proposition.get(j));
            }
        }

    }

    public List<Fournisseur> getFournisseurListeNoir() {
        List<Fournisseur> fournisseurs = fournisseurRepository.findFournisseurByListeNoirTrue();
        for (int i = 0; i < fournisseurs.size(); i++) {
            System.out.println(fournisseurs.get(i).getId());
        }

        return fournisseurs;
    }

    public void retirerFournisseur(Integer id) {
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        fournisseur.setListeNoir(false);
        fournisseurRepository.save(fournisseur);


    }

    public void envoyerMotif(MessageDTO msg) {

        LocalDate lt = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = lt.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        Message m = new Message();
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(msg.getIdFournisseur());

        User respo = userRepository.findUserByRoleEquals(Role.valueOf("RESPONSABLE"));
        m.setFournisseur(fournisseur);
        m.setMessage(msg.getMessage());
        m.setEmetteur(respo);
        m.setDate(parsedDate);
        messageRepository.save(m);

    }

//    public void EnvoyerNotifAccept() {
//
//
//        Message m=new Message();
//
//        m.setMessage(msg.getMessage());
//        messageRepository.save(m);
//
//    }

    /********************Recuperer les messages******************************/
    public List<MessageDTO> getMessage(Integer id) {

        List<Message> l = messageRepository.findAllMessageByid(id);
        List<MessageDTO> mes = new ArrayList<>();

        for (Message m : l) {
            boolean exsist = false;
            int idrec = 0;
            if (m.getRecepteur() != null) {
                idrec = m.getRecepteur().getId();
            } else {
                idrec = m.getFournisseur().getId();
                System.err.println("iciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + idrec);
                if (m.getFournisseur().getGerant() != null)
                    exsist = true;


            }
            MessageDTO mdto = MessageDTO.builder()
                    .message(m.getMessage())
                    .emteur(m.getEmetteur().getNom() + " " + m.getEmetteur().getPrenom())
                    .idem(m.getEmetteur().getId())
                    .id(m.getId())
                    .idrec(idrec)
                    .exsist(exsist)
                    .date(m.getDate())
                    .build();
            mes.add(mdto);
        }
        return mes;
    }

    public void suprimerMessage(Integer id) {
        Message m = messageRepository.findMessageByid(id);
        messageRepository.delete(m);

    }

    public void AjouterMessage(MessageDTO M) {
        LocalDate lt = LocalDate.now();

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = lt.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        System.out.println("date " + lt);
        Message m = new Message();
        m.setMessage(M.getMessage());
        User em = userRepository.findUserByid(M.getIdem());
        User rec = userRepository.findUserByid(M.getIdrec());
        m.setEmetteur(em);
        m.setRecepteur(rec);
        m.setDate(parsedDate);
        messageRepository.save(m);

    }

    public Integer NombreMessage(Integer id) {
        return messageRepository.numbermessage(id);
    }

    public void modfierVue(List<MessageDTO> lst) {
        for (MessageDTO m : lst) {
            Message msg = messageRepository.findMessageByid(m.getId());
            msg.setVue(true);
            messageRepository.save(msg);
        }
    }

    public void InfoFournisseur(Fournisseur f) {
        fournisseurRepository.save(f);
    }

    public Fournisseur InformationFournisseur(Integer id) {
        return fournisseurRepository.findFournisseurById(id);
    }

    public void notifier(int id) {
        Ensiegnant ensiegnant = enseignantRepository.findById(id).get();
        List<Ensiegnant> ensiegnants = enseignantRepository.findEnsiegnantsByDepartement(ensiegnant.getDepartement());
        for (int i = 0; i < ensiegnants.size(); i++) {
            if (ensiegnants.get(i).getId() == id)
                continue;
            LocalDate lt = LocalDate.now();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = lt.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);
            Message message = Message.builder().emetteur(userRepository.findUserById(id))
                    .recepteur(ensiegnants.get(i))
                    .date(parsedDate)
                    .message(NOTIFICATION.AjoutBesoin.getValue())
                    .build();
            messageRepository.save(message);
        }
    }

}

