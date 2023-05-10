package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.FournisseurCnx;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.ImprimenteDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.OrdinateurDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.FournisseurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.PropositionRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MaterielPropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    FournisseurRepository fouRep;
    @Autowired
    PropositionRepository PropRep;
    @Autowired
    MaterielPropositionRepository Mat_PropRep;
    @Autowired
    MatereilRepository MatRepo;
    @Autowired
    OrdinateurRepository OrdiRep;
    @Autowired
    ImprimanteRepository ImpRep;
    @Autowired
    AppleOffreRepo AppRep;
    @Autowired
    EnseignantRepository EnseRep;

    //Inscription
    public Fournisseur Inscrip(Fournisseur NvFour){

        fouRep.save(NvFour);
        FournisseurCnx fcnx=new FournisseurCnx();
        fcnx.setNom_societe(NvFour.getNomSociete());
        fcnx.setPass(NvFour.getPass());
        return Login(fcnx);
    }
    //CONNECTION
    public Fournisseur Login(FournisseurCnx fourLog) {
        List<Fournisseur> fournisseurList = fouRep.findAll();
        for (Fournisseur f : fournisseurList) {
            if (f.getPass().equals(fourLog.getPass()) && f.getNomSociete().equals(fourLog.getNom_societe()))
                return f;
        }
        return null;
    }

    //*****************PROPOSITION**************************

    //ADD PROPOSITION && UPDATE PROPOSITION
    public String ADDPROPO(Proposition prop){
       Fournisseur fournisseur = fouRep.findFournisseurById(prop.getFournisseur().getId());
        prop.setFournisseur(fournisseur);
        return ""+PropRep.save(prop).getId();
    }
    public Fournisseur getFournisseur(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String jwt = token.substring(7);
        User user = tokenRepository.findTokenByToken(jwt).getUser();
        Fournisseur fournisseur = fouRep.findFournisseurByNomSociete(user.getLogin());
        System.err.println(fournisseur.getNomSociete() + fournisseur.getId());
        return  fournisseur;
    }
    //DELETE PROPOSITION
    public boolean DELETEProp(Integer propid){

        PropRep.deleteById(propid);
        return true;

    }
    // ALL PROPOSITION
    public List<Proposition> ListeProposion(Integer id){
        Fournisseur fou=fouRep.findByid(id);
        List<Proposition> ListeProp =PropRep.findPropositionByFournisseurOrderByFournisseur(fou);
        return ListeProp;

    }

    //*****************Materiel**************************


    //LISTE ORDINATEUR
    public List<OrdinateurDto> getOrdinateursDemande(Integer id)  {
        AppelOffre app=AppRep.getById(id);
        List<OrdinateurDto> list = new ArrayList<>();
        List<Materiel> malist =MatRepo.findMaterielByAppelOffre(app);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Ordinateur ordinateur= OrdiRep.findOrdinateurById(mat.getId());
            if (ordinateur!=null){
                OrdinateurDto ordinateurDto = OrdinateurDto.builder()
                        .id(mat.getId())
                        .cpu(ordinateur.getCpu())
                        .ram(ordinateur.getRam())
                        .ecran(ordinateur.getEcran())
                        .disque(ordinateur.getDisque())
                        .prix(0)
                        .marque("")
                        .build();

                list.add(ordinateurDto);}
        }
        return  list;
    }
    public List<OrdinateurDto> getOrdinateursProp(Integer id)  {
        Proposition app=PropRep.getById(id);
        List<OrdinateurDto> list = new ArrayList<>();
        List<Materiel_Proposition> malist =Mat_PropRep.findByProposition(app);
        for (int i =0;i<malist.size();i++){
            Materiel_Proposition mat = malist.get(i);
            Ordinateur ordinateur= OrdiRep.findOrdinateurById(mat.getMateriel().getId());
            if (ordinateur!=null){
                OrdinateurDto ordinateurDto = OrdinateurDto.builder()
                        .id(mat.getId())
                        .cpu(ordinateur.getCpu())
                        .ram(ordinateur.getRam())
                        .ecran(ordinateur.getEcran())
                        .disque(ordinateur.getDisque())
                        .prix(mat.getPrix())
                        .marque(mat.getMarque())
                        .id_Prop(mat.getProposition().getId())
                        .build();

                list.add(ordinateurDto);}
        }
        return  list;
    }


    //LISTE IMPRIMRNTE
    public List<ImprimenteDto> getImprimentesDemande(int id) {
        AppelOffre app=AppRep.getById(id);
        List<ImprimenteDto> list = new ArrayList<>();
        List<Materiel> malist =MatRepo.findMaterielByAppelOffre(app);
        for (int i =0;i<malist.size();i++){
            Materiel mat = malist.get(i);
            Imprimente imprimente= ImpRep.findImprimenteById(mat.getId());
            if (imprimente!=null){
                ImprimenteDto imprimanteDto = ImprimenteDto.builder()
                        .id(mat.getId())
                        .resolution(imprimente.getResolution())
                        .vitesse(imprimente.getVitesse())
                        .prix(0)
                        .marque("")
                        .build();
                list.add(imprimanteDto);}
        }
        return  list;
    }

    public List<ImprimenteDto> getImprimenteProp(Integer id)  {
        Proposition app=PropRep.getById(id);
        List<ImprimenteDto> list = new ArrayList<>();
        List<Materiel_Proposition> malist =Mat_PropRep.findByProposition(app);
        for (int i =0;i<malist.size();i++){
            Materiel_Proposition mat = malist.get(i);
            Imprimente imp= ImpRep.findImprimenteById(mat.getMateriel().getId());
            if (imp!=null){
                ImprimenteDto imprimanteDto = ImprimenteDto.builder()
                        .id(mat.getId())
                        .resolution(imp.getResolution())
                        .vitesse(imp.getVitesse())
                        .prix(mat.getPrix())
                        .marque(mat.getMarque())
                        .id_Prop(mat.getProposition().getId())
                        .build();
                list.add(imprimanteDto);}
        }
        return  list;
    }




    //ADD  ORDINATEUR
    public String ADD_Ordi(OrdinateurDto ordi){

        Materiel_Proposition MatProp=new Materiel_Proposition();
      MatProp.setMarque(ordi.getMarque());MatProp.setPrix(ordi.getPrix());
      MatProp.setProposition(PropRep.getById(ordi.getId_Prop()));
      MatProp.setMateriel(MatRepo.findMaterielById(ordi.getId()));

      return ""+Mat_PropRep.save(MatProp);

    }



    public String UPDATE_Ordi(OrdinateurDto ordi){

        Materiel_Proposition MatProp=new Materiel_Proposition();
        List<Materiel_Proposition> ListProp=Mat_PropRep.findByProposition(PropRep.getById(ordi.getId_Prop()));
        for(Materiel_Proposition Mat:ListProp){
            if(Mat.getId()== ordi.getId()){
                MatProp=Mat;
            }
        }
        MatProp.setMarque(ordi.getMarque());
        MatProp.setPrix(ordi.getPrix());

        return ""+Mat_PropRep.save(MatProp);

    }



    //ADD && UPDATE IMPRIMENTE
    public String ADD_IMPRI(ImprimenteDto Imp){

        Materiel_Proposition MatProp=new Materiel_Proposition();
        MatProp.setMarque(Imp.getMarque());MatProp.setPrix(Imp.getPrix());
        MatProp.setProposition(PropRep.getById(Imp.getId_Prop()));
        MatProp.setMateriel(MatRepo.findMaterielById(Imp.getId()));

        return ""+Mat_PropRep.save(MatProp);

    }

    //UPDATE IMPRIMENTE

    public String UPDATE_IMPRI(ImprimenteDto Imp){

        Materiel_Proposition MatProp=new Materiel_Proposition();
        List<Materiel_Proposition> ListProp=Mat_PropRep.findByProposition(PropRep.getById(Imp.getId_Prop()));
        for(Materiel_Proposition Mat:ListProp){
            if(Mat.getId()== Imp.getId()){
                MatProp=Mat;
            }
        }
        MatProp.setMarque(Imp.getMarque());
        MatProp.setPrix(Imp.getPrix());

        return ""+Mat_PropRep.save(MatProp);
    }


    //DELETE
    public String DELETE_Materiel(int id){
        Mat_PropRep.deleteById(id);
        return "DONE";
    }

    public List<Materiel> ListeEns(){
        return MatRepo.findAll();
    }

    public List<AppelOffre> ListeApp(){
       return AppRep.findAllByDateFinAfterOrderByDateFinDesc(LocalDate.now());
    }


    public boolean isDejaPostuler(int id, int offreId){
        List<Materiel> materiels = MatRepo.findAllByAppelOffre(AppRep.getById(offreId));
        if( materiels!=null ){
            List<Materiel_Proposition> materielPropositions = Mat_PropRep.findAllByMateriel(materiels.get(0));
            if(materielPropositions != null){
                var fournisseur = fouRep.findFournisseurById(id);
                for(int i=0;i<materielPropositions.size();i++){
                    if(materielPropositions.get(i).getProposition().getFournisseur().equals(fournisseur))
                        return true;
                }
            }
        }
        return false;
    }


}
