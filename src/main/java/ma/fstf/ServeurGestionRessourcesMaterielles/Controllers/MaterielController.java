package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import lombok.AllArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Imprimente;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Ordinateur;
import ma.fstf.ServeurGestionRessourcesMaterielles.Representations.RepresentationMateriel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.MaterielService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor

public class MaterielController {

    private final MaterielService materielService;
    //@PostMapping("/saveOrdi/nateur")
    @PostMapping("/saveOrdinateur")
    public void saveOrdinateur(@RequestBody Ordinateur ordinateur){
        System.out.println("hello ");
        materielService.saveOrdinateur(ordinateur);
    }
    @PostMapping("/saveImprimante")
    public void saveImprimente(@RequestBody Imprimente imprimente){
        System.out.println("hello ");
       materielService.saveImprimante(imprimente);
    }
    @GetMapping("/getMateriels/{id}")
    public List<Materiel> getMateriels(@PathVariable int id){
        return materielService.getMateriels(id);
    }
}
