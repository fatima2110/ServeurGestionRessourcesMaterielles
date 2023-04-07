package ma.fstf.ServeurGestionRessourcesMaterielles.auth;

import ma.fstf.ServeurGestionRessourcesMaterielles.config.JwtService;
import lombok.RequiredArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Role;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Token;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.TokenType;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.User;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.TokenRepository;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .login(request.getLogin())
        .nom(request.getNom())
        .prenom(request.getPrenom())
        .pass(passwordEncoder.encode(request.getPassword()))
        .role(Role.FOURNISSEUR)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getLogin(),
            request.getPassword()
        )
    );
    var user = repository.findByLogin(request.getLogin())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
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
}
