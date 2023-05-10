package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.AffectationRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.EnseignantRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MatereilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterielService {
    @Autowired
    private AffectationRepository affectationRepository;
    @Autowired
    private ConstatRepository constatRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private PanneRepository panneRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MessageRepository messageRepository;
    public void saveOrdinateur(Ordinateur ordinateur,int id){
        Ensiegnant ens=enseignantRepository.findById(id).get();
        ordinateur.setEnsiegnant(ens);
        ordinateurRepository.save(ordinateur);
    }
    public void saveImprimante(Imprimente imprimente,int id){
        Ensiegnant ens=enseignantRepository.findById(id).get();
        imprimente.setEnsiegnant(ens);
        imprimanteRepository.save(imprimente);

    }
    public List<MaterielDto> getMateriels(int id) throws Exception {
        List<MaterielDto> list = new ArrayList<>();
        Ensiegnant ens = getEnseignant(id);
        if (ens != null){
            List<Affectation> affectations = affectationRepository.findAffectationByEnsiegnant(ens);
            //List<Materiel> matList = matereilRepository.findMaterielByEnsiegnantAndAppelOffreNotNull(ens);
            for (int i =0;i<affectations.size();i++){
                Materiel mat = affectations.get(i).getMateriel();
                MaterielDto materielDto = MaterielDto.builder()
                        .id(mat.getId())
                        .marque(mat.getMarque())
                        .date_affectation(mat.getDate_livraison())
                        .duree_garentie(mat.getDuree_garentie())
                        .code_barre(mat.getCodeBarre())
                        .enseignant(ens.getNom())
                        .enPanne(mat.isPanne())
                        .materielState(mat.getMaterielState())
                        .build();
                list.add(materielDto);
            }
            return  list;
        }
        else
            throw new Exception("Enseignant n'existe pas");
    }
    public List<OrdinateurDto> getBesoinsOrdinateursOfEns(int id) throws Exception {
        List<OrdinateurDto> list = new ArrayList<>();
        Ensiegnant ens = getEnseignant(id);
        List<Materiel> malist =matereilRepository.findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(ens);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Ordinateur ordinateur= getordinateur(mat.getId());
            if (ordinateur!=null){
                OrdinateurDto ordinateurDto = OrdinateurDto.builder()
                        .id(mat.getId())
                        .cpu(ordinateur.getCpu())
                        .ram(ordinateur.getRam())
                        .ecran(ordinateur.getEcran())
                        .disque(ordinateur.getDisque())

                        .build();
                list.add(ordinateurDto);}
        }
        return  list;
    }

    public List<ImprimanteDto> getBesoinsImprimentesOfEns(int id) throws Exception {
        List<ImprimanteDto> list = new ArrayList<>();
        Ensiegnant ens = getEnseignant(id);
        List<Materiel> malist =matereilRepository.findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(ens);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Imprimente imprimente= getImprimente(mat.getId());
            if (imprimente!=null){
                ImprimanteDto imprimanteDto = ImprimanteDto.builder()
                        .id(mat.getId())
                        .resolution(imprimente.getResolution())
                        .vitesse(imprimente.getVitesse())

                        .build();
                list.add(imprimanteDto);}
        }
        return  list;
    }

    public Ensiegnant getEnseignant(int id){
        Optional<Ensiegnant> optionalEnsiegnant = enseignantRepository.findById(id);
        return optionalEnsiegnant.orElse(null);
    }
    public Ordinateur getordinateur(int id){
        return ordinateurRepository.findOrdinateurById(id);
    }
    public Imprimente getImprimente(int id){
        return imprimanteRepository.findImprimenteById(id);
    }
    public void supprimerMaterielOrdinateur(int id) {
        Ordinateur ordinateur = ordinateurRepository.findById(id).orElse(null);
        if (ordinateur != null) {
            ordinateurRepository.deleteById(id);
        }
    }
    public void supprimerMaterielImprimente(int id) {
        Imprimente imprimente = imprimanteRepository.findById(id).orElse(null);
        if (imprimente != null) {
            imprimanteRepository.deleteById(id);
        }
    }
    public void enPanne(int id){
        Materiel mat=matereilRepository.findMaterielById(id);
        Panne panne = Panne.builder().materiel(mat).datePanne(LocalDate.now()).build();
        mat.setPanne(true);
        mat.setMaterielState(MaterielState.EnPanne);
        matereilRepository.save(mat);
        panneRepository.save(panne);
    }
    public void materielstate(String id, String state,int id_constat, HttpServletRequest request){
        // id : materiel!!!!!!!!!!!!!!!!!
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User user = tokenRepository.findTokenByToken(jwt).getUser();
        Materiel mat=matereilRepository.findMaterielByCodeBarre(id);
        if(mat != null){
            MaterielState materielState = MaterielState.valueOf(state);
            if(state.equals("REPAREE")){
                Panne panne = panneRepository.findPanneByMaterielAndTreatedIsFalse(mat);
                panne.setTreated(true);
                panne.setTechnicien(user);
                panneRepository.save(panne);
            }
            if(state.equals("EnReparation") || state.equals("DoitChange")){
                Constat constat = constatRepository.findById(id_constat).get();
                constat.setTreated(true);
                constatRepository.save(constat);
            }
            mat.setMaterielState(materielState);
            matereilRepository.save(mat);
        }
    }
    public void editOrdinateur(Ordinateur newOrdinateur){
        Ordinateur ordinateur =ordinateurRepository.findOrdinateurById(newOrdinateur.getId());
        newOrdinateur.setEnsiegnant(ordinateur.getEnsiegnant());
        //newOrdinateur.getEnsiegnant().setId(idEnsi);
        this.ordinateurRepository.save(newOrdinateur);
    }
    public void editImprimente(Imprimente newImprimente){
        Imprimente imprimente1=imprimanteRepository.findImprimenteById(newImprimente.getId());
        newImprimente.setEnsiegnant(imprimente1.getEnsiegnant());
        this.imprimanteRepository.save(newImprimente);
    }
    public List<BesoinChefOrdinateurDto> getMaterielsOrdinateursBesoins(String departement) throws Exception {
        List<BesoinChefOrdinateurDto> list = new ArrayList<>();
        List<Ensiegnant> ens=enseignantRepository.findEnsiegnantByDepartementEquals(departement);
        for (int i =0;i<ens.size();i++){
            System.out.println("enseignat"+ens.get(i).getNom());
            List<Materiel> matList = matereilRepository.findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(ens.get(i));
            for (int j =0;j<matList.size();j++){
                Materiel mat = matList.get(j);
                if(mat!=null){
                    Ordinateur ordinateur=ordinateurRepository.findOrdinateurById(mat.getId());
                    if (ordinateur!=null){
                        User user= userRepository.findUserById(ens.get(i).getId());
                        if(user!=null){
                            BesoinChefOrdinateurDto materielOrdinateurDTO= BesoinChefOrdinateurDto.builder()
                                    .id(mat.getId())
                                    .nom(user.getNom())
                                    .prenom(user.getPrenom())
                                    .cpu(ordinateur.getCpu())
                                    .ram(ordinateur.getRam())
                                    .ecran(ordinateur.getEcran())
                                    .disque(ordinateur.getDisque())

                                    .build();
                            list.add(materielOrdinateurDTO);
                        }}
                }}
        }
        return list;
    }

    public List<BesoinChefImprimenteDto> getMaterielsImprimentesBesoins(String departement) throws Exception {
        List<BesoinChefImprimenteDto> list = new ArrayList<>();
        List<Ensiegnant> ens=enseignantRepository.findEnsiegnantByDepartementEquals(departement);
        for (int i =0;i<ens.size();i++){
            System.out.println("enseignat"+ens.get(i).getNom());
            List<Materiel> matList = matereilRepository.findMaterielByEnsiegnantAndAppelOffreNullAndVerifieIsFalse(ens.get(i));
            for (int j =0;j<matList.size();j++){
                Materiel mat = matList.get(j);
                if(mat!=null){
                    Imprimente imprimente=imprimanteRepository.findImprimenteById(mat.getId());
                    if (imprimente!=null){
                        User user= userRepository.findUserById(ens.get(i).getId());
                        BesoinChefImprimenteDto besoinChefImprimenteDto= BesoinChefImprimenteDto.builder()
                                .id(mat.getId())
                                .nom(user.getNom())
                                .prenom(user.getPrenom())
                                .resolution(imprimente.getResolution())
                                .vitesse(imprimente.getVitesse())
                                .build();
                        list.add(besoinChefImprimenteDto);
                    }
                }}
        }
        return list;
    }
    public void validOrdinateurChef(Ordinateur newOrdinateur){
        Ordinateur ordinateur =ordinateurRepository.findOrdinateurById(newOrdinateur.getId());
        newOrdinateur.setEnsiegnant(ordinateur.getEnsiegnant());
        newOrdinateur.setVerifie(true);
        this.ordinateurRepository.save(newOrdinateur);
        sendMessageNewBesoinAreAded(ordinateur.getEnsiegnant());
    }
    public void validImprimenteChef(Imprimente newImprimente){
        Imprimente imprimente1=imprimanteRepository.findImprimenteById(newImprimente.getId());
        newImprimente.setEnsiegnant(imprimente1.getEnsiegnant());
        newImprimente.setVerifie(true);
        this.imprimanteRepository.save(newImprimente);
        sendMessageNewBesoinAreAded(newImprimente.getEnsiegnant());
    }

    private void sendMessageNewBesoinAreAded(Ensiegnant ensiegnant) {
        User admin = userRepository.findUserByRoleEquals(Role.RESPONSABLE);
        User chef = enseignantRepository.findEnsiegnantByDepartementAndRole(ensiegnant.getDepartement(), Role.CHEF_DEPARTEMENT);
        Message message = Message.builder()
                .message(NOTIFICATION.NewBesoin.getValue())
                .recepteur(admin)
                .emetteur(chef)
                .date(LocalDate.now())
                .vue(false)
                .build();
        messageRepository.save(message);
    }

}