package ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
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
            if (propositions.get(i).getStatus().equals(StatusPropo.En_cour)){
            Fournisseur fournisseur = propositions.get(i).getFournisseur();
//            FournisseurDto fournisseurDto = FournisseurDto.builder()
//                    .nom_societe(fournisseur.getNom_societe())
//                    .adresse(fournisseur.getAdresse())
//                    .email(fournisseur.getEmail())
//                    .id(fournisseur.getId())
//                    .build();
//            list.add(fournisseurDto);
                list.add(fournisseur);

        }}




        return list;
    }
    public List<PropositionOrdinateurDTO> getOrdinateurProposition(int id) throws Exception {
         List<PropositionOrdinateurDTO> list = new ArrayList<>();

        Fournisseur fournisseur=fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition=propositionRepository.findPropositionByFournisseur(fournisseur);
        System.out.println("list proposition"+proposition);
        for (int j = 0; j < proposition.size(); j++) {
        if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)){

            List<Materiel_Proposition> materiel_Proposition = materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));
           System.out.println("material de proposition"+materiel_Proposition);

            System.out.println(materiel_Proposition.size());

            for (int i = 0; i < materiel_Proposition.size(); i++) {

                Materiel mat = matereilRepository.findMaterielById(materiel_Proposition.get(i).getMateriel().getId());

                Ordinateur ordinateur = ordinateurRepository.findOrdinateurById(mat.getId());
System.out.println("material"+mat);
if(ordinateur ==null)
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
                System.out.println("list dans boucle"+list);
                System.out.println(propositionOrdinateurDTO.getCpu());System.out.println(list.size());
            }
        }
        }

        return list;
    }
    public List<PropositionImprimenteDTO> getImprimenteProposition(int id) throws Exception {
        List<PropositionImprimenteDTO> list1 = new ArrayList<>();

        Fournisseur fournisseur=fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition=propositionRepository.findPropositionByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)){

                List<Materiel_Proposition> materiel_Proposition=materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));

                for (int i=0;i<materiel_Proposition.size();i++){


            Materiel mat=matereilRepository.findMaterielById(materiel_Proposition.get(i).getMateriel().getId());
            Imprimente imprimente=imprimanteRepository.findImprimenteById(mat.getId());
            if(imprimente==null)
                continue;
                    System.out.println(imprimente.getResolution());
            PropositionImprimenteDTO propositionImprimenteDTO= PropositionImprimenteDTO.builder()
                    .vitesse(imprimente.getVitesse())
                    .resolution(imprimente.getResolution())
                    .marque(materiel_Proposition.get(i).getMarque())
                    .prix(materiel_Proposition.get(i).getPrix())

                    .build();
            list1.add(propositionImprimenteDTO);System.out.println(propositionImprimenteDTO.getResolution());
        }}}

        System.out.println(list1.get(0).getResolution());

        return list1;
    }
    public void deleteFournisseur(Integer id){
        Fournisseur fournisseur=fournisseurRepository.findFournisseurById(id);

        List<Proposition> proposition=propositionRepository.findPropositionByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            List<Materiel_Proposition> materiel_Proposition=materielPropositionRepository.findMateriel_PropositionByProposition(proposition.get(j));

        for (int i=0;i<materiel_Proposition.size();i++){

            System.out.println(materiel_Proposition.get(i).getId());
            materielPropositionRepository.deleteById(materiel_Proposition.get(i).getId());
        }



       propositionRepository.deleteById(proposition.get(j).getId());}
       fournisseur.setListeNoir(true);
        fournisseurRepository.save(fournisseur);
     //  fournisseurRepository.deleteById(fournisseur.getId());

    }
    public void accepterProposition(Integer id) throws Exception {

        List<Fournisseur> Fournisseurs =getFournisseurs();
        Fournisseur fournisseur=fournisseurRepository.findFournisseurById(id);
        for (int i = 0; i < Fournisseurs.size(); i++) {
            if (fournisseur.equals(Fournisseurs.get(i))){
                List<Proposition> proposition=propositionRepository.findPropositionByFournisseur(fournisseur);
                for (int j = 0; j < proposition.size(); j++) {
                    if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)){
                        proposition.get(j).setStatus(StatusPropo.accepte);
                        propositionRepository.save(proposition.get(j));
                    }


                }
            }else {List<Proposition> proposition=propositionRepository.findPropositionByFournisseur(Fournisseurs.get(i));
                for (int j = 0; j < proposition.size(); j++) {
                    if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)){
                        proposition.get(j).setStatus(StatusPropo.refuse);
                        propositionRepository.save(proposition.get(j));
                        MessageDTO messageDTO=new MessageDTO();
                        messageDTO.setIdFournisseur(Fournisseurs.get(i).getId());
                        messageDTO.setMessage("refuse Proposition");
                        envoyerMotif(messageDTO);
                    }

            }
        }
        }}
    public void rejeterProposition(Integer id) {
        Fournisseur fournisseur = fournisseurRepository.findFournisseurById(id);
        List<Proposition> proposition = propositionRepository.findPropositionByFournisseur(fournisseur);
        for (int j = 0; j < proposition.size(); j++) {
            if (proposition.get(j).getStatus().equals(StatusPropo.En_cour)) {
                proposition.get(j).setStatus(StatusPropo.refuse);
                propositionRepository.save(proposition.get(j));
            }
        }

    }
public List<Fournisseur> getFournisseurListeNoir(){
    List<Fournisseur> fournisseurs = fournisseurRepository.findFournisseurByListeNoirTrue();
    for (int i=0;i<fournisseurs.size();i++){
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


    Message m=new Message();
    Fournisseur fournisseur = fournisseurRepository.findFournisseurById(msg.getIdFournisseur());

    User respo=userRepository.findUserByRoleEquals(Role.valueOf("RESPONSABLE"));
    m.setFournisseur(fournisseur);
    m.setMessage(msg.getMessage());
    m.setEmetteur(respo);
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





}

