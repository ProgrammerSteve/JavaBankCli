package user;

public class UserService {

    //sign by email
    //sign by password

    public boolean isNameFormattedCorrectly(String name){
        return !name.isEmpty();
    }

    public boolean isPasswordFormattedCorrectly(String password){
        return !password.isEmpty();
    }

    public boolean isPasswordMatching(String passwordOne, String passwordTwo){
        return passwordOne.equals(passwordTwo);
    }
}
