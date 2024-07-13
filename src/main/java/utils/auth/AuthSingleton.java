package utils.auth;

import user.User;

public class AuthSingleton {

    private User user;

    private static AuthSingleton instance = null;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void logout(){
        setUser(null);
    }


    public static synchronized AuthSingleton getInstance()
    {
        if (instance == null){
            instance = new AuthSingleton();
            return instance;
        }
        return instance;
    }
    private AuthSingleton(){

    }
}
