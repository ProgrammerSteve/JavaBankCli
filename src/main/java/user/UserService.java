package user;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isNameFormattedCorrectly(String name){
        return !name.isEmpty();
    }

    public boolean isPasswordFormattedCorrectly(String password){
        return !password.isEmpty();
    }

    public boolean isNameUnique(String username){
        Optional<User> optionalUser=userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return false;
        }
        return true;
    }

    public boolean isPasswordMatching(String passwordOne, String passwordTwo){
        return passwordOne.equals(passwordTwo);
    }

    public boolean createUser(User user){
        return userRepository.createUser(user);
    }


}
