package account;

import java.math.BigDecimal;

public class Account {
    private Integer userId;
    private BigDecimal balance;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }



    public Account(Integer user_id, BigDecimal balance) {
        this.userId = user_id;
        this.balance = balance;
    }
}
