package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
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
            List<Materiel> matList = matereilRepository.findMaterielByEnsiegnant(ens);
            for (int i =0;i<matList.size();i++){
                Materiel mat = matList.get(i);
                MaterielDto materielDto = MaterielDto.builder()
                        .marque(mat.getMarque())
                        .date_livraison(mat.getDate_livraison())
                        .duree_garentie(mat.getDuree_garentie())
                        .code_barre(mat.getCode_barre())
                        .enseignant(ens.getNom())
                        .build();
                list.add(materielDto);
            }
            return  list;
        }
        else
            throw new Exception("Enseignant n'existe pas");
    }
    public Ensiegnant getEnseignant(int id){
        Optional<Ensiegnant> optionalEnsiegnant = enseignantRepository.findById(id);
        return optionalEnsiegnant.orElse(null);
    }

    public void supprimerMaterielOrdinateur(int id) {
        Ordinateur ordinateur = ordinateurRepository.findById(id).orElse(null);
        if (ordinateur != null) {
            ordinateurRepository.deleteById(id);
        }
    }
}
