package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.ImprimanteRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MatereilRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.OrdinateurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Representations.RepresentationMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielService {
    @Autowired
    private MatereilRepository matereilRepository;
    @Autowired
    private OrdinateurRepository ordinateurRepository;
    @Autowired
    private ImprimanteRepository imprimanteRepository;
    public void saveOrdinateur(Ordinateur ordinateur){
        System.out.println("CPU : "+ordinateur.getCpu());
        ordinateurRepository.save(ordinateur);

    }
    public void saveImprimante(Imprimente imprimente){
        System.out.println("Resolution : "+imprimente.getResolution());
        imprimanteRepository.save(imprimente);

    }
    public List<Materiel> getMateriels(int id){
       return  matereilRepository.getMatereilsEnsiegnant(id);
    }
}
