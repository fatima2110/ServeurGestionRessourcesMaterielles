package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.ImprimanteDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.OrdinateurDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.EnseignantRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.ImprimanteRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MatereilRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.OrdinateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterielService {
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    public void saveOrdinateur(Ordinateur ordinateur){
        ordinateurRepository.save(ordinateur);
    }
    public void saveImprimante(Imprimente imprimente){
        imprimanteRepository.save(imprimente);

    }
    public List<MaterielDto> getMateriels(int id) throws Exception {
        List<MaterielDto> list = new ArrayList<>();
        Ensiegnant ens = getEnseignant(id);
        if (ens != null){
            List<Materiel> matList = matereilRepository.findMaterielByEnsiegnantAndAppelOffreNotNull(ens);
            for (int i =0;i<matList.size();i++){
                Materiel mat = matList.get(i);
                MaterielDto materielDto = MaterielDto.builder()
                        .id(mat.getId())
                        .marque(mat.getMarque())
                        .date_affectation(mat.getDate_livraison())
                        .duree_garentie(mat.getDuree_garentie())
                        .code_barre(mat.getCode_barre())
                        .enseignant(ens.getNom())
                        .state(mat.getState())

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
        List<Materiel> malist =matereilRepository.findMaterielByEnsiegnantAndAppelOffreNull(ens);
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
        List<Materiel> malist =matereilRepository.findMaterielByEnsiegnantAndAppelOffreNull(ens);
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
        if (mat.getState() != MaterielState.EnPANNE) {
            mat.setState(MaterielState.EnPANNE);
            System.out.println(mat.getState());
            //matereilRepository.save(mat.setPanne(true));
        }
    }
    public void enService(int id){
        Materiel mat=matereilRepository.findMaterielById(id);
        if (mat.getState() != MaterielState.EnSERVICE) {
            mat.setState(MaterielState.EnSERVICE);
            System.out.println(mat.getState());
            //matereilRepository.save(mat.setPanne(true));
        }
    }
    public void editOrdinateur(Ordinateur newOrdinateur){

        this.ordinateurRepository.save(newOrdinateur);
    }
    public void editImprimente(Imprimente imprimente){

        this.imprimanteRepository.save(imprimente);
    }
    public List<Ensiegnant> getMaterielsBesoins(String departement) throws Exception {
        //List<MaterielOrdinateurDTO> list = new ArrayList<>();
        List<Ensiegnant> ens=enseignantRepository.findEnsiegnantByDepartementEquals(departement);
//        for (int i =0;i<ens.size();i++){System.out.println("enseignat"+ens.get(i).getNom());
//        List<Materiel> matList = matereilRepository.findMaterielByEnsiegnantAndAppelOffreNull(ens.get(i));
//        for (int j =0;j<matList.size();j++){
//            Materiel mat = matList.get(j);
//            Ordinateur ordinateur=ordinateurRepository.getById(mat.getId());
//            if (ordinateur!=null){
//                MaterielOrdinateurDTO materielOrdinateurDTO= MaterielOrdinateurDTO.builder()
//                        .cpu(ordinateur.getCpu())
//                        .ram(ordinateur.getRam())
//                        .ecran(ordinateur.getEcran())
//                        .disque(ordinateur.getDisque())
//                        .enseignant(ens.get(i).getNom())
//                        .build();
//                list.add(materielOrdinateurDTO);
//            }
//
//        }}
        return ens;
    }
}
