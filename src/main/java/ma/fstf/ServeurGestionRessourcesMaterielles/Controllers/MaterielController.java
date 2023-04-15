package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import lombok.AllArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielOrdinateurDTO;
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
@RequestMapping("/api")
public class MaterielController {
    @Autowired
    private MaterielService materielService;
    @PostMapping("/saveOrdinateur")
    public void saveOrdinateur(@RequestBody Ordinateur ordinateur){
        materielService.saveOrdinateur(ordinateur);
    }
    @PostMapping("/saveImprimante")
    public void saveImprimente(@RequestBody Imprimente imprimente){
       materielService.saveImprimante(imprimente);
    }
    @GetMapping("/getMateriels/{id}")
    public ResponseEntity<List<MaterielDto>> getMateriels(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(materielService.getMateriels(id), HttpStatus.OK);
    }
//    @GetMapping("/getMaterielsBesoins/{departement}")
//    public ResponseEntity<List<MaterielOrdinateurDTO>> getMaterielsBesoins(@PathVariable String departement) throws Exception {
//        return new ResponseEntity<>(materielService.getMaterielsBesoins(departement), HttpStatus.OK);
//    }
    @GetMapping("/getMaterielsBesoins/{departement}")
    public List<MaterielOrdinateurDTO> getMaterielsBesoins(@PathVariable String departement) throws Exception {
        return materielService.getMaterielsBesoins(departement);
    }

//    @GetMapping("/getMaterielsImprimenteBesoins/{departement}")
//    public ResponseEntity<List<MaterielImprimenteDTO>> getMaterielsImprimenteBesoins(@PathVariable String departement) throws Exception {
//        return new ResponseEntity<>(materielService.getMaterielsImprimenteBesoins(departement), HttpStatus.OK);
//    }
}
