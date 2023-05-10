package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;


import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.FournisseurCnx;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.ImprimenteDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.Fournisseur.OrdinateurDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:4200")
@RequestMapping("/api/projet/Fournisseur")

public class ControlleurFourni {

    @Autowired
    FournisseurService ServicFour;


    //CONNECTION && INSCRIPTION
    @PostMapping("/inscrip")
    public Fournisseur Inscription(@RequestBody Fournisseur four){
          if(ServicFour.Inscrip(four)!=null)
              return ServicFour.Inscrip(four);
          else
              return null;
    }
    @PostMapping("/log")
    public Fournisseur Login(@RequestBody FournisseurCnx four){
        return ServicFour.Login(four);
    }
    @PutMapping("/UPDATEFOUR")
    public Fournisseur UPDATELogin(@RequestBody Fournisseur four){
        return ServicFour.Inscrip(four);
    }

    //PROPOSITION
    @GetMapping("/getFournisseur")
    public ResponseEntity<Fournisseur> getFournisseur(HttpServletRequest request){
        return new ResponseEntity<>(ServicFour.getFournisseur(request), HttpStatus.OK);
    }
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


    @GetMapping("/ListeOrdi/{id}")
    public List<OrdinateurDto> ListeOrdi(@PathVariable(value = "id") Integer id){
        try {
            return ServicFour.getOrdinateursDemande(id);
        }catch (Exception e){
            return null;
        }

    }

    @GetMapping("/ListeOrdiProp/{id}")
    public List<OrdinateurDto> ListeOrdiProp(@PathVariable(value = "id") Integer id){
        try {
            return ServicFour.getOrdinateursProp(id);
        }catch (Exception e){
            return null;
        }

    }

    @GetMapping("/ListeImp/{id}")
    public List<ImprimenteDto> ListeImp(@PathVariable(value = "id") int id){
       try {
           return ServicFour.getImprimentesDemande(id);
       }catch (Exception e){
           return null;
       }
    }

    @GetMapping("/ListeImpProp/{id}")
    public List<ImprimenteDto> ListeImpProp(@PathVariable(value = "id") int id){
        try {
            return ServicFour.getImprimenteProp(id);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping("/ADDOrdi")

    public  String AddOrdi(@RequestBody OrdinateurDto ordi ){
       try {
           return ServicFour.ADD_Ordi(ordi);
       }catch (Exception e){
           return e.getMessage();
       }


    }

    @PostMapping("/ADDImp")

    public  String AddImp(@RequestBody ImprimenteDto imp ){

        try {
            return ServicFour.ADD_IMPRI(imp);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/ModOrdi")
    public  String UpdateOrdi(@RequestBody OrdinateurDto ordi ){
        try {
            return ServicFour.UPDATE_Ordi(ordi);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @PutMapping("/ModImp")

    public  String UpdateImp(@RequestBody ImprimenteDto imp ){

        try {
            return ServicFour.UPDATE_IMPRI(imp);
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    @DeleteMapping("/DeleteMat/{id}")

    public String DeleteMat(@PathVariable(value = "id") int id){
        try {
            return "" + ServicFour.DELETE_Materiel(id);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/listeAppelOff")
    public  List<AppelOffre> AppelOff(){
        return ServicFour.ListeApp();
    }



    @GetMapping("/isDejaPostuler/{id}/{offreId}")
    public ResponseEntity<Boolean> isDejaPostuler(@PathVariable int id, @PathVariable int offreId){
        return new ResponseEntity<Boolean>(ServicFour.isDejaPostuler(id, offreId), HttpStatus.OK);
    }



    

}
