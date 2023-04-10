package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;


import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel_Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.FournisseurRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Materiel_PropositionReposetory;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.MessageReposetory;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")

public class ControlleurFourni {
    @Autowired
    FournisseurRepository FourRep;
    @Autowired
    PropositionRepository PropoRep;
    @Autowired
    MessageReposetory MsgRepo;
    @Autowired
            Materiel_PropositionReposetory Mat_PropRespo;
    Fournisseur FourCnct=new Fournisseur();




    @PostMapping("/Log")
    public String Authen(@RequestBody Fournisseur four){
      Fournisseur FourCnct = FourRep.findByNom_societeAndPass();
      if(FourCnct==null)
          return "Non authentified";
      return "authentified";
    }
    @PostMapping("/inscrip")
    public String Inscrip(@RequestBody Fournisseur four){
        FourRep.save(four);
        return "Inscrip";
    }

    @PostMapping("/saveProp")
    public String SavePropo(@RequestBody Proposition prop){
        PropoRep.save(prop);
        return "Save Proposition";
    }

    @GetMapping("/ListePropo")
    public ArrayList<Proposition> ListePropo(@RequestBody Fournisseur four){

        return  FourCnct.getPropositions();
    }

    @GetMapping("/ListeMat/{idProp}")
    public ArrayList<Materiel_Proposition> ListeMatProp(@PathVariable(value = "idProp") Integer id){

      return PropoRep.getById(id).getMateriels_propositions();
    }

    @PutMapping(path = "/update")

    public String updateCustomer(@RequestBody Materiel_Proposition Mat_Propo)
    {
        if(Mat_PropRespo.existsById(Mat_Propo.getId())) {
            Mat_PropRespo.save(Mat_Propo);
            return "updated";
        }
        return "Non";
    }


    @DeleteMapping(path = "/deleteMateriel/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int id)
    {
        if(Mat_PropRespo.existsById(id)) {
            Mat_PropRespo.deleteById(id);
            return "deleted";
        }
        return "Non";
    }




}
