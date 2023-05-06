package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers.Responsable;


import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielImprimenteDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielOrdinateurDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    ResponsableService ImService;
    @Autowired

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }




//Generer Appel Offre


    /*******************************Responsable******************************/
    /******************************Recuperer Les besoins*********************/
    /*********************************les Imprimantes******************************/
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/besoin/imprimantes")
    public  ResponseEntity<List<MaterielImprimenteDTO>> getbesoinIM()
    {
        List<MaterielImprimenteDTO> listF=ImService.getbesoinIM();

        return new ResponseEntity<>(listF, HttpStatus.OK);
    }
    @GetMapping("/besoin/ordinateur")
    public  ResponseEntity<List<MaterielOrdinateurDTO>> getbesoinOrd()
    {
        List<MaterielOrdinateurDTO> listF=ImService.getbesoinOr();

        return new ResponseEntity<>(listF, HttpStatus.OK);
    }
    /************Pour generer appele offre et update id_appeloffre Material*******/
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public void  Save(@RequestBody List<MaterielImprimenteDTO> listmat)
    {
        ImService.EnregisterIm(listmat);

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/savemat")
    public void  Savemat(@RequestBody List<MaterielOrdinateurDTO> listmat)
    {
        ImService.EnregisterOm(listmat);
    }
    /***************Pour Enregisterer Material Livre******/
    /*****************************Recuprer les Meteriel *************************/
    @GetMapping("/EnregistererOrd")
    public List<MaterielOrdinateurDTO> getMaterielOrd()
    {
        return  ImService.getMatOr();
    }
    @GetMapping("/EnregistrerIm")
    public  List<MaterielImprimenteDTO> getMaterielIm()
    {
        return  ImService.getMatIm();
    }
    /*************************************Enregistrer*******************************************/
    @PostMapping("/EnregistrerMateLivreOr")
    public void SaveMaterialLivreOr(@RequestBody List<MaterielOrdinateurDTO> list)
    {

        ImService.EnregisterMatOR(list);
    }
    @PostMapping("/EnregistrerMateLivreIm")
    public void SaveMaterialLivreIm(@RequestBody List<MaterielImprimenteDTO> list)
    {
        ImService.EnregisterMatIM(list);
    }
    /*************************************Gestion des ressources***********************************/
    /************************************Recuperer les ressources********************************/
    /**********************************Les imprimente******************************************/
    @GetMapping("/ressourcesIm")
    public List<MaterielImprimenteDTO> getResources()
    {
        return   ImService.getRessourcesIm();
    }
    /***************************************Ordinateur*************************************/
    @GetMapping("/ressourcesOr")
    public  List<MaterielOrdinateurDTO> getRessourcesOr()
    {
        return ImService.getRessourcesOr();
    }
    /*************************************Suprimer les ressources**************************************************/
    @GetMapping("/suprimerim/{id}")
    public  void  suprimerIm(@PathVariable Integer id)
    {
        System.out.println("Suprimer me "+id);
        ImService.suprimerIm(id);
    }
    @GetMapping("/suprimerOr/{id}")
    public  void  suprimerOr(@PathVariable Integer id)
    {
        System.out.println("Suprimer me "+id);
        ImService.suprimerOr(id);
    }
}