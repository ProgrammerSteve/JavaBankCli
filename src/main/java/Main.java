import account.AccountController;
import account.AccountRepository;
import account.AccountService;
import user.User;
import user.UserController;
import user.UserRepository;
import user.UserService;
import utils.DatabaseConfig;
import utils.InputUtils;
import utils.auth.AuthController;
import utils.auth.AuthService;
import utils.auth.AuthSingleton;

import javax.sql.DataSource;


class Main {
     public static void main(String[] args){
         System.out.println("Welcome to the Java Bank CLI!");
         AccountRepository accountRepository= new AccountRepository();
         UserRepository userRepository=new UserRepository(accountRepository);
         AccountService accountService=new AccountService(accountRepository,userRepository);
         AuthService authService=new AuthService(userRepository);
         AuthController authController=new AuthController(authService);
         UserService userService=new UserService(userRepository);
         UserController userController=new UserController(userService,userRepository,authController);
         AuthSingleton instance=AuthSingleton.getInstance();
         AccountController accountController=new AccountController(
            userRepository,
                  accountRepository
         );

         int actionOne;
         do {
             if(instance.getUser()==null){
                 StringBuilder sbNoAuth = new StringBuilder();
                 sbNoAuth.append("Choose an action:\n");
                 sbNoAuth.append("1:login\n");
                 sbNoAuth.append("2:register\n");
                 sbNoAuth.append("3:exit\n");
                 String messageNoAuth = sbNoAuth.toString();
                 actionOne = InputUtils.readInt(messageNoAuth);
                 switch (actionOne) {
                     case 1:
                         userController.loginUsername();
                         break;
                     case 2:
                         userController.registerUsername();
                         break;
                     case 3:
                         break;
                     default:
                         System.out.println("\nvalue not found");
                         System.out.println("try again...\n");
                 }
             }else{
                 StringBuilder sbAuth = new StringBuilder();
                 sbAuth.append("Hello "+instance.getUser().getUsername()+", Choose an action:\n");
                 sbAuth.append("1:ViewBalance\n");
                 sbAuth.append("2:Withdraw\n");
                 sbAuth.append("3:Deposit\n");
                 sbAuth.append("4:Logout\n");
                 sbAuth.append("5:Exit\n");
                 String messageAuth = sbAuth.toString();
                 actionOne = InputUtils.readInt(messageAuth);
                 switch (actionOne) {
                     case 1:
                         //View balance
                         accountController.accountViewBalance();
                         break;
                     case 2:
                         //Withdraw
                         accountController.accountWithdraw();
                         break;
                     case 3:
                         //Deposit
                         accountController.accountDeposit();
                         break;
                     case 4:
                         //Logout
                         instance.logout();
                         break;
                     case 5:
                         //exit
                         break;
                     default:
                         System.out.println("\nvalue not found");
                         System.out.println("try again...\n");
                 }
             }
         }while(instance.getUser()==null?actionOne!=3:actionOne!=5);
         System.out.println("closing app...\n");
         InputUtils.closeScanner();
     }
}
