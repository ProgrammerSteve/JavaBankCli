package account;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean createUser(Account account){
        return accountRepository.createAccount(account);
    }
}
