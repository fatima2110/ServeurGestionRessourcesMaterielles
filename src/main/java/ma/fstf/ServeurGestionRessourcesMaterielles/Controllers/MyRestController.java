package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Produit;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    ProduitRepository produitRepository;

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }
    @GetMapping("/produits")
    public List<Produit> getProduits(){
        List<Produit> produits = produitRepository.findAll();
        /*for(int i=1;i<=50;i++){
            Produit p1 = new Produit(i,"produit"+i,"produit"+i,i*100);
            produits.add(p1);
        }*/
        return produits;
    }
    @PostMapping("/ajouterProduit")
    public Produit addProduit(@RequestBody Produit produit){
        System.out.println(produit.getNom() + " " +produit.getPrix());
        return  produitRepository.save(produit);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getProduit/{id}")
    public Produit getProduit(@PathVariable int id){
        Produit produit = produitRepository.findById(id).orElse(null);
        return produit;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/editProduit/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") int id, @RequestBody Produit produit) {
        Optional<Produit> produitData = produitRepository.findById(id);

        if (produitData.isPresent()) {
            Produit _produit = produitData.get();
            _produit.setNom(produit.getNom());
            _produit.setDescription(produit.getDescription());
            _produit.setPrix(produit.getPrix());
            System.out.println(_produit.getNom() + " " +_produit.getPrix());
            return new ResponseEntity<>(produitRepository.save(_produit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
