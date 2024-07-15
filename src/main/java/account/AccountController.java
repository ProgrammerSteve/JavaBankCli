package account;

import user.User;
import utils.InputUtils;
import utils.auth.AuthSingleton;
import java.math.BigDecimal;

public class AccountController {

    private final AccountService accountService;
    public AccountController(
                             AccountRepository accountRepository
                           ) {
        this.accountService=new AccountService(accountRepository);

    }

    public void accountViewBalance(){
        User user=AuthSingleton.getInstance().getUser();
        if(user==null){
            System.out.println("You are not logged in to perform this action...");
            return;
        }
        System.out.println("Your balance is:"+accountService.getAccountBalance(user));
    }

    public void accountDeposit(){
        User user=AuthSingleton.getInstance().getUser();
        if(user==null){
            System.out.println("You are not logged in to perform this action...");
            return;
        }
        BigDecimal deposit;

        while (true) {
            deposit = InputUtils.readBigDecimal("Enter amount you want to deposit(-1 to exit):");
            if (deposit.compareTo(BigDecimal.valueOf(-1.0)) == 0) {
                System.out.println("Exiting deposit action...");
                return;
            }
            if (deposit.compareTo(BigDecimal.ZERO) > 0) {
                break;
            } else {
                System.out.println("Value needs to be above 0.00");
            }
        }
        accountService.depositIntoAccount(user,deposit);
    }

    public void accountWithdraw(){
        User user=AuthSingleton.getInstance().getUser();
        if(user==null){
            System.out.println("You are not logged in to perform this action...");
            return;
        }
        BigDecimal withdraw;
        BigDecimal balance=accountService.getAccountBalance(user);

        while (true) {
            System.out.println("Your current balance is: "+balance);
            withdraw = InputUtils.readBigDecimal("Enter amount you want to withdraw(-1 to exit):");
            if (withdraw.compareTo(BigDecimal.valueOf(-1.0)) == 0) {
                System.out.println("Exiting withdraw action...");
                return;
            }
            if(balance.compareTo(withdraw)>=0 && withdraw.compareTo(BigDecimal.ZERO) > 0){
                break;
            }
            else if (balance.compareTo(withdraw)<0) {
                System.out.println("Value entered exceeds amount in account...");
            } else {
                System.out.println("Value needs to be above 0.00...");
            }
        }
        accountService.withdrawFromAccount(user,withdraw);
    }


}
