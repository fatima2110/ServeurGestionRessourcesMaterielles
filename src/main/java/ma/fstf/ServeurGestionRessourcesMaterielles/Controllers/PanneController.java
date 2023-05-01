package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.ConstatDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PanneImprimenteDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PanneOrdinateurDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Constat;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PanneController {
    @Autowired
    private PanneService panneService;

    @GetMapping("/ordinateurEnPanne")
    public ResponseEntity<List<PanneOrdinateurDTO>> ordinateurEnPanne(){
        return new ResponseEntity<>(panneService.ordinateurEnPanne(), HttpStatus.OK);
    }
    @GetMapping("/imprimenteEnPanne")
    public ResponseEntity<List<PanneImprimenteDTO>> imprimenteEnPanne(){
        return new ResponseEntity<>( panneService.imprimenteEnPanne(), HttpStatus.OK);
    }

    @PostMapping("/ajouterConstat")
    public ResponseEntity<?> ajouterConstat(@RequestBody ConstatDTO constatDTO){
        try {
            panneService.ajouterConstat(constatDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getConstats")
    public ResponseEntity<List<ConstatDTO>> getConstats(){
        return new ResponseEntity<>(panneService.getConstats(), HttpStatus.OK);
    }
}
