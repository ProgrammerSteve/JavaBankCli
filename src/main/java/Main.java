import user.UserController;
import user.UserRepository;
import user.UserService;
import utils.DatabaseConfig;
import utils.InputUtils;

import javax.sql.DataSource;


class Main {
     public static void main(String[] args){
         System.out.println("Welcome to SteveFargo!");
         UserService userService=new UserService();
         UserRepository userRepository=new UserRepository();
         UserController userController=new UserController(userService,userRepository);

         int actionOne;
         do {
             StringBuilder sb = new StringBuilder();
             sb.append("Choose an action:\n");
             sb.append("1:login\n");
             sb.append("2:register\n");
             sb.append("3:exit\n");
             String message = sb.toString();
             actionOne = InputUtils.readInt(message);

             switch (actionOne) {
                 case 1:
                     break;
                 case 2:
                     userController.registerUsername();
                     break;
                 case 3:
                     break;
                 default:
                     System.out.println("\nvalue not found");
                     System.out.println("try again...\n");
                     continue;
             }
         }while(actionOne!=3);
         System.out.println("closing app...\n");
         InputUtils.closeScanner();
     }

}
