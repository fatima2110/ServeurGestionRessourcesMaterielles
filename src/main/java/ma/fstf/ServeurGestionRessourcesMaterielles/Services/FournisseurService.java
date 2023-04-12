package ma.fstf.ServeurGestionRessourcesMaterielles.Services;

import ma.fstf.ServeurGestionRessourcesMaterielles.ModelRepre.Matereil_Propo_Repres;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel_Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.FournisseurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Materiel_PropositionReposetory;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {

    @Autowired
    FournisseurRepository fouRep;
    @Autowired
    PropositionRepository PropRep;
    @Autowired
    Materiel_PropositionReposetory MatRep;

    //Inscription
    public String Inscrip(Fournisseur NvFour){
        List<Fournisseur> fournisseurList = fouRep.findAll();
        for (Fournisseur f : fournisseurList) {
            //QUESTION
            if (f.getNom_societe().equals(NvFour.getNom_societe()) || f.getEmail().equals(NvFour.getEmail()))
                return "DEJA EXISTE";
        }
        fouRep.save(NvFour);
        return Login(NvFour);
    }
    //CONNECTION
    public String Login(Fournisseur fourLog) {
        List<Fournisseur> fournisseurList = fouRep.findAll();
        for (Fournisseur f : fournisseurList) {
            if (f.getPass().equals(fourLog.getPass()) && f.getNom_societe().equals(fourLog.getNom_societe()))
                return "" + f.getId();
        }
        return "EROOR";
    }

    //*****************PROPOSITION**************************

    //ADD PROPOSITION && UPDATE PROPOSITION
    public String ADDPROPO(Proposition prop){
        //if(PropRep.existsById(prop.getId()))
          //  DELETEProp(prop.getId());
        return ""+PropRep.save(prop).getId();

    }
    //DELETE PROPOSITION
    public boolean DELETEProp(Integer propid){

        PropRep.deleteById(propid);
        return true;

    }
    // ALL PROPOSITION
    public List<Proposition> ListeProposion(Integer id){
        Fournisseur fou=fouRep.findByid(id);
        List<Proposition> ListeProp =PropRep.findPropositionByFournisseur(fou);
        return ListeProp;

    }

    //*****************Materiel**************************





}
