package ma.fstf.ServeurGestionRessourcesMaterielles.auth;

import lombok.RequiredArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.config.LogoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

<<<<<<< HEAD
@CrossOrigin("*")
=======
>>>>>>> b8ce556eac4b5e2030eadc704d2fd1d88cf5a3d4
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;
  private final LogoutService logoutService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
  @CrossOrigin("*")
  @GetMapping("/logout")
  public ResponseEntity<String> logout(HttpServletRequest request) {
    logoutService.logout(request,null,null);
    return new ResponseEntity<>("déconnexion réussie", HttpStatus.OK);
  }
<<<<<<< HEAD
=======


>>>>>>> b8ce556eac4b5e2030eadc704d2fd1d88cf5a3d4
}
