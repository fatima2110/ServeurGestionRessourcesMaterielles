package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String login;
    private String password;
    private Role role;
    private String nom;
    private String prenom;
    private String telephone;
    private String photo;
    private String departement;
}
