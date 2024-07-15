package user;

import account.Account;
import account.AccountRepository;
import utils.DatabaseConfig;

import javax.sound.midi.SysexMessage;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    DataSource dataSource= DatabaseConfig.createDataSource();
    AccountRepository accountRepository;

    public UserRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private String sqlFindByUsername="select * from USERS where username=?";
    private String sqlCreateUser="insert into USERS (username, password) values (?, ?) RETURNING user_id";

    public Optional<User> findByUsername(String username){
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(sqlFindByUsername);
            ps.setString(1,username);
            ResultSet resultSet=ps.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }
            User foundUser=new User(
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
            foundUser.setUserId(resultSet.getInt("user_id"));
            return Optional.of(foundUser);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean createUser(User user){
        try(Connection connection= dataSource.getConnection()){
            Integer userId = null;
            try (PreparedStatement insertUserPs = connection.prepareStatement(sqlCreateUser)) {
                insertUserPs.setString(1, user.getUsername());
                insertUserPs.setString(2, user.getPassword());
                ResultSet rs = insertUserPs.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt("user_id");
                }else{
                    new RuntimeException("user_id was not retrieved from create User");
                }
                Account newAccount=new Account(userId, new BigDecimal(0));
                return accountRepository.createAccount(newAccount);
            }catch(SQLException e){
                e.printStackTrace();
            }
            return false;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
