package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MyRestController {

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }
}
