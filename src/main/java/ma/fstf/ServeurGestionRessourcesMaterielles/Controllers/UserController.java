package ma.fstf.ServeurGestionRessourcesMaterielles.Controllers;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.PassWordDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.UserDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.Services.UserService;
import ma.fstf.ServeurGestionRessourcesMaterielles.Utils.UserNotFoundException;
import ma.fstf.ServeurGestionRessourcesMaterielles.Utils.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getEnseignants/{departement}")
    public ResponseEntity<List<UserDTO>> getEnseignants(@PathVariable String departement){
        return new ResponseEntity<>(userService.getEnseignants(departement), HttpStatus.OK);
    }
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDTO, HttpServletRequest request){
        try {
            userService.editUser(userDTO, request);
            return ResponseEntity.ok().build();
        } catch (WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/editProfile/{id}")
    public ResponseEntity<?> editProfile(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        try {
            userService.editProfile(id, userDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PassWordDTO passWordDTO, @NonNull HttpServletRequest request){
        try {
            userService.changePassword(passWordDTO, request);
            return ResponseEntity.ok().build();
        } catch (WrongPasswordException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
