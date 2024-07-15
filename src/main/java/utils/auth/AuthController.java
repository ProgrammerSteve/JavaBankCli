package utils.auth;

import user.User;

public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public User login(String username, String password){
        boolean loginSuccess = false;
        User authUser=authService.login(username,password);
        if(authUser!=null){
                System.out.println("Logged in");
                AuthSingleton instance=AuthSingleton.getInstance();
                instance.setUser(authUser);
                return authUser;
        }else{
            System.out.println("Incorrect username or password");
        }
        return null;
    }
}
