package ma.fstf.ServeurGestionRessourcesMaterielles.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String login;
  private String nom;
  private String prenom;
  private String telephone;
  private String password;
}
