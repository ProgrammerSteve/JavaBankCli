package account;

import user.User;
import user.UserRepository;
import utils.InputUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository,UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository=userRepository;
    }

    public BigDecimal getAccountBalance(User user){

        Optional<BigDecimal> balanceOptional=accountRepository.viewBalance(user.getUserId());
        if(balanceOptional.isPresent()){
            return balanceOptional.get();
        }
        throw new RuntimeException("Unable to get balance using userId...");
    }
    public void depositIntoAccount(User user, BigDecimal amount){
        Optional<BigDecimal> balanceOptional=accountRepository.viewBalance(user.getUserId());
        if(balanceOptional.isPresent()){
            BigDecimal balance=balanceOptional.get();
            System.out.println("Balance before Deposit: "+balance);
            BigDecimal newBalance=amount.add(balance);
            System.out.println("Amount to add: "+amount);
            accountRepository.updateBalance(user.getUserId(),newBalance);
            System.out.println("Your new balance is: "+newBalance);
        }else{
            System.out.println("Unable to find account for User");
        }
    }


    public void withdrawFromAccount(User user, BigDecimal amount){
        Optional<BigDecimal> balanceOptional=accountRepository.viewBalance(user.getUserId());
        if(balanceOptional.isPresent()){
            BigDecimal balance=balanceOptional.get();
            System.out.println("Balance before Withdrawal: "+balance);
            BigDecimal newBalance=balance.subtract(amount);
            System.out.println("Amount to withdraw: "+amount);
            accountRepository.updateBalance(user.getUserId(),newBalance);
            System.out.println("Your new balance is: "+newBalance);
        }else{
            System.out.println("Unable to find account for User");
        }
    }
}
