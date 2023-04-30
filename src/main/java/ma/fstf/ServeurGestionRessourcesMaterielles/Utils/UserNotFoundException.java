package ma.fstf.ServeurGestionRessourcesMaterielles.Utils;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("L'utilisateur n'existe pas.");
    }
}