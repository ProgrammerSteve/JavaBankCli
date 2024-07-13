package user;


import utils.InputUtils;
import utils.auth.AuthController;
import utils.auth.AuthSingleton;

import java.util.Optional;


public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthController authController;

    public UserController(UserService userService, UserRepository userRepository, AuthController authController) {
        this.userService=userService;
        this.userRepository=userRepository;
        this.authController=authController;
    }

    private boolean login(String userName, String Password){
        //check database
        return true;
    }

    private boolean register(String userName, String Password){
        //create in database
        return true;
    }

    public void loginUsername(){
        String username;
        boolean isNameSet=false;
        do{
            username=InputUtils.readString("Enter Username (type -1 to exit):\n").trim();
            if(username.equals("-1")){
                break;
            }else if(userService.isNameFormattedCorrectly(username)){
                isNameSet=true;
                loginPassword(username);

            } else{
                System.out.println("Incorrect format, please try again");
                continue;
            }
        }while(!isNameSet);
    }
    public void loginPassword(String username){
        String password;
        int len="password".split(" ").length;
        boolean isPasswordSet=false;
        do{
            password=InputUtils.readString("Enter Password (type -1 to exit):\n").trim();
            if(password.equals("-1")){
                break;
            }else if(userService.isPasswordFormattedCorrectly(password)){
                isPasswordSet=true;
                User user=authController.login(username,password);
                break;
            }else{
                System.out.println("Incorrect format, please try again");
                continue;
            }
        }while(!isPasswordSet);
    }








    public void registerUsername(){
        String username;
        boolean isNameSet=false;
        do{
            username=InputUtils.readString("Enter a new Username (type -1 to exit):\n").trim();
            if(username.equals("-1")){
                break;
            }else if(userService.isNameFormattedCorrectly(username)){
                isNameSet=true;
                registerPassword(username);

            } else{
                System.out.println("Incorrect format, please try again");
                continue;
            }
        }while(!isNameSet);
    }
    public void registerPassword(String username){
        String password;
        boolean isPasswordSet=false;
        do{
            password=InputUtils.readString("Enter a new Password (type -1 to exit):\n").trim();
            if(password.equals("-1")){
                break;
            }else if(userService.isPasswordFormattedCorrectly(password)){
                isPasswordSet=true;
                registerConfirmPassword(username,password);
            }else{
                System.out.println("Incorrect format, please try again");
                continue;
            }
        }while(!isPasswordSet);
    }
    public void registerConfirmPassword(String username, String password){
        String confirmPassword;
        boolean isConfirmPasswordSet=false;
        do{
            confirmPassword=InputUtils.readString("Reenter new Password (type -1 to exit):\n").trim();
            if(confirmPassword.equals("-1")){
                break;
            }else if(userService.isPasswordMatching(password,confirmPassword)){
                User newUser=new User(username, password);
                boolean isCreated=userService.createUser(newUser);
                if(!isCreated){
                    System.out.println("Error creating user, please try again");
                    registerUsername();
                    break;
                }
                isConfirmPasswordSet=true;
                System.out.println("Account Created for "+username+"!");
                break;
            } else{
                System.out.println("Passwords did not match, please try again");
                registerUsername();
                break;
            }
        }while(!isConfirmPasswordSet);
    }
}
