package hr.algebra.auth;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.User;
import hr.algebra.utilities.PasswordHasher;

import java.util.Optional;

public class AuthService {

    private final Repository repository;

    public AuthService() {
        repository = RepositoryFactory.getRepository();
    }

    public Optional<User> login(String username, char[] password) throws Exception {
        Optional<User> userOpt = repository.selectUserByUsername(username);

        if (userOpt.isEmpty()) {
            return Optional.empty();
        }

        User user = userOpt.get();

        boolean ok = PasswordHasher.verify(password, user.getPasswordHash());
        return ok ? Optional.of(user) : Optional.empty();
    }
}
