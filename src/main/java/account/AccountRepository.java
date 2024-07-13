package account;

import user.User;
import utils.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepository {
    DataSource dataSource= DatabaseConfig.createDataSource();
    private String sqlInsertAccount = "INSERT INTO accounts (balance, user_id) VALUES (?, ?)";



    public boolean createAccount(Account account){
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement insertPs=connection.prepareStatement(sqlInsertAccount);
            insertPs.setBigDecimal(1,account.getBalance());
            insertPs.setInt(2,account.getUserId());
            int insertCount=insertPs.executeUpdate();
            return insertCount>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
