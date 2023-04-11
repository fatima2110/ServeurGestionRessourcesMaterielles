package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import lombok.AllArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class MaterielController {
    @Autowired
    private MaterielService materielService;
    @PostMapping("/saveOrdinateur/{id}")
    public void saveOrdinateur(@RequestBody Ordinateur ordinateur,@PathVariable int id){
        materielService.saveOrdinateur(ordinateur,id);
    }
    @PostMapping("/saveImprimante/{id}")
    public void saveImprimente(@RequestBody Imprimente imprimente,@PathVariable int id){
        materielService.saveImprimante(imprimente,id);
    }
    @GetMapping("/getMateriels/{id}")
    public ResponseEntity<List<MaterielDto>> getMateriels(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getMateriels(id), HttpStatus.OK);
    }
    @GetMapping("/enPanne/{id}")
    public void enPanne(@PathVariable int id){
        System.out.println(id);
        materielService.enPanne(id);
    }
    @GetMapping("/enService/{id}")
    public void enService(@PathVariable int id){
        System.out.println(id);
        materielService.enService(id);
    }
    @GetMapping("/getBesoinsOrdinateurs/{id}")
    public ResponseEntity<List<OrdinateurDto>> getBesoinsOrdinateurs(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getBesoinsOrdinateursOfEns(id), HttpStatus.OK);
    }
    @GetMapping("/getBesoinsImpriments/{id}")
    public ResponseEntity<List<ImprimanteDto>> getBesoinsImprimentes(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getBesoinsImprimentesOfEns(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteImprimente/{id}")
    public void delete(@PathVariable int id){
        materielService.supprimerMaterielImprimente(id);
    }
    @DeleteMapping("/deleteOrdinateur/{id}")
    public void deleteOrdinateur(@PathVariable int id){
        materielService.supprimerMaterielOrdinateur(id);
    }
    @PutMapping("editOrdinateur")
    public void editOrdinateur(@RequestBody Ordinateur ordinateur){
        materielService.editOrdinateur(ordinateur);
    }
    @PutMapping("validOrdibateur")
    public void validOrdinateurChef(@RequestBody Ordinateur ordinateur){
        materielService.validOrdinateurChef(ordinateur);
    }
    @PutMapping("editImprimente")
    public void editOrdinateur(@RequestBody Imprimente imprimente){
        materielService.editImprimente(imprimente);
    }
    @PutMapping("validImprimente")
    public void validImprimente(@RequestBody Imprimente imprimente){
        materielService.validImprimenteChef(imprimente);
    }
    @GetMapping(value = "/getBesoinsOrdinateurChef/{departement}")
    public ResponseEntity<List<BesoinChefOrdinateurDto>> getBesoinsChef(@PathVariable String departement) throws Exception {
        return new ResponseEntity<>(materielService.getMaterielsOrdinateursBesoins(departement), HttpStatus.OK);
    }
    @GetMapping(value = "/getBesoinsImprimentesChef/{departement}")
    public ResponseEntity<List<BesoinChefImprimenteDto>> getBesoinsImprimentesChef(@PathVariable String departement) throws Exception {
        return new ResponseEntity<>(materielService.getMaterielsImprimentesBesoins(departement), HttpStatus.OK);
    }
}