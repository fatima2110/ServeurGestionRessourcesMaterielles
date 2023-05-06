package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MaterielController {
    @Autowired
    private MaterielService materielService;
    @CrossOrigin("*")
    @PostMapping("/saveOrdinateur/{id}")
    public void saveOrdinateur(@RequestBody Ordinateur ordinateur,@PathVariable int id){
        materielService.saveOrdinateur(ordinateur,id);
    }
    @CrossOrigin("*")
    @PostMapping("/saveImprimante/{id}")
    public void saveImprimente(@RequestBody Imprimente imprimente,@PathVariable int id){
        materielService.saveImprimante(imprimente,id);
    }
    @CrossOrigin("*")
    @GetMapping("/getMateriels/{id}")
    public ResponseEntity<List<MaterielDto>> getMateriels(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getMateriels(id), HttpStatus.OK);
    }
    @CrossOrigin("*")
    @GetMapping("/enPanne/{id}")
    public void enPanne(@PathVariable int id){
        materielService.enPanne(id);
    }
    @GetMapping("/materielstate/{id}/{state}")
    public void materielstate(@PathVariable String id, @PathVariable String state, HttpServletRequest request){
        System.err.println(id +" "+" "+state);
        materielService.materielstate(id, state, request);
    }
    @CrossOrigin("*")
    @GetMapping("/getBesoinsOrdinateurs/{id}")
    public ResponseEntity<List<OrdinateurDto>> getBesoinsOrdinateurs(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getBesoinsOrdinateursOfEns(id), HttpStatus.OK);
    }
    @CrossOrigin("*")
    @GetMapping("/getBesoinsImpriments/{id}")
    public ResponseEntity<List<ImprimanteDto>> getBesoinsImprimentes(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getBesoinsImprimentesOfEns(id), HttpStatus.OK);
    }
    @CrossOrigin("*")
    @DeleteMapping("/deleteImprimente/{id}")
    public void delete(@PathVariable int id){
        materielService.supprimerMaterielImprimente(id);
    }
    @CrossOrigin("*")
    @DeleteMapping("/deleteOrdinateur/{id}")
    public void deleteOrdinateur(@PathVariable int id){
        materielService.supprimerMaterielOrdinateur(id);
    }
    @CrossOrigin("*")
    @PutMapping("editOrdinateur")
    public void editOrdinateur(@RequestBody Ordinateur ordinateur){
        materielService.editOrdinateur(ordinateur);
    }
    @CrossOrigin("*")
    @PutMapping("validOrdibateur")
    public void validOrdinateurChef(@RequestBody Ordinateur ordinateur){
        materielService.validOrdinateurChef(ordinateur);
    }
    @CrossOrigin("*")
    @PutMapping("editImprimente")
    public void editOrdinateur(@RequestBody Imprimente imprimente){
        materielService.editImprimente(imprimente);
    }
    @CrossOrigin("*")
    @PutMapping("validImprimente")
    public void validImprimente(@RequestBody Imprimente imprimente){
        materielService.validImprimenteChef(imprimente);
    }
    @CrossOrigin("*")
    @GetMapping(value = "/getBesoinsOrdinateurChef/{departement}")
    public ResponseEntity<List<BesoinChefOrdinateurDto>> getBesoinsChef(@PathVariable String departement) throws Exception {
        return new ResponseEntity<>(materielService.getMaterielsOrdinateursBesoins(departement), HttpStatus.OK);
    }
    @CrossOrigin("*")
    @GetMapping(value = "/getBesoinsImprimentesChef/{departement}")
    public ResponseEntity<List<BesoinChefImprimenteDto>> getBesoinsImprimentesChef(@PathVariable String departement) throws Exception {
        return new ResponseEntity<>(materielService.getMaterielsImprimentesBesoins(departement), HttpStatus.OK);
    }
}