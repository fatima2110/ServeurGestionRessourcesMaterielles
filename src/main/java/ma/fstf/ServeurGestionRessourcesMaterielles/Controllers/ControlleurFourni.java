package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel_Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.FournisseurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Materiel_PropositionReposetory;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MessageReposetory;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.PropositionRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.ModelRepre.Matereil_Propo_Repres;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin //(origins = "http://localhost:4200")
@RequestMapping("/api/projet/Fournisseur")

public class ControlleurFourni {

    @Autowired
    FournisseurService ServicFour;


    //CONNECTION && INSCRIPTION
    @PostMapping("/inscrip")
    public String Inscription(@RequestBody Fournisseur four){
          return  ServicFour.Inscrip(four);
    }
    @PostMapping("/log")
    public String Login(@RequestBody Fournisseur four){
        return ServicFour.Login(four);
    }

    //PROPOSITION
    @PostMapping("/ADDPropo")
    public String ADDPROP(@RequestBody Proposition prop){
        return ServicFour.ADDPROPO(prop);
    }
    @PutMapping("/UPDATEPROP")
    public String UPDATEPROP(@RequestBody Proposition prop){
        return ServicFour.ADDPROPO(prop);
    }
    @DeleteMapping("/DELETEProp/{id}")
    public boolean DELETEPROP(@PathVariable(value = "id") Integer id){

        ServicFour.DELETEProp(id);
        return true;
    }

    //LISTE PROPOSITION
    @GetMapping("/ListePropo/{id}")

    public List<Proposition> ListePropo(@PathVariable(value = "id") Integer id)
    {
        return ServicFour.ListeProposion(id);

    }

    //**************MATERIEL********




    

}
