package ma.fstf.ServeurGestionRessourcesMaterielles.auth;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.DepartementDto;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.EnseignantRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.config.JwtService;
import lombok.RequiredArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.TokenRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {
  private final UserRepository repository;
  private final EnseignantRepository enseignantRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    User user = new User();
    if(request.getRole().name().equals("ENSEIGNANT") || request.getRole().name().equals("CHEF_DEPARTEMENT")){
      Ensiegnant ensiegnant=new Ensiegnant();
      ensiegnant.setDepartement(request.getDepartement());
      enseignantRepository.save(ensiegnant);
      user = repository.findUserById(ensiegnant.getId());
      user.setLogin(request.getLogin());
      user.setNom(request.getNom());
      user.setPrenom(request.getPrenom());
      user.setTelephone(request.getTelephone());
      user.setPass(passwordEncoder.encode(request.getPassword()));
      user.setRole(request.getRole());
    }
    else{
      user = User.builder()
          .login(request.getLogin())
          .nom(request.getNom())
          .prenom(request.getPrenom())
          .pass(passwordEncoder.encode(request.getPassword()))
          .role(request.getRole())
          .telephone(request.getTelephone())
        .build();
    }
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getLogin(),
            request.getPassword()
        )
    );
    var user = repository.findByLogin(request.getLogin()).orElseThrow();
    var role = repository.findRoleByLogin(request.getLogin());
    var id = repository.findIdByLogin(request.getLogin());
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
                                  .token(jwtToken)
                                  .role(role)
                                  .id(id)
                                  .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
  public DepartementDto getDepartement(Integer id){
    DepartementDto departementDto=new DepartementDto();
    departementDto.setDepartement(enseignantRepository.findById(id).get().getDepartement());
    return departementDto;
  }
}
