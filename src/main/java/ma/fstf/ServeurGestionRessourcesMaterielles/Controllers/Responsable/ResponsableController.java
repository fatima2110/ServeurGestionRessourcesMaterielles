package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers.Responsable;

import lombok.AllArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Affectation;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ensiegnant;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Fournisseur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable.AffectationService;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fournisseur")
public class ResponsableController {
    @Autowired
   private PropositionService propositionService;
    @Autowired
    private AffectationService affectationService;

    @GetMapping("/getFournisseurs")
    public List<Fournisseur> getFournisseurs() throws Exception {
        return propositionService.getFournisseurs();
    }
    @GetMapping("/getOrdinateurProposition/{id}")
    public List<PropositionOrdinateurDTO> getOrdinateurProposition(@PathVariable int id) throws Exception {
        return propositionService.getOrdinateurProposition(id);
    }
    @GetMapping("/getImprimenteProposition/{id}")
    public List<PropositionImprimenteDTO> getImprimenteProposition(@PathVariable int id) throws Exception {
        return propositionService.getImprimenteProposition(id);
    }
    @GetMapping("/delete/{id}")
    public void deleteFournisseur(@PathVariable Integer id) throws Exception {

         propositionService.deleteFournisseur(id);
    }

    @GetMapping("/accepter/{id}")
    public void accepterProposition(@PathVariable Integer id) throws Exception {
        //System.out.println(id);
        System.out.println("ha ana");
        propositionService.accepterProposition(id);
    }
    @GetMapping("/rejeter/{id}")
    public void rejeterProposition(@PathVariable Integer id) throws Exception {
        propositionService.rejeterProposition(id);
    }
    @GetMapping("/listeNoir")
    public List<Fournisseur> ListeNoirFournisseurs() throws Exception {
       return propositionService.getFournisseurListeNoir();
    }
    @GetMapping("/retirer/{id}")
    public void retirerFournisseurListeNoir(@PathVariable Integer id) throws Exception {
        propositionService.retirerFournisseur(id);
    }
    @PostMapping("/envoyerMotif")
    public void envoyerMotifElimination(@RequestBody MessageDTO msg) throws Exception {
       propositionService.envoyerMotif(msg);
    }
    @PostMapping("/notifaccept")
    public void envoyerNotifAccept(@RequestBody MessageDTO msg) throws Exception {
        propositionService.envoyerMotif(msg);

    }
    @PostMapping("/notifrejet")
    public void envoyerNotifRejet(@RequestBody MessageDTO msg) throws Exception {
        propositionService.envoyerMotif(msg);
    }
    @GetMapping("/getAllAffectations")
    public List<Affectation> getAffectedRessources() throws Exception {
        return affectationService.getAffectedRessources();
    }
    @GetMapping("/getAllNoAffectations")
    public List<Materiel> getNoAffectedRessources() throws Exception {
        return affectationService.getNonAffectedRessources();
    }
    @DeleteMapping ("/retirerRessourceAffecte/{id}")
    public void retirerRessourceAffecte(@PathVariable Integer id) throws Exception {
         affectationService.retirerRessourceAffecte(id);
    }

    @DeleteMapping("/retirerRessourceNoAffecte/{id}")
    public void retirerRessourceNoAffecte(@PathVariable Integer id) throws Exception {
        affectationService.retirerRessourceNonAffecte(id);
    }
    @GetMapping("/getEnsOfDepart/{departement}")
    public List<Ensiegnant> getEnsOfDepart(@PathVariable String departement) throws Exception {
        return affectationService.getEnsOfdepartement(departement);
    }
    @PostMapping("/addAffectaion")
    public void addAffectaion(@RequestBody AffectationDto affectation) throws Exception {
        affectationService.addAffectation(affectation);
    }
    @GetMapping("/message/{id}")
    public  List<MessageDTO> getMessage(@PathVariable Integer id){
        return   propositionService.getMessage(id);
    }
    @PostMapping("/ajouter")
    public void AjouterM(@RequestBody MessageDTO msg)
    {
        propositionService.AjouterMessage(msg);
    }
    @GetMapping("/suprimmer/{id}")
    public void suprimmer(@PathVariable Integer id)
    {
        propositionService.suprimerMessage(id);
    }
    @GetMapping("/number/{id}")
    public int NumberMessage(@PathVariable Integer id)
    {
;
        System.out.println("vue "+propositionService.NombreMessage(id));
        return  propositionService.NombreMessage(id);
    }
    @GetMapping("/changer")
    public void changer(List<MessageDTO> lst)
    {
        propositionService.modfierVue(lst);
    }
    @PostMapping("/infoFournisseur")
    public  void  InfoFournosseur(@RequestBody Fournisseur f)
    {
        propositionService.InfoFournisseur(f);
    }
    @GetMapping("/info/{id}")
    public  Fournisseur informationFournisseur(@PathVariable Integer id)
    {
        return  propositionService.InformationFournisseur(id);
    }
}

