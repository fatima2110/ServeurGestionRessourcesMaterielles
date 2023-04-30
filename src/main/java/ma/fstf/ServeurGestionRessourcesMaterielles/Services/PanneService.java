package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.ConstatDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PanneImprimenteDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PanneOrdinateurDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PanneService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private ConstatRepository constatRepository;
    @Autowired
    private PanneRepository panneRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;

    public List<PanneOrdinateurDTO> ordinateurEnPanne(){
        List<Panne> pannes = panneRepository.findAll();
        List<PanneOrdinateurDTO> materiels = new ArrayList<>();
        for(int i=0;i< pannes.size();i++){
            Integer id = pannes.get(i).getMateriel().getId();
            Ordinateur ordinateur = ordinateurRepository.findOrdinateurById(id);
            Ensiegnant ensiegnant = pannes.get(i).getMateriel().getEnsiegnant();
            if(ordinateur != null){
                PanneOrdinateurDTO panneOrdinateurDTO=PanneOrdinateurDTO.builder()
                        .enseignant(ensiegnant.getNom()+" "+ensiegnant.getPrenom())
                        .code_barre(ordinateur.getCodeBarre())
                        .marque(ordinateur.getMarque())
                        .cpu(ordinateur.getCpu())
                        .ram(ordinateur.getRam())
                        .ecran(ordinateur.getEcran())
                        .disque(ordinateur.getDisque())
                        .build();
                materiels.add(panneOrdinateurDTO);
            }
        }
        return materiels;
    }
    public List<PanneImprimenteDTO> imprimenteEnPanne(){
        List<Panne> pannes = panneRepository.findAll();
        List<PanneImprimenteDTO> materiels = new ArrayList<>();
        for(int i=0;i< pannes.size();i++){
            Integer id = pannes.get(i).getMateriel().getId();
            Imprimente imprimente = imprimanteRepository.findImprimenteById(id);
            Ensiegnant ensiegnant = pannes.get(i).getMateriel().getEnsiegnant();
            if(imprimente != null){
                PanneImprimenteDTO panneImprimenteDTO=PanneImprimenteDTO.builder()
                        .enseignant(ensiegnant.getNom()+" "+ensiegnant.getPrenom())
                        .code_barre(imprimente.getCodeBarre())
                        .marque(imprimente.getMarque())
                        .resolution(imprimente.getResolution())
                        .vitesse(imprimente.getVitesse())
                        .build();
                materiels.add(panneImprimenteDTO);
            }
        }
        return materiels;
    }

    public void ajouterConstat(ConstatDTO constatDTO){
        Materiel materiel = matereilRepository.findMaterielByCodeBarreAndMaterielState(constatDTO.getCode_barre(),MaterielState.EnPanne);
        if(materiel != null){
            Panne panne = panneRepository.findByMateriel(materiel);
            if (panne != null){
                panne.setTechnicien(userRepository.findUserById(constatDTO.getId_technicien()));
                panneRepository.save(panne);
                Constat constat = Constat.builder()
                        .date_apparition(constatDTO.getDate_apparition())
                        .explication_panne(constatDTO.getExplication_panne())
                        .frequence(constatDTO.getFrequence())
                        .ordre(constatDTO.getOrdre())
                        .panne(panne)
                        .build();
                constatRepository.save(constat);
            }
        }
    }

    public List<ConstatDTO> getConstats(){
        List<ConstatDTO> constatDTOS = new ArrayList<>();
        List<Constat> constats = constatRepository.findAll();
        if(constats != null){
            for(int i=0;i< constats.size();i++){
                ConstatDTO constatDTO = ConstatDTO.builder()
                        .code_barre(constats.get(i).getPanne().getMateriel().getCodeBarre())
                        .date_apparition(constats.get(i).getDate_apparition())
                        .explication_panne(constats.get(i).getExplication_panne())
                        .frequence(constats.get(i).getFrequence())
                        .ordre(constats.get(i).getOrdre())
                        .build();
                constatDTOS.add(constatDTO);
            }
        }
        return constatDTOS;
    }
}
