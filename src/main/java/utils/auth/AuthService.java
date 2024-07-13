package utils.auth;

import user.User;
import user.UserRepository;

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
            if(user.getPassword().equals(password) && user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
