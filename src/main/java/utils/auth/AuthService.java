package utils.auth;

import user.User;
import user.UserRepository;
import utils.hashing.PasswordHasher;

import java.util.Optional;

public class AuthService {
    UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password){

        Optional<User> userOptional= userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();

            if(PasswordHasher.verifyPassword(password, user.getPassword()) && user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
