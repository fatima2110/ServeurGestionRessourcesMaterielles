package ma.fstf.ServeurGestionRessourcesMaterielles.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String login;
  private String departement;
  private String password;
  private Role role;
  private String nom;
  private String prenom;
  private String telephone;

}
